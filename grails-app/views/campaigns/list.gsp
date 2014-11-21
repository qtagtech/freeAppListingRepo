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
    <title>List Campaigns</title>
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
                            <h2>List Campaigns</h2>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Platforms</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <g:each in="${campaigns}" var="campaign" status="i">
                                    <tr>
                                        <td>${i+1}</td>
                                        <td>${campaign.name}</td>
                                        <td>${campaign.plataforma.name}</td>
                                        <td><button data-id="${campaign.id}" class="btn-delete-campaign btn btn-danger btn-xs">Delete</button></td>
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
          var campaignDelete = "${createLink(controller:'campaigns',action:'delete')}";
    </g:javascript>
</sec:access>
</body>
</html>