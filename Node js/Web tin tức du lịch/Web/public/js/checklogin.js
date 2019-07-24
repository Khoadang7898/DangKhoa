$(document).ready(function () {
    var va = getCookie("isL")
    console.log(document.cookie)
    if(va == "true")
    {
        document.getElementById("dh").innerHTML=''
        document.getElementById("dh").innerHTML=`<div class="user_box_login user_box_link"><a href="users?users=account-manager"><i class="fa fa-user" aria-hidden="true"></i> Quản lý Tài khoản</a></div>
           <div class="user_box_login user_box_link"><a href="/loginregister/logout"><i class="fa fa-sign-out" aria-hidden="true"></i> Đăng xuất</a></div>
           <div class="user_box_seen_post user_box_link"><a href="users?users=post-has-been-viewed"><i class="fa fa-eye" aria-hidden="true"></i> Bài viết Đã xem</a></div>
           <div class="user_box_love_post user_box_link"><a href="users?users=post-has-been-liked"><i class="fa fa-heart" aria-hidden="true"></i> Bài viết yêu thích</a></div>`
    }
})

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length,c.length);
        }
    }
    return "";
}