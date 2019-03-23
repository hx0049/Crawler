package com.hx.processor.sina;

import com.hx.processor.AbstractPageProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SinaNewsProcessor  extends AbstractPageProcessor {
    @Override
    public void process(Page page) {

        Html pageHtml = page.getHtml();
        //标题
        String title = pageHtml.xpath("//h1[@class=' main-title']//text()").get();
        System.out.println(title);
        //新闻时间
        String date = pageHtml.xpath("//div[@class='date-source']/span[@class='date']//text()").get();
        try {
            System.out.println(new SimpleDateFormat("yyyy年MM月dd日 hh:mm").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //新闻内容
        String content = pageHtml.xpath("//div[@id='article']//html()").get();
        System.out.println(content);

    }
}
