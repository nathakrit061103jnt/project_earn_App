package com.example.apptravel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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

import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText et_u_name, et_u_email, et_u_password, et_u_tel, et_u_address;
    Button btnRegister;
    TextView txt_login;


    // Creating Volley RequestQueue.
    RequestQueue requestQueue;
    // Create string variable to hold the EditText Value.
    String et_u_nameData, et_u_emailData,
            et_u_passwordData,
            et_u_telData, et_u_addressData;
    // Creating Progress dialog.
    ProgressDialog progressDialog;
    // Storing server url into String variable.
    String HttpUrl = URLs.URL_REGISTER;


    public void GetValueFromEditText() {
        et_u_emailData = et_u_email.getText().toString().trim();
        et_u_passwordData = et_u_password.getText().toString().trim();

        et_u_nameData = et_u_name.getText().toString().trim();
        et_u_telData = et_u_tel.getText().toString().trim();
        et_u_addressData = et_u_address.getText().toString().trim();

        //validating inputs
        if (TextUtils.isEmpty(et_u_emailData)) {
            et_u_email.setError("Please enter your email");
            et_u_email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(et_u_passwordData)) {
            et_u_password.setError("Please enter your password");
            et_u_password.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(et_u_nameData)) {
            et_u_name.setError("Please enter your name");
            et_u_name.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(et_u_telData)) {
            et_u_tel.setError("Please enter your tel");
            et_u_tel.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(et_u_addressData)) {
            et_u_address.setError("Please enter your address");
            et_u_address.requestFocus();
            return;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Assigning ID's to EditText.
        et_u_email = findViewById(R.id.et_u_email);
        et_u_password = findViewById(R.id.et_u_password);
        et_u_name = findViewById(R.id.et_u_name);
        et_u_tel = findViewById(R.id.et_u_tel);
        et_u_address = findViewById(R.id.et_u_address);
        // Assigning ID's to Button.
        btnRegister = findViewById(R.id.btnRegister);
        txt_login = findViewById(R.id.txt_login);


        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister();
            }
        });


        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void userRegister() {
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

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(ServerResponse);
                            // Showing response message coming from server.

                            int status = obj.getInt("status");

                            if (status == 200) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                                finish();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "ไม่สามารถสมัครสมาชิกผู้ใช้งานได้", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception ex) {
                            StringWriter stringWriter = new StringWriter();
                            ex.printStackTrace(new PrintWriter(stringWriter));
                            Log.e("exception ::: ", stringWriter.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                // Hiding the progress dialog after all task complete.
                progressDialog.dismiss();
                // Showing error message if something goes wrong.
                Toast.makeText(getApplicationContext(), volleyError.toString(),
                        Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();
                // Adding All values to Params.
                params.put("u_email", et_u_emailData);
                params.put("u_password", et_u_passwordData);
                params.put("u_tel", et_u_telData);
                params.put("u_name", et_u_nameData);
                params.put("u_address", et_u_addressData);
                return params;
            }
        };
        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

    }

}