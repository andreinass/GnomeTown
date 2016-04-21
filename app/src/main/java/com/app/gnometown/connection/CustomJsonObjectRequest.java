package com.app.gnometown.connection;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Class created to handle json responses from server
 *
 */
public class CustomJsonObjectRequest extends JsonObjectRequest {

    private Map<String, String> headers = new HashMap<>();
    String encoding;
    Map<String, String> responseHeaders = new HashMap<>();

    public CustomJsonObjectRequest(int method, String url,
                                   JSONObject jsonRequest, Response.Listener<JSONObject> listener,
                                   ErrorListener errorListener, Map<String, String> headers) {
        super(method, url, jsonRequest, listener, errorListener);
        this.headers = headers;
        encoding = "UTF-8";
    }

    /**
     *
     * Constructor for CustomJsonObjectRequest
     *
     * @param method
     * @param url
     * @param jsonRequest
     * @param listener
     * @param errorListener
     *
     */
    public CustomJsonObjectRequest(int method, String url, JSONObject jsonRequest,
                                   Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest , listener,
                errorListener);

        encoding = "UTF-8";
    }

    /**
     * @param response
     *
     * @return response parsed
     */
    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {


            if (response == null){

                return Response.success(null, null);
            }

            String jsonString = new String(response.data, encoding);

            //Allow null
            if (jsonString == null || jsonString.length() == 0) {
                return Response.success(null, HttpHeaderParser.parseCacheHeaders(response));
            }
            responseHeaders = response.headers;
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    /**
     * @return headersfor request
     *
     * @throws AuthFailureError
     *
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {

        if (headers.size() == 0)
            return super.getHeaders();
        else {
            if (!headers.containsKey("Content-Type"))
                headers.put("Content-Type", "application/json");
            if (!headers.containsKey("Accept"))
                headers.put("Accept", "application/json");

            headers.put("language", Locale.getDefault().getLanguage());
            return headers;
        }
    }

    /**
     *
     * @return body content for json request
     *
     */
    @Override
    public String getBodyContentType() {
        return "application/json";
    }




}
