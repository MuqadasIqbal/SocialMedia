package com.example.socialmedia.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialmedia.Model.NotificationModel;
import com.example.socialmedia.R;
import com.example.socialmedia.databinding.NotificationsampleBinding;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewHolder>{
ArrayList<NotificationModel>list;
Context context;

    public NotificationAdapter(ArrayList<NotificationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notificationsample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        NotificationModel model=list.get(position);
        holder.binding.profileImage.setImageResource(model.getProfile());
        holder.binding.notification.setText(Html.fromHtml(model.getNotification()));
        holder.binding.time.setText(Html.fromHtml(model.getTime()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        NotificationsampleBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding=NotificationsampleBinding.bind(itemView);
        }
    }
}
