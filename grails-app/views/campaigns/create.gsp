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
                <div class="col-md-10">
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
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</sec:access>
</body>
</html>