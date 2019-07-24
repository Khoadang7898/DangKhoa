var mongoClient = require('mongodb').MongoClient;
var config = require('./config.js');
var chalk = require('chalk');


module.exports.createCounterCollection = function (){
    mongoClient.connect(config.db.uri, {useNewUrlParser: true}, function (err, db) {
        if (err) throw err;
        var dbo = db.db("TravelDB");
        // Create "Counter" Collection
        var counterDoc = [{"_id":"memberId","sequenceValue":0},{"_id":"postId","sequenceValue":0}];
        dbo.collection("Counter").insertMany(counterDoc, function(err, res) {
            if (err) throw err;
            console.log(chalk.green("Member Counter and Post Counter documents inserted"));
        });
        //close db
        db.close();
    });
};
