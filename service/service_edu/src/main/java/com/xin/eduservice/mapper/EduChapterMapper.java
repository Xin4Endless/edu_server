package com.xin.eduservice.mapper;

import com.xin.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.eduservice.entity.vo.EduChapterListVo;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Xinwb
 * @since 2021-12-22
 */
public interface EduChapterMapper extends BaseMapper<EduChapter> {

    List<EduChapterListVo> getChapterList(String coruseId);
}
