/**
 * Copyright 2010-present Mobily.ws.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mobily.api.sms.utility;

import java.io.UnsupportedEncodingException;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class MyConnectionType {

	private static int TIME_OUT = 30000;// 30sec

	public static void get(Context context, String url, RequestParams params,
			org.json.JSONObject jsonObject,
			AsyncHttpResponseHandler responseHandler) {

		AsyncHttpClient client = new AsyncHttpClient();
		int timeoutConnection = TIME_OUT;// establishingConnection

		client.setConnectTimeout(timeoutConnection);
		client.setResponseTimeout(timeoutConnection);
		client.setTimeout(timeoutConnection);
		client.get(url, params, responseHandler);

	}

	public static void post(Context context, String url, RequestParams params,
			org.json.JSONObject jsonObject,
			AsyncHttpResponseHandler responseHandler) {
		AsyncHttpClient client = new AsyncHttpClient();
		(new PersistentCookieStore(context.getApplicationContext())).clear();
		int timeoutConnection = TIME_OUT;// establishingConnection
		client.setConnectTimeout(timeoutConnection);
		client.setResponseTimeout(timeoutConnection);
		client.setTimeout(timeoutConnection);

		cz.msebera.android.httpclient.entity.StringEntity se = null;
		try {
			se = new cz.msebera.android.httpclient.entity.StringEntity(jsonObject.toString(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		se.setContentType( new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		client.post(null, url, se, "application/json", responseHandler);

	}
}