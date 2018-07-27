package com.demo.util;

import java.util.UUID;

public class StringUtillUUID {




    public static void main(String[] args){
        String intellectual = "conviction,variation,delivery,protest,lawn,constitute,testify,obligation,optimize";
        new Thread(()->{
            ThreadLocal t1 = new ThreadLocal<String>();
            ThreadLocal t2 = new InheritableThreadLocal<String>();
            t1.set("父线程变量");
            t2.set("父线程变量2");
            System.out.println(Thread.currentThread().getName()+"--"+t2.get());
            new Thread(() -> System.out.println(Thread.currentThread().getName()+"--"+t2.get())).start();
        }).start();

    }

    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }

}
