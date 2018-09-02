package com.demo.controller;



import com.github.tobato.fastdfs.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UploadImageController {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private FdfsWebServer fdfsWebServer;

    @PostMapping("/article/img/fdfs")
    public Map<String,String> uploadImgfdfs(@RequestParam(value = "file") MultipartFile multipartFile, HttpServletRequest request) throws IOException,SocketException {
        String g = request.getHeader("1");
        Map<String,String> map = new HashMap<>(1);
        //图片服务器地址
        String baseUrl = "http://139.199.164.21/";
        String fileName = multipartFile.getOriginalFilename();
        //设置文件后缀
        int index = fileName.indexOf(".") + 1;
        String fileSuffix = fileName.substring(index);
        StorePath storePath= storageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(), fileSuffix, null);
        String path = storePath.getFullPath();
        map.put("url",baseUrl+path);
        return map;
    }


    /*  将图片上传至本地
        public Map<String,String>  uploadImg(@RequestParam("file") MultipartFile multipartFile)  {
        Map<String,String> map = new HashMap<>();
        String location = "F:/img/";
        if (multipartFile.isEmpty() || StringUtils.isBlank(multipartFile.getOriginalFilename())) {

        }
        String contentType = multipartFile.getContentType();
        if (!contentType.contains("")) {

        }
        String root_fileName = multipartFile.getOriginalFilename();

        //处理图片
       // User currentUser = userService.getCurrentUser();
        //获取路径
       // String return_path = ImageUtil.getFilePath(currentUser);
        String filePath = location + ""*//*return_path*//*;

        String file_name = null;
        try {
            file_name = ImageUtil.saveImg(multipartFile, filePath);

            map.put("","");
            if(StringUtils.isNotBlank(file_name)){

            }


        } catch (IOException e) {

        }
        return map;*/

}
