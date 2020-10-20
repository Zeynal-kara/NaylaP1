package com.example.naylap1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naylap1.R;
import com.example.naylap1.model.Course;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class CoursesRvAdapter extends RecyclerView.Adapter<CoursesRvAdapter.ViewHolder> {
    private List<Course> mCourseList;

    private OnItemClickListener mListener;
    private int mColorComplete, mColorInProcess, mColorNotStarted;
    private String btnTextBegin, btnTextContinue;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvTitle, tvDuration, tvProcess, tvGetCertificate;
        Button btnContinue;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_recent_cours_list_item_title);
            tvDuration = itemView.findViewById(R.id.tv_recent_cours_list_item_duration);
            tvProcess = itemView.findViewById(R.id.tv_recent_cours_list_item_process);
            tvGetCertificate = itemView.findViewById(R.id.tv_recent_cours_list_item_get_certificate);
            btnContinue = itemView.findViewById(R.id.btn_recent_cours_list_item_continue);
        }

    }

    public CoursesRvAdapter(List<Course> courseList, OnItemClickListener listener) {
        this.mCourseList = courseList;
        this.mListener = listener;
    }

    @NotNull
    @Override
    public CoursesRvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        // Inflate the custom layout
        View v = inflater.inflate(R.layout.list_item_course, parent, false);


        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvTitle.setText(mCourseList.get(position).getTitle());
        holder.tvDuration.setText(mCourseList.get(position).getDuration());

        holder.tvProcess.setText(mCourseList.get(position).
                getProcessText(holder.tvProcess.getContext()));

        switch (mCourseList.get(position).getProcessCode()){
            case COMPLETED:
                holder.tvProcess.setTextColor(mColorComplete);
                holder.tvGetCertificate.setVisibility(View.VISIBLE);
                holder.btnContinue.setVisibility(View.GONE);
            break;

            case IN_PROCESS:
                holder.tvProcess.setTextColor(mColorInProcess);
                holder.tvGetCertificate.setVisibility(View.GONE);
                holder.btnContinue.setVisibility(View.VISIBLE);
                holder.btnContinue.setText(btnTextContinue);
            break;

            case NOT_STARTED:
                holder.tvProcess.setTextColor(mColorNotStarted);
                holder.tvGetCertificate.setVisibility(View.GONE);
                holder.btnContinue.setVisibility(View.VISIBLE);
                holder.btnContinue.setText(btnTextBegin);

            break;
        }


        holder.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mListener.onItemClick(mCourseList.get(position), position);

            }
        });

        holder.tvGetCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(mCourseList.get(position), position);
            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mCourseList.size();
    }

    public Course getItem(int position) {
        return mCourseList.get(position);
    }

    public void removeItem(int position) {
        mCourseList.remove(position);
        notifyDataSetChanged();

    }

    public void getCertificate(int position){
        mCourseList.get(position).setProcessCode(Course.ProcessCode.COMPLETED);
        notifyDataSetChanged();
    }

    public void setInProcess(int position){
        mCourseList.get(position).setProcessCode(Course.ProcessCode.IN_PROCESS);
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(Course course, int position);

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        mColorComplete = context.getResources().getColor(R.color._colorTrasBlue, null);
        mColorInProcess = context.getResources().getColor(R.color._colorTrasGreen, null);
        mColorNotStarted = context.getResources().getColor(R.color._colorTrasYellow, null);

        btnTextBegin = context.getResources().getString(R.string.btn_begin_text);
        btnTextContinue = context.getResources().getString(R.string.btn_continue_text);

    }
}

