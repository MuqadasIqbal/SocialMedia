package com.example.socialmedia.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmedia.Model.Story;
import com.example.socialmedia.Model.User;
import com.example.socialmedia.Model.UserStories;
import com.example.socialmedia.R;
import com.example.socialmedia.databinding.StoryRvDesignBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.viewHolder> {

    ArrayList<Story> list;
    Context context;

    public StoryAdapter(ArrayList<Story> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.story_rv_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Story story=list.get(position);

       /* UserStories lastStory=story.getStories().get(story.getStories().size()-1);
        Picasso.with(context).load(lastStory.getImage()).into(holder.binding.story);

        holder.binding.statuscircle.setPortionsCount(story.getStories().size());

        FirebaseDatabase.getInstance().getReference()
                .child("Users").child(story.getStoryBy()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user=snapshot.getValue(User.class);
                        Picasso.with(context).load(user.getProfile())
                                .placeholder(R.drawable.avatar)
                                .into(holder.binding.profileImage);
                        holder.binding.name.setText(user.getName());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        StoryRvDesignBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding=StoryRvDesignBinding.bind(itemView);
        }
    }
}
