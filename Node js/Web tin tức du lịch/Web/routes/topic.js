var express = require('express');
var router = express.Router();
var url = require('url');
var chalk = require('chalk');
var mongoClient = require('mongodb').MongoClient;
var path = require('path');
var config = require(path.resolve('./config/config'));
var assert = require('assert');
var fs = require('fs');

router.post('/', (req, res,next) => {
    // Information of Router
    console.log(chalk.yellow("NOTIFY : ROUTER INFORMATION"));
    console.log(chalk.green("*Router : topicRouter"));
    console.log(chalk.green("*Process Url : " + req.url));
    // Create Session About Current Page
    req.session.current_page = req.url.substr(1); //Exclude first slash
    // Get querystring
    var query_string = url.parse(req.url, true).query.topic;
    if (query_string == "news") {
        console.log("***Request for " + url.parse(req.url, true).query.topic + " received.");
        //    Process
        mongoClient.connect(config.db.uri, (err, db) => {
            if (err) throw err;
            var dbo = db.db("TravelDB");
            var collection = dbo.collection("Post");
            collection.find({}).toArray((err, result) => {
                if (err) throw err;
                let new_post ="";
                if(result!=undefined && result!=null){
                    for (var i=0;i<result.length;i++){
                        if (result[i]._id<=10){
                            new_post+="<div class=\"blog_post\">\n" +
                                "                            <div class=\"blog_post_image\">\n" +
                                "                                <img src=\""+result[i].IconUrl+"\" alt=\""+result[i].Title+"\">\n" +
                                "                                <div class=\"blog_post_date d-flex flex-column align-items-center justify-content-center\">\n" +
                                "                                    <div class=\"blog_post_day\">"+result[i].DateCreated.getDay()+"</div>\n" +
                                "                                    <div class=\"blog_post_month\">"+result[i].DateCreated.getMonth()+","+result[i].DateCreated.getFullYear()+" </div>\n" +
                                "                                </div>\n" +
                                "                            </div>\n" +
                                "                            <div class=\"blog_post_meta\">\n" +
                                "                                <ul>\n" +
                                "                                    <li class=\"blog_post_meta_item\"><a href=\"\">"+result[i].PostBy+"</a></li>\n" +
                                "                                    <li class=\"blog_post_meta_item\"><a href=\"\">"+result[i].Tags[0]+"</a></li>\n" +
                                "                                    <li class=\"blog_post_meta_item\"><a href=\"\">"+result[i].Comments.length+" Comments</a></li>\n" +
                                "                                </ul>\n" +
                                "                            </div>\n" +
                                "                            <div class=\"blog_post_title\"><a href=\""+result[i].Url+"\">"+result[i].FullTitle+"</a></div>\n" +
                                "                            <div class=\"blog_post_text\">\n" +
                                "                                <p>\n" +
                                "                                    "+result[i].Description+"\n" +
                                "                                </p>\n" +
                                "                            </div>\n" +
                                "                            <div class=\"blog_post_link\"><a href=\""+result[i].Url+"\">Đọc thêm</a></div>\n" +
                                "                        </div>";
                        }

                    }
                    res.send(new_post);
                }
                else{
                    res.send("ERROR : Result is empty !");
                }
                db.close();
            });
        });

    } else {
        next();
    }
});

router.get('/', (req, res) => {
    //Get configuration
    console.log(chalk.green.bgCyan("=>URL WAS PROCESSED : " + req.url));
    console.log(chalk.green("Time : " + new Date().toLocaleString()));
    console.log(chalk.green("Request for " + chalk.blue(url.parse(req.url, true).query.topic) + " received."));
    if ( url.parse(req.url, true).query.topic == 'news' ||url.parse(req.url, true).query.topic == 'contact' || url.parse(req.url, true).query.topic == 'about-us') {
        res.render("pages/" + url.parse(req.url, true).query.topic, {
            domain: "thongtindulich.azurewebsite.net",
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
            post_title_1: "Du lịch Đà Lạt",
            post_title_2: "Du lịch Hà Nội",
            post_title_3: "Du lịch Đà Nẵng",
            post_title_4: "Du lịch Huế",
            post_title_5: "Kinh nghiệm du lịch Đà Lạt",
            post_title_6: "Du lịch Nha Trang",
            post_title_7: "Du lịch Phan Thiết",
            post_title_8: "Du lịch Quảng Bình",
            post_title_9: "Du lịch SaPa",
            post_title_10: "Du lịch Vũng Tàu",
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
            post_description_10:"Bài viết giới thiệu nội dung : Bãi biển Vũng Tàu, Hải đăng Vũng Tàu, Tượng chúa Giêsu Kitô vua, Thích ca phật đài, Bảo tàng vũ khí Robert Taylor, Cánh đồng cừu suối Nghệ",
        });
        console.log("***Request for " + url.parse(req.url, true).query.topic + " received.");
    } else {
        res.render("pages/404", {domain: "thongtindulich.azurewebsites.net"});
        console.log("***Request for invalid page received.***");
    }
});

module.exports = router;


