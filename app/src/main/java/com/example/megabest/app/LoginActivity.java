package com.example.megabest.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.megabest.R;
import com.example.megabest.app.features.home.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    Intent registerActivity ;
    Intent homeActivity ;
    TextView registerText ;
    TextInputEditText email ;
    TextInputEditText password ;
    AppCompatButton signInButton ;
    ProgressBar loginProgressBar;
    FirebaseAuth firebaseAuth;
    DatabaseReference firebaseDatabase;

    List<Integer> favouriteMovies =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setRegisterButton();
        setLoginButton();
    }
    private void setRegisterButton(){
        registerText=findViewById(R.id.register);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerActivity= new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerActivity);
            }
        });

    }
    private void setLoginButton(){
        signInButton=findViewById(R.id.signIn);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }
    public void login(){
        email=findViewById(R.id.emailInputText);
        password=findViewById(R.id.passwordInputText);
        loginProgressBar=findViewById(R.id.loginProgressBar);
        firebaseAuth =FirebaseAuth.getInstance();
        if(TextUtils.isEmpty(email.getText().toString())){
            email.setError("Please Enter Email");
            return;
        }
        if(TextUtils.isEmpty(password.getText().toString())){
            password.setError("Please Enter Password");
            return;
        }
        loginProgressBar.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    homeActivity= new Intent(getApplicationContext(), HomeActivity.class);
                    firebaseDatabase = FirebaseDatabase.getInstance().getReference("Users").child(firebaseAuth.getCurrentUser().getUid());
                    firebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                String username ;
                                username=snapshot.child("name").getValue().toString();
                                homeActivity.putExtra("UserName",username);
                                homeActivity.putExtra("User ID",firebaseAuth.getCurrentUser().getUid());
                                startActivity(homeActivity);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                    //homeActivity.putExtra("name",)

                }
                else{
                    Toast.makeText(LoginActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    loginProgressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}