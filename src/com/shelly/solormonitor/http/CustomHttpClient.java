package com.shelly.solormonitor.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;

/**
 * 在Android 2.x的版本中使用HttpURLConnection有bug，但是后来高级版本的Android已经将带来的bug修复，
 * 并且做了一些进一步优化的工作，所以建议在高级版本的Android系统(Android 2.3之后)使用HttpURLConnection，
 * 低版本的系统仍使用HttpClient
 */
public class CustomHttpClient {

	private static final String CHARSET_UTF8 = HTTP.UTF_8;
	private static final String CHARSET_GB2312 = "GB2312";
	private static DefaultHttpClient customHttpClient;
	private static CookieStore cookieStore;

	private CookieStore getCookieStore() {
		return cookieStore;
	}

	private CustomHttpClient() {
	}

	private static DefaultHttpClient getHttpClient(Context context) {
		if (customHttpClient == null) {
			synchronized (CustomHttpClient.class) {
				if (customHttpClient == null) {
					HttpParams params = new BasicHttpParams();
					HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
					HttpProtocolParams.setHttpElementCharset(params, CHARSET_UTF8);
					HttpProtocolParams.setUseExpectContinue(params, true);
					HttpProtocolParams.setUserAgent(params,
							"Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83)"
									+ "AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");

					ConnManagerParams.setTimeout(params, 10000);
					int connectTimeOut = 30000;
					if (!HttpUtils.isWifiDataEnable(context)) {
						connectTimeOut = 100000;
					}

					HttpConnectionParams.setConnectionTimeout(params, connectTimeOut);
					HttpConnectionParams.setSoTimeout(params, 40000);
					SchemeRegistry schReg = new SchemeRegistry();
					schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
					schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

					ClientConnectionManager connManager = new ThreadSafeClientConnManager(params, schReg);
					customHttpClient = new DefaultHttpClient(connManager, params);
				}
			}
		}

		return customHttpClient;
	}

	public static String postFromWebByHttpClient(Context context, String uri, NameValuePair... nameValuePairs) {
		String reqResultFromWeb = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (nameValuePairs != null) {
			for (int i = 0; i < nameValuePairs.length; i++) {
				params.add(nameValuePairs[i]);
			}
		}

		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, CHARSET_GB2312);
			HttpPost httpPost = new HttpPost(uri);
			httpPost.setEntity(entity);
			DefaultHttpClient httpClient = getHttpClient(context);
			if (cookieStore != null) {
				httpClient.setCookieStore(cookieStore);
			}
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				// TODO 没有返回200状态码，此次请求失败
			}

			HttpEntity httpEntity = httpResponse.getEntity();
			reqResultFromWeb = (httpEntity == null) ? null : EntityUtils.toString(httpEntity, CHARSET_UTF8);
			cookieStore = httpClient.getCookieStore();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return reqResultFromWeb;

	}

	public static String getFromWebByHttpClient(Context context, String uri, NameValuePair... nameValuePairs) {
		String reqResultFromWeb = null;
		StringBuilder sb = new StringBuilder();
		sb.append(uri);
		if (nameValuePairs != null && nameValuePairs.length > 0) {
			sb.append("?");
			for (int i = 0; i < nameValuePairs.length; i++) {
				if (i > 0) {
					sb.append("&");
				}
				sb.append(String.format("%s=%s", nameValuePairs[i].getName(), URLEncoder.encode(nameValuePairs[i].getValue())));
			}
		}

		HttpGet httpGet = new HttpGet(sb.toString());
		DefaultHttpClient httpClient = getHttpClient(context);
		if (cookieStore != null) {
			httpClient.setCookieStore(cookieStore);
		}
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);

			if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				// TODO 网页请求失败，说点什么吧。。。
			}
			HttpEntity httpEntity = httpResponse.getEntity();
			reqResultFromWeb = (httpEntity == null) ? null : EntityUtils.toString(httpEntity, CHARSET_UTF8);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		cookieStore = httpClient.getCookieStore();
		return reqResultFromWeb;
	}
}
