package com.alexstorm13.entity;

import javax.persistence.Entity;

/**
 * Created by AlexStorm13 A.K.A. King of everything A.K.A. Alex Brasser on 12/05/2017.
 */
@Entity
public class Notification extends BaseEntity {
    private String name;
    private String message;
    private String type;

    private Notification(){
        super();
    }

    public Notification(String name, String message, String type) {
        this.name = name;
        this.message = message;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
