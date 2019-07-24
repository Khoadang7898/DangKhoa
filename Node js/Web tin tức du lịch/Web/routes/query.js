var express = require('express');
var router = express.Router();
var url = require('url');
var chalk = require('chalk');
var mongoClient = require('mongodb').MongoClient;
var path = require('path');
var config = require(path.resolve('./config/config'));
var assert = require('assert');


router.get("/",(req,res,next)=>{
    // Output Information of Router
    console.log(chalk.yellow("NOTIFY : ROUTER INFORMATION"));
    console.log(chalk.green("*Router : queryRouter"));
    console.log(chalk.green("*Process Url : "+req.url));

    // Get query string
    var query_string = url.parse(req.url,true).query.query;
    // Get url of post
    var post_url = req.session.current_page;

    // Process "Like Number" query
    if (query_string=="likes-number"){
        mongoClient.connect(config.db.uri,(err,db)=>{
            if (err) throw err;
            let dbo = db.db("TravelDB");
            dbo.collection("Post").findOne({Url:post_url},(err,result)=>{
                if (err) throw err;
                res.send(result.Likes.toString());
                db.close();
            })
        })
    }else if(query_string=="shares-number"){
        mongoClient.connect(config.db.uri,(err,db)=>{
            if (err) throw err;
            let dbo = db.db("TravelDB");
            dbo.collection("Post").findOne({Url:post_url},(err,result)=>{
                if (err) throw err;
                // console.log("Shares : "+result.Shares);
                res.send(result.Shares.toString());
                db.close();
            })
        })
    }else if(query_string=="rank-number"){
        mongoClient.connect(config.db.uri,(err,db)=>{
            if (err) throw err;
            let dbo = db.db("TravelDB");
            dbo.collection("Post").findOne({Url:post_url},(err,result)=>{
                if (err) throw err;
                if (result!=null){
                    let arrRank=result.Rank;
                    let avgRank=0;
                    if (arrRank!=undefined && arrRank!=null){
                        let i ;
                        for (i=0;i<arrRank.length;i++){
                            avgRank+=parseInt(arrRank[i]);
                        }
                        avgRank=avgRank/arrRank.length;
                    }
                    if (isNaN(avgRank))
                        avgRank=1;
                    res.send(avgRank.toString());
                }
                db.close();
            });
        })
    }else if(query_string=="comment-data"){
        mongoClient.connect(config.db.uri,(err,db)=>{
            if (err) throw err;
            let dbo = db.db("TravelDB");
            dbo.collection("Post").findOne({Url:post_url},(err,result)=>{
                if (err) throw err;
                // console.log(result);
                let comments = "";
                if (result!=null){
                    var arrComment = result.Comments;
                    if (arrComment!=undefined&&arrComment.length>0){ // Check it isn't empty
                        var i;
                        var len=arrComment.length;
                        for(i=0;i<len;i++){
                            comments+="<div id=\"comment\" class=\"card mt-0\">\n" +
                                "        <div class=\"card-body\">\n" +
                                "            <div class=\"row\">\n" +
                                "                <div class=\"col-md-3 col-lg-3 text-center\" >\n" +
                                "                    <img src=\"https://image.ibb.co/jw55Ex/def_face.jpg\" class=\"img img-rounded img-fluid\" style=\"width:100px;height:100px;\"/>\n" +
                                "                    <p class=\"text-secondary text-center\">"+arrComment[i].DateCreated.toLocaleString()+"</p>\n" +
                                "                </div>\n" +
                                "                <div class=\"col-md-9 col-lg-9\">\n" +
                                "                    <p style=\"max-height: 100px;\">\n" +
                                "                        <a id=\"Comment_UserName\" class=\"float-left\" href=\"?users="+arrComment[i].UserName+"\"><strong>"+arrComment[i].UserName+"</strong></a>\n" +
                                "                    </p>\n" +
                                "                    <div class=\"clearfix\"></div>\n" +
                                "                    <p>"+arrComment[i].Message+"</p>\n" +
                                "\n" +
                                "                </div>\n" +
                                "            </div>\n" +
                                "            <div>\n" +
                                "                <a class=\"float-right btn btn-primary ml-2\"> <i class=\"fa fa-reply\"></i> Reply</a>\n" +
                                "                <a id='"+arrComment[i].UserName+"' class=\"float-right btn text-danger btn-primary\"><i class=\"far fa-thumbs-up\"></i><sup><span class=\"badge badge-primary badge-pill\">"+arrComment[i].Likes+"</span>\n" +
                                "                </sup></a>\n" +
                                "            </div>\n" +
                                "        </div>\n" +
                                "    </div>";
                        }
                        res.send(comments);
                    }else{
                        console.log(chalk.red("ERROR : Commnet Array is empty !"))
                        res.send("ERROR : Commnet Array is empty");
                    }

                }else {
                    console.log(chalk.red("ERROR : arrComment can't find!"))
                    res.send("ERROR : arrComment can't find!")
                }
                db.close();
            })
        })
    }
});

