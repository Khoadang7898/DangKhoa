var express = require('express');
var router = express.Router();
var url = require("url");
var chalk = require("chalk");
var mongoClient = require("mongodb").MongoClient;
var config = require('../config/config');
var ObjectId = require("mongodb").ObjectID;
var path = require('path');
var fs = require('fs');
var autoIncrement = require("mongodb-autoincrement");
var multer = require('multer');


// Xử lý chèn dữ liệu khi sửa
router.post('/', function (req, res, next) {
    var query_string = url.parse(req.url, true).query.users;
    if (query_string.indexOf("edit-post-") != -1) {
        var idpost = query_string.slice(10, query_string.length + 1);
        var id1 = parseInt(idpost);
        console.log(idpost)
        console.log(id1);
        mongoClient.connect(config.db.uri, function (err, db) {
            if (err) throw err;
            var dbo = db.db("TravelDB");
            var Title, Description, Content, Tag, i;

            dbo.collection("Post").find({_id: id1}).toArray(function (err, result) {
                if (err) throw err;
                var a = result[0].Url;
                url1 = a.slice(6, a.length);
                Title = result[0].Title;
                var a = result[0].Tags;
                Tag = a[0]
                for (i = 1; i < a.length; i++) {
                    Tag = Tag + ", " + a[i]
                }
                console.log(Tag)
                Description = result[0].Description;
                Content = fs.readFileSync("views/postpages/" + url1 + ".ejs", 'utf8', function (err) {
                });
                console.log(Content);
                db.close();
                res.render("userpages/create-post", {
                    domain: "thongtindulich.azurewebsites.net",
                    Title: Title,
                    Description: Description,
                    Tag: Tag,
                    Content: Content,
                    id: "update-post-" + id1,
                    Button: "Cập nhật",
                    caption: "Cập nhật và chuyển tới bài viết"
                });
            });
        })
    } else {
        next();
    }
});

//xử lý sửa
router.post('/', function (req, res, next) {
    var query_string = url.parse(req.url, true).query.users;
    if (query_string.indexOf("update-post-") != -1) {
        var idpost = query_string.slice(12, query_string.length + 1);
        var id1 = parseInt(idpost);
        mongoClient.connect(config.db.uri, function (err, db) {
            if (err) throw err;
            var dbo = db.db("TravelDB");
            var datapost = req.body.Content;
            var Title1 = req.body.Title;

            function xoa_dau(str) {
                str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
                str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
                str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
                str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
                str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
                str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
                str = str.replace(/đ/g, "d");
                str = str.replace(/À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ/g, "A");
                str = str.replace(/È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ/g, "E");
                str = str.replace(/Ì|Í|Ị|Ỉ|Ĩ/g, "I");
                str = str.replace(/Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ/g, "O");
                str = str.replace(/Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ/g, "U");
                str = str.replace(/Ỳ|Ý|Ỵ|Ỷ|Ỹ/g, "Y");
                str = str.replace(/Đ/g, "D");
                return str;
            }

            Title = xoa_dau(Title1);
            var tag = req.body.Tag;
            var Tag1;
            var NewTag = tag.split(",");
            if (NewTag.length > 0)
                Tag1 = NewTag;
            else
                Tag1 = [
                    "Du lịch"
                ];
            var Titledata1 = Title.replace(/ /g, '-');
            var Titledata = Titledata1.toLowerCase()
            dbo.collection("Post").find({_id: id1}).toArray(function (err, result) {
                if (err) throw err;
                var a = result[0].Url;
                var name = a.slice(6, a.length + 1);
                fs.unlink('views/postpages/' + name + ".ejs", function (err) {
                    if (err) throw err;
                    fs.writeFileSync('views/postpages/' + Titledata + "-id" + id1 + ".ejs", datapost, 'utf8', function (err) {
                        //Kiểm tra nếu có lỗi thì xuất ra lỗi
                        if (err) throw err;

                    });
                })
                dbo.collection('Post').updateMany(
                    {_id: id1},
                    {
                        $set: {
                            Title: req.body.Title,
                            Description: req.body.Description,
                            Tags: Tag1,
                            Url: "?post=" + Titledata + "-id" + id1
                        }
                    }
                );
                db.close();
                res.redirect("?users=list-post");
            });


        });

    } else {
        next();
    }
});


