package com.teo.cakes.service.model;

import java.util.Objects;

/**
 * Model class for Cake object
 */


public class Cake {

    public String title;
    public String desc;
    public String image;

    public Cake(){

    }

    public Cake(String title, String desc, String image) {
        this.title=title;
        this.desc=desc;
        this.image =image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cake cake = (Cake) o;
        return Objects.equals(title, cake.title) &&
                Objects.equals(desc, cake.desc) &&
                Objects.equals(image, cake.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, desc, image);
    }
}