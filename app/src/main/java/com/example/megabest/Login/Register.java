package com.example.megabest.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.megabest.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class Register extends AppCompatActivity {

    Intent loginActivity ;
    AppCompatButton registerButton ;
    FirebaseAuth firebaseAuth;
    TextInputEditText name;
    TextInputEditText number;
    TextInputEditText email;
    TextInputEditText password;
    ProgressBar registrationProgressBar ;
    FirebaseDatabase firebaseDatabaseRootNode;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton=findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }
    public void register(){
        name=findViewById(R.id.firstNameInputText);
        number=findViewById(R.id.lastNameInputText);
        email=findViewById(R.id.emailInputEdit);
        password=findViewById(R.id.passwordInputRegister);

        registrationProgressBar=findViewById(R.id.registrationProgressBar);
        firebaseAuth =FirebaseAuth.getInstance();
        if(TextUtils.isEmpty(name.getText().toString())){
            name.setError("Please Enter Your Name");
            return;
        }
        if(TextUtils.isEmpty(number.getText().toString())){
            number.setError("Please Enter Your Number");
            return;
        }
        if(TextUtils.isEmpty(email.getText().toString())){
            email.setError("Please Enter Your Email");
            return;
        }
        if(TextUtils.isEmpty(password.getText().toString())){
            password.setError("Please Enter Your Password");
            return;
        }
        if(password.length()<6){
            password.setError("Password Must Be > Than 6 letters ");
            return;
        }
        registrationProgressBar.setVisibility(View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user =new User (name.getText().toString(),number.getText().toString(),email.getText().toString(),password.getText().toString());
                    firebaseDatabaseRootNode=FirebaseDatabase.getInstance();
                    databaseReference =firebaseDatabaseRootNode.getReference("Users");
                    databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Register.this, "Account Created Successfully ", Toast.LENGTH_SHORT).show();
                                loginActivity=new Intent(Register.this,Login.class);
                                startActivity(loginActivity);
                            }
                        }
                    });

                }
                else{
                    Toast.makeText(Register.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    registrationProgressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}