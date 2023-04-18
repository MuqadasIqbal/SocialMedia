package com.example.socialmedia.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmedia.Adapter.NotificationAdapter;
import com.example.socialmedia.Model.NotificationModel;
import com.example.socialmedia.R;

import java.util.ArrayList;


public class Notification2Fragment extends Fragment {
RecyclerView recyclerView;
ArrayList<NotificationModel>list;
NotificationAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_notification2, container, false);
       recyclerView=view.findViewById(R.id.Notification2RV);
       list=new ArrayList<>();
        list.add(new NotificationModel(R.drawable.alia,"<b>Alia</b> mention in your comment","Just Now"));
        list.add(new NotificationModel(R.drawable.mehrab,"<b>Mehrab</b> mention in your comment","3 hour"));
        list.add(new NotificationModel(R.drawable.alia,"<b>Mehrab</b> mention in your comment","Just Now"));
        list.add(new NotificationModel(R.drawable.img,"<b>Mehrab</b> mention in your comment","4 Hour"));
        list.add(new NotificationModel(R.drawable.alia,"<b>Alia</b> mention in your comment","Just Now"));
        list.add(new NotificationModel(R.drawable.doll,"<b>Tanzeel</b> mention in your comment","5 hour"));
        list.add(new NotificationModel(R.drawable.alia,"<b>Mehrab</b> mention in your comment","Just Now"));
        list.add(new NotificationModel(R.drawable.alia,"<b>Alia</b> mention in your comment","Just Now"));
        list.add(new NotificationModel(R.drawable.alia,"<b>Mehrab</b> mention in your comment","40 minutes"));
        list.add(new NotificationModel(R.drawable.alia,"<b>Mehrab</b> mention in your comment","40 minutes"));
        list.add(new NotificationModel(R.drawable.alia,"<b>Mehrab</b> mention in your comment","40 minutes"));
        list.add(new NotificationModel(R.drawable.alia,"<b>Mehrab</b> mention in your comment","40 minutes"));
        list.add(new NotificationModel(R.drawable.alia,"<b>Alia</b> mention in your comment","Just Now"));
        list.add(new NotificationModel(R.drawable.doll,"<b>Alia</b> mention in your comment","Just Now"));
        list.add(new NotificationModel(R.drawable.mehrab,"<b>Alia</b> mention in your comment","Just Now"));
        NotificationAdapter adapter=new NotificationAdapter(list,getContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
}