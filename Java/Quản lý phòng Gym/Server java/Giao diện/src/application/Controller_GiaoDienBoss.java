package application;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import org.server.Client.QueryServer;
import org.server.Client.listdata;

import CoSoVatChat.ThietBiPhongTap;
import NhanVienHocVien.GiaTriDinhDuong;
import NhanVienHocVien.HocVien;
import NhanVienHocVien.NhanVien;
import SanPhamDichVu.DichVu;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;


public class Controller_GiaoDienBoss implements Initializable {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	Statement statement= null;
	ResultSet resultSet = null;
	//combobox thiết bị
	ObservableList<String> thietbi= FXCollections.observableArrayList("Mã thiết bị","Tên thiết bị", "Tình Trạng","Giá");
	@FXML
	private ComboBox<String> thietbicombox;


	//combobox dịch vụ

	ObservableList<String> dichvu= FXCollections.observableArrayList("Mã dịch vụ","Số ngày hiệu lực", "Giá");
	@FXML
	private ComboBox<String> dichvucombox;
	//combobox học viên
	ObservableList<String> hocvien= FXCollections.observableArrayList("Mã học viên", "Số điện thoại", "Số ngày hiệu lực");
	@FXML
	private ComboBox<String> hocviencombox;
	//combobox nhân viên
	ObservableList<String> nhanvien= FXCollections.observableArrayList("Mã nhân viên","Hệ số lương","Số điện thoại");
	@FXML
	private ComboBox<String> nhanviencombox;


	//===================================================================initialize===============================================
	public void initialize(URL arg0, ResourceBundle arg1) {
		thietbicombox.setItems(thietbi);
		dichvucombox.setItems(dichvu);
		nhanviencombox.setItems(nhanvien);
		hocviencombox.setItems(hocvien);

		DisableButtonWhileNotConnectToDB_NV();
		DisableButtonWhileNotConnectToDB_HV();
        DisableButtonWhileNotConnectToDB_DV();
        DisableButtonWhileNotConnectToDB_TB();
	}
	//===================================================================
//	@FXML
//	public void ThemHV() throws IOException {
//		Parent root=FXMLLoader.load(getClass().getResource("/application/GiaoDienThem_HocVien.fxml"));
//		Scene scene=new Scene(root);
//		Stage stage=new Stage(StageStyle.DECORATED);
//		stage.setTitle("Thêm Học Viên");
//		stage.setScene(scene);
//		stage.show();
//
//	}
//	@FXML
//	public void ThemNV() throws IOException {
//		Parent root=FXMLLoader.load(getClass().getResource("/application/GiaoDienThem_NhanVien.fxml"));
//		Scene scene=new Scene(root);
//		Stage stage=new Stage(StageStyle.DECORATED);
//		stage.setTitle("Thêm Nhân Viên");
//		stage.setScene(scene);
//		stage.show();
//
//	}
//	@FXML
//	public void ThemDV() throws IOException {
//		Parent root=FXMLLoader.load(getClass().getResource("/application/GiaoDienThem_DichVu.fxml"));
//		Scene scene=new Scene(root);
//		Stage stage=new Stage(StageStyle.DECORATED);
//		stage.setTitle("Thêm Dịch Vụ");
//		stage.setScene(scene);
//		stage.show();
//
//	}
//	@FXML
//	public void ThemCSVC() throws IOException {
//		Parent root=FXMLLoader.load(getClass().getResource("/application/GiaoDienThem_CoSoVatChat.fxml"));
//		Scene scene=new Scene(root);
//		Stage stage=new Stage(StageStyle.DECORATED);
//		stage.setTitle("Thêm Cơ Sở Vật Chất");
//		stage.setScene(scene);
//		stage.show();
//
//	}
	//Chuyển type String date --> GregorianCalendar
    public static GregorianCalendar convertFromDMY(String dd_mm_yy) throws ParseException {

        String[] splitDate = dd_mm_yy.split("-");
        int year = Integer.parseInt(splitDate[0]);
        int month = (Integer.parseInt(splitDate[1]) - 1);
        int  days= Integer.parseInt(splitDate[2]);

        GregorianCalendar dateConverted = new GregorianCalendar(year, month, days);
        return dateConverted;
    }
    //*****************************thêm cơ sở dữ liệu vào giao diện nhân viên**************************************
	@FXML
	Button btnSua_NV;
	@FXML
	Button btnThem_NV;
	@FXML
	Button btnXoa_NV;
	@FXML
	Button btnClear_NV;
	@FXML
	Button btnOPENDB_NV;
    @FXML
	Button btnSearch_NV;
	@FXML
	Button btnBrowseImage_NV;




	@FXML
	TextField txt_Ma_NV;
	@FXML
	TextField txt_hoten_NV;
	@FXML
	TextField txt_gioitinh_NV;
	@FXML
	TextField txt_chucvu_NV;
	@FXML
	TextField txt_hesoluong_NV;
	@FXML
	TextField txt_sdt_NV;
	@FXML
	TextField txt_diachi_NV;
	@FXML
	TextField txt_hinhanh_NV;
	@FXML
	DatePicker dat_ngaysinh_NV;
	@FXML
	TextField txt_luongcoban_NV;
	@FXML
	ImageView imageview_NV;
	@FXML
	TextField txt_search_NV;



	@FXML
    private TableView tablenhanvien;
    @FXML
    private TableColumn<ObservableList, String> col_manhanvien;
    @FXML
    private TableColumn<ObservableList, String> col_hovatennv;
    @FXML
    private TableColumn<ObservableList, String> col_gioitinh;
    @FXML
    private TableColumn<ObservableList, String> col_chucvu;
    @FXML
    private TableColumn<ObservableList, String> col_hesoluong;
    @FXML
    private TableColumn<ObservableList, String> col_diachi;

	ObservableList<ObservableList> dataNV=FXCollections.observableArrayList();

	void DisableButtonWhileNotConnectToDB_NV()
	{
		btnSua_NV.setDisable(true);
		btnThem_NV.setDisable(true);
		btnXoa_NV.setDisable(true);
	}
	void EndableButtonWhileConnectToDB_NV()
	{
		btnSua_NV.setDisable(false);
		btnThem_NV.setDisable(false);
		btnXoa_NV.setDisable(false);
	}

