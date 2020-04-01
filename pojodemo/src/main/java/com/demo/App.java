package com.demo;

import com.demo.bean.Column;
import com.demo.bean.Table;
import com.demo.constant.AppContants;
import com.demo.handler.TemplateHandler;
import com.demo.utils.PojoUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) {
//        App app = new App();
//        try {
//            app.init();
//            app.process("demo.ftl", "d://demo.html");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



        TemplateHandler th = new TemplateHandler();
        try {
            th.init();

            List<Table> tables = PojoUtils.getTables();

            for (Table t : tables) {
                System.out.println(t.getTableName() + ":" + t.getClassName());
                for (Column col : t.getColumns()) {
                    System.out.printf("%s\t%s\t%s\t%s\t%s%n", col.getColumnName(), col.getColumnType(), col.getPropertyName(), col.getJavaType(), col.getComment());
                }
                System.out.println();

                Map map = new HashMap();
                map.put("package", AppContants.PACKAGE);
                map.put("table",t);
                th.process(map,"pojo.ftl","d://"+t.getClassName()+".java");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
