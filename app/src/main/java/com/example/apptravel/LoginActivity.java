package com.example.apptravel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    EditText et_u_email, et_u_password;
    Button btnLogin;
    TextView txt_register, txt_u_name_data, txt_u_tel_data, txt_u_email_data, txt_u_address_data,
            txt_u_name_h, txt_u_email_h;

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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Assigning ID's to EditText.
        et_u_email = findViewById(R.id.et_u_email);
        et_u_password = findViewById(R.id.et_u_password);
        // Assigning ID's to Button.
        btnLogin = findViewById(R.id.btnLogin);
        txt_register = findViewById(R.id.txt_login);

        txt_u_name_data = findViewById(R.id.txt_u_name_data);
        txt_u_tel_data = findViewById(R.id.txt_u_tel_data);
        txt_u_email_data = findViewById(R.id.txt_u_email_data);
        txt_u_address_data = findViewById(R.id.txt_u_address_data);

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

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(ServerResponse);
                            // Showing response message coming from server.

                            int status = obj.getInt("status");

                            if (status == 200) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                                int u_id = obj.getInt("u_id");
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

                                txt_u_name_data.setText(u_name);
                                txt_u_email_data.setText(u_email);
                                txt_u_address_data.setText(u_address);
                                txt_u_tel_data.setText(u_tel);

                                txt_u_name_h.setText(u_name);
                                txt_u_email_h.setText(u_email);


                                //displaying the JSONObject as a String
//                                            Log.d("JSONObject = ", obj.toString());
//                                            //getting specific key values
//                                            Log.d("status = ", String.valueOf(obj.getInt("status")));
//                                            Log.d("error = ", String.valueOf(obj.getBoolean("error")));

                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); finish();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "อีเมลหรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception ex) {
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
        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);
    }
}