package com.cetc28.bean;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class ChromeRunner implements CommandLineRunner {
    private String url = "http://localhost:8080/start/csdn";

    private static final String CHROME_PATH = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";

    @Override
    public void run(String... args) throws Exception {
        try {
            if(isChromeInProcess()) {
                return;
            }
            Process process = Runtime.getRuntime().exec(
                    CHROME_PATH + " " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isChromeInProcess() {
        try {
            InputStream inputStream = Runtime.getRuntime().exec("tasklist").getInputStream();
            String outputStr = IOUtils.toString(inputStream, "UTF-8");
            return outputStr.contains("chrome.exe");
        } catch (IOException e) {
            return false;
        }

    }
}
