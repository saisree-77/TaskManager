<%@ page import="com.app.model.User" %>

<%
    User user = (User) session.getAttribute("user");

    if(user == null || !user.getRole().equals("ADMIN")){

        response.sendRedirect("dashboard.jsp");
    }
%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Project</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container mt-5">

    <div class="row justify-content-center">

        <div class="col-md-6">

            <div class="card shadow">

                <div class="card-header text-center">

                    <h3>Create Project</h3>

                </div>

                <div class="card-body">

                    <form action="createProject" method="post">

                        <div class="mb-3">

                            <label>Project Name</label>

                            <input type="text"
                                   name="projectName"
                                   class="form-control"
                                   required>

                        </div>

                        <div class="mb-3">

                            <label>Description</label>

                            <textarea name="description"
                                      class="form-control"
                                      rows="4"
                                      required></textarea>

                        </div>

                        <div class="text-center">

                            <button type="submit"
                                    class="btn btn-primary">

                                Create Project

                            </button>

                        </div>

                    </form>

                </div>

            </div>

        </div>

    </div>

</div>

</body>
</html>