/**
 * Funcionaes basica register jquery
 */
var appfreeApplistingregister =(function($){

     var app = {
        events:{
            init:function(){
                app.events.cacheElements();
                app.events.bindEvents();
                stadisticForm.knobInit();
                //app.events.instance.autocomplete();

            },
            cacheElements:function(){
                this.$btnRegister = $("#btn-registerUserAdmin");
                this.$btnNextSlide = $("#btn-next-slide");
                this.$btnBackSlide = $("#btn-back-slide");
                this.$inpSlideUno = $("#slide-register-0 .form-group input");
                this.$inpSlideDos = $("#slide-register-1 .form-group input");
            },
            bindEvents: function(){
                this.$btnRegister.off("click").on("click",actionForm.form.sendData);
                this.$btnNextSlide.off("click").on("click",controlsFomr.nextSlide);
                this.$btnBackSlide.off("click").on("click",controlsFomr.backSlide);
                this.$inpSlideUno.off("blur").on("blur",stadisticForm.calValueKnob);
                this.$inpSlideDos.off("blur").on("blur",stadisticForm.calValueKnob);
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
    var controlsFomr = {
        nextSlide: function (e) {
            
            e.preventDefault();
            // Validation formulary user
            var respon = validationForm.validateSlideUno();

            if(!respon.sendInfo){
                validationForm.printErrors(respon.stringMessaje);
                $("#modalErrorForm").modal("show");
                return false;
            }
            
            $("#slide-register-0").hide();
            $("#slide-text-register-0").hide();
            $("#slide-register-1").show();
            $("#slide-text-register-1").show();
            $("#btn-back-slide").removeAttr("disabled","disabled");
            $("#btn-next-slide").attr("disabled","disabled");

            // Finish register
            $("#div-register-button").show();
        },
        backSlide: function (e) {
            
            e.preventDefault();
            
            $("#slide-register-0").show();
            $("#slide-text-register-0").show();
            $("#slide-register-1").hide();
            $("#slide-text-register-1").hide();
            $("#btn-back-slide").attr("disabled","disabled");
            $("#btn-next-slide").removeAttr("disabled","disabled");

            // Finish Register
            $("#div-register-button").hide();
        }
    };
    
    var stadisticForm = {
        knobInit: function () {
            $(".knobStadistic").knob({
                "bgColor":"#6D6D6D",
                "format": function (v) {
                    return v + "%";
                }
            });
        },
        calValueKnob: function () {
            var $inputForm = $(this);
            var knobFlag = $inputForm.attr("flagKnob");

            if(knobFlag=="false"){
                var newValKnob;
                var valueKnob = parseFloat($(".knobStadistic").val());

                if(valueKnob==0){
                    if($inputForm.val() != ""){
                        $(".knobStadistic").val(9).trigger('change');
                        $inputForm.attr("flagKnob","true");
                    };
                }
                if(valueKnob>0){
                    if($inputForm.val() != ""){
                        newValKnob = valueKnob + 9;
                        $(".knobStadistic").val(newValKnob).trigger('change');
                        $inputForm.attr("flagKnob","true");
                    };
                };
                if(newValKnob == 99){
                    $(".knobStadistic").val(100).trigger('change');
                };

            }else{
                var valueKnob = parseFloat($(".knobStadistic").val());

                if($inputForm.val() == "" || $inputForm.val() == null ){
                    if(valueKnob == 100){
                        newValKnob = valueKnob - 10
                        $(".knobStadistic").val(newValKnob).trigger('change');
                        $inputForm.attr("flagKnob","false");
                    }else{
                        newValKnob = valueKnob - 9
                        $(".knobStadistic").val(newValKnob).trigger('change');
                        $inputForm.attr("flagKnob","false");
                    }
                };
            }
        }
    };

    var validationForm = {
        validateSlideUno: function () {
            var userFullName, userName, email, position, password, passWordAgain, equalsPass;
            var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;

            userFullName = ($("#txtUserFullName").val() == null || $("#txtUserFullName").val() == "")? false : true;
            userName = ($("#txtUserName").val() == null || $("#txtUserName").val() == "")? false : true;
            email = ($("#txtUserEmail").val() == null || $("#txtUserEmail").val() == "")? false : true;
            position = ($("#txtUserPosition").val() == null || $("#txtUserPosition").val() == "")? false : true;
            password = ($("#txtUserPassword").val() == null || $("#txtUserPassword").val() == "")?false : true;
            passWordAgain = ($("#txtUserPasswordRep").val() == null || $("#txtUserPasswordRep").val() == "")? false : true;
            equalsPass = ($("#txtUserPassword").val() == $("#txtUserPasswordRep").val())? true : false;

            var stringMesaje=[];
            var sendInfo;

            if(!userFullName){
                stringMesaje.push("The field User Fullname is required");
                sendInfo = false;
            };
            if(!userName){
                stringMesaje.push("The field Username is required");
                sendInfo = false;
            };
            if(!email){
                stringMesaje.push("The field Email is required");
                sendInfo = false;
            };
            if(!regex.test($("#txtUserEmail").val())){
                stringMesaje.push("The Email isn't valid");
                sendInfo = false;
            }
            if(!position){
                stringMesaje.push("The field Position is required");
                sendInfo = false;
            };
            if(!password){
                stringMesaje.push("The field password is required");
                sendInfo = false;
            };
            if(!passWordAgain){
                stringMesaje.push("The field Password(Again) is required");
                sendInfo = false;
            };
            if(!equalsPass){
                stringMesaje.push("The Password isn't equal to password(again)");
                sendInfo = false;
            };
            if(sendInfo!=false || sendInfo==="" || sendInfo==null){
                sendInfo = true
            }
            var respuesta={"sendInfo":sendInfo,"stringMessaje":stringMesaje};
            return respuesta;
        },
        validateSlideDos: function () {
            var companyName, companyAddres, vatNumber, webPage, location;

            companyName = ($("#txtCompanyName").val()==null || $("#txtCompanyName").val()=="" )? false:true;
            companyAddres = ($("#txtCompanyAddress").val()==null || $("#txtCompanyAddress").val()=="" )? false:true;
            vatNumber = ($("#txtCompanyVATNumber").val()==null || $("#txtCompanyVATNumber").val()=="" )? false:true;
            webPage = ($("#txtCompanyWebPage").val()==null || $("#txtCompanyWebPage").val()=="" )? false:true;
            location = ($("#txtCompanyLocation").val()==null || $("#txtCompanyLocation").val()=="" )? false:true;

            var stringMesaje=[];
            var sendInfo;

            if(!companyName){
                stringMesaje.push("The field Company Name is required");
                sendInfo = false;
            };
            if(!companyAddres){
                stringMesaje.push("The field Company Address is required");
                sendInfo = false;
            };
            if(!vatNumber){
                stringMesaje.push("The field VAT Number is required");
                sendInfo = false;
            };
            if(!webPage){
                stringMesaje.push("The field Web Page is required");
                sendInfo = false;
            }
            if(!location){
                stringMesaje.push("The field Location is required");
                sendInfo = false;
            };
            if(sendInfo!=false || sendInfo==="" || sendInfo==null){
                sendInfo = true
            }
            var respuesta={"sendInfo":sendInfo,"stringMessaje":stringMesaje};
            return respuesta;
        },
        printErrors: function(stringMessage){
            // Delete list if exist
            $("#errorMessageList li").remove();
            // Create new list
            $.each(stringMessage, function (idx,val) {
                $("#errorMessageList").append("<li style='color: red'>"+val+"</li>");
            });
        }
    };
    
    var actionForm = {
        form :{
            sendData: function(e){

                e.preventDefault();

                var respon = validationForm.validateSlideDos();

                if(!respon.sendInfo){
                    validationForm.printErrors(respon.stringMessaje);
                    $("#modalErrorForm").modal("show");
                    return false;
                };

                // User details
                var fullname = $("#txtUserFullName").val();
                var userName = $("#txtUserName").val();
                var email = $("#txtUserEmail").val();
                var position = $("#txtUserPosition").val();
                var password = $("#txtUserPassword").val();
                // Company Details
                var companyName = $("#txtCompanyName").val();
                var companyAddres = $("#txtCompanyAddress").val();
                var vatNumber = $("#txtCompanyVATNumber").val();
                var webPage = $("#txtCompanyWebPage").val();
                var location = $("#txtCompanyLocation").val();

                var dataToSend = {
                    "userDetails": {
                        "userFullName" : fullname,
                        "userName" : userName ,
                        "email" : email,
                        "position" : position ,
                        password : password
                    },
                    "companyDetails": {
                        "companyName":companyName,
                        "companyAddres":companyAddres,
                        "vatNumber":vatNumber,
                        "webPage":webPage,
                        "location":location
                    }
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