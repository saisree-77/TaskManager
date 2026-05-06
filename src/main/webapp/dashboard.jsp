<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.app.dao.TaskDAO" %>
<%@ page import="com.app.model.Task" %>
<%@ page import="com.app.model.User" %>

<%
    User user = (User) session.getAttribute("user");

    if(user == null){

        response.sendRedirect("login.jsp");
    }

    TaskDAO dao = new TaskDAO();

    List<Task> taskList = dao.getAllTasks();
    int totalTasks = taskList.size();

    int pendingTasks = 0;
    int completedTasks = 0;
    int inProgressTasks = 0;

    for(Task t : taskList){

        if(t.getStatus().equalsIgnoreCase("Pending")){
            pendingTasks++;
        }

        else if(t.getStatus().equalsIgnoreCase("Completed")){
            completedTasks++;
        }

        else{
            inProgressTasks++;
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container mt-5">

    <div class="d-flex justify-content-between mb-4">

        <div>
            <h2>Welcome <%= user.getName() %></h2>
            <h5>Role : <%= user.getRole() %></h5>
        </div>

<div>

<%
    if(user.getRole().equals("ADMIN")){
%>

    <a href="createProject.jsp" class="btn btn-primary">
        Create Project
    </a>

    <a href="createTask.jsp" class="btn btn-success">
        Create Task
    </a>

<%
    }
%>

</div>
</div>

<div class="row mb-4">

    <div class="col-md-4">

        <div class="card bg-primary text-white shadow">

            <div class="card-body text-center">

                <h4>Total Tasks</h4>

                <h2><%= totalTasks %></h2>

            </div>

        </div>

    </div>

    <div class="col-md-4">

        <div class="card bg-warning text-dark shadow">

            <div class="card-body text-center">

                <h4>Pending Tasks</h4>

                <h2><%= pendingTasks %></h2>

            </div>

        </div>

    </div>

    <div class="col-md-4">

        <div class="card bg-success text-white shadow">

            <div class="card-body text-center">

                <h4>Completed Tasks</h4>

                <h2><%= completedTasks %></h2>

            </div>

        </div>

    </div>

</div>

    <div class="card shadow">

        <div class="card-header">

            <h4>All Tasks</h4>

        </div>

        <div class="card-body">

            <table class="table table-bordered">

                <thead>

                    <tr>

                        <th>Task ID</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Assigned User</th>
                        <th>Project ID</th>

                    </tr>

                </thead>

                <tbody>

                <%
                    for(Task task : taskList){
                %>

                    <tr>

                        <td><%= task.getTaskId() %></td>
                        <td><%= task.getTitle() %></td>
                        <td><%= task.getDescription() %></td>
                        <td><%= task.getStatus() %></td>
                        <td><%= task.getAssignedTo() %></td>
                        <td><%= task.getProjectId() %></td>

                    </tr>

                <%
                    }
                %>

                </tbody>

            </table>

        </div>

    </div>

</div>

</body>
</html>