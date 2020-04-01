package com.demo.handler;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

public class TemplateHandler {

    Configuration configuration;

    public void init() throws Exception {
        //实例化Configuration对象
        configuration = new Configuration(Configuration.getVersion());
        //设置模板所在路径
        String path = this.getClass().getClassLoader().getResource("").getPath();
        configuration.setDirectoryForTemplateLoading(new File(path));
    }

    public void process(Map map,String templateName, String destPath) throws Exception {
        //根据模板文件名获取模板对象
        Template template = configuration.getTemplate(templateName);

        //目标文件
        FileOutputStream fileOutputStream = new FileOutputStream(destPath);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

        //生成目标文件，并保存
        template.process(map, outputStreamWriter);
    }

}
