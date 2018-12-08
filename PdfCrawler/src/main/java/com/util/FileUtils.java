package com.util;

import java.io.File;
import java.util.List;

public class FileUtils {

    public static void moveFile2Path(String srcPath,List<String> fileNameList, String newPath){
        for(String fileName:fileNameList){
            moveToOtherFolders(srcPath,fileName,newPath);
        }
    }
    private static void moveToOtherFolders(String pathName,String fileName,String ansPath){
        String startPath = pathName  + fileName;
        String endPath = ansPath  + fileName;
        try {
            File startFile = new File(startPath);
            File tmpFile = new File(endPath);
            if (startFile.renameTo(new File(endPath))) {
                System.out.println("File is moved successful!");
            } else {
                System.out.println("File is failed to move!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
