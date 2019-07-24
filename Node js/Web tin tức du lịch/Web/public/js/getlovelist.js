$(document).ready(function () {
    console.log("okkkkkkkkkkkkkkkkkkkkkkkkkk")
   var name = getCookie("user")
    console.log(name)
    var email = getEmail(name)
    console.log("user ->>>>>>> "+email)
    $.ajax({
        url: '/loginregister/lovelist',
        data: {
            email: email
        },
        error: function() {
            // $('#info').html('connect');
        },
        dataType: 'json',
        success: function(data) {
            //var key =[]

            //$('#info').html(toString(data.data))
            console.log(data.data)
            data.data.forEach(function (h) {
               console.log(h)

                $('#lovelist').append(
                    `  <tr>
                                                        <td>
                                                            ${h.Title}
                                                        </td>
                                                        <td>
                                                            ${h.PostBy}
                                                        </td>
                                                        <td>
                                                            ${h.DateCreated}
                                                        </td>
                                                        <td>
                                                            ${h.DateCreated}
                                                        </td>
                                                    <tr>`)
            })

        },
        type: 'POST'
    });
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
function getEmail(cname) {

    var ca = cname.split('%40');
    return ca[0]+"@"+ca[1];
}