package com.example.tom.Organite;

/**
 * Created by Tom on 29/03/2016.
 */
public class SubjectClass {
    public String Name;
    public String DefaultTeacher;
    public String DefaultRoom;

    public SubjectClass(String N, String T, String R){
        Name = N;
        DefaultTeacher = T;
        DefaultRoom = R;
    }

    public String Getname(){
      return Name;
    }

}
