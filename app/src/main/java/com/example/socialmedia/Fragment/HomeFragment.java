package com.example.socialmedia.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmedia.Adapter.PostAdapter;
import com.example.socialmedia.Adapter.StoryAdapter;
import com.example.socialmedia.Model.Post;
import com.example.socialmedia.Model.StoryModel;
import com.example.socialmedia.R;
import com.example.socialmedia.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
FragmentHomeBinding binding;
ArrayList<StoryModel>list;
ArrayList<Post>postlist;
FirebaseAuth auth;
FirebaseDatabase database;
    public HomeFragment() {

    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();

        list=new ArrayList<>();
        list.add(new StoryModel(R.drawable.img, R.drawable.video, R.drawable.doll,"Maria"));
        list.add(new StoryModel(R.drawable.mehrab, R.drawable.video,R.drawable.doll,"Mehrab"));
        list.add(new StoryModel(R.drawable.doll, R.drawable.live,R.drawable.doll,"Alia"));
        list.add(new StoryModel(R.drawable.img, R.drawable.video,R.drawable.doll,"Maria"));
        list.add(new StoryModel(R.drawable.mehrab, R.drawable.live,R.drawable.doll,"Maria"));

        StoryAdapter adapter=new StoryAdapter(list,getContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
       binding.storyRv.setLayoutManager(layoutManager);
       binding.storyRv.setNestedScrollingEnabled(false);
       binding.storyRv.setAdapter(adapter);

        postlist=new ArrayList<>();


        PostAdapter postAdapter =new PostAdapter(postlist,getContext());
        LinearLayoutManager layoutManager1=new LinearLayoutManager(getContext());
        binding.dashboardRecylr.setLayoutManager(layoutManager1);
       binding.dashboardRecylr.setAdapter(postAdapter);

       database.getReference().child("posts").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                   Post post=dataSnapshot.getValue(Post.class);
                   postlist.add(post);
               }
               postAdapter.notifyDataSetChanged();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
        return binding.getRoot();
    }

}