package com.example.naylap1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naylap1.R;
import com.example.naylap1.object.RecentCours;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class RecentCoursesRecyclerViewAdapter extends RecyclerView.Adapter<RecentCoursesRecyclerViewAdapter.ViewHolder> {
    private List<RecentCours> recentCours;

    private OnItemClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_title, tv_duration, tv_process, tv_get_certificate;
        Button btnContinue;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_recent_cours_list_item_title);
            tv_duration = itemView.findViewById(R.id.tv_recent_cours_list_item_duration);
            tv_process = itemView.findViewById(R.id.tv_recent_cours_list_item_process);
            tv_get_certificate = itemView.findViewById(R.id.tv_recent_cours_list_item_get_certificate);
            btnContinue = itemView.findViewById(R.id.btn_recent_cours_list_item_continue);
        }

    }

    public RecentCoursesRecyclerViewAdapter(List<RecentCours> recentCours, OnItemClickListener listener) {
        this.recentCours = recentCours;
        this.listener = listener;
    }

    @NotNull
    @Override
    public RecentCoursesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        // Inflate the custom layout
        View v = inflater.inflate(R.layout.list_item_recent_cours, parent, false);


        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tv_title.setText(recentCours.get(position).getTitle());
        holder.tv_duration.setText(recentCours.get(position).getDuration());
        holder.tv_process.setText(recentCours.get(position).getProcessText());

        switch (recentCours.get(position).getProcessCode()){
            case COMPLETED: holder.tv_process.setTextColor(Color.parseColor("#3485E8")); break;
            case IN_PROCESS: holder.tv_process.setTextColor(Color.parseColor("#30E3CA")); break;
            case NOT_STARTED: holder.tv_process.setTextColor(Color.parseColor("#F9CE00")); break;
        }

        if (!recentCours.get(position).getIsContinue()){
            holder.tv_get_certificate.setVisibility(View.VISIBLE);
            holder.btnContinue.setVisibility(View.GONE);
        }
        else{
            holder.tv_get_certificate.setVisibility(View.GONE);
            holder.btnContinue.setVisibility(View.VISIBLE);
        }

        holder.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onItemClick(recentCours.get(position), position);

            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return recentCours.size();
    }

    public RecentCours getItem(int position) {
        return recentCours.get(position);
    }

    public void removeItem(int position) {
        recentCours.remove(position);
        notifyDataSetChanged();

    }

    public void getCertificate(int position){
        recentCours.get(position).setIsContinue(false);
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(RecentCours recentCours, int position);

    }
}

