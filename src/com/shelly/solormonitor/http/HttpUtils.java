package com.shelly.solormonitor.http;

import org.apache.http.NameValuePair;

import android.content.Context;

public class HttpUtils {

	public static String postByHttpClient(Context context, String paramUrl, NameValuePair... nameValuePairs) {
		return CustomHttpClient.postFromWebByHttpClient(context, paramUrl, nameValuePairs);
	}

	public static String getByHttpClient(Context context, String paramUrl, NameValuePair... nameValuePairs) {
		return CustomHttpClient.getFromWebByHttpClient(context, paramUrl, nameValuePairs);
	}

	public static String postByHttpUrlConnection(String paramUrl, NameValuePair... nameValuePairs) {
		return CustomHttpURLConnection.PostFromWebByHttpURLConnection(paramUrl, nameValuePairs);
	}

	public static String getByHttpUrlConnection(String paramUrl, NameValuePair... nameValuePairs) {
		return CustomHttpURLConnection.getFromWebByHttpUrlConnection(paramUrl, nameValuePairs);
	}

	public static boolean isNetworkAvailable(Context context) {
		return NetworkHelper.isNetworkAvailable(context);
	}

	public static boolean isMobileDataEnable(Context context) {
		return NetworkHelper.isMobileDataEnable(context);
	}

	public static boolean isWifiDataEnable(Context context) {

		return NetworkHelper.isWifiDataEnable(context);
	}

	public static boolean isNetworkRoaming(Context context) {
		return NetworkHelper.isMoblieRoaming(context);
	}
}