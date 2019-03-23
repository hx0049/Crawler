package com.hx;


import com.hx.processor.huaiqiu.HQNewsListProcessor;
import com.hx.processor.huaiqiu.HQNewsProcessor;
import com.hx.service.AbstractNewsService;
import com.hx.service.huaiqiu.HQNewsService;
import com.hx.service.sina.SinaNewsService;
import us.codecraft.webmagic.Spider;

import java.net.URLEncoder;
import java.util.*;

public class CrawlerStarter {

    public static void main(String[] args) throws Exception {
       /* String keyWord = "民兵3";
        String url = "http://s.huanqiu.com/s?q="+URLEncoder.encode(keyWord,"utf-8");
        System.out.println(IOUtils.toString(new URL(url),"utf-8"));*/

        /*String[] keyWords = {"民兵3", "弹道弹道"};
        List<String> urls = new ArrayList<>();
        for (String keyWord : keyWords) {
            for (int i = 0; i < 10; i++) {
                urls.add("http://s.huanqiu.com/s?q=" +
                        URLEncoder.encode(keyWord, "utf-8")+ "&p="+i);
            }
        }

        AbstractNewsService newsService = new HQNewsService();
        newsService.startCrawler(urls);*/

        Map<String,String> keyWordMap = new HashMap<>();
        keyWordMap.put("中国军情","57918");
        keyWordMap.put("国际军情","57919");
        keyWordMap.put("东海局势","234399");
        keyWordMap.put("南海局势","234400");
        Collection<String> keyCidSet = keyWordMap.values();
        String sinaUrlPrefix="http://mil.news.sina.com.cn/roll/index.d.html?cid=";
        List<String> urls = new ArrayList<>();
        for (String keyCid : keyCidSet) {
            for (int i = 0; i < 20; i++) {
                urls.add(sinaUrlPrefix + keyCid +"?page="+i);
            }
        }
        AbstractNewsService newsService = new SinaNewsService();
        newsService.startCrawler(urls);
    }

}
