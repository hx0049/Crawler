package com.cetc28.controller;

import com.cetc28.service.CSDNService;
import com.cetc28.util.KeyWordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/start/")
public class StartController {

    private static List<String> keywordFiles = Arrays.asList(
            "android.txt", "bigdata.txt","centos.txt","eclipse.txt",
            "idea.txt", "linux.txt","matlab.txt","maven.txt",
            "python.txt", "spring.txt");

    @Autowired
    private CSDNService csdnService;

    @Autowired
    private CrawlerService crawlerService;

    @RequestMapping("/csdn")
    public void startCrawlerAll(){
        //读取所有关键词
        Set<String> keywords = KeyWordUtil.getKeyWordFromAllFile(keywordFiles);
        //读取测试关键词(只有一个spring boot)
        keywords = getTestKeyword();
        //根据所有关键词查询csdn作者姓名
        Set<String> authors = csdnService.getCSDNWriterByKeyword(keywords);
        System.out.println("------------"+authors.size());

        //获取所有作者信息并保存 获取对应作者的所有的文章列表地址
        Set<String> articleListUrls = crawlerService.crawleAuthorInfo(authors);
        //爬取对应所有文章地址
        Set<String> articleUrls = crawlerService.crawleAllArticleUrl(articleListUrls);
        //爬取对应所有文章并保存
        crawlerService.crawleAllArticle(articleUrls);
    }

    private static Set<String> getTestKeyword() {
        Set<String> strings = new HashSet<String>();
        strings.add("服务发现框架");
        return strings;
    }
}
