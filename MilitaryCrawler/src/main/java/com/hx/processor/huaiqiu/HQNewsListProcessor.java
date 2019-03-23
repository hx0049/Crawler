package com.hx.processor.huaiqiu;

import com.hx.processor.AbstractPageProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

public class HQNewsListProcessor extends AbstractPageProcessor {

    @Override
    public void process(Page page) {
        String url = page.getUrl().get();

        Html pageHtml = page.getHtml();
        //新闻类型
        List<String> newsTypes = pageHtml.xpath("//ul[@class='spread']/li/dl/dd/h3/strong/a//text()").all();
        System.out.println(newsTypes);
        List<String> urls = pageHtml.xpath("//ul[@class='spread']/li/dl/dd/span//text()").all();
        System.out.println(urls);

        for (int i = 0; i < newsTypes.size(); i++) {
            if (newsTypes.get(i).equals("军事") || newsTypes.get(i).equals("国际新闻")) {
                add(urls.get(i).split("\\s+")[0]);
            }
        }
    }


}
