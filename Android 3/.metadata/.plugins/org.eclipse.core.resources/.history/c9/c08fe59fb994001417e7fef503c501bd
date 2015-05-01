package com.o9pathshala.splash;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Splash {
	Context context;

	public boolean isLogin(Context context) {
		this.context = context;
		boolean result = false;
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		result = sharedPreferences.getBoolean("isLogin", false);
		return result;
	}
	
}
