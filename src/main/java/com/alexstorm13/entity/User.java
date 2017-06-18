package com.alexstorm13.entity;

import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexStorm13 A.K.A. King of everything A.K.A. Alex Brasser on 11/05/2017.
 */
@Entity
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String imgUrl;
    @OneToOne
    private Session session;
    @ManyToMany
    private List<Bundle> bundles;
    private String role;

    private User() {
        super();
        bundles = new ArrayList<>();
    }

    public User(String firstName, String lastName, String email, String password, String imgUrl, String role) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.imgUrl = imgUrl;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        session.setUser(this);
        this.session = session;
    }

    public List<Bundle> getBundles() {
        return bundles;
    }

    public void addBundle(Bundle bundle) {
        this.bundles.add(bundle);
        bundle.addUser(this);
    }

    public void removeBundle(Bundle bundle) {
        this.bundles.remove(bundle);
        bundle.removeUser(this);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
