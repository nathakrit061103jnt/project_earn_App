package com.example.apptravel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BulletinBoardDetailActivity extends AppCompatActivity {

    BulletinBoard bulletinBoardData;
    private TextView  bb_date,bb_name,bb_detail;
    ImageView bb_imageData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin_board_detail);
//
        bb_date =  findViewById(R.id.txt_bb_date);
        bb_name = findViewById(R.id.txt_bb_name);
        bb_detail= findViewById(R.id.txt_bb_detail);
        bb_imageData = findViewById(R.id.image_bb_image);
////รับข้อมูล moviedata ที8ส่งมาจากหน้า ListMoviesFragment
        bulletinBoardData = (BulletinBoard)getIntent().getSerializableExtra("bulletinBoardData");
////นําข้อมูลไปแสดงบน EditText แต่ละอัน
//
        bb_date.setText(bulletinBoardData.getBb_date().trim());
        bb_name.setText(bulletinBoardData.getBb_name().trim());
        bb_detail.setText(bulletinBoardData.getBb_name().trim());

        String image_profileData_str = bulletinBoardData.getBb_image().trim();

        String path = URLs.IMAGE_URL + "news/" + image_profileData_str;
        Picasso.get().load(path).into(bb_imageData);

    }
}