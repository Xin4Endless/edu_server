package com.xin.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.eduservice.entity.EduSubject;
import com.xin.eduservice.entity.vo.SubjectList;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Xinwb
 * @since 2021-12-18
 */
@Service
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<SubjectList> getAllSubjectList();
}
