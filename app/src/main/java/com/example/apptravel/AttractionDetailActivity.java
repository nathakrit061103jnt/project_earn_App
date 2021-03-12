package com.example.apptravel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AttractionDetailActivity extends AppCompatActivity {

    Attraction attractionData;
    private TextView at_location,at_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_detail);

        //
        at_name =  findViewById(R.id.at_name);
        at_location = findViewById(R.id.at_location);
////รับข้อมูล moviedata ที8ส่งมาจากหน้า ListMoviesFragment
        attractionData = (Attraction) getIntent().getSerializableExtra("attractionData");
////นําข้อมูลไปแสดงบน EditText แต่ละอัน
//
        at_name.setText(attractionData.getAt_name());
        at_location.setText(attractionData.getAt_location());

    }
}