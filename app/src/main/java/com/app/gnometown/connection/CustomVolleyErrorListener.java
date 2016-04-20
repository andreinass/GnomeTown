package com.app.gnometown.connection;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

public class CustomVolleyErrorListener implements Response.ErrorListener {

	public Request<?> request;

	@Override
	public void onErrorResponse(VolleyError error) {

	}

}
