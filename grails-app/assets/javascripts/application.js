//= require utilitys/modernizr.2.8.3/modernizr-latest.js
//= require utilitys/bootstrap.3.2.0/bootstrap.min.js
//= require utilitys/jqueryKnob/jquery.knob.js
//= require utilitys/sweetalert/sweet-alert.min.js

var generalFunction = ( function ($) {
    var app = {
        init : function () {
            app.instance.scrollFooter();
        },
        instance : {
            scrollFooter : function () {
                $(document).scroll(function() {
                    if($(document).scrollTop()> 4){
                        $("#footer").removeClass("footer-login");
                        $("#footer").addClass("footer-login-scroll-active");
                    }else{
                        $("#footer").addClass("footer-login");
                        $("#footer").removeClass("footer-login-scroll-active");
                    }
                })
            }
        }
    }

    return {
        init : app.init()
    }

})(jQuery)

