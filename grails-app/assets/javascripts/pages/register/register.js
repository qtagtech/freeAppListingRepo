/**
 * Funcionaes basica register jquery
 */
var appfreeApplistingregister =(function($){

     var app = {
        events:{
            init:function(){
                app.events.cacheElements();
                app.events.bindEvents();
                //app.events.instance.autocomplete();

            },
            cacheElements:function(){
                this.$btnRegister = $("#btn-registerUserAdmin");
            },
            bindEvents: function(){
                this.$btnRegister.off("click").on("click",actionForm.form.sendData);
            }/*,
            instance:{
                autocomplete:function(){
                    $(".autocompleteLocation").autocomplete({
                        serviceUrl:'https://maps.googleapis.com/maps/api/place/autocomplete/json',
                        minChars:3,
                        type:"GET",
                        dataType:"JSON",
                        paramName:"input",
                        params: { sensor:"false",key:"AIzaSyDsmI7Hy52CUF-JRJ5gclqyt3B17q8ZM2c"}
                    })
                }
            }*/
        }
    };
    var actionForm = {
        form :{
            sendData: function(e){

                e.preventDefault();

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

                    $("#successMessaje").text(data.messaje);

                    if(data.status == 1){
                        $("#registerCallback").modal('show');
                    };

                }).fail(function(data){
                    $("#successMessaje").text("Error in send data, please do again.");

                    if(data.status == 1){
                        $("#registerCallback").modal('show');
                    };
                })
            }
        }
    }

    return appInit = {
        init: app.events.init()
    };

})(jQuery);