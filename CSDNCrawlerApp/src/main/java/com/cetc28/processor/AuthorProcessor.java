package com.cetc28.processor;

import com.cetc28.entity.Author;
import com.cetc28.repo.AuthorRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;

import us.codecraft.webmagic.selector.Html;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class AuthorProcessor extends AbstractPageProcessor {
    private Set<String> articleListUrl = new HashSet<>();

    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public void process(Page page) {
        Html pageHtml = page.getHtml();
        try {
            //页面地址
            String pageUrl = page.getUrl().get();
            //作者ID
            String authorId = pageUrl.substring(pageUrl.lastIndexOf("/") + 1);
            //头像图片地址
            String imgUrl = pageHtml.xpath("//img[@class='avatar_pic']/@src").get();
            byte[] photo = IOUtils.toByteArray(new URL(imgUrl));
            //昵称
            String nickName = pageHtml.xpath("//a[@id='uid']//text()").get();
            //粉丝数
            String fansNum = pageHtml.xpath("//span[@id='fan']//text()").get();
            //原创文章数
            String selfNum = pageHtml.xpath("//dl/dd/a/span//text()").get();
            //--------粉丝数 喜欢数 评论数-----
            List<String> fansAndlikeAndComment = pageHtml.xpath("//dl/dd/span//text()").all();
            //喜欢数
            String othersLikeNum = fansAndlikeAndComment.get(1);
            //评论数
            String commentNum = fansAndlikeAndComment.get(2);
            //文章总数
            int articleNum = 0;
            List<String> articleClassifyInfo = pageHtml.xpath("//div[@id='asideArchive']//span[@class='count float-right']//text()").all();
            for (String classInfo : articleClassifyInfo) {
                String numStr = classInfo.substring(0, classInfo.length() - 1);
                int number = Integer.parseInt(numStr);
                articleNum += number;
            }
            //System.out.println(authorId + "  文章数： " + articleNum);
            //----访问量 排名 积分
            List<String> otherInfo = pageHtml.xpath("//div[@class='grade-box clearfix']/dl/dd//text()").all();
            //访问量
            String visitNum = otherInfo.get(1);
            //排名
            String rankNum = otherInfo.get(3);
            //积分
            String jifenNum = otherInfo.get(2);
            //System.out.println(authorId + "  积分： " +jifenNum);

            int pageCount = getPageCountByNum(articleNum);
            generateArticleListUrls(authorId,pageCount);
            Author author = new Author();
            author.setId(authorId);
            author.setName(nickName);
            author.setCommentNum(commentNum);
            author.setFansNum(fansNum);
            author.setOthersLikeNum(othersLikeNum);
            author.setPhoto(photo);
            author.setRankNo(rankNum);
            author.setSelfArticleNum(selfNum);
            author.setVisitNo(visitNum);
            author.setJifen(jifenNum);
            authorRepository.save(author);
        } catch (Exception e) {
            System.err.println(page.getUrl() + "爬取作者信息出错");
            e.printStackTrace();
        }
    }

    private void generateArticleListUrls(String authorId, int pageCount) {
        for (int i = 1; i <= pageCount; i++) {
            String url = "https://blog.csdn.net/"+authorId+"/article/list/"+i;
            articleListUrl.add(url);
        }
    }

    public Set<String> getArticleListUrl() {
        return articleListUrl;
    }

    private int getPageCountByNum(int sum){
        int result = sum/20;
        if(sum - result*20 > 0 ){
            result++;
        }
        return result;
    }

    private String getGradeByJiFen(String jifen) {
        int jifenNum = Integer.parseInt(jifen.trim());
        if (jifenNum < 100) {
            return "V1";
        } else if (jifenNum < 500) {
            return "V2";
        } else if (jifenNum < 1000) {
            return "V3";
        } else if (jifenNum < 2000) {
            return "V4";
        } else if (jifenNum < 5000) {
            return "V5";
        } else if (jifenNum < 10000) {
            return "V6";
        } else if (jifenNum < 30000) {
            return "V7";
        } else if (jifenNum < 50000) {
            return "V8";
        } else if (jifenNum < 100000) {
            return "V9";
        } else if (jifenNum < 200000) {
            return "V10";
        } else if (jifenNum < 300000) {
            return "V11";
        } else if (jifenNum < 500000) {
            return "V12";
        } else if (jifenNum < 800000) {
            return "V13";
        } else if (jifenNum < 1000000) {
            return "V14";
        } else {
            return "V15";
        }
    }
}
