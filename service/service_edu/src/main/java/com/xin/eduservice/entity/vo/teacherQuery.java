package com.xin.eduservice.entity.vo;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class teacherQuery {
    private String name;
    private Integer level;
    private String begin;
    private String end;
}
