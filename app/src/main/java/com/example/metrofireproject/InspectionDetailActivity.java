package com.example.metrofireproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class InspectionDetailActivity extends AppCompatActivity {
    private TextView recordNum, inspectionDate, inspectionTime, inspector, site;
    private ImageView siteimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_detail);

        siteimage = (ImageView)findViewById(R.id.site_image);
        site = (TextView) findViewById(R.id.site_name);
        recordNum = (TextView) findViewById(R.id.recordNum);
        inspectionDate = (TextView) findViewById(R.id.inspection_date);
        inspectionTime = (TextView) findViewById(R.id.inspection_time);
        inspector = (TextView) findViewById(R.id.inspector);
    }
}