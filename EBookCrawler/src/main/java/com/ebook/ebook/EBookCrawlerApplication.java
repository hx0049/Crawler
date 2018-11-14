package com.ebook.ebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class EBookCrawlerApplication {

    public static void main(String[] args) {
        String jdkPath = System.getenv("JAVA_HOME");
        if(!new File(jdkPath).exists()){
            String[] argus = new String[]{"www.duquanben.com"};
            try {
                CertInstaller.main(argus);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SpringApplication.run(EBookCrawlerApplication.class, args);
    }
}
