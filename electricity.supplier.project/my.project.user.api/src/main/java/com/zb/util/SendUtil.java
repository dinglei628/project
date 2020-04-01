package com.zb.util;//指定模板 ID 单发短信

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.IOException;

public class SendUtil {
    public static void main(String[] args) {

        String code = generateCode(4);
        SmsSingleSenderResult codeResult = sendCode("15190709702", code);

    }

    public static String generateCode(Integer length) {
        int i = 1;
        String code = "";
        while (length >= i) {
            double random = Math.random() * 10;
            double floor = Math.floor(random);
            code += (int) floor;
            i++;
        }
        return code;
    }


    public static SmsSingleSenderResult sendCode(String phoneNumber, String code) {
        int appid = 1400303947; // SDK AppID 以1400开头
        String appkey = "c889a597aad7684808c5c634eb2db2a5";    // 短信应用 SDK AppKey
        /*String phoneNumber = "15190700702";*/// 需要发送短信的手机号码
        int templateId = 515512; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请   // 短信模板 ID，需要在短信应用中申请
        SmsSingleSenderResult result = null;
        String smsSign = "何秋浩的学习记录"; //    // 签名 NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
        try {
            String[] params = {code};
            SmsSingleSender sender = new SmsSingleSender(appid, appkey);       //  sender.send(1,"86",phoneNumbers[0],"sss","","");
            result = sender.sendWithParam("86",
                    phoneNumber,
                    templateId,
                    params,
                    smsSign, "", "");
        /*    if (result.result == 0) {
                System.out.println("no error");
            }*/
           // System.out.println(result);
        } catch (HTTPException e) {  // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) { // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {     // 网络 IO 错误
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}