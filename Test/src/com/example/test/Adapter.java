package com.example.test;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Adapter extends BaseAdapter {
	private Activity context;
	private List<String> list;

	public Adapter(Activity context, List<String> list) {
		this.context = context;
		this.list = new ArrayList<String>();
		this.list = list;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(final int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.row_list, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.textView1);
		txtTitle.setText("" + list.get(position));
		Button btn = (Button) rowView.findViewById(R.id.button1);
		btn.setId(position);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				list.remove(position);
				ListView listView = (ListView) ((Activity)context).findViewById(R.id.listView1);
				Adapter adapter = new Adapter(context, list);
				listView.setAdapter(adapter);
			}
		});
		return rowView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
}