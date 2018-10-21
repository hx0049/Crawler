package com.cetc28.entity;


import javax.persistence.*;

@Entity
@Table(name = "blog_author")
public class Author {
    @Id
    private String id;

    @Column
    private String name;

    @Lob
    private byte[] photo;

    @Column
    private String selfArticleNum;

    @Column
    private String fansNum;

    @Column
    private String othersLikeNum;

    @Column
    private String commentNum;

    @Column
    private String visitNo;

    @Column
    private String rankNo;

    @Column
    private String jifen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getSelfArticleNum() {
        return selfArticleNum;
    }

    public void setSelfArticleNum(String selfArticleNum) {
        this.selfArticleNum = selfArticleNum;
    }

    public String getFansNum() {
        return fansNum;
    }

    public void setFansNum(String fansNum) {
        this.fansNum = fansNum;
    }

    public String getOthersLikeNum() {
        return othersLikeNum;
    }

    public void setOthersLikeNum(String othersLikeNum) {
        this.othersLikeNum = othersLikeNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getVisitNo() {
        return visitNo;
    }

    public void setVisitNo(String visitNo) {
        this.visitNo = visitNo;
    }

    public String getRankNo() {
        return rankNo;
    }

    public void setRankNo(String rankNo) {
        this.rankNo = rankNo;
    }

    public String getJifen() {
        return jifen;
    }

    public void setJifen(String jifen) {
        this.jifen = jifen;
    }
}
