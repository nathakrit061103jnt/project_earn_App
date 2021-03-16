package com.example.apptravel;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    ClipData.Item BtnloginActivityLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        BtnloginActivityLogout = findViewById(R.loginActivity);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.bulletinBoaedFragment ,R.id.bulletinBoardDetailActivity,
                R.id.attractionFragment,R.id.hotelFragment,R.id.profileFragment,
                R.id.editProfileFragment)
                .setDrawerLayout(drawer)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View headerView = navigationView.getHeaderView(0);
        TextView txt_u_name_h = (TextView) headerView.findViewById(R.id.txt_u_name_h);
        TextView txt_u_email_h = (TextView) headerView.findViewById(R.id.txt_u_email_h);

        User user = SharedPrefManager.getInstance(this).getUser();

        txt_u_name_h.setText(String.valueOf(user.getName()));
        txt_u_email_h.setText(String.valueOf(user.getEmail()));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()) {
            Intent intent = new Intent( getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "ยังไม่เข้าสู่ระบบ", Toast.LENGTH_LONG).show();
        }else{

            NavigationView navigationView = findViewById(R.id.nav_view);

            View headerView = navigationView.getHeaderView(0);
            TextView txt_u_name_h = (TextView) headerView.findViewById(R.id.txt_u_name_h);
            TextView txt_u_email_h = (TextView) headerView.findViewById(R.id.txt_u_email_h);

            User user = SharedPrefManager.getInstance(this).getUser();

            txt_u_name_h.setText(String.valueOf(user.getName()));
            txt_u_email_h.setText(String.valueOf(user.getEmail()));
        }
    }


}