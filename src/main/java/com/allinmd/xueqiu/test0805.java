package com.allinmd.xueqiu;

import com.allinmd.page.AndroidServer;
import com.allinmd.page.xueqiuPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class test0805 {
    public static AndroidDriver driver;

    @BeforeClass
    public void setup() {

        driver = AndroidServer.androidDriverRun();
        new xueqiuPage(driver);

    }

    @Test
    public static void loginXueqiu() throws InterruptedException {

        xueqiuPage.login();

    }
}
