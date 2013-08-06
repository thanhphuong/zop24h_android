package vn.fiosoft.zop.util;

import android.content.Context;
import android.net.ConnectivityManager;

public class Utils {
	/*
	 * Check 3g or wifi
	 */
	public static boolean isNetworkConnected(Context context) {
		boolean val = false;

		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		final android.net.NetworkInfo mobile = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (mobile.isAvailable() && mobile.isConnected()) {

			val = true;
		}

		final android.net.NetworkInfo wifi = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifi.isAvailable() && wifi.isConnected()) {

			val = true;
		}

		return val;
	}
}
