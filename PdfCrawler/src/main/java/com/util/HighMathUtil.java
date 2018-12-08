package com.util;

public class HighMathUtil {
    static String[] bookVersions = {"教材版本","人教A版","人教B版","北师大版",
            "沪教版","苏教版","精品专题","个性化课程(理数)","个性化课程(文数)",
            "高考专题","通用版本"};
    static String[][] allInfo = {
            {"分册"},
            {"分册","必修一","必修二","必修三","必修四","必修五","选修1-1",
                    "选修1-2","选修2-1","选修2-2","选修2-3","选修3-1",
                    "选修3-3","选修3-4","选修4-1","选修4-2","选修4-4",
                    "选修4-5","选修4-6","选修4-7","选修4-9"},
            {"分册","必修一","必修二","必修三","必修四","必修五","选修1-1",
                    "选修1-2","选修2-1","选修2-2","选修2-3","选修4-5",
                    "高一上","高一下","高二上","高二下"},
            {"分册","必修一","必修二","必修三","必修四","必修五","选修1-1",
                    "选修1-2","选修2-1","选修2-2","选修2-3","选修3-1",
                    "选修3-3","选修3-4","选修4-1","选修4-2","选修4-4",
                    "选修4-5","选修4-6","选修4-7"},
            {"分册","高一上册","高一下册","高二上册","高二下册","高三上册","高三下册"},
            {"分册","必修一","必修二","必修三","必修四","必修五","选修1-1",
                    "选修1-2","选修2-1","选修2-2","选修2-3","选修3-3",
                    "选修4-1","选修4-2","选修4-4", "选修4-5"},
            {"分册"},
            {"分册","必修一","必修二","必修三","必修四","必修五","选修2-1","选修2-2","选修2-3","选修4"},
            {"分册","必修一","必修二","必修三","必修四","必修五","选修1-1","选修1-2"},
            {"分册","全国版","北京版","参考版"},
            {"分册","研究院","师资培训"}
    };

    public static String getRelativePath(int bookVersionIndex,int bookPartIndex){
        String bookVersion = bookVersions[bookVersionIndex];
        String bookPart = allInfo[bookVersionIndex][bookPartIndex];
        return bookVersion+"/"+bookPart+"/";
    }
    public static int getBookVersionCount(){
        return bookVersions.length;
    }
    public static int getBookPartCount(int bookVersionIndex){
        return allInfo[bookVersionIndex].length;
    }
}
