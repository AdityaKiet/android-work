package com.kiet;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.newproject.R;

public class MainActivity extends Activity {
	Toast mToast;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button startBtn = (Button) findViewById(R.id.start);
		startBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, RepeatingAlarm.class);
			      PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0,
			          intent, 0);

			      // We want the alarm to go off 30 seconds from now.
			      long firstTime = SystemClock.elapsedRealtime();
			      firstTime += 15 * 1000;

			      // Schedule the alarm!
			      AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
			      am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime,
			          15 * 1000, sender);

			      // Tell the user about what we did.
			      if (mToast != null) {
			        mToast.cancel();
			      }
			      mToast = Toast.makeText(MainActivity.this, "repeating_scheduled",
			          Toast.LENGTH_LONG);
			      mToast.show();
			}
		});

	}
	}