package com.alexstorm13.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by AlexStorm13 A.K.A. King of everything A.K.A. Alex Brasser on 12/05/2017.
 */
@Entity
public class Software extends BaseEntity{
    private String name;
    private String imgUrl;
    private String description;
    private String url;

    @ManyToMany(mappedBy = "softwareList", cascade = CascadeType.PERSIST)
    private List<Bundle> bundleList;

    private Software(){
        super();
    }
    public Software(String name, String imgUrl, String description, String url) {
        this();
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setBundleList(List<Bundle> bundleList) {
        this.bundleList = bundleList;
    }
}
