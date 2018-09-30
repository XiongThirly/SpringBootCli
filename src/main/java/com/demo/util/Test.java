package com.demo.util;

import org.omg.CORBA.OBJ_ADAPTER;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Test {
    public static void main(String args[]){


        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");

        System.out.println(df.format(new Date()));

    }
}
