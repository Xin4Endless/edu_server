package com.xin.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.eduservice.entity.EduChapter;
import com.xin.eduservice.entity.vo.EduChapterListVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Xinwb
 * @since 2021-12-22
 */
public interface EduChapterService extends IService<EduChapter> {

    List<EduChapterListVo> getChapterList(String coruseId);

    boolean deleteChapter(String id);
}
