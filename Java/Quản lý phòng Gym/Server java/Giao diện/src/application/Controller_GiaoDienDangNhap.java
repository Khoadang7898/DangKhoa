package application;
import App.dataApp;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import NhanVienHocVien.HocVien;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.server.Client.QueryServer;
import org.server.Client.listdata;
import org.server.Client.loginApp;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.App;
public class Controller_GiaoDienDangNhap implements Initializable{
	private Connection conn=null;
	private PreparedStatement pst=null;
	public void GD_Boss() throws IOException {
		Parent root=FXMLLoader.load(getClass().getResource("/application/GiaoDienBoss.fxml"));
		Scene scene=new Scene(root);
		Stage stage=new Stage(StageStyle.DECORATED);
		stage.setTitle("Giao Diện Quản Lý");
		stage.setScene(scene);
		stage.show();
	}
	//đăng ký tài khoản
	@FXML
	Button dangkytaikhoan;
	@FXML
	public void GD_DK() throws IOException {
		Parent root=FXMLLoader.load(getClass().getResource("/application/GiaoDienDangKy.fxml"));
		Scene scene=new Scene(root);
		Stage stage=new Stage(StageStyle.DECORATED);
		stage.setTitle("Giao Diện Đăng Ký");
		stage.setScene(scene);
		stage.show();
	}
	//kiểm tra username có tồn tại ,nếu không đăng ký
	@FXML
	TextField username;
	@FXML
	PasswordField password;
	@FXML
	Button dangnhap;
	@FXML
	public void kiemtradangnhap() throws IOException {
  

			String ten=username.getText();
			String matkhau=password.getText();
			loginApp lg = new loginApp(ten, matkhau);
			Client client = Client.create();
	         WebResource webResource;
	 
	        	  webResource = client.resource("https://gymcenter.herokuapp.com/user/login");
	       
	         ClientResponse response = webResource.accept("application/json").post(ClientResponse.class, lg);
	    
	         // Trạng thái 200 là thành công
	         if (response.getStatus() != 200) {
	        	 System.out.print("not connected");
	         }
	         else {	
	        	 
	        	 
	        	 dataApp list = response.getEntity(dataApp.class);
	        	 System.out.print(list.getResult());
	        	 
	        	 if(list.getResult()==1) {
				GD_Boss();
			}else {
				Alert mess= new Alert(Alert.AlertType.INFORMATION);
	    		mess.setTitle("Thông báo!!!");
	    		mess.setHeaderText("****ĐĂNG NHẬP SAI USERNAME HOẶC PASSWORD****");
	    		mess.show();
			}
	         }
	         
	         
			
//			if(ten.isEmpty()||matkhau.isEmpty()) {
//				return;
//			}
//			Statement st =conn.createStatement();
//			
//			String sql="SELECT * FROM LOGIN_BOSS WHERE username="+"'"+ten+"'";
//			ResultSet rs= st.executeQuery(sql);
//			
//			while(rs.next()) {
//				if(rs.getString("username").equals(username.getText())&& rs.getString("password").equals(password.getText())) {
//					cotaikhoan=1;
//				}
//			}
			
				
		
	}
	//nhấn nút quên mật khẩu --> giao diện tương ứng
	@FXML
	Button quenmatkhau;
	@FXML
	public void quenmatkhau() throws IOException {
		Parent root=FXMLLoader.load(getClass().getResource("/application/GiaoDienQuenMatKhau.fxml"));
		Scene scene=new Scene(root);
		Stage stage=new Stage(StageStyle.DECORATED);
		stage.setTitle("Giao Diện Quên Mật Khẩu");
		stage.setScene(scene);
		stage.show();
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
