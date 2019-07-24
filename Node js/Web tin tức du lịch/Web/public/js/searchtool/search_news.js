const express = require('express');
const router=express.Router();
var chalk = require('chalk');
var path = require('path');
var config = require(path.resolve("./config/config.js"));
var mongoClient = require("mongodb").MongoClient;
var ejs=require('ejs');
var data_server=[];
mongoClient.connect(config.db.uri, function (err, db) {
    if (err) throw err;
    var dbo = db.db("TravelDB");
    var query={_id: { $lte: 10}};
    dbo.collection('Post').find(query).toArray(function (err, result) {
        db.close();
        data_server=result;
        console.log(chalk.blue("successfully attached data_server"));
    })
});

template="<div class=\"blog_post\">\n" +
    "                            <div class=\"blog_post_image\">\n" +
    "                                <img src=\"<%=IconUrl%>\" alt=\"<%=Tags1%>\">\n" +
    "                                <div class=\"blog_post_date d-flex flex-column align-items-center justify-content-center\">\n" +
    "                                    <div class=\"blog_post_day\">22</div>\n" +
    "                                    <div class=\"blog_post_month\">May, 2019</div>\n" +
    "                                </div>\n" +
    "                            </div>\n" +
    "                            <div class=\"blog_post_meta\">\n" +
    "                                <ul>\n" +
    "                                    <li class=\"blog_post_meta_item\"><a href=\"\">Administrator</a></li>\n" +
    "                                    <li class=\"blog_post_meta_item\"><a href=\"\">Travel</a></li>\n" +
    "                                    <li class=\"blog_post_meta_item\"><a href=\"\"><%=Commentslength%> Comments</a></li>\n" +
    "                                </ul>\n" +
    "                            </div>\n" +
    "                            <div class=\"blog_post_title\"><a href=\"<%=Url%>\"><%=Tags1%></a></div>\n" +
    "                            <div class=\"blog_post_text\">\n" +
    "                                <p>\n"+
                                            "<%=Description%>"+
    "                                </p>\n" +
    "                            </div>\n" +
    "                            <div class=\"blog_post_link\"><a href=\"<%=Url%>\">Đọc thêm</a></div>\n" +
    "                        </div>";

router.post('/',(req,res)=>{
    var input=req.body.input;
    var list_post="";
    for(i=0;i<data_server.length;i++){

        var fulltitle=data_server[i].FullTitle.toLowerCase();
        var description=data_server[i].Description.toLowerCase();
        if(fulltitle.indexOf(input.toLowerCase())!=-1||description.indexOf(input.toLowerCase())!=-1){
            list_post += ejs.render(template,{
                IconUrl: data_server[i].IconUrl,
                Tags1: data_server[i].Tags[1],
                Description:data_server[i].Description,
                Commentslength:data_server[i].Comments.length,
                Url: data_server[i].Url
            });
        }
    }
    if(list_post===""){
        list_post="Không tìm thấy kết quả";
    }
    res.json({server:list_post});
});
module.exports=router;