package com.example.apptravel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText et_u_name, et_u_email, et_u_password, et_u_tel, et_u_address;
    Button btnRegister;

    // Creating Volley RequestQueue.
    RequestQueue requestQueue;
    // Create string variable to hold the EditText Value.
    String et_u_nameData, et_u_emailData, et_u_passwordData, et_u_telData, et_u_addressData;
    // Creating Progress dialog.
    ProgressDialog progressDialog;
    // Storing server url into String variable.
//    String HttpUrl = "http://192.168.1.33:81/project_e/android_api/register.php";
    String HttpUrl = URLs.URL_REGISTER;

    // Creating method to get value from EditText.
    public void GetValueFromEditText() {
        et_u_nameData = et_u_name.getText().toString().trim();
        et_u_emailData = et_u_email.getText().toString().trim();
        et_u_passwordData = et_u_password.getText().toString().trim();
        et_u_telData = et_u_tel.getText().toString().trim();
        et_u_addressData = et_u_address.getText().toString().trim();
    }

    public void ResetForm() {
        et_u_nameData = "";
        et_u_emailData = "";
        et_u_passwordData = "";
        et_u_telData = "";
        et_u_addressData = "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView txt_login;
        // Assigning ID's to EditText.
        et_u_name = findViewById(R.id.et_u_name);
        et_u_email = findViewById(R.id.et_u_email);
        et_u_password = findViewById(R.id.et_u_password);
        et_u_tel = findViewById(R.id.et_u_tel);
        et_u_address = findViewById(R.id.et_u_address);
        // Assigning ID's to Button.
        btnRegister = findViewById(R.id.btnRegister);
        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);

        txt_login = findViewById(R.id.txt_login);

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Register();
            }
        });

    }

    public void Register() {

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
                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();
                        // Showing response message coming from server.
                        Toast.makeText(getApplicationContext(), ServerResponse, Toast.LENGTH_LONG)
                                .show();
                        ResetForm();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
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
                params.put("u_name", et_u_nameData);
                params.put("u_password", et_u_passwordData);
                params.put("u_email", et_u_emailData);
                params.put("u_tel", et_u_telData);
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