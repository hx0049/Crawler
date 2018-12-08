package com.selenium;

import com.gargoylesoftware.htmlunit.WebWindow;
import com.you.win.AsciiPic;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SeleniumStart {
    public static final String LOGIN_PAGE = "http://yes.youwinedu.com/";
    public static final String USERNAME = "cuiqp";
    public static final String PASSWORD = "password";
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception{
        WebDriver webDriver = getChromeDriver();
        webDriver.manage().window().maximize();       //将浏览器最大化
        webDriver.get(LOGIN_PAGE);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver,10);
        //输入用户名
        webDriverWait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("login-name"));
            }
        }).sendKeys(USERNAME);
        //输入密码
        webDriver.findElement(By.id("login-pass")).sendKeys(PASSWORD);
        //获取图片
        String imgStr = webDriver.findElement(By.tagName("img")).getAttribute("src");
       // AsciiPic.createAsciiPic(imgStr);
        //手动写入验证码
        String vCodeString = scanner.next();
        webDriver.findElements(By.tagName("input")).get(2).sendKeys(vCodeString);
        //点击登录
        webDriver.findElement(By.tagName("button")).click();
        //点击教研系统
        webDriverWait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.linkText("教研系统"));
            }
        }).sendKeys(Keys.ENTER);
        //点击备课笔记库
        webDriverWait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.linkText("备课笔记库"));
            }
        }).sendKeys(Keys.ENTER);
        //选择高中
        waitForLoading(webDriverWait);
        new Select(webDriver.findElements(By.tagName("select")).get(0)).selectByIndex(2);
        //选择数学
        waitForLoading(webDriverWait);
        new Select(webDriver.findElements(By.tagName("select")).get(1)).selectByIndex(2);

        CircleDownload.start(webDriver,webDriverWait);
    }



    public static void waitForLoading(WebDriverWait webDriverWait){
        webDriverWait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.className("ng-hide"));
            }
        });
    }

    public static WebDriver getChromeDriver(){
        String downloadFilepath = "G:\\youwin";
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
        options.setExperimentalOption("prefs",chromePrefs);
        options.addArguments("--test-type");
        options.addArguments("disable-infobars");//去除chrome正受到自动测试软件的控制
        System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver(options);
        return webDriver;
    }

    public static WebDriver getFireFoxDriver() {
        System.setProperty("webdriver.gecko.driver","C:\\Program Files\\Mozilla Firefox\\geckodriver.exe");
        WebDriver webDriver = new FirefoxDriver();
        return webDriver;
    }
}