// Xử lý xóa
router.post('/', function (req, res, next) {
    var query_string = url.parse(req.url, true).query.users;

    if (query_string.indexOf("rm-post-") != -1) {
        var idpost = query_string.slice(8, query_string.length + 1);
        var id1 = parseInt(idpost);
        mongoClient.connect(config.db.uri, function (err, db) {
            if (err) throw err;
            var dbo = db.db("TravelDB");
            dbo.collection("Post").find({_id: id1}).toArray(function (err, result) {
                if (err) throw err;
                var a = result[0].Url;
                var name = a.slice(6, a.length + 1);
                fs.unlink('views/postpages/' + name + ".ejs", function (err) {
                    if (err) throw err;
                    dbo.collection("Post").deleteOne({_id: id1}, function (err, obj) {
                        if (err) throw err;
                        db.close();
                        res.redirect("?users=list-post");
                    });
                })
            });


        })


    } else {
        next();
    }
});

// XỬ LÝ DANH SÁCH BÀI VIẾT.
router.get('/', function (req, res, next) {

    var query_string = url.parse(req.url, true).query.users;
    if (query_string == "list-post") {
        mongoClient.connect(config.db.uri, function (err, db) {
            if (err) throw err;
            var dbo = db.db("TravelDB");

            var mailuser = req.session.user.name;

            var query = {Email: mailuser};

            dbo.collection("Member").find(query).toArray(function (err, result) {
                if (err) throw err;
                var ids = result[0]._id;
                var querydata = {PostBy: ids};

                dbo.collection("Post").find(querydata).toArray(function (err, result) {
//    Xử lý result
                    a = ""
                    var row = a;
                    var i;

                    for (i = 0; i < result.length; i++) {
                        var comment = 0;
                        if (result[i].Comments.length == undefined) {
                            comment = 0;
                        } else {
                            comment = result[i].Comments.length;
                        }
                        row += "\n<tr>";
                        row += "\n<td>" + result[i].Title + "</td>";
                        row += "\n<td>" + result[i].DateCreated.toLocaleString() + "</td>";
                        row += "\n<td>" + result[i].Views + "</td>";
                        row += "\n<td>" + result[i].Likes + "</td>";
                        row += "\n<td>" + result[i].Shares + "</td>";
                        row += "\n<td>" + comment + "</td>";
                        row += "\n<td>" + "<div class = \"container\" > " +
                            "<button type = \"button\" class  = \"btn btn-info\" data-toggle = \"modal\" data-target = \"#myModal" + result[i]._id + "\" >" +
                            " Sửa" +
                            "</button>" +


                            "<div class = \"modal\" id = \"myModal" + result[i]._id + "\" >" +
                            "<div class= \"modal-dialog\" >" +
                            "<div class = \"modal-content\" >" +
                            "<div class = \"modal-header\" >" +
                            "<h4 class = \"modal-title\" >" + "Sửa bài viết" + "</h4>" +
                            "<button type = \"button\" class = \"close\" data-dismiss = \"modal\" >" + "&times;" +
                            "</button>" +
                            "</div>" +


                            "<div class = \"modal-body\" >" + "Bạn muốn sửa bài viết này?" +
                            "</div>" +


                            "<div class = \"modal-footer\" >" +
                            "<input type=\"submit\" formaction=\'?users=edit-post-" + result[i]._id + " \' class=\"btn btn-info\" value=\"Sửa\">" +
                            "<button type = \"button\" class = \"btn btn-secondary\" data-dismiss = \"modal\" >" + "Đóng" + "</button>" +
                            "</div>" +

                            "</div>" +
                            "</div>" +
                            "</div>" +

                            "</div>" + "</td> ";
                        row += "\n<td>" + "<div class = \"container\" > " +
                            "<button type = \"button\" class  = \"btn btn-danger\" data-toggle = \"modal\" data-target = \"#myModal1" + result[i]._id + "\" >" +
                            " Xóa" +
                            "</button>" +


                            "<div class = \"modal\" id = \"myModal1" + result[i]._id + "\" >" +
                            "<div class= \"modal-dialog\" >" +
                            "<div class = \"modal-content\" >" +
                            "<div class = \"modal-header\" >" +
                            "<h4 class = \"modal-title\" >" + "Xóa bài viết" + "</h4>" +
                            "<button type = \"button\" class = \"close\" data-dismiss = \"modal\" >" + "&times;" +
                            "</button>" +
                            "</div>" +


                            "<div class = \"modal-body\" >" + "Bạn có chắc chắn muốn xóa bài viết này?" +
                            "</div>" +


                            "<div class = \"modal-footer\" >" +
                            "<input type=\"submit\" formaction=\'?users=rm-post-" + result[i]._id + " \' class=\"btn btn-danger\" value=\"Xóa\">" +
                            "<button type = \"button\" class = \"btn btn-secondary\" data-dismiss = \"modal\" >" + "Đóng" + "</button>" +
                            "</div>" +

                            "</div>" +
                            "</div>" +
                            "</div>" +

                            "</div>" + "</td> ";
                        // row += "<td>" + "<input type='submit' formaction='?users=rm-post-" + result[i]._id + "\' class=\"btn btn-danger\" data-dismiss=\"modal\" value=\"Xóa\"'>" + "</td>";

                        row += "</tr>\n";

                    }


                    db.close();
                    res.render("userpages/list-post", {domain: "thongtindulich.azurewebsites.net", data: row});

                })
            })

        });

        // });
    } else {
        next();
    }

});


