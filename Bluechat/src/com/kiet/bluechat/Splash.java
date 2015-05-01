package com.kiet.bluechat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class Splash extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Thread timer=new Thread(){
			@SuppressLint("NewApi")
			public void run(){
			try{
				
				sleep(5000);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			finally{
				Intent intent=new Intent("com.kiet.bluechat.MainActivity");
				startActivity(intent);
			}
			}
			};
			timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
   
}
