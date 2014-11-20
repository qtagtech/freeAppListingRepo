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

    var createCampaing = {
        save : function(e){

            e.preventDefault();

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
                        url:campaignDelete,
                        type:"POST",
                        data:dataToSend
                    }).done(function (data) {
                        if(data.status==1){
                            swal("Save Compaign","Save Campaign successfully","success")
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