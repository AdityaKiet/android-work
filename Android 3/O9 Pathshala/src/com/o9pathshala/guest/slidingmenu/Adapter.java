package com.o9pathshala.guest.slidingmenu;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.O9.pathshala.R;
import com.o9pathshala.dto.AdapterDTO;

public class Adapter extends BaseAdapter {
	private Context context;
	private ArrayList<AdapterDTO> adapterDTOs;

	public Adapter(Context context, ArrayList<AdapterDTO> adapterDTOs) {
		this.context = context;
		this.adapterDTOs = adapterDTOs;
	}

	public int getCount() {
		return adapterDTOs.size();
	}

	public Object getItem(int position) {
		return adapterDTOs.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.drawer_list_item, null);
		}

		ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
		TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
		TextView txtCount = (TextView) convertView.findViewById(R.id.counter);
		/*if (position == 0) {
			FirstRowView fv = new FirstRowView(context);
			fv.setTitle("Exams");
			return fv;
		} else {*/
			imgIcon.setImageResource(adapterDTOs.get(position).getIcon());
			txtTitle.setText(adapterDTOs.get(position).getTitle());
		/*}*/
		if (adapterDTOs.get(position).getCounterVisibility()) {
			txtCount.setText(adapterDTOs.get(position).getCount());
		} else {
			txtCount.setVisibility(View.GONE);
		}

		return convertView;
	}

	/*private class FirstRowView extends LinearLayout {
		private TextView textView;
		private ImageView imageView;

		@SuppressWarnings("deprecation")
		public FirstRowView(Context context) {
			super(context);
			this.setOrientation(HORIZONTAL);
			LinearLayout myLayout = new LinearLayout(context);
			myLayout.setBackgroundResource(R.drawable.actiob_bar);
			this.addView(myLayout, new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			textView = new TextView(context);
			textView.setText("");
			textView.setTypeface(Typeface.SERIF);
			textView.setTextSize(22);
			imageView = new ImageView(context);
			imageView.setImageResource(R.drawable.icon);
			myLayout.addView(imageView, new LinearLayout.LayoutParams(200,
					LayoutParams.MATCH_PARENT));
			myLayout.addView(textView, new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		}

		public void setTitle(String title) {
			textView.setText(title);
		}
	}*/
}
