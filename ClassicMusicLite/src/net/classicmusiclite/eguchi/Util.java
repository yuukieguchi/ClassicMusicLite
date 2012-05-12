package net.classicmusiclite.eguchi;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

/**
 * Utility Class
 * 
 * */
public class Util {

	public static void showToast(Context context, int string_id) {
		Resources res = context.getResources();
		String str = res.getString(string_id);
		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
	}

}
