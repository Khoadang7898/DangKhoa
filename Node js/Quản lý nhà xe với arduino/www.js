
var http = require('http');
var express = require('express'); 
var socketio = require('socket.io') 

var ip = require('ip');
var app = express(); 
var server = http.Server(app)
var path = require('path');
var io = socketio(server); 

var webapp_nsp = io.of('/web') 
var esp8266_nsp = io.of('/esp8266') 

var middleware = require('socketio-wildcard')(); //Để có thể bắt toàn bộ lệnh!
esp8266_nsp.use(middleware); 
webapp_nsp.use(middleware); 
const PORT = 3434; 


server.listen(PORT); 
console.log("Server nodejs chay tai dia chi: " + ip.address() + ":" + PORT)
Router = require('./routes')
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');
app.use('/web',Router)


app.use(express.static("web")) 


//giải nén chuỗi JSON thành các OBJECT
function ParseJson(jsondata) {
    try {
        return JSON.parse(jsondata);
    } catch (error) {
        return null;
    }
}


//Bắt các sự kiện khi esp8266 kết nối
esp8266_nsp.on('connection', function(socket) {
    console.log('es connected')
    socket.on('kiem-tra',function(data){
        console.log(data);
        socket.emit("Accept",{});
    })
    socket.on('disconnect', function() {
        console.log("Disconnect es");
    })




})

//Bắt các sự kiện khi webapp kết nối

webapp_nsp.on('connection', function(socket) {
    var data =[];
    data[0]= 'Bùi Đăng Khoa';
    data[1] = ' KTX khu B';
    console.log('web')
    socket.emit('Xuat',data )
    //Khi webapp socket bị mất kết nối
    socket.on('disconnect', function() {
        console.log("Disconnect  web")
    })

})