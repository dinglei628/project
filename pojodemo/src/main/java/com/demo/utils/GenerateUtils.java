package com.demo.utils;


import com.demo.constant.TypeCastMapper;

public class GenerateUtils {

    /**
     * 根据表名获取类名（帕斯卡命名法）
     * @param tableName
     * @return
     */
    public static String getClassNameByTableName(String tableName){
        String firstLetter = tableName.substring(0, 1);
        String otherLetters = tableName.substring(1);

        String className = firstLetter.toUpperCase()+otherLetters;
        return className;
    }

    /**
     * 根据列名获取对应的属性名(骆驼命名法)
     * @param columnName
     * @return
     */
    public static String getPropertyNameByColumnName(String columnName){
        String firstLetter = columnName.substring(0, 1);
        String otherLetters = columnName.substring(1);

        String propertyName = firstLetter.toLowerCase()+otherLetters;
        return propertyName;
    }
    /**
     * 根据列名获取对应的属性名(帕斯卡名法)
     * @param columnName
     * @return
     */
    public static String getUpperPropertyNameByColumnName(String columnName){
        String firstLetter = columnName.substring(0, 1);
        String otherLetters = columnName.substring(1);

        String propertyName = firstLetter.toUpperCase()+otherLetters;
        return propertyName;
    }
    /**
     * 根据列类型获取对应的java类型
     * @param columnType
     * @return
     */
    public static String getJavaTypeByColumnType(String columnType){
        return TypeCastMapper.TYPEMAP.get(columnType);
    }

}
