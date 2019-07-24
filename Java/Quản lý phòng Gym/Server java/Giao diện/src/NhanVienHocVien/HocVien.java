package NhanVienHocVien;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;

import NhanVienHocVien.GiaTriDinhDuong;

import NhanVienHocVien.GiaTriDinhDuong.GiaTri;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HocVien extends Nguoi implements Serializable{
	protected StringProperty MaSoHocVien;
	protected StringProperty MaSoGoiDichVu;
	protected String MaSoHuanLuyenVien;
	protected StringProperty NgayDangKy;
	protected StringProperty NgayBatDau;
	protected StringProperty NgayKetThuc;
	protected IntegerProperty SoNgayHieuLuc;
	protected double ChieuCao;
	protected double CanNang;
	Scanner s= new Scanner(System.in);
//	public HocVien() {
//		super();
//		this.GanMaSoHocVien("null");
//		this.GanMaSoGoiDichVu("null");
//		this.GanMaSoHuanLuyenVien("null");
//		GregorianCalendar d = (GregorianCalendar) GregorianCalendar.getInstance();
//		this.GanNgayDangKy(d.get(Calendar.YEAR), d.get(Calendar.MONTH), d.get(Calendar.DAY_OF_MONTH));
//		this.GanNgayBatDau(1970, 1, 1);
//		this.GanNgayKetThuc(1970, 1, 1);
//		this.GanSoNgayHieuLuc(0);  // phải gán =0 ;
//		this.CanNang=0;
//		this.ChieuCao=0;
////		TaoDuLieu();
//	}

	
	
	public HocVien(String hoVaTen, GregorianCalendar ngayThangNamSinh, String diaChi, int sDT,String maSoHocVien, String maSoGoiDichVu, 
			String maSoHuanLuyenVien, String ngayDangKy,
			String ngayBatDau, String ngayKetThuc, int soNgayHieuLuc, String gioiTinh,String hinhanh,
			double chieuCao, double canNang) {
		super(hoVaTen,ngayThangNamSinh,diaChi,sDT,gioiTinh,hinhanh);
		this.GanMaSoHocVien(maSoHocVien);
		this.GanMaSoGoiDichVu(maSoGoiDichVu);
		this.GanMaSoHuanLuyenVien(maSoHuanLuyenVien);
		this.GanNgayDangKy(ngayDangKy);
		this.GanNgayBatDau(ngayBatDau);
		this.GanNgayKetThuc(ngayKetThuc);
		this.GanSoNgayHieuLuc(soNgayHieuLuc);
		this.GanGioiTinh(gioiTinh);
		this.GanCanNang(chieuCao);
		this.GanHinhAnh(hinhanh);
		this.GanChieuCao(canNang);
//		TaoDuLieu();
	}



	public double LayChieuCao() {
		return ChieuCao;
	}

	public void GanChieuCao(double chieuCao) {
		ChieuCao = chieuCao;
	}

	public double LayCanNang() {
		return CanNang;
	}

	public void GanCanNang(double canNang) {
		CanNang = canNang;
	}
	public StringProperty MaSoHocVienProperty() {
        if (MaSoHocVien == null) MaSoHocVien = new SimpleStringProperty(this, "MaSoHocVien");
        return MaSoHocVien;
    }
	public String LayMaSoHocVien() {
		return MaSoHocVienProperty().get();
	}

	public void GanMaSoHocVien(String maSoHocVien) {
		MaSoHocVienProperty().set(maSoHocVien);
	}
	public StringProperty NgayDangKyProperty() {
        if (NgayDangKy == null) NgayDangKy = new SimpleStringProperty(this, "NgayDangKy");
        return NgayDangKy;
    }
	public String LayNgayDangKy() {
		return NgayDangKyProperty().get();
	}

	public void GanNgayDangKy(String ngayDangKy) {
		NgayDangKyProperty().set(ngayDangKy);
	}
	public StringProperty MaSoGoiDichVuProperty() {
        if (MaSoGoiDichVu == null) MaSoGoiDichVu = new SimpleStringProperty(this, "MaSoGoiDichVu");
        return MaSoGoiDichVu;
    }
	public String LayMaSoGoiDichVu() {
		return MaSoGoiDichVuProperty().get();
	}

	public void GanMaSoGoiDichVu(String maSoGoiDichVu) {
		MaSoGoiDichVuProperty().set(maSoGoiDichVu);
	}

	public String LayMaSoHuanLuyenVien() {
		return MaSoHuanLuyenVien;
	}

	public void GanMaSoHuanLuyenVien(String maSoHuanLuyenVien) {
		MaSoHuanLuyenVien = maSoHuanLuyenVien;
	}
	public StringProperty NgayBatDauProperty() {
        if (NgayBatDau == null) NgayBatDau = new SimpleStringProperty(this, "NgayBatDau");
        return NgayBatDau;
    }
	public String LayNgayBatDau() {
		return NgayBatDauProperty().get();
	}

	public void GanNgayBatDau(String ngayBatDau) {
		NgayBatDauProperty().set(ngayBatDau);
	}
	public StringProperty NgayKetThucProperty() {
        if (NgayKetThuc == null) NgayKetThuc = new SimpleStringProperty(this, "NgayKetThuc");
        return NgayKetThuc;
    }
	public String LayNgayKetThuc() {
		return NgayKetThucProperty().get();
	}

	public void GanNgayKetThuc(String ngayKetThuc) {
		NgayKetThucProperty().set(ngayKetThuc);
	}
	public int LaySoNgayHieuLuc() {
		return SoNgayHieuLucProperty().get();
	}
	public IntegerProperty SoNgayHieuLucProperty() {
        if (SoNgayHieuLuc == null) SoNgayHieuLuc = new SimpleIntegerProperty(this, "SoNgayHieuLuc");
        return SoNgayHieuLuc;
    }
	public void GanSoNgayHieuLuc(int soNgayHieuLuc) {
		SoNgayHieuLucProperty().set(soNgayHieuLuc);;
	}

//	public HashMap TruyVanThongTin() {
//		HashMap ThongTin = new HashMap();
//		ThongTin.putAll(super.TruyVanThongTin());
//		ThongTin.put("Mã số học viên",this.MaSoHocVien);
//		ThongTin.put("Mã số gói dịch vụ",this.MaSoGoiDichVu);
//		ThongTin.put("Mã số huấn luyện viên",this.MaSoHuanLuyenVien);
//		ThongTin.put("Ngày đăng ký",this.NgayDangKy);
//		ThongTin.put("Ngày bắt đầu",this.NgayBatDau);
//		ThongTin.put("Ngày kết thúc",this.NgayKetThuc);
//		ThongTin.put("Số ngày hiệu lực",this.SoNgayHieuLuc);
//		ThongTin.put("Cân Nặng",this.CanNang);
//		ThongTin.put("Chiều Cao",this.ChieuCao);
//		return ThongTin;
//	}
//	public String toString() {
//		SimpleDateFormat ft= new SimpleDateFormat("dd/MM/yyyy");
//		return String.format("\n%s\n%s : %s\n%s : %s\n%s : %s\n%s : %s\n%s : %s\n%s : %s\n%s : %d\n%s : %s\n%s : %.2f\n%s : %.2f",super.toString(), "Mã số học viên",this.MaSoHocVien,"Mã số gói dịch vụ",this.MaSoGoiDichVu,"Mã số huấn luyện viên",this.MaSoHuanLuyenVien,"Ngày đăng ký",ft.format(this.NgayDangKy.getTime()),"Ngày bắt đầu",ft.format(this.NgayBatDau.getTime()),"Ngày kết thúc",ft.format(this.NgayKetThuc.getTime()),"Số ngày hiệu lực",this.SoNgayHieuLuc,"Giới Tính",this.GioiTinh,"Cân Nặng",this.CanNang,"Chiều Cao",this.ChieuCao);
//	}
//	public void DangKyGoiDichVu(String MaGoi,int SoNgayHieuLuc)
//	{
//		GanMaSoGoiDichVu(MaGoi);
//		GregorianCalendar HomNay = new GregorianCalendar();
//		GanNgayBatDau(HomNay.get(Calendar.YEAR), HomNay.get(Calendar.MONTH), HomNay.get(Calendar.DAY_OF_MONTH));
//		HomNay.add(Calendar.DAY_OF_MONTH, SoNgayHieuLuc);// sau khi có class gói 30->số ngày của gói
//		GanNgayKetThuc(HomNay.get(Calendar.YEAR), HomNay.get(Calendar.MONTH), HomNay.get(Calendar.DAY_OF_MONTH));
////		CapNhatDuLieu();
//	}

//	public void GiaHanGoiDichVu(int SoNgayHieuLuc)// THam số là thời gian hiệu lực của gói dịch vụ gia hạn
//	{
//		this.NgayKetThuc.add(GregorianCalendar.DATE, SoNgayHieuLuc);
////		CapNhatDuLieu();
//	}
//
//	public void DongBangGoi() {
//		GregorianCalendar ThoiGianHienTai = new GregorianCalendar();
//		long MiliGiayThoiGianHienTai = ThoiGianHienTai.getTimeInMillis();
//		long MiliGiayThoiGianKetThuc = this.NgayKetThuc.getTimeInMillis();
//		long MiliGiayHieuLuc=0;
//		if (MiliGiayThoiGianHienTai<MiliGiayThoiGianKetThuc)  // Gói dịch vụ còn hạn sử dụng
//			MiliGiayHieuLuc=MiliGiayThoiGianKetThuc- MiliGiayThoiGianHienTai;
//		//1 day = 86400000 milisecond   , Nếu thời gian lỡ cỡ 1 ngày thì cho luôn 1 ngày :v. Hào phóng. Muốn chính xác thì Dùng ước lượng milisecond để gia hạn dịch vụ
//		this.SoNgayHieuLuc = (int)Math.ceil((float)MiliGiayHieuLuc/86400000);   
////		CapNhatDuLieu();
//	}

//	public void HuyDongBangGoi() {
//		if (LaySoNgayHieuLuc() > 0) {
//			GanSoNgayHieuLuc(0);
//			GregorianCalendar HomNay = new GregorianCalendar();
//			GanNgayBatDau(HomNay.get(Calendar.YEAR), HomNay.get(Calendar.MONTH), HomNay.get(Calendar.DAY_OF_MONTH));
//			HomNay.add(Calendar.DAY_OF_MONTH, LaySoNgayHieuLuc());
//			GanNgayKetThuc(HomNay.get(Calendar.YEAR), HomNay.get(Calendar.MONTH), HomNay.get(Calendar.DAY_OF_MONTH));   
////			CapNhatDuLieu();
//		}
//	}

	public void DoiHuanLuyenVien(String a) {
		GanMaSoHuanLuyenVien(a);
//		CapNhatDuLieu();
	}
	
	public void ChuanDoan() {
		Calendar NamHienTai=GregorianCalendar.getInstance();
		int Tuoi=NamHienTai.get(Calendar.YEAR) - this.LayNgayThangNamSinh().get(GregorianCalendar.YEAR);
		double BMI;
		BMI=this.CanNang/(this.ChieuCao*this.ChieuCao);
		if(BMI<18.5) {
			System.out.println("\t\t ---> Bạn gầy");
		}else if(18.5<BMI||BMI<25) {
			System.out.println("\t\t ---> Bạn vừa người, sức khỏe tốt");
		}else if(25<BMI||BMI<30) {
			System.out.println("\t\t ---> Bạn thừa cân");
		}else if(BMI>30) {
			System.out.println("\t\t ---> Bạn bị béo phì");
		}
		double TyLePhanTramMo;
		if(this.LayGioiTinh().equals("Nam")) {
			System.out.println("nam");
			TyLePhanTramMo=(1.2*BMI)+(0.23*Tuoi)-(10.8*1)-5.4;
		}else {
			TyLePhanTramMo=(1.2*BMI)+(0.23*Tuoi)-(10.8*0)-5.4;
			System.out.println("nu");
		}
		System.out.println("TyLePhanTramMo :"+TyLePhanTramMo);
//		double LBM=(this.CanNang*(100-TyLePhanTramMo))/100;
		double BMR=0;
		if(this.LayGioiTinh().equals("Nam")) {
			BMR=(13.397 * this.CanNang) + (4.799 * this.ChieuCao) - (5.677 * Tuoi) + 88.362;
		}
		else {
			BMR=(9.247  * this.CanNang) + (3.098  * this.ChieuCao) - (4.330 * Tuoi) + 447.593;
		}
		//TDEE là lượng calo tối thiểu tiêu thụ trong 1 ngày= BMR*1.5
		int x;
		double TDEE=0;//Xuất cho khách hàng
		do {
			System.out.println("Bạn thuộc kiểu người nào: ");
			System.out.println("0. Ít hoặc không vận động");
			System.out.println("1. Vận động nhẹ: 1-3 lần/1 tuần");
			System.out.println("2. Vận động vừa phải: 3-5 lần/ 1 tuần");
			System.out.println("3. Vận động nhiều: 6-7 lần/1 tuần");
			System.out.println("4. Vận động nặng: Trên 7 lần 1 tuần");
			System.out.println("Nhập lựa chọn :");
			x=s.nextInt();
			if(x==0) {
				TDEE=BMR*1.2;
			}else if(x==1) {
				TDEE=BMR*1.375;
			}else if(x==2) {
				TDEE=BMR*1.55;
				System.out.println(2);
			}else if(x==3) {
				TDEE=BMR*1.725;
			}else if(x==4) {
				TDEE=BMR*1.9;
			}
		}while(x<0||x>4);
		System.out.println("x; "+x);
		System.out.println(" tdee: "+TDEE);
		System.out.println(" bmr: "+BMR);
		
		double LuongMo =this.CanNang*(TyLePhanTramMo/100);
		System.out.println("Lượng Mỡ: "+LuongMo);
		double CanNac=this.CanNang-LuongMo;
		//Các giá trị dinh dưỡng tối thiểu cần nạp vào cơ thể(Có thể xuất cho khách hàng biết) ->chế độ ăn phù hợp
		GiaTri Protein=(new GiaTriDinhDuong()).new GiaTri();
			Protein.Min=2.2*CanNac;
			Protein.CaloMax=Protein.Min*4;
			Protein.Max=3.3*CanNac;
			Protein.CaloMin=Protein.Max*4;
		GiaTri Fat=(new GiaTriDinhDuong()).new GiaTri();
			Fat.Min=0.77*CanNac;
			Fat.CaloMin=Fat.Min*9;
			Fat.Max=1.1*CanNac;
			Fat.CaloMax=Fat.Max*9;
		//Trung bình giữa BMR và TDEE
			double TrungBinh=(BMR+TDEE)/2;
			System.out.println("TrungBinh: "+TrungBinh);
		GiaTri Cards=(new GiaTriDinhDuong()).new GiaTri();
			Cards.CaloMin=TrungBinh-(Protein.CaloMin+Fat.CaloMin);
			System.out.println("Calo max protein: "+Protein.CaloMin);
			System.out.println("Calo min fat: "+Fat.CaloMin);
			Cards.CaloMax=TrungBinh-(Protein.CaloMax+Fat.CaloMax);
			Cards.Min=Cards.CaloMin/4;
			Cards.Max=Cards.CaloMax/4;
		System.out.println("\tGiá trị dinh dưỡng cần nạp vào 1 ngày");
		System.out.println("Giá trị 'calo' cần nạp tối thiểu 1 ngày: "+TDEE);
		System.out.println("Giá trị chất đạm cần nạp: "+Protein.Min+" --> "+Protein.Max);
		System.out.println("Giá trị chất béo cần nạp:"+Fat.Min+" --> "+Fat.Max);
		System.out.println("Giá trị tinh bột cần nạp:"+Cards.Min+" --> "+Cards.Max);
	}
	@Override
	public void KiemSoatRaVao() {
		java.util.Date date = Calendar.getInstance().getTime();
		System.out.println(date);
//		CapNhatDuLieu();
	}
	//trả ra các thuộc tính của 1 đối tượng
	public Object[] ToArray() {
		return new Object[] {MaSoHocVien,this.LayHoVaTen(),this.LaySDT()};
	}
}
