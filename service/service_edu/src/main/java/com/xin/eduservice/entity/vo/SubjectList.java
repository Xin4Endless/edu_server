package com.xin.eduservice.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class SubjectList {
    private String title;
    private String id;
    private String parentId;
    private List<SubjectList> children;
}
