package com.demo.test;

import com.demo.config.QuartzManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @Author: THIRLY
 * @Date: 2018/10/19 15:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {

    private Logger logger = LoggerFactory.getLogger(SpringbootdemoApplicationTests.class);

    @Autowired
    private QuartzManager quartzManager;

    @Autowired
    private TestTask testTask;

    @Test
    public void testCronTest() throws InterruptedException {
        quartzManager.start();
        TimeUnit.SECONDS.sleep(10);
        logger.info("start update job");
        //修改任务
        quartzManager.updateJob("testTask", "0/3 * * * * ? ");
        logger.info("end update job");
        TimeUnit.SECONDS.sleep(10);
        logger.info("start delete job");
        //删除任务
        quartzManager.deleteJob("testTask");
        logger.info("end delete job");
        TimeUnit.SECONDS.sleep(10);
        //添加任务
        logger.info("start add job");
        quartzManager.addJob("testTask", testTask.getClass().getName(), "0/3 * * * * ?");
        logger.info("end add job");
        TimeUnit.SECONDS.sleep(10);
        //修改任务
        logger.info("start update job");
        quartzManager.updateJob("testTask", "0/3 * * * * ?");
        logger.info("end update job");
        TimeUnit.SECONDS.sleep(10);
        //删除任务
        logger.info("start delete job");
        quartzManager.deleteJob("testTask");
        logger.info("end delete job");
        logger.info("end.");
    }

}

