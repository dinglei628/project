package com.demo.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 类型转换映射器
 */
public class TypeCastMapper {
    public static Map<String,String> TYPEMAP = new HashMap<String,String>();

    static{
        TYPEMAP.put("BIGINT","Long");
        TYPEMAP.put("INT","Integer");
        TYPEMAP.put("VARCHAR", "String");
        TYPEMAP.put("TEXT", "String");
        TYPEMAP.put("DATETIME", "Date");
        TYPEMAP.put("DATE", "Date");
        TYPEMAP.put("DECIMAL", "Double");
        TYPEMAP.put("TIMESTAMP","Date");
        TYPEMAP.put("BIT","Integer");
        TYPEMAP.put("FLOAT","float");
    }
}
