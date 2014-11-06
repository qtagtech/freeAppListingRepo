<!DOCTYPE html>
<html>
<head>
    <meta name='layout' content='template_login'/>
    <title><g:message code="common.register.title"/> </title>
</head>
<body>
    <div class="col-md-12">
        <form role="form" novalidate>
            <div class="row">
                <div class="content-register">
                    <div id="slide-register-0" class="col-md-4 col-md-offset-2">
                        <div>
                            <h3>Main User Details</h3>
                        </div>
                        <div class="form-group">
                            <label for="txtUserFullName">User Fullname:</label>
                            <input type="text" class="form-control" id="txtUserFullName" name="userFullName" flagKnob="false" placeholder="">
                        </div>
                        <div class="form-group">
                            <label for="txtUserName">Username:</label>
                            <input type="text" class="form-control" id="txtUserName" name="userName" flagKnob="false" placeholder="">
                        </div>
                        <div class="form-group">
                            <label for="txtUserEmail">E-mail:</label>
                            <input type="email" class="form-control" id="txtUserEmail" name="userMail" flagKnob="false" placeholder="">
                        </div>
                        <div class="form-group">
                            <label for="txtUserPosition">Position:</label>
                            <input type="text" class="form-control autocompleteLocation" id="txtUserPosition" flagKnob="false" name="userPosition" placeholder="">
                        </div>
                        <div class="form-group">
                            <label for="txtUserPassword">Password:</label>
                            <input type="text" class="form-control" id="txtUserPassword" name="txtUserPassword" flagKnob="false" placeholder="">
                        </div>
                        <div class="form-group">
                            <label for="txtUserPasswordRep">Password (Again):</label>
                            <input type="text" class="form-control" id="txtUserPasswordRep" name="txtUserPasswordAgain" flagKnob="false" placeholder="">
                        </div>
                    </div>
                    <div id="slide-register-1" class="col-md-4  col-md-offset-2 " style="display: none">
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
                            <input type="text" class="form-control autocompleteLocation" id="txtCompanyLocation" name="companyLocation" placeholder="">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div id="slide-text-register-0">
                            <p class="zone-text-register text-justify">
                                This formulary contain the basic information of user normal for access to this platform, everyone of this text fields can be required.
                            </p>
                            <p class="zone-text-register-m text-justify">
                                Remember that text field <em><mark>username</mark></em> is the same field that you use in the page of <em><mark>Sign In</mark></em>, the same form that you write in this field you must write in the page <em><mark>Sign In</mark></em> in the text field <em><mark>username</mark></em>.
                            </p>
                            <br/><br/>
                        </div>
                        <div id="slide-text-register-1" style="display: none">
                            <p class="zone-text-register text-justify">
                                This formulary contains basic information about your company that you want register, in text field that you see (*), this text field is optional field, if text field don't contain this then is required. Is very important that you complete of form correct this fields to you haven't problems in future, all text fields you can update within the plataform.
                            </p>
                            <br/><br/>
                        </div>
                        <div>
                            <div class="text-center">
                                <input class="knobStadistic" data-readOnly="true" value="0" type="text"/>
                            </div>
                        </div>
                        <br/><br/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div id="div-control-register-button" class="row">
                        <div class="col-md-8 col-md-offset-2">
                            <div class="row">
                                <div class="col-md-6">
                                    <button id="btn-next-slide" class="btn btn-info width-total-content">Next</button>
                                </div>
                                <div class="col-md-6">
                                    <button id="btn-back-slide" disabled class="btn btn-info width-total-content">Back</button>
                                </div>
                            </div>
                        </div>
                        <br/><br/>
                    </div>
                    <div id="div-register-button" class="row" style="display: none">
                        <div class="col-md-8 col-md-offset-2">
                            <button id="btn-registerUserAdmin" class="btn btn-success width-total-content">Register</button>
                        </div>
                        <br/><br/><br/><br/>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <!-- Zona modales -->
    <!-- Modal Register Message-->
    <div class="modal fade" id="registerCallback" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel">Register</h4>
                </div>
                <div class="modal-body">
                    <div id="successMessaje">

                    </div>
                </div>
                <div class="modal-footer">
                    <g:link controller="login" action="auth" class="btn btn-success">Go to Sign in</g:link>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal Print erro register-->
    <div class="modal fade" id="modalErrorForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="modal-error-title">Errors Formulary</h4>
                </div>
                <div class="modal-body">
                    <ul id="errorMessageList">

                    </ul>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <g:javascript>
      var registerLink = "${createLink(controller:'register',action:'save')}"
    </g:javascript>
</body>
</html>
