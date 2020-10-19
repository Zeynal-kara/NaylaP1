package com.example.naylap1.model;


import android.content.Context;

import com.example.naylap1.R;

public class RecentCourse {

    int imgIconResId;
    String title, duration;
    Boolean isContinue;
    ProcessCode processCode;


    public static enum ProcessCode{
        COMPLETED,
        IN_PROCESS,
        NOT_STARTED
    }

    public RecentCourse(ProcessCode processCode){
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


    public RecentCourse.ProcessCode getProcessCode() {
        return processCode;
    }

    public void setProcessCode(RecentCourse.ProcessCode processCode) {
        this.processCode = processCode;
    }

    public void setIsContinue(boolean isContinue){
        this.isContinue = isContinue;
    }

    public boolean getIsContinue(){
        return isContinue;
    }


}
