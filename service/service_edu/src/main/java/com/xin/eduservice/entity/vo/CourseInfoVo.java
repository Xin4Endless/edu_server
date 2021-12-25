package com.xin.eduservice.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseInfoVo {
    //课程ID
    private String id;
    //讲师ID
    private String teacherId;
    //课程分类父ID
    private String subjectParentId;
    //课程分类ID
    private String subjectId;
    //课程标题
    private String title;
    //课程价钱（为0可以免费观看）
    private BigDecimal price;
    //总课时
    private Integer lessonNum;
    //课程封面图路径
    private String cover;
    //课程简介
    private String description;
}
