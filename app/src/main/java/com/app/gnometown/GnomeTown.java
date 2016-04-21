package com.app.gnometown;

import android.app.Application;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.app.gnometown.connection.RequestManager;
import com.app.gnometown.connection.cache.ImageCacheManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by andreinasarda on 17/4/16.
 */
public class GnomeTown extends Application{

    private RequestQueue mRequestQueue;
    private String TAG = GnomeTown.class.getSimpleName();

    private static GnomeTown singleton;
    private RealmConfiguration config;
    private static int DISK_IMAGECACHE_SIZE = 1024*1024*50;
    private static Bitmap.CompressFormat DISK_IMAGECACHE_COMPRESS_FORMAT = Bitmap.CompressFormat.PNG;
    private static int DISK_IMAGECACHE_QUALITY = 100;  //PNG is lossless so quality is ignored but must be provided

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;

        config = new RealmConfiguration.Builder(this)
                .name("gnomes.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);

        init();
    }


    public static GnomeTown getInstance(){
        return singleton;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {

            Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);
            mRequestQueue.start();
        }

        return mRequestQueue;
    }

    public  <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }


    private void init() {
        RequestManager.init(this);
        createImageCache();
    }


    /*
    * Creating image cache
    * */
    private void createImageCache(){
        ImageCacheManager.getInstance().init(this,
                this.getPackageCodePath()
                , DISK_IMAGECACHE_SIZE
                , DISK_IMAGECACHE_COMPRESS_FORMAT
                , DISK_IMAGECACHE_QUALITY
                , ImageCacheManager.CacheType.MEMORY);
    }
}
