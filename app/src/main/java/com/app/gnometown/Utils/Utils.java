package com.app.gnometown.Utils;

import com.app.gnometown.R;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Random;

import io.realm.RealmObject;

/**
 * Created by andreinasarda on 17/4/16.
 */
public class Utils {


    public static GsonBuilder getGsonStrategy(){


        GsonBuilder  gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                });



        return gson;
    }

    public static int randomColors() {

        Random r = new Random();
        int i = r.nextInt(3) + 1;

        switch (i){

            case 0:

                return R.color.random_1;


            case 1:

                return R.color.random_2;

            case 2:

                return R.color.random_3;

            default:

                return R.color.black_alpha;


        }

    }
}