// Xử lý Tổng quan User
router.get('/', function (req, res, next) {
    var query_string = url.parse(req.url, true).query.users;
    if (query_string == "account-manager") {
        mongoClient.connect(config.db.uri, function (err, db) {
            if (err) throw err;
            var dbo = db.db("TravelDB");
            var mailuser = req.session.user.name;

            var query = {Email: mailuser};

            dbo.collection("Member").find(query).toArray(function (err, result) {
                if (err) throw err;
                var ids = result[0]._id;
                var querydata = {PostBy: ids};
                dbo.collection("Post").find(querydata).toArray(function (err, result) {
//    Xử lý result

                    var i, soluongbaiviet = 0, luotxem = 0, luotthich = 0, luotchiase = 0, nguoitheodoi = 0;
                    for (i = 0; i < result.length; i++) {

                        soluongbaiviet = i + 1;
                        luotxem = luotxem + result[i].Views;
                        luotthich = luotthich + result[i].Likes;
                        luotchiase = luotchiase + result[i].Shares;
                        nguoitheodoi = nguoitheodoi + result[i].Rank;
                    }
                    db.close();
                    res.render("userpages/account-manager", {
                        domain: "thongtindulich.azurewebsites.net",
                        soluongbaiviet: soluongbaiviet,
                        luotchiase: luotchiase,
                        nguoitheodoi: nguoitheodoi,
                        luotthich: luotthich,
                        luotxem: luotxem
                    });
                });


            })
        });
    } else {
        next();
    }

});

