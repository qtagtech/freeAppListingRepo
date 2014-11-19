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
    <title>Create Applications</title>
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
                            <h2>Create Application</h2>
                            <form id="create-application" role="form">
                                <div class="slide-app-0 row">
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
                                    <div class="col-md-5">
                                        <div style="height: 36px">
                                        </div>
                                        <p class="text-justify">
                                            The  information initial that is requested, is the information necessary to register a application, after, request the information about links, initially is necessary that you selected any platform, if is android or Windows and IOS, any place where you have your application upload, the url direct is a link in where i can see the application to install or see a description of this and url Hasoffers is a url that the system set to that your application, to read few times the application is installed or viewed in a web page.
                                        </p>
                                        <p class="text-justify">
                                            Under of this text appears the button to register your application when you finish of complete the information.
                                        </p>
                                    </div>
                                </div>
                                <div class="slide-app-1 row" style="display: none">
                                    <div class="col-md-5">
                                        <h2 id="data-link"><small> Link information </small></h2>
                                        <div class="content-form-links">
                                            <div class="form-links" style="padding: 5px 20px; border: 1px solid deepskyblue; border-radius: 5px; margin-bottom: 4px">
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
                                                <br/><br/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-5">
                                        <div style="height: 36px">
                                        </div>
                                        <p class="text-justify">
                                            The  information initial that is requested, is the information necessary to register a application, after, request the information about links, initially is necessary that you selected any platform, if is android or Windows and IOS, any place where you have your application upload, the url direct is a link in where i can see the application to install or see a description of this and url Hasoffers is a url that the system set to that your application, to read few times the application is installed or viewed in a web page.
                                        </p>
                                        <p class="text-justify">
                                            Under of this text appears the button to register your application when you finish of complete the information.
                                        </p>
                                        <div class="text-center">
                                            <button id="btn-register-app" class="btn btn-success" style="border-radius: 50%; height: 140px; width: 140px"> Register App</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-5">
                                        <div id="content-more-links" style="display: none" class="row">
                                            <div class="col-md-6">
                                                <button id="btn-save-create-link" type="button" class="btn btn-info width-total-content tooltipInfo" data-toggle="tooltip" data-placement="top" title="Add more links">
                                                    Save and Create New Link
                                                </button>
                                            </div>
                                            <div class="col-md-6">
                                                <button id="btn-save-link" type="button" class="btn btn-info width-total-content tooltipInfo" data-toggle="tooltip" data-placement="top" title="Delete links">
                                                    Save unique Link
                                                </button>
                                            </div>
                                            <br/>
                                            <br/>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <button id="btn-next" type="button" class="btn btn-info width-total-content">Next</button>
                                            </div>
                                            <div class="col-md-6">
                                                <button id="btn-back" type="button" class="btn btn-info width-total-content" disabled>Back</button>
                                            </div>
                                            <br/>
                                            <br/>
                                        </div>

                                        <button style="display: none" id="btn-create-application" type="button" class="btn btn-success">Create Application</button>
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
    <g:javascript>
          var applicationSave = "${createLink(controller:'application',action:'save')}";
          var redirectPanel = "${createLink(controller:'application',action:'list')}";
    </g:javascript>
</sec:access>
</body>
</html>