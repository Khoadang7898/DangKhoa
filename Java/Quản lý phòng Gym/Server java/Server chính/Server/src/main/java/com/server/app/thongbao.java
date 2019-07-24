package com.server.app;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="thongbao")
@XmlAccessorType(XmlAccessType.FIELD)
public class thongbao {
	@XmlElement
	private String maThongBao;
	@XmlElement
	private String ngayThongBao;
	@XmlElement
	private String noiDung;
	@XmlElement
	private String trangThai;
	@XmlElement
	private String doiTuong;
	
	
	public thongbao() {

	}
	
	public thongbao(String maThongBao, String ngayThongBao, String noiDung, String trangThai, String doiTuong) {
		this.maThongBao = maThongBao;
		this.ngayThongBao = ngayThongBao;
		this.noiDung = noiDung;
		this.trangThai = trangThai;
		this.doiTuong = doiTuong;
	}
	public String getMaThongBao() {
		return maThongBao;
	}
	public void setMaThongBao(String maThongBao) {
		this.maThongBao = maThongBao;
	}
	public String getNgayThongBao() {
		return ngayThongBao;
	}
	public void setNgayThongBao(String ngayThongBao) {
		this.ngayThongBao = ngayThongBao;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public String getDoiTuong() {
		return doiTuong;
	}
	public void setDoiTuong(String doiTuong) {
		this.doiTuong = doiTuong;
	}


}
