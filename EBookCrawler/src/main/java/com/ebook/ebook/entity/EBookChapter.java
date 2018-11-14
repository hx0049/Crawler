package com.ebook.ebook.entity;

import javax.persistence.*;

@Entity
@Table(name = "ebook_chapter_content")
public class EBookChapter {

    @Id
    private String url;
    @Column
    private int orderNo;
    @Column
    private String ebookTitle;
    @Column
    private String chapterName;
    @Lob
    private String chapterContent;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getEbookTitle() {
        return ebookTitle;
    }

    public void setEbookTitle(String ebookTitle) {
        this.ebookTitle = ebookTitle;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterContent() {
        return chapterContent;
    }

    public void setChapterContent(String chapterContent) {
        this.chapterContent = chapterContent;
    }
}
