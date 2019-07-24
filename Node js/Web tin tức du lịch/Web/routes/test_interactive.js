var express = require('express');
var router = express.Router();
var url = require("url");
const {check, validationResult} = require('express-validator')
var md5 = require('md5');
var mongoClient = require("mongodb").MongoClient;
var config = require('../config/config')
var nodemailer = require('nodemailer');
var ObjectID = require('mongodb').ObjectID

router.post("/like", function (req, res) {
    if (req.body.like == "like") //kiểm tra data gửi lên là like hay unlike
    {
        mongoClient.connect(config.db.uri, function (err, db) { //kết nối tới DB
            if (err) throw err;
            var dbo = db.db("TravelDB");
            var urlPost = req.headers.referer.split("http://localhost:8080")
            var rl = urlPost[1].split("/")
            var newurl = "?" + rl[1] + "=" + rl[2]

            dbo.collection("Post").findOne({Url: newurl}, function (err, result) {
                if (err) {
                    res.json({like: false})
                    throw err
                }
                var li = result.Likes                //xu ly tang so like +1 khi like thành công.
                li = li + 1;
                var newvalues = {$set: {Likes: li}};
                dbo.collection("Post").updateOne({Url: newurl}, newvalues, function (err, result) { //update
                    if (err) {
                        res.json({like: false})
                        throw err
                    }
                    console.log("1 document updated: ĐÃ LIKE");
                    res.json({like: true})
                    db.close();
                });
            })
        });
    } else {
        console.log("unlike")
        mongoClient.connect(config.db.uri, function (err, db) { //kết nối tới DB
            if (err) throw err;
            var dbo = db.db("TravelDB");
            console.log("thanh cong");
            var urlPost = req.headers.referer.split("http://localhost:8080")
            var rl = urlPost[1].split("/")
            var newurl = "?" + rl[1] + "=" + rl[2]
            console.log(newurl)
            dbo.collection("Post").findOne({Url: newurl}, function (err, result) {
                if (err) {
                    res.json({unlike: false})
                    throw err
                }
                var li = result.Likes;                //xu ly tang so like -1 khi bo like
                li = li - 1;
                var newvalues = {$set: {Likes: li}};
                dbo.collection("Post").updateOne({Url: newurl}, newvalues, function (err, result) { //update
                    if (err) {
                        res.json({unlike: false})
                        throw err
                    }
                    res.json({unlike: true})
                    console.log("1 document updated: CHƯA LIKE");
                    db.close();
                });
            })
        });
    }
});
router.post("/share", function (req, res) {
    mongoClient.connect(config.db.uri, function (err, db) { //kết nối tới DB
        if (err) throw err;
        var dbo = db.db("TravelDB");
        var urlPost = req.headers.referer.split("http://localhost:8080")
        var rl = urlPost[1].split("/")
        var newurl = "?" + rl[1] + "=" + rl[2]

        dbo.collection("Post").findOne({Url: newurl}, function (err, result) {
            if (err) {
                res.json({share: false})
                throw err
            }
            var sha = result.Shares
            sha = sha + 1;
            var newvalues = {$set: {Shares: sha}};
            dbo.collection("Post").updateOne({Url: newurl}, newvalues, function (err, result) { //update
                if (err) {
                    res.json({share: false})
                    throw err
                }
                console.log("1 document updated: ĐÃ SHARE");
                res.json({share: true})
                db.close();
            });
        })
    });
});
router.post("/star", function (req, res) {
    console.log(req.body.star)
    mongoClient.connect(config.db.uri, function (err, db) { //kết nối tới DB
        if (err) throw err;
        var dbo = db.db("TravelDB");
        var urlPost = req.headers.referer.split("http://localhost:8080")
        var rl = urlPost[1].split("/")
        var newurl = "?" + rl[1] + "=" + rl[2]

        dbo.collection("Post").findOne({Url: newurl}, function (err, result) {
            if (err) {
                res.json({star: false})
                throw err
            }
            var ra = result.Rank
            if (Array.isArray(ra)) {
                ra.push(req.body.star)
            } else {
                ra = []
                ra.push(req.body.star)
            }
            var newvalues = {$set: {Rank: ra}};
            dbo.collection("Post").updateOne({Url: newurl}, newvalues, function (err, result) { //update
                if (err) {
                    res.json({star: false})
                    throw err
                }
                console.log("1 document updated: ĐÃ ĐÁNH GIÁ");
                res.json({star: true})
                db.close();
            });
        })
    });
});


