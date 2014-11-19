<%--
  Created by IntelliJ IDEA.
  User: Rafael
  Date: 18/11/2014
  Time: 01:39 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name='layout' content='main'/>
    <title>Manager Panel</title>
</head>
<body>
<sec:access expression="hasRole('ROLE_USER')">
    <div class="row">
        <div class="col-md-12">
            <div class="row">

                <g:render template="/template/menu"/>

                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>List Applications</h2>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Name</th>
                                        <th>Description</th>
                                        <th>Keywords</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <g:each in="${applicationList}" var="application" status="i">
                                    <tr>
                                        <td>${i+1}</td>
                                        <td>${application.nombre}</td>
                                        <td>${application.description}</td>
                                        <td>${application.keywords}</td>
                                        <td><button class="btn-delete-app btn btn-danger btn-xs">Delete</button></td>
                                    </tr>
                                    </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</sec:access>
</body>
</html>