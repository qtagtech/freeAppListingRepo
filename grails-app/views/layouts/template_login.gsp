<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
  		<asset:stylesheet src="application.css"/>
        <asset:stylesheet src="login/login.css"/>
		<g:layoutHead/>
	</head>
	<body>
        <div class="container-fluid">
            <div class="row">
                <div id="header-login">
                    <div class="col-md-1 text-right col-md-offset-1">
                        <figure style="margin-top: 5px">
                            <a href="${app}"><i class="fa fa-home fa-3x text-white"></i></a>
                        </figure>
                    </div>
                    <div class="col-md-3">
                        <figure style="margin-top: 5px">
                            <img src="${assetPath(src: 'login/logo.png')}" alt="logo-freeapplisting" width="300"/>
                        </figure>
                    </div>
                    <div class="col-md-7">
                        <div class="row">
                            <div class="col-md-4 col-md-offset-8">
                                <div style="margin-top: 5px">
                                    <g:if test="${activeButton == 1}">
                                        <g:link controller="register" class="btn btn-info">Register Now!</g:link>
                                    </g:if>
                                    <g:else>
                                        <g:link controller="register" class="btn btn-info">Sign In</g:link>
                                    </g:else>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <g:layoutBody/>
                </div>
            </div>
            <div class="row">
                <div class="footer-login">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-4 col-md-offset-8">
                                <figure style="display: inline-block; margin-top: 2px; color: #4A6EA9">
                                    <i class="fa fa-facebook-square fa-2x"></i>
                                </figure>
                                <figure style="display: inline-block; margin-top: 2px; color: #00ACED">
                                    <i class="fa fa-twitter fa-2x"></i>
                                </figure>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <asset:javascript src="jquery.1.11.1/jquery1.11.1.min.js" />
        <asset:javascript src="application.js"/>
	</body>
</html>
