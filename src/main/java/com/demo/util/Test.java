package com.demo.util;

import org.omg.CORBA.OBJ_ADAPTER;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Test {
    public static void main(String args[]){

        List<Object> list = new ArrayList<>();
        list.add(5);
        list.add("5");

        System.out.println(list.toArray());

    }
}
