package application;

import NhanVienHocVien.HocVien;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class Controller_GiaoDienChuanDoan implements Initializable {
    @FXML
    Button chuandoancothe;
    @FXML
    Text cothe;
    @FXML
    TextField proteinmin;
    @FXML
    TextField proteinmax;
    @FXML
    TextField fatmmin;
    @FXML
    TextField fatmax;
    @FXML
    TextField cardsmin;
    @FXML
    TextField cardsmax;
    @FXML
    TextField luachon;

//    @FXML
//    public void chuandoan() {
//        HocVien hv=tablehocvien.getItems().get(tablehocvien.getSelectionModel().getSelectedIndex());
//        Calendar NamHienTai= GregorianCalendar.getInstance();
//        int Tuoi=NamHienTai.get(Calendar.YEAR) - hv.LayNgayThangNamSinh().get(GregorianCalendar.YEAR);
//        double BMI;
//        BMI=hv.LayCanNang()/(hv.LayChieuCao()*hv.LayChieuCao());
//        if(BMI<18.5) {
//            cothe.setText("Bạn gầy");
//        }else if(18.5<BMI||BMI<25) {
//            cothe.setText("Bạn vừa người, sức khỏe tốt");
//        }else if(25<BMI||BMI<30) {
//            cothe.setText("Bạn thừa cân");
//        }else if(BMI>30) {
//            cothe.setText("Bạn bị béo phì");
//        }
//        double TyLePhanTramMo;
//        if(hv.LayGioiTinh().equals("Nam")) {
//            TyLePhanTramMo=(1.2*BMI)+(0.23*Tuoi)-(10.8*1)-5.4;
//        }else {
//            TyLePhanTramMo=(1.2*BMI)+(0.23*Tuoi)-(10.8*0)-5.4;
//        }
////		double LBM=(this.CanNang*(100-TyLePhanTramMo))/100;
//        double BMR=0;
//        if(hv.LayGioiTinh().equals("Nam")) {
//            BMR=(13.397 * hv.LayCanNang()) + (4.799 * hv.LayChieuCao()) - (5.677 * Tuoi) + 88.362;
//        }
//        else {
//            BMR=(9.247  * hv.LayCanNang()) + (3.098  * hv.LayChieuCao()) - (4.330 * Tuoi) + 447.593;
//        }
//        //TDEE là lượng calo tối thiểu tiêu thụ trong 1 ngày= BMR*1.5
//        int x;
//        double TDEE=0;
//        if(Integer.parseInt(luachon.getText())==0) {
//            TDEE=BMR*1.2;
//        }else if(Integer.parseInt(luachon.getText())==01) {
//            TDEE=BMR*1.375;
//        }else if(Integer.parseInt(luachon.getText())==02) {
//            TDEE=BMR*1.55;
//            System.out.println(2);
//        }else if(Integer.parseInt(luachon.getText())==03) {
//            TDEE=BMR*1.725;
//        }else if(Integer.parseInt(luachon.getText())==04) {
//            TDEE=BMR*1.9;
//        }else {
//            return;
//        }
//
//        System.out.println(" tdee: "+TDEE);
//        System.out.println(" bmr: "+BMR);
//
//        double LuongMo =hv.LayCanNang()*(TyLePhanTramMo/100);
//        System.out.println("Lượng Mỡ: "+LuongMo);
//        double CanNac=hv.LayCanNang()-LuongMo;
//        //Các giá trị dinh dưỡng tối thiểu cần nạp vào cơ thể(Có thể xuất cho khách hàng biết) ->chế độ ăn phù hợp
//        proteinmin.setText(String.valueOf((int)Math.ceil(2.2*CanNac)));
//        double pCaloMax=(int)Math.ceil(2.2*CanNac)*4;
//        proteinmax.setText(String.valueOf((int)Math.ceil(3.3*CanNac)));
//        double pCaloMin=(int)Math.ceil(3.3*CanNac)*4;
//
//
//        fatmmin.setText(String.valueOf((int)Math.ceil(0.77*CanNac)));
//        double fCaloMin=(int)Math.ceil(0.77*CanNac)*9;
//        fatmax.setText(String.valueOf((int)Math.ceil(1.1*CanNac)));
//        double fCaloMax=(int)Math.ceil(1.1*CanNac)*9;
//        //Trung bình giữa BMR và TDEE
//        double TrungBinh=(BMR+TDEE)/2;
//        System.out.println("TrungBinh: "+TrungBinh);
//
//
//        double cCaloMin=TrungBinh-(pCaloMin+fCaloMin);
//        System.out.println("Calo min protein: "+pCaloMin);
//        System.out.println("Calo min fat: "+fCaloMin);
//        double cCaloMax=TrungBinh-(pCaloMax+fCaloMax);
//        cardsmin.setText(String.valueOf((int)Math.ceil(cCaloMin/4)));
//        cardsmax.setText(String.valueOf((int)Math.ceil(cCaloMax/4)));
//    }

    public void initialize(URL location, ResourceBundle resources) {

    }

}