// Xử lý Đăng bài viết
router.post('/', function (req, res, next) {

    var query_string = url.parse(req.url, true).query.users;
    if (query_string == "post-post") {
        var chuyenkieu = require("mongodb").ObjectID;
        var DateCreate = new Date();
        var tag = req.body.Tag;
        var Tag1;

        var NewTag = tag.split(",");


        if (NewTag.length > 0)
            Tag1 = NewTag;
        else
            Tag1 = [
                "Du lịch"
            ];
        var RelevantPostUrl = {};
        var datapost = req.body.Content;
        var Title1 = req.body.Title;

        function xoa_dau(str) {
            str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
            str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
            str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
            str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
            str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
            str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
            str = str.replace(/đ/g, "d");
            str = str.replace(/À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ/g, "A");
            str = str.replace(/È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ/g, "E");
            str = str.replace(/Ì|Í|Ị|Ỉ|Ĩ/g, "I");
            str = str.replace(/Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ/g, "O");
            str = str.replace(/Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ/g, "U");
            str = str.replace(/Ỳ|Ý|Ỵ|Ỷ|Ỹ/g, "Y");
            str = str.replace(/Đ/g, "D");
            return str;
        }
        //chuyển chữ hoa thành thường
        Title = xoa_dau(Title1);
        var Titledata1 = Title.replace(/ /g, '-');
        var Titledata = Titledata1.toLowerCase();


        var comment = {};

        mongoClient.connect(config.db.uri, function (err, db) {
            if (err) throw err;
            var mailuser = req.session.user.name;

            var query = {Email: mailuser};
            var dbo = db.db("TravelDB");

            dbo.collection("Member").find(query).toArray(function (err, result) {
                if (err) throw err;
                var iduser = result[0]._id;
                var PostCollections = result[0].PostCollection;

                var query = {};
                var dbo = db.db("TravelDB");


                var newpost = {
                    PostBy: iduser,
                    Title: req.body.Title,
                    FullTitle: req.body.Title,
                    Keywords: req.body.Title,
                    DateCreated: DateCreate,
                    Tags: Tag1,
                    Url: "Chu y",
                    IconUrl: "Da co loi xay ra",
                    Description: req.body.Description,
                    Rank: 0,
                    Comments: comment,
                    Likes: 0,
                    Views: 0,
                    Shares: 0,
                    RelevantPostUrl: RelevantPostUrl


                };

                var idpost;

                autoIncrement.getNextSequence(dbo, "Post", function (err, autoIndex) {
                    var collection = dbo.collection("Post");
                    newpost._id = autoIndex;
                    dbo.collection("Post").insertOne(newpost, function (err, res1) {
                        if (err) throw err;
                        idpost = res1.insertedId;
                        var Content = req.body.Content;
                        datastring = Content;
                        var start = datastring.indexOf("src=") + 5;
                        var end = datastring.indexOf(" ", start) - 1;
                        var urlimage;
                        if (start > 5) {
                            urlimage = datastring.slice(start, end)
                        }
                        var url = "?post=" + Titledata + "-id" + idpost;

                        fs.writeFileSync('views/postpages/' + Titledata + "-id" + idpost + ".ejs", datapost, 'utf8', function (err) {
                            //Kiểm tra nếu có lỗi thì xuất ra lỗi
                            if (err)
                                throw err;

                        });


                        dbo.collection('Post').updateOne(
                            {_id: idpost},
                            {
                                $set: {
                                    Url: "?post=" + Titledata + "-id" + idpost,
                                    IconUrl: urlimage
                                }
                            }
                        );
                        PostCollections.push(idpost);

                        dbo.collection('Member').updateOne(
                            {_id: iduser},
                            {
                                $set: {
                                    PostCollection: PostCollections
                                }
                            }
                        );
                        res.redirect("?users=list-post");
                        db.close();

                    });
                });
            });


        });
    } else {
        next();
    }
});

// xử lý tạo bài viết
router.get('/', function (req, res, next) {
    var query_string = url.parse(req.url, true).query.users;
    if (query_string == "create-post") {
        var Title = "", Description = "", Content = "", Tag = "";

        res.render("userpages/create-post", {
            domain: "thongtindulich.azurewebsites.net",
            Title: Title,
            Description: Description,
            Tag: Tag,
            Content: Content,
            id: "post-post",
            Button: "Đăng bài",
            caption: "Đăng bài và chuyển đến bài viết"
        });

    } else {
        next();
    }

});


// Xử lý Comment
router.post('/', function (req, res, next) {
    var query_string = url.parse(req.url, true).query.users;
    if (query_string == "comment") {
        mongoClient.connect(config.db.uri, function (err, db) {
            if (err) throw err;
            var dbo = db.db("TravelDB");
            var url = req.headers.referer.split(req.hostname);//thay đổi: hostname của từng trang server
            var rl = url[1].split("/")
            var cutid = url[1].split("id")
            var urlget = "?" + rl[1] + "=" + rl[2]
            var id = Number(cutid[1]);
            var query = {_id: id}

            dbo.collection("Post").find(query).toArray(function (err, result) { // Xử lý result trong comment
                if (err) throw err;
                if (result.length > 0) {
                    var listPost = result[0].Comments;
                    var username
                    if (req.session.user.name == false) {
                        username = "---ANONYMOUS---"
                    } else {
                        username = req.session.user.name
                    }
                    var newcomment = {
                        UserName: username,
                        Message: req.body.comment,
                        DateCreated: new Date().toLocaleString(),
                        Likes: 0
                    }
                    listPost.push(newcomment);

                    dbo.collection('Post').updateOne({Url: urlget},
                        {
                            $set: {
                                Comments: listPost
                            }
                        }
                    )
                    db.close();
                    res.redirect(urlget);
                } else {
                    res.redirect(urlget);
                }
            })
        });
    } else {
        next();
    }

});


// XỬ LÝ QUẢN LÝ THÔNG TIN CÁ NHÂN
// cau hinh multer
// var anh = [];
var md5 = require('md5');
var upload = multer({storage: storage, fileFilter: CheckfileUpload})
var storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, 'public/images/anh_user')
    },
    filename: function (req, file, cb) {
        cb(null, Date.now() + '-' + file.originalname);
    }
});

