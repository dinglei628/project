package ${package}.pojo;
import java.io.Serializable;
import java.util.Date;

/**
*   pojo of ${table.tableName}
*/
public class ${table.className} implements Serializable {
<#list table.columns as column>
    //${column.comment}
    private ${column.javaType} ${column.propertyName};
</#list>
    //getter setter
<#list table.columns as column>
    public void set${column.upperPropertyName} (${column.javaType}  ${column.propertyName}){
        this.${column.propertyName}=${column.propertyName};
    }
    public  ${column.javaType} get${column.upperPropertyName}(){
        return this.${column.propertyName};
    }
</#list>
}
