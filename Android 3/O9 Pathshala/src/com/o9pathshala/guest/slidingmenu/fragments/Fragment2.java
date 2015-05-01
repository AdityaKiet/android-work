package com.o9pathshala.guest.slidingmenu.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.O9.pathshala.R;

public class Fragment2 extends Fragment {
	
	public Fragment2(){}
	LinearLayout linearLayout;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		linearLayout = (LinearLayout) inflater.inflate(R.layout.test, container, false);
        return linearLayout;
    }
}
