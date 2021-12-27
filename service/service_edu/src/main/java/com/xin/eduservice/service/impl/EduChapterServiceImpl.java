package com.xin.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.eduservice.entity.EduChapter;
import com.xin.eduservice.entity.vo.EduChapterListVo;
import com.xin.eduservice.mapper.EduChapterMapper;
import com.xin.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Xinwb
 * @since 2021-12-22
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduChapterMapper eduChapterMapper;


    @Override
    public List<EduChapterListVo> getChapterList(String coruseId) {
        List<EduChapterListVo> rootList = eduChapterMapper.getChapterList(coruseId);
        List<EduChapterListVo> finalList = new ArrayList<EduChapterListVo>();
        for (EduChapterListVo eduChapterListVo : rootList) {
            if(eduChapterListVo.getParentId().equals("0")){
                finalList.add(eduChapterListVo);
            }
        }
        for (EduChapterListVo finallist : finalList) {
            finallist.setChildren(getChildSubjectList(finallist.getId(),rootList));
        }
        return finalList;
    }

    @Override
    public boolean deleteChapter(String id) {
        //TODO 暂未实现级联删除
        boolean flag = removeById(id);
        return flag;
    }

    private List<EduChapterListVo> getChildSubjectList(String id, List<EduChapterListVo> RootList) {
        List<EduChapterListVo> childList = new ArrayList<>();
        for (EduChapterListVo eduChapterListVo : RootList) {
            if (eduChapterListVo.getId() != null && eduChapterListVo.getId() != "") {
                if (eduChapterListVo.getParentId().equals(id)) {
                    childList.add(eduChapterListVo);
                }
            }
        }
        //递归所有子菜单
        for (EduChapterListVo eduChapterListVo : childList) {
            eduChapterListVo.setChildren(getChildSubjectList(eduChapterListVo.getId(),RootList));
        }
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
