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

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    //ประกาศตัวแปรสําหรับใช้ในคลาสนีB
    private LayoutInflater inflater;
    private ArrayList<Hotel> hotelsArrayList;

    //Constructor method
    public HotelAdapter(Context ctx, ArrayList<Hotel> hotelDataArrayList) {
        inflater = LayoutInflater.from(ctx);
        this.hotelsArrayList = hotelDataArrayList;
    }

    private View.OnClickListener onItemClickListener;

    public void setItemClickListener(View.OnClickListener clickListener) {
        onItemClickListener = clickListener;
    }

    class HotelViewHolder extends RecyclerView.ViewHolder {
        TextView txt_h_name;
        ImageView imageView;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_h_name = itemView.findViewById(R.id.txt_h_name);
            imageView = itemView.findViewById(R.id.imageView8_hotel);

            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //กําหนด Layout ที)ใช้สําหรับแสดงข้อมูลแต่ละรายการบน RecycleView ในที)นีBคือ recycleview_item
        View view = inflater.inflate(R.layout.hotel_item,
                parent, false);
        HotelViewHolder holder = new HotelViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
//นาข้อมลูแต่ละฟิ วด์ไปแสดงบน TextView แต่ละตัว
        String txt_h_name = hotelsArrayList.get(position).getH_name();
        holder.txt_h_name.setText(txt_h_name);
        String h_profile_img = hotelsArrayList.get(position).getH_profile();
        String path = URLs.IMAGE_URL + "b/" + h_profile_img;
        Picasso.get().load(path).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return hotelsArrayList.size();
    }
}
