package com.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.ProjectDAO;
import com.app.model.Project;
import com.app.model.User;

@WebServlet("/createProject")
public class CreateProjectServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String projectName = request.getParameter("projectName");
        String description = request.getParameter("description");

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        Project project = new Project();

        project.setProjectName(projectName);
        project.setDescription(description);
        project.setCreatedBy(user.getId());

        ProjectDAO dao = new ProjectDAO();

        boolean status = dao.createProject(project);

        if (status) {

            response.getWriter().println("Project Created Successfully");

        } else {

            response.getWriter().println("Project Creation Failed");

        }

    }

}
