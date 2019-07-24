"use strict";
var path = require('path');
var pkg = require(path.resolve('./package.json'));
var events = require('events');
var assert = require('assert');
var chalk = require('chalk');

module.exports = {
    db: {
        uri: process.env.MONGODB_URI || 'mongodb://47a46be8-0ee0-4-231-b9ee:quXuWIGRxCxnbQbuPIY8g3W9Bn6Hwe8qkJeqG30ZcmUALfk9RC3LqLU7CV7wJrNJn9eVAriDWhTPQOon9JWmYA%3D%3D@47a46be8-0ee0-4-231-b9ee.documents.azure.com:10255/?ssl=true',
        username: 'doanngoctai',
        password: '8ib0BtxMJVcy3oqTXMcjX9nPxaBzRGvCodHY9EXLyVoLnGOxNbUFviXmfCq73F50Gwyek7LJ8Esvf7l7TCzn1Q==',
        url: () => {
            if (process.env.MONGODB_URI)
                return process.env.MONGODB_URI.split('@')[-1];
            else
                return "doanngoctai.documents.azure.com:10255/?ssl=true";
        },
    },
    server: {
        port: process.env.PORT || '8080',
        host: process.env.HOST || '127.0.0.1',
    },
    app: {
        version: pkg.version,
        "ui-version": pkg["ui-version"],
        description: pkg.description,
        environment: () => {
            if (process.env.NODE_ENV) {
                return process.env.NODE_ENV;
            } else
                return "development";
        },
    },
    connect: {
        type: () => {
            if (process.env.HOST) {
                return 'public';
            } else {
                return 'local';
            }
        }
    },
    getNextSequenceValue: function (dbo, sequence, initialValue=false) {
        var col = dbo.collection("Counter");
        const new_id=-1;
        if (initialValue){
            col.findOneAndUpdate({"_id": sequence}, {$set: {"sequenceValue": 0}}, {
                returnOriginal: false
                , upsert: true
                , j:true
            }, function (err, res) {
                assert.equal(null, err);
                if (res == undefined) {
                    console.log(chalk.red("ERROR : res is underfined. - config.js - line 43"));
                }
                if(isNaN(res.value.sequenceValue)){
                    console.log(chalk.red("ERROR : "+sequence + "is NaN"))
                }
                console.log(chalk.green.bgYellow(sequence + " New _id : " + res.value.sequenceValue));
                return res.value.sequenceValue;
            });
        }else{
            col.findOneAndUpdate({"_id": sequence}, {$inc: {"sequenceValue": 1}}, {
                returnOriginal: false
                , upsert: true
                , j:true
            }, function (err, res) {
                assert.equal(null, err);
                if (res == undefined) {
                    console.log(chalk.red("ERROR : res is underfined. - config.js - line 43"));
                }
                if (isNaN(res.value.sequenceValue)) {
                    console.log(chalk.red("ERROR : " + sequence + "is NaN"))
                }
                console.log(chalk.green.bgYellow(sequence + " New _id : " + res.value.sequenceValue));
                return res.value.sequenceValue;
            });
        }

    },
};
