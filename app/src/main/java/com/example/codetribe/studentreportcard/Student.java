package com.example.codetribe.studentreportcard;

/**
 * Created by codetribe on 2017/07/27.
 */

public class Student {

    private int id;
    private String fullName;
    private String subject;
    private  int marks;
    public Student() { }

    public Student(int id,String fullName,String subject,int marks){
        this.id = id;
        this.fullName = fullName;
        this. subject =subject;
        this.marks =marks;
    }
    public Student(String fullName,int id) {
        this.fullName =fullName;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
