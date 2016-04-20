package com.app.gnometown.View.Splash;

import android.app.Activity;

import com.android.volley.VolleyError;
import com.app.gnometown.Model.Gnome;
import com.app.gnometown.Model.RealmString;

import com.app.gnometown.Model.Response;
import com.app.gnometown.Utils.Utils;
import com.app.gnometown.connection.ConnectionUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;


/**
 * Created by andreinasarda on 17/4/16.
 */
public class loadDataInteractorImpl implements
        loadDataInteractor,
        ConnectionUtils.ConnectionCallbacks
{

    public static final String path="https://raw.githubusercontent.com/AXA-GROUP-SOLUTIONS/mobilefactory-test/master/data.json";
    public static final String codeRequest="REQUEST";

    onLoadDataListener listener;

    @Override
    public void loadData(Activity a, onLoadDataListener listener) {

        ConnectionUtils.performRequest(path,null,codeRequest,a,ConnectionUtils.GET,null,null,this);
        this.listener = listener;
    }


    @Override
    public void onSuccess(String identifier, JSONObject response, Object extraData) {

        if(identifier.equals(codeRequest)){

            //Getting default json Builder
            GsonBuilder gsonBuilder = Utils.getGsonStrategy();

            Type token = new TypeToken<RealmList<RealmString>>(){}.getType();

            //Adding serializer to RealmString class, this is necessary in order to parse object and save data
            gsonBuilder.registerTypeAdapter(token, new TypeAdapter<RealmList<RealmString>>() {

                @Override
                public void write(JsonWriter out, RealmList<RealmString> value) throws IOException {
                    // Ignore


                }

                @Override
                public RealmList<RealmString> read(JsonReader in) throws IOException {
                    RealmList<RealmString> list = new RealmList<RealmString>();
                    in.beginArray();
                    while (in.hasNext()) {
                        list.add(new RealmString(in.nextString()));
                    }
                    in.endArray();
                    return list;
                }
            })
                    .create();


            Gson gson = gsonBuilder.create();
            JSONArray array = null;
            try {
                array = response.getJSONArray("Brastlewark");

                List<Gnome> objects = gson.fromJson(array.toString(), new TypeToken<List<Gnome>>(){}.getType());

                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();

                realm.copyToRealmOrUpdate(objects);
                realm.commitTransaction();
                listener.onSuccess();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void onFail(String identifier, VolleyError error) {

    }

    @Override
    public void onDisconnected(String identifier) {



    }
}
