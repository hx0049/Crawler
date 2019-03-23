package com.hx.processor;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPageProcessor implements PageProcessor {
    // 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    private static Site site = Site.me().setRetryTimes(0)
            .setSleepTime(1000).setTimeOut(5*1000);


    // 部分一：抓取网站的相关配置，包括编码、重试次数、抓取间隔、超时时间、请求消息头、UA信息等
//    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(6000)
//            .addHeader("Accept-Encoding", "/").setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.59 Safari/537.36");

    @Override
    public Site getSite() {
        return site;
    }


    public List<String> newsUrls = new ArrayList<>();

    public synchronized void add(String newsUrl) {
        newsUrls.add(newsUrl);
    }

    public synchronized void add(List<String> newsUrl) { newsUrls.addAll(newsUrl); }


    public List<String> getNewsUrls() {
        return newsUrls;
    }

}
