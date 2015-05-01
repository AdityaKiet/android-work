package com.example.alarmmanagerexample;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class AlarmManagerExample extends Activity {
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_manager_example);
		
		
		try {
			
	        Intent intent = new Intent(AlarmManagerExample.this, MyReceiver.class);
	        PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmManagerExample.this, 0, intent ,0);
	        AlarmManager am =(AlarmManager)getSystemService(Activity.ALARM_SERVICE);
	        am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(),2*60*60,pendingIntent);
			
		  } catch (Exception e) {}
	}
}
