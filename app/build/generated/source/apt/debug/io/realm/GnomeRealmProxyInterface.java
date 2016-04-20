package io.realm;


public interface GnomeRealmProxyInterface {
    public int realmGet$id();
    public void realmSet$id(int value);
    public String realmGet$name();
    public void realmSet$name(String value);
    public String realmGet$thumbnail();
    public void realmSet$thumbnail(String value);
    public int realmGet$age();
    public void realmSet$age(int value);
    public float realmGet$weight();
    public void realmSet$weight(float value);
    public float realmGet$height();
    public void realmSet$height(float value);
    public String realmGet$hair_color();
    public void realmSet$hair_color(String value);
    public RealmList<com.app.gnometown.Model.RealmString> realmGet$professions();
    public void realmSet$professions(RealmList<com.app.gnometown.Model.RealmString> value);
    public RealmList<com.app.gnometown.Model.RealmString> realmGet$friends();
    public void realmSet$friends(RealmList<com.app.gnometown.Model.RealmString> value);
}