/// check file
function CheckfileUpload(req, file, cb) {
    if (file.originalname.match(/\.(jpg|png|gif|jpeg)$/)) {
        cb(null, true);
    } else {
        cb(new Error('Bạn chỉ được up file ảnh'));
    }
}

var upload = multer({storage: storage, fileFilter: CheckfileUpload})

router.get('/', function (req, res, next) {
    console.log("Request for " + url.parse(req.url, true).query.users + " received.");
    var query_string = url.parse(req.url, true).query.users;
    if (query_string == "personal-information") {
        var mongoClient = require("mongodb").MongoClient;
        const assert = require('assert');

        mongoClient.connect(config.db.uri, function (err, db) {
            var dbo = db.db("TravelDB");
            if (err) throw err;
            console.log('da ket noi thanh cong')
            dbo.collection("Member").find({}).toArray(function (err, result) {
                if (err) throw err;
                dbo.collection("Post").find({}).toArray(function (err, result2) {
                    var result2 = result2;

                    if (err) throw err;
                    // console.log(result2);

                    //ham xu li
                    var id = req.session.user.name;


                    console.log(typeof (id));
                    console.log(id + '//////////////////////////////////////////////////////////////////////////');
                    // var iduser = chuyenthanhObjectId(id);
                    // const findDocuments = function (dbo, callback) {
                    //     const collection = dbo.collection('Member');
                    //     collection.find({Email: id}).toArray(function (err, docs) {
                    //         assert.equal(err, null);
                    //         console.log("Tìm thấy !");
                    //         callback(docs);
                    //     });
                    // }

                    dbo.collection("Member").find({Email: id}).toArray(function (err, dulieu) {

                        // if (dulieu.toString() == '') {
                        //
                        //     next();
                        //
                        //
                        // }
                        // console.log(req.session.user.pass);
                        console.log(dulieu)
                        // var t = 0;
                        // if (req.session.user.pass != dulieu[0].password) {
                        //     t = t + 1;
                        //     req.session.user = {name : dulieu[0].email,pass : dulieu[0].password}
                        // }

                        if (dulieu[0].UserName == null) {
                            dulieu[0].UserName = "";
                        }
                        if (dulieu[0].Image == null) {
                            dulieu[0].Image = "";
                        }
                        if (dulieu[0].Company == null) {
                            dulieu[0].Company = "";
                        }
                        if (dulieu[0].Address == null) {
                            dulieu[0].Address = "";
                        }
                        if (dulieu[0].CityProvince == null) {
                            dulieu[0].CityProvince = "";
                        }
                        if (dulieu[0].Country == null) {
                            dulieu[0].Country = "";
                        }
                        if (dulieu[0].PostalCode == null) {
                            dulieu[0].PostalCode = "";
                        }
                        if (dulieu[0].Description == null) {
                            dulieu[0].Description = "";
                        }
                        if (dulieu[0].FriendList == null) {
                            dulieu[0].FriendList = [];
                        }
                        if (dulieu[0].FavoriteAuthorList == null) {
                            dulieu[0].FavoriteAuthorList = [];
                        }
                        if (dulieu[0].LovePostList == null) {
                            dulieu[0].LovePostList = [];
                        }


                        // console.log((dulieu[0].FavoriteAuthorList));
                        // console.log(typeof (dulieu[0].FavoriteAuthorList));
                        // console.log(result[4]._id);
                        // console.log(typeof (result[4]._id));
                        //lay cac id bai viet tu id_user
                        var baiviet = [];
                        for (var i = 0; i < result2.length; i++) {
                            for (var y = 0; y < (dulieu[0].LovePostList).length; y++) {
                                if (result2[i]._id == ((dulieu[0].LovePostList)[y])[0]) {
                                    baiviet.push(result2[i]);
                                }
                            }
                        }

                        console.log(baiviet);
                        var t = 0;
                        res.render("userpages/personal-information", {
                            domain: "thongtindulich.azurewebsites.net",
                            data: dulieu,
                            bigdata: result,
                            baiviet: baiviet,
                            t: t
                        });
                        // console.log(dulieu);

                    });


                    db.close();
                });
            });
        });

    } else next();
});

//post

