var express = require('express');
var router = express.Router();
var url = require('url');
var chalk = require('chalk');
var mongoClient = require('mongodb').MongoClient;
var path = require('path');
var config = require(path.resolve('./config/config'));
var assert = require('assert');
var fs = require('fs');

router.get("/", (req, res) => {
    // Information of Router
    console.log(chalk.yellow("NOTIFY : ROUTER INFORMATION"));
    console.log(chalk.green("*Router : postRouter"));
    console.log(chalk.green("*Process Url : "+req.url));
    // Create Session About Current Page
    req.session.current_page = req.url.substr(1); //Exclude first slash

    // Xuất thông báo thông tin định tuyến
    var postName = url.parse(req.url, true).query.post;
//    Cài đặt load trang theo dữ liệu từ db.
    if (postName != undefined) {
        //Create Connect to Db
        mongoClient.connect(config.db.uri, (err, db) => {
            if (err) throw err;
            var dbo = db.db("TravelDB");
            var collection = dbo.collection("Post");
            // Create the index
            collection.createIndex(
                {name: "Url"}, function (err, result) {
                    // console.log(result);
                });
            // collection.find({ '$Url': {'$search' : postName } } ).toArray(function(err, docs) {
            //     assert.equal(err, null);
            //     console.log("Found the following records");
            //     console.log(docs);
            //     console.log(chalk.red("Result:",docs.Title));
            //     db.close();
            // });
            collection.findOne({Url: "?post=" + postName}, (err, result) => {
                if (err) throw err;
                dbo.collection("Member").findOne({_id:result.PostBy},(err,result2)=>{
                   if (err) throw err;
                    var postData = {
                        domain: "thongtindulich.azurewebsites.net",
                        Title:result.Title,
                        FullTitle:result.FullTitle,
                        DateCreated:result.DateCreated.toLocaleString(),
                        IconUrl:result.IconUrl,
                        Description:result.Description,
                        Keywords:result.Keywords,
                        PostBy:result2.UserName.trim(),
                        Url:result.Url,
                        HostName:req.hostname,
                    };
                    //Lấy nội dung file
                    // var pathName = "./views/postpages/test-page"+".ejs";
                    var pathName = "./views/postpages/"+postName+".ejs";
                    var postContent=fs.readFileSync(pathName,'utf8');
                    postData.PostContent=postContent;
                    // res.render("postpages/test-page", {domain:"thongtindulich.azurewebsites.net",});
                    res.render("templates/post_pages_template",postData);
                    // console.log(postData);
                    console.log(chalk.green("*Request for \"" + postName + "\" received."));
                    db.close();
                });
            });

        });
    } else {
        res.redirect("/404");
        console.log(chalk.red("ERROR :Request for invalid page received."));
    }
});


module.exports = router;
