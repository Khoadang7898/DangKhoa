"use strict";
var chalk = require('chalk');
var express = require('express');
var router = express.Router();
var path = require('path');
var events = require('events');
var mongoClient = require('mongodb').MongoClient;
var home_page_partial = require(path.resolve('./modules/home_page_partials'));
var config = require(path.resolve('./config/config'));
var ejs = require('ejs');

/* GET home page.*/
router.get('/', function (req, res, next) {
    // Information of Router
    console.log(chalk.yellow("NOTIFY : ROUTER INFORMATION"));
    console.log(chalk.green("*Router : indexRouter"));
    console.log(chalk.green("*Process Url : "+req.url));
    // Create Session About Current Page
    req.session.current_page = req.url.substr(1); // Exclude first slash in url

    //------------------------------START PROCESS LOAD BEST-POST & RECOMMENED-POST-------------------------
    var best_post_items = "";
    var recommend_post_items = "";  //Limit = 3 Post has like highest.

    //Set event to load data
    var eventEmitter = new events.EventEmitter();

    // Create function for process event get data finished
    var initData = function () {
        //LOAD BEST POST ITEMS IN HOME PAGE
        res.render("pages/index", {
            domain: "thongtindulich.azurewebsite.net",
            best_post_items: best_post_items,
            recommend_post_items: recommend_post_items,
            post_route_1: "post?post=du-lich-da-lat",
            post_route_2: "post?post=du-lich-ha-noi",
            post_route_3: "post?post=du-lich-da-nang",
            post_route_4: "post?post=du-lich-hue",
            post_route_5: "post?post=kinh-nghiem-du-lich-da-lat",
            post_route_6: "post?post=du-lich-nha-trang",
            post_route_7: "post?post=du-lich-phan-thiet",
            post_route_8: "post?post=du-lich-quang-binh",
            post_route_9: "post?post=du-lich-sapa",
            post_route_10: "post?post=du-lich-vung-tau",
            post_route_img_1: "/images/icon-du-lich-da-lat.webp",
            post_route_img_2: "/images/icon-du-lich-ha-noi.webp",
            post_route_img_3: "/images/icon-du-lich-da-nang.webp",
            post_route_img_4: "/images/icon-du-lich-hue.webp",
            post_route_img_5: "/images/icon-kinh-nghiem-du-lich-da-lat.webp",
            post_route_img_6: "/images/icon-du-lich-nha-trang.webp",
            post_route_img_7: "/images/icon-du-lich-phan-thiet.webp",
            post_route_img_8: "/images/icon-du-lich-quang-binh.webp",
            post_route_img_9: "/images/icon-du-lich-sapa.webp",
            post_route_img_10: "/images/icon-du-lich-vung-tau.webp",
            post_title_1: "Đà Lạt",
            post_title_2: "Hà Nội",
            post_title_3: "Đà Nẵng",
            post_title_4: "Huế",
            post_title_5: "TIP Đà Lạt",
            post_title_6: "Nha Trang",
            post_title_7: "Phan Thiết",
            post_title_8: "Quảng Bình",
            post_title_9: "SaPa",
            post_title_10: "Vũng Tàu",
            post_author_1: "Administrator",
            post_author_2: "Administrator",
            post_author_3: "Administrator",
            post_author_4: "Administrator",
            post_author_5: "Administrator",
            post_author_6: "Administrator",
            post_author_7: "Administrator",
            post_author_8: "Administrator",
            post_author_9: "Administrator",
            post_author_10: "Administrator",
            post_description_1: "Bài viết giới thiệu nội dung : Ga Đà Lạt, Nhà thờ Con Gà, Thung lũng tình yêu, Hồ xuân hương, Đỉnh Langbiang, Quảng trường lâm viên, Chợ Đà Lạt.",
            post_description_2: "Bài viết giới thiệu nội dung : Phố cổ hà Nội, Cầu Long Biên, Chùa Hương, Làng gốm Bát Tràng và một số địa điểm ăn uống, nơi ở tốt nhất.",
            post_description_3: "Bài viết giới thiệu nội dung : Cù Lao Chàm, Bà Nà Hills, Bãi tắm Non nước, Làng chiếu Cẩm Nê, Biển Mỹ Khê, Bảo tàng điêu khắc Chăm, Mực rim me Đà Nẵng, Nai khô Đà Nẵng",
            post_description_4: "Bài viết giới thiệu nội dung : Khu vực thành nội (kinh thành Huế), Phá Tam Giang – Đầm Chuồn, Vịnh Lăng Cô, Địa chỉ quán ăn ngon - Quán bún giò heo - Bánh canh bà Đợi",
            post_description_5: "Bài viết giới thiệu nội dung : Phương tiện di chuyển, Thời điểm du lịch, Địa điểm du lịch nổi bật, Món ăn ngon, Trang phục phù hợp ở Đà Lạt",
            post_description_6: "Bài viết giới thiệu nội dung : Tứ Bình, Vinpearl Land, Đảo Hòn Mun, Đảo Hòn Tằm, Vịnh Ninh Vân, Bún chả cá, Bún sứa, Bánh ướt, Nem Ninh Hoà",
            post_description_7: "Bài viết giới thiệu nội dung : Trường Dục Thanh, Dinh Vạn Thủy Tú, Lầu Ông Hoàng, Khu vực xung quanh Mũi Né - Bãi Rạng - Hòn Rơm, Bánh tráng cuốn dẻo, Cá lồi xối mỡ, Gỏi ốc giác, Lẩu thả, Dông nướng",
            post_description_8: "Bài viết giới thiệu nội dung : Vườn Quốc Gia Phong Nha Kẻ Bàng, Động Thiên Đường,Hang Sơn Đoòng, Bãi biển Đá Nhảy, Nhà lưu niệm Đại tướng Võ Nguyên Giáp,..., Cháo hàu ở Quán Hàu, Bánh Khoái",
            post_description_9: "Bài viết giới thiệu nội dung : Nhà thờ đá cổ SaPa, Núi Hàm Rồng, Thung lũng Mường Hoa, Bản Cát Lát, Fansifan-nóc nhà Đông Dương, Chợ phiên Bắc Hà, Cá hồi tươi, Lợn cắp nách",
            post_description_10: "Bài viết giới thiệu nội dung : Bãi biển Vũng Tàu, Hải đăng Vũng Tàu, Tượng chúa Giêsu Kitô vua, Thích ca phật đài, Bảo tàng vũ khí Robert Taylor, Cánh đồng cừu suối Nghệ",
        });
    };

    //Bind event "finished" with initData handler
    eventEmitter.on("finished", initData);

    //Connect db
    mongoClient.connect(config.db.uri, {useNewUrlParser: true}, function (err, db) {
        if (err) throw err;
        var dbo = db.db("TravelDB");
        // Query data which had been sorted with "Likes" attribute
        dbo.collection("Post").find({}, {projection: {_id: 0, PostBy: 0, DateCreated: 0, Tags: 0, Rank: 0, Comments: 0, RelevantPostUrl: 0}}).sort({Likes: 1}).limit(10).toArray(function (err, result) {
            if (err) throw err;
            //GET DATA OF POST FORM DB AND USE PROJECTION TO FILTER ATTRIBUTES -> CONSTRUCT COMPLETED POST
            //Constraint : The best post which has number of like greater than 0
            var i;
            var len = result.length;
            for (i = 0; i < len; i++) {
                best_post_items += ejs.render(home_page_partial.bestPostTemplate, {Title: result[i].Title, Url: result[i].Url, IconUrl: result[i].IconUrl, Description: result[i].Description, Likes: result[i].Likes, Views: result[i].Views, Shares: result[i].Shares}) + "\n\n";
                if (i < 3) {
                    recommend_post_items += ejs.render(home_page_partial.recommendPostTemplate, {Title: result[i].Title, Url: result[i].Url, IconUrl: result[i].IconUrl, DateCreated: result[i].DateCreated}) + "\n\n";
                }
            }

            eventEmitter.emit("finished")
        });
        //close db
        db.close();
    });

    //------------------------------FINISH PROCESS LOAD BEST-POST & RECOMMENED-POST-------------------------


});
module.exports = router;