router.post('/', upload.single("avatar"), function (req, res, next) {
    //
    var motanh = req.file;
    // console.log(motanh + '@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@');
    var upload = multer().single('avatar');


    upload(req, res, function (err) {
        if (err instanceof multer.MulterError) {
            res.redirect('/users/personal-information');
        } else if (err) {
            res.redirect('/users/personal-information');
        }

        // Everything went fine.
    });


    // console.log(motanh.path);
    // console.log(typeof(motanh.path));

    //
    console.log("Request for " + url.parse(req.url, true).query.users + " received.");
    var query_string = url.parse(req.url, true).query.users;
    if (query_string == "personal-information") {
        var mongoClient = require("mongodb").MongoClient;
        const assert = require('assert');
        // var chuyenthanhObjectId = require('mongodb').ObjectID;
        mongoClient.connect(config.db.uri, function (err, db) {
            if (err) throw err;
            var dbo = db.db("TravelDB");
            // const dbName = 'TravelDB';
            const url = 'mongodb://doanngoctai:8ib0BtxMJVcy3oqTXMcjX9nPxaBzRGvCodHY9EXLyVoLnGOxNbUFviXmfCq73F50Gwyek7LJ8Esvf7l7TCzn1Q%3D%3D@doanngoctai.documents.azure.com:10255/?ssl=true';
            console.log('da ket noi thanh cong')
            dbo.collection("Member").find({}).toArray(function (err, result) {
                dbo.collection("Post").find({}).toArray(function (err, result2) {
                    if (err) throw err;
                    // console.log(result);
                    //ham xu li

                    //them du lieu

                    // var dulieu = {
                    //   "Company" : req.body.Company ,
                    //   "UserName" : req.body.UserName ,
                    //   "Email" : req.body.Email,
                    //   "LastName" : req.body.LastName,
                    //   "FirstName" : req.body.FirstName,
                    //   "Address": req.body.Address,
                    //   "CityProvince" : req.body.CityProvince,
                    //   "Country" : req.body.Country,
                    //   "PostalCode" : req.body.PostalCode,
                    //   "Description" : req.body.Description
                    //
                    //
                    //
                    // };
                    // console.log(dulieu.Company);
                    // console.log(dulieu.UserName);
                    // console.log(dulieu.Email);
                    // const insertDocuments = function(db, callback) {
                    //   // Get the documents collection
                    //   const collection = db.collection('Member');
                    //   // Insert some documents
                    //   collection.insert(dulieu, function(err, result) {
                    //     assert.equal(err, null);
                    //     console.log("da them du lieu vao db");
                    //     callback(result);
                    //   });
                    // }
                    // // Use connect method to connect to the server
                    // mongoClient.connect(url, function(err, client) {
                    //   assert.equal(null, err);
                    //   console.log("Connected successfully to server");
                    //
                    //   const db = client.db(dbName);
                    //
                    //   insertDocuments(db, function() {
                    //     client.close();
                    //     res.redirect('/users/personal-information');
                    //   });
                    // });

                    // update du lieu

                    var id = req.session.user.name;
                    console.log(id);
                    // const findDocuments = function (dbo, callback) {
                    //     const collection = dbo.collection('Member');
                    //     collection.find({Email: id}).toArray(function (err, docs) {
                    //         assert.equal(err, null);
                    //         console.log("Tìm thấy !");
                    //         console.log('kkkkkkkkkk' + req.body.password1+ 'kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk')
                    //         callback(docs);
                    //     });
                    // }

                    dbo.collection("Member").find({Email: id}).toArray(function (err, nguoidung) {
                        console.log(nguoidung);
                        if (nguoidung[0].UserName == null) {
                            nguoidung[0].UserName = "";
                        }
                        if (nguoidung[0].Image == null) {
                            nguoidung[0].Image = "";
                        }
                        if (nguoidung[0].Company == null) {
                            nguoidung[0].Company = "";
                        }
                        if (nguoidung[0].Address == null) {
                            nguoidung[0].Address = "";
                        }
                        if (nguoidung[0].CityProvince == null) {
                            nguoidung[0].CityProvince = "";
                        }
                        if (nguoidung[0].Country == null) {
                            nguoidung[0].Country = "";
                        }
                        if (nguoidung[0].PostalCode == null) {
                            nguoidung[0].PostalCode = "";
                        }
                        if (nguoidung[0].Description == null) {
                            nguoidung[0].Description = "";
                        }
                        if (nguoidung[0].FriendList == null) {
                            nguoidung[0].FriendList = [];
                        }
                        if (nguoidung[0].FavoriteAuthorList == null) {
                            nguoidung[0].FavoriteAuthorList = [];
                        }
                        if (nguoidung[0].LovePostList == null) {
                            nguoidung[0].LovePostList = [];
                        }


                        if (motanh) {
                            var dulieu = {

                                "Image": ".." + (motanh.path).slice(6)


                            }
                        } else {


                            var dulieu = {
                                "Company": req.body.Company,
                                "UserName": req.body.UserName,
                                "Email": req.body.Email,
                                "LastName": req.body.LastName,
                                "FirstName": req.body.FirstName,
                                "Address": req.body.Address,
                                "CityProvince": req.body.CityProvince,
                                "Country": req.body.Country,
                                "PostalCode": req.body.PostalCode,
                                "Description": req.body.Description


                            }
                        }
                        // const updateDocument = function (dbo, callback) {
                        //     const collection = dbo.collection('Member');
                        //     collection.updateOne({Email: id}
                        //         , {$set: dulieu}, function (err, result) {
                        //             assert.equal(err, null);
                        //             assert.equal(1, result.result.n);
                        //             console.log("Updated the document ");
                        //             callback(result);
                        //         });
                        // }
                        //
                        //
                        // // Use connect method to connect to the server
                        //
                        // updateDocument(dbo, function () {
                        //     // db.close();
                        // });
                        dbo.collection("Member").updateOne({Email : id}, {$set: dulieu}, function(err, log) {
                            if (err) throw err;
                            console.log("1 document updated");

                        });
                        dbo.collection("Member").find({Email: id}).toArray(function (err, motuser) {
                            console.log(motuser);

                            if (motuser[0].FriendList == null) {
                                motuser[0].FriendList = [];
                            }
                            if (motuser[0].FavoriteAuthorList == null) {
                                motuser[0].FavoriteAuthorList = [];
                            }
                            if (motuser[0].LovePostList == null) {
                                motuser[0].LovePostList = [];
                            }
                        // Use connect method to connect to the server
                        // console.log(md5(req.body.password1));
                        // console.log(motuser[0].password);
                        var baiviet = [];
                        for (var i = 0; i < result2.length; i++) {
                            for (var y = 0; y < (motuser[0].LovePostList).length; y++) {
                                if (result2[i]._id == ((motuser[0].LovePostList)[y])[0]) {
                                    baiviet.push(result2[i]);
                                }
                            }
                        }
                        console.log('kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk')
                        if (!(req.body.password1) || !(req.body.password2) || !(req.body.password3)) {
                            var t = 1;
                            res.render("userpages/personal-information", {
                                domain: "thongtindulich.azurewebsites.net",
                                data: motuser,
                                bigdata: result,
                                baiviet: baiviet,
                                t: t

                            });
                            console.log('xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx')
                        } else if ((md5(req.body.password1) == motuser[0].password) && (md5(req.body.password1) == md5(req.body.password2))) {
                            dbo.collection("Member").updateOne({Email: id}, {$set: {password: md5(req.body.password3)}}, function (err, result) {
                                if (err) throw err;
                            })

                            db.close();
                            var t = 2;
                            res.render("userpages/personal-information", {
                                domain: "thongtindulich.azurewebsites.net",
                                data: motuser,
                                bigdata: result,
                                baiviet: baiviet,
                                t: t

                            });

                        } else {
                            var t = 3;
                            res.render("userpages/personal-information", {
                                domain: "thongtindulich.azurewebsites.net",
                                data: motuser,
                                bigdata: result,
                                baiviet: baiviet,
                                t: t

                            });
                            console.log('hhhhhhhhhhhhhhaaaaaaaaaaaaa')
                        }

                        // updateDocument(dbo, function () {
                        //     res.redirect('/users/personal-information');
                        // });


                    });
                    });

                });
            });

        });

    } else next();
});


