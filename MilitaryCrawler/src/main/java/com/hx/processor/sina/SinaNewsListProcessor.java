package com.hx.processor.sina;

import com.hx.processor.AbstractPageProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

public class SinaNewsListProcessor extends AbstractPageProcessor {
    @Override
    public void process(Page page) {
        String url = page.getUrl().get();
        Html pageHtml = page.getHtml();
        //新闻url
        List<String> newsTypes = pageHtml.xpath("//div[@class='fixList']/ul[@class='linkNews']/li/a/@href").all();
        add(newsTypes);
    }
}
