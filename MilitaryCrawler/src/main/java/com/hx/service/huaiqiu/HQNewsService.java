package com.hx.service.huaiqiu;

import com.hx.processor.AbstractPageProcessor;
import com.hx.processor.huaiqiu.HQNewsListProcessor;
import com.hx.processor.huaiqiu.HQNewsProcessor;
import com.hx.service.AbstractNewsService;
import us.codecraft.webmagic.Spider;

import java.util.List;

public class HQNewsService extends AbstractNewsService {

    @Override
    public AbstractPageProcessor getNewsListProcessor() {
        return new HQNewsListProcessor();
    }

    @Override
    public AbstractPageProcessor getNewsProcessor() {
        return new HQNewsProcessor();
    }
}
