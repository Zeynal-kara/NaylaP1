package com.example.naylap1.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naylap1.R;
import com.example.naylap1.adapter.InboxRecyclerViewAdaper;
import com.example.naylap1.adapter.RecentCoursesRecyclerViewAdapter;
import com.example.naylap1.object.RecentCours;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        final RecyclerView recyclerView = getActivity().findViewById(R.id.rv_fragment_profile_recent_courses);

        RecentCoursesRecyclerViewAdapter recentCoursesRecyclerViewAdapter =
                new RecentCoursesRecyclerViewAdapter(loadData(),
                        new RecentCoursesRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecentCours recentCours, int position) {

                ( (RecentCoursesRecyclerViewAdapter)(recyclerView.getAdapter()) ).getCertificate(position);
            }
        });

        recyclerView.setAdapter(recentCoursesRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    private List<RecentCours> loadData(){

        List<RecentCours> recentCoursesList = new ArrayList<>();

        RecentCours recentCours = new RecentCours(RecentCours.ProcessCode.COMPLETED);
        recentCours.setTitle("Figma for beginners");
        recentCours.setIsContinue(false);
        recentCours.setDuration("Duration: 24hr 30 min");
        recentCours.setImgIconResId(R.drawable.recent_cours_icon);

        recentCoursesList.add(recentCours);

        for (int i=0; i<7; i++){

            RecentCours recentCours1 = new RecentCours(RecentCours.ProcessCode.IN_PROCESS);
            recentCours1.setTitle("Figma for beginners"+i);
            recentCours1.setIsContinue(true);
            recentCours1.setDuration("Duration: 24hr 30 min");
            recentCours1.setImgIconResId(R.drawable.recent_cours_icon);

            recentCoursesList.add(recentCours1);

        }

        return recentCoursesList;

    }
}
