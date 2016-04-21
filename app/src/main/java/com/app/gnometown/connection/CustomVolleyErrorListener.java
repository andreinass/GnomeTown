package com.app.gnometown.connection;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Listener to handle error response in service
 */
public class CustomVolleyErrorListener implements Response.ErrorListener {

	public Request<?> request;

	/**
	 * @param error
	 */
	@Override
	public void onErrorResponse(VolleyError error) {

	}

}
