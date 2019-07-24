module.exports = {
    bestPostTemplate:"<!-- Post Item -->\n" +
        "<div class=\"col-lg-6 offers_col\">\n" +
        "    <div class=\"offers_item\">\n" +
        "        <div class=\"row\">\n" +
        "            <div class=\"col-lg-6\">\n" +
        "                <div class=\"offers_image_container\">\n" +
        "                    <div class=\"offers_image_background\"\n" +
        "                         style=\"background-image:url(<%=IconUrl%>)\"></div>\n" +
        "                    <div class=\"offer_name\"><a href=\"<%=Url%>\"><%=Title%></a></div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "            <div class=\"col-lg-6\">\n" +
        "                <div class=\"offers_content\">\n" +
        "                    <div class=\"offers_views\"><%=Views%><span>views</span></div>\n" +
        "                    <div class=\"offers_likes\"><%=Likes%><span>likes</span></div>\n" +
        "                    <div class=\"offers_shares\"><%=Shares%><span>shares</span></div>\n" +
        "                    <div class=\"rating_r rating_r_5 offers_rating\">\n" +
        "                        <i></i> <i></i> <i></i> <i></i> <i></i>\n" +
        "                    </div>\n" +
        "                    <p class=\"offers_text\">\n" +
        "                        <%=Description%>\n" +
        "                    </p>\n" +
        "                    <div class=\"offers_icons\">\n" +
        "                        <ul class=\"offers_icons_list\">\n" +
        "                            <li class=\"offers_icons_item\"><img src=\"images/post.png\" alt=\"\"></li>\n" +
        "                            <li class=\"offers_icons_item\"><img src=\"images/compass.png\" alt=\"\"></li>\n" +
        "                            <li class=\"offers_icons_item\"><img src=\"images/bicycle.png\" alt=\"\"></li>\n" +
        "                            <li class=\"offers_icons_item\"><img src=\"images/sailboat.png\" alt=\"\"></li>\n" +
        "                        </ul>\n" +
        "                    </div>\n" +
        "\n" +
        "                    <div class=\"button offers_link\">\n" +
        "                        <div class=\"button_bcg\"></div>\n" +
        "                        <a href=\"<%=Url%>\">Đọc thêm<span></span><span></span><span></span></a>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "</div>",
    recommendPostTemplate:"<!-- Footer blog item -->\n" +
        "                        <div class=\"footer_blog_item clearfix\">\n" +
        "                            <div class=\"footer_blog_image\"><img src=\"<%=IconUrl%>\"\n" +
        "                                                                alt=\"<%=Title%>\"></div>\n" +
        "                            <div class=\"footer_blog_content\">\n" +
        "                                <div class=\"footer_blog_title\"><a href=\"<%=Url%>\">Du lịch <%=Title%> với chúng tôi</a>\n" +
        "                                </div>\n" +
        "                                <div class=\"footer_blog_date\"><%=DateCreated%></div>\n" +
        "                            </div>\n" +
        "                        </div>"
};
