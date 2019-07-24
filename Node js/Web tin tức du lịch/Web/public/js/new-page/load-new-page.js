$(document).ready(()=>{
    $("#blog_parameters").load("?topic=news",{},(result,status,jqXHR)=>{
        console.log("Load Status : "+status);
    })
});
