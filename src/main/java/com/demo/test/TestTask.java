package com.demo.test;

import com.demo.task.AbstractTask;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @Author: THIRLY
 * @Date: 2018/10/19 15:55
 */
@Component("testTask")
public class TestTask extends AbstractTask {

    private Logger logger = LoggerFactory.getLogger(TestTask.class);

    @PostConstruct
    public void init() {
        this.cronExpression = "0/2 * * * * ? ";
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {
        logger.info("test task start execute.");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            logger.info("test task execute interrupted.");
        }
        logger.info("test task execute end.");
    }
}
