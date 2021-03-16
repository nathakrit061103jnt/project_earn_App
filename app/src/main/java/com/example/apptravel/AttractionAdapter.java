package com.example.apptravel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class AttractionAdapter extends RecyclerView.Adapter<AttractionAdapter.AttractionViewHolder> {

    //ประกาศตัวแปรสําหรับใช้ในคลาสนีB
    private LayoutInflater inflater;
    private ArrayList<Attraction> attractionsArrayList;

    //Constructor method
    public AttractionAdapter(Context ctx, ArrayList<Attraction> attractionsArrayList) {
        inflater = LayoutInflater.from(ctx);
        this.attractionsArrayList = attractionsArrayList;
    }

    private View.OnClickListener onItemClickListener;

    public void setItemClickListener(View.OnClickListener clickListener) {
        onItemClickListener = clickListener;
    }

    class AttractionViewHolder extends RecyclerView.ViewHolder {
        //ประกาศชื)อตัวแปรอ้างอิงถึง TextView ที)อยู่บน Layout
        TextView txt_at_name;
        ImageView imageView;

        public AttractionViewHolder(@NonNull View itemView) {
            super(itemView);
            //อ้างอิงถึงตัวแปรแต่ละตัวไปยัง TextView ที)อยู่บน Layout
            txt_at_name = itemView.findViewById(R.id.txt_h_name);
            imageView = itemView.findViewById(R.id.imageView6);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }
    }

    @NonNull
    @Override
    public AttractionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //กําหนด Layout ที)ใช้สําหรับแสดงข้อมูลแต่ละรายการบน RecycleView ในที)นีBคือ recycleview_item
        View view = inflater.inflate(R.layout.attraction_item,
                parent, false);
        AttractionViewHolder holder = new AttractionViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AttractionViewHolder holder, int position) {
//นาข้อมลูแต่ละฟิ วด์ไปแสดงบน TextView แต่ละตัว
        String txt_at_name = attractionsArrayList.get(position).getAt_name();
          holder.txt_at_name.setText(txt_at_name);
          String t_img1 = attractionsArrayList.get(position).getAt_profile();
          String path = URLs.IMAGE_URL + "a/" + t_img1;
          Picasso.get().load(path).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        //ส่งค่าจํานวนแถวทั=งหมดที@อยู่ใน moviesArrayList
        return attractionsArrayList.size();
    }
}
