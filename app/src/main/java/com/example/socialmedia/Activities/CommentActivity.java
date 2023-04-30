package com.example.socialmedia.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.socialmedia.Adapter.CommentAdapter;
import com.example.socialmedia.Model.Comment;
import com.example.socialmedia.Model.Notification;
import com.example.socialmedia.Model.Post;
import com.example.socialmedia.Model.User;
import com.example.socialmedia.R;
import com.example.socialmedia.databinding.ActivityCommentBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class CommentActivity extends AppCompatActivity {
ActivityCommentBinding binding;
Intent intent;
String postId;
String postedBy;
FirebaseAuth auth;
FirebaseDatabase database;
ArrayList<Comment>list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCommentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        intent=getIntent();

        setSupportActionBar(binding.toolbars);
        CommentActivity.this.setTitle("Comments");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        postId=intent.getStringExtra("postId");
        postedBy=intent.getStringExtra("postedBy");

        database.getReference()
                .child("posts")
                .child(postId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Post post=snapshot.getValue(Post.class);

                Picasso.with(CommentActivity.this)
                        .load(post.getPostImage())
                        .placeholder(R.drawable.avatar)
                        .into(binding.postImage);

                binding.Descripton.setText(post.getPostDescription());
                binding.like.setText(post.getPostLike()+"");
                binding.comment.setText(post.getCommentCount()+"");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       database.getReference()
               .child("Users").child(postedBy).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               User user=snapshot.getValue(User.class);
               Picasso.with(CommentActivity.this)
                       .load(user.getProfile())
                       .placeholder(R.drawable.avatar)
                       .into(binding.profileImage);

               binding.Name.setText(user.getName());
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
        binding.commentPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comment comment=new Comment();
                comment.setCommentBody(binding.commentET.getText().toString());
                comment.setCommentedAt(new Date().getTime());
                comment.setCommentedBy(FirebaseAuth.getInstance().getUid());

                     database.getReference()
                     .child("posts").child(postId)
                     .child("comments").push().setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                         @Override
                         public void onSuccess(Void unused) {
                             database.getReference().child("posts").child(postId)
                                     .child("commentCount").addListenerForSingleValueEvent(new ValueEventListener() {
                                 @Override
                                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                                     int commentCount=0;
                                     if (snapshot.exists()){
                                         commentCount=snapshot.getValue(Integer.class);
                                     }
                                     database.getReference()
                                             .child("posts").child(postId).child("commentCount")
                                             .setValue(commentCount+1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                 @Override
                                                 public void onSuccess(Void unused) {

                                                     binding.commentET.setText("");
                                                     Toast.makeText(CommentActivity.this, "Commented", Toast.LENGTH_SHORT).show();
                                                     Notification notification=new Notification();
                                                     notification.setNotificationBy(FirebaseAuth.getInstance().getUid());
                                                     notification.setNotificationAt(new Date().getTime());
                                                     notification.setPostID(postId);
                                                     notification.setPostedBy(postedBy);
                                                     notification.setType("comment");

                                                     FirebaseDatabase.getInstance().getReference().child("notification")
                                                             .child(postedBy).push().setValue(notification);

                                                 }
                                             });
                                 }

                                 @Override
                                 public void onCancelled(@NonNull DatabaseError error) {

                                 }
                             });
                         }
                     });
            }
        });

        CommentAdapter adapter=new CommentAdapter(this,list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.CommentRv.setLayoutManager(layoutManager);
        binding.CommentRv.setAdapter(adapter);

        database.getReference().child("posts").child(postId)
                .child("comments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Comment comment=dataSnapshot.getValue(Comment.class);
                    list.add(comment);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}