/**
 * Funcionaes basica register jquery
 */
var appfreeApplistingregister =(function($){

     var app = {
        events:{
            init:function(){
                app.events.cacheElements();
                app.events.bindEvents();

            },
            cacheElements:function(){
                this.$btnRegister = $("#btn-registerUserAdmin");
            },
            bindEvents: function(){
                this.$btnRegister.off("click").on("click",actionForm.form.sendForm);
            }
        }
    };
    var actionForm = {
        form :{
            sendForm: function(){
                var dataToSend = {
                    "item": "hola",
                    "itemUno": "rafael"
                };
                $.ajax({
                    url: registerLink,
                    contentType: "application/json",
                    type: "POST",
                    dataType: "JSON",
                    data: JSON.stringify(dataToSend)
                }).done(function(data){
                    alert(data.messaje + ".............");
                }).fail(function(data){
                    alert("error de envio")
                })
            }
        }
    }

    return appInit = {
        init: app.events.init()
    };

})(jQuery);