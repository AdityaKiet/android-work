package com.o9pathshala.guest.slidingmenu.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.O9.pathshala.R;

public class FragmentHome extends Fragment implements OnClickListener,OnItemClickListener {
	private RelativeLayout btnAllTests, btnDiscussionFourm, btnShare,
			btnRateUs,btnMyAccount;
	private ListView lstAllTests,lstMyAccount;
	private ImageView imgArrowAllTests,imgArrowMyAccount;
	private String[] testNames = { "Aptitude tests", "Science Tests",
			"Commerce tests" };
	private String[] accounts = {"Profile","Settings"};
	public FragmentHome() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout linearLayout = (LinearLayout) inflater.inflate(
				R.layout.guest_home, container, false);
		btnAllTests = (RelativeLayout) linearLayout.findViewById(R.id.btnGuestAllTests);
		btnDiscussionFourm = (RelativeLayout) linearLayout.findViewById(R.id.btnGuestDiscussionFourm);
		btnShare = (RelativeLayout) linearLayout.findViewById(R.id.btnGuestShare);
		btnMyAccount = (RelativeLayout)linearLayout.findViewById(R.id.btnGuestAccount);
		btnRateUs = (RelativeLayout) linearLayout.findViewById(R.id.btnGuestRateUs);
		lstAllTests = (ListView) linearLayout.findViewById(R.id.lstGuestAllTests);
		lstMyAccount = (ListView)linearLayout.findViewById(R.id.lstGuestAccount);
		imgArrowAllTests = (ImageView) linearLayout.findViewById(R.id.imgArrowAllTests);
		imgArrowMyAccount = (ImageView)linearLayout.findViewById(R.id.imgArrowAccount);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, testNames);
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, accounts);
		lstAllTests.setAdapter(adapter);
		lstMyAccount.setAdapter(adapter1);
		lstMyAccount.setVisibility(View.GONE);
		lstAllTests.setVisibility(View.GONE);
		btnAllTests.setOnClickListener(this);
		btnRateUs.setOnClickListener(this);
		btnMyAccount.setOnClickListener(this);
		btnDiscussionFourm.setOnClickListener(this);
		btnShare.setOnClickListener(this);
		lstAllTests.setOnItemClickListener(this);
		lstMyAccount.setOnItemClickListener(this);
		return linearLayout;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnGuestAllTests:
			if (lstAllTests.isShown()) {
				imgArrowAllTests.setImageResource(R.drawable.arrow_down_button);
				lstAllTests.setVisibility(View.GONE);
			} else {
				imgArrowAllTests.setImageResource(R.drawable.arrow_up_button);
				lstAllTests.setVisibility(View.VISIBLE);
			}

			break;
		case R.id.btnGuestShare:
			Intent intent = new Intent(android.content.Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share App");
			intent.putExtra(android.content.Intent.EXTRA_TEXT,"Message here ... LINK HERE");
			startActivity(Intent.createChooser(intent, "Share via"));
			break;
		case R.id.btnGuestRateUs:

			break;
		case R.id.btnGuestDiscussionFourm:

			break;
		case R.id.btnGuestAccount:
			if (lstMyAccount.isShown()) {
				imgArrowMyAccount.setImageResource(R.drawable.arrow_down_button);
				lstMyAccount.setVisibility(View.GONE);
			} else {
				imgArrowMyAccount.setImageResource(R.drawable.arrow_up_button);
				lstMyAccount.setVisibility(View.VISIBLE);
			}

			break;
		default:
			break;
		}

	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Fragment newFragment = new FragmentTest();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.frame_container, newFragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}
}
