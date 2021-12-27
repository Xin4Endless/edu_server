package com.xin.eduservice.entity.vo;

import lombok.Data;

import java.util.List;
@Data
public class EduChapterListVo {
    private String id;
    private String title;
    private String parentId;
    private String courseId;
    private List<EduChapterListVo> children;
}
