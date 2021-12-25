package com.xin.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.eduservice.Listener.SubjectExcelListener;
import com.xin.eduservice.entity.EduSubject;
import com.xin.eduservice.entity.excel.SubjectData;
import com.xin.eduservice.entity.vo.SubjectList;
import com.xin.eduservice.mapper.EduSubjectMapper;
import com.xin.eduservice.service.EduSubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Xinwb
 * @since 2021-12-18
 */
@Service
@Slf4j
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService, AutoCloseable {

    @Autowired
    private EduSubjectMapper eduSubjectMapper;

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try (InputStream ips = file.getInputStream()) {
            EasyExcel.read(ips, SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            log.error("读取文件错误", e);
            e.printStackTrace();
        }
    }

    @Override
    public List<SubjectList> getAllSubjectList() {
        //找到所有课程数据
        List<SubjectList> RootList = eduSubjectMapper.getAllSubjectList();
        List<SubjectList> finalSubjectList = new ArrayList<SubjectList>();
        //找到所有一级课程分类（parentID为0）
        for (SubjectList list : RootList) {
            if (list.getParentId().equals("0")) {
                //添加到最终返回的List集合中
                finalSubjectList.add(list);
            }
        }
        //遍历一级分类集合，找到下面的子集
        for (SubjectList list : finalSubjectList) {
            list.setChildren(getChildSubjectList(list.getId(),RootList));
        }
        return finalSubjectList;
    }

    /**
     *
     * @param id 父级ID
     * @param RootList 最原始的数据集合
     * @return
     */
    private List<SubjectList> getChildSubjectList(String id, List<SubjectList> RootList) {
        List<SubjectList> childList = new ArrayList<>();
        for (SubjectList subjectList : RootList) {
            if (subjectList.getId() != null && subjectList.getId() != "") {
                if (subjectList.getParentId().equals(id)) {
                    childList.add(subjectList);
                }
            }
        }
        //递归所有子菜单
        for (SubjectList subjectList : childList) {
            subjectList.setChildren(getChildSubjectList(subjectList.getId(),RootList));
        }
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    @Override
    public void close() throws Exception {
        log.info("正在关闭流");
    }
}

