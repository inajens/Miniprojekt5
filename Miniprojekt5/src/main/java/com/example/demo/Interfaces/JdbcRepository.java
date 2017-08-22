package com.example.demo.Interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class JdbcRepository implements QueueRepository {

    @Autowired
    DataSource dataSource;

    @Override
    public void addItem(String studentName, String location, String question, boolean teacher1, boolean teacher2, boolean anyTeacher) {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Queue(studentName, location, question, teacher1, teacher2, anyteacher) VALUES (?,?,?,?,?,?,?)", new String[] {"id"})) {
            ps.setString(1, studentName);
            ps.setString(2, location);
            ps.setString(3, question);
            ps.setBoolean(4, teacher1);
            ps.setBoolean(5, teacher2);
            ps.setBoolean(6, anyTeacher);
            ps.executeUpdate();
        } catch (SQLException e) {
            //throw new QueueRepositoryException(e);
        }
    }
}
