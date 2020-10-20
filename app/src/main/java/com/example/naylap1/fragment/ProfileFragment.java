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
import android.widget.Toast;

import com.example.naylap1.helper.DialogHelper;
import com.example.naylap1.R;
import com.example.naylap1.adapter.CoursesRvAdapter;
import com.example.naylap1.model.Course;
import com.example.naylap1.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements CoursesRvAdapter.OnItemClickListener{

    RecyclerView recyclerView;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
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


        User user = User.getInstance();

        CoursesRvAdapter coursesRvAdapter =
                new CoursesRvAdapter(user.getCourseManager().getCourseList(), this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(coursesRvAdapter);

        scrollUp(nestedScrollView);
    }

    @Override
    public void onItemClick(Course course, int position) {

        switch (course.getProcessCode()){
            case COMPLETED:  getCertificateCourseDialog(position); break;
            case IN_PROCESS: completeCourseDialog(position);  break;
            case NOT_STARTED: beginCourseDialog(position); break;
        }

    }

    /*@Description: This method allows the recent courses to be completed.*/
    private void getCertificateCourseDialog(final int position){

        AlertDialog.Builder builder = DialogHelper.alertBuilder(getContext());
        builder.setTitle(getString(R.string.get_certificate_dialog_title_text));
        builder.setMessage(getString(R.string.get_certificate_dialog_message_text));
        builder.setNegativeButton(getString(R.string.dialog_no_text), null);

        builder.setPositiveButton(getString(R.string.dialog_yes_text),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "Success!", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();
    }

    /*@Description: This method allows the recent courses to be completed.*/
    private void beginCourseDialog(final int position){

        AlertDialog.Builder builder = DialogHelper.alertBuilder(getContext());
        builder.setTitle(getString(R.string.in_process_dialog_title_text));
        builder.setMessage(getString(R.string.in_process_dialog_message_text));
        builder.setNegativeButton(getString(R.string.dialog_no_text), null);

        builder.setPositiveButton(getString(R.string.dialog_yes_text),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ( (CoursesRvAdapter)(recyclerView.getAdapter()) )
                                .setInProcess(position);
                    }
                });
        builder.show();
    }

    /*@Description: This method allows the recent courses to be completed.*/
    private void completeCourseDialog(final int position){

        AlertDialog.Builder builder = DialogHelper.alertBuilder(getContext());
        builder.setTitle(getString(R.string.complete_dialog_title_text));
        builder.setMessage(getString(R.string.complete_dialog_message_text));
        builder.setNegativeButton(getString(R.string.dialog_no_text), null);

        builder.setPositiveButton(getString(R.string.dialog_yes_text),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ( (CoursesRvAdapter)(recyclerView.getAdapter()) )
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


}
