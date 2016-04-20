package com.app.gnometown.Model;

import io.realm.RealmObject;

/**
 * Created by andreinasarda on 17/4/16.
 */
public class RealmString extends RealmObject{

    private String string;

    public RealmString(String s) {
        string = s;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public RealmString() {
    }
}
