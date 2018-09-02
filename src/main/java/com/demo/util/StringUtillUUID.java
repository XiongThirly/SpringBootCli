package com.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class StringUtillUUID {




    public static void main(String[] args){
        int countTime = 60 * 60 * 24;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String time = df.format(new Date());
        String [] arr = time.split(":");
        int [] arr2 = new int[3];
        for(int i = 0; i < arr.length; i++) {
            arr2[i] = Integer.parseInt(arr[i]);
        }
        int currentTime = arr2[0] * 60 * 60 + ( arr2[1] * 60) + arr2[2];
        System.out.println(countTime - currentTime);

    }

    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }

    public static int getSecondTimestampTwo(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime()/1000);
        return Integer.valueOf(timestamp);
    }


}
