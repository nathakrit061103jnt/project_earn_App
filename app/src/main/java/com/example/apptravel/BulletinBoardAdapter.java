package com.example.apptravel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BulletinBoardAdapter extends RecyclerView.Adapter< BulletinBoardAdapter.BulletinBoardViewHolder> {

    //ประกาศตัวแปรสําหรับใช้ในคลาสนีB
    private LayoutInflater inflater;
    private ArrayList<BulletinBoard> bulletinBoarsArrayList;

    //Constructor method
    public BulletinBoardAdapter(Context ctx, ArrayList<BulletinBoard> movieDataArrayList) {
        inflater = LayoutInflater.from(ctx);
        this.bulletinBoarsArrayList = movieDataArrayList;
    }

    private View.OnClickListener onItemClickListener;
    public void setItemClickListener(View.OnClickListener clickListener) {
        onItemClickListener = clickListener;
    }

    class BulletinBoardViewHolder extends RecyclerView.ViewHolder {
        //ประกาศชื)อตัวแปรอ้างอิงถึง TextView ที)อยู่บน Layout
        TextView  txt_bb_name ;
        ImageView imageView;

        public BulletinBoardViewHolder(@NonNull View itemView) {
            super(itemView);
//อ้างอิงถึงตัวแปรแต่ละตัวไปยัง TextView ที)อยู่บน Layout
            txt_bb_name = itemView.findViewById(R.id.txt_bb_name);
            imageView = itemView.findViewById(R.id.imageView10);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);

        }
    }

    @NonNull
    @Override
    public BulletinBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //กําหนด Layout ที)ใช้สําหรับแสดงข้อมูลแต่ละรายการบน RecycleView ในที)นีBคือ recycleview_item
        View view = inflater.inflate(R.layout.bulletin_board_item,
                parent, false);
        BulletinBoardViewHolder holder = new BulletinBoardViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BulletinBoardViewHolder holder, int position) {
//นาข้อมลูแต่ละฟิ วด์ไปแสดงบน TextView แต่ละตัว

        String txt_bb_name = bulletinBoarsArrayList.get(position).getBb_name();
        holder.txt_bb_name.setText(txt_bb_name);

        String bb_image_s = bulletinBoarsArrayList.get(position).getBb_image();

        String path = URLs.IMAGE_URL + "news/" + bb_image_s;
        Picasso.get().load(path).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        //ส่งค่าจํานวนแถวทั=งหมดที@อยู่ใน moviesArrayList
        return bulletinBoarsArrayList.size();
    }
}
