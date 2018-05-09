package com.munch.service.mysqlbean;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class ArticleContent {

    @Id
    private long artId;
    private String content;
    private Timestamp modifytime;

    public ArticleContent() {
    }

    public ArticleContent(long artId, String content) {
        this.artId = artId;
        this.content = content;
    }

    public long getArtId() {
        return artId;
    }

    public void setArtId(long artId) {
        this.artId = artId;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Timestamp getModifytime() {
        return modifytime;
    }

    public void setModifytime(Timestamp modifytime) {
        this.modifytime = modifytime;
    }

}
