package com.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dao.TaskDAO;
import com.app.model.Task;

@WebServlet("/createTask")
public class CreateTaskServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        int assignedTo = Integer.parseInt(request.getParameter("assignedTo"));

        int projectId = Integer.parseInt(request.getParameter("projectId"));

        Task task = new Task();

        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setAssignedTo(assignedTo);
        task.setProjectId(projectId);

        TaskDAO dao = new TaskDAO();

        boolean result = dao.createTask(task);

        if (result) {

            response.getWriter().println("Task Created Successfully");

        } else {

            response.getWriter().println("Task Creation Failed");

        }

    }

}