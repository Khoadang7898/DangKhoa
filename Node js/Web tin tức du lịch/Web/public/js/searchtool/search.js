const express = require('express');
const router=express.Router();
var chalk = require('chalk');
var path = require('path');
var config = require(path.resolve("./config/config.js"));
var mongoClient = require("mongodb").MongoClient;
var kqdb_search=[];
mongoClient.connect(config.db.uri, function (err, db) {
    if (err) throw err;
    var dbo = db.db("TravelDB");
    var query={_id: { $lte: 10}};
    dbo.collection('Post').find(query).toArray(function (err, result) {
        db.close();
        kqdb_search=result;
        console.log(chalk.blue("successfully attached kqdb_search"));
    })
});

router.post('/',(req,res)=>{
    var data =[];
    for( i = 0;i<kqdb_search.length;i++)
    {
        var temp=kqdb_search[i].Tags[1].toLowerCase();
        if(temp.indexOf(req.body.datainput.toLowerCase())!=-1)
            data.push({key:kqdb_search[i].Tags[1],url : kqdb_search[i].Url})
    }
    res.json({data:data})
});
module.exports=router;