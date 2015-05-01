package com.example.alarmmanagerexample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

public class RingAlarm extends BroadcastReceiver {
	    
	   MediaPlayer mp=null ;
	  
	   @Override
	    public void onReceive(Context context, Intent intent) {
	        NotificationManager mNM;
	        mNM = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
	        // Set the icon, scrolling text and timestamp
	        Notification notification = new Notification(R.drawable.ic_launcher, "Medi Craft", System.currentTimeMillis());
	        // The PendingIntent to launch our activity if the user selects this notification
	        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, RingAlarm.class), 0);
	        // Set the info for the views that show in the notification panel.
	        notification.setLatestEventInfo(context, "STring", "Have you checked out todays health tip?", contentIntent);
	        // Send the notification.
	        // We use a layout id because it is a unique number. We use it later to cancel.
	        mNM.notify(100, notification);
	       /* AlarmService service = new AlarmService(context);
	        service.startAlarm();*/
	    }
	/*	@Override
		protected void onCreate(Bundle savedInstanceState) {
			
			        super.onCreate(savedInstanceState);
			        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			                WindowManager.LayoutParams.FLAG_FULLSCREEN);
			        
			       // setContentView(R.layout.alarm);
			     // Button stopAlarm = (Button) findViewById(R.id.stopAlarm);
			        
			        mp = MediaPlayer.create(getBaseContext(),R.raw.audio);
			        mp.start();
			        
		} 
			        stopAlarm.setOnTouchListener(new OnTouchListener() {
			            
						@Override
						public boolean onTouch(View arg0, MotionEvent arg1) {
							// TODO Auto-generated method stub
							mp.stop();
			                finish();
			                return false;
						}
			        });
			 
			        playSound(this, getAlarmUri());
			    }
			 
			    private void playSound(final Context context, Uri alert) {
			         
			        
			        Thread background = new Thread(new Runnable() {
						public void run() {
							try {
								
	    		               mp.start();
	    		               
							} catch (Throwable t) {
								Log.i("Animation", "Thread  exception "+t);
							}	
				        }
	        	 });
	        	 background.start();
			   }
			 
			    @Override
				protected void onDestroy() {
					super.onDestroy();
					mp.stop();
				}		        //Get an alarm sound. Try for an alarm. If none set, try notification,
			        //Otherwise, ringtone.
			    private Uri getAlarmUri() {
			    	
			        Uri alert = RingtoneManager
			                .getDefaultUri(RingtoneManager.TYPE_ALARM);
			        if (alert == null) {
			            alert = RingtoneManager
			                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			            if (alert == null) {
			                alert = RingtoneManager
			                        .getDefaultUri(RingtoneManager.TYPE_RINGTONE);
			            }
			        }
			        return alert;
			    }
			*/
}
