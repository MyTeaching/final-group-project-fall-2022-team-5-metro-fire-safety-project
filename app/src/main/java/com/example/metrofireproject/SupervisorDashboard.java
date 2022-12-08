package com.example.metrofireproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SupervisorDashboard extends AppCompatActivity {
    private RecyclerView recyclerView;

    public static final String TAG = "SupervisorDashboard";
    private ImageView img;
    private static final int INFO_REQUEST = 1;
    private InspectionItemAdapter inspectionAdapter;
    private int gridColumnCount;// column number in integers.xml files to change based on orientation
    private ArrayList<InspectionItem> listInspections;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_dashboard);

        gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));//setting gridLayout

        listInspections = new ArrayList<>();//initalizing list
        inspectionAdapter = new InspectionItemAdapter(this, listInspections);
        recyclerView.setAdapter(inspectionAdapter);

        loadInspections();//method will load string arrays containing items
    }
    private void loadInspections() { //obtaining string arrays from strings.xml
        listInspections.clear();
        String[] recordNums = getResources().getStringArray(R.array.recordNumbers);
        String[] sites = getResources().getStringArray(R.array.sites);
        TypedArray siteImages = getResources().obtainTypedArray(R.array.site_images);
        String[] inspectionTimes = getResources().getStringArray(R.array.times);
        String[] inspectionDates = getResources().getStringArray(R.array.dates);
        String[] inspectors = getResources().getStringArray(R.array.inspectors);
        for (int i = 0; i < recordNums.length; i++) {
            listInspections.add(new InspectionItem(recordNums[i], sites[i], siteImages.getResourceId(i, 0), inspectionTimes[i], inspectionDates[i], inspectors[i]));
        }
        siteImages.recycle();
        inspectionAdapter.notifyDataSetChanged();
    }
}