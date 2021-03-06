package com.canteen.login;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andappers.canteenproject.R;

public class MainActivity extends Activity implements OnClickListener {
	EditText account_number, password, account_number_reset;
	Button login_button;
	InputStream is;
	HttpEntity entity;
	SharedPreferences sp = null;
	SharedPreferences.Editor edit = null;
	TextView forgotPassword;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		account_number = (EditText) findViewById(R.id.etAccount_Number);
		password = (EditText) findViewById(R.id.etPassword);
		login_button = (Button) findViewById(R.id.btnLogin);
		forgotPassword = (TextView) findViewById(R.id.forgotPassword);
		sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		edit = sp.edit();
		login_button.setOnClickListener(this);
		forgotPassword.setOnClickListener(this);
	}

	public void onClick(View v) {
		if (v.getId() == R.id.btnLogin) {
			String account_number_string = account_number.getText().toString()
					.trim();
			String password_string = password.getText().toString().trim();
			if (account_number_string == null || password_string == null
					&& account_number_string.equals("")
					|| password_string.equals("")) {
				new AlertDialog.Builder(MainActivity.this)
						.setMessage(
								"Enter something in account number and password")
						.setTitle("Warning !!").setCancelable(true)
						.setIcon(R.drawable.ic_launcher)
						.setNegativeButton("Okay", null).show();
			} else {
				checkLogin();
			}
		} else if (v.getId() == R.id.forgotPassword) {
			openPopUpForReset();
		}
	}

	private void openPopUpForReset() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				MainActivity.this);
		alertDialog.setTitle("Reset Password");
		account_number_reset = new EditText(MainActivity.this);
		LinearLayout layout = new LinearLayout(MainActivity.this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(account_number_reset);
		alertDialog.setView(layout);
		alertDialog.setMessage("Enter your account number");
		alertDialog.setIcon(R.drawable.ic_launcher);
		alertDialog.setNegativeButton("Cancel", null);
		alertDialog.setPositiveButton("Confirm",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (account_number_reset.getText() == null
								|| account_number_reset.getText().toString()
										.equals("")) {
							new AlertDialog.Builder(MainActivity.this)
									.setMessage(
											"Enter something in account number")
									.setTitle("Warning !!").setCancelable(true)
									.setIcon(R.drawable.ic_launcher)
									.setNegativeButton("Okay", null).show();
						} else {
							new ResetPassword().execute();
						}
					}
				});
		alertDialog.show();
	}

	class Task extends AsyncTask<String, String, Void> {
		private ProgressDialog progressDialog = new ProgressDialog(
				MainActivity.this);
		String result = "";

		protected void onPreExecute() {
			progressDialog.setTitle("Loading.....");
			progressDialog.setMessage("Loading.....");
			progressDialog.show();
			progressDialog.setOnCancelListener(new OnCancelListener() {

				@Override
				public void onCancel(DialogInterface arg0) {
					Task.this.cancel(true);
				}
			});
			super.onPreExecute();
		}

		protected Void doInBackground(String... params) {
			String id_string = account_number.getText().toString().trim();
			String pass_string = password.getText().toString().trim();
			List<NameValuePair> list = new ArrayList<NameValuePair>(1);
			list.add(new BasicNameValuePair("account_number", id_string));
			list.add(new BasicNameValuePair("password", pass_string));
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(
						"http://10.0.2.2/canteen/login.php");
				httpPost.setEntity(new UrlEncodedFormEntity(list));
				HttpResponse httpResponse = httpClient.execute(httpPost);
				Log.d("log", httpResponse.toString());
				entity = httpResponse.getEntity();
				Log.d("log", entity.toString());
				is = entity.getContent();
				Log.d("log", is.toString());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				Toast.makeText(getApplicationContext(),
						"Exception raised " + e.getMessage(), Toast.LENGTH_LONG)
						.show();
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(getApplicationContext(),
						"Exception raised " + e.getMessage(), Toast.LENGTH_LONG)
						.show();
			}
			try {
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
				Toast.makeText(getApplicationContext(), "error 2",
						Toast.LENGTH_LONG).show();
			}
			return null;
		}

		protected void onPostExecute(Void v) {
			Log.d("testrerslut", result);
			if (result.contains("false")) {
				progressDialog.dismiss();
				new AlertDialog.Builder(MainActivity.this)
						.setMessage("Wrong Account number and password...")
						.setTitle("Warning !!").setCancelable(true)
						.setIcon(R.drawable.ic_launcher)
						.setNegativeButton("Okay", null).show();
			} else {
				try {
					JSONArray jsonArray = new JSONArray(result);
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = null;
						jsonObject = jsonArray.getJSONObject(i);

						if (jsonObject.getString("isLogin").equals("1")) {
							progressDialog.dismiss();
							new AlertDialog.Builder(MainActivity.this)
									.setMessage(
											"You have already login from some other device.. please logout from there and login here again...")
									.setTitle("Warning !!").setCancelable(true)
									.setIcon(R.drawable.ic_launcher)
									.setNegativeButton("Okay", null).show();
						} else {
							new UpdateTask().execute();
							progressDialog.dismiss();
							edit.putBoolean("isLogin", true).commit();
							edit.putString("password",
									jsonObject.getString("password")).commit();
							edit.putString("name",
									jsonObject.getString("name").trim())
									.commit();
							edit.putString("account_number",
									jsonObject.getString("account_number"))
									.commit();
							edit.putString("balance",
									jsonObject.getString("balance")).commit();
							edit.putString("roll_no",
									jsonObject.getString("roll_no")).commit();
							edit.putString("email",
									jsonObject.getString("email")).commit();
							edit.putString("phoneno",
									jsonObject.getString("phoneno")).commit();
							Bundle bundel = new Bundle();
							bundel.putString("name",
									jsonObject.getString("name").trim());
							bundel.putString("account_number", jsonObject
									.getString("account_number").trim());
							bundel.putString("roll_no",
									jsonObject.getString("roll_no").trim());
							bundel.putString("balance",
									jsonObject.getString("balance").trim());
							bundel.putString("email",
									jsonObject.getString("email"));
							bundel.putString("phoneno",
									jsonObject.getString("phoneno").trim());
							Intent intent = new Intent(
									"com.canteen.profile.MainActivity");
							intent.putExtras(bundel);
							startActivity(intent);
							finish();
						}
					}
				} catch (JSONException e) {
					progressDialog.dismiss();
					Toast.makeText(getApplicationContext(),
							"Exception caused is " + e.getMessage(),
							Toast.LENGTH_LONG).show();
				}
			}

			super.onPostExecute(v);
		}

	}

	class UpdateTask extends AsyncTask<String, String, Void> {
		private ProgressDialog progressDialog = new ProgressDialog(
				MainActivity.this);
		String result = "";

		protected void onPreExecute() {

			progressDialog.setOnCancelListener(new OnCancelListener() {

				@Override
				public void onCancel(DialogInterface arg0) {
					UpdateTask.this.cancel(true);
				}
			});
			super.onPreExecute();
		}

		protected Void doInBackground(String... params) {

			List<NameValuePair> list = new ArrayList<NameValuePair>(1);
			list.add(new BasicNameValuePair("password", password.getText()
					.toString().trim()));
			list.add(new BasicNameValuePair("account_number", account_number
					.getText().toString().trim()));
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(
						"http://10.0.2.2/canteen/updateLogin.php");
				httpPost.setEntity(new UrlEncodedFormEntity(list));
				HttpResponse httpResponse = httpClient.execute(httpPost);
				Log.d("log", httpResponse.toString());
				entity = httpResponse.getEntity();
				Log.d("log", entity.toString());
				is = entity.getContent();
				Log.d("log", is.toString());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				Toast.makeText(getApplicationContext(),
						"Exception raised " + e.getMessage(), Toast.LENGTH_LONG)
						.show();
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(getApplicationContext(),
						"Exception raised " + e.getMessage(), Toast.LENGTH_LONG)
						.show();
			}
			try {
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
				Toast.makeText(getApplicationContext(), "error 2",
						Toast.LENGTH_LONG).show();
			}
			return null;
		}

		protected void onPostExecute(Void v) {
			Log.d("aditya", result);

			super.onPostExecute(v);
		}

	}

	class ResetPassword extends AsyncTask<String, String, Void> {
		private ProgressDialog progressDialog = new ProgressDialog(
				MainActivity.this);
		String result = "";

		protected void onPreExecute() {
			progressDialog.setTitle("Loading.....");
			progressDialog.setMessage("Please Wait.....");
			progressDialog.show();
			progressDialog.setOnCancelListener(new OnCancelListener() {

				@Override
				public void onCancel(DialogInterface arg0) {
					ResetPassword.this.cancel(true);
				}
			});
			super.onPreExecute();
		}

		protected Void doInBackground(String... params) {
			List<NameValuePair> list = new ArrayList<NameValuePair>(1);
			list.add(new BasicNameValuePair("account_number",
					account_number_reset.getText().toString()));
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(
						"http://10.0.2.2/canteen/resetpassword.php");
				httpPost.setEntity(new UrlEncodedFormEntity(list));
				HttpResponse httpResponse = httpClient.execute(httpPost);
				Log.d("log", httpResponse.toString());
				entity = httpResponse.getEntity();
				Log.d("log", entity.toString());
				is = entity.getContent();
				Log.d("log", is.toString());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				Toast.makeText(MainActivity.this.getApplicationContext(),
						"Exception raised " + e.getMessage(), Toast.LENGTH_LONG)
						.show();
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(MainActivity.this.getApplicationContext(),
						"Exception raised " + e.getMessage(), Toast.LENGTH_LONG)
						.show();
			}
			try {
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
				Toast.makeText(getApplicationContext(), "error 2",
						Toast.LENGTH_LONG).show();
			}
			return null;
		}

		protected void onPostExecute(Void v) {
			progressDialog.dismiss();
			Log.d("aditya", result);
			if (result.equals("false")) {
				Toast.makeText(getApplicationContext(),
						"You entered an invalid account number",
						Toast.LENGTH_LONG).show();
			} else
				Toast.makeText(getApplicationContext(), result,
						Toast.LENGTH_LONG).show();
			super.onPostExecute(v);
		}

	}

	private void checkLogin() {
		new Task().execute();
	}
}
