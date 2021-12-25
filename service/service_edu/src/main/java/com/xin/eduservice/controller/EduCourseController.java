package com.xin.eduservice.controller;


import com.xin.commonUtils.R;
import com.xin.eduservice.entity.vo.CourseInfoVo;
import com.xin.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Xinwb
 * @since 2021-12-22
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("addCourse")
    public R addCourse(@RequestBody CourseInfoVo courseInfoVo){
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseid",id);
    }

}

