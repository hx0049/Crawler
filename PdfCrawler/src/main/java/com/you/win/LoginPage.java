package com.you.win;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageReader;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPage {
    public static final String LOGIN_PAGE = "http://yes.youwinedu.com/";
    public static final String USERNAME = "cuiqp";
    public static final String PASSWORD = "password";
    public static Scanner scanner = new Scanner(System.in);

    public static HtmlPage login() throws Exception{
        WebClient webClient = getWebClient();
        //打开登录页面
        HtmlPage page = webClient.getPage(LOGIN_PAGE);
        //填写用户名和密码
        HtmlTextInput loginElement = (HtmlTextInput) page.getElementById("login-name");
        HtmlPasswordInput passwordElement = (HtmlPasswordInput) page.getElementById("login-pass");
        loginElement.setText(USERNAME);
        passwordElement.setText(PASSWORD);
        //将验证码保存至gif文件并在控制台显示
        HtmlImage imageElement = (HtmlImage)page.getElementsByTagName("img").get(0);
        String imageStr = imageElement.getAttribute("src");
        AsciiPic.createAsciiPic(imageStr);
        //手动写入验证码
        String vCodeString = scanner.next();
        HtmlTextInput vCodeInputElement=(HtmlTextInput)page.getElementsByTagName("input").get(2);
        vCodeInputElement.setText(vCodeString);
        //点击登录按钮
        DomElement loginButton=page.getElementsByTagName("button").get(0);
        HtmlPage firstPage = loginButton.click();
        return firstPage;
    }

    public static WebClient getWebClient(){
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        return webClient;
    }

    public static void getImage(String page) throws Exception{
        String regex =Pattern.quote("<a ng-click=\"getVerifyImg() \">")+
                "(.*?)"+
                Pattern.quote("</a>");
        Pattern pat = Pattern.compile(regex,Pattern.DOTALL);
        Matcher mat = pat.matcher(page);
        while (mat.find()) {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\hx\\Desktop\\html.html");
            IOUtils.write(mat.group(1),fos);
            fos.close();
            System.out.println("图片写入完毕！");
        }
    }
}
