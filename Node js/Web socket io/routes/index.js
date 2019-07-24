var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('login', { title: 'Kchat' });
});

router.use(function timeLog(req, res, next){
  console.log('Time: ', Date.now());
  next();
});

router.get('/forgot-password', function (req, res){
  res.render('forgot-password');
})

router.get('/forgot', function(req, res){
  res.render('forgot-password');
})

router.get('/login', function (req, res){
  res.render('login',{
      error: '',
  });
})

router.get('/register', function (req, res){
  res.render('register',{
      notic: '',
  });
})

module.exports = router;
