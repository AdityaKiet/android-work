package com.o9pathshala.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.o9.R;
import com.o9pathshala.dto.AlertDialogDTO;
import com.o9pathshala.ui.AlertDialogActivity;
import com.o9pathshala.ui.IAlertLogic;
import com.o9pathshala.util.NetworkCheck;

public class SplashActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		Thread thread = new Thread() {
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					Toast.makeText(getApplicationContext(),
							"Error occured. Try again later", Toast.LENGTH_LONG)
							.show();
				} finally {

					Splash splash = new Splash();
					if (splash.isLogin(SplashActivity.this
							.getApplicationContext())) {
						Intent intent = new Intent(
								"com.o9pathshala.ui.slidingmenu.fragments.MainActivity");
						startActivity(intent);
					} else {
						Intent intent = new Intent(
								"com.o9pathshala.splash.LoginTypeActivity");
						startActivity(intent);
					}
				}

			}
		};
		if (!NetworkCheck.isNetworkAvailable(SplashActivity.this)) {
			AlertDialogDTO alertDialogDTO = new AlertDialogDTO();
			alertDialogDTO.setContext(SplashActivity.this);
			alertDialogDTO.setMessage("Internet not available..");
			alertDialogDTO.setTitle("Alert !!");
			alertDialogDTO.setPositiveButon("Exit");
			IAlertLogic iAlertLogic = new IAlertLogic() {

				public void positiveButton() {
					SplashActivity.this.finish();
				}

				public void negativeButton() {
				}
			};
			AlertDialogActivity.alertDialogDisplay(alertDialogDTO, iAlertLogic);
		} else
			thread.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

	@Override
	public void onBackPressed() {

	}
}
