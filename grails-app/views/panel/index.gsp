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
    <h1>Settings</h1>
    <div class="row">
        <div class="col-md-4">
            <h3>Platform </h3>
            <div class="row">
                <div class="col-md-12">
                    <form role="form" novalidate>
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
                        <div>
                            <button class="btn btn-success">Save</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <h3>Publisher</h3>
            <div class="row">
                <div class="col-md-12">
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
                        <div>
                            <button class="btn btn-success">Save</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <h3>Event Type</h3>
            <div class="row">
                <div class="col-md-12">
                    <form role="form" novalidate>
                        <div class="form-group">
                            <label for="txtEvTpyName">Name:</label>
                            <input type="text" class="form-control" id="txtEvTpyName" name="evTpyName" placeholder="Name to the Event Type">
                        </div>

                        <div class="form-group">
                            <label for="txtEvTpyDescription">Description:</label>
                            <textarea class="form-control" id="txtEvTpyDescription" placeholder="Description of Event Type." rows="3" style="resize: none"></textarea>
                        </div>
                        <div>
                            <button class="btn btn-success">Save</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>