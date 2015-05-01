package com.example;

import java.util.Calendar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends Activity 
{

    private PendingIntent pendingIntent;
    
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    

    Calendar calendar = Calendar.getInstance();
    
    calendar.set(Calendar.MONTH, 3);
    calendar.set(Calendar.YEAR, 2015);
    calendar.set(Calendar.DAY_OF_MONTH, 15);

    calendar.set(Calendar.HOUR_OF_DAY, 12);
    calendar.set(Calendar.MINUTE, 47);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.AM_PM,Calendar.AM);
    
    Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);
    pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent,0);
    
    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
    alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
   
	} //end onCreate
	

	
	
	
}