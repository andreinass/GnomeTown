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

	// (jsonRequest == null) ? null :jsonRequest
	public CustomJsonObjectRequest(int method, String url, JSONObject jsonRequest,
									 Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
		super(method, url, jsonRequest , listener,
				errorListener);

		encoding = "UTF-8";
	}

	@Override
	protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
		try {
			//Log.v("RESPONSE","ENTRE AQUI EN PARSE NETWORK");

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

	@Override
	public String getBodyContentType() {
		return "application/json";
	}




}
