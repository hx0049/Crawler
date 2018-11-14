package com.ebook.ebook.service;

import com.ebook.ebook.bean.MyHttpClientDownloader;
import com.ebook.ebook.processor.EBookContentProcessor;
import com.ebook.ebook.processor.EBookMetaDataProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

import java.util.Collection;
import java.util.List;

@Service
public class EBookService {

    @Autowired
    EBookMetaDataProcessor EBookMetaDataProcessor;

    @Autowired
    EBookContentProcessor EBookContentProcessor;

    public List<String> crawlerEbookInfo(Collection<String> urls){
        long startTime, endTime;
        System.out.println("【爬虫开始】爬电子书元数据...");
        startTime = System.currentTimeMillis();
        Spider spider = Spider.create(EBookMetaDataProcessor);
        spider.setDownloader(new MyHttpClientDownloader());
        for (String url : urls) {
            spider.addUrl(url);
        }
        //开启10个线程
        spider.thread(10).run();
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】共耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库，请查收！");
        return EBookMetaDataProcessor.getAllEbookContentUrls();
    }

    public void crawlerEbookContent(Collection<String> urls){
        long startTime, endTime;
        System.out.println("【爬虫开始】爬电子书内容...");
        startTime = System.currentTimeMillis();
        Spider spider = Spider.create(EBookContentProcessor);
        spider.setDownloader(new MyHttpClientDownloader());
        for (String url : urls) {
            spider.addUrl(url);
        }
        //开启10个线程
        spider.thread(10).run();
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】共耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库，请查收！");
    }
}
