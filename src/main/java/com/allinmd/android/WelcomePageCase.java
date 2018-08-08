package com.allinmd.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.server.SystemClock;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.allinmd.page.AndroidServer;
import com.allinmd.page.gbTestPage;
import com.allinmd.util.Utils;
import com.runtime.listener.Assertion;

/**
 * 欢迎页面 ， 进入主页并完成初始引导
 * @author yan
 *
 */
@Listeners({com.runtime.listener.AssertionListener.class})
public class WelcomePageCase {
	
	private AndroidDriver<AndroidElement> driver;
	private static final Logger log = Logger.getLogger(WelcomePageCase.class);
	private String name = "WelcomePageCase";
	@BeforeClass
	public void setup() {
		log.info("-------------------------  欢迎页面  -------------------------");
		Utils.clearAppData();
		driver = AndroidServer.androidDriverRun();
		new gbTestPage(driver);

	}
	
	@AfterClass  
	public void tearDown() {
		driver.quit();
		Utils.sleep(2);
		Utils.setInputMethod();
		log.info("-------------------------  已完成: 欢迎页面  -------------------------\n\n\n" );
	} 
	
	/**
	 * 首次安装进入app体验一下
	 */
	@Test (priority = 1)
	public void welcomeExperienceIt() {
		gbTestPage.loginApp();
	}


	
}
