package com.allinmd.page;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class xueqiuPage {

    //首次啓動彈窗關閉按鈕(id)
    @FindBy(id = "cancel")
    public static AndroidElement cancel_btn;

    //首頁左上角(id)
    @FindBy(id = "user_profile_icon")
    public static AndroidElement user_btn;

    public static AndroidDriver driver;

    public xueqiuPage(AndroidDriver driver){
        PageFactory.initElements(driver,this);
        //this.driver = driver;

    }

    public static void login() throws InterruptedException {
        Thread.sleep(3000);
        cancel_btn.click();
        Thread.sleep(3000);

        user_btn.click();
    }


}
