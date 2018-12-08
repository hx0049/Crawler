package com.you.win;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.UnexpectedPage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.InputStream;

public class CrawFileUtil {
    public static final String BASE_PATH = "G:\\youwin\\";

    public static void savePage2html(HtmlPage page, int no){
        try {
            FileOutputStream fo = new FileOutputStream(BASE_PATH + no + ".html");
            IOUtils.write(page.asXml(), fo);
            fo.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void saveFile(Page page, String fileName) throws Exception {
        String file = BASE_PATH + fileName;
        InputStream is = page.getWebResponse().getContentAsStream();
        String fileNameHeader = page.getWebResponse().getResponseHeaderValue("Content-Disposition");
        System.out.println(fileNameHeader);
        FileOutputStream output = new FileOutputStream(file);
        //IOUtils.copy(is, output);
        byte[] buff = new byte[1024];
        int len;
        while((len = is.read(buff))!=-1){
            output.write(buff,0,len);
        }
        output.close();
       // Thread.sleep(30*1000);
    }
}
