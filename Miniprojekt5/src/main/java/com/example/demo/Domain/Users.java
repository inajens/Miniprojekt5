package com.example.demo.Domain;


public class Users {
    public long id;
    public String studentName;
    public String username;
    public String password;

    public Users(long id, String studentName, String username, String password) {
        this.id = id;
        this.studentName = studentName;
        this.username = username;
        this.password = password;
    }
}
