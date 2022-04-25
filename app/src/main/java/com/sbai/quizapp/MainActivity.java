package com.sbai.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    //Step 1: Declaration
    EditText etLogin, etPassword;
    Button bLogin,bCapture,bMicro;
    TextView tvRegister,tvForgotPassword;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Associer layout avec une classe
        setContentView(R.layout.activity_main);
        //Step 2: Recuperation des ids
        mAuth=FirebaseAuth.getInstance();

        etLogin = (EditText) findViewById(R.id.etMail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        bCapture=(Button)findViewById(R.id.btn_captureImage) ;
        bMicro=(Button)findViewById(R.id.btn_micro);
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvForgotPassword=(TextView)findViewById(R.id.tvForgotPwd);
        //Step 3: Association de listeners
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Step 4: Traitement
                String mail = etLogin.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty((mail))) {
                    etLogin.setError("Please fill in the email field");
                    etLogin.requestFocus();
                    return;
                }


                if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    etLogin.setError("Please enter a valid email");
                    etLogin.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please fill in the required fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(mail, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this,"Bienvenue !",Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(MainActivity.this, Quiz1.class));

                                } else {
                                    Toast.makeText(getApplicationContext(), "Login or password incorrect !", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Step 4: Traitement
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ForgotPassword.class));
            }
        });
        bCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Camera.class));
            }
        });
        bMicro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Micro.class));
            }
        });

    }
}
