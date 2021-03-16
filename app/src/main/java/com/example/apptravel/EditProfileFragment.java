package com.example.apptravel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ProgressDialog progressDialog;


    String et_u_nameData_req, et_u_emailData_req, et_u_telData_req,
            et_u_addressData_req, u_idData;

    EditText et_u_name, et_u_email, et_u_tel, et_u_address;
    String HttpUrl = URLs.URL_UPDATE_USER;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditProfileFragment newInstance(String param1, String param2) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public EditProfileFragment() {
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


    public void GetValueFromEditText() {
        et_u_emailData_req = et_u_email.getText().toString().trim();
        et_u_nameData_req = et_u_name.getText().toString().trim();
        et_u_telData_req = et_u_tel.getText().toString().trim();
        et_u_addressData_req = et_u_address.getText().toString().trim();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        Button btnEditProfile;
        User user = SharedPrefManager.getInstance(getContext()).getUser();

        et_u_email = root.findViewById(R.id.et_u_email);
        et_u_name = root.findViewById(R.id.et_u_name);
        et_u_tel = root.findViewById(R.id.et_u_tel);
        et_u_address = root.findViewById(R.id.et_u_address);
        // Assigning ID's to Button.
        btnEditProfile = root.findViewById(R.id.btnEditProfile);

        u_idData = user.getUid();
        et_u_name.setText(String.valueOf(user.getName()));
        et_u_tel.setText(String.valueOf(user.getTel()));
        et_u_address.setText(String.valueOf(user.getAddress()));
        et_u_email.setText(String.valueOf(user.getEmail()));

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetValueFromEditText();

//        Log.d("et_name",et_u_nameData_req);
// Showing progress dialog at user registration time.
//                progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
//                progressDialog.show();
// Calling method to get value from EditText.
                GetValueFromEditText();
// Creating string request with post method.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {
// Hiding the progress dialog after all task complete.
//                                progressDialog.dismiss();
// Showing response message coming from server.
                                Toast.makeText(getActivity(), ServerResponse, Toast.LENGTH_LONG).show();

                                //creating a new user object
                                User user = new User(
                                        u_idData,
                                        et_u_emailData_req,
                                        et_u_telData_req,
                                        et_u_nameData_req,
                                        et_u_addressData_req
                                );

                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getActivity()).userLogin(user);
                                //starting the profile activity

                                Navigation.findNavController(v).navigate(R.id.action_editProfileFragment_to_profileFragment);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
// Hiding the progress dialog after all task complete.
//                        progressDialog.dismiss();
// Showing error message if something goes wrong.
                        Toast.makeText(getActivity(), volleyError.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
// Creating Map String Params.
                        Map<String, String> params = new HashMap<String, String>();
// Adding All values to Params.
                        params.put("u_email", et_u_emailData_req);
                        params.put("u_tel", et_u_telData_req);
                        params.put("u_name", et_u_nameData_req);
                        params.put("u_address", et_u_addressData_req);
                        params.put("u_id", u_idData);
                        return params;
                    }
                };
// Creating RequestQueue.
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
// Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);


            }
        });


        return root;
    }


}