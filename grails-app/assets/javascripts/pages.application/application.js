var appfreeApplistingApplication =(function($){
    var app = {
        init: function () {
            app.cacheElements();
            app.bindEvents();
            app.instance.tooltips();
            app.instance.initialdataLink();
        },
        cacheElements: function () {
            this.$btnNext = $("#btn-next");
            this.$btnBack = $("#btn-back");
            this.$saveMoreLinks = $("#btn-save-create-link");
            this.$savelinks = $("#btn-save-link");
            this.$regiserApp = $("#btn-register-app");
        },
        bindEvents : function () {
            this.$btnNext.off("click").on("click", controllSlide.next);
            this.$btnBack.off("click").on("click", controllSlide.back);
            this.$regiserApp.off("click").on("click", registerApp.save)
        },
        instance : {
            tooltips : function () {
                $(".tooltipInfo").tooltip();
            },
            initialdataLink : function () {
                var links = [];
                $(".form-links").data("links",links);
            }
        }
    }

    var registerApp = {
        save : function (e) {

            e.preventDefault();
            $("#content-page").show();
            var name = $("#txtName").val();
            var description = $("#txtDescription").val();
            var keywords = $("#txtKeywords").val();
            var platformId = $("#sltPlatforms").val();
            var urlDirect = $("#txtUrlDirect").val();
            var urlHasOffers = $("#txtUrlHasoffers").val();

            if(platformId==null||platformId==""){
                swal("Platform","You need select someone platform","error")
            }

            if(urlDirect==null || urlDirect==""){
                swal("Url App","The url of your app is empty","error")
            };

            var dataToSend = {
                "nombre":name,
                "description":description,
                "keywords":keywords,
                "platformid": platformId,
                "urlDirect": urlDirect,
                "urlHasOffers":urlHasOffers
            };

            $.ajax({
                url: applicationSave,
                type: "POST",
                contentType: "application/json",
                data : JSON.stringify(dataToSend)
            }).done(function () {
                $("#content-page").hide();
                swal({
                    title:"Done",
                    text:"The Application is register",
                    type:"success"
                });
                setTimeout(function () {
                    window.location.replace(redirectPanel);
                },3000)
            }).fail(function () {
                $("#content-page").hide();
            });
        }
    }


    var controllSlide = {
        next : function (e) {

            e.preventDefault();

            if(!validationForms.slideCero()){
                return false;
            };

            $(".slide-app-0").hide();
            $(".slide-app-1").show();
            $("#content-more-links").show();
            $("#btn-back").removeAttr("disabled");
            $("#btn-next").attr("disabled","disabled");

        },
        back : function (e) {
            e.preventDefault()
            $(".slide-app-0").show();
            $(".slide-app-1").hide();
            $("#content-more-links").hide();
            $("#btn-next").removeAttr("disabled");
            $("#btn-back").attr("disabled","disabled");
        }
    }
    
    var validationForms = {
        slideCero : function () {
            var $name = $("#txtName");
            if($name.val() == "" || $name.val() == null ){
                hasErrorIput($name);
                swal({
                    title:"Error",
                    text:"The field name is empty",
                    type:"error"
                });
                return false;
            }else{
                deleteErrorIput($name)
            };

            var $description = $("#txtDescription");
            if($description.val() == "" || $description.val() == null ){
                hasErrorIput($description);
                swal({
                    title:"Error",
                    text:"The field description is empty",
                    type:"error"
                });
                return false;
            }else{
                deleteErrorIput($description)
            };

            var $keywords = $("#txtKeywords");
            if($keywords.val() == "" || $keywords.val() == null ){
                hasErrorIput($keywords);
                swal({
                    title:"Error",
                    text:"The field keywords is empty",
                    type:"error"
                });
                return false;
            }else{
                deleteErrorIput($keywords)
            };

            return true
        },
        slideUno: function () {

        }
    }

    function hasErrorIput($input){
        var formgroup = $input.parent()
        formgroup.addClass("has-error");
    };

    function deleteErrorIput($input){
        var formgroup = $input.parent()
        formgroup.removeClass("has-error");
    };

    return {
        init: app.init()
    }
})(jQuery)