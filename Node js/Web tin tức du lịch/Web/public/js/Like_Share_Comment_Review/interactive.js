document.getElementById("like_icon").addEventListener("click", like_process);
var like_icon = document.getElementById("like_icon");

<!-- Script Like-->

function like_process() {
    var id_like_state = like_icon.firstChild.id;

    if (id_like_state == "like") {
        $.ajax({
            type: "POST",
            url: "/test_interactive/like",
            data: {
                like: "unlike"
            },
            // contentType: "application/json; charset=utf-8",
            dataType: "json",
            beforeSend: function (xhr) {
                var number = parseInt($("#numberlike").text()) - 1
                $("#like_icon").html(`<i id="unlike" class="far fa-thumbs-up fa-5x"></i>
                <sup><span id="numberlike" class="badge badge-primary badge-pill">0</span>
                </sup>`)
                $('#numberlike').html('')
                $('#numberlike').append(number)
                console.log($("#numberlike").text())
                // document.getElementById("like_icon").innerHTML = "<i id=\"unlike\" class=\"far fa-thumbs-up fa-5x\"></i> </i> <sup><span id=\"numberlike\" class=\"badge badge-primary badge-pill\"></span>\n" +
                //     "                </sup>"
                // document.getElementById("numberlike").innerHTML = parseInt(document.getElementById("numberlike")) - 1;

            },
            success: function (data) {
                if (data.unlike) {
                    console.log("like dc chưa?")
                }
            },
            failure: function (errMsg) {
            }
        });
    } else {
        $.ajax({
            type: "POST",
            url: "/test_interactive/like",
            data: {
                like: "like"
            },
            // contentType: "application/json; charset=utf-8",
            dataType: "json",
            beforeSend: function (xhr) {
                var number = parseInt($("#numberlike").text()) + 1
                $("#like_icon").html(`<i id="like" class="fas fa-thumbs-up fa-5x"></i>
                <sup><span id="numberlike" class="badge badge-primary badge-pill">0</span>
                </sup>`)
                $('#numberlike').html('')
                $('#numberlike').append(number)
                console.log($("#numberlike").text())
            },
            success: function (data) {
                if (data.like) {
                    Console.log("Like rồi!!!")
                }
            },
            failure: function (errMsg) {
            }
        });
    }
}

<!-- Script Share-->

function Share() {
    var txt;
    var person = prompt("Copy link chia sẻ tại đây: ", window.location.href);
    if (person == null || person == "") {
    } else {
        var share_icon = $("#share_icon")
        $.ajax({
            type: "POST",
            url: "/test_interactive/share",
            data: {
                share: "share"
            },
            dataType: "json",
            beforeSend: function (xhr) {
                var number = parseInt($("#numbershare").text()) + 1
                $("#share_icon_icon").html(`<sup><span id="numbershare" class="badge badge-primary badge-pill">0</span></sup>`)
                $('#numbershare').html('')
                $('#numbershare').append(number)
                console.log($("#numbershare").text())
            },
            success: function (data) {
                if (data.share) {
                    console.log("Share rồi!!!")
                }
            },
            failure: function (errMsg) {
            }
        });
    }
    $("#numbershare").html(txt)
}

<!--Script Rank-->

var startstar = 1;
function process(star) {
    if (Number(startstar) > Number(star)) {
        for (i = star + 1; i < startstar + 1; i++) {
            let id = "#star";
            let far = "far fa-star fa-5x";
            id += String(i);
            $(id).removeClass().addClass(far);
            console.log(id)
        }
    }
    for (i = 1; i < star + 1; i++) {
        var rating = "rating rating_";
        var fa = "fa fa-star fa-5x";
        var id = "#star";
        id += String(i);
        rating += String(i);
        $(id).removeClass().addClass(fa);
    }
    $('#rating').removeClass().addClass(rating);
    startstar = star;
}

function Checkstar(star) {
    console.log(star)
    $.ajax({
        type: "POST",
        url: "/test_interactive/star",
        data: {
            star: star
        },
        dataType: "json",
        success: function (data) {
        },
        failure: function (errMsg) {
        }
    });
}


// document.getElementById("share_icon").addEventListener("click", share_process);
// var share_icon = document.getElementById("share_icon");
//
// function share_process() {
//     // var id_share_state = share_icon.firstChild.id;
//     // console.log(id_share_state)
//     $.ajax({
//         type: "POST",
//         url: "/test_interactive/share",
//         data: {
//             share: "share"
//         },
//         dataType: "json",
//         beforeSend: function (xhr) {
//             var number = parseInt($("#numbershare").text()) + 1
//             $("#share_icon_icon").html(`<sup><span id="numbershare" class="badge badge-primary badge-pill">0</span></sup>`)
//             $('#numbershare').html('')
//             $('#numbershare').append(number)
//             console.log($("#numbershare").text())
//         },
//         success: function (data) {
//             if (data.share) {
//                 console.log("Share rồi!!!")
//             }
//         },
//         failure: function (errMsg) {
//         }
//     });
// }



