package com.example.socialmedia.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.socialmedia.Model.User;
import com.example.socialmedia.R;
import com.example.socialmedia.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
ActivitySignUpBinding binding;
FirebaseAuth auth;
FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();


        binding.SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=binding.etEmail.getText().toString();
                String pass=binding.etPassword.getText().toString();
                String name=binding.etName.getText().toString();
                String profession=binding.etProfession.getText().toString();
                if (value.isEmpty()){
                    binding.etEmail.setError("Required");
                    return;
                }
                if (pass.isEmpty()){
                    binding.etPassword.setError("Required");
                    return;
                }
                if (name.isEmpty()){
                    binding.etName.setError("Required");
                    return;
                }
                if (profession.isEmpty()){
                    binding.etProfession.setError("Required");
                    return;
                }

                 auth.createUserWithEmailAndPassword(value,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isComplete()){
                                    User user=new User(name,profession, value,pass);
                                    String id=task.getResult().getUser().getUid();

                                    database.getReference().child("Users").child(id).setValue(user);
                                    Toast.makeText(SignUpActivity.this, "Data Save", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(SignUpActivity.this,Login.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(SignUpActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        binding.Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUpActivity.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}