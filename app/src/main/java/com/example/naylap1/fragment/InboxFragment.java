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
import com.example.naylap1.adapter.InboxRvAdapter;
import com.example.naylap1.Model.InboxMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        InboxRvAdapter inboxRvAdapter = new InboxRvAdapter(loadList(),
                new InboxRvAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(InboxMessage inboxMessage, int position) {
                        Toast.makeText(getContext(), "Mesaj Okundu ...", Toast.LENGTH_LONG).show();
                        ( (InboxRvAdapter)(recyclerView.getAdapter()) ).setItemRead(position);


                    }
                });

        recyclerView.setAdapter(inboxRvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    //Load mock item for testing ...
    private List<InboxMessage> loadList(){

        List<InboxMessage>  messageList = new ArrayList<>();
        InboxMessage inboxMessage = new InboxMessage();
        inboxMessage.setTitle("From: Figma course");
        inboxMessage.setContent("Congratulations! You passed the test and your certificate is ready.");
        inboxMessage.setUnreadCount(1);
        inboxMessage.setDate(new Date());

        messageList.add(inboxMessage);



        for (int i=0; i<7; i++){

            InboxMessage inboxMessage1 = new InboxMessage();
            inboxMessage1.setTitle("From: Figma course"+i);
            inboxMessage1.setContent("Congratulations! You passed the test and your certificate is ready.");
            inboxMessage1.setUnreadCount(0);
            inboxMessage1.setDate(new Date());
            messageList.add(inboxMessage1);

        }



        return messageList;
    }
}
