package com.example.apptravel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class HotelDetailActivity extends AppCompatActivity {

    Hotel hotelData;
    private TextView h_name, h_tel, h_detail, h_address, h_price;
    ImageView image_profileData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);

        h_name = findViewById(R.id.h_name);
        h_address = findViewById(R.id.h_address);
        h_tel = findViewById(R.id.h_tel);
        h_detail = findViewById(R.id.h_detail);
        image_profileData = findViewById(R.id.image_h_profile);
        h_price = findViewById(R.id.h_price);
////รับข้อมูล moviedata ที8ส่งมาจากหน้า ListMoviesFragment
        hotelData = (Hotel) getIntent().getSerializableExtra("hotelData");

        String image_profileData_str = hotelData.getH_profile();

        String path = URLs.IMAGE_URL + "b/" + image_profileData_str;
        Picasso.get().load(path).into(image_profileData);

////นําข้อมูลไปแสดงบน EditText แต่ละอัน
//
        h_name.setText(hotelData.getH_name().trim());
        h_tel.setText(hotelData.getH_tel().trim());
        h_detail.setText(hotelData.getH_detail().trim());
        h_address.setText(hotelData.getH_address().trim());
        h_price.setText(hotelData.getH_price().trim());
    }
}