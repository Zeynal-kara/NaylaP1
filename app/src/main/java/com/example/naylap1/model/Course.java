package com.example.naylap1.model;


import android.content.Context;

import com.example.naylap1.R;

import java.io.Serializable;

public class Course implements Serializable {

    int imgIconResId;
    String title, duration;
    ProcessCode processCode;


    public static enum ProcessCode{
        COMPLETED,
        IN_PROCESS,
        NOT_STARTED
    }

    public Course(ProcessCode processCode){
        this.processCode = processCode;
    }

    public int getImgIconResId() {
        return imgIconResId;
    }

    public void setImgIconResId(int imgIconResId) {
        this.imgIconResId = imgIconResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public String getProcessText(Context context){

        switch (processCode){
            case COMPLETED:  return context.getString(R.string.process_text_completed);
            case IN_PROCESS:  return context.getString(R.string.process_text_in_process);
            case NOT_STARTED:  return context.getString(R.string.process_text_not_started);
            default: return context.getString(R.string.process_text_not_define);
        }
    }


    public Course.ProcessCode getProcessCode() {
        return processCode;
    }

    public void setProcessCode(Course.ProcessCode processCode) {
        this.processCode = processCode;
    }


}
