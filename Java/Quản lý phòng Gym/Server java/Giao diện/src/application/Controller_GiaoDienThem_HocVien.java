package application;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.swing.JOptionPane;

import NhanVienHocVien.HocVien;
import application.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
//import application.Controller_GiaoDienBoss;


public class Controller_GiaoDienThem_HocVien implements Initializable {
	private Connection conn=null;
	private PreparedStatement pst=null;
	
	//check box nam, nữ
		@FXML
		CheckBox Nam,Nu;
		@FXML
		public void dachon() {
			if(Nu.isSelected()) {
				Nam.setSelected(false);
			}

		}
	//combobox dịch vụ
		ObservableList<String> dichvu_themdv= FXCollections.observableArrayList("Dịch vụ I(30 ngày)","Dịch vụ II(30 ngày, có huấn luyện viên)");
		@FXML
		private ComboBox<String> dichvu_themdvcombox;
	//combobox huấn luyện viên
		ObservableList<String> dichvu_hlv= FXCollections.observableArrayList("Nguyễn Tuấn Dương","Doãn Ngọc Tài","Bùi Đăng Khoa");
		@FXML
		private ComboBox<String> dichvu_hlvcombox;
	//choicebox trong ngày tháng năm sinh -->ThêmHV
		
		@FXML private ChoiceBox<Integer> nam=new ChoiceBox<Integer>();
		
		@FXML private ChoiceBox<Integer> ngay=new ChoiceBox<Integer>();
		
		@FXML private ChoiceBox<Integer> thang=new ChoiceBox<Integer>();
	 //Nút lưu học viên
		@FXML
		private TextField hovaten,diachi,sdt,chieucao,cannang;
		@FXML
		private Button luu;
		@FXML
		public void themhocvien() {
    	Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String text_ngaydangky=sdf.format(cal.getTime());
        String text_ngaybatdau=sdf.format(cal.getTime());
        cal.add(Calendar.MONTH, 1);
        String text_ngayketthuc=sdf.format(cal.getTime());
        
    	
    	conn=DBConnection.getConnection();
    	String gioitinh="";
    	String text_ten=hovaten.getText();
    	if(Nam.isSelected()) {
    		gioitinh="Nam";
    	}
    	if(Nu.isSelected()) {
    		gioitinh="Nu";
    	}
    	int text_ngay=ngay.getValue();
    	int text_thang=thang.getValue();
    	int text_nam=nam.getValue();
    	String ngaythangnamsinh=String.valueOf(text_ngay)+"/"+String.valueOf(text_thang)+"/"+String.valueOf(text_nam);
    	String text_diachi=diachi.getText();
    	int int_sdt=Integer.parseInt(sdt.getText());
    	int songayhieuluc=30;
    	String text_dichvu=dichvu_themdvcombox.getValue();
    	String text_hlv=dichvu_hlvcombox.getValue();
    	float text_chieucao=Float.valueOf(chieucao.getText());
    	float text_cannang=Float.valueOf(cannang.getText());
    	String text_hinhanh=hinhanh.getText();
    	try {
    		String sql="INSERT INTO HOIVIEN" + 
        			" VALUES ("+"N'"+text_dichvu+"'"+","+"N'"+text_hlv+"'"+","+"N'"+text_ten+"'"+","+"'"+ngaythangnamsinh+"'"+","+"N'"
    				+text_diachi+"'"+","+int_sdt+","+"'"+gioitinh+"'"+","+"N'"+text_hinhanh+"'"+","+"'"+text_ngaydangky+"'"+
        			","+"'"+text_ngaybatdau+"'"+","+"'"+text_ngayketthuc+"'"+","+songayhieuluc+","+text_chieucao+","+text_cannang+")";
        	
			pst=conn.prepareStatement(sql);
//			pst.setString(1, text_dichvu);
//	    	pst.setString(2, text_hlv);
//	    	pst.setString(3, text_ten);
//	    	pst.setString(4, ngaythangnamsinh);
//	    	pst.setString(5, text_diachi);
//	    	pst.setInt(6, int_sdt);
//	    	pst.setString(7, gioitinh);
//	    	pst.setString(8, text_hinhanh);
//	    	pst.setString(9, text_ngaydangky);
//	    	pst.setString(9, text_ngaybatdau);
//	    	pst.setString(10, text_ngayketthuc);
//	    	pst.setInt(11, songayhieuluc);
//	    	pst.setFloat(12, text_chieucao);
//	    	pst.setFloat(13, text_cannang);
	    	System.out.println(sql);
	    	int i=pst.executeUpdate();
	    	if(i==1) {
	    		Alert mess= new Alert(Alert.AlertType.INFORMATION);
	    		mess.setTitle("Thông báo");
	    		mess.setContentText("Học viên: "+text_ten+"\n"+"Ngày tháng năm sinh: "+ngaythangnamsinh+"\n"+"Sdt: "+int_sdt);
	    		mess.setHeaderText("****THÊM THÀNH CÔNG****");
	    		mess.show();
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    //ALL --> combobox dịch vụ,combobox huấn luyện viên,choicebox
	public void All() {
		int a=1970;
		ObservableList<Integer> listnam=FXCollections.observableArrayList();
		for(int i=0;i<2019-1970;i++) {
			listnam.add(a);
			a+=1;
		}
		dichvu_hlvcombox.setItems(dichvu_hlv);
		dichvu_hlvcombox.setValue("Nguyễn Tuấn Dương");
		dichvu_themdvcombox.setItems(dichvu_themdv);
		dichvu_themdvcombox.setValue("Dịch vụ I(30 ngày)");
		ngay.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31);
		ngay.setValue(1);
		thang.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
		thang.setValue(1);
		nam.getItems().addAll(listnam);
		nam.setValue(1970);
	}
	//chọn file ảnh 
	@FXML
	TextField hinhanh;
	@FXML
	Button chonfileanh;
	Desktop desktop = Desktop.getDesktop();
	@FXML
	public void chonanh() {
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
			      new FileChooser.ExtensionFilter("All Files", "*.*"), 
			      new FileChooser.ExtensionFilter("JPG", "*.jpg"),
			      new FileChooser.ExtensionFilter("PNG", "*.png"));
		File file = fileChooser.showOpenDialog(null);
        
        if(file!=null) {
        	hinhanh.setText(file.getAbsolutePath());
        }
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		All();
	}
	
	
}
