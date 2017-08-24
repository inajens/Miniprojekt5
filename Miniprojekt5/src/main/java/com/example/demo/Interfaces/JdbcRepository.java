package com.example.demo.Interfaces;

import com.example.demo.Domain.QueueItem;
import com.example.demo.Domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

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
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Queue(studentName, location, question, status) VALUES (?,?,?,?)")) {
            ps.setString(1, studentName);
            ps.setString(2, location);
            ps.setString(3, question);
            ps.setString(4, "Väntar");
//            ps.setBoolean(4, teacher1);
//            ps.setBoolean(5, teacher2);
//            ps.setBoolean(6, anyTeacher);
            ps.executeUpdate();
        } catch (SQLException e) {
            //throw new QueueRepositoryException(e);
        }
    }

    @Override
    public void deleteItem(int id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Queue WHERE id = ?")) {
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            //throw new QueueRepositoryException(e);
        }
    }

    @Override
    public void chooseItem(int id) {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Queue SET status = 'Får hjälp' WHERE id=?")) {
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            //throw new QueueRepositoryException(e);
        }
    }

    @Override
    public void addUser(String studentName, String username, String password) {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Users(studentName, username, password) VALUES (?,?,?)")) {
            ps.setString(1, studentName);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            //throw new QueueRepositoryException(e);
        }
    }

    @Override
    public List<QueueItem> getQueueItems(){
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, studentName, location, question, status FROM Queue")) {
            List<QueueItem> queue = new ArrayList<>();
            while (rs.next()) queue.add(rsQueueItem(rs));
            return queue;
        } catch (SQLException e){
            return null;

        }
    }

    @Override
    public boolean verifyUser(String username, String password){
        boolean verified=false;
        try (Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT userid, studentName, username, password FROM Users")) {
            List<Users> allUsers = new ArrayList<>();
            while (rs.next()) allUsers.add(rsUsers(rs));
            for (Users users : allUsers) {
                if ((username.equals(users.username)) && (password.equals(users.password))) {
                    verified=true;
                    break;
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return verified;
    }

//    @Override
//    public boolean verifyUser(String username, String password){
//        boolean verified=false;
//        List <Users> allUsers = getUsers();
//        for (Users users : allUsers) {
//            if ((username.equals(users.username)) && (password.equals(users.password))) {
//                verified=true;
//            }
//            else {
//                verified=false;
//            }
//        }
//        return verified;
//    }

    private QueueItem rsQueueItem(ResultSet rs) throws SQLException {
        return new QueueItem(rs.getLong("id"), rs.getString("studentName"), rs.getString("location"), rs.getString("question"), rs.getString("status"));
    }

    private Users rsUsers(ResultSet rs) throws SQLException {
        return new Users(rs.getLong("userid"), rs.getString("studentName"), rs.getString("username"), rs.getString("password"));
    }

}
