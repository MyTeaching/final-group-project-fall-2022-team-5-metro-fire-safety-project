package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    TextInputEditText editEmail;
    EditText editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        //creating edittexts and button
        editEmail = (TextInputEditText)findViewById(R.id.enterLicenseNumber);
        editPassword = (TextInputEditText)findViewById(R.id.enterPassword);
        startButton =  (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dashboard  = new Intent(getApplicationContext(), UserDashboard.class);
                startActivity(dashboard);
            }
        });{

        }

    }
}