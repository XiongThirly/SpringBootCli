package com.demo.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 抽象任务类
 * @Author: THIRLY
 * @Date: 2018/10/19 15:54
 */
public abstract class AbstractTask implements Job {

    private Logger logger = LoggerFactory.getLogger(AbstractTask.class);

    protected abstract void executeInternal(JobExecutionContext context);

    protected String cronExpression;

    @Override
    public void execute(JobExecutionContext context) {
        try {
            executeInternal(context);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error("job execute failed!");
        }
    }

    public String getCronExpression() {
        return cronExpression;
    }
}

