package com.server.app;
import App.createAccount;
import DBsql.interfaceSQL;

import App.loginApp;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import App.result;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import com.server.app.listdata;
import com.server.app.QueryServer;

import App.FogetPassWord;
import App.baitap;
import App.dataApp;
import App.ketqua;
import App.lichtap;
@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	
	@Controller
	@RequestMapping("/data")
	public class getData {
	       @RequestMapping(value  = "/query/select", method = RequestMethod.POST)
	       @ResponseBody
	       public listdata Select(@RequestBody QueryServer model) {
	    	   
	    	   
	    	   listdata l = interfaceSQL.Select(model);
	    	   return l;
	   }
	       @RequestMapping(value  = "/query/upindel", method = RequestMethod.POST)
	       @ResponseBody
	       public listdata UpInDel(@RequestBody QueryServer model) {
	    	   return interfaceSQL.StatementUPIS(model);
	   }
	       
	       @RequestMapping(value = "/login", method = RequestMethod.POST)
	       @ResponseBody
	       public result login(@RequestBody loginApp query ) {
	    	   return interfaceSQL.Login(query);
	       }
	       @RequestMapping(value = "/fogetpassword", method = RequestMethod.POST)
	       @ResponseBody
	       public ketqua login(@RequestBody FogetPassWord data ) {
	    	   return interfaceSQL.FogetPassWord(data);
	       }
	       @RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	       @ResponseBody
	       public ketqua createAccount(@RequestBody createAccount data ) {
	    	   return interfaceSQL.createAccount(data);
	       }
	}
	
	@Controller
	@RequestMapping("/user")
	public class user{
		@RequestMapping(value  = "/login", method = RequestMethod.POST)
	       @ResponseBody
	       public dataApp getDataUser(@RequestBody loginApp model) {
	    	   return interfaceSQL.getDataUser(model);
	   }
		@RequestMapping(value  = "/setlichtap", method = RequestMethod.POST)
	       @ResponseBody
	       public int setLichTap(@RequestBody lichtap model) {
	    	   return interfaceSQL.setLichTap(model);
	   }
		@RequestMapping(value  = "/setnewlichtap", method = RequestMethod.POST)
	       @ResponseBody
	       public int setnewLichTap(@RequestBody lichtap model) {
	    	   return interfaceSQL.setNewLichTap(model);
	   }
		@RequestMapping(value  = "/setbaitap", method = RequestMethod.POST)
	       @ResponseBody
	       public int setBaiTap(@RequestBody baitap model) {
	    	   return interfaceSQL.setNewBaiTap(model);
	   }
	}
	
}