/* GET users listing. */
router.get('/', function (req, res, next) {
    console.log("Request for " + url.parse(req.url, true).query.users + " received.");
    var query_string = url.parse(req.url, true).query.users;
    if (query_string != undefined && (query_string == "login" || query_string == "register" || query_string == "forgot-password")) {
        res.render("userpages/" + url.parse(req.url, true).query.users, {domain: "thongtindulich.azurewebsites.net"});
        console.log("***Request for " + url.parse(req.url, true).query.users + " received.");
    } else {
        res.render("pages/404", {domain: "thongtindulich.azurewebsites.net"});
        console.log("***Request for invalid page received.aaa");
    }
});
// router.post('/like', function (req, res) {
//     if (req.body.like == "like") //kiểm tra data gửi lên là like hay unlike
//     {
//         if (req.session.user) {   //nguoi dung truy cap
//             var url1 = req.url;
//             console.log("like")
//             mongoClient.connect(config.db.uri, function (err, db) { //kết nối tới DB
//                 if (err) throw err;
//                 var dbo = db.db("TravelDB");
//                 console.log("thanh cong");
//                 dbo.collections("Post").findOne({Url: req.url}, function (err, result) {
//                     if (err) {
//                         res.json({like: false})
//                         throw err
//                     }
//                     var li = result.Likes;                //xu ly tang so like +1 khi like thành công.
//                     li = li + 1;
//                     var myquery = {Url: url1};
//                     var newvalues = {$set: {Likes: li}};
//                     dbo.collection("Post").updateOne(myquery, newvalues, function (err, res) { //update
//                         if (err) {
//                             res.json({like: false})
//                             throw err
//                         }
//                         console.log("1 document updated");
//                         db.close();
//                     });
//
//                 })
//             });
//         } else {
//             res.json({like: false})
//         }
//     } else {
//         console.log("unlike")
//         if (req.session.user) {   //nguoi dung truy cap
//             console.log("unlike")
//             mongoClient.connect(config.db.uri, function (err, db) { //kết nối tới DB
//                 if (err) throw err;
//                 var dbo = db.db("TravelDB");
//                 console.log("thanh cong");
//                 dbo.collections("Post").findOne({Url: req.url}, function (err, result) {
//                     if (err) {
//                         res.json({unlike: false})
//                         throw err
//                     }
//                     var li = result.Likes;                //xu ly giam so like -1 khi bỏ like.
//                     li = li - 1;
//                     var myquery = {Url: url1};
//                     var newvalues = {$set: {Likes: li}};
//                     dbo.collection("Post").updateOne(myquery, newvalues, function (err, res) { //update
//                         if (err) {
//                             res.json({unlike: false})
//                             throw err
//                         }
//                         console.log("1 document updated");
//                         db.close();
//                     });
//
//                 })
//             });
//         }
//     }
// });
// router.post("/like", function (req, res) {
//     var like = req.body.like;
//     if (like == "like") {
//         if (req.session.user) {
//             req.url;
//             var name = req.session.user.name;
//             mongoClient.connect(config.db.uri, function (err, db) {
//                 if (err) throw err;
//                 var dbo = db.db("TravelDB");
//                 console.log("thanh cong");
//                 dbo.collections("Post").findOne({Url: req.url}, function (err, result) {
//                     if (err) throw err
//                     var li = result.Likes
//                     li = li + 1
//                     var myquery = {Url: req.url};
//                     var newvalues = {$set: {Likes: li}};
//                     dbo.collection("Post").updateOne(myquery, newvalues, function (err, res) {
//                         if (err) throw err;
//                         console.log("1 document updated");
//
//                         db.close();
//                     });
//
//                 })
//             });
//
//         }
//     } else {
//         if (req.session.user) {
//             var url1 = req.url;
//             var name = req.session.user.name
//             mongoClient.connect(config.db.uri, function (err, db) {
//                 if (err) {
//                     throw err
//                     res.json({static: false})
//                 }
//                 ;
//                 var dbo = db.db("TravelDB");
//                 console.log("thanh cong cmnr");
//                 dbo.collections("Post").findOne({Url: req.url}, function (err, result) {
//                     if (err) {
//                         throw err
//                         res.json({static: false})
//                     }
//                     ;
//                     var li = result.Likes
//                     li = li - 1
//                     var myquery = {Url: url1};
//                     var newvalues = {$set: {Likes: li}};
//                     dbo.collection("Post").updateOne(myquery, newvalues, function (err, res) {
//                         if (err) {
//                             throw err
//                             res.json({static: false})
//                         }
//                         ;
//                         console.log("1 document updated");
//                         res.json({static: true})
//                         db.close();
//                     });
//
//                 })
//             });
//
//         }
//     }
// })
module.exports = router;
