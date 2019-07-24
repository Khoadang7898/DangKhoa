package DBsql;

 

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class ConnectionSQL {
 
	
	
	//Xin chào ae: chúng ta bắt đầu với buổi connect server do Khoa pro lây dơ làm:
	//Hàm này là ae lấy connect mysql://b8e8314434bfda:14b2d950@us-cdbr-iron-east-02.cleardb.net/heroku_359f87a8a52c7e7?reconnect=true
	public static Connection getConnection() throws URISyntaxException, SQLException {
		return DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-02.cleardb.net:3306/heroku_359f87a8a52c7e7?useUnicode=true&characterEncoding=utf-8", "b8e8314434bfda", "14b2d950");//Thông số đưa vô lần lượt là URL, user , pass
	}
	//trả về cho ta một cái connect
	
	

}