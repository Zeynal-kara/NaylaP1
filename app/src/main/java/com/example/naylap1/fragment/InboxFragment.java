package com.example.naylap1.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.naylap1.R;
import com.example.naylap1.adapter.MessageRvAdapter;
import com.example.naylap1.model.Message;
import com.example.naylap1.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class InboxFragment extends Fragment {


    public InboxFragment() {
        // Required empty public constructor
    }

    public static InboxFragment newInstance() {

        Bundle args = new Bundle();

        InboxFragment fragment = new InboxFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_inbox, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final RecyclerView recyclerView = getActivity().findViewById(R.id.rv_fragment_inbox);

        User user = User.getInstance();

        MessageRvAdapter messageRvAdapter = new MessageRvAdapter(user.getMessageManager().getMessageList(),
                new MessageRvAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(Message inboxMessage, int position) {
                        Toast.makeText(getContext(), "Mesaj Okundu ...", Toast.LENGTH_LONG).show();
                        ( (MessageRvAdapter)(recyclerView.getAdapter()) ).setItemRead(position);


                    }
                });

        recyclerView.setAdapter(messageRvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
