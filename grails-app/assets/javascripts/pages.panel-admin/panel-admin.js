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
            },
            bindEvents: function(){
                this.$btnCreatePlatform.off("click").on("click", platform.createNew);
            },
            instance:{
                tooltipsIns:function(){
                    $(".tooltipInf").tooltip()
                }
            }
        }
    };

    var platform = {
        createNew: function () {

            dataToSend = {
                "name": "windows",
                "description":"Plataforma windows plone",
                "web": "http://www.windows.com",
                "market": "games, accion"
            };

            $.ajax({
                url: platformSave,
                type: "POST",
                data: dataToSend
            }).done(function (data){
                if(data.status==1){
                    alert(data.message);
                };
            }).fail(function () {
                alert("Error en el envio de formulario");
            })
        }
    }

    return appInit = {
        init: app.events.init()
    };

})(jQuery);