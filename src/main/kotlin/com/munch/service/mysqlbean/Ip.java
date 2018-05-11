package com.munch.service.mysqlbean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Ip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ipId;
    private String ip;
    private long auth;
    private Timestamp addtime;


    public long getIpId() {
        return ipId;
    }

    public void setIpId(long ipId) {
        this.ipId = ipId;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


    public long getAuth() {
        return auth;
    }

    public void setAuth(long auth) {
        this.auth = auth;
    }


    public Timestamp getAddtime() {
        return addtime;
    }

    public void setAddtime(Timestamp addtime) {
        this.addtime = addtime;
    }

}
