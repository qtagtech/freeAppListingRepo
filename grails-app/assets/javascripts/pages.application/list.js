/**
 * Created by Qtagtech on 19/11/14.
 */
var amplificiusAppList = (function ($) {

    var app = {
        init : function () {
            app.cacheElement();
            app.bindEvents();
        },
        cacheElement : function () {
            this.$btnDeleteApp = $(".btn-delete-app");
        },
        bindEvents : function () {
            this.$btnDeleteApp.off("click").on("click", actionApp.delete);
        }
    };

    var actionApp = {
        delete : function () {

            var id = $(this).data("id");

            if(id == null || id == ""){
                swal("Error","Error of system","error")
            };

            var dataToSend = {
                "id": id
            };

            $.ajax({
                url: applicationDete,
                type: "POST",
                data: dataToSend
            }).done(function (data) {
                if(data.status == 1){
                    swal("Delete","Delete successfully","success")
                    setTimeout(function () {
                        window.location.reload(true)
                    },2000);
                }else{
                    swal("Delete","Delete  no successfully","error")
                    setTimeout(function () {
                        window.location.reload(true)
                    },2000);
                }
            }).fail(function () {
                swal("Delete","Delete  no successfully","error");
            });
        }
    }

    return {
        init : app.init()
    }
})(jQuery)
