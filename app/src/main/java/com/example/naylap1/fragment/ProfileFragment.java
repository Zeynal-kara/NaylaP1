package com.example.naylap1.fragment;


import android.annotation.SuppressLint;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naylap1.adapter.MessageRvAdapter;
import com.example.naylap1.helper.DialogHelper;
import com.example.naylap1.R;
import com.example.naylap1.adapter.CoursesRvAdapter;
import com.example.naylap1.model.Course;
import com.example.naylap1.model.User;

import java.text.DateFormat;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements CoursesRvAdapter.OnItemClickListener{

    RecyclerView recyclerView;
    TextView tvUserName, tvMail, tvJoinDate, tvCompleteNum, tvInProcessNum, tvNotStartedNum;
    ImageView imgProfile;
    User user;

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


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final NestedScrollView nestedScrollView =
                getActivity().findViewById(R.id.nscoll_view_fragment_profile_parent);
        
        defineElements();

        user = User.getInstance();

        tvUserName.setText(user.getFullName());
        tvMail.setText(user.getMail());
        String date = DateFormat.getDateInstance().format(user.getJoinDate());
        String Joined = getResources().getString(R.string.profile_joined_text) +" "+ date;
        tvJoinDate.setText(Joined);
        imgProfile.setImageDrawable(getResources().getDrawable(user.getImgResID()));

        setCoursesCount();

        CoursesRvAdapter coursesRvAdapter =
                new CoursesRvAdapter(user.getCourseManager().getCourseList(), this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(coursesRvAdapter);

        scrollUp(nestedScrollView);
    }
    
    private void defineElements(){
        
        tvUserName = getActivity().findViewById(R.id.tvUserName);
        tvMail = getActivity().findViewById(R.id.tvUserMail);
        tvJoinDate = getActivity().findViewById(R.id.tvEnjoyDate);
        imgProfile = getActivity().findViewById(R.id.imgProfile);

        tvCompleteNum = getActivity().findViewById(R.id.tv_complete_num);
        tvInProcessNum = getActivity().findViewById(R.id.tv_in_proses_num);
        tvNotStartedNum = getActivity().findViewById(R.id.tv_not_started_num);

        recyclerView =
                getActivity().findViewById(R.id.rv_fragment_profile_recent_courses);
    }
    
    private void setCoursesCount(){
        tvCompleteNum.setText(String.valueOf(user.getCourseManager().getCompletedCourseList().size()));
        tvInProcessNum.setText(String.valueOf(user.getCourseManager().getInProcessCourseList().size()));
        tvNotStartedNum.setText(String.valueOf(user.getCourseManager().getNotStartedCourseList().size()));
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
                        setCoursesCount();
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
                        setCoursesCount();
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

    @Override
    public void onPause() {
        user.getCourseManager().setCourseList(( (CoursesRvAdapter)(recyclerView.getAdapter()) ).getCourseList());
        user.saveUser(getActivity().getApplicationContext());
        super.onPause();
    }
}
