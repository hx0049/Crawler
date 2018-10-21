package com.cetc28.processor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ArticleListProcessor extends AbstractPageProcessor {

    private Set<String> articleUrls = new HashSet<String>();

    @Override
    public void process(Page page) {
        List<String> urls = page.getHtml()
                .xpath("//div[@class='article-item-box csdn-tracking-statistics']/h4/a/@href").all();
       /* for(String  s:urls){
            String regex = Pattern.quote("<a href=\"") +"(.*?)"
                    + Pattern.quote("\"");
            Pattern pat = Pattern.compile(regex,Pattern.DOTALL);
            Matcher mat = pat.matcher(s);
            if(mat.find()){
                articleUrls.add(mat.group(1));
            }
        }*/
        articleUrls.addAll(urls);
    }

    public Set<String> getArticleUrls() {
        return articleUrls;
    }
}
