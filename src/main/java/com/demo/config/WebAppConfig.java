package com.demo.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.*;
import org.springframework.jmx.support.RegistrationPolicy;

import javax.servlet.MultipartConfigElement;

/**
 * @Descrption: fdfs配置
 * @author: THIRLY
 * @date: 2018/10/19 16:38
 */

@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class WebAppConfig {
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize("2MB");
        //设置总上传数据总大小
        factory.setMaxRequestSize("10MB");
        return factory.createMultipartConfig();
    }
}
