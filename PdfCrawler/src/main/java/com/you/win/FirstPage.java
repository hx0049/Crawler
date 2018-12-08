package com.you.win;

import com.gargoylesoftware.htmlunit.InteractivePage;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.UnexpectedPage;
import com.gargoylesoftware.htmlunit.html.*;
import org.w3c.dom.html.HTMLSelectElement;

import java.util.List;


public class FirstPage {
    //主界面
    private HtmlPage htmlPage = null;
    //学段
    private HtmlSelect gradeSelect;
    //学科
    HtmlSelect subjectSelect;

    public FirstPage(HtmlPage page) throws Exception{
        htmlPage = page.getWebClient().getPage("http://yes.youwinedu.com/#/tars/pre/notes");
        page.getWebClient().waitForBackgroundJavaScript(30000);

        click(page);

    }

    public void select(HtmlPage page) throws Exception{
        gradeSelect = (HtmlSelect)page.getElementsByTagName("select").get(0);
        subjectSelect = (HtmlSelect)page.getElementsByTagName("select").get(1);
        //小学-0 初中-1 高中-2 通用-3
        //小学：请选择、语文、数学、英语、少儿英语、科学、奥数
        //初中：请选择、语文、数学、英语、物理、化学、生物、历史、地理、政治、科学
        //高中：请选择、语文、数学、英语、物理、化学、生物、历史、地理、政治、科学
        //通用：请选择、数学、英语、语文、物理、化学、通用
        gradeSelect.setSelectedIndex(2);
        subjectSelect.setSelectedIndex(2);
        //初始化选择框
        HtmlSelect bookVersionSelect = (HtmlSelect) page.getElementsByTagName("select").get(2);
        HtmlSelect bookNoSelect = (HtmlSelect)page.getElementsByTagName("select").get(3);
        //高中数学-教材版本
        //教材版本
        //人教A版：分册，必修一--必修五，选修1-1,1-2,2-1,2-2,2-3,3-1,3-3,3-4,4-1,4-2,4-4,4-5,4-6,4-7,4-9
        //人教B版：分册，必修一--必修五，选修1-1,1-2,2-1,2-2,2-3,4-5，高一上，高一下，高二上，高二下
        //北师大版：分册，必修一--必修五，选修1-1,1-2,2-1,2-2,2-3,3-1,3-3,3-4,4-1,4-2,4-4,4-5,4-6,4-7
        //沪教版：分册，高一上册，高一下册，高二上册，高二下册，高三上册，高三下册
        //苏教版：分册，必修一--必修五，选修1-1,1-2,2-1,2-2,2-3,3-3,4-1,4-2,4-4,4-5
        //精品专题：分册
        //个性化课程（理数）：分册，必修一--必修五，选修2-1,2-2,2-3,4
        //个性化课程（文数）：分册，必修一--必修五，选修1-1,1-2
        //高考专题：分册，全国版，北京版，参考版
        //通用版本：分册，研究院，师资培训
        bookVersionSelect.setSelectedIndex(5);
        bookNoSelect.setSelectedIndex(1);
        System.out.println(htmlPage.asXml());
        crawFile(bookVersionSelect.getSelectedOptions().get(0).getText(),
                bookNoSelect.getSelectedOptions().get(0).getText(),null);
    }
    public void click(HtmlPage page) throws Exception{
        gradeSelect = (HtmlSelect)page.getElementsByTagName("select").get(0);
        HtmlPage temp = gradeSelect.getOption(2).click();
        CrawFileUtil.savePage2html(temp,1);
        subjectSelect = (HtmlSelect)temp.getElementsByTagName("select").get(1);
        temp = subjectSelect.getOption(2).click();
        CrawFileUtil.savePage2html(temp,2);
        HtmlSelect bookVersionSelect = (HtmlSelect) temp.getElementsByTagName("select").get(2);
        temp = bookVersionSelect.getOption(5).click();
        CrawFileUtil.savePage2html(temp,3);
        HtmlSelect bookNoSelect = (HtmlSelect)temp.getElementsByTagName("select").get(3);
        HtmlPage temp2 = bookNoSelect.getOption(1).click();
        CrawFileUtil.savePage2html(temp,4);
        CrawFileUtil.savePage2html(temp2,5);
        CrawFileUtil.savePage2html(htmlPage,6);
        temp2.getWebClient().waitForBackgroundJavaScript(40*1000);
        CrawFileUtil.savePage2html(temp,7);
        CrawFileUtil.savePage2html(temp2,8);
        CrawFileUtil.savePage2html(htmlPage,9);
        crawFile(bookVersionSelect.getSelectedOptions().get(0).getText(),
                bookNoSelect.getSelectedOptions().get(0).getText(),temp);
    }


    public void crawFile(String bookVersion,String bookNo,HtmlPage temp) throws Exception{
        System.out.println(bookVersion);
        System.out.println(bookNo);
        HtmlPage page = temp==null?htmlPage:temp;
        List<HtmlAnchor> as = page.getAnchors();
        for(int i=as.size()-1;i>=0;i--){
            HtmlAnchor domElement = as.get(i);
            if(domElement.getTextContent().equals("下载")){
                //System.out.println(domElement.getAttribute("ng-click"));
                String fileName = domElement.getParentNode().getChildNodes().get(0).getTextContent();
                Page filePage = domElement.click();
                //htmlPage.getWebClient().waitForBackgroundJavaScript(30*1000);
                CrawFileUtil.saveFile(filePage,fileName);
                //CrawFileUtil.savePage2html(htmlPage,10);

            }
        }
    }
}
