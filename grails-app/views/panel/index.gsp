<%--
  Created by IntelliJ IDEA.
  User: Qtagtech
  Date: 04/11/14
  Time: 10:14
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name='layout' content='main'/>
    <title>Manager Panel</title>
</head>

<body>
    <sec:access expression="hasRole('ROLE_SUPERADMIN')">
    <h2>Settings</h2>
    <div class="row">
        <div class="col-md-4">
            <div class="background-blocks-title-admin">
                <h4 style="color: #f5f5f5"> Create Platform </h4>
                <h4 style="display: none"> List Platform </h4>
            </div>
            <div class="row">
                <g:if test="${platformsList.empty}">
                    <div  class="col-md-12">
                        <form role="form" novalidate>
                            <div class="height-fixed-setting background-blocks-admin">
                                <div class="form-group">
                                    <label for="txtPlfName">Name:</label>
                                    <input type="text" class="form-control" id="txtPlfName" name="plfName" placeholder="Example: Android, Windows, IOS. etc...">
                                </div>

                                <div class="form-group">
                                    <label for="txtPlfDescription">Description:</label>
                                    <textarea class="form-control" id="txtPlfDescription" placeholder="Description of platform." rows="3" style="resize: none"></textarea>
                                </div>

                                <div class="form-group">
                                    <label for="txtPlfDescription">Web:</label>
                                    <input type="text" class="form-control" id="txtPlfWeb" name="plfWeb" placeholder="Direction web of the platform.">
                                </div>

                                <div class="form-group">
                                    <label for="txtPlfDescription">Market:</label>
                                    <input type="text" class="form-control" id="txtPlfMarket" name="plfMarket" placeholder="">
                                </div>
                            </div>
                        </form>
                    </div>
                </g:if>
                <g:else>
                    <div class="col-md-12">
                        <div class="height-fixed-setting">

                        </div>
                    </div>
                </g:else>
                <div class="clearfix visible-md-block"></div>
                <div class="col-md-12">
                    <div class="background-blocks-btn-admin">
                        <div class="row" style="padding: 4px 10px">
                            <div class="col-md-4">
                                <button class="btn btn-success width-total-content">Save</button>
                            </div>
                            <div class="col-md-4">
                                <button class="btn btn-info width-total-content">List</button>
                            </div>
                            <div class="col-md-4">
                                <button class="btn btn-danger width-total-content" >Delete</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="background-blocks-title-admin">
                <h4 style="color: #f5f5f5">Create Publisher</h4>
                <h4 style="display: none">List Publisher</h4>
            </div>
            <div class="row">
                <g:if test="${publisherList.empty}">
                    <div class="col-md-12 " >
                        <form role="form" novalidate>
                            <div class="height-fixed-setting background-blocks-admin">
                                <div class="form-group">
                                    <label for="txtPlshName">Name:</label>
                                    <input type="text" class="form-control" id="txtPlshName" name="plfName" placeholder="Example: Facebook, Google. etc...">
                                </div>

                                <div class="form-group">
                                    <label for="txtPlshKey">Key:</label>
                                    <input type="text" class="form-control" id="txtPlshKey" name="plfWeb" placeholder="">
                                </div>

                                <div class="form-group">
                                    <label for="txtPlshWeb">Web:</label>
                                    <input type="text" class="form-control" id="txtPlshWeb" name="plfWeb" placeholder="Direction web of the Publisher.">
                                </div>
                            </div>
                        </form>
                    </div>
                </g:if>
                <g:else>
                    <div class="col-md-12">
                        <div class="height-fixed-setting">

                        </div>
                    </div>
                </g:else>
                <div class="clearfix visible-md-block"></div>
                <div class="col-md-12">
                    <div class="background-blocks-btn-admin">
                        <div class="row" style="padding: 4px 10px">
                            <div class="col-md-4">
                                <button class="btn btn-success width-total-content">Save</button>
                            </div>
                            <div class="col-md-4">
                                <button class="btn btn-info width-total-content">List</button>
                            </div>
                            <div class="col-md-4">
                                <button class="btn btn-danger width-total-content" >Delete</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="background-blocks-title-admin">
                <h4 style="color: #f5f5f5">Create Event Type</h4>
                <h4 style="display: none">List Event Type</h4>
            </div>
            <div class="row">
                <g:if test="${eventTypeList.empty}">
                    <div class="col-md-12">
                        <form role="form" novalidate>
                            <div class="height-fixed-setting background-blocks-admin">
                                <div class="form-group">
                                    <label for="txtEvTpyName">Name:</label>
                                    <input type="text" class="form-control" id="txtEvTpyName" name="evTpyName" placeholder="Name to the Event Type">
                                </div>

                                <div class="form-group">
                                    <label for="txtEvTpyDescription">Description:</label>
                                    <textarea class="form-control" id="txtEvTpyDescription" placeholder="Description of Event Type." rows="3" style="resize: none"></textarea>
                                </div>
                            </div>

                        </form>
                    </div>
                </g:if>
                <g:else>
                    <div class="col-md-12">
                        <div class="height-fixed-setting">

                        </div>
                    </div>
                </g:else>
                <div class="clearfix visible-md-block"></div>
                <div class="col-md-12">
                    <div class="background-blocks-btn-admin">
                        <div class="row" style="padding: 4px 10px">
                            <div class="col-md-4">
                                <button class="btn btn-success width-total-content">Save</button>
                            </div>
                            <div class="col-md-4">
                                <button class="btn btn-info width-total-content">List</button>
                            </div>
                            <div class="col-md-4">
                                <button class="btn btn-danger width-total-content" >Delete</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </sec:access>
<br/><br/>
</body>
</html>