package com.example.demo.Interfaces;

import com.example.demo.Domain.QueueItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRepository implements QueueRepository {

    @Autowired
    DataSource dataSource;

    @Override
    public void addItem(String studentName, String location, String question) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Queue(studentName, location, question) VALUES (?,?,?,?)", new String[]{"id"})) {
            ps.setString(1, studentName);
            ps.setString(2, location);
            ps.setString(3, question);
//            ps.setBoolean(4, teacher1);
//            ps.setBoolean(5, teacher2);
//            ps.setBoolean(6, anyTeacher);
            ps.executeUpdate();
        } catch (SQLException e) {
            //throw new QueueRepositoryException(e);
        }
    }

    @Override
    public List<QueueItem> getQueueItems() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name, location, question FROM Queue")) {
            List<QueueItem> queue = new ArrayList<>();
            while (rs.next()) queue.add(rsQueueItem(rs));
            return queue;
        } catch (SQLException e){
            throw null ;

        }
    }

    private QueueItem rsQueueItem(ResultSet rs) throws SQLException {
        return new QueueItem(rs.getLong("id"), rs.getString("name"), rs.getString("location"), rs.getString("question"));
    }
}
