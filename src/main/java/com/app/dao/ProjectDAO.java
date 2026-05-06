package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.app.model.Project;
import com.app.util.DBConnection;

public class ProjectDAO {

    Connection con;

    public boolean createProject(Project project) {

        boolean status = false;

        try {

            con = DBConnection.getConnection();

            String query = "insert into projects(project_name,description,created_by) values(?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, project.getProjectName());
            ps.setString(2, project.getDescription());
            ps.setInt(3, project.getCreatedBy());

            int row = ps.executeUpdate();

            if (row > 0) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

}