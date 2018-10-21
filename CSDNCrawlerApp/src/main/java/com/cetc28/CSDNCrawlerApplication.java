package com.cetc28;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.net.ssl.SSLSession;

@SpringBootApplication
public class CSDNCrawlerApplication {
    public static void main(String[] args) {

        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
            public boolean verify(String hostname,
                                  SSLSession sslsession) {
                return true;
            }
        });
        SpringApplication.run(CSDNCrawlerApplication.class, args);

    }
}
