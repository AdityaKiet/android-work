package com.o9pathshala.guest.test;

import com.O9.pathshala.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AllTests extends Activity {
	ListView listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		String[] tests = { "First", "Second" };
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alltests);
		listview = (ListView) findViewById(R.id.lstAllTests);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(AllTests.this,
				android.R.layout.simple_list_item_1, tests);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == 0) {
					
				}
			}
		});
	}

}
