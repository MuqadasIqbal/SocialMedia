package com.example.socialmedia.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmedia.Adapter.DashboardAdapter;
import com.example.socialmedia.Adapter.StoryAdapter;
import com.example.socialmedia.Model.DasboardModel;
import com.example.socialmedia.Model.StoryModel;
import com.example.socialmedia.R;
import com.example.socialmedia.databinding.FragmentHomeBinding;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
FragmentHomeBinding binding;
ArrayList<StoryModel>list;
RecyclerView storyRV,dashboardRv;
ArrayList<DasboardModel>dasboardlist;
    public HomeFragment() {

    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       /* binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();*/
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        storyRV=view.findViewById(R.id.storyRv);
        list=new ArrayList<>();
        list.add(new StoryModel(R.drawable.img, R.drawable.video, R.drawable.doll,"Maria"));
        list.add(new StoryModel(R.drawable.mehrab, R.drawable.video,R.drawable.doll,"Mehrab"));
        list.add(new StoryModel(R.drawable.doll, R.drawable.live,R.drawable.doll,"Alia"));
        list.add(new StoryModel(R.drawable.img, R.drawable.video,R.drawable.doll,"Maria"));
        list.add(new StoryModel(R.drawable.mehrab, R.drawable.live,R.drawable.doll,"Maria"));

        StoryAdapter adapter=new StoryAdapter(list,getContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        storyRV.setLayoutManager(layoutManager);
        storyRV.setNestedScrollingEnabled(false);
        storyRV.setAdapter(adapter);

        dashboardRv=view.findViewById(R.id.dashboardRecylr);
        dasboardlist=new ArrayList<>();
        dasboardlist.add(new DasboardModel(R.drawable.mehrab,R.drawable.mehrab,R.drawable.bookmark,"Dansh","Travel","300","12","25"));
        dasboardlist.add(new DasboardModel(R.drawable.img,R.drawable.doll, R.drawable.bookmark,"Dansh","Travel","300","12","25"));
        dasboardlist.add(new DasboardModel(R.drawable.doll,R.drawable.img, R.drawable.bookmark,"Dansh","Travel","300","12","25"));
        dasboardlist.add(new DasboardModel(R.drawable.mehrab,R.drawable.mehrab, R.drawable.bookmark,"Dansh","Travel","300","12","25"));

        DashboardAdapter dashboardAdapter=new DashboardAdapter(dasboardlist,getContext());
        LinearLayoutManager layoutManager1=new LinearLayoutManager(getContext());
        dashboardRv.setLayoutManager(layoutManager1);
        dashboardRv.setAdapter(dashboardAdapter);
        return view;
    }

}