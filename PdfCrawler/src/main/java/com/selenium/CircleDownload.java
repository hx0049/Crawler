package com.selenium;

import com.util.FilePathUtil;
import com.util.FileUtils;
import com.util.HighMathUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class CircleDownload {

    public static void start(WebDriver webDriver, WebDriverWait webDriverWait) throws Exception{
        int bookVersionCount = HighMathUtil.getBookVersionCount();
        for(int i=7;i<bookVersionCount;i++){
            //选择教材版本
            SeleniumStart.waitForLoading(webDriverWait);
            new Select(webDriver.findElements(By.tagName("select")).get(2)).selectByIndex(i);
            int bookPartCount = HighMathUtil.getBookPartCount(i);
            if(bookPartCount == 1){
                startDownLoad(webDriver,webDriverWait);
                writeFile2RealPath(i,0);
            }else{
                for(int j=6;j<bookPartCount;j++){
                    SeleniumStart.waitForLoading(webDriverWait);
                    new Select(webDriver.findElements(By.tagName("select")).get(3)).selectByIndex(j);
                    startDownLoad(webDriver,webDriverWait);
                    writeFile2RealPath(i,j);
                }
            }
        }
    }


    public static void startDownLoad(WebDriver webDriver,WebDriverWait webDriverWait) throws Exception{
        //waitForLoading(webDriverWait);
        List<WebElement> webElementList = webDriver.findElements(By.linkText("下载"));
        int count = webElementList.size();
        SeleniumStart.waitForLoading(webDriverWait);
        System.out.println("COUNT:"+ count);
        for(int i=0;i<count;i++){
            retryClickUtilSuccess(webDriver,i);
            SeleniumStart.waitForLoading(webDriverWait);
            String title = webDriver.getTitle();
            Thread.sleep(40*100);
        }
        Thread.sleep(4000);
       // if(true)return;
        try {
            WebElement pageInfo = webDriver.findElement(By.className("page-info"));
            String[] info = pageInfo.getText().replace(" ", "#").split("#");
            /*for(String s:info){
                System.out.println("num:"+s);
            }*/
            int fileCount = Integer.parseInt(info[info.length - 2]);
            int pgCount = fileCount/10+(fileCount%10==0?0:1);
            WebElement pgNumInfo = webDriver.findElement(By.id("go_pg_num"));
            int currentPg = Integer.parseInt(pgNumInfo.getAttribute("value"));
            if(pgCount == currentPg){
                System.out.println("已经到达当前最后一页");
                System.out.println("NUmber:"+fileCount+"  pCount:"+pgCount+" currPg:"+currentPg);
                return;
            }
        }catch (Exception e){
            System.out.println("此页可能无数据！"+e.getMessage()+":"+e.getClass().getName());
            return;
        }
        WebElement webElement = webDriver.findElement(By.linkText(">"));
        if(webElement.isEnabled()){
            webElement.sendKeys(Keys.ENTER);
            SeleniumStart.waitForLoading(webDriverWait);
            String title = webDriver.getTitle();
            startDownLoad(webDriver,webDriverWait);
        }
    }

    private static void retryClickUtilSuccess(WebDriver webDriver, int i) {
        try{
            WebElement webElement = webDriver.findElements(By.linkText("下载")).get(i);
            webElement.sendKeys(Keys.ENTER);
        }catch (StaleElementReferenceException e){
            retryClickUtilSuccess(webDriver,i);
        }catch (IndexOutOfBoundsException e){
            return;
        }
    }

    /*public static void main(String[] args){
        writeFile2RealPath(1,1);
    }*/

    public static void writeFile2RealPath(final int bookVersionIndex,final int bookPartIndex){
        String newPath = FilePathUtil.getFilePath(2,2,bookVersionIndex,bookPartIndex);
        File[] fileList = new File("G:/youwin/").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        List<String> fileNameList = new ArrayList<>();
        for(File f:fileList){
            String fileName = f.getName();
            if(fileName.endsWith(".crdownload")) {
                try {
                    fileName = fileName.substring(0, fileName.indexOf(".crdownload"));
                    fileNameList.add(fileName);
                }catch (Exception e){
                    fileNameList.add(fileName);
                }
            }else{
                fileNameList.add(fileName);
            }
        }
        while(true){
            boolean isAllFileComplete = true;
            for(String fileName:fileNameList){
                if(!new File("G:/youwin/"+fileName).exists()){
                    isAllFileComplete = false;
                    break;
                }
            }
            if(!isAllFileComplete) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                break;
            }
        }
        String srcPath = "G:/youwin/";
        FileUtils.moveFile2Path(srcPath,fileNameList,newPath);
    }
}
