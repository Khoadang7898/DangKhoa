$(document).ready(() => {
    $("#numberlike").load("?query=likes-number", function (result, status, xhr) {
        console.log("Likes number : " + result);
    });
    $("#numbershare").load("?db_query=shares-number", function (result) {
        console.log("Shares number : " + result);
    });
    $.get("?db_query=rank-number", function (avgRank, textStatus, jqXHR) {
        avgRank=parseInt(avgRank);
        console.log("Average Rank : " + avgRank);
        if (avgRank <= 1) {
            $("#star_icon_lists").children().removeClass().addClass("rating rating_1");
            $("#star_icon_lists").children().children("#star1").removeClass().addClass("fa fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star2").removeClass().addClass("far fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star3").removeClass().addClass("far fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star4").removeClass().addClass("far fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star5").removeClass().addClass("far fa-star  fa-5x");
        } else if (avgRank <= 2) {
            $("#star_icon_lists").children().removeClass().addClass("rating rating_2");
            $("#star_icon_lists").children().children("#star1").removeClass().addClass("fa fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star2").removeClass().addClass("fa fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star3").removeClass().addClass("far fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star4").removeClass().addClass("far fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star5").removeClass().addClass("far fa-star  fa-5x");
        } else if (avgRank <= 3) {
            $("#star_icon_lists").children().removeClass().addClass("rating rating_3");
            $("#star_icon_lists").children().children("#star1").removeClass().addClass("fa fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star2").removeClass().addClass("fa fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star3").removeClass().addClass("fa fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star4").removeClass().addClass("far fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star5").removeClass().addClass("far fa-star  fa-5x");
        } else if (avgRank <= 4) {
            $("#star_icon_lists").children().removeClass().addClass("rating rating_4");
            $("#star_icon_lists").children().children("#star1").removeClass().addClass("fa fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star2").removeClass().addClass("fa fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star3").removeClass().addClass("fa fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star4").removeClass().addClass("fa fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star5").removeClass().addClass("far fa-star  fa-5x");
        } else {
            $("#star_icon_lists").children().removeClass().addClass("#rating rating_5");
            $("#star_icon_lists").children().children("#star1").removeClass().addClass("fa fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star2").removeClass().addClass("fa fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star3").removeClass().addClass("fa fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star4").removeClass().addClass("fa fa-star  fa-5x");
            $("#star_icon_lists").children().children("#star5").removeClass().addClass("fa fa-star  fa-5x");
        }
    });
});
