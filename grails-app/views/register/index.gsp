<!DOCTYPE html>
<html>
<head>
    <meta name='layout' content='template_login'/>
    <title><g:message code="common.register.title"/> </title>
</head>
<body>
    <div class="col-md-12">
        <form role="form" action="">
            <div class="row">
                <div class="col-md-4  col-md-offset-2">
                    <div>
                        <h3>Main User Details</h3>
                    </div>
                    <div class="form-group">
                        <label for="txtUserFullName">FullName:</label>
                        <input type="text" class="form-control" id="txtUserFullName" name="userFullName" required  placeholder="Complete username">
                    </div>
                    <div class="form-group">
                        <label for="txtUserName">Username:</label>
                        <input type="text" class="form-control" id="txtUserName" name="userName" required placeholder="Login username">
                    </div>
                    <div class="form-group">
                        <label for="txtUserEmail">E-mail:</label>
                        <input type="text" class="form-control" id="txtUserEmail" name="userMail" required placeholder="Email">
                    </div>
                    <div class="form-group">
                        <label for="txtUserPosition">Position:</label>
                        <input type="text" class="form-control" id="txtUserPosition" name="userPosition" placeholder="">
                    </div>
                    <div class="form-group">
                        <label for="txtUserPassword">Password:</label>
                        <input type="text" class="form-control" id="txtUserPassword" name="userPassword" required placeholder="Password">
                    </div>
                    <div class="form-group">
                        <label for="txtUserPasswordRep">Password (Again):</label>
                        <input type="text" class="form-control" id="txtUserPasswordRep" name="userPasswordAgain" required placeholder="Password Again">
                    </div>
                </div>
                <div class="col-md-4">

                </div>
            </div>
            <div class="row">
                <div class="col-md-4  col-md-offset-2">
                    <div>
                        <h3>Company Details</h3>
                    </div>
                    <div class="form-group">
                        <label for="txtCompanyName">Company Name:</label>
                        <input type="text" class="form-control" id="txtCompanyName" name="companyName" placeholder="">
                    </div>
                    <div class="form-group">
                        <label for="txtCompanyAddress">Address:</label>
                        <input type="text" class="form-control" id="txtCompanyAddress" name="companyAddress" placeholder="">
                    </div>
                    <div class="form-group">
                        <label for="txtCompanyVATNumber">VAT Number:</label>
                        <input type="text" class="form-control" id="txtCompanyVATNumber" name="companyVatNumber" placeholder="">
                    </div>
                    <div class="form-group">
                        <label for="txtCompanyWebPage">WEB Page:</label>
                        <input type="text" class="form-control" id="txtCompanyWebPage" name="companyWebpage" placeholder="">
                    </div>
                    <div class="form-group">
                        <label for="txtCompanyLocation">Location:</label>
                        <input type="text" class="form-control" id="txtCompanyLocation" name="companyLocation" placeholder="">
                    </div>
                </div>
                <div class="col-md-4">

                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="col-md-8 col-md-offset-2">
                        <button id="btn-registerUserAdmin" class="btn btn-success width-total-content">Sign Up</button>
                    </div>
                    <br/><br/><br/><br/>
                </div>
            </div>
        </form>
    </div>
    <g:javascript>
      var registerLink = "${createLink(controller:'register',action:'save')}"
    </g:javascript>
</body>
</html>
