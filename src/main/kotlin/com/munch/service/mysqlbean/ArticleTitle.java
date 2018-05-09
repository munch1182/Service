package com.munch.service.mysqlbean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class ArticleTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long artId;
    private String title;
    private Timestamp createtime;
    private long userId;

    public ArticleTitle() {
    }

    public ArticleTitle(String title, long userId) {
        this.title = title;
        this.userId = userId;
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


    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