router.post("/",(req,res,next)=>{
    // Output Information of Router
    console.log(chalk.yellow("NOTIFY : ROUTER INFORMATION"));
    console.log(chalk.green("*Router : queryRouter"));
    console.log(chalk.green("*Method : POST"));
    console.log(chalk.green("*Process Url : "+req.url));

    // Get query string
    var query_string = url.parse(req.url,true).query.query;
    // Get url of post
    var post_url = req.session.current_page;
    // Get User Name Who gived a like from someone
    var UserName=req.body.UserName;
    if(query_string=="increase-like"){
        mongoClient.connect(config.db.uri,(err,db)=>{
            if (err) throw err;
            let dbo = db.db("TravelDB");
            dbo.collection("Post").findOne({Url:post_url},(err,result)=>{
                if (err) throw err;
                if (result!=null){
                    let arrComment = result.Comments;
                    if(arrComment!=undefined&&arrComment.length>0){
                        for (var i =0 ; i<arrComment.length;i++){
                            if(arrComment[i].UserName==UserName) {
                                arrComment[i].Likes=parseInt(arrComment[i].Likes)+1;
                            }
                        }
                        dbo.collection("Post").updateOne({Url:post_url},{$set:{Comments:arrComment}},(err,result2)=>{
                            if(err) throw err;
                            res.send(result2);
                            db.close();
                        });
                    }
                    else{
                        console.log(chalk.red("ERROR : Commnet Array is empty !"));
                        res.send("ERROR : Commnet Array is empty");
                        db.close();
                    }
                }else {
                    console.log(chalk.red("ERROR : arrComment can't find!"));
                    res.send("ERROR : arrComment can't find!");
                    db.close();
                }

            })
        })
    }else if(query_string=="decrease-like"){
        mongoClient.connect(config.db.uri,(err,db)=>{
            if (err) throw err;
            let dbo = db.db("TravelDB");
            dbo.collection("Post").findOne({Url:post_url},(err,result)=>{
                if (err) throw err;
                if (result!=null){
                    let arrComment = result.Comments;
                    if(arrComment!=undefined&&arrComment.length>0){
                        for (var i =0 ; i<arrComment.length;i++){
                            if(arrComment[i].UserName==UserName) {
                                arrComment[i].Likes=parseInt(arrComment[i].Likes)-1;
                            }
                        }
                        dbo.collection("Post").updateOne({Url:post_url},{$set:{Comments:arrComment}},(err,result2)=>{
                            if(err) throw err;
                            res.send(result2);
                            db.close();
                        });
                    }
                    else{
                        console.log(chalk.red("ERROR : Commnet Array is empty !"));
                        res.send("ERROR : Commnet Array is empty");
                        db.close();
                    }
                }else {
                    console.log(chalk.red("ERROR : arrComment can't find!"));
                    res.send("ERROR : arrComment can't find!");
                    db.close();
                }

            })
        })
    }
});
module.exports = router;
