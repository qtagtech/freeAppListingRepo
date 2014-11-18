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
                        <div style="height: 38px"></div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <h2>Create Application</h2>
                            <form id="create-application" role="form">
                                <div class="row">
                                    <div class="col-md-5">
                                        <h2><small> Basic information </small></h2>
                                        <div class="form-group">
                                            <label for="txtName">Name:</label>
                                            <input type="text" class="form-control" id="txtName" name="Name" placeholder="Name application">
                                        </div>
                                        <div class="form-group">
                                            <label for="txtDescription">Description:</label>
                                            <textarea class="form-control" id="txtDescription" placeholder="Description of application." rows="3" style="resize: none"></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label for="txtKeywords">Keywords:</label>
                                            <input type="text" class="form-control" id="txtKeywords" name="keywords" placeholder="Example: accion, sports.">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-5">
                                        <h2><small> Link information </small></h2>
                                        <div class="form-group">
                                            <label for="txtKeywords">Platform:</label>
                                            <select id="sltPlatforms" class="form-control">
                                                <option value="0"> Select some plataform</option>
                                                <g:each in="${platformsList}" var="platforms">
                                                    <option value="${platforms.id}">${platforms.name}</option>
                                                </g:each>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="txtUrlDirect">Url Direct:</label>
                                            <input type="text" class="form-control" id="txtUrlDirect" name="UrlDirect" placeholder="Direct link to see in store">
                                        </div>
                                        <div class="form-group">
                                            <label for="txtUrlHasoffers">Url Hasoffers:</label>
                                            <input type="text" class="form-control" id="txtUrlHasoffers" name="Urlhasoffers" placeholder="Link of hasoffers">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-5 text-right">
                                        <button id="btn-create-application" type="button" class="btn btn-success">Create Application</button>
                                    </div>
                                </div>
                                <br/><br/>
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