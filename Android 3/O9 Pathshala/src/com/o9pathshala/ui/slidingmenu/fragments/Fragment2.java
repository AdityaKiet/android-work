package com.o9pathshala.ui.slidingmenu.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.O9.pathshala.R;

public class Fragment2 extends Fragment {
	
	public Fragment2(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.login, container, false);
         
        return rootView;
    }
}
