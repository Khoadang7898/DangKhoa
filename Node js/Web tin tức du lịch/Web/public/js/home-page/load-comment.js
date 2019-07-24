$(document).ready(()=>{
    $("#comment-area").load("?query=comment-data",(result,status,jqXHR)=>{
        console.log("Load Comment Status : "+status);
    })
});

// Process Comment-Like
$(document).ready(()=>{
    $("#comment-area").click("click",(event)=>{
        // alert(event.target.nodeName);
        var UserName=event.target.id;
        $("#"+UserName+" i ").toggleClass("far fas");
        if($("#"+UserName+" i ").hasClass("far")){//Like
            $("#"+UserName+" sup span ").text(parseInt($("#"+UserName+" sup span").text())-1);
        }else{//Unlike
            $("#"+UserName+" sup span ").text(parseInt($("#"+UserName+" sup span").text())+1);
        }
        // If unlike
        if($("#"+UserName+" i ").hasClass("fas")){
            $.post("?query=increase-like",{"UserName":UserName},(data,status,jqXHR)=>{
                console.log("Increase Like Status : ",status);
                console.log("UserName given like : ",UserName);
            }).done((data)=>{
                console.log("UserName : "+UserName);
                console.log("Likes Now :"+data);
            })
        }else{ //like
            $.post("?query=decrease-like",{"UserName":UserName},(data,status,jqXHR)=>{
                console.log("Decrease Like Status : ",status);
                console.log("UserName given like : ",UserName);
            }).done((data)=>{
                console.log("UserName : "+UserName);
                console.log("Likes Now :"+data);
            })
        }
    });
    // $("#comment-area").on("click",(event)=>{
    // });
});

