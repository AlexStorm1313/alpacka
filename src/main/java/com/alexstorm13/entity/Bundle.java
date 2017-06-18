package com.alexstorm13.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexStorm13 A.K.A. King of everything A.K.A. Alex Brasser on 12/05/2017.
 */
@Entity
public class Bundle extends BaseEntity {
    private String name;
    private String imgUrl;
    private String description;
    @ManyToMany(mappedBy = "bundles", cascade = CascadeType.PERSIST)
    private List<User> users;

    @ManyToMany()
    private List<Software> software;

    @ElementCollection
    private List<Long> ownerIds;

    private Bundle() {
        super();
        software = new ArrayList<>();
        ownerIds = new ArrayList<>();
        users = new ArrayList<>();
    }

    public Bundle(String name, String imgUrl, String description) {
        this();
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getOwners() {
        return ownerIds;
    }

    public void addOwner(User user) {
        this.ownerIds.add(user.getId());
    }

    public void removeOwner(User user) {
        this.ownerIds.remove(user.getId());
    }


    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public List<Software> getSoftware() {
        return software;
    }

    public void addSoftware(Software software) {
        this.software.add(software);
        software.addBundle(this);
    }

    public void removeSoftware(Software software) {
        this.software.remove(software);
        software.removeBundle(this);
    }
}
