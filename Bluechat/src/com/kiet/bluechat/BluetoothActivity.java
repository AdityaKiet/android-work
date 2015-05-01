package com.kiet.bluechat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class BluetoothActivity extends Activity {

	@SuppressLint("ShowToast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.bluetooth_activity);

	}

	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater1 = getMenuInflater();
		inflater1.inflate(R.menu.switch_bt, menu);

		return super.onCreateOptionsMenu(menu);

	}

	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.myswitch:
			
			Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT)
					.show();
			/*
			 * BluetoothAdapter bluetooth =
			 * BluetoothAdapter.getDefaultAdapter(); bluetooth.enable();
			 */
			/*
			 * BluetoothAdapter bluetooth =
			 * BluetoothAdapter.getDefaultAdapter(); if(!(bluetooth.enable())) {
			 * Toast.makeText(getApplicationContext(),
			 * "Turning ON Bluetooth",Toast.LENGTH_LONG );
			 * startActivityForResult(new
			 * Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 0);
			 * 
			 * }
			 * 
			 * 
			 * else { bluetooth.disable();
			 * Toast.makeText(getApplicationContext(), "TURNING OFF BLUETOOTH",
			 * Toast.LENGTH_LONG); }
			 */

			return true;

		default:
			return super.onOptionsItemSelected(item);

		}

	}
}
