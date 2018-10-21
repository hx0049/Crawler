package com.cetc28.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "blog_article")
public class Article {
    @Id
    private String url;

    @ManyToOne
    private Author author;//authorName String
    @Column
    private String title;
    @Column
    private Date createTime;
    @Column
    private String readCount;

    @ElementCollection
    @CollectionTable(name = "blog_aricle_person_tag")
    private List<String> personTags;

    @ElementCollection
    @CollectionTable(name = "blog_aricle_label_tag")
    private List<String> labelTags;

    @Lob
    private String content;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public List<String> getPersonTags() {
        return personTags;
    }

    public void setPersonTags(List<String> personTags) {
        this.personTags = personTags;
    }

    public List<String> getLabelTags() {
        return labelTags;
    }

    public void setLabelTags(List<String> labelTags) {
        this.labelTags = labelTags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
