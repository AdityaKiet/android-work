package com.o9pathshala.login;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.o9pathshala.dto.AlertDialogDTO;
import com.o9pathshala.dto.UserDTO;
import com.o9pathshala.ui.AlertDialogActivity;

public class Login {
	Context context;
	UserDTO userDTO;

	public void checkLogin(UserDTO userDTO, Context context) {
		this.userDTO = userDTO;
		this.context = context;
		LoginAsynTask loginAsynTask = new LoginAsynTask();
		loginAsynTask.execute();
	}

	class LoginAsynTask extends AsyncTask<String, String, Void> {
		private ProgressDialog progressDialog = new ProgressDialog(context);
		String result = "";

		protected void onPreExecute() {
			progressDialog.setTitle("Loading.....");
			progressDialog.setMessage("Please Wait.....");
			progressDialog.show();
			super.onPreExecute();
		}

		protected Void doInBackground(String... params) {
			InputStream is = null;
			HttpEntity entity;
			List<NameValuePair> list = new ArrayList<NameValuePair>(1);
			list.add(new BasicNameValuePair("email", userDTO.getEmail()));
			list.add(new BasicNameValuePair("password", userDTO.getPassword()));
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(
						"http://192.168.51.103:80/O9pathshala/login.php");
				httpPost.setEntity(new UrlEncodedFormEntity(list));
				HttpResponse httpResponse = httpClient.execute(httpPost);
				entity = httpResponse.getEntity();
				is = entity.getContent();

				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(is));
				StringBuilder stringBuilder = new StringBuilder();
				String line = "";
				while ((line = bufferedReader.readLine()) != null) {
					stringBuilder.append(line);
				}
				is.close();
				result = stringBuilder.toString();

			} catch (Exception e) {
				Log.d("error", e.getMessage());
			}
			return null;
		}

		protected void onPostExecute(Void v) {
			progressDialog.dismiss();
			if (result.contains("false")) {
				AlertDialogDTO alertDialogDTO = new AlertDialogDTO();
				alertDialogDTO.setContext(context);
				alertDialogDTO
						.setMessage("Wrong Account number and password...");
				alertDialogDTO.setTitle("Warning !!");
				alertDialogDTO.setPositiveButon("Okay");
				AlertDialogActivity.alertDialogDisplay(alertDialogDTO, null);
			} else {
				SharedPreferences sharedPreferences = PreferenceManager
						.getDefaultSharedPreferences(context);
				SharedPreferences.Editor edit = sharedPreferences.edit();
				edit.putBoolean("isLogin", true);
				if (edit.commit()) {
					Intent intent = new Intent(
							"com.o9pathshala.ui.slidingmenu.fragments.MainActivity");
					context.startActivity(intent);
					((Activity) context).finish();
				} else {
					AlertDialogDTO alertDialogDTO = new AlertDialogDTO();
					alertDialogDTO.setContext(context);
					alertDialogDTO.setMessage("Error occured try again..");
					alertDialogDTO.setTitle("Warning !!");
					alertDialogDTO.setPositiveButon("Okay");
					AlertDialogActivity
							.alertDialogDisplay(alertDialogDTO, null);
				}

			}

			super.onPostExecute(v);
		}

	}

}
