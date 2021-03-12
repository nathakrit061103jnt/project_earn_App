package com.example.apptravel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AttractionAdapter extends RecyclerView.Adapter<AttractionAdapter.AttractionViewHolder> {

    //ประกาศตัวแปรสําหรับใช้ในคลาสนีB
    private LayoutInflater inflater;
    private ArrayList<Attraction> attractionsArrayList;

    //Constructor method
    public AttractionAdapter(Context ctx, ArrayList<Attraction> attractionsArrayList){
        inflater = LayoutInflater.from(ctx);
        this.attractionsArrayList = attractionsArrayList;
    }

    private View.OnClickListener onItemClickListener;
    public void setItemClickListener(View.OnClickListener clickListener) {
        onItemClickListener = clickListener;
    }

    class AttractionViewHolder extends RecyclerView.ViewHolder {
//ประกาศชื)อตัวแปรอ้างอิงถึง TextView ที)อยู่บน Layout
        TextView txt_at_name ;
        public AttractionViewHolder(@NonNull View itemView) {
            super(itemView);
            //อ้างอิงถึงตัวแปรแต่ละตัวไปยัง TextView ที)อยู่บน Layout
            txt_at_name = itemView.findViewById(R.id.txt_h_name);

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
    }



    @Override
    public int getItemCount() {
        //ส่งค่าจํานวนแถวทั=งหมดที@อยู่ใน moviesArrayList
        return attractionsArrayList.size();
    }
}
