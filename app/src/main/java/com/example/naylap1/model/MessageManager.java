package com.example.naylap1.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageManager implements Serializable {
    List<Message> messageList;

    public MessageManager(){
        messageList = new ArrayList<>();
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(@NotNull List<Message> messageList) {
        this.messageList = messageList;
    }

    public void addMessage(@NotNull Message message){

        messageList.add(0, message);
    }

    public int getUnreadCount(){
        int unReadMessageCount = 0;
        for (int i=0; i<messageList.size(); i++){
            if (messageList.get(i).hasUnreadMessage())
                unReadMessageCount ++;
        }

        return unReadMessageCount;
    }

}
