package com.util;

import java.io.File;

public class FilePathUtil {

    public static final String BasePath = "G:/youwinData/";

    public static String getFilePath(int stageIndex,int subjectIndex,int bookVersionIndex,
                                     int bookPartIndex){
        //高中-数学
        if(stageIndex != 2 || subjectIndex != 2){
            throw new RuntimeException("这不是我想要的");
        }
        String filePath = BasePath+HighMathUtil.getRelativePath(bookVersionIndex,bookPartIndex);
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }
        return filePath;
    }



}
