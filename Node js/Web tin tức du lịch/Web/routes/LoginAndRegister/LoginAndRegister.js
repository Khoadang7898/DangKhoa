var express = require('express');
var router = express.Router();
var url = require("url");
const {check, validationResult} = require('express-validator')
var md5 = require('md5');
var mongoClient = require("mongodb").MongoClient;
var config = require('../../config/config')
var nodemailer = require('nodemailer');
var autoIn = require("mongodb-autoincrement");
var chalk = require("chalk");

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
                var urlget = "?" + rl[1] + "=" + rl[2]

                //khúc trên là lấy ra url của bài đăng vd post?post=du-lich-nha-trang
                //nếu like share thì up
                dbo.collection("Post").find({Url: urlget}).toArray(function (err, result) {//tìm bài đăng với urlget
                    if (err) {
                        res.json({love: false})
                        throw err
                    }
                    // console.log(result)
                    if (result.length > 0) {


                        var idPost = result[0]._id

                        //Lấy ra danh sách yêu thích trong Member -> like share sẽ lấy số like share trong Post
                        dbo.collection("Member").find({Email: req.session.user.name}).toArray(function (err, result) {
                            if (err) {
                                res.json({love: false})
                                throw err
                            }

                            var list = result[0].ListPostLove//lấy danh sách love ->like share thì lấy số like vd like result[0].Likes
                            if (Array.isArray(list)) {
                                list.push((idPost))//thêm love like share thì tăng 1 khúc dưới chỉ khác chỗ này là giảm 1
                            } else {

                                list = []
                                list.push((idPost))
                            }
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
                    } else {
                        req.json({love: false})
                    }
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
                // console.log(req)
                var urlPost = req.headers.referer.split("http://localhost:8080")
                var rl = urlPost[1].split("/")
                console.log(rl)

                dbo.collection("Post").find({Url: "?" + rl[1] + "=" + rl[2]}).toArray(function (err, result) {
                    if (err) {
                        res.json({unlove: false})
                        throw err
                    }
                    var idPost = result[0]._id
                    dbo.collection("Member").find({Email: req.session.user.name}).toArray(function (err, result) {
                        if (err) {
                            res.json({unlove: false})
                            throw err
                        }
                        var list = result[0].ListPostLove
                        var newlist = []
                        if (Array.isArray(list)) {
                            list.forEach(function (idp) {
                                if (idp != idPost) {
                                    newlist.push((idp))
                                }
                            })
                        }


                        dbo.collection("Member").updateOne({Email: req.session.user.name}, {$set: {ListPostLove: newlist}}, function (err, result) {
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

router.post("/checklove", function (req, res) {
    if (req.session.view.isLogin) {
        mongoClient.connect(config.db.uri, function (err, db) {
            if (err) {
                res.json({data: false})
                throw err
            }
            var dbo = db.db("TravelDB")
            // console.log(req)
            var urlPost = req.headers.referer.split("http://localhost:8080")
            var rl = urlPost[1].split("/")
            console.log(rl)

            dbo.collection("Post").find({Url: "?" + rl[1] + "=" + rl[2]}).toArray(function (err, result) {
                if (err) {
                    res.json({data: false})
                    throw err
                }
                console.log(result)
                if (result.length > 0) {
                    var idPost = result[0]._id
                    dbo.collection("Member").find({Email: req.session.user.name}).toArray(function (err, result) {
                        if (err) {
                            res.json({data: false})
                            throw err
                        }

                        var list = result[0].ListPostLove
                        console.log(list)
                        db.close()
                        console.log("ID Póst " + idPost)
                        if (Array.isArray(list)) {
                            var bb = true
                            list.forEach(function (id) {

                                console.log(id)
                                if (idPost == id) {
                                    if (bb) {
                                        res.json({data: true})
                                        bb = false
                                    }
                                }

                            })

                        }

                    })
                }
            })

        })
    } else {
        res.json({data: false})
    }
})

router.post("/reset-password", function (req, res) {
    console.log(req.body.password)
    console.log(req.session.resetpassword.Email)
    var newpass = md5(req.body.password)
    mongoClient.connect(config.db.uri, function (err, db) {
        if (err) throw err;
        var dbo = db.db("TravelDB");
        dbo.collection("Member").updateOne({Email: req.session.resetpassword.Email}, {$set: {password: newpass}}, function (err, result) {
            if (err) {
                res.json({data: false})
                throw err
            }
            console.log("1 document updated");
            res.json({data: true})
            db.close();
        });
    })
})
router.get("/reset-password", function (req, res) {
    console.log(req.query)
    mongoClient.connect(config.db.uri, function (err, db) {
        if (err) throw err;
        var dbo = db.db("TravelDB");
        var myobj = {Email: req.query.p, password: req.query.e};
        dbo.collection("Member").find(myobj).toArray(function (err, result) {
            if (err) {
                res.render("pages/404", {domain: "thongtindulich.azurewebsites.net"});
                throw err
            }

            if (result.length > 0) {
                req.session.resetpassword = {
                    Email: req.query.p
                }
                res.render('userpages/reset-password', {domain: "thongtindulich.azurewebsites.net"})
            } else {
                res.render("pages/404", {domain: "thongtindulich.azurewebsites.net"});
            }

        })
    })

})
router.post('/forgotPassword', function (req, res) {

    var email = req.body.email
    mongoClient.connect(config.db.uri, function (err, db) {
        if (err) throw err;
        var dbo = db.db("TravelDB");
        var myobj = {Email: email};
        dbo.collection("Member").find(myobj).toArray(function (err, result) {
            if (err) throw err


            if (result.length > 0) {
                var transporter = nodemailer.createTransport({
                    service: 'gmail',
                    auth: {
                        user: 'khoahoc200598@gmail.com',
                        pass: 'Khoahuyen04122018'
                    }
                });
                console.log("Emmmmmmmmmmm->" + req.body.email)
                var mailOptions = {
                    from: 'khoahoc200598@gmail.com',
                    to: req.body.email,
                    subject: 'Reset password >>>>>>>>>>>>>>>thongtindulich.azurewebsites.net<<<<<<<<<<<<<<<<<',
                    text: 'Vui lòng cập nhập lại PassWord của bạn theo link: http://localhost:8080/loginregister/reset-password?e=' + result[0].password + '&p=' + req.body.email + ''
                };
                transporter.sendMail(mailOptions, function (error, info) {
                    if (error) {
                        console.log(error);
                    } else {
                        console.log('Email sent: ' + info.response);
                        res.json({data: true})
                    }
                });


            } else {
                console.log("rpppppp->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
                res.json({data: false})
            }

        })
    })
})
router.post("/lovelist", function (req, res, next) {
    console.log("post love list " + req.body.email)
    mongoClient.connect(config.db.uri, function (err, db) {
        if (err) throw err
        var email = req.session.user.name

        var myquery = {Email: email}
        // console.log(myquery)
        dbo = db.db("TravelDB")
        dbo.collection("Member").find(myquery).toArray(function (err, result) {
            if (err) throw err
            if (result.length > 0) {

                result.forEach(function (data) {
                    var list = []
                    if (Array.isArray(data.ListPostLove)) {
                        data.ListPostLove.forEach(function (id) {
                            var i = (id)
                            list.push({_id: i})
                        })
                    }
                    if (list.length > 0) {
                        dbo.collection("Post").find({$or: list}).toArray(function (err, result) {
                            if (err) throw err
                            res.json({data: result})
                            db.close()
                        })
                    } else {
                        res.json({data: []})
                        db.close()
                    }


                })


            }
        })
    })
})

router.post('/loginFB', function (req, res) {
    console.log(req.body.email)
    var email = req.body.email;
    var password = req.body.id;
    console.log(email);
    console.log(password);
    console.log(config.db.uri)
    mongoClient.connect(config.db.uri, function (err, db) {
        if (err) throw err;
        var dbo = db.db("TravelDB");
        var myobj = {Email: email, password: password};
        dbo.collection("Member").find(myobj).toArray(function (err, result) {
            if (err) throw err
            var firstname = req.body.first_name;
            var lastname = req.body.last_name;
            if (result.length > 0) {
                req.session.user.id = result[0]._id
                res.cookie("id", result[0]._id, {maxAge: 90000000, httpOnly: true})
                console.log("đã có->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
                req.session.view.isLogin = true
                req.session.user.name = email
                res.cookie('user', email, {maxAge: 90000000})
                res.cookie('isL', 'true', {maxAge: 90000000})
                res.cookie('urlold', req.session.urlOld, {maxAge: 90000000})
                console.log(req.session.user.id)
                res.json({data: true})
            } else {
                console.log("them->>>>>>>>0>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
                autoIn.getNextSequence(dbo, "Member", function (err, autiIndex) {
                    if (err) throw err
                    var q = {_id: autiIndex, Email: email, password: password, FirstName: firstname, LastName: lastname};
                    console.log(q)
                    dbo.collection('Member').insertOne(q, function (err, res) {
                        if (err) throw err;
                        req.session.view.isLogin = true
                        req.session.user.name = email
                        res.cookie('user', email, {maxAge: 90000000})
                        res.cookie('isL', 'true', {maxAge: 90000000})
                        res.cookie('urlold', req.session.urlOld, {maxAge: 90000000})
                        res.cookie("id", res.ops[0]._id, {maxAge: 90000000, httpOnly: true})
                        req.session.user.id = res.ops[0]._id
                        res.json({data: true})
                        console.log('inserted');
                        db.close()
                    })
                })

            }
        });
    })

    //res.redirect('/')
})


function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}


router.get('/login', function (req, res) {
    // Information of Router
    console.log(chalk.yellow("NOTIFY : ROUTER INFORMATION"));
    console.log(chalk.green("*Router : loginRouter"));
    console.log(chalk.green("*Process Url : " + req.url));

    // console.log(req)
    req.session.urlOld = req.headers.referer.split('http://localhost:8080')[1]
    console.log(req.session.urlOld)
    res.render('userpages/login', {domain: "thongtindulich.azurewebsites.net"})
})


router.get('/register', function (req, res) {
    // Information of Router
    console.log(chalk.yellow("NOTIFY : ROUTER INFORMATION"));
    console.log(chalk.green("*Router : registerRouter"));
    console.log(chalk.green("*Process Url : " + req.url));

    res.render('userpages/register', {domain: "thongtindulich.azurewebsites.net", notic: false})
})

router.get('/logout', function (req, res, next) {
    // Information of Router
    console.log(chalk.yellow("NOTIFY : ROUTER INFORMATION"));
    console.log(chalk.green("*Router : logoutRouter"));
    console.log(chalk.green("*Process Url : " + req.url));

    req.session.view.isLogin = false
    res.cookie('isL', 'false', {maxAge: 90000000})
    res.cookie('user', 'false', {maxAge: 90000000})
    res.cookie('id', 'false', {maxAge: 90000000, httpOnly: true})
    res.redirect(req.headers.referer.split('http://localhost:8080')[1])
})

router.get('/logout-in-account', function (req, res, next) {
    // Information of Router
    console.log(chalk.yellow("NOTIFY : ROUTER INFORMATION"));
    console.log(chalk.green("*Router : logout-in-accountRouter"));
    console.log(chalk.green("*Process Url : " + req.url));

    req.session.view.isLogin = false
    res.cookie('isL', 'false', {maxAge: 90000000})
    res.cookie('user', 'false', {maxAge: 90000000})
    res.cookie('id', 'false', {maxAge: 90000000, httpOnly: true})
    res.redirect("/")
})
router.post('/login', function (req, res, next) {
    // Information of Router
    console.log(chalk.yellow("NOTIFY : ROUTER INFORMATION"));
    console.log(chalk.green("*Router : loginRouter"));
    console.log(chalk.green("*Process Url : " + req.url));

    if (!req.session.views) {
        req.session.views = {};
    }
    var email = req.body.email;
    var password = md5(req.body.password);

    // Information of Account
    console.log(chalk.yellow("NOTIFY : ACCOUNT INFORMATION"));
    console.log(chalk.green("*Email : ")+chalk.cyan(email));
    console.log(chalk.green("*Password : ") + chalk.cyan(password));

    mongoClient.connect(config.db.uri, function (err, db) {
        if (err) throw err;
        var dbo = db.db("TravelDB");
        var myobj = {Email: email, password: password};
        // console.log(myobj)
        dbo.collection("Member").find(myobj).toArray(function (err, result) {
            if (err) throw err
            if (result.length > 0) {
                FirstName = result[0].FirstName;
                var id = result[0]._id
                // console.log("------------------------->" + FirstName)
                req.session.views['FirstName'] = FirstName;
                // console.log("okokokokoko->>>>>>>>0>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
                req.session.view.isLogin = true
                req.session.user = {}
                req.session.user = {name: email, id: id}
                res.cookie("id", id, {maxAge: 9000000, httpOnly: true})
                res.cookie('user', email, {maxAge: 90000000})
                res.cookie('isL', "true", {maxAge: 90000000})

                res.json({data: req.session.urlOld})
                //  res.render('userpages/register',{
                //    domain: "thongtindulich.azurewebsites.net",notic:false
                //})
            } else {
                // console.log("rpppppp->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
                res.json({data: false})
            }
        })


    });

})
router.post('/register', function (req, res) {
    // Information of Router
    console.log(chalk.yellow("NOTIFY : ROUTER INFORMATION"));
    console.log(chalk.green("*Router : registerRouter"));
    console.log(chalk.green("*Process Url : " + req.url));

    var firstname = req.body.firstname;
    var lastname = req.body.lastname;
    var email = req.body.email;
    var password = md5(req.body.password);
    var comfirmpass = md5(req.body.confirmpass);

    // Account information
    console.log(chalk.yellow("NOTIFY : ACCOUNT INFORMATION"));
    console.log(chalk.green("*Email : ")+chalk.cyan(email));
    console.log(chalk.green("*Password : ") + chalk.cyan(password));
    console.log(chalk.green("*Confirm Password : ") + chalk.cyan(comfirmpass));
    console.log(chalk.green("*FirstName : ") + chalk.cyan(firstname));
    console.log(chalk.green("*LastName: ") + chalk.cyan(lastname));

    if (password != comfirmpass) {
        res.render('userpages/register', {
            notic: 'Mật khẩu không trùng', domain: "thongtindulich.azurewebsites.net"
        })
    } else {
        mongoClient.connect(config.db.uri, function (err, db) {
            if (err) throw err;
            var dbo = db.db("TravelDB");
            var query = {Email: email}
            dbo.collection("Member").find(query).toArray(function (err, result) {
                if (err) throw err;

                if (result.length > 0) {
                    res.render('userpages/register', {
                        notic: 'Email đã tồn tại!', domain: "thongtindulich.azurewebsites.net"
                    })
                } else {
                    autoIn.getNextSequence(dbo, "Member", function (err, autiIndex) {
                        if (err) throw err
                        var myobj = {_id: autiIndex, Email: email, password: password, FirstName: firstname, LastName: lastname};
                        dbo.collection('Member').insertOne(myobj, function (err, res) {
                            if (err) throw err;
                            req.session.user.id = res.ops[0]._id
                            res.cookie("id", res.ops[0]._id, {httpOnly: true, maxAge: 90000000})
                            console.log(chalk.blue("*Action : ")+chalk.green("Insert"));
                            console.log(chalk.green("*res.ops : ")+chalk.green(res.ops));
                            //

                            var transporter = nodemailer.createTransport({
                                service: 'gmail',
                                auth: {
                                    user: 'khoahoc200598@gmail.com',
                                    pass: 'Khoahuyen04122018'
                                }
                            });
                            console.log(chalk.green("*Email : ")+chalk.cyan(email));
                            var mailOptions = {
                                from: 'khoahoc200598@gmail.com',
                                to: email,
                                subject: 'Đăng ký thành công >>>>>>>>>>>>>>>thongtindulich.azurewebsites.net<<<<<<<<<<<<<<<<<',
                                text: 'Bạn đã đăng ký thành công ! ' +
                                    'Tài khoản: ' + email + " ; password: " + req.body.password
                            };
                            transporter.sendMail(mailOptions, function (error, info) {
                                if (error) throw error;
                                else {
                                    console.log(chalk.green('*Email sent: ' + info.response));
                                    // res.json({data: true})
                                }
                            });


                            //
                            db.close()
                        });
                        req.session.view.isLogin = true
                        req.session.user = {}
                        req.session.user = {name: email}
                        res.cookie('isL', "true", {maxAge: 90000000})
                        res.cookie('user', email, {maxAge: 90000000})
                        res.redirect(req.session.urlOld)
                    })
                }

            })
            console.log(chalk.yellow('NOTIFY : Connect successfully'));


        })
    }
});

module.exports = router;
