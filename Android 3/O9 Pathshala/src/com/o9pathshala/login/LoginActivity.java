package com.o9pathshala.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.O9.pathshala.R;
import com.o9pathshala.dto.UserDTO;

public class LoginActivity extends Activity implements OnClickListener {
	EditText email, password;
	Button btnLogin;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		email = (EditText) findViewById(R.id.etemail);
		password = (EditText) findViewById(R.id.etpassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(this);
	}

	public void onClick(View v) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(email.getText().toString());
		userDTO.setPassword(password.getText().toString());
		Login login = new Login();
		login.checkLogin(userDTO, LoginActivity.this);

	}
}
