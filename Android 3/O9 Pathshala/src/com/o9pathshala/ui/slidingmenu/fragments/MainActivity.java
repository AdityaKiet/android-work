package com.o9pathshala.ui.slidingmenu.fragments;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.O9.pathshala.R;
import com.o9pathshala.dto.AdapterDTO;
import com.o9pathshala.dto.AlertDialogDTO;
import com.o9pathshala.logger.ALogger;
import com.o9pathshala.logout.Logout;
import com.o9pathshala.ui.AlertDialogActivity;
import com.o9pathshala.ui.IAlertLogic;
import com.o9pathshala.ui.slidingmenu.Adapter;

public class MainActivity extends Activity {
	private DrawerLayout drawerLayout;
	private ListView listView;
	private ActionBarDrawerToggle actionBarDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	private ArrayList<AdapterDTO> adapterDTOs;
	private Adapter adapter;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTitle = mDrawerTitle = getTitle();
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		listView = (ListView) findViewById(R.id.list_slidermenu);

		adapterDTOs = new ArrayList<AdapterDTO>();
		adapterDTOs.add(new AdapterDTO(navMenuTitles[0], navMenuIcons
				.getResourceId(0, -1)));
		adapterDTOs.add(new AdapterDTO(navMenuTitles[1], navMenuIcons
				.getResourceId(1, -1)));
		adapterDTOs.add(new AdapterDTO(navMenuTitles[2], navMenuIcons
				.getResourceId(2, -1)));
		adapterDTOs.add(new AdapterDTO(navMenuTitles[3], navMenuIcons
				.getResourceId(3, -1)));
		adapterDTOs.add(new AdapterDTO(navMenuTitles[4], navMenuIcons
				.getResourceId(4, -1)));
		adapterDTOs.add(new AdapterDTO(navMenuTitles[5], navMenuIcons
				.getResourceId(5, -1)));
		adapterDTOs.add(new AdapterDTO(navMenuTitles[6], navMenuIcons
				.getResourceId(6, -1)));
		navMenuIcons.recycle();
		listView.setOnItemClickListener(new SlideMenuClickListener());
		adapter = new Adapter(getApplicationContext(), adapterDTOs);
		listView.setAdapter(adapter);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();
			}
		};
		drawerLayout.setDrawerListener(actionBarDrawerToggle);

		if (savedInstanceState == null) {
			displayView(0);
		}
	}

	private class SlideMenuClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = drawerLayout.isDrawerOpen(listView);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	private void displayView(int position) {
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new Fragment1();
			break;
		case 1:
			fragment = new Fragment2();
			break;
		case 2:
			fragment = new Fragment3();
			break;
		case 3:
			fragment = new Fragment4();
			break;
		case 4:
			fragment = new Fragment5();
			break;
		case 5:
			fragment = new Fragment6();
			break;
		case 6:
			Logout logout = new Logout(MainActivity.this);
			logout.logoutMember();
			break;
		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			listView.setItemChecked(position, true);
			listView.setSelection(position);
			setTitle(navMenuTitles[position]);
			drawerLayout.closeDrawer(listView);
		} else {
			if (position != 6)
				Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		actionBarDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		actionBarDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void onBackPressed() {
		ALogger.printLog(MainActivity.this);
		IAlertLogic iAlertLogic = new IAlertLogic() {

			public void positiveButton() {
				MainActivity.this.finish();
			}

			public void negativeButton() {
			}
		};
		AlertDialogDTO alertDialogDTO = new AlertDialogDTO();
		alertDialogDTO.setContext(MainActivity.this);
		alertDialogDTO.setMessage("Are you sure you want to exit..");
		alertDialogDTO.setTitle("Alert !!");
		alertDialogDTO.setPositiveButon("Exit");
		alertDialogDTO.setNegativeButton("Cancel");
		AlertDialogActivity.alertDialogDisplay(alertDialogDTO, iAlertLogic);
	}

}
