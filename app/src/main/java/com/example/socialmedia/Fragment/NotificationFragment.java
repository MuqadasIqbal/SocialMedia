package com.example.socialmedia.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.socialmedia.Adapter.ViewPagerAdapter;
import com.example.socialmedia.R;
import com.google.android.material.tabs.TabLayout;

public class NotificationFragment extends Fragment {
 ViewPager viewPager;
 TabLayout tabLayout;
   
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_notification, container, false);

        viewPager=view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getFragmentManager()));
        
        tabLayout=view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        
        
        return view;
    }
}