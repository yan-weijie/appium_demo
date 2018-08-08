package com.allinmd.page;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.allinmd.file.Dom4jXml;
import com.allinmd.util.DeviceParseCopy;
import com.allinmd.util.RandomStr;
import com.allinmd.util.Utils;

import org.apache.log4j.Logger;


public class AndroidServer {

	public static AndroidDriver<AndroidElement> androiddriver;
	public static Logger log = Logger.getLogger(AndroidServer.class);
	
	public AndroidServer() {
		new DeviceParseCopy();
	}
	  
    public static void restartAppium() {
		//stopAppium();
		startAppium();
	}
    
    /**
     * 启动Appium Server
     */
    private static void startAppium() {
    	Properties props = System.getProperties(); 
    	String command = "";
    	System.out.print("os.name是:"+props.getProperty("os.name"));
    	switch(props.getProperty("os.name")) {
    	
    	case "Windows 10":
    		command = "cmd appium -g "+ props.getProperty("user.dir") + "/logs/AppiumLog/log" + RandomStr.random() + ".log --language cn";;
    		System.out.println("command是："+command);
    		break;
    	case "Mac OS X":
          command = Dom4jXml.getValue("macStartAppium") + " --session-override --log-level info --log-timestamp --log "
                  + props.getProperty("user.dir") + "/logs/AppiumLog/log" + RandomStr.random() + ".log --language cn";
////        String command = "/usr/local/bin/node /usr/local/bin/appium --log-level info --log-timestamp --log " 
////                + path + "/logs/AppiumLog/log" + RandomStr.random() + ".log --native-instruments-lib --session-override";
    		break;
		default:
			log.error("os.name = " + props.getProperty("os.name"));
			break;
    			
    	}
    		
//      System.out.println(command);    	
        log.info("启动Appium Server");
        Utils.command(command);
        Utils.sleep(5);

    }
    
    /**
     * kill node进程
     */
    @Test
    public static void stopAppium() {
    	Utils.killProcess(4723);
    }  
	
	/**
	 * 启动AndroidDriver
	 * @return
	 */
	public static AndroidDriver<AndroidElement> androidDriverRun() {
		try {
			//restartAppium();
			androiddriver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), setCapability());
			//androiddriver = new AndroidDriver<AndroidElement>(new URL("http://10.130.32.76:4723/wd/hub"), setCapability());
			androiddriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			if(androiddriver.getSessionId() != null) {
				log.info("SessionID: " + androiddriver.getSessionId() + "\n启动完成");
			} else {
				log.error("SessionID: " + androiddriver.getSessionId());
			}
			
			// 转型为WebDriver, 统一类型，方便调用异常截图方法
//			RuntimeServer runtime = new RuntimeServer();
//			runtime.runtimeDriver(androiddriver);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
		
		return androiddriver;
	}
	
	@Test
	public void f() {
		androidDriverRun();
	}
    
    public static DesiredCapabilities setCapability() {
    	DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability("automationName", "uiautomator2");  //自动化测试的引擎
        capabilities.setCapability("platformName", "Android");			//手机os
        capabilities.setCapability("platformVersion", "6.0");	//真机的Android版本
		//capabilities.setCapability("avd", "avds6.0");
        capabilities.setCapability("deviceName", "emulator-5554");
        //capabilities.setCapability("udid", "5LM0215C25003252");com.example.android.apis/.ApiDemos
        //capabilities.setCapability("appPackage", "com.xueqiu.android");
		capabilities.setCapability("appPackage", "com.example.android.apis");


//        capabilities.setCapability("appWaitActivity", ".modules.login.LoginAndRegisterActivity");
        //capabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
		capabilities.setCapability("appActivity", ".ApiDemos");
        capabilities.setCapability("unicodeKeyboard", true);									//使用unicodeKeyboard的编码方式来发送字符串
        capabilities.setCapability("resetKeyboard", true);										//隐藏键盘，和unicodeKeyboard结合可以输入中文，特殊字符，英文、数字的混合等。

        return capabilities;
    }
}