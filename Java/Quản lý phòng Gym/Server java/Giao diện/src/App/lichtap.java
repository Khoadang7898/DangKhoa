package App;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="lichtap")
@XmlAccessorType(XmlAccessType.FIELD)
public class lichtap {
	@XmlElement
	private String maLichTap;
	@XmlElement
	private String maBaiTap;
	@XmlElement
	private String ngayTap;
	@XmlElement
	private String maHoiVien;
	
	
	
	
	public lichtap() {
		super();
	}
	public lichtap(String maLichTap, String maBaiTap, String ngayTap, String maHoiVien) {
		super();
		this.maLichTap = maLichTap;
		this.maBaiTap = maBaiTap;
		this.ngayTap = ngayTap;
		this.maHoiVien = maHoiVien;
	}
	public String getMaLichTap() {
		return maLichTap;
	}
	public void setMaLichTap(String maLichTap) {
		this.maLichTap = maLichTap;
	}
	public String getMaBaiTap() {
		return maBaiTap;
	}
	public void setMaBaiTap(String maBaiTap) {
		this.maBaiTap = maBaiTap;
	}
	public String getNgayTap() {
		return ngayTap;
	}
	public void setNgayTap(String ngayTap) {
		this.ngayTap = ngayTap;
	}
	public String getMaHoiVien() {
		return maHoiVien;
	}
	public void setMaHoiVien(String maHoiVien) {
		this.maHoiVien = maHoiVien;
	}

}
