var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');


var app = express();
var session = require('express-session');
var md5 = require('md5');
var bodyParser = require('body-parser');
var mongoClient = require('mongodb').MongoClient;
mongoClient.connect('mongodb://127.0.0.1:27017', function(err , db){

        if(err) throw err;
        console.log("connect db");
        db.close();
        console.log("close db");
})


app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());
app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

// app.use('/', indexRouter);
// app.use('/users', usersRouter);
app.use(session({
  secret: 'abc',
  resave: false,
  saveUninitialized: true
}))
app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
  });
// catch 404 and forward to error handler
/*app.use(function(req, res, next) {
  next(createError(404));
});
*/
// error handler
// var MongoClient = require('mongodb').MongoClient;
// var url = 'mongodb://localhost:27017';
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});


app.get('/',function(req,res){
    res.json({khoa:"Dang khoa",name: "25"})
})
// app.get('/', function (req, res){
//   if (!req.session.views){
//       req.session.views = {};
//       console.log("nothing");
//   }
//   else{
//       if (req.session.views['user'] != null){
//           res.render('welcome',{
//               name: req.session.views['user'],
//           })
//           return;
//       }      
//   }
//   res.render('login',{
//       error: '',
//   });
// })
app.post('/', function(req, res){
    res.json("connected")
})
// app.post('/', function(req, res){

//   MongoClient.connect(url, function(err, db) {
//       if (err) throw err;

//       var dbo = db.db("kchatdb");
//       var query = { username: email, password: password };
//       var name = '';
//       dbo.collection("users").find(query).toArray(function(err, result) {
//           if (err) throw err;
//           db.close();

//           if (result.length > 0){
//               name = result[0].name;
//               req.session.views['user'] = name;
//               res.render('welcome',{
//                   name: name,
//               })
//           }
//           else{
//               res.render('login',{
//                   error: 'Sai tài khoản hoặc mật khẩu',
//               })
//           }
//       });
//   });    
// });





function validateEmail(email) {
  var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(String(email).toLowerCase());
}
// view engine setup


app.post('/register', function(req, res){
  console.log("register");
  var fullname = req.body.reg.name;
  var email = req.body.reg.email;
  var password = md5(req.body.reg.password);
  var comfirmpass = md5(req.body.reg.confirmpass);

  console.log(fullname);
  console.log(email);
  console.log(password);
  console.log(comfirmpass);

  if (password != comfirmpass){
      res.render('login', {
          notic: 'Mật khẩu không trùng',
      })
  }
  else{
      MongoClient.connect(url, function(err, db) {
          if (err) throw err;
          var dbo = db.db("kchatdb");
          var query = { username: email};

          dbo.collection("users").find(query).toArray(function(err, result) {
              if (err) throw err;
  
              if (result.length > 0){
                  res.render('login',{
                      notic: 'Tài khoản đã tồn tại!',
                  })
              }
              else{
                  var myobj = {username: email, password: password, name: fullname};
                  dbo.collection('users').insertOne(myobj, function(err, res){
                      if (err) throw err;
                      console.log('inserted');
                  });
                  res.render('login',{
                      notic: 'Đăng ký thành công',
                  })
              }

              db.close();
          });
      });    
  }
});









module.exports = app;
