package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.dto.Page;
import com.zb.entity.User;
import com.zb.feign.AuthFeignClient;
import com.zb.feign.OrderFeignClient;
import com.zb.mapper.GoodMapper;
import com.zb.pojo.Video_data;
import com.zb.pojo.Videoaddress;
import com.zb.pojo.Videotype;
import com.zb.service.GoodService;
import com.zb.util.RedisUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.elasticsearch.common.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GoodServiceImpl implements GoodService {
    @Autowired(required = false)
    GoodMapper goodMapper;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    AuthFeignClient authFeignClient;

    @Autowired
    OrderFeignClient orderFeignClient;


    @Override
    @Cacheable(value = "cache" ,key="#id")
    public Dto getVideoById(Integer id, Integer pageSize, String token) {
        //判断用户是否登录
        if(authFeignClient.userinfo(token) != null){
            Video_data notVip = goodMapper.getNotVip(id);
            //判断视频是否是会员或免费或需要购买才能观看  0:免费  1:会员 2:购买
            if(notVip.getNotVip() == 0){
                List<Video_data> videoByList = goodMapper.getVideoByList(pageSize);
                for (Video_data v:videoByList){
                    if(v.getId() == id){
                        System.out.println("热点数据。。。");
                        //return DtoUtil.returnSuccess(id.toString(),pageSize);
                        return toVideoById(id,pageSize,token);
                    }
                }
                Video_data videoById = null;
                String key = "good_"+id;
                if(redisUtils.hasKey(key)){
                    System.out.println("1redis中获取数据。。。");
                    String json = redisUtils.get(key).toString();
                    videoById = JSON.parseObject(json, Video_data.class);
                    System.out.println("1redis中获取数据。。。");
                }else{
                    System.out.println("1mysql中获取数据。。。");
                    videoById = goodMapper.getVideoById(id);
                    redisUtils.set(key, JSON.toJSONString(videoById));
                }
                goodMapper.UpVideoById(id);
                if(notVip.getNotVip() == 1 && token.equals("会员")){
                    //视频信息为1(会员)并且登录用户也是会员可以观看
                }
                if(notVip.getNotVip() == 2){
                    //进入购买页面
                    //直接调用的创建订单方法
                    orderFeignClient.createOrder(token,id.toString());
                }
                //返回商品编号
                DtoUtil.returnDataSuccess(videoById);
                //return videoById;
            }
            //返回是否是会员编号
            DtoUtil.returnDataSuccess(notVip);
            //return notVip;
        }else{
            //调用登录方法
        }
       return DtoUtil.returnSuccess("。。。。");
    }

    @Override
    public List<Videoaddress> getVideoAddRess(Integer videoTypeId) {
        return goodMapper.getVideoAddRess(videoTypeId);
    }

    /**
     * 查询热点数据
     * @param id
     * @param pageSize
     * @return
     */
    public Dto toVideoById(Integer id,Integer pageSize,String token) {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Video_data notVip = goodMapper.getNotVip(id);
        if(notVip.getNotVip() == 0){
            Video_data videoById = null;
            String key = "good_"+id;
            if(redisUtils.hasKey(key)){
                System.out.println("2redis中获取数据");
                String json = redisUtils.get(key).toString();
                videoById = JSON.parseObject(json, Video_data.class);
                System.out.println("2redis中获取数据");
            }else{
                System.out.println("2mysql中获取数据");
                videoById = goodMapper.getVideoById(id);
                redisUtils.set(key, JSON.toJSONString(videoById));
            }
            goodMapper.UpVideoById(id);
            if(notVip.getNotVip() == 1 && token.equals("会员")){
                //视频信息为1(会员)并且登录用户也是会员可以观看
            }
            if(notVip.getNotVip() == 2){
                //进入购买页面
                //直接调用的创建订单方法
                orderFeignClient.createOrder(authFeignClient.userinfo(token).toString(),id.toString());
            }
            return DtoUtil.returnDataSuccess(videoById);
        }
        return DtoUtil.returnSuccess("。。。。");
        //return videoById;
    }



    @Autowired(required = false)
    private RestHighLevelClient restHighLevelClient;

    @Override
    public Page<Video_data> findVideoByPage(Integer index, Integer size, String key)throws Exception {
        List<Video_data> list=new ArrayList<>();
        //全部查询
        SearchRequest searchRequest=new SearchRequest("video_data");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.from((index-1)*size);
        searchSourceBuilder.size(size);
        //创建bool组合查询对象
        BoolQueryBuilder boolQueryBuilder=new BoolQueryBuilder();
        if(key != null){
            //第一个条件
            MultiMatchQueryBuilder multiMatchQueryBuilder= QueryBuilders.multiMatchQuery(key,"videoTitle","typeVideoName");
            multiMatchQueryBuilder.field("videoTitle",10);
            boolQueryBuilder.must(multiMatchQueryBuilder);
        }
        //资源与查询绑定
        searchSourceBuilder.query(boolQueryBuilder);
        //设置高亮对象
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        highlightBuilder.preTags("<div style='color:red'>");
        highlightBuilder.postTags("</div>");
        highlightBuilder.fields().add(new HighlightBuilder.Field("videoTitle"));
        highlightBuilder.fields().add(new HighlightBuilder.Field("typeVideoName"));
        searchSourceBuilder.highlighter(highlightBuilder);

        //获取要显示的数
        searchRequest.source(searchSourceBuilder);
        SearchResponse response=restHighLevelClient.search(searchRequest);
        SearchHits hits=response.getHits();
        SearchHit[] searchHits=hits.getHits();
        //总条数
        Long totalHits = hits.totalHits;
        for(SearchHit hit : searchHits){
            String id=hit.getId();
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(JSON.toJSONString(sourceAsMap)+"aaaaaaaaaaaaaa");
            String videoTitle = sourceAsMap.get("videoTitle").toString();
            String typeVideoName = sourceAsMap.get("typeVideoName").toString();


            Integer id1 = Integer.parseInt(sourceAsMap.get("id").toString());
            String lecturerName=sourceAsMap.get("lecturerName").toString();
            Integer difficultyLevel=Integer.parseInt(sourceAsMap.get("difficultyLevel").toString());
            Integer browseCount=Integer.parseInt(sourceAsMap.get("browseCount").toString());
            String uploadTime=sourceAsMap.get("uploadTime").toString();
            Integer notUpload=Integer.parseInt(sourceAsMap.get("notUpload").toString());
            Integer videoTypeId=Integer.parseInt(sourceAsMap.get("videoTypeId").toString());
            Double videoPrice=Double.parseDouble(sourceAsMap.get("videoPrice").toString());
            Integer notVip=Integer.parseInt(sourceAsMap.get("notVip").toString());
            Integer lowerShelf=Integer.parseInt(sourceAsMap.get("lowerShelf").toString());
            //获取高亮的结果数据
            Map<String, HighlightField> highlightFields=hit.getHighlightFields();
            if(highlightBuilder!=null){
                HighlightField nameField=highlightFields.get("videoTitle");
                if(nameField!=null){
                    Text[] nameTxt = nameField.getFragments();
                    StringBuffer nameStr=new StringBuffer();
                    for (Text text : nameTxt){
                        nameStr.append(text);
                    }
                    videoTitle=nameStr.toString();
                }
                HighlightField titleField = highlightFields.get("typeVideoName");
                if(titleField!=null){
                    Text[] titleTxt = titleField.getFragments();
                    StringBuffer titleStr=new StringBuffer();
                    for (Text text : titleTxt){
                        titleStr.append(text);
                    }
                    typeVideoName=titleStr.toString();
                }
            }
            Video_data v = new Video_data();
            v.setId(id1);
            v.setVideoTitle(videoTitle);
            v.setTypeVideoName(typeVideoName);
            v.setLecturerName(lecturerName);
            v.setDifficultyLevel(difficultyLevel);
            v.setBrowseCount(browseCount);
            v.setUploadTime(uploadTime);
            v.setNotUpload(notUpload);
            v.setVideoTypeId(videoTypeId);
            v.setVideoPrice(videoPrice);
            v.setNotVip(notVip);
            v.setLowerShelf(lowerShelf);
            list.add(v);
        }
        Page<Video_data> page =new Page<>(index,size,totalHits.intValue(),list);
        return page;
    }

    @Override
    @Cacheable(value = "cache" ,key="#typeId")
    public List<Video_data> getTypeSel(Integer typeId) {
        List<Video_data> list = null;
        String key = "VideoType:"+typeId;
        if(redisUtils.hasKey(key)){
            System.out.println("redis");
            String s = redisUtils.get(key);
            list = JSON.parseArray(s, Video_data.class);
        }else{
            System.out.println("db");
            list = goodMapper.getTypeSel(typeId);
            redisUtils.set(key,JSON.toJSONString(list));
        }
        return list;
    }


}
