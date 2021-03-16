package com.example.apptravel.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.apptravel.LoginActivity;
import com.example.apptravel.MainActivity;
import com.example.apptravel.R;
import com.example.apptravel.SharedPrefManager;
import com.example.apptravel.User;

import java.util.zip.Inflater;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel = new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        TextView txt_login_status, txt_u_nameShow;
        Button btnLogin, btnLogout;

        txt_u_nameShow = root.findViewById(R.id.txt_u_nameShow);
        txt_login_status = root.findViewById(R.id.txt_login_status);
        btnLogout = root.findViewById(R.id.btnLogout);
        btnLogin = root.findViewById(R.id.btnLogin);

        if (SharedPrefManager.getInstance(getContext()).isLoggedIn()) {

            User user = SharedPrefManager.getInstance(getContext()).getUser();

            txt_u_nameShow.setText(String.valueOf(user.getName()));
            txt_login_status.setText("เข้าสู่ระบบสำเร็จ");


            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnLogout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getContext(), "ออกจากระบบ", Toast.LENGTH_LONG).show();
                            SharedPrefManager.getInstance(getContext()).logout();
                        }
                    });
                }
            });

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnLogout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent  intent = new Intent( getContext(),LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            });

        } else {
            Intent  intent = new Intent( getContext(),LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getContext(), "ยังไม่ Login", Toast.LENGTH_LONG).show();
            txt_login_status.setText("ยังไม่ Login");
        }
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!SharedPrefManager.getInstance(getContext()).isLoggedIn()) {
            Intent  intent = new Intent( getContext(),LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getContext(), "ยังไม่ Login", Toast.LENGTH_LONG).show();
        }
    }

}