/**
 * Funcionaes basica panel admin jquery
 */
var appfreeApplistingregister =(function($){

     var app = {
        events:{
            init:function(){
                app.events.cacheElements();
                app.events.bindEvents();
                app.events.instance.tooltipsIns();
                app.events.instance.instanceListIds();
                app.events.instance.actionModalPlatform();
            },
            cacheElements:function(){
                this.$btnCreatePlatform = $("#btn-save-platform");
                this.$btnDeletePlatform = $("#btn-delete-platform");
                this.$inputsTypeCheckbox = $("#table-platforms tbody tr td input");
                this.$allSelectTypeCheckbox = $("#chb-all-platforms");
                this.$btnCreatePublisher = $("#btn-save-publisher");
                this.$btnDeletePublisher = $("#btn-delete-publisher");
                this.$inputsTypeCheckboxPublish = $("#table-publisher tbody tr td input");
                this.$allSelectTypeCheckboxPublish = $("#chb-all-publisher");
                this.$btnCreateEventType = $("#btn-save-eventType");
                this.$btnDeleteEventType = $("#btn-delete-eventtype");
                this.$inputsTypeCheckboxEventType = $("#table-eventype tbody tr td input");
                this.$allSelectTypeCheckboxEventtype = $("#chb-all-eventtype");
                this.$btnDeleteUsers = $("#btn-delete-users");
                this.$allSelectTypeCheckboxUsers = $("#chb-all-users");
                this.$inputsTypeCheckboxUsers = $("#table-users tbody tr td input");
            },
            bindEvents: function(){
                this.$btnCreatePlatform.off("click").on("click", platform.createNew);
                this.$btnDeletePlatform.off("click").on("click", platform.delete);
                this.$inputsTypeCheckbox.off("click").on("click", platform.saveIds);
                this.$allSelectTypeCheckbox.off("click").on("click", platform.allSelectedPlatform);
                this.$btnCreatePublisher.off("click").on("click", publisher.createNew);
                this.$btnDeletePublisher.off("click").on("click", publisher.delete);
                this.$inputsTypeCheckboxPublish.off("click").on("click", publisher.saveIds);
                this.$allSelectTypeCheckboxPublish.off("click").on("click", publisher.allSelectedPublisher);
                this.$btnCreateEventType.off("click").on("click", eventType.createNew);
                this.$btnDeleteEventType.off("click").on("click", eventType.delete);
                this.$inputsTypeCheckboxEventType.off("click").on("click", eventType.saveIds);
                this.$allSelectTypeCheckboxEventtype.off("click").on("click", eventType.allSelectedEventType);
                this.$btnDeleteUsers.off("click").on("click", users.delete)
                this.$inputsTypeCheckboxUsers.off("click").on("click", users.saveIds);
                this.$allSelectTypeCheckboxUsers.off("click").on("click", users.allSelectedUsers);
            },
            instance:{
                tooltipsIns:function(){
                    $(".tooltipInf").tooltip()
                },
                instanceListIds: function () {
                    var ids = [];
                    $("#table-platforms").data("platids", ids);
                    $("#table-publisher").data("publisherids", ids);
                    $("#table-eventype").data("eventypeids", ids);
                    $("#table-users").data("idUsers", ids);
                },
                actionModalPlatform : function () {
                    $("#create-platform").on("hidden.bs.modal", function () {
                        $("#form-crete-platforms").trigger("reset");
                    });
                }
            }
        }
    };
    
    var users = {
        delete : function () {
            sweetAlert({
                title:"Delete Users",
                type:"warning",
                text: "¿Are you sure of delete (this/these) users?",
                confirmButtonText: "Confirm",
                closeOnConfirm: true,
                showCancelButton: true,
                cancelButtonText: "Cancel",
                closeOnCancel: false
            },function(isConfirm){

                if (isConfirm) {


                } else {
                    swal({
                        title:"Cancelled",
                        text:"Operation cancelled",
                        type:"error"
                    });
                }
            });
        },
        saveIds: function () {

            if($(this)[0].checked){
                var ids = $("#table-users").data("idUsers");
                var eventTypeId = $(this).val();

                if( ids=="" || ids == null){
                    ids.push(eventTypeId);
                    $("#table-users").data("idUsers",ids);
                }else{
                    var respon = idsActions.existIdsPlatforms(eventTypeId,ids);
                    if(!respon){
                        ids.push(eventTypeId);
                        $("#table-users").data("idUsers",ids);
                    }
                }
            }else{
                var usersId = $(this).val();
                var ids = $("#table-users").data("idUsers");
                var respon = idsActions.existIdsPlatforms(usersId,ids);
                if(respon){
                    idsActions.elminarIdPlatforms(usersId,ids);
                }
            }
        },
        allSelectedUsers: function () {
            $("#table-users tbody tr td input").click()
        }
    }

    var eventType = {
        createNew : function () {
            var name= $("#txtEvTpyName").val();
            var description= $("#txtEvTpyDescription").val();

            var dataToSend = {
                "name": name,
                "description" : description
            };

            $("#create-eventType").modal('hide');

            $.ajax({
                url: eventTypeSave,
                type: "POST",
                data: dataToSend
            }).done(function (data){
                if(data.status==1){
                    swal({
                        title:"Save!",
                        text:"The Event Type register has finished successfully.",
                        type:"success"
                    },function(isConfirm){
                        if(isConfirm){
                            location.reload(true);
                        }
                    });
                }else{
                    swal({
                        title:"Error",
                        text:"Operation save, failed!",
                        type:"error"
                    });
                }
            }).fail(function () {
                swal({
                    title:"Error",
                    text:"Operation save, failed!",
                    type:"error"
                });
            })
        },
        delete : function () {

            var ids = $("#table-eventype").data("eventypeids");

            if(ids == null || ids == ""){
                swal({
                    title:"Error",
                    text:"You need select some Event Type",
                    type:"error"
                });
                return false
            };

            sweetAlert({
                title:"Delete Event Type",
                type:"warning",
                text: "¿Are you sure of delete to this Event Type?",
                confirmButtonText: "Confirm",
                closeOnConfirm: false,
                showCancelButton: true,
                cancelButtonText: "Cancel",
                closeOnCancel: false
            },function(isConfirm){

                if (isConfirm) {

                    var dataToSend = {
                        "ids": ids
                    };

                    $.ajax({
                        url: eventTypeDelete,
                        contentType:"application/json",
                        type: "POST",
                        data: JSON.stringify(dataToSend)
                    }).done(function(data){
                        swal({
                            title:"Deleted!",
                            text:"The Event Type(s)selected has been deleted.",
                            type:"success"
                        },function(isConfirm){
                            if(isConfirm){
                                location.reload(true);
                            }
                        });
                    }).fail(function () {
                        swal({
                            title:"Error",
                            text:"Operation delete, failed!",
                            type:"error"
                        });
                    });

                } else {
                    swal({
                        title:"Cancelled",
                        text:"Operation cancelled",
                        type:"error"
                    });
                }
            });
        },
        saveIds: function () {

            if($(this)[0].checked){
                var ids = $("#table-eventype").data("eventypeids");
                var eventTypeId = $(this).val();

                if( ids=="" || ids == null){
                    ids.push(eventTypeId);
                    $("#table-eventype").data("eventypeids",ids);
                }else{
                    var respon = idsActions.existIdsPlatforms(eventTypeId,ids);
                    if(!respon){
                        ids.push(eventTypeId);
                        $("#table-eventype").data("eventypeids",ids);
                    }
                }
            }else{
                var eventTypeId = $(this).val();
                var ids = $("#table-eventype").data("eventypeids");
                var respon = idsActions.existIdsPlatforms(eventTypeId,ids);
                if(respon){
                    idsActions.elminarIdPlatforms(eventTypeId,ids);
                }
            }
        },
        allSelectedEventType: function () {
            $("#table-eventype tbody tr td input").click()
        }
    }

    var publisher = {
        createNew : function () {
            var name = $("#txtPlshName").val();
            var key = $("#txtPlshKey").val();
            var web = $("#txtPlshWeb").val();

            $("#create-publisher").modal('hide');

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
                    swal({
                        title:"Save!",
                        text:"The publisher register has finished successfully.",
                        type:"success"
                    },function(isConfirm){
                        if(isConfirm){
                            location.reload(true);
                        }
                    });
                }else{
                    swal({
                        title:"Error",
                        text:"Operation save, failed!",
                        type:"error"
                    });
                }
            }).fail(function () {
                swal({
                    title:"Error",
                    text:"Operation save, failed!",
                    type:"error"
                });
            })
        },
        delete: function () {

            var ids = $("#table-publisher").data("publisherids");

            if(ids == null || ids == ""){
                swal({
                    title:"Error",
                    text:"You need select some publisher",
                    type:"error"
                });
                return false
            };

            sweetAlert({
                title:"Delete Publisher",
                type:"warning",
                text: "¿Are you sure of delete to this publisher?",
                confirmButtonText: "Confirm",
                closeOnConfirm: false,
                showCancelButton: true,
                cancelButtonText: "Cancel",
                closeOnCancel: false
            },function(isConfirm){

                if (isConfirm) {

                    var dataToSend = {
                        "ids": ids
                    };

                    $.ajax({
                        url: publisherDelete,
                        contentType:"application/json",
                        type: "POST",
                        data: JSON.stringify(dataToSend)
                    }).done(function(data){
                        swal({
                            title:"Deleted!",
                            text:"The publisher(s)selected has been deleted.",
                            type:"success"
                        },function(isConfirm){
                            if(isConfirm){
                                location.reload(true);
                            }
                        });
                    }).fail(function () {
                        swal({
                            title:"Error",
                            text:"Operation delete, failed!",
                            type:"error"
                        });
                    });

                } else {
                    swal({
                        title:"Cancelled",
                        text:"Operation cancelled",
                        type:"error"
                    });
                }
            });
        },
        saveIds: function () {

            if($(this)[0].checked){
                var ids = $("#table-publisher").data("publisherids");
                var publisherId = $(this).val();

                if( ids=="" || ids == null){
                    ids.push(publisherId);
                    $("#table-publisher").data("publisherids",ids);
                }else{
                    var respon = idsActions.existIdsPlatforms(publisherId,ids);
                    if(!respon){
                        ids.push(publisherId);
                        $("#table-publisher").data("publisherids",ids);
                    }
                }
            }else{
                var platformId = $(this).val();
                var ids = $("#table-publisher").data("publisherids");
                var respon = idsActions.existIdsPlatforms(platformId,ids);
                if(respon){
                    idsActions.elminarIdPlatforms(platformId,ids);
                }
            }
        },
        allSelectedPublisher: function () {
            $("#table-publisher tbody tr td input").click()
        }
    };

    var platform = {
        createNew: function () {

            var name = $("#txtPlfName").val();
            var description = $("#txtPlfDescription").val();
            var web = $("#txtPlfWeb").val();
            var market = $("#txtPlfMarket").val();

            $("#create-platform").modal('hide');

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

                    swal({
                        title:"Save!",
                        text:"The platform register has finished successfully.",
                        type:"success"
                    },function(isConfirm){
                        if(isConfirm){
                            location.reload(true);
                        }
                    });
                }else{
                    swal({
                        title:"Error",
                        text:"Operation save, failed!",
                        type:"error"
                    });
                }
            }).fail(function () {
                swal({
                    title:"Error",
                    text:"Operation save, failed!",
                    type:"error"
                });
            })
        },
        delete: function () {

            var ids = $("#table-platforms").data("platids");
            if(ids == null || ids == "" ){
                swal({
                    title:"Error",
                    text:"You need select some plataform",
                    type:"error"
                });
                return false
            };

            sweetAlert({
                title:"Delete Platforms",
                type:"warning",
                text: "¿Are you sure of delete to this platform?",
                confirmButtonText: "Confirm",
                closeOnConfirm: false,
                showCancelButton: true,
                cancelButtonText: "Cancel",
                closeOnCancel: false
            },function(isConfirm){

                if (isConfirm) {

                    var dataToSend = {
                        "ids": ids
                    };

                    $.ajax({
                        url: platformDelete,
                        contentType:"application/json",
                        type: "POST",
                        data: JSON.stringify(dataToSend)
                    }).done(function(data){
                        swal({
                            title:"Deleted!",
                            text:"The platform(s)selected has been deleted.",
                            type:"success"
                        },function(isConfirm){
                            if(isConfirm){
                                location.reload(true);
                            }
                        });
                    }).fail(function () {
                        swal({
                            title:"Error",
                            text:"Operation delete, failed!",
                            type:"error"
                        });
                    });

                } else {
                    swal({
                        title:"Cancelled",
                        text:"Operation cancelled",
                        type:"error"
                    });
                }
            });
        },
        saveIds: function () {

            if($(this)[0].checked){
                var ids = $("#table-platforms").data("platids");
                var platformId = $(this).val();

                if( ids=="" || ids == null){
                   ids.push(platformId);
                   $("#table-platforms").data("platids",ids);
                }else{
                   var respon = idsActions.existIdsPlatforms(platformId,ids);
                   if(!respon){
                       ids.push(platformId);
                       $("#table-platforms").data("platids",ids);
                   }
                }
            }else{
                var platformId = $(this).val();
                var ids = $("#table-platforms").data("platids");
                var respon = idsActions.existIdsPlatforms(platformId,ids);
                if(respon){
                    idsActions.elminarIdPlatforms(platformId,ids);
                }
            }
        },
        allSelectedPlatform: function () {
            $("#table-platforms tbody tr td input").click()
        }
    }

    var idsActions = {
        existIdsPlatforms: function (plaId, ids) {
            var respon = false;
            $.each(ids, function (i,v) {
                if(v==plaId){
                    respon = true;
                }
            })

            return respon;
        },
        elminarIdPlatforms: function (plaId,ids) {

            $.each(ids, function (i,v) {
                if(v==plaId){
                    ids.splice(i,1);
                }
            });
        }
    };

    return appInit = {
        init: app.events.init()
    };

})(jQuery);