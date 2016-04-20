package com.app.gnometown.Model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by andreinasarda on 17/4/16.
 *
 * Class created to represent gnome population
 */
@RealmClass
public class Gnome extends RealmObject{

    @PrimaryKey
    private int id;
    private String name;
    private String thumbnail;
    private int age;
    private float weight;
    private float height;
    private String hair_color;
    private RealmList<RealmString> professions;
    private RealmList<RealmString> friends;

    public Gnome() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getHair_color() {
        return hair_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }


    public RealmList<RealmString> getProfessions() {
        return professions;
    }

    public void setProfessions(RealmList<RealmString> professions) {
        this.professions = professions;
    }

    public RealmList<RealmString> getFriends() {
        return friends;
    }

    public void setFriends(RealmList<RealmString> friends) {
        this.friends = friends;
    }
}
