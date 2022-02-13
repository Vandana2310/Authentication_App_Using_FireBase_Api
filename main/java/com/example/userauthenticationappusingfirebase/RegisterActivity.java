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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText edtMail1, edtUsername, edtPass1;
    Button btnLogin, btnRegister;
    FirebaseAuth auth;
    ProgressBar progressBar;
    TextView loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtMail1 = findViewById(R.id.edtMail);
        edtPass1 = findViewById(R.id.edtPass);
        edtUsername = findViewById(R.id.edtuse);
        btnLogin = findViewById(R.id.btlogin);
        btnRegister = findViewById(R.id.btnRegisterReister);
        loginBtn = findViewById(R.id.txtLoginBtn);
        progressBar = findViewById(R.id.progressBar);
        auth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String email = edtMail1.getText().toString();
                 String password = edtPass1.getText().toString();
                 if(TextUtils.isEmpty(email)){
                     edtMail1.setError("Email is required");
                     return;
                 }
                if(TextUtils.isEmpty(password)){
                    edtMail1.setError("Password is required");
                    return;
                }
                if(password.length() < 6){
                    edtMail1.setError("Password must have 6 character");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                if(auth.getCurrentUser() !=null){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override

                            public void onComplete(@NonNull Task<AuthResult> task) {
                               if(task.isSuccessful()){
                                   Toast.makeText(RegisterActivity.this, "User is registered", Toast.LENGTH_SHORT).show();
                                   startActivity(new Intent(getApplicationContext(), MainActivity.class));

                               }else{
                                   Toast.makeText(RegisterActivity.this ,"Error -> "+ task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                                   progressBar.setVisibility(View.GONE);
                               }

                            }

                        });
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }
}