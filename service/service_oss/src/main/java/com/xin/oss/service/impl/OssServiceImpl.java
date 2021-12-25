package com.xin.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.xin.oss.service.OssService;
import com.xin.oss.utils.ConstandPropertiesUitls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@Slf4j
public class OssServiceImpl implements OssService {
    @Override
    public String uploadAvater(MultipartFile file) {

        String fileName = file.getOriginalFilename();
        log.info("开始上传，文件名称为："+fileName);
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstandPropertiesUitls.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstandPropertiesUitls.KEY_ID;
        String accessKeySecret = ConstandPropertiesUitls.KEY_SECRET;
        String bucketName = ConstandPropertiesUitls.BUCKENT_NAME;
        String Url = null;
        String uuid = UUID.randomUUID().toString().replace("-","");
        fileName = uuid+fileName;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, fileName, inputStream);
            Url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            log.info("文件已上传完成，访问路径为："+Url);
            return Url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error("关闭inputStrem流出现错误",e);
                e.printStackTrace();
            }
            // 关闭OSSClient。
            ossClient.shutdown();
            log.info("ossClient已关闭");
        }
    }
}
