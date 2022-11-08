package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.metrofireproject.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class UserDashboard extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private TextInputEditText displayDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        displayDate = findViewById(R.id.enterDate);

        //clicking on the date field will open a date picker dialog
        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            showDatePickerDialog();
            }
        });
        }

        //dialog to choose date
        private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        String selectedDate = (month+1) + " / " + dayOfMonth + " / " + year;
        displayDate.setText(selectedDate);
    }
}