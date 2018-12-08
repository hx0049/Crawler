import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.gargoylesoftware.htmlunit.util.WebConnectionWrapper;
import com.you.win.FirstPage;
import com.you.win.LoginPage;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.html.HTMLInputElement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) throws Exception {
        new FirstPage(LoginPage.login());

    }


//    String hrefValue = "$('#input')";
//    ScriptResult s = page.executeJavaScript(hrefValue);//执行js方法
//    result = ((HtmlPage)s.getNewPage()).asXml();


}
