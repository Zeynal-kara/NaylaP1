package com.example.naylap1.model;

import java.util.ArrayList;
import java.util.List;

public class CourseManager {
    List<Course> courseList;

    public CourseManager(){
        courseList =  new ArrayList<>();
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Course> getCompletedCourseList(){

        List<Course> completedList = new ArrayList<>();

        for (int i = 0; i < courseList.size(); i++){

            if (courseList.get(i).getProcessCode() == Course.ProcessCode.COMPLETED){
                completedList.add(courseList.get(i));
            }
        }

        return completedList;
    }

    public List<Course> getInProcessCourseList(){

        List<Course> inProcessList = new ArrayList<>();

        for (int i = 0; i < courseList.size(); i++){

            if (courseList.get(i).getProcessCode() == Course.ProcessCode.IN_PROCESS){
                inProcessList.add(courseList.get(i));
            }
        }

        return inProcessList;
    }

    public List<Course> getNotStartedCourseList(){

        List<Course> notStartedList = new ArrayList<>();

        for (int i = 0; i < courseList.size(); i++){

            if (courseList.get(i).getProcessCode() == Course.ProcessCode.NOT_STARTED){
                notStartedList.add(courseList.get(i));
            }
        }

        return notStartedList;
    }
}
