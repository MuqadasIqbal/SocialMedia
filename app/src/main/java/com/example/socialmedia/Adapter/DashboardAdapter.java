package com.example.socialmedia.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmedia.Model.DasboardModel;
import com.example.socialmedia.R;
import com.example.socialmedia.databinding.DashboardRvBinding;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.viewHolder> {
ArrayList<DasboardModel>list;
Context context;

    public DashboardAdapter(ArrayList<DasboardModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_rv,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
      DasboardModel model=list.get(position);
      holder.binding.profileImage.setImageResource(model.getProfile());
      holder.binding.postImg.setImageResource(model.getPostImage());
      holder.binding.userName.setText(model.getName());
      holder.binding.about.setText(model.getAbout());
      holder.binding.like.setText(model.getLike());
      holder.binding.comment.setText(model.getComment());
      holder.binding.share.setText(model.getShare());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        DashboardRvBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding=DashboardRvBinding.bind(itemView);
        }
    }
}
