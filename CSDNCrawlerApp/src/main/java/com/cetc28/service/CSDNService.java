package com.cetc28.service;

import com.cetc28.util.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CSDNService {

    public Set<String> getCSDNWriterByKeyword(Set<String> keywords) {
        WebDriver webDriver = WebDriverUtil.chromeDriver();
        Set<String> writers = new HashSet<String>();
        webDriver.manage().window().maximize(); //将浏览器最大化
        for(String keyword:keywords){
            String url = "https://so.csdn.net/so/search/s.do?q="+keyword+"&t=blog&u=";
            webDriver.get(url);
            WebDriverWait webDriverWait = new WebDriverWait(webDriver,10);
            List<WebElement> webElements = webDriver.findElements(By.xpath("//dd[@class='search-link']/a"));
            int i=0;
            for (WebElement webElement : webElements) {
                System.out.println(++i);
                String urlText = webElement.getText();
                System.out.println(urlText);
                String regex = Pattern.quote("https://blog.csdn.net/") + "(.*?)" + Pattern.quote("/article/details");
                Pattern pat = Pattern.compile(regex,Pattern.DOTALL);
                Matcher mat = pat.matcher(urlText);
                if (mat.find()) {
                    writers.add(mat.group(1));
                }
                //System.out.println(urlText);
            }
           // System.out.println("size:"+writers.size());
        }
        webDriver.close();
        return writers;
    }



}
