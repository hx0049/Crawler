package com.cetc28.controller;

import com.cetc28.processor.ArticleListProcessor;
import com.cetc28.processor.ArticleProcessor;
import com.cetc28.processor.AuthorProcessor;
import com.cetc28.repo.ArticleRepository;
import com.cetc28.repo.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CrawlerService {


    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private AuthorProcessor authorProcessor;
    @Autowired
    private ArticleListProcessor articleListProcessor;
    @Autowired
    private ArticleProcessor articleProcessor;


    public void crawleAllArticle(Set<String> allArticleUrl) {
        long startTime, endTime;
        System.out.println("【爬虫开始】爬文章内容...");
        startTime = System.currentTimeMillis();
        Spider spider = Spider.create(articleProcessor);
        for(String articleUrl:allArticleUrl){
            //if(null == articleRepository.findByUrl(articleUrl)) {
                spider.addUrl(articleUrl);
           // }
        }
        //开启10个线程
        spider.thread(10).run();
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】共耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库，请查收！");
    }

    public Set<String> crawleAllArticleUrl(Set<String> articleListUrls) {
        long startTime, endTime;
        System.out.println("【爬虫开始】爬文章地址...");
        startTime = System.currentTimeMillis();
        Spider spider = Spider.create(articleListProcessor);
        //list转数组
        String[] urls = new String[articleListUrls.size()];
        articleListUrls.toArray(urls);
        //添加需要抓取的url
        spider.addUrl(urls);
        //开启10个线程
        spider.thread(10).run();
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】共耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库，请查收！");
        return articleListProcessor.getArticleUrls();
    }

    public Set<String> crawleAuthorInfo(Set<String> authors) {
        long startTime, endTime;
        System.out.println("【爬虫开始】爬作者信息...");
        startTime = System.currentTimeMillis();
        Spider spider = Spider.create(authorProcessor);
        for(String authorName:authors){
           if(!hasCrawled(authorName)){
                spider.addUrl("https://blog.csdn.net/"+authorName);
            }
        }


        //开启10个线程
        spider.thread(10).run();
        endTime = System.currentTimeMillis();
        System.out.println("【爬虫结束】共耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库，请查收！");
        return authorProcessor.getArticleListUrl();
    }


    public boolean hasCrawled(String authorId){
        List<String> authorNames = Arrays.asList("mopdwell","qq_31941773","迷彩风情","贝壳里的沙",
                "一颗洛米","一念成佛_LHY","hxpjava1","贤哲007",
                "程序员海波","panhaigang123","Jadyli1","shi199434",
                "双斜杠少年","橘子味阳光","擅长删库跑路","马小琥");
        List<String> authorsId = Arrays.asList("mopdwell","qq_31941773","sxdtzhaoxinguo","lixiang987654321",
                "qq_30604989","qq_36807862","hxpjava1","qq_35489575",
                "u010753907","panhaigang123","u013005791","shi199434",
                "u012373815","code__code","qq27Ke","c77061900");
        if(authorsId.contains(authorId)){
            return true;
        }
        return false;

    }
}
