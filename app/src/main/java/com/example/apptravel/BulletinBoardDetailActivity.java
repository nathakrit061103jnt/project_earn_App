package com.example.apptravel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class BulletinBoardDetailActivity extends AppCompatActivity {

    BulletinBoard bulletinBoardData;
    private TextView  bb_id,bb_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin_board_detail);
//
        bb_id =  findViewById(R.id.txt_h_name);
        bb_name = findViewById(R.id.txt_bb_name);
////รับข้อมูล moviedata ที8ส่งมาจากหน้า ListMoviesFragment
        bulletinBoardData = (BulletinBoard)getIntent().getSerializableExtra("bulletinBoardData");
////นําข้อมูลไปแสดงบน EditText แต่ละอัน
//
        bb_id.setText(bulletinBoardData.getBb_id());
        bb_name.setText(bulletinBoardData.getBb_name());

    }
}