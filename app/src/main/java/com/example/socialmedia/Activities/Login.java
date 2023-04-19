package com.example.socialmedia.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialmedia.R;
import com.example.socialmedia.databinding.ActivityLoginBinding;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
ActivityLoginBinding binding;
FirebaseAuth auth;
FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        currentUser=auth.getCurrentUser();
        binding.LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=binding.email.getText().toString();
                String password=binding.password.getText().toString();
                if (email.isEmpty()){
                    binding.email.setError("Required");
                }
                if (password.isEmpty()){
                    binding.password.setError("Required");
                }
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent=new Intent(Login.this,MainActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        }else{
                            Toast.makeText(Login.this, "Please Register", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        binding.GoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(Login.this, SignUpActivity.class));
              finishAffinity();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (currentUser!=null){
            Intent intent=new Intent(Login.this,MainActivity.class);
            startActivity(intent);
            finishAffinity();
        }
    }
}