package com.cetc28.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverUtil {

    public static WebDriver chromeDriver(){
        System.setProperty("webdriver.chrome.driver",
                "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        //去除chrome正受到自动测试软件的控制字样
        chromeOptions.addArguments("disable-infobars");
        // 设置为 headless 模式 （必须）
        // chromeOptions.addArguments("--headless");
        // 设置浏览器窗口打开大小  （非必须）
        //chromeOptions.addArguments("--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(chromeOptions);
        return driver;
    }
}
