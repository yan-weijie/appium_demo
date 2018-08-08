package com.allinmd.util;

import com.allinmd.page.gbTestPage;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by Administrator on 2018/3/20.
 */
public class DeviceParseCopy {

    private static String result = "";

    public DeviceParseCopy() {
    }

    public static String getAndroidVersion() {
        return getResult("ro.build.version.release");
    }

    public static String getDeviceUdid() {
        return getResult("ro.boot.serialno");
    }

    public static String getDeviceName() {
        return getResult("ro.product.model");
    }

    public static String getResult(String field) {
        List list = getDeviceInfo("adb shell getprop");

        for(int i = 0; i < list.size() - 1; ++i) {
            if(((String)list.get(i)).contains(field)) {
                String temp = ((String)list.get(i)).split(":")[1];
                result = temp.replace(" [", "").replace("]", "");
            }
        }

        return result;
    }

    private static List<String> getDeviceInfo(String command) {
        LinkedList list = new LinkedList();
        String line = "";
        //String command = "adb shell getprop";
        //String command = "adb devices";

        try {
            Process e = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(e.getInputStream()));

            while((line = reader.readLine()) != null) {
                list.add(line.toString());
            }

            e.waitFor();
        } catch (InterruptedException | IOException var5) {
            var5.getMessage();
        }

        return list;
    }

    @Test
    public static List<String> getDeviceList() {
        List list = getDeviceInfo("adb devices");
        List<String> udid =new ArrayList<>();
        for (int i=0;i<list.size();i++) {
            if(((String)list.get(i)).contains("device")) {
                String temp = list.get(i).toString().replace("\tdevice","");
                //System.out.println(temp);
                udid.add(temp);
            }
        }

        return udid;
    }

    @Test
    public void f() {
//        String path = System.getProperty("user.dir");
//        String time = RandomStr.random();
//        String cmdstr = "adb logcat -v time -f /mnt/sdcard/log"+time+".log";
//        gbTestPage.sleep(10.0d);
//
//        String pullcmd = "adb pull /mnt/sdcard/log"+time+".log "+path +"/logs/deviceLog/";
//        //List list = getDeviceInfo(cmdstr);
//
//        List list = getDeviceInfo(pullcmd);



        System.out.println("deviceName: " + getDeviceName());
        System.out.println("udid: " + getDeviceUdid());
        System.out.println("Android Version: " + getAndroidVersion());

    }
}
