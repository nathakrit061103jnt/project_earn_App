package com.example.apptravel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        // Inflate the layout for this fragmen

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView txt_u_name_data, txt_u_tel_data,txt_u_address_data,txt_u_email_data;
        Button btnEditProfile_to_page;
        User user = SharedPrefManager.getInstance(getContext()).getUser();

        txt_u_name_data = root.findViewById(R.id.txt_u_name_data);
        txt_u_tel_data = root.findViewById(R.id.txt_u_tel_data);
        txt_u_address_data = root.findViewById(R.id.txt_u_email_data);
        txt_u_email_data = root.findViewById(R.id.txt_u_address_data);
        btnEditProfile_to_page = root.findViewById(R.id.btnEditProfile_to_page);

        txt_u_name_data.setText(String.valueOf(user.getName()));
        txt_u_tel_data.setText(String.valueOf(user.getTel()));
        txt_u_address_data.setText(String.valueOf(user.getAddress()));
        txt_u_email_data.setText(String.valueOf(user.getEmail()));

        btnEditProfile_to_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEditProfile_to_page.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_editProfileFragment);
                    }
                });
            }
        });

        return root;
    }
}