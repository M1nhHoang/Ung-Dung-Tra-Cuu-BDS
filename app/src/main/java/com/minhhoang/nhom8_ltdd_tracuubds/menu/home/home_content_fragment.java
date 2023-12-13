package com.minhhoang.nhom8_ltdd_tracuubds.menu.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minhhoang.nhom8_ltdd_tracuubds.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home_content_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_content_fragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public home_content_fragment() {

    }

    public static home_content_fragment newInstance(String param1, String param2) {
        home_content_fragment fragment = new home_content_fragment();
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
        return inflater.inflate(R.layout.about_us, container, false);
    }
}