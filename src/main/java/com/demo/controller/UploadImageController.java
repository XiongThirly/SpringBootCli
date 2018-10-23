package com.demo.controller;



import com.demo.util.ResponseData;
import com.github.tobato.fastdfs.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;
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

/**
 * @Descrption: fdfs上传图片
 * @author: THIRLY
 * @date: 2018/10/19 16:40
 */
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




}
