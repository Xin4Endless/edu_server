package com.xin.eduservice.controller;


import com.xin.commonUtils.R;
import com.xin.eduservice.entity.EduChapter;
import com.xin.eduservice.entity.vo.EduChapterListVo;
import com.xin.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Xinwb
 * @since 2021-12-22
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @GetMapping("getChapterList/{coruseId}")
    public R getChapterList(@PathVariable String coruseId) {
        List<EduChapterListVo> list = eduChapterService.getChapterList(coruseId);
        return R.ok().data("list", list);
    }

    @GetMapping("getChapter/{id}")
    public R getChapter(@PathVariable String id) {
        EduChapter chapterInfo = eduChapterService.getById(id);
        return R.ok().data("chapterInfo", chapterInfo);
    }

    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter) {
        boolean save = eduChapterService.save(eduChapter);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter) {
        boolean updateById = eduChapterService.updateById(eduChapter);
        if (updateById) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("deleteChapter/{id}")
    public R deleteChapter(@PathVariable String id) {
        eduChapterService.deleteChapter(id);
        return R.ok();
    }

}

