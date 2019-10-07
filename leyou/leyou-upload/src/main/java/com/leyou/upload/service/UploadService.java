package com.leyou.upload.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {

    // 常用文件對照表 http://tool.oschina.net/commons
    private static final List<String> leagal_content_typs = Arrays.asList("jpg","gif");

    public String uploadImage(MultipartFile file) {


        // 校驗文件類型
        String originalFileName = file.getOriginalFilename();
        //StringUtils.substringAfterLast()
        String contentTyp = file.getContentType();


        // 校驗文件類型

        // 保存到服務器

        // 返回url
    }
}
