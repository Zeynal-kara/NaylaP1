package com.example.naylap1.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.naylap1.Helper.DialogHelper;
import com.example.naylap1.R;
import com.example.naylap1.adapter.RecentCoursesRvAdapter;
import com.example.naylap1.Model.RecentCourse;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements RecentCoursesRvAdapter.OnItemClickListener{

    RecyclerView recyclerView;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       recyclerView =
                getActivity().findViewById(R.id.rv_fragment_profile_recent_courses);

        final NestedScrollView nestedScrollView =
                getActivity().findViewById(R.id.nscoll_view_fragment_profile_parent);



        RecentCoursesRvAdapter recentCoursesRvAdapter =
                new RecentCoursesRvAdapter(loadData(), this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(recentCoursesRvAdapter);

        scrollUp(nestedScrollView);
    }

    @Override
    public void onItemClick(RecentCourse recentCourse, int position) {

       completeCourseDialog(position);
    }

    /*@Description: This method allows the recent courses to be completed.*/
    private void completeCourseDialog(final int position){

        AlertDialog.Builder builder = DialogHelper.alertBuilder(getContext());
        builder.setTitle(getString(R.string.dialog_title_text));
        builder.setMessage(getString(R.string.dialog_message_text));
        builder.setNegativeButton(getString(R.string.dialog_no_text), null);

        builder.setPositiveButton(getString(R.string.dialog_yes_text),
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ( (RecentCoursesRvAdapter)(recyclerView.getAdapter()) )
                        .getCertificate(position);
            }
        });
        builder.show();
    }

    /*
    * When this fragment started, recyclerview is going to top.
    * Recyclerview include user recent courses list.
    * @Description: This method scrolls nestedscrollview to top.
    */
    private void scrollUp(final NestedScrollView nestedScrollView){

        new CountDownTimer(100, 200) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                nestedScrollView.scrollTo(0,0);
            }
        }.start();
    }


    //Load mock item for testing ...
    private List<RecentCourse> loadData(){

        List<RecentCourse> recentCoursesList = new ArrayList<>();

        RecentCourse recentCourse = new RecentCourse(RecentCourse.ProcessCode.COMPLETED);
        recentCourse.setTitle("Figma for beginners");
        recentCourse.setIsContinue(false);
        recentCourse.setDuration("Duration: 24hr 30 min");
        recentCourse.setImgIconResId(R.drawable.recent_cours_icon);

        recentCoursesList.add(recentCourse);

        for (int i=0; i<7; i++){

            RecentCourse recentCourse1 = new RecentCourse(RecentCourse.ProcessCode.IN_PROCESS);
            recentCourse1.setTitle("Figma for beginners"+i);
            recentCourse1.setIsContinue(true);
            recentCourse1.setDuration("Duration: 24hr 30 min");
            recentCourse1.setImgIconResId(R.drawable.recent_cours_icon);

            recentCoursesList.add(recentCourse1);

        }

        RecentCourse recentCourse2 = new RecentCourse(RecentCourse.ProcessCode.NOT_STARTED);
        recentCourse2.setTitle("Figma for beginners");
        recentCourse2.setIsContinue(true);
        recentCourse2.setDuration("Duration: 24hr 30 min");
        recentCourse2.setImgIconResId(R.drawable.recent_cours_icon);
        recentCoursesList.add(recentCourse2);

        return recentCoursesList;

    }


}
