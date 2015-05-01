package com.o9pathshala.guest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.O9.pathshala.R;

public class MainActivity extends Activity implements OnClickListener {
	EditText etEmailId, etPassword;
	Button btnLogin;
	TextView tvNewGuest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guest);
		btnLogin = (Button) findViewById(R.id.btnGuestLogin);
		etEmailId = (EditText) findViewById(R.id.etGuestID);
		etPassword = (EditText) findViewById(R.id.etGuestPassword);
		tvNewGuest = (TextView) findViewById(R.id.tvNewGuestUser);
		btnLogin.setOnClickListener(this);
		tvNewGuest.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnGuestLogin:
			Intent intent = new Intent(
					"com.o9pathshala.guest.slidingmenu.fragments.MainActivity");
			startActivity(intent);
			this.finish();
			break;
		case R.id.tvNewGuestUser:

			break;
		}
	}

}
