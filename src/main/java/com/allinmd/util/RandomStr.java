package com.allinmd.util;

import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/3/21.
 */
public class RandomStr {
    public RandomStr() {
    }

    public static String random() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String times = format.format(cal.getTime()).substring(0, 16);
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(times);
        StringBuffer sbr = new StringBuffer();

        while(matcher.find()) {
            matcher.appendReplacement(sbr, "");
        }

        matcher.appendTail(sbr);
        return sbr.toString();
    }

    public static int randomNum(int min, int max) {
        return (new Random()).nextInt(max - min + 1) + min;
    }

    public static String randomEmail() {
        String str = "";
        String[] hGroup = new String[]{"@sina.com", "@163.com", "@126.com", "@qq.com", "@hotmail.com", "@gmail.com", "@sohu.com"};
        String[] qGroup = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        for(int n = 0; n < 3; ++n) {
            str = str + qGroup[randomNum(0, 25)];
        }

        return str + "ypg70" + randomNum(10, 99) + hGroup[randomNum(0, 6)];
    }

    public static String randomPhone() {
        long currenTime = System.currentTimeMillis();
        String time = Long.toString(currenTime);
        String str = time.substring(time.length() - 5, time.length());
        return "127000" + str;
    }

    @Test
    public void f() {
        System.out.println(random());
    }
}
