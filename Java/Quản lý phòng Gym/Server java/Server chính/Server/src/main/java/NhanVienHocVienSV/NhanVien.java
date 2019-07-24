package NhanVienHocVienSV;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="NhanVien")
@XmlAccessorType(XmlAccessType.FIELD)
public class NhanVien extends Nguoi {
	// khu vực làm ae ko động đến . Liên hệ Khoa pro lây dơ
	@XmlElement
	private String maNhanVien;
	@XmlElement
	private String chucVu;
	@XmlElement
	private double luongCoBan;
	@XmlElement
	private double heSoLuong;
	
	
	public NhanVien(String hoVaTen, String diaChi, int soDienThoai, String NgaySinh, String maNhanVien, String chucVu, double luongCoBan, double heSoLuong,String gioiTinh,String hinhanh) {
		super(hoVaTen, NgaySinh,diaChi, soDienThoai,gioiTinh,hinhanh);
		setMaNhanVien(maNhanVien);
		setChucVu(chucVu);
		setLuongCoBan(luongCoBan);
		setHeSoLuong(heSoLuong);
		
	}
	public NhanVien() {
		super();
	}
	
	//khu vực ae tùy ý phá thêm bớt
	
	public String getMaNhanVien() {
		return this.maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getChucVu() {
		return this.chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	public double getLuongCoBan() {
		return this.luongCoBan;
	}
	public void setLuongCoBan(double luongCoBan) {
		this.luongCoBan = luongCoBan;
	}
	public double getHeSoLuong() {
		return this.heSoLuong;
	}
	public void setHeSoLuong(double heSoLuong) {
		this.heSoLuong = heSoLuong;
	}

	public double TinhLuong() {
		return this.heSoLuong*this.luongCoBan;
	}

}
