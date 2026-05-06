package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.app.model.Task;
import com.app.util.DBConnection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    Connection con;

    public boolean createTask(Task task) {

        boolean status = false;

        try {

            con = DBConnection.getConnection();

            String query = "insert into tasks(title,description,status,assigned_to,project_id) values(?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getStatus());
            ps.setInt(4, task.getAssignedTo());
            ps.setInt(5, task.getProjectId());

            int row = ps.executeUpdate();

            if (row > 0) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    public List<Task> getAllTasks() {

        List<Task> list = new ArrayList<>();

        try {

            con = DBConnection.getConnection();

            String query = "select * from tasks";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Task task = new Task();

                task.setTaskId(rs.getInt("task_id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setStatus(rs.getString("status"));
                task.setAssignedTo(rs.getInt("assigned_to"));
                task.setProjectId(rs.getInt("project_id"));

                list.add(task);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

}
