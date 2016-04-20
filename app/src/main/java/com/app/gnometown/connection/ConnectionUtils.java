package com.app.gnometown.connection;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.app.gnometown.GnomeTown;
import com.app.gnometown.View.Splash.loadDataInteractorImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Map;


public class ConnectionUtils {

    public static final int GET = Request.Method.GET;


    private static void makeJsonObjectRequest(Object model, String url,
                                              CustomVolleyJsonListener listenerOk,
                                              CustomVolleyErrorListener listenerError,
                                              Map<String, String> headers, int timeoutMillis, int method, String identifier) {

        String request_headers = "";

        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request_headers += "\nKey = " + entry.getKey() + ", Value = " + entry.getValue();
            }

        }else{
            request_headers = "Sin headers";
        }

        Log.i(identifier+"_REQUEST_HEADERS", request_headers);

        String out;
        if (model instanceof JSONObject){
            out = model.toString();
        }else {
            out = objectToJsonString(model) ;
        }

        Log.i(identifier + "_REQUEST_DATA: ", out);

        JSONObject outJson = null;
        try {
            outJson = new JSONObject(out);
        } catch (JSONException e) {

        }
        CustomJsonObjectRequest jsObjRequest;

        if (headers == null)
            jsObjRequest = new CustomJsonObjectRequest(method, url, outJson,
                    listenerOk, listenerError);
        else
            jsObjRequest = new CustomJsonObjectRequest(method, url, outJson,
                    listenerOk, listenerError, headers);

        listenerOk.jsonRequest = jsObjRequest;
        listenerError.request = jsObjRequest;

        if (timeoutMillis != 0) {
            jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(timeoutMillis,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        }else{

            jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        }

        jsObjRequest.setTag(identifier);
        GnomeTown.getInstance().addToRequestQueue(jsObjRequest,identifier);

    }



    public static boolean isConnectingToInternet(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null)

                        return  info.getState() == NetworkInfo.State.CONNECTED;

        }
        return false;
    }


    private static String objectToJsonString(Object object){

        Gson gson =  new GsonBuilder().create();
        return gson.toJson(object);
    }


    public static void performRequest(String relativePath, Object model, final String identifier, Activity activity, int typeRequest, Map<String, String> headers, final Object extraData,ConnectionCallbacks callback) {

        performRequestGeneric(relativePath, model, identifier, activity, null, typeRequest, headers, 5000, extraData, true,callback);
    }



    private static void performRequestGeneric(String relativePath, Object model, final String identifier, final Activity activity, final Fragment fragment, int typeRequest, Map<String, String> headers, int timeout, final Object extraData, final boolean showLoaderDialog, final ConnectionCallbacks callbacks){

        if (ConnectionUtils.isConnectingToInternet(activity)) {

            CustomVolleyJsonListener responseListener = new CustomVolleyJsonListener() {
                @Override
                public void onResponse(JSONObject response) {

                   callbacks.onSuccess(identifier, response, extraData);

                }
            };

            CustomVolleyErrorListener errorListener = new CustomVolleyErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    callbacks.onFail(identifier, error);
                }
            };

            ConnectionUtils.makeJsonObjectRequest(model, relativePath, responseListener, errorListener, headers, timeout, typeRequest, identifier);

        } else {

            if (fragment != null){
                ((ConnectionCallbacks) fragment).onDisconnected(identifier);
            }else{
                ((ConnectionCallbacks) activity).onDisconnected(identifier);
            }
        }
    }


    public interface ConnectionCallbacks {
        /**
         * Called when the make any request.
         */
        void onSuccess(String identifier, JSONObject response, Object extraData);
        void onFail(String identifier, VolleyError error);
        void onDisconnected(String identifier);
    }

}
