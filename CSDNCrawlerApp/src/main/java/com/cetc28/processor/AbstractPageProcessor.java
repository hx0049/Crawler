package com.cetc28.processor;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public abstract class AbstractPageProcessor implements PageProcessor {
    // 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
    private static Site site = Site.me().setRetryTimes(2)
            .setSleepTime(1000).setTimeOut(5*1000);

    @Override
    public Site getSite() {
        return site;
    }
}
