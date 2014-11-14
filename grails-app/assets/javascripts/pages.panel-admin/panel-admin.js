/**
 * Funcionaes basica panel admin jquery
 */
var appfreeApplistingregister =(function($){

     var app = {
        events:{
            init:function(){
                app.events.cacheElements();
                app.events.bindEvents();
                app.events.instance.tooltipsIns()
            },
            cacheElements:function(){
                this.$btnCreatePlatform = $("#btn-save-platform");
                this.$btnCreatePublisher = $("#btn-save-publisher");
                this.$btnCreateEventType = $("#btn-save-eventType");
            },
            bindEvents: function(){
                this.$btnCreatePlatform.off("click").on("click", platform.createNew);
                this.$btnCreatePublisher.off("click").on("click", publisher.createNew);
                this.$btnCreateEventType.off("click").on("click", eventType.createNew);
            },
            instance:{
                tooltipsIns:function(){
                    $(".tooltipInf").tooltip()
                }
            }
        }
    };

    var eventType = {
        createNew : function () {
            var name= $("#txtEvTpyName").val();
            var description= $("#txtEvTpyDescription").val();

            var dataToSend = {
                "name": name,
                "description" : description
            };

            $.ajax({
                url: eventTypeSave,
                type: "POST",
                data: dataToSend
            }).done(function (data){
                if(data.status==1){
                    $("#create-eventType").modal('hide');
                    $("#div-success").show();
                    setTimeout(function(){
                        $("#div-success").hide();
                        location.reload();
                    },2000);
                }else{
                    $("#create-eventType").modal('hide');
                    $("#div-error").show();
                    setTimeout(function(){
                        $("#div-error").hide();
                    },4000);
                }
            }).fail(function () {
                alert("Error en el envio de formulario");
            })
        }
    }

    var publisher = {
        createNew : function () {
            var name = $("#txtPlshName").val();
            var key = $("#txtPlshKey").val();
            var web = $("#txtPlshWeb").val();

            var dataToSend = {
                "name": name,
                "key" : key,
                "web" : web
            };

            $.ajax({
                url: publisherSave,
                type: "POST",
                data: dataToSend
            }).done(function (data){
                if(data.status==1){
                    $("#create-publisher").modal('hide');
                    $("#div-success").show();
                    setTimeout(function(){
                        $("#div-success").hide();
                        location.reload();
                    },2000);
                }else{
                    $("#create-publisher").modal('hide');
                    $("#div-error").show();
                    setTimeout(function(){
                        $("#div-error").hide();
                    },4000);
                }
            }).fail(function () {
                alert("Error en el envio de formulario");
            })
        }

    };

    var platform = {
        createNew: function () {

            var name = $("#txtPlfName").val();
            var description = $("#txtPlfDescription").val();
            var web = $("#txtPlfWeb").val();
            var market = $("#txtPlfMarket").val();

            var dataToSend = {
                "name": name,
                "description": description,
                "web": web,
                "market": market
            };

            $.ajax({
                url: platformSave,
                type: "POST",
                data: dataToSend
            }).done(function (data){
                if(data.status==1){
                    $("#create-platform").modal('hide');
                    $("#div-success").show();
                    setTimeout(function(){
                        $("#div-success").hide();
                        location.reload();
                    },2000);
                }else{
                    $("#create-platform").modal('hide');
                    $("#div-error").show();
                    setTimeout(function(){
                        $("#div-error").hide();
                    },4000);
                }
            }).fail(function () {
                alert("Error en el envio de formulario");
            })
        }
    }

    return appInit = {
        init: app.events.init()
    };

})(jQuery);