package com.example.demo.Interfaces;

import com.example.demo.Domain.QueueItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface QueueRepository {
    void addItem(String studentName, String location, String question);
    List<QueueItem> getQueueItems();
    void deleteItem(int id);
    void addUser(String studentName, String username, String password);
}
