package com.ebook.ebook.controller;

import com.ebook.ebook.service.EBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class EBookController {

    public static final String PREFIX = "https://www.duquanben.com/xiaoshuo/";
    public static int FIRST_INDEX = 9;
    public static final int SECOND_INDEX = 0;


    @Autowired
    EBookService EBookService;

    @RequestMapping("ebook")
    public String start(){
        Collection<String> chapterUrls = EBookService.crawlerEbookInfo(getAllBookUrl());
        EBookService.crawlerEbookContent(chapterUrls);
        return "success";
    }

    public List<String> getAllBookUrl(){
        List<String> urls = new ArrayList<>();
        for(int i = 0;i<1;i++){
            String index = i>10?String.valueOf(i):("0"+i);
            String url = PREFIX+FIRST_INDEX+"/"+FIRST_INDEX+ SECOND_INDEX +index;
            urls.add(url);
        }
        return urls;
    }
}