	void MoCSDL_NV()
	{
		System.out.println("Connect to SQLServer");
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLPTGYM;user=sa;password=1");

			statement = connection.createStatement();
			System.out.println("Connect succeed!");
			LoadCSDL_NV();
			EndableButtonWhileConnectToDB_NV();

		} catch (SQLException e) {

			System.out.println(e.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	void LoadCSDL_NV()
	{
		dataNV.clear();
		tablenhanvien.getColumns().clear();
		ResultSet rs;
		try {
			rs = statement.executeQuery("SELECT * FROM NHANVIEN");
			System.out.println(rs.toString());
			createtableNV();
			//đọc dữ liệu bỏ vào bảng
			while(rs.next()){
				//Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
					//Iterate Column
					row.add(rs.getString(i));
				}
				System.out.println("Row [1] added "+row );
				dataNV.add(row);
			}
			System.out.println("Số dòng dữ liệu NHÂN VIÊN: "+dataNV.size());
			System.out.println("Dữ liệu NHÂN VIÊN : "+dataNV);
			tablenhanvien.setItems(dataNV);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void createtableNV(){
		col_manhanvien=new TableColumn<ObservableList, String>("Mã nhân viên");
		col_manhanvien.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> p) {
				// p.getValue() returns the Person instance for a particular TableView row
				return new SimpleStringProperty(p.getValue().get(0).toString());
			}
		});

		col_hovatennv=new TableColumn<ObservableList, String>("Họ và tên");
		col_hovatennv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
				return  new SimpleStringProperty(param.getValue().get(4).toString());
			}
		});

		col_gioitinh= new TableColumn<ObservableList, String>("Giới tính");
		col_gioitinh.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
				return new SimpleStringProperty(param.getValue().get(8).toString());
			}
		});
		col_chucvu= new TableColumn<ObservableList, String>("Chức vụ");
		col_chucvu.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
				return new SimpleStringProperty(param.getValue().get(1).toString());
			}
		});
		col_hesoluong= new TableColumn<ObservableList, String>("Hệ số lương");
		col_hesoluong.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
				return new SimpleStringProperty(param.getValue().get(3).toString());
			}
		});
		col_diachi= new TableColumn<ObservableList, String>("Số điện thoại");
		col_diachi.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
				return new SimpleStringProperty(param.getValue().get(7).toString());
			}
		});


		tablenhanvien.getColumns().setAll(col_manhanvien,col_hovatennv,col_gioitinh,col_chucvu,col_hesoluong,col_diachi);
	}
	void LoadDataFromTWToTxt_NV(int index)
	{
		ObservableList<String> row;
		row= dataNV.get(index);
		txt_sdt_NV.setText(row.get(7));
		txt_chucvu_NV.setText(row.get(1));
		txt_diachi_NV.setText(row.get(6));
		txt_Ma_NV.setText(row.get(0));
		txt_hoten_NV.setText(row.get(4));
		txt_hesoluong_NV.setText(row.get(3));
		txt_gioitinh_NV.setText(row.get(8));
		Image image=new Image("file:///"+row.get(9));
		imageview_NV.setImage(image);
		txt_hinhanh_NV.setText(row.get(9));
		dat_ngaysinh_NV.setValue(LocalDate.parse(row.get(5)));
		txt_luongcoban_NV.setText(row.get(2));
	}

    @FXML
    void restrictNumbersOnly(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case TAB:
            case BACK_SPACE:
            case DELETE:
                break;
            default:
                if (!keyEvent.getText().matches("\\d")) {
                    // if (!keyEvent.getCode().isDigitKey()) {
                    keyEvent.consume();
                }
        }
    }
	@FXML
	void HandleBtnSearch_NV(){
		dataNV.clear();
		tablehocvien.getColumns().clear();
		ResultSet rs=null;
		try {
			if(nhanviencombox.getItems().equals("")&&txt_search_NV.getText().equals("")){
				Alert mess= new Alert(Alert.AlertType.INFORMATION);
				mess.setTitle("Thông báo");
				mess.setHeaderText("BẠN CHƯA PHÂN LOẠI VÀ NHẬP TỪ KHÓA CẦN TÌM!!!");
				mess.show();
				return;
			}else if(nhanviencombox.getItems().equals("")||txt_search_NV.getText().equals("")){
				Alert mess= new Alert(Alert.AlertType.INFORMATION);
				mess.setTitle("Thông báo");
				mess.setHeaderText("BẠN CHƯA PHÂN LOẠI HOẶC CHƯA NHẬP TỪ KHÓA CẦN TÌM!!!");
				mess.show();
				return;
			}
			if(nhanviencombox.getValue().equals("Mã nhân viên")){
                rs = statement.executeQuery("SELECT * FROM NHANVIEN WHERE MaNhanVien="+Integer.parseInt(txt_search_NV.getText()));
			}else if(nhanviencombox.getValue().equals("Hệ số lương")){
                rs = statement.executeQuery("SELECT * FROM NHANVIEN WHERE HeSoLuong="+Integer.parseInt(txt_search_NV.getText()));
            }else if(nhanviencombox.getValue().equals("Số điện thoại")){
                rs = statement.executeQuery("SELECT * FROM NHANVIEN WHERE SDT="+Integer.parseInt(txt_search_NV.getText()));
            }
//			rs = statement.executeQuery("SELECT * FROM NHANVIEN WHERE MaNhanVien="+Integer.parseInt(txt_search_NV.getText()));
			createtableNV();
			//đọc dữ liệu bỏ vào bảng
			while(rs.next()){
				//Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
					//Iterate Column
					row.add(rs.getString(i));
				}
				System.out.println("Row [1] added "+row );
				dataNV.add(row);
			}
			System.out.println("Số dòng dữ liệu NHÂN VIÊN: "+dataNV.size());
			System.out.println("Dữ liệu NHÂN VIÊN : "+dataNV);
			tablehocvien.setItems(dataNV);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	void HandleBtnXoa_NV(ActionEvent event) {
		try {
			String manv=txt_Ma_NV.getText();
			preparedStatement= connection.prepareStatement("DELETE FROM NHANVIEN WHERE MaNhanVien=?");
			preparedStatement.setString(1, manv);

			int count =preparedStatement.executeUpdate();
			if(count==1){
				Alert mess= new Alert(Alert.AlertType.INFORMATION);
				mess.setTitle("Thông báo");
				mess.setHeaderText("✍ XÓA THÀNH CÔNG ✍");
				mess.show();
			}else{
				Alert mess= new Alert(Alert.AlertType.INFORMATION);
				mess.setTitle("Thông báo");
				mess.setHeaderText("✍ XÓA KHÔNG THÀNH CÔNG ✍");
				mess.show();
			}

			LoadCSDL_NV();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	void HandleBtnClear_NV(ActionEvent event) {
		txt_sdt_NV.clear();
		txt_chucvu_NV.clear();
		txt_diachi_NV.clear();
		txt_Ma_NV.clear();
		txt_hoten_NV.clear();
		txt_hesoluong_NV.clear();
		txt_gioitinh_NV.clear();
		dat_ngaysinh_NV.getEditor().clear();
		imageview_NV.setImage(null);

	}
	@FXML
	void HandleTVClick_NV(MouseEvent event) {
		System.out.println("Selected Row:"+ tablenhanvien.getSelectionModel().getSelectedIndex());
		btnXoa_NV.setDisable(false);
		LoadDataFromTWToTxt_NV(tablenhanvien.getSelectionModel().getSelectedIndex());

	}
	@FXML
	void handleMoCSDL_NV(ActionEvent event) throws ParseException {
		System.out.println("Mo CSDL NHÂN VIÊN");
		MoCSDL_NV();
	}
	@FXML
	void HandleBtnSua_NV(ActionEvent event) {
		try {
			String gioitinh=txt_gioitinh_NV.getText();
			String diachi=txt_diachi_NV.getText();
			String hinhanh=txt_hinhanh_NV.getText();
			String chucvu=txt_chucvu_NV.getText();
			int sdt= Integer.parseInt(txt_sdt_NV.getText());
			float hesoluong=Float.valueOf(txt_hesoluong_NV.getText());
			float luongcoban=Float.valueOf(txt_luongcoban_NV.getText());
			String ngaysinh = dat_ngaysinh_NV.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String hoten=txt_hoten_NV.getText();
			int manv=Integer.parseInt(txt_Ma_NV.getText());


			preparedStatement= connection.prepareStatement("UPDATE NHANVIEN SET GioiTinh=?, DiaChi=?, HinhAnh=?, ChucVu=?, SDT=?, HeSoLuong=?, LuongCoBan=?,NgayThangNamSinh=?,HoVaTen=? WHERE MaNhanVien=?");

			preparedStatement.setString(1, gioitinh);
			preparedStatement.setString(2, diachi);
			preparedStatement.setString(3, hinhanh);
			preparedStatement.setString(4, chucvu);
			preparedStatement.setInt(5, sdt);
			preparedStatement.setFloat(6, hesoluong);
			preparedStatement.setFloat(7, luongcoban);
			preparedStatement.setString(8, ngaysinh);
			preparedStatement.setString(9, hoten);
			preparedStatement.setInt(10, manv);

			int count= preparedStatement.executeUpdate();
			if(count==1){
				Alert mess= new Alert(Alert.AlertType.INFORMATION);
				mess.setTitle("Thông báo");
				mess.setHeaderText("✍ SỬA THÀNH CÔNG ✍");
				mess.show();
			}else
			{
				Alert mess= new Alert(Alert.AlertType.INFORMATION);
				mess.setTitle("Thông báo");
				mess.setHeaderText("✍ SỬA KHÔNG THÀNH CÔNG ✍");
				mess.show();
			}
			LoadCSDL_NV();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	void HandleBtnThem_NV(ActionEvent event) {
		try {
			String gioitinh=txt_gioitinh_NV.getText();
			String diachi=txt_diachi_NV.getText();
			String hinhanh=txt_hinhanh_NV.getText();
			String chucvu=txt_chucvu_NV.getText();
			int sdt= Integer.parseInt(txt_sdt_NV.getText());
			float hesoluong=Float.valueOf(txt_hesoluong_NV.getText());
			float luongcoban=Float.valueOf(txt_luongcoban_NV.getText());
			String ngaysinh = dat_ngaysinh_NV.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String hoten=txt_hoten_NV.getText();

//			String sql="INSERT INTO NHANVIEN " +
//					"VALUES (N'?',?,?,N'?','?',N'?',?,N'?',N'?')";
			preparedStatement= connection.prepareStatement("INSERT INTO NHANVIEN " +
					"VALUES (?,?,?,?,?,?,?,?,?)");

			preparedStatement.setString(1, chucvu);
			preparedStatement.setFloat(2, luongcoban);
			preparedStatement.setFloat(3, hesoluong);
			preparedStatement.setString(4, hoten);
			preparedStatement.setString(5, ngaysinh);
			preparedStatement.setString(6, diachi);
			preparedStatement.setInt(7, sdt);
			preparedStatement.setString(8, gioitinh);
			preparedStatement.setString(9, hinhanh);

			int ok=preparedStatement.executeUpdate();
			if(ok==1){
				Alert mess= new Alert(Alert.AlertType.INFORMATION);
				mess.setTitle("Thông báo");
				mess.setHeaderText("✍ THÊM THÀNH CÔNG ✍");
				mess.show();
			}else{
				Alert mess= new Alert(Alert.AlertType.INFORMATION);
				mess.setTitle("Thông báo");
				mess.setHeaderText("☹ THÊM KHÔNG THÀNH CÔNG ☹");
				mess.show();
			}

			LoadCSDL_NV();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	void HandleBtnchonanh_NV() {

		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("All Files", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				new FileChooser.ExtensionFilter("PNG", "*.png"));
		File file = fileChooser.showOpenDialog(null);

		if(file!=null) {
			txt_hinhanh_NV.setText(file.getAbsolutePath());
		}
	}

	//	********************************************thêm cơ sở dữ liệu vào giao diện học viên***************************
	@FXML
	Button btnSua_HV;
	@FXML
	Button btnThem_HV;
	@FXML
	Button btnXoa_HV;
	@FXML
	Button btnClear_HV;
	@FXML
	Button btnOPENDB_HV;
	@FXML
	Button btnSearch_HV;
	@FXML
	Button btnBrowseImage_HV;


	@FXML
	TextField txt_Ma_HV;
	@FXML
	TextField txt_hoten_HV;
	@FXML
	TextField txt_gioitinh_HV;
	@FXML
	TextField txt_songayhieuluc_HV;
	@FXML
	TextField txt_hlv_HV;
	@FXML
	TextField txt_sdt_HV;
	@FXML
	TextField txt_diachi_HV;
	@FXML
	TextField txt_hinhanh_HV;
	@FXML
	TextField txt_madichvu_HV;
	@FXML
	TextField txt_chieucao_HV;
	@FXML
	TextField txt_cannang_HV;
	@FXML
	DatePicker dat_ngaysinh_HV;
	@FXML
	DatePicker dat_ngaybatdau_HV;
	@FXML
	DatePicker dat_ngaydangky_HV;
	@FXML
	DatePicker dat_ngayketthuc_HV;
	@FXML
	ImageView imageview_HV;
	@FXML
	TextField txt_search_HV;

	@FXML
    private TableView tablehocvien;
    @FXML
    private TableColumn<ObservableList, String> col_mahocvien;
    @FXML
    private TableColumn<ObservableList, String> col_hovatenhocvien;
    @FXML
    private TableColumn<ObservableList, String> col_sdt;
    @FXML
    private TableColumn<ObservableList, String> col_ngaydangky;
    @FXML
    private TableColumn<ObservableList, String> col_ngayketthuc;
    @FXML
    private TableColumn<ObservableList, String> col_songayhieuluc;

    ObservableList<ObservableList> dataHV=FXCollections.observableArrayList();

	void DisableButtonWhileNotConnectToDB_HV()
	{
		btnSua_HV.setDisable(true);
		btnThem_HV.setDisable(true);
		btnXoa_HV.setDisable(true);
	}
	void EndableButtonWhileConnectToDB_HV()
	{
		btnSua_HV.setDisable(false);
		btnThem_HV.setDisable(false);
		btnXoa_HV.setDisable(false);
	}
	void MoCSDL_HV()
	{
		System.out.println("Connect to SQLServer");
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLPTGYM;user=sa;password=1");

			statement = connection.createStatement();
			System.out.println("Connect succeed!");
			LoadCSDL_HV();
			EndableButtonWhileConnectToDB_HV();

		} catch (SQLException e) {

			System.out.println(e.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	void LoadCSDL_HV()
	{
		dataHV.clear();
		tablehocvien.getColumns().clear();
		ResultSet rs;
		try {
			rs = statement.executeQuery("SELECT * FROM HOIVIEN");
			System.out.println(rs.toString());
			createtableHV();//bảng do mình tự set colum

			//đọc dữ liệu bỏ vào bảng
			while(rs.next()){
				//Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
					//Iterate Column
					row.add(rs.getString(i));
				}
				System.out.println("Row [1] added "+row );
				dataHV.add(row);
			}
			System.out.println("Số dòng dữ liệu: "+dataHV.size());
			System.out.println("Dữ liệu database: "+dataHV);
			tablehocvien.setItems(dataHV);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    void createtableHV(){
		col_mahocvien=new TableColumn<ObservableList, String>("Mã học viên");
		col_mahocvien.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> p) {
				// p.getValue() returns the Person instance for a particular TableView row
				return new SimpleStringProperty(p.getValue().get(0).toString());
			}
		});
		col_hovatenhocvien=new TableColumn<ObservableList, String>("Họ và tên");
		col_hovatenhocvien.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
				return  new SimpleStringProperty(param.getValue().get(3).toString());
			}
		});
		col_sdt= new TableColumn<ObservableList, String>("Số điện thoại");
		col_sdt.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
				return new SimpleStringProperty(param.getValue().get(6).toString());
			}
		});
		col_ngaydangky= new TableColumn<ObservableList, String>("Ngày đăng ký");
		col_ngaydangky.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
				return new SimpleStringProperty(param.getValue().get(9).toString());
			}
		});
		col_ngayketthuc= new TableColumn<ObservableList, String>("Ngày kết thúc");
		col_ngayketthuc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
				return new SimpleStringProperty(param.getValue().get(11).toString());
			}
		});
		col_songayhieuluc= new TableColumn<ObservableList, String>("Số ngày hiệu lực");
		col_songayhieuluc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
				return new SimpleStringProperty(param.getValue().get(12).toString());
			}
		});


		tablehocvien.getColumns().setAll(col_mahocvien,col_hovatenhocvien,col_sdt,col_ngaydangky,col_ngayketthuc,col_songayhieuluc);
	}
	void LoadDataFromTWToTxt_HV(int index)
	{
		ObservableList<String> row;
		row= dataHV.get(index);
		txt_sdt_HV.setText(row.get(6));
		txt_hlv_HV.setText(row.get(2));
		txt_diachi_HV.setText(row.get(5));
		txt_Ma_HV.setText(row.get(0));
		txt_hoten_HV.setText(row.get(3));
		txt_chieucao_HV.setText(row.get(13));
		txt_gioitinh_HV.setText(row.get(7));
		Image image=new Image("file:///"+row.get(8));
		imageview_HV.setImage(image);
		txt_hinhanh_HV.setText(row.get(8));
		dat_ngaysinh_HV.setValue(LocalDate.parse(row.get(4)));
		dat_ngaybatdau_HV.setValue(LocalDate.parse(row.get(10)));
		dat_ngaydangky_HV.setValue(LocalDate.parse(row.get(9)));
		dat_ngayketthuc_HV.setValue(LocalDate.parse(row.get(11)));
		txt_cannang_HV.setText(row.get(14));
		txt_songayhieuluc_HV.setText(row.get(12));
		txt_madichvu_HV.setText(row.get(1));
	}
	@FXML
	void HandleBtnSearch_HV(){
		dataHV.clear();
		tablehocvien.getColumns().clear();
		ResultSet rs=null;
		try {
			if(hocviencombox.getItems().equals("")&&txt_search_HV.getText().equals("")){
				Alert mess= new Alert(Alert.AlertType.INFORMATION);
				mess.setTitle("Thông báo");
				mess.setHeaderText("BẠN CHƯA PHÂN LOẠI VÀ NHẬP TỪ KHÓA CẦN TÌM!!!");
				mess.show();
				return;
			}else if(hocviencombox.getItems().equals("")||txt_search_HV.getText().equals("")){
				Alert mess= new Alert(Alert.AlertType.INFORMATION);
				mess.setTitle("Thông báo");
				mess.setHeaderText("BẠN CHƯA PHÂN LOẠI HOẶC CHƯA NHẬP TỪ KHÓA CẦN TÌM!!!");
				mess.show();
				return;
			}
			if(hocviencombox.getValue().equals("Mã học viên")){
				rs = statement.executeQuery("SELECT * FROM HOIVIEN WHERE MaSoHocVien="+Integer.parseInt(txt_search_HV.getText()));
			}else if(hocviencombox.getValue().equals("Số ngày hiệu lực")){
				rs = statement.executeQuery("SELECT * FROM HOIVIEN WHERE SoNgayHieuLuc="+Integer.parseInt(txt_search_HV.getText()));
			}else if(hocviencombox.getValue().equals("Số điện thoại")){
				rs = statement.executeQuery("SELECT * FROM HOIVIEN WHERE SDT="+Integer.parseInt(txt_search_HV.getText()));
			}
//			rs = statement.executeQuery("SELECT * FROM NHANVIEN WHERE MaNhanVien="+Integer.parseInt(txt_search_NV.getText()));
			createtableHV();
			//đọc dữ liệu bỏ vào bảng
			while(rs.next()){
				//Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
					//Iterate Column
					row.add(rs.getString(i));
				}
				System.out.println("Row [1] added "+row );
				dataHV.add(row);
			}
			System.out.println("Số dòng dữ liệu NHÂN VIÊN: "+dataHV.size());
			System.out.println("Dữ liệu NHÂN VIÊN : "+dataHV);
			tablehocvien.setItems(dataHV);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	void HandleTVClick_HV(MouseEvent event) {
		System.out.println("Selected Row:"+ tablehocvien.getSelectionModel().getSelectedIndex());
		btnXoa_HV.setDisable(false);
		LoadDataFromTWToTxt_HV(tablehocvien.getSelectionModel().getSelectedIndex());

	}
	@FXML
	void HandleBtnXoa_HV(ActionEvent event) {
		try {
			String mahv=txt_Ma_HV.getText();
			preparedStatement= connection.prepareStatement("DELETE FROM HOIVIEN WHERE MaSoHocVien=?");
			preparedStatement.setString(1, mahv);

			int count =preparedStatement.executeUpdate();
			if(count==1){
				Alert mess= new Alert(Alert.AlertType.INFORMATION);
				mess.setTitle("Thông báo");
				mess.setHeaderText("✍ XÓA THÀNH CÔNG ✍");
				mess.show();
			}else{
				Alert mess= new Alert(Alert.AlertType.INFORMATION);
				mess.setTitle("Thông báo");
				mess.setHeaderText("✍ XÓA KHÔNG THÀNH CÔNG ✍");
				mess.show();
			}

			LoadCSDL_HV();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	void HandleBtnClear_HV(ActionEvent event) {
		txt_sdt_HV.clear();
		txt_hlv_HV.clear();
		txt_diachi_HV.clear();
		txt_Ma_HV.clear();
		txt_hoten_HV.clear();
		txt_chieucao_HV.clear();
		txt_gioitinh_HV.clear();
		imageview_HV.setImage(null);
		txt_hinhanh_HV.clear();
		dat_ngaysinh_HV.getEditor().clear();
		dat_ngaybatdau_HV.getEditor().clear();
		dat_ngaydangky_HV.getEditor().clear();
		dat_ngayketthuc_HV.getEditor().clear();
		txt_cannang_HV.clear();
		txt_songayhieuluc_HV.clear();
		txt_madichvu_HV.clear();

	}
	@FXML
	void handleMoCSDL_HV(ActionEvent event) throws ParseException {
		System.out.println("Mở CSDL HỌC VIÊN");
		MoCSDL_HV();
	}
	@FXML
	void HandleBtnSua_HV(ActionEvent event) {
		try {
			String gioitinh=txt_gioitinh_HV.getText();
			String diachi=txt_diachi_HV.getText();
			String hinhanh=txt_hinhanh_HV.getText();
			int songayhieuluc=Integer.parseInt(txt_songayhieuluc_HV.getText());
			Float chieucao=Float.valueOf(txt_chieucao_HV.getText());
			int sdt= Integer.parseInt(txt_sdt_HV.getText());
			String ngaybatdau=dat_ngaybatdau_HV.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String ngaydangky=dat_ngaydangky_HV.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String ngayketthuc=dat_ngayketthuc_HV.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String ngaysinh = dat_ngaysinh_HV.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String hoten=txt_hoten_HV.getText();
			String madv=txt_madichvu_HV.getText();
			String mahlv=txt_hlv_HV.getText();
			Float cannang=Float.valueOf(txt_cannang_HV.getText());
			int mahv=Integer.parseInt(txt_Ma_HV.getText());

			preparedStatement= connection.prepareStatement("UPDATE HOIVIEN SET MaSoGoiDichVu=?, MaSoHuanLuyenVien=?, HoVaTen=?, NgayThangNamSinh=?, DiaChi=?, SDT=?, GioiTinh=?,HinhAnh=?,NgayDangKy=? ,NgayBatDau=?,NgayKetThuc=?," +
					"SoNgayHieuLuc=?,ChieuCao=?,CanNang=? WHERE MaSoHocVien=?");

			preparedStatement.setString(1, madv);
			preparedStatement.setString(2, mahlv);
			preparedStatement.setString(3, hoten);
			preparedStatement.setString(4, ngaysinh);
			preparedStatement.setString(5, diachi);
			preparedStatement.setInt(6, sdt);
			preparedStatement.setString(7, gioitinh);
			preparedStatement.setString(8, hinhanh);
			preparedStatement.setString(9, ngaydangky);
			preparedStatement.setString(10, ngaybatdau);
			preparedStatement.setString(11, ngayketthuc);
			preparedStatement.setInt(12, songayhieuluc);
			preparedStatement.setFloat(13, chieucao);
			preparedStatement.setFloat(14, cannang);
			preparedStatement.setFloat(15, mahv);

			int count= preparedStatement.executeUpdate();
			if(count==1){
				Alert mess= new Alert(Alert.AlertType.INFORMATION);
				mess.setTitle("Thông báo");
				mess.setHeaderText("✍ SỬA THÀNH CÔNG ✍");
				mess.show();
			}else
			{
				Alert mess= new Alert(Alert.AlertType.INFORMATION);
				mess.setTitle("Thông báo");
				mess.setHeaderText("✍ SỬA KHÔNG THÀNH CÔNG ✍");
				mess.show();
			}
			LoadCSDL_HV();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    @FXML
    void HandleBtnThem_HV(ActionEvent event) {
        try {
            String gioitinh=txt_gioitinh_HV.getText();
            String diachi=txt_diachi_HV.getText();
            String hinhanh=txt_hinhanh_HV.getText();
            int songayhieuluc=Integer.parseInt(txt_songayhieuluc_HV.getText());
            Float chieucao=Float.valueOf(txt_chieucao_HV.getText());
            int sdt= Integer.parseInt(txt_sdt_HV.getText());
            String ngaybatdau=dat_ngaybatdau_HV.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String ngaydangky=dat_ngaydangky_HV.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String ngayketthuc=dat_ngayketthuc_HV.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String ngaysinh = dat_ngaysinh_HV.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String hoten=txt_hoten_HV.getText();
            String madv=txt_madichvu_HV.getText();
            String mahlv=txt_hlv_HV.getText();
            Float cannang=Float.valueOf(txt_cannang_HV.getText());

//			String sql="INSERT INTO NHANVIEN " +
//					"VALUES (N'?',?,?,N'?','?',N'?',?,N'?',N'?')";
            preparedStatement= connection.prepareStatement("INSERT INTO NHANVIEN " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1, madv);
            preparedStatement.setString(2, mahlv);
            preparedStatement.setString(3, hoten);
            preparedStatement.setString(4, ngaysinh);
            preparedStatement.setString(5, diachi);
            preparedStatement.setInt(6, sdt);
            preparedStatement.setString(7, gioitinh);
            preparedStatement.setString(8, hinhanh);
            preparedStatement.setString(9, ngaydangky);
            preparedStatement.setString(10, ngaybatdau);
            preparedStatement.setString(11, ngayketthuc);
            preparedStatement.setInt(12, songayhieuluc);
            preparedStatement.setFloat(13, chieucao);
            preparedStatement.setFloat(14, cannang);

            int ok=preparedStatement.executeUpdate();
            if(ok==1){
                Alert mess= new Alert(Alert.AlertType.INFORMATION);
                mess.setTitle("Thông báo");
                mess.setHeaderText("✍ THÊM THÀNH CÔNG ✍");
                mess.show();
            }else{
                Alert mess= new Alert(Alert.AlertType.INFORMATION);
                mess.setTitle("Thông báo");
                mess.setHeaderText("☹ THÊM KHÔNG THÀNH CÔNG ☹");
                mess.show();
            }

            LoadCSDL_NV();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML
    void HandleBtnchonanh_HV() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
        File file = fileChooser.showOpenDialog(null);

        if(file!=null) {
            txt_hinhanh_HV.setText(file.getAbsolutePath());
        }
    }
  //*****************************thêm cơ sở dữ liệu vào giao diện dịch vụ**************************************
    @FXML
    private TableView tabledichvu;
    @FXML
    private TableColumn<ObservableList, String> col_madichvu;
    @FXML
    private TableColumn<ObservableList, String> col_dichvu_songayhieuluc;
    @FXML
    private TableColumn<ObservableList, String> col_mota;
    @FXML
    private TableColumn<ObservableList, String> col_gia;
    ObservableList<ObservableList> dataDV=FXCollections.observableArrayList();

    @FXML
    Button btnSua_DV;
    @FXML
    Button btnThem_DV;
    @FXML
    Button btnXoa_DV;
    @FXML
    Button btnClear_DV;
    @FXML
    Button btnOPENDB_DV;
    @FXML
    Button btnSearch_DV;

    @FXML
    TextField txt_Ma_DV;
    @FXML
    TextField txt_motagoi_DV;
    @FXML
    TextField txt_songayhieuluc_DV;
    @FXML
    TextField txt_giagoi_DV;
    @FXML
    TextField txt_search_DV;


    void DisableButtonWhileNotConnectToDB_DV()
    {
        btnSua_DV.setDisable(true);
        btnThem_DV.setDisable(true);
        btnXoa_DV.setDisable(true);
        btnSearch_DV.setDisable(true);
    }
    void EndableButtonWhileConnectToDB_DV()
    {
        btnSua_DV.setDisable(false);
        btnThem_DV.setDisable(false);
        btnXoa_DV.setDisable(false);
        btnSearch_DV.setDisable(false);
    }
    void MoCSDL_DV()
    {
        System.out.println("Connect to SQLServer");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLPTGYM;user=sa;password=1");

            statement = connection.createStatement();
            System.out.println("Connect succeed!");
            LoadCSDL_DV();
            EndableButtonWhileConnectToDB_DV();

        } catch (SQLException e) {

            System.out.println(e.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
	void LoadCSDL_DV()
	{
		dataDV.clear();
		tabledichvu.getColumns().clear();
		ResultSet rs;
		try {

			rs = statement.executeQuery("SELECT * FROM DICHVU");
			System.out.println(rs.toString());
			createtableDV();
			//đọc dữ liệu bỏ vào bảng
			while(rs.next()){
				//Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
					//Iterate Column
					row.add(rs.getString(i));
				}
				System.out.println("Row [1] added "+row );
				dataDV.add(row);

			}
			System.out.println("Số dòng dữ liệu DỊCH VỤ: "+dataDV.size());
			System.out.println("Dữ liệu DỊCH VỤ: "+dataDV);
			tabledichvu.setItems(dataDV);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void createtableDV(){
		col_madichvu=new TableColumn<ObservableList, String>("Mã dịch vụ");
		col_madichvu.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> p) {
				// p.getValue() returns the Person instance for a particular TableView row
				return new SimpleStringProperty(p.getValue().get(0).toString());
			}
		});

		col_dichvu_songayhieuluc=new TableColumn<ObservableList, String>("Số ngày hiệu lực");
		col_dichvu_songayhieuluc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
				return  new SimpleStringProperty(param.getValue().get(2).toString());
			}
		});

		col_mota= new TableColumn<ObservableList, String>("Mô tả gói");
		col_mota.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
				return new SimpleStringProperty(param.getValue().get(1).toString());
			}
		});
		col_gia= new TableColumn<ObservableList, String>("Giá");
		col_gia.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
				return new SimpleStringProperty(param.getValue().get(3).toString());
			}
		});



		tabledichvu.getColumns().setAll(col_madichvu,col_dichvu_songayhieuluc,col_mota,col_gia);
	}
    void LoadDataFromTWToTxt_DV(int index)
    {
        ObservableList<String> row;
        row= dataDV.get(index);
        txt_Ma_DV.setText(row.get(0));
        txt_motagoi_DV.setText(row.get(1));
        txt_songayhieuluc_DV.setText(row.get(2));
        txt_giagoi_DV.setText(row.get(3));
    }


    @FXML
    void HandleTVClick_DV(MouseEvent event) {
        System.out.println("Selected Row:"+ tabledichvu.getSelectionModel().getSelectedIndex());
        btnXoa_DV.setDisable(false);
        LoadDataFromTWToTxt_DV(tabledichvu.getSelectionModel().getSelectedIndex());

    }
    @FXML
    void HandleBtnSearch_DV(){
        dataDV.clear();
        tabledichvu.getColumns().clear();
        ResultSet rs=null;
        try {
            if(dichvucombox.getItems().equals("")&&txt_search_DV.getText().equals("")){
                Alert mess= new Alert(Alert.AlertType.INFORMATION);
                mess.setTitle("Thông báo");
                mess.setHeaderText("BẠN CHƯA PHÂN LOẠI VÀ NHẬP TỪ KHÓA CẦN TÌM!!!");
                mess.show();
                return;
            }else if(dichvucombox.getItems().equals("")||txt_search_DV.getText().equals("")){
                Alert mess= new Alert(Alert.AlertType.INFORMATION);
                mess.setTitle("Thông báo");
                mess.setHeaderText("BẠN CHƯA PHÂN LOẠI HOẶC CHƯA NHẬP TỪ KHÓA CẦN TÌM!!!");
                mess.show();
                return;
            }
            if(dichvucombox.getValue().equals("Mã dịch vụ")){
                rs = statement.executeQuery("SELECT * FROM DICHVU WHERE MaSoGoiDichVu="+Integer.parseInt(txt_search_DV.getText()));
            }else if(dichvucombox.getValue().equals("Số ngày hiệu lực")){
                rs = statement.executeQuery("SELECT * FROM DICHVU WHERE SoNgayHieuLuc="+Integer.parseInt(txt_search_DV.getText()));
            }else if(dichvucombox.getValue().equals("Giá")){
                rs = statement.executeQuery("SELECT * FROM DICHVU WHERE GiaGoi="+Integer.parseInt(txt_search_DV.getText()));
            }
            createtableDV();
            //đọc dữ liệu bỏ vào bảng
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                dataDV.add(row);
            }
            System.out.println("Số dòng dữ liệu NHÂN VIÊN: "+dataDV.size());
            System.out.println("Dữ liệu NHÂN VIÊN : "+dataDV);
            tabledichvu.setItems(dataDV);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML
    void HandleBtnXoa_DV(ActionEvent event) {
        try {
            String madv=txt_Ma_DV.getText();
            preparedStatement= connection.prepareStatement("DELETE FROM DICHVU WHERE MaSoGoiDichVu=?");
            preparedStatement.setString(1, madv);

            int count =preparedStatement.executeUpdate();
            if(count==1){
                Alert mess= new Alert(Alert.AlertType.INFORMATION);
                mess.setTitle("Thông báo");
                mess.setHeaderText("✍ XÓA THÀNH CÔNG ✍");
                mess.show();
            }else{
                Alert mess= new Alert(Alert.AlertType.INFORMATION);
                mess.setTitle("Thông báo");
                mess.setHeaderText("✍ XÓA KHÔNG THÀNH CÔNG ✍");
                mess.show();
            }

            LoadCSDL_DV();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML
    void HandleBtnClear_DV(ActionEvent event) {
        txt_Ma_DV.clear();
        txt_motagoi_DV.clear();
        txt_songayhieuluc_DV.clear();
        txt_giagoi_DV.clear();
        txt_search_DV.clear();
    }
    @FXML
    void handleMoCSDL_DV(ActionEvent event) throws ParseException {
        System.out.println("Mở CSDL DỊCH VỤ");
        MoCSDL_DV();
    }
    @FXML
    void HandleBtnSua_DV(ActionEvent event) {
        try {
            int madv=Integer.parseInt(txt_Ma_DV.getText());
            String motagoi=txt_motagoi_DV.getText();
            int songayhieuluc=Integer.parseInt(txt_songayhieuluc_DV.getText());
            float giagoi=Float.valueOf(txt_giagoi_DV.getText());

            preparedStatement= connection.prepareStatement("UPDATE DICHVU SET MoTaGoi=?, SoNgayHieuLuc=?, GiaGoi=? WHERE MaSoGoiDichVu=?");

            preparedStatement.setString(1, motagoi);
            preparedStatement.setInt(2, songayhieuluc);
            preparedStatement.setFloat(3, giagoi);
            preparedStatement.setInt(4, madv);


            int count= preparedStatement.executeUpdate();
            if(count==1){
                Alert mess= new Alert(Alert.AlertType.INFORMATION);
                mess.setTitle("Thông báo");
                mess.setHeaderText("✍ SỬA THÀNH CÔNG ✍");
                mess.show();
            }else
            {
                Alert mess= new Alert(Alert.AlertType.INFORMATION);
                mess.setTitle("Thông báo");
                mess.setHeaderText("✍ SỬA KHÔNG THÀNH CÔNG ✍");
                mess.show();
            }
            LoadCSDL_DV();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML
    void HandleBtnThem_DV(ActionEvent event) {
        try {
            String motagoi=txt_motagoi_DV.getText();
            int songayhieuluc=Integer.parseInt(txt_songayhieuluc_DV.getText());
            float giagoi=Float.valueOf(txt_giagoi_DV.getText());

//			String sql="INSERT INTO NHANVIEN " +
//					"VALUES (N'?',?,?,N'?','?',N'?',?,N'?',N'?')";
            preparedStatement= connection.prepareStatement("INSERT INTO DICHVU " +
                    "VALUES (?,?,?)");

            preparedStatement.setString(1, motagoi);
            preparedStatement.setInt(2, songayhieuluc);
            preparedStatement.setFloat(3, giagoi);

            int ok=preparedStatement.executeUpdate();
            if(ok==1){
                Alert mess= new Alert(Alert.AlertType.INFORMATION);
                mess.setTitle("Thông báo");
                mess.setHeaderText("✍ THÊM THÀNH CÔNG ✍");
                mess.show();
            }else{
                Alert mess= new Alert(Alert.AlertType.INFORMATION);
                mess.setTitle("Thông báo");
                mess.setHeaderText("☹ THÊM KHÔNG THÀNH CÔNG ☹");
                mess.show();
            }

            LoadCSDL_DV();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
  //*****************************thêm cơ sở dữ liệu vào giao diện cơ sở vật chất**************************************
    @FXML
    private TableView tablethietbi;
    @FXML
    private TableColumn<ObservableList, String> col_mathietbi;
    @FXML
    private TableColumn<ObservableList, String> col_tenthietbi;
    @FXML
    private TableColumn<ObservableList, String> col_tinhtrang;
    @FXML
    private TableColumn<ObservableList, String> col_ngaysanxuat;
    @FXML
    private TableColumn<ObservableList, String> col_giathietbi;


    @FXML
    Button btnSua_TB;
    @FXML
    Button btnThem_TB;
    @FXML
    Button btnXoa_TB;
    @FXML
    Button btnClear_TB;
    @FXML
    Button btnOPENDB_TB;
    @FXML
    Button btnSearch_TB;


    @FXML
    TextField txt_Ma_TB;
    @FXML
    TextField txt_tenthietbi_TB;
    @FXML
    TextField txt_tinhtrang_TB;
    @FXML
    DatePicker dat_ngaysanxuat_TB;
    @FXML
    TextField txt_hinhanh_TB;
    @FXML
    TextField txt_giatb_TB;
    @FXML
    TextField txt_search_TB;
    @FXML
    ImageView imageview_TB;

    ObservableList<ObservableList> dataTB=FXCollections.observableArrayList();


    void DisableButtonWhileNotConnectToDB_TB()
    {
        btnSua_TB.setDisable(true);
        btnThem_TB.setDisable(true);
        btnXoa_TB.setDisable(true);
        btnSearch_TB.setDisable(true);
    }
    void EndableButtonWhileConnectToDB_TB()
    {
        btnSua_TB.setDisable(false);
        btnThem_TB.setDisable(false);
        btnXoa_TB.setDisable(false);
        btnSearch_TB.setDisable(false);
    }
    void MoCSDL_TB()
    {
        System.out.println("Connect to SQLServer");
  
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            connection= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLPTGYM;user=sa;password=1");
//
//            statement = connection.createStatement();
//            System.out.println("Connect succeed!");
        	LoadCSDL_TB();
            EndableButtonWhileConnectToDB_TB();

      

    }
	void LoadCSDL_TB()
	{
		dataTB.clear();
		tablethietbi.getColumns().clear();

				QueryServer query = new QueryServer("SELECT", "ThietBiPhongTap", "*", "*");
				listdata list = org.server.Client.App.Query(query);
				//ObservableList<ObservableList> data=FXCollections.observableArrayList();
				dataTB= list.getThietBiOb();
				System.out.print(dataTB.toString());
//			rs = statement.executeQuery("SELECT * FROM COSOVATCHAT");
//			System.out.println(rs.toString());
//			createtableTB();
//			//đọc dữ liệu bỏ vào bảng
//			while(rs.next()){
//				//Iterate Row
//				ObservableList<String> row = FXCollections.observableArrayList();
//				for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
//					//Iterate Column
//					row.add(rs.getString(i));
//				}
//				System.out.println("Row [1] added "+row );
//				dataTB.add(row);
//
//			}
//			System.out.println("Số dòng dữ liệu THIẾT BỊ: "+dataTB.size());
//			System.out.println("Dữ liệu THIẾT BỊ: "+dataTB);
		tablethietbi.setItems(dataTB);


	}
	void createtableTB(){
		col_mathietbi=new TableColumn<ObservableList, String>("Mã thiết bị");
		col_mathietbi.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> p) {
				// p.getValue() returns the Person instance for a particular TableView row
				return new SimpleStringProperty(p.getValue().get(0).toString());
			}
		});

		col_tenthietbi=new TableColumn<ObservableList, String>("Tên thiết bị");
		col_tenthietbi.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
				return  new SimpleStringProperty(param.getValue().get(1).toString());
			}
		});

		col_tinhtrang= new TableColumn<ObservableList, String>("Tình trạng ");
		col_tinhtrang.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
				return new SimpleStringProperty(param.getValue().get(2).toString());
			}
		});
		col_ngaysanxuat= new TableColumn<ObservableList, String>("Ngày sản xuất");
		col_ngaysanxuat.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
				return new SimpleStringProperty(param.getValue().get(3).toString());
			}
		});

		col_giathietbi= new TableColumn<ObservableList, String>("Giá");
		col_giathietbi.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
			
			public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
				return new SimpleStringProperty(param.getValue().get(5).toString());
			}
		});


		tablethietbi.getColumns().setAll(col_mathietbi,col_tenthietbi,col_tinhtrang,col_ngaysanxuat,col_giathietbi);
	}
    void LoadDataFromTWToTxt_TB(int index)
    {
        ObservableList<String> row;
        row= dataTB.get(index);
        txt_Ma_TB.setText(row.get(0));
        txt_tenthietbi_TB.setText(row.get(1));
        txt_tinhtrang_TB.setText(row.get(2));
        dat_ngaysanxuat_TB.setValue(LocalDate.parse(row.get(3)));
        txt_hinhanh_TB.setText(row.get(4));
        Image image=new Image("file:///"+row.get(4));
        imageview_TB.setImage(image);
        txt_giatb_TB.setText(row.get(5));
    }



    @FXML
    void HandleTVClick_TB(MouseEvent event) {
        System.out.println("Selected Row:"+ tablethietbi.getSelectionModel().getSelectedIndex());
        btnXoa_TB.setDisable(false);
        LoadDataFromTWToTxt_TB(tablethietbi.getSelectionModel().getSelectedIndex());

    }
    @FXML
    void HandleBtnSearch_TB(){
        dataTB.clear();
        tablethietbi.getColumns().clear();
        ResultSet rs=null;
        try {
            if(thietbicombox.getItems().equals("")&&txt_search_TB.getText().equals("")){
                Alert mess= new Alert(Alert.AlertType.INFORMATION);
                mess.setTitle("Thông báo");
                mess.setHeaderText("BẠN CHƯA PHÂN LOẠI VÀ NHẬP TỪ KHÓA CẦN TÌM!!!");
                mess.show();
                return;
            }else if(thietbicombox.getItems().equals("")||txt_search_TB.getText().equals("")){
                Alert mess= new Alert(Alert.AlertType.INFORMATION);
                mess.setTitle("Thông báo");
                mess.setHeaderText("BẠN CHƯA PHÂN LOẠI HOẶC CHƯA NHẬP TỪ KHÓA CẦN TÌM!!!");
                mess.show();
                return;
            }
            if(thietbicombox.getValue().equals("Mã dịch vụ")){
                rs = statement.executeQuery("SELECT * FROM DICHVU WHERE MaSoGoiDichVu="+Integer.parseInt(txt_search_TB.getText()));
            }else if(thietbicombox.getValue().equals("Số ngày hiệu lực")){
                rs = statement.executeQuery("SELECT * FROM DICHVU WHERE SoNgayHieuLuc="+Integer.parseInt(txt_search_TB.getText()));
            }else if(thietbicombox.getValue().equals("Giá")){
                rs = statement.executeQuery("SELECT * FROM DICHVU WHERE GiaGoi="+Integer.parseInt(txt_search_TB.getText()));
            }
            createtableTB();
            //đọc dữ liệu bỏ vào bảng
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                dataTB.add(row);
            }
            System.out.println("Số dòng dữ liệu THIẾT BỊ: "+dataTB.size());
            System.out.println("Dữ liệu THIẾT BỊ : "+dataTB);
            tablethietbi.setItems(dataTB);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML
    void HandleBtnXoa_TB(ActionEvent event) {
        try {
            String matb=txt_Ma_TB.getText();
            preparedStatement= connection.prepareStatement("DELETE FROM COSOVATCHAT WHERE MaThietBi=?");
            preparedStatement.setString(1, matb);

            int count =preparedStatement.executeUpdate();
            if(count==1){
                Alert mess= new Alert(Alert.AlertType.INFORMATION);
                mess.setTitle("Thông báo");
                mess.setHeaderText("✍ XÓA THÀNH CÔNG ✍");
                mess.show();
            }else{
                Alert mess= new Alert(Alert.AlertType.INFORMATION);
                mess.setTitle("Thông báo");
                mess.setHeaderText("✍ XÓA KHÔNG THÀNH CÔNG ✍");
                mess.show();
            }

            LoadCSDL_DV();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML
    void HandleBtnClear_TB(ActionEvent event) {
        txt_Ma_TB.clear();
        txt_tenthietbi_TB.clear();
        txt_tinhtrang_TB.clear();
        dat_ngaysanxuat_TB.setValue(null);
        txt_hinhanh_TB.clear();
        txt_giatb_TB.clear();
        txt_search_TB.clear();
    }
    @FXML
    void handleMoCSDL_TB(ActionEvent event) throws ParseException {
        System.out.println("Mở CSDL THIẾT BỊ");
        MoCSDL_TB();
    }
    @FXML
    void HandleBtnSua_TB(ActionEvent event) {
        try {
            int matb=Integer.parseInt(txt_Ma_TB.getText());
            String txt_Ma_TB=txt_motagoi_DV.getText();
            String tenthietbi=txt_tenthietbi_TB.getText();
            String tinhtrang=txt_tinhtrang_TB.getText();
            String ngaysanxuat=dat_ngaysanxuat_TB.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String hinhanh=txt_hinhanh_TB.getText();
            float giatb=Float.valueOf(txt_giatb_TB.getText());

            preparedStatement= connection.prepareStatement("UPDATE COSOVATCHAT SET TenThietBi=?, TinhTrang=?, NgaySanXuat=?,HinhAnh=?,Gia=? WHERE MaThietBi=?");

            preparedStatement.setString(1, tenthietbi);
            preparedStatement.setString(2, tinhtrang);
            preparedStatement.setString(3, ngaysanxuat);
            preparedStatement.setString(4, hinhanh);
            preparedStatement.setFloat(5, giatb);
            preparedStatement.setInt(6, matb);


            int count= preparedStatement.executeUpdate();
            if(count==1){
                Alert mess= new Alert(Alert.AlertType.INFORMATION);
                mess.setTitle("Thông báo");
                mess.setHeaderText("✍ SỬA THÀNH CÔNG ✍");
                mess.show();
            }else
            {
                Alert mess= new Alert(Alert.AlertType.INFORMATION);
                mess.setTitle("Thông báo");
                mess.setHeaderText("✍ SỬA KHÔNG THÀNH CÔNG ✍");
                mess.show();
            }
            LoadCSDL_TB();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML
    void HandleBtnThem_TB(ActionEvent event) {
        try {
            String tenthietbi=txt_tenthietbi_TB.getText();
            String tinhtrang=txt_tinhtrang_TB.getText();
            String ngaysanxuat=dat_ngaysanxuat_TB.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String hinhanh=txt_hinhanh_TB.getText();
            float giatb=Float.valueOf(txt_giatb_TB.getText());



            preparedStatement= connection.prepareStatement("INSERT INTO COSOVATCHAT " +
                    "VALUES (?,?,?,?,?)");

            preparedStatement.setString(1, tenthietbi);
            preparedStatement.setString(2, tinhtrang);
            preparedStatement.setString(3, ngaysanxuat);
            preparedStatement.setString(4, hinhanh);
            preparedStatement.setFloat(5, giatb);

            int ok=preparedStatement.executeUpdate();
            if(ok==1){
                Alert mess= new Alert(Alert.AlertType.INFORMATION);
                mess.setTitle("Thông báo");
                mess.setHeaderText("✍ THÊM THÀNH CÔNG ✍");
                mess.show();
            }else{
                Alert mess= new Alert(Alert.AlertType.INFORMATION);
                mess.setTitle("Thông báo");
                mess.setHeaderText("☹ THÊM KHÔNG THÀNH CÔNG ☹");
                mess.show();
            }

            LoadCSDL_TB();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML
    void HandleBtnchonanh_TB() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
        File file = fileChooser.showOpenDialog(null);

        if(file!=null) {
            txt_hinhanh_TB.setText(file.getAbsolutePath());
        }
    }
    //Show Giao diện chuẩn đoán

    @FXML
    public void giaodienchuandoan() throws IOException {
    	Parent root=FXMLLoader.load(getClass().getResource("/application/GiaoDienChuanDoan.fxml"));
		Scene scene=new Scene(root);
		Stage stage=new Stage(StageStyle.DECORATED);
		stage.setTitle("Chuẩn đoán cơ thể");
		stage.setScene(scene);
		stage.show();
    }
}
