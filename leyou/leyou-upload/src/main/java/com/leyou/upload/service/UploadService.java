package com.leyou.upload.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UploadService {

    // 先到這裡查找常用文件類型對照表 content-type http://tool.oschina.net/commons

    // 定義合法文件類型
    private static final List<String> leagal_content_typs = Arrays.asList("image/gif","image/jpeg","image/jpg");

    // 定義log對象
    //private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UploadService.class);

    public String uploadImage(MultipartFile file) throws IOException {


        // 校驗文件類型
        String originalFileName = file.getOriginalFilename();
        //StringUtils.substringAfterLast()

        // 校驗文件類型
        String contentTyp = file.getContentType();
        if(!leagal_content_typs.contains(contentTyp)){
            ///LOGGER.info("文件類型不合法 : "+file.getOriginalFilename());
            return null;
        }

        // 校驗文件內容
        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        if(bufferedImage == null ){  // || bufferedImage.getWidth() 檢查高寬
            ///LOGGER.info("文件內容為空 : "+file.getOriginalFilename());
            return null;
        }

        // 保存到文件服務器
        file.transferTo(new File("E:/web/SSM-T06/leyou/leyou-upload/images/"+originalFileName));

        // 返回url，進行回顯 (進 Nginx配置和 Host 增加 )
        return "http://image.leyou.com/"+originalFileName;
    }
}
