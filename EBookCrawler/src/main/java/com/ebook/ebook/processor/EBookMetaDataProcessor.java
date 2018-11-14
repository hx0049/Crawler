package com.ebook.ebook.processor;


import com.ebook.ebook.entity.EBookMetaInfo;
import com.ebook.ebook.repo.EBookMetaInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;


@Component
public class EBookMetaDataProcessor extends AbstractPageProcessor{

    private List<String> ebookContentUrls = new ArrayList<>();

    @Autowired
    private EBookMetaInfoRepository eBookMetaInfoRepository;

    @Override
    public void process(Page page) {
        String url = page.getUrl().get();
        String orderInfo = url.split("xiaoshuo/")[1];
        if(orderInfo.endsWith("/")){
            orderInfo = orderInfo.substring(0,orderInfo.length()-1);
        }
        String[] orders = orderInfo.split("/");
        Html pageHtml = page.getHtml();
        //电子书标题
        String eBookTitle = pageHtml.xpath("//h1//text()").get();
        eBookTitle = eBookTitle.substring(0,eBookTitle.length()-4);
        List<String> hrefs = pageHtml.xpath("//ul[@class='mulu_list']/li/a/@href").all();
        for(String href:hrefs){
            String chapterUrl = url+"/"+href;
            ebookContentUrls.add(chapterUrl);
        }

        String desc = pageHtml.xpath("//div[@class='mu_contain']/p//text()").get();

        EBookMetaInfo eBookMetaInfo = new EBookMetaInfo();
        eBookMetaInfo.setUrl(url);
        eBookMetaInfo.setFirstOrder(orders[0]);
        eBookMetaInfo.setSecondOrder(orders[1]);
        eBookMetaInfo.setName(eBookTitle);
        eBookMetaInfo.setChapterCount(hrefs.size());
        eBookMetaInfo.setDescribeInfo(desc);
        System.out.println(eBookMetaInfo);
        //System.out.println(page.getHtml().all());
        try {
            eBookMetaInfoRepository.save(eBookMetaInfo);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public List<String> getAllEbookContentUrls(){
        return ebookContentUrls;
    }
}
