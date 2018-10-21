package com.cetc28.util;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KeyWordUtil {

    public static Set<String> getKeyWordFromAllFile(List<String> keywordFiles) {
        Set<String> keywords = new HashSet<String>();
        for(String fileName: keywordFiles){
            Set<String> kws = getKeyWordsFromSingleFile(fileName);
            keywords.addAll(kws);
        }
        return keywords;
    }

    private static Set<String> getKeyWordsFromSingleFile(String fileName){
        Set<String> result = new HashSet<String>();
        FileInputStream fis = null;
        BufferedReader br = null;
        try {
            String path = KeyWordUtil.class.getClassLoader().getResource(fileName).getPath();
            fis = new FileInputStream(path);
            br = new BufferedReader(new InputStreamReader(fis));
            String lineInfo;
            while((lineInfo = br.readLine()) != null){
                result.add(lineInfo);
            }
        }catch (IOException e){
            System.err.println("读取关键词出错:"+fileName+"  :  "+e.getMessage());
        }finally {
            try {
                fis.close();
            } catch (Exception e) {
            }
            try {
                br.close();
            } catch (Exception e) {
            }
        }
        return result;
    }
}
