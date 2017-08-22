package com.example.demo.Interfaces;

import org.springframework.beans.factory.annotation.Autowired;

public interface QueueRepository {
    void addItem(String studentName, String location, String question, boolean teacher1, boolean teacher2, boolean anyTeacher);
}
