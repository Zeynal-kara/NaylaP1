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
import com.example.naylap1.model.RecentCourse;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class RecentCoursesRvAdapter extends RecyclerView.Adapter<RecentCoursesRvAdapter.ViewHolder> {
    private List<RecentCourse> mRecentCourses;

    private OnItemClickListener mListener;
    private int mColorComplete, mColorInProcess, mColorNotStarted;

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

    public RecentCoursesRvAdapter(List<RecentCourse> recentCourses, OnItemClickListener listener) {
        this.mRecentCourses = recentCourses;
        this.mListener = listener;
    }

    @NotNull
    @Override
    public RecentCoursesRvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        // Inflate the custom layout
        View v = inflater.inflate(R.layout.list_item_recent_cours, parent, false);


        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvTitle.setText(mRecentCourses.get(position).getTitle());
        holder.tvDuration.setText(mRecentCourses.get(position).getDuration());

        holder.tvProcess.setText(mRecentCourses.get(position).
                getProcessText(holder.tvProcess.getContext()));

        switch (mRecentCourses.get(position).getProcessCode()){
            case COMPLETED: holder.tvProcess.setTextColor(mColorComplete); break;
            case IN_PROCESS: holder.tvProcess.setTextColor(mColorInProcess); break;
            case NOT_STARTED: holder.tvProcess.setTextColor(mColorNotStarted); break;
        }

        if (!mRecentCourses.get(position).getIsContinue()){
            holder.tvGetCertificate.setVisibility(View.VISIBLE);
            holder.btnContinue.setVisibility(View.GONE);
        }
        else{
            holder.tvGetCertificate.setVisibility(View.GONE);
            holder.btnContinue.setVisibility(View.VISIBLE);
        }

        holder.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mListener.onItemClick(mRecentCourses.get(position), position);

            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mRecentCourses.size();
    }

    public RecentCourse getItem(int position) {
        return mRecentCourses.get(position);
    }

    public void removeItem(int position) {
        mRecentCourses.remove(position);
        notifyDataSetChanged();

    }

    public void getCertificate(int position){
        mRecentCourses.get(position).setIsContinue(false);
        mRecentCourses.get(position).setProcessCode(RecentCourse.ProcessCode.COMPLETED);
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(RecentCourse recentCourse, int position);

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        mColorComplete = context.getResources().getColor(R.color._colorTrasBlue, null);
        mColorInProcess = context.getResources().getColor(R.color._colorTrasGreen, null);
        mColorNotStarted = context.getResources().getColor(R.color._colorTrasYellow, null);

    }
}

