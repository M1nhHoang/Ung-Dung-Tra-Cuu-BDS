package com.minhhoang.nhom8_ltdd_tracuubds.menu.lookup;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.minhhoang.nhom8_ltdd_tracuubds.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link lookup_by_toado_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class lookup_by_toado_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public lookup_by_toado_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment lookup_by_toado_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static lookup_by_toado_Fragment newInstance(String param1, String param2) {
        lookup_by_toado_Fragment fragment = new lookup_by_toado_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_lookup_by_toado_, container, false);

        // Bắt sự kiện khi nút được nhấn
        view.findViewById(R.id.toado_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy giá trị biến bạn muốn truyền
                String coordinates = ((EditText)view.findViewById(R.id.toado_srach)).getText().toString();

                // Chuyển sang Activity mới và truyền giá trị
                Intent intent = new Intent(getActivity(), lookup_map.class);
                intent.putExtra("Coordinates", coordinates);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}