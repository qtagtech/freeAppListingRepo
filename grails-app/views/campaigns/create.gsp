<%--
  Created by IntelliJ IDEA.
  User: Qtagtech
  Date: 19/11/14
  Time: 17:25
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name='layout' content='main'/>
    <title>Create Campaigns</title>
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
                            <h2>Create Campaigns</h2>
                            <form role="form">
                                <div class="row">
                                    <div class="col-md-5">
                                        <h2><small>Basic information</small></h2>
                                        <div class="form-group">
                                            <label for="txtName">Name Campaign:</label>
                                            <input type="text" class="form-control" id="txtName" name="name" placeholder="Name of campaign">
                                        </div>
                                        <div class="form-group">
                                            <label for="sltApplication">Application:</label>
                                            <select id="sltApplication" class="form-control">
                                                <option value="0"> Select some Application</option>
                                                <g:each in="${applicationList}" var="app">
                                                    <option value="${app.id}">${app.nombre}</option>
                                                </g:each>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="sltPublisher">Publisher:</label>
                                            <select id="sltPublisher" class="form-control">
                                                <option value="0"> Select some Publisher</option>
                                                <g:each in="${publisherList}" var="publisher">
                                                    <option value="${publisher.id}">${publisher.name}</option>
                                                </g:each>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="sltPlatforms">Platforms:</label>
                                            <select id="sltPlatforms" class="form-control">
                                                <option value="0"> Select some Platform</option>
                                                <g:each in="${platformsList}" var="platforms">
                                                    <option value="${platforms.id}">${platforms.name}</option>
                                                </g:each>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-5">
                                        <div class="margin-create-campaign">
                                            <p class="text-justify">
                                                The formulary that you see in this page, contain the basic information to begin with a new campaign, with this campaign you can see or follow  the events that happen with the publication in various sites, you can see events as installs or clicks, how many times the publication or the application is executed by a events as these.
                                            </p>
                                        </div>
                                        <div class="text-center paddingTop-30">
                                            <button id="btn-create-campaign" class="btn btn-info btn-lg">Register Campaign</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <g:javascript>
          var campaignSave = "${createLink(controller:'campaigns',action:'save')}";
          var campaignDelete = "${createLink(controller:'campaigns',action:'delete')}";
          var campaignList = "${createLink(controller:'campaigns',action:'list')}";
    </g:javascript>
</sec:access>
</body>
</html>