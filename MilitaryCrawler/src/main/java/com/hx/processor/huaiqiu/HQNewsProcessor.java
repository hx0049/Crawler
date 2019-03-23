package com.hx.processor.huaiqiu;

import com.hx.processor.AbstractPageProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class HQNewsProcessor extends AbstractPageProcessor {
    @Override
    public void process(Page page) {
        Html pageHtml = page.getHtml();
        //标题
        String title = pageHtml.xpath("//div[@class='l_a']/h1[@class='tle']//text()").get();
        if(title == null){
            title = pageHtml.xpath("//div[@class='conText']/h1//text()").get();
        }
        if(title == null) {
            //图集
            title = pageHtml.xpath("//div[@class='focus_box']/h1[@class='hd']/strong//text()").get();
            System.err.println("图集:" + title);
            return;
        }
        System.out.println(title);

        //新闻时间
        String date = pageHtml.xpath("//div[@class='la_tool']/span[@class='la_t_a']//text()").get();
        try {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String content = pageHtml.xpath("//div[@class='la_con']//html()").get();
        if(content == null){
            content = pageHtml.xpath("//div[@id='text']//html()").get();
        }
        System.out.println(content);
    }
}
