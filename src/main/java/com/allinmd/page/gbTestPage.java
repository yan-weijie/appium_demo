package com.allinmd.page;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;

import java.text.Bidi;

/**
 * Created by Administrator on 2018/3/20.
 */
public class gbTestPage {

    public final static String I_D = "com.coinsauto.car:id/ivMine";

    public final static String USER_INPUT = "com.coinsauto.car:id/phone";

    public final static String PW_INPUT = "com.coinsauto.car:id/password";

    public final static String LOGIN_BTN = "com.coinsauto.car:id/btnLogin";


    public static AndroidDriver<AndroidElement> driver;

    public gbTestPage(AndroidDriver<AndroidElement> driver) {
        gbTestPage.driver = driver;
    }

    public static void loginApp() {
        for (int i=0; i<5; i++) {
            System.out.println("等待中ing————————————");
            sleep(5.0d);
        }

//        driver.findElement(By.id(I_D)).click();
//        sleep(1.0d);
        driver.findElement(By.id(USER_INPUT)).click();
        driver.findElement(By.id(USER_INPUT)).sendKeys("17700010009");
        sleep(1.0d);
        driver.findElement(By.id(PW_INPUT)).click();
        driver.findElement(By.id(PW_INPUT)).sendKeys("111111");
        sleep(1.0d);
        driver.findElement(By.id(LOGIN_BTN)).click();
        sleep(8.0d);
        System.out.print("测试完成");

    }


    public static void sleep(double d) {
        try {
            d *= 1000.0D;
            Thread.sleep((long)((int)d));
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

}
