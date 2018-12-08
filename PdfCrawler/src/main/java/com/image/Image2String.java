package com.image;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class Image2String {

    public static void main(String[] args){
        String s =System.getenv("TESSDATA_PREFIX");
        System.out.println(s);
        System.out.println(new File(System.getenv("TESSDATA_PREFIX"),"./tessdata/eng.traineddata").exists());
        File imageFile = new File("C:\\Users\\hx\\Desktop\\0.jpg");
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        instance.setDatapath("E:\\java\\Tesseract-OCR\\");
        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result==null?"null":result);
            System.out.println(result.equals("")?"null":result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
