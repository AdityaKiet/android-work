package com.kiet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class RepeatingAlarm extends BroadcastReceiver {
	  @Override
	  public void onReceive(Context context, Intent intent) {
	    Toast.makeText(context, "repeating_received", Toast.LENGTH_SHORT)
	        .show();
	  }
	}