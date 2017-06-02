package com.alexstorm13.entity;

import com.alexstorm13.repository.AccessTokenRepository;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.Entity;

/**
 * Created by AlexStorm13 A.K.A. King of everything A.K.A. Alex Brasser on 12/05/2017.
 */
@Entity
public class AccessToken extends BaseEntity {
    private String token;

    public AccessToken(){
        super();
        this.token = RandomStringUtils.randomAlphanumeric(128);
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccessToken that = (AccessToken) o;

        return token.equals(that.token);
    }

    @Override
    public int hashCode() {
        return token.hashCode();
    }
}
