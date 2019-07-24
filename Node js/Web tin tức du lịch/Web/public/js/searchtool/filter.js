const express = require('express');
const router=express.Router();
var chalk = require('chalk');
var path = require('path');
var config = require(path.resolve("./config/config.js"));
var mongoClient = require("mongodb").MongoClient;
var kqdb_filter=[];
var ejs=require('ejs');

//Mẫu bài viết
var template_single_post =
    "                <div class=\"col-lg-4 intro_col\">\n" +
    "                    <div class=\"intro_item\">\n" +
    "                        <div class=\"intro_item_overlay\"></div>\n" +
    "                        <div class=\"intro_item_background\" style=\"background-image:url(<%=IconUrl%>)\"></div>\n" +
    "                        <div class=\"intro_item_content d-flex flex-column align-items-center justify-content-center\">\n" +
    "                            <div class=\"intro_type\"><%=Tag0%></div>\n" +
    "                            <div class=\"button intro_button\">\n" +
    "                                <div class=\"button_bcg\"></div>\n" +
    "                                <a href=\"<%=Url%>\">Xem thêm<span></span><span></span><span></span></a></div>\n" +
    "                            <div class=\"intro_center text-center\">\n" +
    "                                <h1><%=Tag1%></h1>\n" +
    "                                <div class=\"intro_location\"><%=Tilte%>, Việt Nam</div>\n" +
    "                                <div class=\"rating rating_5\">\n" +
    "                                    <i class=\"fa fa-star\"></i> <i class=\"fa fa-star\"></i> <i class=\"fa fa-star\"></i> <i\n" +
    "                                            class=\"fa fa-star\"></i> <i class=\"fa fa-star\"></i>\n" +
    "                                </div>\n" +
    "                            </div>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n";


mongoClient.connect(config.db.uri, function (err, db) {
    if (err) throw err;
    var dbo = db.db("TravelDB");
    var query={_id: { $lte: 10}};
    dbo.collection('Post').find(query).toArray(function (err, result) {
        db.close();
        kqdb_filter=result;
        console.log(chalk.blue("successfully attached kqdb_filter"));
    })
});



router.post('/',(req,res)=>{
    var temp_locations=req.body.locations;
    var list_post="";
    // var data =[];
    if(temp_locations==="Tất cả"){
        for( i = 0;i<kqdb_filter.length;i++)
        {
            var temp=kqdb_filter[i].Description.toLowerCase();
            var fulltitle=kqdb_filter[i].FullTitle.toLowerCase();
            var keywords=kqdb_filter[i].Keywords.toLowerCase();
            var tag1=kqdb_filter[i].Tags[1].toLowerCase();
            if(temp.indexOf(req.body.keyword.toLowerCase())!=-1||fulltitle.indexOf(req.body.keyword.toLowerCase())!=-1||keywords.indexOf(req.body.keyword.toLowerCase())!=-1||tag1.indexOf(req.body.keyword.toLowerCase())!=-1) {
                list_post += ejs.render(template_single_post, {
                    Tilte: kqdb_filter[i].Title,
                    IconUrl: kqdb_filter[i].IconUrl,
                    Tag0: kqdb_filter[i].Tags[0],
                    Tag1: kqdb_filter[i].Tags[1],
                    Url: kqdb_filter[i].Url
                });
            }
        }
    }else{
        for( i = 0;i<kqdb_filter.length;i++){
            if(temp_locations===kqdb_filter[i].Title){
                var temp=kqdb_filter[i].Description.toLowerCase();
                if(temp.indexOf(req.body.keyword.toLowerCase())!=-1)
                    list_post+=ejs.render(template_single_post,{Tilte:kqdb_filter[i].Title,
                        IconUrl:kqdb_filter[i].Url,
                        Tag0: kqdb_filter[i].Tags[0],
                        Tag1 :  kqdb_filter[i].Tags[1],
                        Url: kqdb_filter[i].Url});
                break;
            }else{
                continue;
            }
        }
    }
    if(list_post===""){
        list_post+="<div class=\"intro\">\n" +
            "        <div class=\"container\">\n" +
            " <div class=\"row\">\n" +
            "                <div class=\"col-lg-10 offset-lg-1\">\n" +
            "                    <div class=\"intro_text text-center\">\n" +
            "                        <p>KHÔNG CÓ KẾT QUẢ TÌM KIẾM</p>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "\n" +
            "   </div>\n" +
            "</div>";
    }

    res.render("searchpages/resultsearch" , {
        data_search_post:list_post,
        domain: "thongtindulich.azurewebsite.net",
        });
});
module.exports=router;