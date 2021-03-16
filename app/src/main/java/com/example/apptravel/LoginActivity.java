package com.example.apptravel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText et_u_email, et_u_password;
    Button btnLogin;
    TextView txt_register, txt_u_name_data, txt_u_tel_data, txt_u_email_data, txt_u_address_data,
            txt_u_name_h, txt_u_email_h;
//    SessionManager sessionManager;

    // Creating Volley RequestQueue.
    RequestQueue requestQueue;
    // Create string variable to hold the EditText Value.
    String et_u_emailData, et_u_passwordData;
    // Creating Progress dialog.
    ProgressDialog progressDialog;
    // Storing server url into String variable.
    String HttpUrl = URLs.URL_LOGIN;

    // Creating method to get value from EditText.
    public void GetValueFromEditText() {
        et_u_emailData = et_u_email.getText().toString().trim();
        et_u_passwordData = et_u_password.getText().toString().trim();

        //validating inputs
        if (TextUtils.isEmpty(et_u_emailData)) {
            et_u_email.setError("Please enter your username");
            et_u_email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(et_u_passwordData)) {
            et_u_password.setError("Please enter your password");
            et_u_password.requestFocus();
            return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        SharedPrefManager.getInstance(getApplicationContext()).logout();

//        if (SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()) {
//            finish();
//            startActivity(new Intent(this, MainActivity.class));
//        }

//        sessionManager = new SessionManager(this);

        // Assigning ID's to EditText.
        et_u_email = findViewById(R.id.et_u_email);
        et_u_password = findViewById(R.id.et_u_password);
        // Assigning ID's to Button.
        btnLogin = findViewById(R.id.btnLogin);
        txt_register = findViewById(R.id.txt_login);


        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });



        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    private void userLogin() {

        // Showing progress dialog at user registration time.
        progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
        progressDialog.show();
        // Calling method to get value from EditText.
        GetValueFromEditText();
        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        txt_u_name_data = findViewById(R.id.txt_u_name_data);
                        txt_u_tel_data = findViewById(R.id.txt_u_tel_data);
                        txt_u_email_data = findViewById(R.id.txt_u_email_data);
                        txt_u_address_data = findViewById(R.id.txt_u_address_data);

                        txt_u_name_h = findViewById(R.id.txt_u_name_h);
                        txt_u_email_h = findViewById(R.id.txt_u_email_h);

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(ServerResponse);
                            // Showing response message coming from server.

                            int status = obj.getInt("status");

                            if (status == 200) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                                String u_id = obj.getString("u_id");
                                String u_email, u_tel, u_name, u_address;
                                u_email = obj.getString("u_email");
                                u_tel = obj.getString("u_tel");
                                u_name = obj.getString("u_name");
                                u_address = obj.getString("u_address");

                                //creating a new user object
                                User user = new User(
                                        u_id,
                                        u_email,
                                        u_tel,
                                        u_name,
                                        u_address
                                );

                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                //starting the profile activity
                                finish();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

                            } else {
                                btnLogin.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "อีเมลหรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception ex) {
                            btnLogin.setVisibility(View.VISIBLE);
                            StringWriter stringWriter = new StringWriter();
                            ex.printStackTrace(new PrintWriter(stringWriter));
                            Log.e("exception ::: ", stringWriter.toString());
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();
                        btnLogin.setVisibility(View.VISIBLE);
                        // Showing error message if something goes wrong.
//                                Toast.makeText(getApplicationContext(), volleyError.toString(),
//                                        Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "อีเมลหรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();
                // Adding All values to Params.
                params.put("u_email", et_u_emailData);
                params.put("u_password", et_u_passwordData);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);
    }
}