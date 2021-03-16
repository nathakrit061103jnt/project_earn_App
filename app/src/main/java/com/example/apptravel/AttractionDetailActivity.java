package com.example.apptravel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class AttractionDetailActivity extends AppCompatActivity {

    Attraction attractionData;
    private TextView at_location, at_name,at_detial;
    ImageView image_profileData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_detail);

        image_profileData = findViewById(R.id.image_profile);
        at_name = findViewById(R.id.at_name);
        at_location = findViewById(R.id.at_locationff);
        at_detial = findViewById(R.id.at_detial);

////รับข้อมูล moviedata ที8ส่งมาจากหน้า ListMoviesFragment
        attractionData = (Attraction) getIntent().getSerializableExtra("attractionData");
////นําข้อมูลไปแสดงบน EditText แต่ละอัน
//
        at_name.setText(attractionData.getAt_name().trim());
        at_location.setText(attractionData.getAt_location().trim());
        at_detial.setText(attractionData.getAt_detial().trim());

        String image_profileData_str = attractionData.getAt_profile();

        String path = URLs.IMAGE_URL + "a/" + image_profileData_str;
        Picasso.get().load(path).into(image_profileData);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }


}