package com.allinmd.xueqiu;

import com.allinmd.page.AndroidServer;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.GsmCallActions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;

public class appiumDemo {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() {
        driver = AndroidServer.androidDriverRun();
    }

    @Test
    public void sampleTest() throws InterruptedException {
        MobileElement el1 = (MobileElement) driver.findElementById("com.xueqiu.android:id/cancel");
        el1.click();
        Thread.sleep(3000);
//        MobileElement el2 = (MobileElement) driver.findElementById("com.xueqiu.android:id/user_profile_icon");
//        el2.click();
//        Thread.sleep(3000);
//        MobileElement el3 = (MobileElement) driver.findElementById("com.xueqiu.android:id/tv_login");
//        el3.click();
//        Thread.sleep(3000);
//        MobileElement el4 = (MobileElement) driver.findElementById("com.xueqiu.android:id/tv_login_by_phone_or_others");
//        el4.click();
//        Thread.sleep(3000);
//        MobileElement el5 = (MobileElement) driver.findElementById("com.xueqiu.android:id/register_phone_number");
//        el5.sendKeys("18910189981");
//        Thread.sleep(3000);
//        MobileElement el6 = (MobileElement) driver.findElementById("com.xueqiu.android:id/register_code");
//        el6.sendKeys("1234");
//        Thread.sleep(3000);
//        MobileElement el7 = (MobileElement) driver.findElementById("com.xueqiu.android:id/button_next");
//        el7.click();
//        Thread.sleep(3000);
//        MobileElement el8 = (MobileElement) driver.findElementById("com.xueqiu.android:id/md_buttonDefaultPositive");
//        el8.click();
    }

   // @Test
    public void touchAction () throws InterruptedException {
        Thread.sleep(3000);
        for (int i=0;i<15;i++){
            TouchAction touchAction = new TouchAction(driver);
            touchAction.press(PointOption.point(400,1500));
            touchAction.moveTo(PointOption.point(600,600));
            touchAction.release();
            touchAction.perform();
            Thread.sleep(1000);
        }

    }

    //@Test
    public void testSwipe( ) throws InterruptedException {
        for (int i=0; i<20;i++) {
            swipe(0.5,0.8, 0.5, 0.6);
            System.out.println("huandong");
        }
    }

    //@Test
    public void testApi() throws InterruptedException {

        driver.rotate(ScreenOrientation.LANDSCAPE);// 切换横屏
        Thread.sleep(2000);
        driver.rotate(ScreenOrientation.PORTRAIT);// 切换为竖屏：注意：有些APP事不支持切换横竖屏的



        driver.navigate().back(); //后退一步

    }

    @Test
    public void testCall() {
        driver.sendSMS("18910189981", "hello from 闫伟杰"); //模拟接收短信

        driver.makeGsmCall("18910189981", GsmCallActions.CALL); //模拟接电话
    }


    /***
     * app置入后台
     */
    @Test
    public void testDevice() throws Exception {
        driver.runAppInBackground(Duration.ofSeconds(3));//app置入后台

        driver.openNotifications(); //打开提示框

        //System.out.println("" + driver.getBatteryInfo().getState().ordinal()); //获取电量

        driver.getPerformanceData("com.xueqiu.android", "cpuinfo", 5); //CPU
        driver.manage().logs().getAvailableLogTypes().forEach(x-> System.out.println(x));//获取log信息
        driver.manage().logs().get("logcat").toJson().toString();


    }


    //@Test
    public void swipe (double x, double y, double xx, double yy) throws InterruptedException {
        Dimension size = driver.manage().window().getSize();

        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point((int) (size.width*x), (int) (size.height*y)));
        touchAction.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)));
        touchAction.moveTo(PointOption.point((int) (size.width*xx), (int) (size.height*yy)));
        touchAction.release();
        touchAction.perform();
        Thread.sleep(1000);

    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}
