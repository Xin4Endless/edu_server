package com.xin.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.eduservice.entity.EduSubject;
import com.xin.eduservice.entity.vo.SubjectList;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author Xinwb
 * @since 2021-12-18
 */

public interface EduSubjectMapper extends BaseMapper<EduSubject> {

    List<SubjectList> getAllSubjectList();
}
