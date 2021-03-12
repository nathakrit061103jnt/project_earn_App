package com.example.apptravel;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BulletinBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BulletinBoardFragment extends Fragment {

    //ประกาศตัวแปรสําหรับใช้ในคลาสนีB
    private String jsonURL = URLs.URL_GET_Bulletin_board;
    private RecyclerView rvbulletinBoard;
    private BulletinBoardAdapter bulletinBoardAdapter;
    private ArrayList<BulletinBoard> listData;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BulletinBoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BulletinBoardFragment newInstance(String param1, String param2) {
        BulletinBoardFragment fragment = new BulletinBoardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public BulletinBoardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bulletin_boaed, container, false);

        rvbulletinBoard = view.findViewById(R.id.rvBulletinBoard);
        rvbulletinBoard.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvbulletinBoard.setLayoutManager(llm);
//เรียกใช้เมธอด loadMovieData ที@สร้างก่อนหน้านี=
        loadBulletinBoardData();
        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//เมื@อผู้ใช้กดปุ่ ม แก้ไขข้อมูลจะย้อนกลับมาหน้านี=ซึ@งจะมีresultCode = -1
        if (requestCode == 1 && resultCode == -1) {
            loadBulletinBoardData();
            bulletinBoardAdapter.notifyDataSetChanged();
        }
    }

    public void loadBulletinBoardData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("data");
                            listData = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject dataObj = array.getJSONObject(i);
                                BulletinBoard itemData = new BulletinBoard(
                                        dataObj.getString("bb_id"),
                                        dataObj.getString("bb_name"),
                                        dataObj.getString("bb_detail"),
                                        dataObj.getString("bb_date"),
                                        dataObj.getString("bb_image")
                                );
                                listData.add(itemData);
                            } //end for
                            bulletinBoardAdapter = new BulletinBoardAdapter(getActivity(), listData);
                            rvbulletinBoard.setAdapter(bulletinBoardAdapter);

                            bulletinBoardAdapter.setItemClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                                    int position = viewHolder.getAdapterPosition();
                                    Intent data = new Intent(view.getContext(), BulletinBoardDetailActivity.class);
                                    BulletinBoard bulletinBoard = listData.get(position);
                                    data.putExtra("bulletinBoardData", bulletinBoard);
                                    startActivityForResult(data, 1);
                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        } //end try
                    } //end onResponse
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
            } //end onErrorResponse
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    } //end loadMovieData method

}