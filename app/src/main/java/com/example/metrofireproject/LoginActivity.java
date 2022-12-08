package com.example.metrofireproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    String email, password;

    TextInputLayout email_input, password_input;
    Button login_btn;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        email_input = findViewById(R.id.sign_in_email_layout);
        password_input = findViewById(R.id.sign_in_password_layout);
        login_btn = findViewById(R.id.login_btn);
        auth = FirebaseAuth.getInstance();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = email_input.getEditText().getText().toString();
                password = password_input.getEditText().getText().toString();

                loginUser(email, password);
                /* ADD CONDITIONS FOR THE LAUNCH OF APPROPRIATE SCREENS -- guard_screen or supervisor_screen*/
                //startActivity(new Intent(LoginActivity.this,));
            }
        });
    }


    public void loginUser(String email, String password){

         auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(authResult -> {
             Toast.makeText(LoginActivity.this,"Login Successfully !", Toast.LENGTH_LONG).show();
             startActivity(new Intent(LoginActivity.this, FireguardDashboard.class));
             finish();
         });

    }


    public void forgot_password_click(View view) {
    }

    public void go_sign_up(View view) {
        Intent intent_sign_up = new Intent(LoginActivity.this, SignUp.class);
        startActivity(intent_sign_up);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity( new Intent(getApplicationContext(), LoginActivity.class));
        }
    }
}