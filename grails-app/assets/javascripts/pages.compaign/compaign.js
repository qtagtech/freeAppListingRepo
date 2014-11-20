/**
 * Created by Rafael on 19/11/2014.
 */

var amplificiusCampaigns = ( function ($) {

    var app = {
        init : function () {
            app.cacheElements();
            app.bindElements();
        },
        cacheElements : function () {
            this.$btnCreateCampaign = $("#btn-create-campaign")
        },
        bindElements : function () {
            this.$btnCreateCampaign.off("click").on("click", createCampaing.save)
        }
    }

    var formAction = {
        validation : function () {
            var name = ($("#txtName").val() == null || $("#txtName").val() == "") ? false : true ;
            var application = ($("#sltApplication").val()==null || $("#sltApplication").val()=="" || $("#sltApplication").val()==0 ) ? false : true;
            var publisher = ($("#sltPublisher").val()==null || $("#sltPublisher").val()=="" || $("#sltPublisher").val()==0 )? false : true ;
            var platform = ($("#sltPlatforms").val()==null || $("#sltPlatforms").val()== "" || $("#sltPlatforms").val()==0)? false : true;

            var listError = [];

            if(!name){
                listError.push("Name: Name is empty ")
            }
            if(!application){
                listError.push("Application: Non-selected item ")
            }
            if(!publisher){
                listError.push("Publisher: Non-selected item ")
            }
            if(!platform){
                listError.push("platform: Non-selected item. ")
            }

            if(listError.length>0){
                return {status:0,mensaje:listError.toString()}
            }

            return{status:1}

        }
    }

    var createCampaing = {
        save : function(e){

            e.preventDefault();

            var validation = formAction.validation()

            if(validation.status == 0){
                swal("Error Form","Correct the next errors: "+validation.mensaje+" ","error");
                return false;
            };

            sweetAlert({
                title:"Save Campaign",
                text :"¿Are you sure of save this campaign?",
                type : "warning",

                showCancelButton : true,
                closeOnCancel: false,
                closeOnConfirm: false
            }, function (isConfirm) {
                if(isConfirm){

                    var name = $("#txtName").val();
                    var applicationId = $("#sltApplication").val();
                    var publisherId = $("#sltPublisher").val();
                    var platformId = $("#sltPlatforms").val();

                    var dataToSend = {
                        "name":name,
                        "appId" : applicationId,
                        "plshrId" : publisherId,
                        "plfmId" : platformId
                    };

                    $.ajax({
                        url:campaignSave,
                        type:"POST",
                        data:dataToSend
                    }).done(function (data) {
                        if(data.status==1){
                            sweetAlert({
                                title:"Save Compaign",
                                text:"Save Campaign successfully",
                                type:"success"},function(isConfirm){
                                    if(isConfirm){
                                        window.location.replace(campaignList);
                                    }
                                }
                            )
                        }else{
                            swal("Save Compaign","Error Save Campaign","error")
                        }
                    }).fail(function () {
                        swal("Operatino","Error to operation save campaign ","error")
                    })

                }else{
                    swal("Operatino","Cancel operation","error")
                }
            });
        }
    }

    return {
        init: app.init()
    }
})(jQuery)