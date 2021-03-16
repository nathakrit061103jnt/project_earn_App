package com.example.apptravel;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

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
 * Use the {@link AttractionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AttractionFragment extends Fragment {

    //ประกาศตัวแปรสําหรับใช้ในคลาสนีB
    private String jsonURL = URLs.URL_GET_ATTRACTION;
    private RecyclerView rvAttractions;
    private AttractionAdapter attractionsAdapter;
    private ArrayList<Attraction> listData;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AttractionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AttractionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AttractionFragment newInstance(String param1, String param2) {
        AttractionFragment fragment = new AttractionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_attraction, container, false);

        rvAttractions = view.findViewById(R.id.rvAttraction);
        rvAttractions.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvAttractions.setLayoutManager(llm);

//        fragment_attraction.setTitle("Your actionbar title");

//เรียกใช้เมธอด loadMovieData ที@สร้างก่อนหน้านี=
        loadAttractionData();

        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//เมื@อผู้ใช้กดปุ่ ม แก้ไขข้อมูลจะย้อนกลับมาหน้านี=ซึ@งจะมีresultCode = -1
        if (requestCode == 1 && resultCode == -1) {
            loadAttractionData();
            attractionsAdapter.notifyDataSetChanged();
        }
    }

    public void loadAttractionData() {
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
                                Attraction itemData = new Attraction(
                                        dataObj.getString("at_id"),
                                        dataObj.getString("at_name"),
                                        dataObj.getString("at_location"),
                                        dataObj.getString("at_detial"),
                                        dataObj.getString("at_p"),
                                        dataObj.getString("at_profile"),
                                        dataObj.getString("at_img1"),
                                        dataObj.getString("at_img2"),
                                        dataObj.getString("at_img3")
                                );
                                listData.add(itemData);
                            } //end for
                            attractionsAdapter = new AttractionAdapter(getActivity(), listData);
                            rvAttractions.setAdapter(attractionsAdapter);

                            attractionsAdapter.setItemClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                                    int position = viewHolder.getAdapterPosition();
                                    Intent data = new Intent(view.getContext(), AttractionDetailActivity.class);
                                    Attraction attraction = listData.get(position);
                                    data.putExtra("attractionData", attraction);
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