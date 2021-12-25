package com.xin.eduservice.controller;


import com.xin.commonUtils.R;
import com.xin.commonUtils.ResultCode;
import com.xin.eduservice.entity.vo.SubjectList;
import com.xin.eduservice.service.EduSubjectService;
import com.xin.serverbase.handler.exceptionhandler.BizRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Xinwb
 * @since 2021-12-18
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        if(file.isEmpty()) {
            throw new BizRuntimeException(ResultCode.ERROR,"上传文件为空");
        }
        eduSubjectService.saveSubject(file,eduSubjectService);
        return R.ok();
    }

    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<SubjectList> allSubjectList = eduSubjectService.getAllSubjectList();
        return R.ok().data("list",allSubjectList);
    }
}

