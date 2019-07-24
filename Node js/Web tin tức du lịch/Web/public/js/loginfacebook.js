window.fbAsyncInit = function() {
    FB.init({
        appId      : '1294847024015348', // App ID
        channelURL : '', // Channel File, not required so leave empty
        status     : true, // check login status
        cookie     : true, // enable cookies to allow the server to access the session
        oauth      : true, // enable OAuth 2.0
        xfbml      : false  // parse XFBML
    });
};
// logs the user in the application and facebook
function login(){
    FB.getLoginStatus(function(r){
        if(r.status === 'connected'){
            //  window.location.href = '/';
        }else{
            FB.login(function(response) {
                if(response.authResponse) {
                    console.log(response.authResponse.accessToken)
                    console.log(response.authResponse.userID)
                    var data = {}

                    FB.api(
                        '/me',
                        'GET',
                        {"fields":"id,name,first_name,last_name,email,about"},
                        function(response) {

                            console.log(response.email)
                            $.ajax({
                                url: '/loginregister/loginFB',
                                data: {
                                    email: response.email,
                                    first_name: response.first_name,
                                    id: response.id,
                                    last_name: response.last_name,
                                    name: response.name
                                },
                                error: function() {
                                    // $('#info').html('connect');
                                },
                                dataType: 'json',
                                success: function(data) {
                                    //var key =[]

                                    //$('#info').html(toString(data.data))
                                    console.log(data.data)
                                    if(data.data) {
                                        window.location = "http://localhost:8080"+getCookie("urlold")
                                    }

                                },
                                type: 'POST'
                            });
                        }
                    );


                }

                else {
                    // user is not logged in
                }
            },{scope:'email'}); // which data to access from user profile
        }
    });
}
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

// Load the SDK Asynchronously
// (function() {
//     var e = document.createElement('script'); e.async = true;
//     e.src = document.location.protocol + '//connect.facebook.net/en_US/all.js';
//     document.getElementById('fb-root').appendChild(e);
// }());

