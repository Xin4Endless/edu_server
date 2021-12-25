package com.xin.oss.controller;

import com.xin.commonUtils.R;
import com.xin.commonUtils.ResultCode;
import com.xin.oss.service.OssService;
import com.xin.serverbase.handler.exceptionhandler.BizRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("upload")
    public R uploadOssFile(MultipartFile file) {
        if (file == null) {
            throw new BizRuntimeException(ResultCode.ERROR,"上传文件不能为空");
        }
        String url = ossService.uploadAvater(file);
        return R.ok().data("url", url);
    }

}
