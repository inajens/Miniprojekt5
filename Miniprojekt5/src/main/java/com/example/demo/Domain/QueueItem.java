package com.example.demo.Domain;

import java.time.LocalDateTime;

public class QueueItem {
    public long id;
    public String studentName;
    public String location;
    public String question;
    public String status;
//    public boolean teacher1;
//    public boolean teacher2;
//    public boolean anyTeacher;

    public QueueItem(long id, String studentName, String location, String question, String status) {
        this.id = id;
        this.studentName = studentName;
        this.location = location;
        this.question = question;
        this.status = status;
//        this.teacher1 = teacher1;
//        this.teacher2 = teacher2;
//        this.anyTeacher = anyTeacher;
    }
}