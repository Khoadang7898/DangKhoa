package NhanVienHocVienSV;

import java.util.GregorianCalendar;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="HocVien")
@XmlAccessorType(XmlAccessType.FIELD)
public class HocVien extends Nguoi {
	@XmlElement
	private String MaSoHocVien;
	@XmlElement
	private String MaSoGoiDichVu;
	@XmlElement
	private String MaSoHuanLuyenVien;
	@XmlElement
	private GregorianCalendar NgayDangKy;
	@XmlElement
	private GregorianCalendar NgayBatDau;
	@XmlElement
	private GregorianCalendar NgayKetThuc;
	@XmlElement
	private int SoNgayHieuLuc;
	@XmlElement
	private double ChieuCao;
	@XmlElement
	private double CanNang;
	public HocVien(String hoTen, GregorianCalendar ngayThangNamSinh, String diaChi, String sDT, String gioiTinh,
			String hinhAnh,String maSoHocVien, String maSoGoiDichVu, String maSoHuanLuyenVien, GregorianCalendar ngayDangKy,
			GregorianCalendar ngayBatDau, GregorianCalendar ngayKetThuc, int soNgayHieuLuc, double chieuCao,
			double canNang) {
		super(hoTen,ngayThangNamSinh,diaChi,sDT,gioiTinh,hinhAnh);
		MaSoHocVien = maSoHocVien;
		MaSoGoiDichVu = maSoGoiDichVu;
		MaSoHuanLuyenVien = maSoHuanLuyenVien;
		NgayDangKy = ngayDangKy;
		NgayBatDau = ngayBatDau;
		NgayKetThuc = ngayKetThuc;
		SoNgayHieuLuc = soNgayHieuLuc;
		ChieuCao = chieuCao;
		CanNang = canNang;
	}
	public HocVien() {
		super();
	}
	public String getMaSoHocVien() {
		return MaSoHocVien;
	}
	public void setMaSoHocVien(String maSoHocVien) {
		MaSoHocVien = maSoHocVien;
	}
	public String getMaSoGoiDichVu() {
		return MaSoGoiDichVu;
	}
	public void setMaSoGoiDichVu(String maSoGoiDichVu) {
		MaSoGoiDichVu = maSoGoiDichVu;
	}
	public String getMaSoHuanLuyenVien() {
		return MaSoHuanLuyenVien;
	}
	public void setMaSoHuanLuyenVien(String maSoHuanLuyenVien) {
		MaSoHuanLuyenVien = maSoHuanLuyenVien;
	}
	public GregorianCalendar getNgayDangKy() {
		return NgayDangKy;
	}
	public void setNgayDangKy(GregorianCalendar ngayDangKy) {
		NgayDangKy = ngayDangKy;
	}
	public GregorianCalendar getNgayBatDau() {
		return NgayBatDau;
	}
	public void setNgayBatDau(GregorianCalendar ngayBatDau) {
		NgayBatDau = ngayBatDau;
	}
	public GregorianCalendar getNgayKetThuc() {
		return NgayKetThuc;
	}
	public void setNgayKetThuc(GregorianCalendar ngayKetThuc) {
		NgayKetThuc = ngayKetThuc;
	}
	public int getSoNgayHieuLuc() {
		return SoNgayHieuLuc;
	}
	public void setSoNgayHieuLuc(int soNgayHieuLuc) {
		SoNgayHieuLuc = soNgayHieuLuc;
	}
	public double getChieuCao() {
		return ChieuCao;
	}
	public void setChieuCao(double chieuCao) {
		ChieuCao = chieuCao;
	}
	public double getCanNang() {
		return CanNang;
	}
	public void setCanNang(double canNang) {
		CanNang = canNang;
	}
	
	
	
	
//	public Object[] ToString() {
//		// TODO Auto-generated method stub
//		return new Object[] {MaSoHocVien,this.LayHoVaTen()
//				,this.LayDiaChi()
//				,this.LayGioiTinh()
//				,this.LayHinhAnh()
//				,this.LayNgayThangNamSinhString()
//				,this.LaySDT()
//				,MaSoGoiDichVu
//				,MaSoHuanLuyenVien,
//				this.LayNgayDangKyString(),
//				this.LayNgayBatDauString()
//				,this.LayNgayKetThucString()
//				,SoNgayHieuLuc
//				,ChieuCao
//				,CanNang};
//	}
//	public HocVien() {
//		super();
//	}
//
//	
//	
//	public HocVien(String hoVaTen, GregorianCalendar ngayThangNamSinh, String diaChi, int sDT,String maSoHocVien, String maSoGoiDichVu, String maSoHuanLuyenVien, GregorianCalendar ngayDangKy,
//			GregorianCalendar ngayBatDau, GregorianCalendar ngayKetThuc, int soNgayHieuLuc, String gioiTinh,String hinhanh,
//			double chieuCao, double canNang) {
//		super(hoVaTen,ngayThangNamSinh,diaChi,sDT,gioiTinh,hinhanh);
//		this.GanMaSoHocVien(maSoHocVien);
//		this.GanMaSoGoiDichVu(maSoGoiDichVu);
//		this.GanMaSoHuanLuyenVien(maSoHuanLuyenVien);
//		this.GanNgayDangKy(ngayDangKy.get(Calendar.YEAR), ngayDangKy.get(Calendar.MONTH), ngayDangKy.get(Calendar.DAY_OF_MONTH));
//		this.GanNgayBatDau(ngayBatDau.get(Calendar.YEAR), ngayBatDau.get(Calendar.MONTH), ngayBatDau.get(Calendar.DAY_OF_MONTH));
//		this.GanNgayKetThuc(ngayKetThuc.get(Calendar.YEAR), ngayKetThuc.get(Calendar.MONTH), ngayKetThuc.get(Calendar.DAY_OF_MONTH));
//		this.GanSoNgayHieuLuc(soNgayHieuLuc);
//		this.GanGioiTinh(gioiTinh);
//		this.GanCanNang(canNang);
//		this.GanChieuCao(chieuCao);
//
//	}
//
//
//
//	public double LayChieuCao() {
//		return ChieuCao;
//	}
//
//	public void GanChieuCao(double chieuCao) {
//		ChieuCao = chieuCao;
//	}
//
//	public double LayCanNang() {
//		return CanNang;
//	}
//
//	public void GanCanNang(double canNang) {
//		CanNang = canNang;
//	}
//
//	public String LayMaSoHocVien() {
//		return MaSoHocVien;
//	}
//
//	public void GanMaSoHocVien(String maSoHocVien) {
//		MaSoHocVien = maSoHocVien;
//	}
//
//	public GregorianCalendar LayNgayDangKy() {
//		return NgayDangKy;
//	}
//	public String LayNgayDangKyString() {
//		String n="";
//		n = Integer.toString(NgayDangKy.get(Calendar.DATE)) + "-" +Integer.toString(NgayDangKy.get(Calendar.MONTH)) + "-"+Integer.toString(NgayDangKy.get(Calendar.YEAR));
//		return n;
//		
//	}
//	public void GanNgayDangKy(int a, int b, int c) {
//		NgayDangKy = (GregorianCalendar) GregorianCalendar.getInstance();
//		NgayDangKy.set(a, b, c);
//	}
//
//	public String LayMaSoGoiDichVu() {
//		return MaSoGoiDichVu;
//	}
//
//	public void GanMaSoGoiDichVu(String maSoGoiDichVu) {
//		MaSoGoiDichVu = maSoGoiDichVu;
//	}
//
//	public String LayMaSoHuanLuyenVien() {
//		return MaSoHuanLuyenVien;
//	}
//
//	public void GanMaSoHuanLuyenVien(String maSoHuanLuyenVien) {
//		MaSoHuanLuyenVien = maSoHuanLuyenVien;
//	}
//
//	public GregorianCalendar LayNgayBatDau() {
//		return NgayBatDau;
//	}
//	public String LayNgayBatDauString() {
//		String n="";
//		n = Integer.toString(NgayBatDau.get(Calendar.DATE)) + "-" +Integer.toString(NgayBatDau.get(Calendar.MONTH)) + "-"+Integer.toString(NgayBatDau.get(Calendar.YEAR));
//		return n;
//	}
//	public void GanNgayBatDau(int a, int b, int c) {
//		NgayBatDau = (GregorianCalendar) GregorianCalendar.getInstance();
//		NgayBatDau.set(a, b, c);
//	}
//
//	public GregorianCalendar LayNgayKetThuc() {
//		return NgayKetThuc;
//	}
//	public String LayNgayKetThucString() {
//		String n="";
//		n = Integer.toString(NgayKetThuc.get(Calendar.DATE)) + "-" +Integer.toString(NgayKetThuc.get(Calendar.MONTH)) + "-"+Integer.toString(NgayKetThuc.get(Calendar.YEAR));
//		return n;
//	}
//
//	public void GanNgayKetThuc(int a, int b, int c) {
//		NgayKetThuc = (GregorianCalendar) GregorianCalendar.getInstance();
//		NgayKetThuc.set(a, b, c);
//	}
//
//	public int LaySoNgayHieuLuc() {
//		return SoNgayHieuLuc;
//	}
//
//	public void GanSoNgayHieuLuc(int soNgayHieuLuc) {
//		SoNgayHieuLuc = soNgayHieuLuc;
//	}
//
//
//	public void DangKyGoiDichVu(String MaGoi,int SoNgayHieuLuc)
//	{
//		GanMaSoGoiDichVu(MaGoi);
//		GregorianCalendar HomNay = new GregorianCalendar();
//		GanNgayBatDau(HomNay.get(Calendar.YEAR), HomNay.get(Calendar.MONTH), HomNay.get(Calendar.DAY_OF_MONTH));
//		HomNay.add(Calendar.DAY_OF_MONTH, SoNgayHieuLuc);// sau khi có class gói 30->số ngày của gói
//		GanNgayKetThuc(HomNay.get(Calendar.YEAR), HomNay.get(Calendar.MONTH), HomNay.get(Calendar.DAY_OF_MONTH));
//
//	}
//
//	public void GiaHanGoiDichVu(int SoNgayHieuLuc)// THam số là thời gian hiệu lực của gói dịch vụ gia hạn
//	{
//		this.NgayKetThuc.add(GregorianCalendar.DATE, SoNgayHieuLuc);
//
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
//
//	}
//
//	public void HuyDongBangGoi() {
//		if (LaySoNgayHieuLuc() > 0) {
//			GanSoNgayHieuLuc(0);
//			GregorianCalendar HomNay = new GregorianCalendar();
//			GanNgayBatDau(HomNay.get(Calendar.YEAR), HomNay.get(Calendar.MONTH), HomNay.get(Calendar.DAY_OF_MONTH));
//			HomNay.add(Calendar.DAY_OF_MONTH, LaySoNgayHieuLuc());
//			GanNgayKetThuc(HomNay.get(Calendar.YEAR), HomNay.get(Calendar.MONTH), HomNay.get(Calendar.DAY_OF_MONTH));   
//
//		}
//	}
//
//	public void DoiHuanLuyenVien(String a) {
//		GanMaSoHuanLuyenVien(a);
//
//	}
//	
//	public void ChuanDoan() {
//		Calendar NamHienTai=GregorianCalendar.getInstance();
//		int Tuoi=NamHienTai.get(Calendar.YEAR) - this.LayNgayThangNamSinh().get(GregorianCalendar.YEAR);
//		double BMI;
//		BMI=this.ChieuCao/(this.ChieuCao*this.ChieuCao);
//		if(BMI<18.5) {
//			System.out.println("Bạn gầy");
//		}else if(18.5<BMI||BMI<25) {
//			System.out.println("Bạn vừa người, sức khỏe tốt");
//		}else if(25<BMI||BMI<30) {
//			System.out.println("Bạn thừa cân");
//		}else if(BMI>30) {
//			System.out.println("Bạn bị béo phì");
//		}
//		double TyLePhanTramMo;
//		if(this.LayGioiTinh().equals("Nam")) {
//			TyLePhanTramMo=(1.2*BMI)+(0.23*Tuoi)-(10.8*1)-5.4;
//		}else {
//			TyLePhanTramMo=(1.2*BMI)+(0.23*Tuoi)-(10.8*0)-5.4;
//		}
//		double LBM=(this.CanNang*(100-TyLePhanTramMo))/100;
//		double BMR=370+(21.6*LBM);
//		//TDEE là lượng calo tối thiểu tiêu thụ trong 1 ngày= BMR*1.5
//		double TDEE=BMR*1.5;//Xuất cho khách hàng
//		double LuongMo =this.CanNang*(TyLePhanTramMo/100);
//		double CanNac=this.CanNang-LuongMo;
//		//Các giá trị dinh dưỡng tối thiểu cần nạp vào cơ thể(Có thể xuất cho khách hàng biết) ->chế độ ăn phù hợp
//		GiaTri Protein=(new GiaTriDinhDuong()).new GiaTri();
//			Protein.Min=2.2*CanNac;
//			Protein.Max=3.3*CanNac;
//		GiaTri Fat=(new GiaTriDinhDuong()).new GiaTri();
//			Fat.Min=0.77*CanNac;
//			Fat.Max=1.1*CanNac;
//		//Trung bình giữa BMR và TDEE
//			double TrungBinh=(BMR+TDEE)/2;
//		GiaTri Cards=(new GiaTriDinhDuong()).new GiaTri();
//			Cards.Max=TrungBinh-(Protein.Max+Fat.Max);
//			Cards.Min=TrungBinh-(Protein.Min+Fat.Min);
//		System.out.println("\tGiá trị dinh dưỡng cần nạp vào 1 ngày");
//		System.out.println("Giá trị 'calo' cần nạp tối thiểu 1 ngày: "+TDEE);
//		System.out.println("Giá trị chất đạm cần nạp: "+Protein.Min+" --> "+Protein.Max);
//		System.out.println("Giá trị chất béo cần nạp:"+Fat.Min+" --> "+Fat.Max);
//		System.out.println("Giá trị tinh bột cần nạp:"+Cards.Min+" --> "+Cards.Max);
//	}
	
}
