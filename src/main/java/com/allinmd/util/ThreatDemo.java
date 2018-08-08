package com.allinmd.util;

import org.testng.annotations.Test;

/**
 * Created by Administrator on 2018/3/24.
 */
public class ThreatDemo {

    @Test
    public static void threadTest() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("这是线程");

            }
        });
        System.out.println("测试开始");
        thread.start();
    }

    public static void main(String[] args) {
        threadTest();
    }




}
