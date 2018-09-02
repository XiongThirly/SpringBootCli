package com.demo.test;

import com.demo.model.UserBean;
import com.demo.util.RedisUtil;
import org.apache.catalina.mbeans.UserMBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

    @Autowired
    private RedisTemplate<Object, Object> template;

   @Test
    public void contextLoads() {

       UserBean u = new UserBean();
       u.setUsername("1");
      RedisUtil.set("8",u);

        System.out.println(RedisUtil.get("8"));
    }






}