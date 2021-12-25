package com.xin.eduservice.service;

import com.xin.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.eduservice.entity.vo.CourseInfoVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Xinwb
 * @since 2021-12-22
 */
@Service
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String id);

    boolean updateCourseInfo(CourseInfoVo courseInfoVo);
}
