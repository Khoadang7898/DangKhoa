// --------------------------------------------BUID-IN MODULE---------------------------------------------------
var createError = require('http-errors');
var express = require('express');
var app = express();
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var url = require('url');
var mongoClient = require('mongodb');
var chalk = require('chalk');
var bodyParser = require('body-parser');
var multer = require('multer');
var session = require('express-session');
var RewriteMiddleware = require('express-htaccess-middleware');
var request = require('request-promise');

// --------------------------------------------PERSONAL MODULE---------------------------------------------------
var config = require('./config/config');
var dbFunction = require('./config/db-funtion');
var autoIncrement = require("mongodb-autoincrement");

// ---------------------------------------------REFERENCE TO ROUTERS---------------------------------------------------
var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');
var postsRouter = require('./routes/post');
var topicRouter = require('./routes/topic');
var search = require('./public/js/searchtool/search');
var search_news=require('./public/js/searchtool/search_news');
var filter = require('./public/js/searchtool/filter');
var test_interactive = require('./routes/test_interactive');
var queryRouter = require('./routes/query');
var loginandregister = require('./routes/LoginAndRegister/LoginAndRegister');

// --------------------------------------------BUID FRIEND URL---------------------------------------------------
var RewriteOptions = {
    file: path.resolve(__dirname, '.htaccess'),
    verbose: (process.env.ENV_NODE = true),
    watch: (process.env.ENV_NODE = true),
};
app.use(RewriteMiddleware(RewriteOptions));

// --------------------------------SET VIEW ENGINE IS EJS AND SET DEFAULT FORDER ---------------------------------------
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');
//Set static root forder
app.use(express.static(path.join(__dirname, 'public')));

// ----------------------------------------------USE PARSE STRUCTURE MODULE---------------------------------------------
app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({extended: false}));
app.use(bodyParser.urlencoded({extended: true}));  // PARSE FORM DATA
app.use(bodyParser.json());                       // PARSE JSON DATA
app.use(cookieParser());                          // PARSE COOKIE

// -----------------------------------------------USE AND ININT SESSION-------------------------------------------------
app.use(session({
    secret: "Shh, its a secret!",
    resave: false,
    saveUninitialized: true
}));

// ******************************WARNING : NOT CHANGE THE ORDER OF THE FOLLOW ROUTERS***********************************
// ----------------------------------------FIRST : GET CONFIGURATION ROUTER--------------------------------------------
// GET CONFIGURATION
app.use((req, res, next) => {
    console.log(chalk.yellow("NOTIFY : FIRST ROUTER INFORMATION"));
    console.log(chalk.green("*Router : configurationRouter"));
    console.log(chalk.green("*Original Url : " + req.url));
    next();
});
// CHECK SESSION
app.use((req, res, next) => {
    console.log(chalk.yellow("NOTIFY : SESSION INFORMATION"));
    console.log(chalk.green("*Router : sessionRouter"));
    if(req.cookies.isL){
        if(req.cookies.isL=='true')
        {
            console.log(chalk.green("*Login status : ")+chalk.blue("ALREADY"));
            if(!req.session.view)
            {
                req.session.view = { isLogin : true}
            }
            if(!req.session.user)
            {
                req.session.user={}
            }
            req.session.user = {name : req.cookies.user,id: req.cookies.id}
            // console.log(req.cookies.user)
        }
        else {
            if(!req.session.view)
            {
                req.session.view = { isLogin : false}
            }
            if(!req.session.user)
            {
                req.session.user={}
            }
            req.session.user = {name : "false",id: null}
            console.log(chalk.green("*LOGIN status : ")+chalk.red("NOT YET"));
                // res.cookie('isL','false',{maxAge:90000000})
            // console.log("false-------------------------------------------------------")
               // res.cookie('user',"false",{maxAge:90000000})
        }
    }
    else {
        if(!req.session.view)
        {
            req.session.view = { isLogin : false}
        }
        if(!req.session.user)
        {
            req.session.user={}
            req.session.user = {name : false,id: null}
        }
        res.cookie('isL','false',{maxAge:90000000})
        console.log(chalk.green("*LOGIN status : ")+chalk.red("NOT YET"));
        // res.cookie('isL','false',{maxAge:90000000})
        // console.log("false-------------------------------------------------------")
        res.cookie('user',"false",{maxAge:90000000})
    }
    if (req.session.user.name == false) {
        console.log(chalk.green("*User Name : ")+chalk.magenta("Anonymous"));
        res.cookie('user', "false")
    } else {
        console.log(chalk.green("*User Name :")+chalk.cyan(req.session.user.name));
        res.cookie('user', req.session.user.name);
    }
  next();
});
// -------------------------------------------SECOND : PROCESS ROUTERS--------------------------------------------------
app.use('/', indexRouter);
app.use('/users', usersRouter);
app.use('/post', postsRouter);
app.use('/topic', topicRouter);
app.use('/search', search);
app.use('/search_news',search_news);
app.use('/filter', filter);
app.use('/test_interactive', test_interactive);
app.use('/query', queryRouter);
app.use('/loginregister', loginandregister);
// -------------------------------------------THIRD : ERROR ROUTERS----------------------------------------------------
// 404 Router. Must place here ! After all others router
app.use((req, res) => {
    res.render("pages/404", {domain: "thongtindulich.azurewebsites.net"});
});
// ------------------------------------------------ERROR HANDLERS-----------------------------------------------------
// catch 404 and forward to error handler
app.use(function (req, res, next) {
    next(createError(404));
});
// error handler
app.use(function (err, req, res) {
    // set locals, only providing error in development
    res.locals.message = err.message;
    res.locals.error = req.app.get('env') === 'development' ? err : {};

    // render the error page
    res.status(err.status || 500);
    res.render('error');
});

// -------------------------------------------------------SERVER------------------------------------------------------
//Run server
var server = app.listen(config.server.port, config.server.host, () => {
    var host = server.address().address;
    var port = server.address().port;
    //Create server url
    var server_address = "https://" + host + ":" + port;
    // Logging initialization
    console.log();
    console.log(chalk.bgGreen("--------------------------------" + config.app.description + " Project---------------------------------"));
    console.log(chalk.green('Environment:           ' + config.app.environment()));
    console.log(chalk.green('Server:                ' + server_address));
    console.log(chalk.green('App-Type :             ' + config.connect.type()));
    console.log(chalk.green('Server of Database :   ' + config.db.url()));
    console.log(chalk.green('Connect String :       ' + config.db.uri));
    console.log(chalk.green('App version:           ' + config.app.version));
    if (config.app['ui-version'])
        console.log(chalk.green('User interface:        ' + config.app['ui-version']));
    console.log('----------------------------------------------------------------------------------------');
});
