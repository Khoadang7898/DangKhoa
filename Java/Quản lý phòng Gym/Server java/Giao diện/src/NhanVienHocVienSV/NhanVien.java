package NhanVienHocVienSV;

import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="NhanVien")
@XmlAccessorType(XmlAccessType.FIELD)
public class NhanVien extends Nguoi {
	
	//vùng làm phiền ae ko động tới, thay đổi xóa sửa j liên hệ Khoa pro lây dơ 
	@XmlElement
	private String MaNhanVien;
	@XmlElement
	private String ChucVu;
	@XmlElement
	private double LuongCoBan;
	public NhanVien() {
		super();

	}
	
	public NhanVien(String hoVaTen, String diaChi, String soDienThoai, GregorianCalendar NgaySinh, String maNhanVien, String chucVu, double luongCoBan,String gioiTinh,String hinhanh) {
		super(hoVaTen, NgaySinh,diaChi, soDienThoai,gioiTinh,hinhanh);
		setMaNhanVien(maNhanVien);
		setChucVu(chucVu);
		setLuongCoBan(luongCoBan);
	}
	//Phần này đi ae muốn chơi sao cũng đc
	
	public String getMaNhanVien() {
		return MaNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		MaNhanVien = maNhanVien;
	}
	public String getChucVu() {
		return ChucVu;
	}
	public void setChucVu(String chucVu) {
		ChucVu = chucVu;
	}
	public double getLuongCoBan() {
		return LuongCoBan;
	}
	public void setLuongCoBan(double luongCoBan) {
		LuongCoBan = luongCoBan;
	}


	

//	public double TinhLuong() {
//		return this.HeSoLuong*this.LuongCoBan;
//	}
//
//
//
//	public Object[] ToString() {
//		// TODO Auto-generated method stub
//		return new Object[] {MaNhanVien,this.LayHoVaTen(),this.LayDiaChi(),this.LayGioiTinh(),this.LayHinhAnh(),this.LayNgayThangNamSinhString(),this.LaySDT(),ChucVu,LuongCoBan,HeSoLuong};
//	}
}
