package NhanVienHocVien;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public abstract class Nguoi {
	private StringProperty  HoVaTen;
	private GregorianCalendar NgayThangNamSinh;
	private StringProperty  DiaChi;
	private int SDT;
	private StringProperty  GioiTinh;
	private StringProperty  HinhAnh;
//	Nguoi()
//	{
//		this.DiaChi="null";
//		this.HoVaTen="null";
//		this.NgayThangNamSinh= (GregorianCalendar) GregorianCalendar.getInstance();
//		this.NgayThangNamSinh.set(1970,1,1);
//		this.SDT= -1;
//		this.HinhAnh="";
//		this.GioiTinh="";
//	}
//	
	
	public Nguoi(String hoVaTen, GregorianCalendar ngayThangNamSinh, String diaChi, int sDT,String gioitinh,String hinhanh) {
		super();
		HoVaTen = new SimpleStringProperty(hoVaTen);
		NgayThangNamSinh = ngayThangNamSinh;
		DiaChi = new SimpleStringProperty(diaChi);
		SDT = sDT;
		HinhAnh=new SimpleStringProperty(gioitinh);
		GioiTinh=new SimpleStringProperty(hinhanh);
	}
	public StringProperty HoVaTenProperty() {
        if (HoVaTen == null) HoVaTen = new SimpleStringProperty(this, "HoVaTen");
        return HoVaTen;
    }
	public String LayHoVaTen() {
		return HoVaTenProperty().get();
	}

	public void GanHoVaTen(String hoVaTen) {
		HoVaTenProperty().set( hoVaTen);
	}

	public GregorianCalendar LayNgayThangNamSinh() {
		return NgayThangNamSinh;
	}

	public void GanNgayThangNamSinh(int a, int b, int c) {
		NgayThangNamSinh = (GregorianCalendar) GregorianCalendar.getInstance();
		NgayThangNamSinh.set(a,b,c);
	}
	public StringProperty DiaChiProperty() {
        if (DiaChi == null) DiaChi = new SimpleStringProperty(this, "DiaChi");
        return DiaChi;
    }
	public String LayDiaChi() {
		return DiaChiProperty().get();
	}

	public void GanDiaChi(String diaChi) {
		DiaChiProperty().set(diaChi);
	}

	public int LaySDT() {
		return SDT;
	}

	public void GanSDT(int sDT) {
		SDT = sDT;
	}
	public StringProperty GioiTinhProperty() {
        if (GioiTinh == null) GioiTinh = new SimpleStringProperty(this, "GioiTinh");
        return GioiTinh;
    }
	public String LayGioiTinh() {
		return GioiTinhProperty().get();
	}

	public void GanGioiTinh(String Gioitinh) {
		GioiTinhProperty().set(Gioitinh);
	}
	public StringProperty HinhAnhProperty() {
        if (HinhAnh == null) HinhAnh = new SimpleStringProperty(this, "HinhAnh");
        return HinhAnh;
    }
	public String LayHinhAnh() {
		return HinhAnhProperty().get();
	}
	
	public void GanHinhAnh(String hinhanh) {
		HinhAnhProperty().set(hinhanh);
	}
//	public HashMap TruyVanThongTin()
//	{
//		HashMap ThongTin = new HashMap();
//		ThongTin.put("Họ và tên",this.HoVaTen);
//		ThongTin.put("Ngày sinh",this.NgayThangNamSinh);
//		ThongTin.put("Địa chỉ",this.DiaChi);
//		ThongTin.put("SĐT",this.SDT);
//		return ThongTin;
//	}
	public String toString() {
		return String.format("\n%s : %s\n%s : %td/%tm/%tY\n%s : %s\n%s : %d", "Họ và tên",this.HoVaTen,"Ngày sinh",this.NgayThangNamSinh,this.NgayThangNamSinh,this.NgayThangNamSinh,"Địa chỉ",this.DiaChi,"SĐT",this.SDT);
	}

	public void KiemSoatRaVao() {
		// TODO Auto-generated method stub
		
	}
}
