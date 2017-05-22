package com.alexstorm13.entity;

import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by AlexStorm13 A.K.A. King of everything A.K.A. Alex Brasser on 12/05/2017.
 */
@Entity
public class Session extends BaseEntity {
    private String key;
    private Date expiration;
    @OneToOne(mappedBy = "session", cascade = CascadeType.REMOVE)
    private User user;

    public Session(){
        super();
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 2);
        Date date = c.getTime();
        this.expiration = date;
        this.key = RandomStringUtils.randomAlphanumeric(64);
    }

    public String getKey() {
        return key;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
