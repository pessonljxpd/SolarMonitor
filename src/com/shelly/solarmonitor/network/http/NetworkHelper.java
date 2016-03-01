package com.shelly.solarmonitor.network.http;

import com.shelly.solarmonitor.utils.LogUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class NetworkHelper {

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectService = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectService == null) {
			return false;
		} else {
			NetworkInfo[] allNetworkInfo = connectService.getAllNetworkInfo();
			if (allNetworkInfo != null) {
				for (int i = 0; i < allNetworkInfo.length; i++) {
					NetworkInfo networkInfo = allNetworkInfo[i];
					if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public static boolean isMoblieRoaming(Context context) {
		ConnectivityManager connectService = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectService == null) {
			LogUtils.d("Cann't get connectivityManager");
			return false;
		} else {
			NetworkInfo activeNetworkInfo = connectService.getActiveNetworkInfo();
			if (activeNetworkInfo != null && activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
				TelephonyManager telephonyService = (TelephonyManager) context
						.getSystemService(Context.TELEPHONY_SERVICE);
				if (telephonyService != null && telephonyService.isNetworkRoaming()) {
					LogUtils.d("network is roaming");
					return true;
				} else {
					LogUtils.d("network isn't roaming");
					return false;
				}
			} else {
				LogUtils.d("Connect type isn't 'ConnectivityManager.TYPE_MOBILE'");
			}
		}

		return false;
	}

	public static boolean isMobileDataEnable(Context context) {
		ConnectivityManager connectService = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectService.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		return networkInfo.isConnectedOrConnecting();
	}

	public static boolean isWifiDataEnable(Context context) {
		ConnectivityManager connectService = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectService.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		return networkInfo.isConnectedOrConnecting();
	}
}
