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
                        <g:if test="${applicationData.empty}">
                            <h2>No hay data</h2>
                        </g:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </sec:access>
    <sec:access expression="hasRole('ROLE_SUPERADMIN')">
    <h2>Settings</h2>
    <div class="row">
        <div class="col-md-4">
            <div class="background-blocks-title-admin">
                <h4 id="" style="color: #f5f5f5"> List Platforms </h4>
            </div>
            <div class="row">
                <g:if test="${platformsList.empty}">
                    <div class="col-md-12">
                        <div class="height-fixed-setting background-blocks-admin">
                            <h2 style="color: darkgray">Register a new Platform</h2>
                        </div>
                    </div>
                </g:if>
                <g:else>
                    <div class="col-md-12">
                        <div class="height-fixed-setting background-blocks-admin">
                            <table id="table-platforms" class="table">
                                <thead>
                                    <tr>
                                        <th><input id="chb-all-platforms" type="checkbox"></th>
                                        <th>#</th>
                                        <th>Name</th>
                                        <th>Web</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <g:each in="${platformsList}" var="platforms" status="i">
                                        <tr data-id="${platforms.id}">
                                            <td><input type="checkbox" value="${platforms.id}"></td>
                                            <td>${i+1}</td>
                                            <td data-colunmvalue="${platforms.name}">${platforms.name}</td>
                                            <td data-colunmvalue="${platforms.web}">${platforms.web}</td>
                                            <td><button data-id="${platforms.id}" class="btn btn-xs btn-info">View All</button></td>
                                        </tr>
                                    </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </g:else>
                <div class="clearfix visible-md-block"></div>
                <div class="col-md-12">
                    <div class="background-blocks-btn-admin">
                        <div class="row" style="padding: 4px 10px">
                            <div class="col-md-6">
                                <button id="btn-create-platform" class="btn btn-success width-total-content" data-toggle="modal" data-target="#create-platform">Create</button>
                            </div>
                            <div class="col-md-6">
                                <button id="btn-delete-platform" class="btn btn-danger width-total-content" >Delete</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="background-blocks-title-admin">
                <h4 style="color: #f5f5f5">List Publisher</h4>
            </div>
            <div class="row">
                <g:if test="${publisherList.empty}">
                    <div class="col-md-12">
                        <div class="height-fixed-setting background-blocks-admin">
                            <h2 style="color: darkgray">Register a new Publisher</h2>
                        </div>
                    </div>
                </g:if>
                <g:else>
                    <div class="col-md-12">
                        <div class="height-fixed-setting background-blocks-admin">
                            <table id="table-publisher" class="table">
                                <thead>
                                <tr>
                                    <th><input id="chb-all-publisher" type="checkbox"></th>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Key</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <g:each in="${publisherList}" var="publisher" status="i">
                                    <tr data-id="${publisher.id}">
                                        <td><input type="checkbox" value="${publisher.id}"></td>
                                        <td>${i+1}</td>
                                        <td data-colunmvalue="${publisher.name}">${publisher.name}</td>
                                        <td data-colunmvalue="${publisher.key}">${publisher.key}</td>
                                        <td><button data-id="${publisher.id}" class="btn btn-xs btn-info">View All</button></td>
                                    </tr>
                                </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </g:else>
                <div class="clearfix visible-md-block"></div>
                <div class="col-md-12">
                    <div class="background-blocks-btn-admin">
                        <div class="row" style="padding: 4px 10px">
                            <div class="col-md-6">
                                <button class="btn btn-success width-total-content" data-toggle="modal" data-target="#create-publisher">Create</button>
                            </div>
                            <div class="col-md-6">
                                <button id="btn-delete-publisher" class="btn btn-danger width-total-content" >Delete</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="background-blocks-title-admin">
                <h4 style="color: #f5f5f5">List Event Type</h4>
            </div>
            <div class="row">
                <g:if test="${eventTypeList.empty}">
                    <div class="col-md-12">
                        <div class="height-fixed-setting background-blocks-admin">
                            <h2 style="color: darkgray">Register a new Event Type</h2>
                        </div>
                    </div>
                </g:if>
                <g:else>
                    <div class="col-md-12">
                        <div class="height-fixed-setting background-blocks-admin">
                            <table id="table-eventype" class="table">
                                <thead>
                                <tr>
                                    <th><input id="chb-all-eventtype" type="checkbox"></th>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <g:each in="${eventTypeList}" var="eventType" status="i">
                                    <tr data-id="${eventType.id}">
                                        <td><input type="checkbox" value="${eventType.id}"></td>
                                        <td>${i+1}</td>
                                        <td data-colunmvalue="${eventType.name}">${eventType.name}</td>
                                        <td data-colunmvalue="${eventType.description}">${eventType.description}</td>
                                        <td><button data-id="${eventType.id}" class="btn btn-xs btn-info">View All</button></td>
                                    </tr>
                                </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </g:else>
                <div class="clearfix visible-md-block"></div>
                <div class="col-md-12">
                    <div class="background-blocks-btn-admin">
                        <div class="row" style="padding: 4px 10px">
                            <div class="col-md-6">
                                <button class="btn btn-success width-total-content" data-toggle="modal" data-target="#create-eventType">Create</button>
                            </div>
                            <div class="col-md-6">
                                <button id="btn-delete-eventtype" class="btn btn-danger width-total-content" >Delete</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal Create platform-->
    <div class="modal fade" id="create-platform" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" >Create Platform </h4>
                </div>
                <div class="modal-body">
                    <div id="form-create-platform">
                        <form id="form-crete-platforms" role="form" novalidate>
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
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="btn-save-platform" class="btn btn-success">Save</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal Create publisher-->
    <div class="modal fade" id="create-publisher" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">Create Publisher </h4>
                </div>
                <div class="modal-body">
                    <div>
                        <form role="form" novalidate>
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
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="btn-save-publisher" class="btn btn-success">Save</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal Create Event Type-->
    <div class="modal fade" id="create-eventType" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">Create Event Type </h4>
                </div>
                <div class="modal-body">
                    <div >
                        <form role="form" novalidate>
                            <div class="form-group">
                                <label for="txtEvTpyName">Name:</label>
                                <input type="text" class="form-control" id="txtEvTpyName" name="evTpyName" placeholder="Name to the Event Type">
                            </div>

                            <div class="form-group">
                                <label for="txtEvTpyDescription">Description:</label>
                                <textarea class="form-control" id="txtEvTpyDescription" placeholder="Description of Event Type." rows="3" style="resize: none"></textarea>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="btn-save-eventType" class="btn btn-success">Save</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <g:javascript>
      var platformSave = "${createLink(controller:'platforms',action:'save')}";
      var platformDelete = "${createLink(controller:'platforms',action:'delete')}";
      var publisherSave = "${createLink(controller:'publisher',action:'save')}";
      var publisherDelete = "${createLink(controller:'publisher',action:'delete')}";
      var eventTypeSave = "${createLink(controller:'eventType',action:'save')}";
      var eventTypeDelete = "${createLink(controller:'eventType',action:'delete')}";
    </g:javascript>
    </sec:access>
<br/><br/>


</body>
</html>

