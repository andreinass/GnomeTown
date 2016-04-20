package com.app.gnometown.connection;

import com.android.volley.Response;

import org.json.JSONObject;

public class CustomVolleyJsonListener implements Response.Listener<JSONObject> {

	public CustomJsonObjectRequest jsonRequest;

	@Override
	public void onResponse(JSONObject response) {}

}