router.post('/love', function (req, res) {
    if (req.body.love == "love")//kiểm tra data gửi lên là like hay unlike
    {
        if (req.session.view.isLogin) {//nếu user đã login mới thích đc
            console.log("love")
            mongoClient.connect(config.db.uri, function (err, db) {//tạo connect đến db
                if (err) {
                    res.json({love: false})
                    throw err
                }
                var dbo = db.db("TravelDB")
                //  console.log(req)
                var urlPost = req.headers.referer.split("http://localhost:8080")

                var rl = urlPost[1].split("/")
                console.log(rl)
                var urlget = rl[1] + "?" + rl[1] + "=" + rl[2]

                //khúc trên là lấy ra url của bài đăng vd post?post=du-lich-nha-trang
                //nếu like share thì up
                dbo.collection("Post").find({Url: urlget}).toArray(function (err, result) {//tìm bài đăng với urlget
                    if (err) {
                        res.json({love: false})
                        throw err
                    }
                    var idPost = result._id

                    //Lấy ra danh sách yêu thích trong Member -> like share sẽ lấy số like share trong Post
                    dbo.collection("Member").find({Email: req.session.user.name}).toArray(function (err, result) {
                        if (err) {
                            res.json({love: false})
                            throw err
                        }
                        var list = result[0].ListPostLove//lấy danh sách love ->like share thì lấy số like vd like result[0].Likes
                        list.push(ObjectID(idPost))//thêm love like share thì tăng 1 khúc dưới chỉ khác chỗ này là giảm 1
                        //gán lại giá trị Like share thì updataOne lại cho bài đăng có Url : urlget
                        dbo.collection("Member").updateOne({Email: req.session.user.name}, {$set: {ListPostLove: list}}, function (err, result) {
                            if (err) {
                                res.json({love: false})
                                throw err
                            }
                            console.log("1 document updated");
                            res.json({love: true})//love thành công
                            db.close();
                        });
                    })

                })
            })
        } else {
            res.json({love: false})
        }
    } else {
        console.log("unlove")
        if (req.session.view.isLogin) {
            mongoClient.connect(config.db.uri, function (err, db) {
                if (err) {
                    res.json({unlove: false})
                    throw err
                }
                var dbo = db.db("TravelDB")
                console.log(req)
                var urlPost = req.headers.referer.split("http://localhost:8080")
                var rl = urlPost[1].split("/")
                console.log(rl)

                dbo.collection("Post").find({Url: rl[1] + "?" + rl[1] + "=" + rl[2]}).toArray(function (err, result) {
                    if (err) {
                        res.json({unlove: false})
                        throw err
                    }
                    var idPost = result._id
                    dbo.collection("Member").find({Email: req.session.user.name}).toArray(function (err, result) {
                        if (err) {
                            res.json({unlove: false})
                            throw err
                        }
                        var list = result[0].ListPostLove
                        list.pop(ObjectID(idPost))

                        dbo.collection("customers").updateOne({Email: req.session.user.name}, {$set: {ListPostLove: list}}, function (err, result) {
                            if (err) {
                                res.json({unlove: false})
                                throw err
                            }
                            console.log("1 document updated");
                            res.json({unlove: true})
                            db.close();
                        });
                    })

                })
            })
        } else {
            res.json({unlove: false})
        }
    }
})
module.exports = router;