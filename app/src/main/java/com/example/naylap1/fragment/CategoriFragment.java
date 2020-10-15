package com.example.naylap1.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.naylap1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriFragment extends Fragment {


    public CategoriFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_categori, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();


    }

}
