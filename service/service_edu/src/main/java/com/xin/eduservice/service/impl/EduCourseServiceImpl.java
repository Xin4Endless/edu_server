package com.xin.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.commonUtils.ResultCode;
import com.xin.eduservice.entity.EduCourse;
import com.xin.eduservice.entity.EduCourseDescription;
import com.xin.eduservice.entity.vo.CourseInfoVo;
import com.xin.eduservice.mapper.EduCourseMapper;
import com.xin.eduservice.service.EduCourseDescriptionService;
import com.xin.eduservice.service.EduCourseService;
import com.xin.serverbase.handler.exceptionhandler.BizRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Xinwb
 * @since 2021-12-22
 */
@Service
@Slf4j
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduCourseService eduCourseService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert < 1) {
            log.error("添加eduCourse失败");
            throw new BizRuntimeException(ResultCode.ERROR, "添加eduCourse失败添加eduCourse失败");
        }

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(eduCourse.getId());
        boolean save = eduCourseDescriptionService.save(eduCourseDescription);
        if (!save) {
            log.error("添加eduCourseDescription失败");
            throw new BizRuntimeException(ResultCode.ERROR, "添加eduCourseDescription失败");
        }
        return eduCourse.getId();
    }

    @Override
    public CourseInfoVo getCourseInfo(String id) {
        EduCourse eduCourse = eduCourseService.getById(id);
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(id);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        if(eduCourse != null && eduCourseDescription != null ){
            BeanUtils.copyProperties(eduCourse,courseInfoVo);
            BeanUtils.copyProperties(eduCourseDescription,courseInfoVo);
        }
        return courseInfoVo;
    }

    @Override
    public boolean updateCourseInfo(CourseInfoVo courseInfoVo) {
        boolean flag = false;
        EduCourse eduCourse = new EduCourse();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        BeanUtils.copyProperties(courseInfoVo,eduCourseDescription);
        boolean b = eduCourseService.updateById(eduCourse);
        boolean b1 = eduCourseDescriptionService.updateById(eduCourseDescription);
        if(b && b1){
             flag = true;
        }
        return  flag;
    }

}
