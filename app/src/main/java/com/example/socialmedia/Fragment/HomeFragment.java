package com.example.socialmedia.Fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialmedia.Adapter.PostAdapter;
import com.example.socialmedia.Adapter.StoryAdapter;
import com.example.socialmedia.Model.Post;
import com.example.socialmedia.Model.Story;
import com.example.socialmedia.Model.UserStories;
import com.example.socialmedia.R;
import com.example.socialmedia.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Date;


public class HomeFragment extends Fragment {
FragmentHomeBinding binding;
ArrayList<Story>storylist;
ArrayList<Post>postlist;
FirebaseAuth auth;
FirebaseDatabase database;
ActivityResultLauncher<String>galleryLauncher;
FirebaseStorage storage;
ProgressDialog dialog;
    public HomeFragment() {

    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        binding.dashboardRecylr.showShimmerAdapter();
        dialog=new ProgressDialog(getContext());
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        storage=FirebaseStorage.getInstance();
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Story Uploading");
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);


        storylist=new ArrayList<>();
        StoryAdapter adapter=new StoryAdapter(storylist,getContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
       binding.storyRv.setLayoutManager(layoutManager);
       binding.storyRv.setNestedScrollingEnabled(false);
       binding.storyRv.setAdapter(adapter);

       database.getReference().child("stories").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
              if (snapshot.exists()){
                  storylist.clear();
                  for (DataSnapshot storySnapshot:snapshot.getChildren()){
                      Story story=new Story();
                      story.setStoryBy(storySnapshot.getKey());
                      story.setStoryAt(storySnapshot.child("postedBy").getValue(Long.class));

                      ArrayList<UserStories>stories=new ArrayList<>();
                      for (DataSnapshot snapshot1:storySnapshot.child("userStories").getChildren()){
                          UserStories userStories=snapshot1.getValue(UserStories.class);
                          stories.add(userStories);
                      }
                      story.setStories(stories);
                      storylist.add(story);
                  }
                  adapter.notifyDataSetChanged();
              }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
        postlist=new ArrayList<>();


        PostAdapter postAdapter =new PostAdapter(postlist,getContext());
        LinearLayoutManager layoutManager1=new LinearLayoutManager(getContext());
        binding.dashboardRecylr.setLayoutManager(layoutManager1);


       database.getReference().child("posts").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               postlist.clear();
               for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                   Post post=dataSnapshot.getValue(Post.class);
                   post.setPostId(dataSnapshot.getKey());
                   postlist.add(post);
               }
               binding.dashboardRecylr.setAdapter(postAdapter);
               binding.dashboardRecylr.hideShimmerAdapter();
               postAdapter.notifyDataSetChanged();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
       binding.addStoryImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               galleryLauncher.launch("image/*");

           }
       });
       galleryLauncher=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
           @Override
           public void onActivityResult(Uri result) {

               binding.addStoryImage.setImageURI(result);
               dialog.show();
               final StorageReference reference=storage.getReference()
                       .child("stories").child(FirebaseAuth.getInstance().getUid())
                       .child(new Date().getTime()+"");
               reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                   @Override
                   public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Story story=new Story();
                            story.setStoryAt(new Date().getTime());
                         database.getReference().child("stories")
                                 .child(FirebaseAuth.getInstance().getUid())
                                 .child("postedBy").setValue(story.getStoryAt()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                     @Override
                                     public void onSuccess(Void unused) {
                                         UserStories stories=new UserStories(uri.toString(),story.getStoryAt());

                                         database.getReference()
                                                 .child("stories").child(FirebaseAuth.getInstance()
                                                         .getUid()).child("userStories").push().setValue(stories).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                     @Override
                                                     public void onSuccess(Void unused) {
                                                         dialog.dismiss();
                                                     }
                                                 });
                                     }
                                 });
                        }
                    });
                   }
               });
           }
       });
        return binding.getRoot();
    }

}