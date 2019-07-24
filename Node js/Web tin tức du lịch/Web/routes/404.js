var express = require("express");
var router = express.Router();

router.all("/*",(req,res)=>{
    //Get configuration
    console.log(chalk.green.bgCyan("=>URL WAS PROCESSED : " + req.url));
    console.log(chalk.green("Time : " + new Date().toLocaleString()));

    res.render("pages/404",{domain:"thongtindulich.azurewebsites.net"});
});
module.exports = router;
