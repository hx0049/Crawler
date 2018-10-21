package com.cetc28.processor;

import com.cetc28.entity.Article;
import com.cetc28.entity.Author;
import com.cetc28.repo.ArticleRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ArticleProcessor extends AbstractPageProcessor {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void process(Page page) {
        //文章地址
        String url = page.getUrl().get();
        System.out.println(url);
        Html pageHtml = page.getHtml();
        //文章标题
        String title = pageHtml.xpath("//h1[@class='title-article']/text()").get();
        //文章内容
        String articleContent = pageHtml.xpath("//div[@id='article_content']/html()").get();
        //写作时间
        String timeStr = pageHtml.xpath("//span[@class='time']//text()").get();
        Date createTime = new Date();
        try {
            createTime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").parse(timeStr);
        } catch (ParseException e) {
            System.err.println("解析时间出错：" + e.getMessage());
        }
        //作者昵称
        String authorId = pageHtml.xpath("//a[@class='follow-nickName']/@href").get();
        authorId = authorId.substring(authorId.lastIndexOf("/") + 1);
        //阅读量
        String readCount = pageHtml.xpath("//span[@class='read-count']//text()").get();
        readCount = readCount.substring(4);
        //个人分类
        List<String> personTag = pageHtml.xpath("//div[@class='tags-box space']/a//text()").all();
        //标签
        List<String> labelTag = pageHtml.xpath("//span[@class='tags-box artic-tag-box']/a//text()").all();
        //css引用文件
        List<String> cssElements = page.getHtml().xpath("//div[@id='article_content']//link").all();
        List<String> cssFileLink = page.getHtml().xpath("//div[@id='article_content']//link/@href").all();
        for (int i = 0; i < cssFileLink.size(); i++) {
            String cssLink = cssFileLink.get(i);
            String cssFileStr = getCssFileStrByLink(cssLink);
            articleContent = articleContent.replace(cssElements.get(i), cssFileStr);
        }
        //img引用文件
        List<String> imgFileLink = page.getHtml().xpath("//div[@id='article_content']//img/@src").all();
        for (String imgLink : imgFileLink) {
            String imgLinkD = imgLink.replace("https", "http").replace("http", "https");
            String imgBase64Str = getBase64StrByImgLink(imgLinkD);
            articleContent = articleContent.replace(imgLink, imgBase64Str);
        }

        Article article = new Article();
        Author author = new Author();
        author.setId(authorId);
        article.setAuthor(author);
        article.setUrl(url);
        article.setContent(articleContent);
        article.setCreateTime(createTime);
        article.setLabelTags(labelTag);
        article.setPersonTags(personTag);
        article.setReadCount(readCount);
        article.setTitle(title);
        articleRepository.save(article);
    }

    private String getCssFileStrByLink(String cssLink) {
        try {
            String cssFileContent = IOUtils.toString(new URL(cssLink), "gbk");
            return "<style tyle=\"text/css\">" + cssFileContent + "</style>";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getBase64StrByImgLink(String imgLink) {
        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(imgLink).openConnection();
            if (!imgLink.endsWith(".jpg") && !imgLink.endsWith(".png") && !imgLink.endsWith(".gif")) {
                connection.setRequestProperty("accept-language", "zh-CN,zh;q=0.9,ja;q=0.8");
                connection.setRequestProperty("referer", "https://blog.csdn.net/C20180630/article/details/70596588");
                connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
                connection.connect();
            }
            InputStream is = connection.getInputStream();
            byte[] imgData = IOUtils.toByteArray(is);
            connection.disconnect();
            String base64Str = new BASE64Encoder().encode(imgData);
            return "data:image/jpeg;base64," + base64Str;
        } catch (Exception e) {
            try {
                HttpURLConnection connection =
                        (HttpURLConnection) new URL(imgLink.replace("https", "http")).openConnection();
                InputStream is = connection.getInputStream();
                byte[] imgData = IOUtils.toByteArray(is);
                connection.disconnect();
                String base64Str = new BASE64Encoder().encode(imgData);
                return "data:image/jpeg;base64," + base64Str;
            } catch (Exception e2) {
                System.err.println("第二次请求(HTTP)失败："+e2.getMessage());
            }
        }
        return "";
    }


}
