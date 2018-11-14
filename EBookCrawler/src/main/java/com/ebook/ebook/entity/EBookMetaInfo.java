package com.ebook.ebook.entity;

import javax.persistence.*;

@Entity
@Table(name = "ebook_meta_info")
public class EBookMetaInfo {

    @Id
    private String url;
    @Column
    private String firstOrder;
    @Column
    private String secondOrder;
    @Column
    private String name;
    @Column
    private Integer chapterCount;
    @Lob
    private String describeInfo;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFirstOrder() {
        return firstOrder;
    }

    public void setFirstOrder(String firstOrder) {
        this.firstOrder = firstOrder;
    }

    public String getSecondOrder() {
        return secondOrder;
    }

    public void setSecondOrder(String secondOrder) {
        this.secondOrder = secondOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getChapterCount() {
        return chapterCount;
    }

    public void setChapterCount(Integer chapterCount) {
        this.chapterCount = chapterCount;
    }

    public String getDescribeInfo() {
        return describeInfo;
    }

    public void setDescribeInfo(String describeInfo) {
        this.describeInfo = describeInfo;
    }

}
