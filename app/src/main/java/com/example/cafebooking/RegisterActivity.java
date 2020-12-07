package com.example.cafebooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
     EditText etName, etMail,etNumber, etPassword;
     Button btnRegister;
     TextView tvLogin;
     FirebaseAuth frAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etMail = findViewById(R.id.etMail);
        etNumber = findViewById(R.id.etNumber);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);

        frAuth = FirebaseAuth.getInstance();

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, number, mail, password;
                name = etName.getText().toString().trim();
                number = etNumber.getText().toString().trim();
                mail = etMail.getText().toString().trim();
                password = etPassword.getText().toString().trim();

                if(name.isEmpty() || number.isEmpty() || mail.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                 frAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful())
                         {
                             Toast.makeText(RegisterActivity.this, "Registration Successful!",Toast.LENGTH_SHORT).show();
                             startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                         }
                         else
                         {
                             Toast.makeText(RegisterActivity.this, "Registration UnSuccessful!", Toast.LENGTH_SHORT).show();
                         }
                     }
                 });
                }
            }
        });


    }
}
