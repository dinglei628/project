package com.demo.pojo;
import java.io.Serializable;
import java.util.Date;

/**
*   pojo of bug_detail
*/
public class Bug_detail implements Serializable {
    //
    private Integer id;
    //
    private Integer projectId;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //
    private Integer severity;
    //
    private String title;
    //
    private String reportUser;
    //
    private Date createDate;
    //getter setter
    public void setId (Integer id){
        this.id=id;
    }
    public Integer getId(){
        return this.id;
    }
    public void setProjectId (Integer projectId){
        this.projectId=projectId;
    }
    public Integer getProjectId(){
        return this.projectId;
    }
    public void setSeverity (Integer severity){
        this.severity=severity;
    }
    public Integer getSeverity(){
        return this.severity;
    }
    public void setTitle (String title){
        this.title=title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setReportUser (String reportUser){
        this.reportUser=reportUser;
    }
    public String getReportUser(){
        return this.reportUser;
    }
    public void setCreateDate (Date createDate){
        this.createDate=createDate;
    }
    public Date getCreateDate(){
        return this.createDate;
    }
}
