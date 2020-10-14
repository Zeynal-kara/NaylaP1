package com.example.naylap1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naylap1.R;
import com.example.naylap1.object.InboxMessage;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class InboxRecyclerViewAdaper extends RecyclerView.Adapter<InboxRecyclerViewAdaper.ViewHolder> {
    private List<InboxMessage> _messages;
    private Typeface font_exrta, font_bold,font_medium;

    private OnItemClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_title, tv_content, tv_date, tv_unreadCount;
        public RelativeLayout rl_unread_badges;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_inbox_list_item_title);
            tv_content = itemView.findViewById(R.id.tv_inbox_list_item_content);
            tv_date = itemView.findViewById(R.id.tv_inbox_list_item_date);
            tv_unreadCount = itemView.findViewById(R.id.tv_inbox_list_item_unread_count);
            rl_unread_badges = itemView.findViewById(R.id.rl_inbox_list_item_counter);
        }

    }

    public InboxRecyclerViewAdaper(List<InboxMessage> messages, OnItemClickListener listener) {
        _messages = messages;
        this.listener = listener;
    }

    @NotNull
    @Override
    public InboxRecyclerViewAdaper.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        font_bold = ResourcesCompat.getFont(context, R.font.gilroy_bold);
        font_exrta = ResourcesCompat.getFont(context, R.font.gilroy_extra_bold);
        font_medium = ResourcesCompat.getFont(context, R.font.gilroy_medium);

        // Inflate the custom layout
        View v = inflater.inflate(R.layout.list_item_inbox, parent, false);


        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tv_title.setText(_messages.get(position).getTitle());
        holder.tv_content.setText(_messages.get(position).getContent());
        holder.tv_date.setText(_messages.get(position).getDate());



        if (_messages.get(position).getUnreadCount() == 0){
            holder.rl_unread_badges.setVisibility(View.GONE);
            holder.tv_title.setTypeface(font_medium);
            holder.tv_content.setTypeface(font_medium);
            holder.tv_content.setTextColor(Color.parseColor("#A5A5A5"));
        }else{
            holder.rl_unread_badges.setVisibility(View.VISIBLE);
            holder.tv_unreadCount.setText(String.valueOf(_messages.get(position).getUnreadCount()));
            holder.tv_title.setTypeface(font_exrta);
            holder.tv_content.setTypeface(font_bold);
            holder.tv_content.setTextColor(Color.parseColor("#111318"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onItemClick(_messages.get(position), position);

            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return _messages.size();
    }

    public InboxMessage getItem(int position) {
        return _messages.get(position);
    }

    public void removeItem(int position) {
        _messages.remove(position);
        notifyDataSetChanged();

    }

    public void setItemRead(int position){
        _messages.get(position).setUnreadCount(0);
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(InboxMessage inboxMessage, int position);

    }
}
