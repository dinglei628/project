package com.zb.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Curriculum  implements Serializable {

    String id;
    String title;
    String describe;
    Date lastLookTime;
    String coverImage;
    String userId;
    String courseId;



}
