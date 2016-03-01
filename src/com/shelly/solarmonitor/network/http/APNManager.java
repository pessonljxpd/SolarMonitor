package com.shelly.solarmonitor.network.http;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;

import com.shelly.solarmonitor.utils.LogUtils;

public class APNManager {
	// 手机上网分为wap和net两种方式，使用net手机就会直接连入互联网，而使用wap则会中间多了一个代理网关，移动联通均是10.0.0.172，端口80。而写与联网有关的代码，wap和net是不一样的：
	// wap一般是这样：
	// URL url = new URL("http://10.0.0.172:80/index.htm");
	// HttpURLConnection hc = (HttpURLConnection) url.openConnection();
	// hc.setRequestProperty("X-Online-Host", "www.csdn.net");
	// net一般是这样：
	// URL url = new URL("http://www.csdn.net/index.htm");
	// HttpURLConnection hc = (HttpURLConnection) url.openConnection();

	private static final Uri APN_TABLE_URI = Uri.parse("content://telephony/carriers");
	private static final Uri PREFERRED_APN_URI = Uri.parse("content://telephony/carriers/preferapn");
	private static final String[] projection = { "_id", "apn", "type", "proxy", "current" };
	private static String netAPNID;

	public static boolean changeNetAPN(Context context) {
		if (isWapAPN(context)) {
			netAPNID = getNetAPNID(context);
			setAPN(context, netAPNID);
			int count = 1;
			do {
				try {
					Thread.sleep(500);
					count++;
					if (count * 500 >= 3000) {
						return false;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (isWapAPN(context)); // 设置APN,根据具体设备性能的不同，可能会消耗一些时间，等设置成功后，返回true；设置超时时间，避免长时间线程睡死
		}

		return true;
	}

	public static boolean isWapAPN(Context context) {
		boolean isWapAPN = false;
		String wapAPNID = getWapAPNID(context);
		String curAPNID = getCurAPNID(context);
		if (TextUtils.isEmpty(wapAPNID) && curAPNID.equals(wapAPNID)) {
			isWapAPN = true;
		}
		return isWapAPN;
	}

	public static void setAPN(Context context, String apnID) {
		ContentResolver resolver = context.getContentResolver();
		ContentValues values = new ContentValues();
		values.put("_id", apnID);
		resolver.update(PREFERRED_APN_URI, values, null, null);
		
		LogUtils.i("setAPN");
	}

	public static String getCurAPNID(Context context) {
		ContentResolver resolver = context.getContentResolver();
		String[] projection = new String[] { "_id" };
		Cursor cursor = resolver.query(PREFERRED_APN_URI, projection, null, null, null);
		String curAPNID = null;
		if (cursor != null && cursor.moveToNext()) {
			curAPNID = cursor.getString(cursor.getColumnIndex("_id"));
		}
		cursor.close();

		return curAPNID;
	}

	public static APN getCurAPNInfo(Context context) {
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(PREFERRED_APN_URI, projection, null, null, null);
		APN apnInfo = new APN();
		if (cursor != null && cursor.moveToNext()) {
			apnInfo.id = cursor.getString(cursor.getColumnIndex("_id"));
			apnInfo.type = cursor.getString(cursor.getColumnIndex("type"));
			apnInfo.apn = cursor.getString(cursor.getColumnIndex("apn"));
		}
		cursor.close();
		
		return apnInfo;
	}

	public static String getNetAPNID(Context context) {
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(APN_TABLE_URI, projection, "apn = \'cmnet\' and current = 1", null, null);
		if (cursor != null && cursor.moveToNext()) {
			return cursor.getString(cursor.getColumnIndex("_id"));
		}
		cursor.close();
		
		return null;
	}

	public static String getWapAPNID(Context context) {
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(APN_TABLE_URI, projection, "apn = \' cmwap\' and current = 1", null, null);
		if (cursor!= null && cursor.moveToNext()) {
			do {
				String id = cursor.getString(cursor.getColumnIndex("_id"));
				String proxy = cursor.getString(cursor.getColumnIndex("proxy"));
				if (!TextUtils.isEmpty(proxy)) {		//在current 不为空的时候，只有proxy不为空的才是wap
					return id;
				}
			} while (cursor.moveToNext());
		}
		cursor.close();
		
		return null;
	}

	public static ArrayList<APNManager.APN> getAPNList(Context context) {
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(APN_TABLE_URI, projection, null, null, null);
		
		ArrayList<APNManager.APN> apnList = new ArrayList<APNManager.APN>();
		if (cursor!=null && cursor.moveToNext()) {
			do {
				APN apnObj = new APN();
				apnObj.id = cursor.getString(cursor.getColumnIndex("_id"));
				apnObj.apn = cursor.getString(cursor.getColumnIndex("apn"));
				apnObj.type = cursor.getString(cursor.getColumnIndex("type"));
				
				apnList.add(apnObj);
			} while (cursor.moveToNext());
		}
		cursor.close();
		
		return apnList;
	}

	public static ArrayList<APNManager.APN> getAvailableAPNList(Context context) {
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(APN_TABLE_URI, projection, "current is not null", null, null);
		
		ArrayList<APNManager.APN> apnList = new ArrayList<APNManager.APN>();
		if (cursor!=null && cursor.moveToNext()) {
			do {
				APN apnObj = new APN();
				apnObj.id = cursor.getString(cursor.getColumnIndex("_id"));
				apnObj.apn = cursor.getString(cursor.getColumnIndex("apn"));
				apnObj.type = cursor.getString(cursor.getColumnIndex("type"));
				
				apnList.add(apnObj);
			} while (cursor.moveToNext());
		}
		cursor.close();
		
		return apnList;
	}

	static class APN {
		String id;
		String type;
		String apn;

		@Override
		public String toString() {
			return "APN [id=" + id + ", type=" + type + ", apn=" + apn + "]";
		}
	}

}
