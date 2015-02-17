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
  		<asset:stylesheet src="panel/panel.css"/>
		<g:layoutHead/>
	</head>
	<body>
        <div class="container-fluid">
            <div class="row">
                <div id="header-login">
                    <div class="col-xs-2 col-md-1 text-right no-visible-mobile">
                        <figure style="margin-top: 5px">
                            <i class="fa fa-home fa-3x text-white"></i>
                        </figure>
                    </div>
                    <div class="col-xs-6 col-md-3">
                        <figure style="margin-top: 8px">
                            <g:link controller='panel'><img class="img-responsive" src="${assetPath(src: 'login/logo.png')}" alt="logo-freeapplisting" width="260"/></g:link>
                        </figure>
                    </div>
                    <div class="col-xs-4 col-md-7">
                        <div class="row">
                            <div class="col-xs-6 col-md-3 col-md-offset-7">
                                <div class="text-center" style="padding-top: 6px; color: white">
                                    <p>
                                        Welcome ${dataUser?.username} <br/>
                                        ${dataUser?.email}
                                    </p>
                                </div>
                            </div>
                            <div class="col-xs-6 col-md-2">
                                <div style="margin-top: 5px">
                                    <g:link class="btn btn-info" controller='logout'>Logout</g:link>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-12">
                            <g:layoutBody/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div id="footer" class="footer-login footer-position-mobile">
                    <div class="col-xs-12 col-md-10 col-md-offset-2">
                        <div class="row">
                            <div class=" col-md-12 text-center">
                                <p style="color: #B5B5B5">FreeAppListing </p>
                                <figure style="display: inline-block;">
                                    <i class="fa fa-facebook-square fa-2x"></i>
                                </figure>
                                <figure style="display: inline-block; ">
                                    <i class="fa fa-twitter-square fa-2x"></i>
                                </figure>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="content-page" style="display: none">
            <div class="content-loading">
                <i class="fa fa-refresh fa-spin fa-5x" style="color: #f5f5f5"></i>
            </div>
        </div>
        <asset:javascript src="jquery.1.11.1/jquery1.11.1.min.js" />
        <asset:javascript src="utilitys/mafnificPopup/jquery.magnific-popup.min.js"/>
        <asset:javascript src="application.js"/>
        <asset:javascript src="pages.panel-admin/panel-admin.js"/>
        <asset:javascript src="pages.application/application.js"/>
        <asset:javascript src="pages.application/list.js"/>
        <asset:javascript src="pages.compaign/compaign.js"/>
	</body>
</html>
