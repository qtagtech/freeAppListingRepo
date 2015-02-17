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
    <title>List Applications</title>
</head>
<body>
<sec:access expression="hasRole('ROLE_USER')">
    <div class="row">
        <div class="col-md-12">
            <div class="row">

                <g:render template="/template/menu"/>

                <div class="col-md-10 col-md-offset-2">
                    <div class="row" style="border-bottom: 1px solid #D6D6D6; margin-top: 60px">
                        <div class="col-md-4" style="padding-left: 40px">
                            <h3> <span><i class="fa fa-desktop fa-2x"></i></span>&nbsp;&nbsp;  Dashboard  <i class="fa fa-caret-right"></i> Application</h3>
                        </div>
                    </div>
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
                                        <th>Link App</th>
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
                                        <td><a class="btn btn-xs btn-info" target="_blank" href="http://${application.link[0].urlDirect}">Ver page app </a></td>
                                        <td><button data-id="${application.id}" class="btn-delete-app btn btn-danger btn-xs">Delete</button></td>
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
    <g:javascript>
          var applicationDete = "${createLink(controller:'application',action:'delete')}";
    </g:javascript>
</sec:access>
</body>
</html>