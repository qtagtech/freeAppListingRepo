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
        },
        bindEvents : function () {
            this.$btnNext.off("click").on("click", controllSlide.next);
            this.$btnBack.off("click").on("click", controllSlide.back);
            this.$moreLinks.off("click").on("click", morelinks.more);
            this.$deleteLinks.off("click").on("click", morelinks.delete);
        },
        instance : {
            tooltips : function () {
                $(".tooltipInfo").tooltip();
            }
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
            e.preventDefault()
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
            
        }
    }

    return {
        init: app.init()
    }
})(jQuery)