package com.o9pathshala.logout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.O9.pathshala.R;
import com.o9pathshala.dto.AlertDialogDTO;
import com.o9pathshala.ui.AlertDialogActivity;

public class Logout {
	private Context context;

	public Logout() {
	}

	public Logout(Context context) {
		this.context = context;
	}

	public void logoutMember() {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Warning");
		builder.setMessage("Do you want to logout ?");
		builder.setPositiveButton("Yes !",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						doLogOut();
					}
				});
		builder.setIcon(R.drawable.logo);
		builder.setNegativeButton("Cancel", null);
		builder.show();
	}

	private void doLogOut() {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		SharedPreferences.Editor edit = sharedPreferences.edit();
		edit.putBoolean("isLogin", false);
		boolean isDone = edit.commit();
		if (isDone) {
			Intent intent = new Intent(
					"com.o9pathshala.splash.LoginTypeActivity");
			context.startActivity(intent);
			((Activity) context).finish();
		} else {
			AlertDialogDTO alertDialogDTO = new AlertDialogDTO();
			alertDialogDTO.setContext(context);
			alertDialogDTO.setMessage("Log out Unsuccessful !!");
			alertDialogDTO.setTitle("Sorry !!");
			alertDialogDTO.setPositiveButon("Okay");
			AlertDialogActivity.alertDialogDisplay(alertDialogDTO, null);
		}
	}
}
