package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller_GiaoDienDangKy implements Initializable{
	
	private Connection conn=null;
	
	@FXML
	TextField username,password;
	@FXML
	Button dangky;
	@FXML
	public void dangky() throws SQLException {
		conn=DBConnection.getConnection();
		String text_username=username.getText();
		String text_password=password.getText();
		String sql="INSERT INTO LOGIN_BOSS" + 
    			" VALUES ("+"N'"+text_username+"'"+","+"N'"+text_password+"'"+")";
		int i=conn.createStatement().executeUpdate(sql);
		if(i==1) {
    		Alert mess= new Alert(Alert.AlertType.INFORMATION);
    		mess.setTitle("Thông báo");
    		mess.setContentText("UserName: "+text_username);
    		mess.setHeaderText("****ĐĂNG KÝ THÀNH CÔNG****");
    		mess.show();
    	}
	}
	
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
