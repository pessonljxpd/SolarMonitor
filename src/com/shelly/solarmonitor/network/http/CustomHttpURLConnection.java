package com.shelly.solarmonitor.network.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.protocol.HTTP;

import com.shelly.solarmonitor.utils.LogUtils;

import android.content.Context;

/**
 * 在Android 2.x的版本中使用HttpURLConnection有bug，但是后来高级版本的Android已经将带来的bug修复，
 * 并且做了一些进一步优化的工作，所以建议在高级版本的Android系统(Android 2.3之后)使用HttpURLConnection，
 * 低版本的系统仍使用HttpClient
 */
public class CustomHttpURLConnection {

	private static final String CHARSET_UTF8 = HTTP.UTF_8;

	private CustomHttpURLConnection() {

	}

	public static String getFromWebByHttpUrlConnection(String uri, NameValuePair... nameValuePairs) {
		String reqResultFromWeb = null;
		HttpURLConnection conn = null;
		InputStream inputStream = null;
		BufferedReader bufReader = null;

		StringBuilder sb = new StringBuilder();
		sb.append(uri);
		if (nameValuePairs != null && nameValuePairs.length > 0) {
			sb.append("?");
			for (int i = 0; i < nameValuePairs.length; i++) {
				if (i > 0) {
					sb.append("&");
				}
				try {
					sb.append(String.format("%s=%s", nameValuePairs[i].getName(),
							URLEncoder.encode(nameValuePairs[i].getValue(), CHARSET_UTF8)));
				} catch (UnsupportedEncodingException e) {
					LogUtils.d("UnsupportedEncoding", e);
				}
			}
		}

		try {
			URL url = new URL(sb.toString());

			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(4000);
			conn.setRequestProperty("accept", "*/*");
			conn.connect();
			inputStream = conn.getInputStream();
			bufReader = new BufferedReader(new InputStreamReader(inputStream));

			String lineStr = null;
			while ((lineStr = bufReader.readLine()) != null) {
				reqResultFromWeb += lineStr;
			}
		} catch (MalformedURLException e) {
			reqResultFromWeb = null;
			LogUtils.d("MalformedURLException", e);
		} catch (IOException e) {
			reqResultFromWeb = null;
			LogUtils.d("IOException", e);
		} finally {
			if (bufReader != null) {
				try {
					bufReader.close();
				} catch (IOException e) {
					LogUtils.d("IOException", e);
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					LogUtils.d("IOException", e);
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}

		return reqResultFromWeb;
	}

	public static String PostFromWebByHttpURLConnection(String uri, NameValuePair... nameValuePairs) {
		String reqResultFromWeb = null;
		HttpURLConnection conn = null;
		BufferedReader bufReader = null;
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (nameValuePairs != null) {
			for (int i = 0; i < nameValuePairs.length; i++) {
				params.add(nameValuePairs[i]);
			}
		}
		
		try {
			URL url = new URL(uri);
			conn = (HttpURLConnection) url.openConnection();
			// 设置是否从httpUrlConnection读入，默认情况下是true;
			conn.setDoInput(true);
			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true,
			// 默认情况下是false;
			conn.setDoOutput(true);
			// 设定请求的方法为"POST"，默认是GET
			conn.setRequestMethod("POST");
			// 设置超时
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(4000);
			// Post 请求不能使用缓存
			conn.setUseCaches(false);
			// 设定传输的内容类型是可序列化的java对象(如果不设此项,在传送序列化对象时，当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 连接，从上述几条中url.openConnection()至此的配置必须要在connect之前完成
			conn.connect();
			
			ObjectOutput oot = new ObjectOutputStream(conn.getOutputStream()); 	//getOutputStream会隐式的进行connect操作；
			oot.writeObject(params);
			oot.flush();  
			oot.close();  

			InputStream inputStream = conn.getInputStream();
			bufReader = new BufferedReader(new InputStreamReader(inputStream));
			String strLine = null;
			while ((strLine = bufReader.readLine()) != null) {
				reqResultFromWeb += strLine;
			}
		} catch (MalformedURLException e) {
			reqResultFromWeb = null;
			LogUtils.d("MalformedURLException", e);
		} catch (ProtocolException e) {
			reqResultFromWeb = null;
			LogUtils.d("ProtocolException", e);
		} catch (IOException e) {
			reqResultFromWeb = null;
			LogUtils.d("IOException", e);
		} finally {
			if (bufReader!=null) {
				try {
					bufReader.close();
				} catch (IOException e) {
					LogUtils.d("IOException", e);
				}
			}
			if (conn!=null) {
				conn.disconnect();
			}
		}

		return reqResultFromWeb;
	}
}
