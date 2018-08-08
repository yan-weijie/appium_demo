package com.allinmd.xueqiu;

import com.allinmd.page.AndroidServer;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestZuoye {
    public static AndroidDriver driver;

    @BeforeClass
    public void setup() {
        driver= AndroidServer.androidDriverRun();
    }

    @Test
    public void getDeviceInfo(String parameter) throws Exception {

        List<List<Object>> performanceData;
        if (parameter.contains("cpu")) {
            performanceData = driver.getPerformanceData("com.xueqiu.android", "batteryinfo" , 5);
        } else if (parameter.contains("battery")) {
            performanceData = driver.getPerformanceData("com.xueqiu.android", "batteryinfo" , 5);

        }else {
            performanceData = driver.getPerformanceData("com.xueqiu.android", "batteryinfo" , 5);

        }


        System.out.println("电量：" + performanceData); //获取电量
    }

    /**
     * 截图
     */
    public void screenshot() throws IOException {
        FileUtils.copyFile(
                driver.getScreenshotAs(OutputType.FILE).getCanonicalFile() ,new File( "path.png")
        );
    }

    /**
     * 处理toast
     */
    @Test
    public void testToast(){
        driver.findElementByXPath("//*[@text='Views']").click();
        driver.findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" +
                        "new UiSelector().text(\"Popup Menu\").instance(0));").click();
        driver.findElementByXPath("//*[contains(@text, 'Make')]").click();
        driver.findElementByXPath("//*[@text='Search']").click();
        //System.out.println(driver.findElementByClassName("android.widget.Toast").getText());
        System.out.println(driver.findElementByXPath("//*[@class='android.widget.Toast']").getText()); //找到

        //driver.getPageSource(); //逻辑不一样无法获取toast

        System.out.println("操作完成");
    }

}
