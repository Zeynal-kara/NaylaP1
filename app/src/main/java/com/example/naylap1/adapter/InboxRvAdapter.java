package com.example.naylap1.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naylap1.R;
import com.example.naylap1.Model.InboxMessage;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class InboxRvAdapter extends RecyclerView.Adapter<InboxRvAdapter.ViewHolder> {
    private List<InboxMessage> mMessages;
    private Typeface mFontExtra, mFontBold, mFontMedium;

    private OnItemClickListener mListener;
    private Context mContext;
    private int mColorGrey, mColorBlack;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvTitle, tvContent, tvDate, tvUnreadCount;
        public RelativeLayout rlUnreadBadges;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_inbox_list_item_title);
            tvContent = itemView.findViewById(R.id.tv_inbox_list_item_content);
            tvDate = itemView.findViewById(R.id.tv_inbox_list_item_date);
            tvUnreadCount = itemView.findViewById(R.id.tv_inbox_list_item_unread_count);
            rlUnreadBadges = itemView.findViewById(R.id.rl_inbox_list_item_counter);
        }

    }

    public InboxRvAdapter(List<InboxMessage> messages, OnItemClickListener listener) {
        mMessages = messages;
        this.mListener = listener;
    }

    @NotNull
    @Override
    public InboxRvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View v = inflater.inflate(R.layout.list_item_inbox, parent, false);


        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvTitle.setText(mMessages.get(position).getTitle());
        holder.tvContent.setText(mMessages.get(position).getContent());
        holder.tvDate.setText(mMessages.get(position).getDate());



        if (mMessages.get(position).getUnreadCount() == 0){
            holder.rlUnreadBadges.setVisibility(View.GONE);
            holder.tvTitle.setTypeface(mFontMedium);
            holder.tvContent.setTypeface(mFontMedium);
            holder.tvContent.setTextColor(mColorGrey);
        }else{
            holder.rlUnreadBadges.setVisibility(View.VISIBLE);
            holder.tvUnreadCount.setText(String.valueOf(mMessages.get(position).getUnreadCount()));
            holder.tvTitle.setTypeface(mFontExtra);
            holder.tvContent.setTypeface(mFontBold);
            holder.tvContent.setTextColor(mColorBlack);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.onItemClick(mMessages.get(position), position);

            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public InboxMessage getItem(int position) {
        return mMessages.get(position);
    }

    public void removeItem(int position) {
        mMessages.remove(position);
        notifyDataSetChanged();

    }

    public void setItemRead(int position){
        mMessages.get(position).setUnreadCount(0);
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(InboxMessage inboxMessage, int position);

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mContext = recyclerView.getContext();

        mFontBold = ResourcesCompat.getFont(mContext, R.font.gilroy_bold);
        mFontExtra = ResourcesCompat.getFont(mContext, R.font.gilroy_extra_bold);
        mFontMedium = ResourcesCompat.getFont(mContext, R.font.gilroy_medium);

        mColorGrey = mContext.getResources().getColor(R.color._colorTextGrey3, null);
        mColorBlack = mContext.getResources().getColor(R.color._colorTextBlack, null);

    }
}
