package com.xin.eduservice.Listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xin.commonUtils.ResultCode;
import com.xin.eduservice.entity.EduSubject;
import com.xin.eduservice.entity.excel.SubjectData;
import com.xin.eduservice.service.EduSubjectService;
import com.xin.serverbase.handler.exceptionhandler.BizRuntimeException;


public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //SubjectExcelListener不可以交给Spring管理 需要自己new，不可以注入其它对象
    public EduSubjectService eduSubjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.eduSubjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new BizRuntimeException(ResultCode.ERROR, "文件内容为空");
        }
        EduSubject existsOne = this.ExistsSubjectForOne(eduSubjectService, subjectData.getOneSubjectName());
        if (existsOne == null) {
            existsOne = new EduSubject();
            existsOne.setParentId("0");
            existsOne.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(existsOne);
        }
        EduSubject existsTow = this.ExistsSubjectForTwo(eduSubjectService, subjectData.getTwoSubjectName(), existsOne.getId());
        if (existsTow == null) {
            existsTow = new EduSubject();
            existsTow.setParentId(existsOne.getId());
            existsTow.setTitle(subjectData.getTwoSubjectName());
            eduSubjectService.save(existsTow);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    private EduSubject ExistsSubjectForOne(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    private EduSubject ExistsSubjectForTwo(EduSubjectService eduSubjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject towSubject = eduSubjectService.getOne(wrapper);
        return towSubject;
    }
}
