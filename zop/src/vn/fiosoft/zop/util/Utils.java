package vn.fiosoft.zop.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
		if (mobile != null && mobile.isAvailable() && mobile.isConnected()) {

			val = true;
		}

		final android.net.NetworkInfo wifi = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifi != null && wifi.isAvailable() && wifi.isConnected()) {

			val = true;
		}

		return val;
	}
	
	public static void showDialogNetwork(final Activity activity){
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Network failure")
        	   .setMessage("This application requires a working data connection.")
               .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       activity.finish();
                   }
               });
        builder.create().show();
	}
}
