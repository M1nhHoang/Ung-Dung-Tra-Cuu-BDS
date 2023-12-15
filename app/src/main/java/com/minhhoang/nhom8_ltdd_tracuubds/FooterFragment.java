package com.minhhoang.nhom8_ltdd_tracuubds;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.minhhoang.nhom8_ltdd_tracuubds.menu.home.Home_MainActivity;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.home.home_main_activity;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.lookup.lookup_main_activity;
import com.minhhoang.nhom8_ltdd_tracuubds.menu.profile.profile_main_activity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FooterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FooterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageButton home, lookup, profile;

    public FooterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FooterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FooterFragment newInstance(String param1, String param2) {
        FooterFragment fragment = new FooterFragment();
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
        View view = inflater.inflate(R.layout.fragment_footer, container, false);

        // create footer feature
        home = view.findViewById(R.id.footer_home);
        lookup = view.findViewById(R.id.footer_lookup);
        profile = view.findViewById(R.id.footer_profile);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển đổi sang Activity mới
                Intent intent = new Intent(getActivity(), Home_MainActivity.class);
                startActivity(intent);
            }
        });

        lookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển đổi sang Activity mới
                Intent intent = new Intent(getActivity(), lookup_main_activity.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển đổi sang Activity mới
                Intent intent = new Intent(getActivity(), profile_main_activity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}