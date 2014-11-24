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

                <div class="col-md-10 col-md-offset-2">
                    <div class="row" style="border-bottom: 1px solid #D6D6D6; margin-top: 60px">
                        <div class="col-md-4" style="padding-left: 40px">
                            <h3> <span><i class="fa fa-desktop fa-2x"></i></span>&nbsp;&nbsp;  Dashboard  <i class="fa fa-caret-right"></i> Campaign</h3>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <h2>List Campaigns</h2>
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Platforms</th>
                                    <th>Publisher</th>
                                    <th>Traking Link</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <g:each in="${campaigns}" var="campaign" status="i">
                                    <tr>
                                        <td>${i+1}</td>
                                        <td>${campaign.name}</td>
                                        <td>${campaign.plataforma.name}</td>
                                        <td>${campaign.publisher.name}</td>
                                        <td>
                                            <a href="#${campaign._id}" class="open-popup-link btn btn-info btn-xs">Show Traking Link</a>
                                            <div id="${campaign._id}" class="white-popup mfp-hide">
                                                <label>Tracking Link:</label>
                                                <p>You can copy link to add in the place that you want. </p>
                                                <p style="word-wrap: break-word;">Link: http://localhost:8080${campaign.trakingUrl}<p>
                                            </div>
                                        </td>
                                        <td><button data-id="${campaign._id}" class="btn-delete-campaign btn btn-danger btn-xs">Delete</button></td>
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