package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller_GiaoDienQuenMatKhau implements Initializable{
	
	private Connection conn=null;
	@FXML
	TextField username;
	@FXML
	Button laylai;
	@FXML
	public void laylaimatkhau() throws IOException {
    	conn= DBConnection.getConnection();
		try {
			
			String ten=username.getText();
			String matkhau="";
			if(ten.isEmpty()) {
				return;
			}
			
			Statement st =conn.createStatement();
			String sql="SELECT * FROM LOGIN_BOSS WHERE username="+"'"+ten+"'";
			ResultSet rs= st.executeQuery(sql);
			String name="";
			String pass="";
			while(rs.next()) {
				name=rs.getString("username");
				pass=rs.getString("password");
			}
			if(name.equals("")) {
				Alert mess= new Alert(Alert.AlertType.INFORMATION);
	    		mess.setTitle("Thông báo!!!");
	    		mess.setHeaderText("CHƯA ĐĂNG KÝ TÀI KHOẢN");
	    		mess.show();
			}
			if(name.equals(username.getText())) {
				Alert mess= new Alert(Alert.AlertType.INFORMATION);
	    		mess.setTitle("Thông báo!!!");
	    		mess.setHeaderText("USERNAME: "+ten);
	    		mess.setContentText("PASSWORD: "+pass);
	    		mess.show();
			}
				
		}catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
