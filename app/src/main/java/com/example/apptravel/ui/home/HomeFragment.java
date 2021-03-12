package com.example.apptravel.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.apptravel.BulletinBoard;
import com.example.apptravel.R;
import com.example.apptravel.User;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =  new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        CardView btnCardAction = root.findViewById(R.id.btnCardAction);
        CardView btnCardHotel = root.findViewById(R.id.btnCardHotel);
        CardView btnCardBulletinBoard = root.findViewById(R.id.btnCardBulletinBoard);

        btnCardAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCardAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(v).navigate(R.id.action_nav_home_to_attractionFragment);
                    }
                });
            }
        });

        btnCardHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCardHotel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(v).navigate(R.id.action_nav_home_to_hotelFragment);
                    }
                });
            }
        });

        btnCardBulletinBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCardBulletinBoard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(v).navigate(R.id.action_nav_home_to_bulletinBoaedFragment);
                    }
                });
            }
        });

        return root;
    }
}