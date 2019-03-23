package com.hx.service.sina;

import com.hx.processor.AbstractPageProcessor;
import com.hx.processor.sina.SinaNewsListProcessor;
import com.hx.processor.sina.SinaNewsProcessor;
import com.hx.service.AbstractNewsService;

import java.util.List;

public class SinaNewsService extends AbstractNewsService {

    @Override
    public AbstractPageProcessor getNewsListProcessor() {
        return new SinaNewsListProcessor();
    }

    @Override
    public AbstractPageProcessor getNewsProcessor() {
        return new SinaNewsProcessor();
    }
}
