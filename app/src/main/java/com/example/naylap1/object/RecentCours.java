package com.example.naylap1.object;


public class RecentCours {

    int imgIconResId;
    String title, duration;
    Boolean isContinue;
    ProcessCode processCode;


    public static enum ProcessCode{
        COMPLETED,
        IN_PROCESS,
        NOT_STARTED
    }

    public RecentCours(ProcessCode processCode){
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


    public String getProcessText(){

        switch (processCode){
            case COMPLETED:  return "Completed";
            case IN_PROCESS:  return "In Progress";
            case NOT_STARTED:  return "Not Started";
            default: return "Process Not Defined!";
        }
    }


    public RecentCours.ProcessCode getProcessCode() {
        return processCode;
    }

    public void setProcessCode(RecentCours.ProcessCode processCode) {
        this.processCode = processCode;
    }

    public void setIsContinue(boolean isContinue){
        this.isContinue = isContinue;
    }

    public boolean getIsContinue(){
        return isContinue;
    }


}
