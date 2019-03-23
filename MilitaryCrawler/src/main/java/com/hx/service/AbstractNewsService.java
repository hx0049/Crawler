package com.hx.service;

import com.hx.processor.AbstractPageProcessor;
import com.hx.processor.huaiqiu.HQNewsListProcessor;
import com.hx.processor.huaiqiu.HQNewsProcessor;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNewsService {

    public void startCrawler(List<String> newsListUrls){
       List<String> newsUrl = crawNewsUrl(newsListUrls);
       crawNews(newsUrl);
    }

    public List<String> crawNewsUrl(List<String> newsListUrls){
        long startTime, endTime;
        System.out.println("【爬虫开始】爬新闻元数据...");
        startTime = System.currentTimeMillis();
        AbstractPageProcessor newsListProcessor = getNewsListProcessor();
        Spider spider = Spider.create(newsListProcessor);
        //添加需要爬取的新闻元数据网址
        spider.addUrl(newsListUrls.toArray(new String[0]));
        //开启10个线程
        spider.thread(10).run();
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】共耗时约" + ((endTime - startTime) / 1000) + "秒，即将开始爬取新闻数据！");
        return newsListProcessor.getNewsUrls();
    }

    public void crawNews(List<String> newsUrls){
        System.out.println("【爬虫开始】爬News元数据...");
        long startTime = System.currentTimeMillis();
        AbstractPageProcessor newsProcessor = getNewsProcessor();
        Spider spider = Spider.create(newsProcessor);
        //添加需要爬取的新闻数据网址
        spider.addUrl(newsUrls.toArray(new String[0]));
        //开启10个线程
        spider.thread(10).run();
        long endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】共耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库，请查收！");
    }

    public abstract AbstractPageProcessor getNewsListProcessor();

    public abstract AbstractPageProcessor getNewsProcessor();
}
