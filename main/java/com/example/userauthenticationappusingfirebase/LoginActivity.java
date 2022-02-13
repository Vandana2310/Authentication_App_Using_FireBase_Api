package com.example.userauthenticationappusingfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText edtMail, edtPass;
    Button  btnlogin;
    ProgressBar progressBar;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtMail = findViewById(R.id.edtuse);
        edtPass = findViewById(R.id.edtPass);
        btnlogin = findViewById(R.id.btlogin);
        progressBar = findViewById(R.id.progressBar2);
        auth = FirebaseAuth.getInstance();


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtMail.getText().toString();
                String password = edtPass.getText().toString();
                if(TextUtils.isEmpty(email)){
                    edtMail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    edtMail.setError("Password is required");
                    return;
                }
                if(password.length() < 6){
                    edtMail.setError("Password must have 6 character");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()){
                          Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(getApplicationContext(),MainActivity.class));
                      }else{
                          Toast.makeText(LoginActivity.this, "Error -> "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                          progressBar.setVisibility(View.GONE);
                      }
                    }
                });
            }
        });

    }
}