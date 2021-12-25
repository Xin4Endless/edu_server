package com.xin.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String uploadAvater(MultipartFile file);
}
