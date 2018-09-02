package com.demo.test;

import com.demo.util.RedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisLockTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLock.class);
    @Autowired
    private RedisLock redisLock;

    @Test
    public void test()throws Exception{

        RedisLock redisLockN = redisLock.newInstance("", 10000);
        if (redisLockN.lock()) {
            //业务代码
            redisLockN.unlock();
            LOGGER.info(" 释放redis分布式锁");
        } else {
            LOGGER.error("获取redis分布式锁失败 请检查");
            throw new Exception();
        }
    }

    @Test
    public void binary(){
        System.out.print(2 >> 2);
    }

}
