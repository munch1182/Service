package com.munch.service.resbean;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class ResArticleBean {

    @Id
    private long artId;
    private String title;
    private String author;
    private String content;
    private Timestamp createTime;
    private Timestamp lastModify;

    public ResArticleBean() {
    }

    public ResArticleBean(long artId, String title, String author, String content, Timestamp createTime, Timestamp lastModify) {
        this.artId = artId;
        this.title = title;
        this.author = author;
        this.content = content;
        this.createTime = createTime;
        this.lastModify = lastModify;
    }

    public long getArtId() {
        return artId;
    }

    public void setArtId(long artId) {
        this.artId = artId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastModify() {
        return lastModify;
    }

    public void setLastModify(Timestamp lastModify) {
        this.lastModify = lastModify;
    }
}
