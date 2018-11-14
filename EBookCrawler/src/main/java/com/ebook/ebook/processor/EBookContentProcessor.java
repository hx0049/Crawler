package com.ebook.ebook.processor;

import com.ebook.ebook.entity.EBookChapter;
import com.ebook.ebook.repo.EBookChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

@Component
public class EBookContentProcessor extends AbstractPageProcessor {
    @Autowired
    EBookChapterRepository eBookChapterRepository;

    @Override
    public void process(Page page) {
        String url = page.getUrl().get();
        String orderStr = url.substring(0,url.length()-5);
        orderStr = orderStr.substring(orderStr.lastIndexOf("/")+1);
        int order = Integer.parseInt(orderStr);
        Html pageHtml = page.getHtml();

        String eBookTitle = pageHtml.xpath("//div[@class='weizhi']/a//text()").all().get(1);
        eBookTitle = eBookTitle.split("TXT")[0];
        String chapterName = pageHtml.xpath("//h1//text()").get();
        String chapterContent = pageHtml.xpath("//div[@id='htmlContent']//text()").get();

        System.out.println(eBookTitle);
        System.out.println(chapterName);
        //System.out.println(chapterContent);

        EBookChapter eBookChapter = new EBookChapter();
        eBookChapter.setUrl(url);
        eBookChapter.setOrderNo(order);
        eBookChapter.setEbookTitle(eBookTitle);
        eBookChapter.setChapterName(chapterName);
        eBookChapter.setChapterContent(chapterContent);
        eBookChapterRepository.save(eBookChapter);
    }
}
