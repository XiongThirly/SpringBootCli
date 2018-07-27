package com.demo.test;

import com.demo.util.RedisUtil;
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

/*    @Test
    public void contextLoads() {
        ContractVO c = new ContractVO();
        c.setContractText("255225");

      RedisUtil.set("8",c);

        System.out.println(RedisUtil.get("8"));
    }*/



}