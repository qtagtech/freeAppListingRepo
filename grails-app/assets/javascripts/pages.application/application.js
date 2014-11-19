var appfreeApplistingApplication =(function($){
    var app = {
        init: function () {
            app.cacheElements();
            app.bindEvents();
            app.instance.tooltips();
        },
        cacheElements: function () {
            this.$btnNext = $("#btn-next");
            this.$btnBack = $("#btn-back");
            this.$moreLinks = $("#btn-add-more-link");
            this.$deleteLinks = $("#btn-delete-link");
            this.$regiserApp = $("#btn-register-app");
        },
        bindEvents : function () {
            this.$btnNext.off("click").on("click", controllSlide.next);
            this.$btnBack.off("click").on("click", controllSlide.back);
            this.$moreLinks.off("click").on("click", morelinks.more);
            this.$deleteLinks.off("click").on("click", morelinks.delete);
            this.$regiserApp.off("click").on("click", registerApp.save)
        },
        instance : {
            tooltips : function () {
                $(".tooltipInfo").tooltip();
            }
        }
    }

    var registerApp = {
        save : function (e) {

            e.preventDefault();

            var name = $("#txtName").val();
            var description = $("#txtDescription").val();
            var keywords = $("#txtKeywords").val();
            var link = {
                "urlDirect": $("#txtUrlDirect").val(),
                "urlHasOffer": $("#txtUrlHasoffers").val(),
                "platformsId": $("#sltPlatforms").val()
            };

            var dataToSend = {
                "nombre":name,
                "description":description,
                "keywords":keywords,
                "link": link
            };

            $.ajax({
                url: applicationSave,
                type: "POST",
                contentType: "application/json",
                data : JSON.stringify(dataToSend)
            }).done(function () {
                swal({
                    title:"Done",
                    text:"The Application is register",
                    type:"success"
                });
            }).fail(function () {

            });
        }
    }
    
    var morelinks = {
        more: function () {
            var html = $(".form-links").parent().html();
            $(".content-form-links").append(html);
        },
        delete : function () {
            var countForms = $(".form-links")
            if(countForms.length==1){
                return false
            }else{
                $(".form-links").last().remove()
            }
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