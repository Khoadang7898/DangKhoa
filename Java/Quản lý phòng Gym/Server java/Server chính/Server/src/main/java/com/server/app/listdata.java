package com.server.app;
import java.util.List;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import CoSoVatChatSV.ThietBiPhongTap;
import NhanVienHocVienSV.HocVien;
import NhanVienHocVienSV.NhanVien;
import SanPhamDichVuSV.DichVu;

@XmlRootElement(name="listdata")
@XmlAccessorType(XmlAccessType.FIELD)
public class listdata {

	@XmlElement
	private List<NhanVien> listnhanvien;
	@XmlElement
	private List<DichVu> listdichvu;
	@XmlElement
	private List<ThietBiPhongTap> thietbiphongtap;
	@XmlElement
	private int result;
	@XmlElement
	private List<HocVien> listhoivien;
	public int getResult() {
		return result;
	}


	public void setResult(int result) {
		this.result = result;
	}


	public listdata( ) {
		super();
	}


	public List<HocVien> getListhoivien() {
		return listhoivien;
	}


	public void setListhoivien(List<HocVien> listhoivien) {
		this.listhoivien = listhoivien;
	}


	public List<NhanVien> getListnhanvien() {
		return listnhanvien;
	}


	public void setListnhanvien(List<NhanVien> listnhanvien) {
		this.listnhanvien = listnhanvien;
	}


	public List<DichVu> getListdichvu() {
		return listdichvu;
	}


	public void setListdichvu(List<DichVu> listdichvu) {
		this.listdichvu = listdichvu;
	}


	public List<ThietBiPhongTap> getThietbiphongtap() {
		return thietbiphongtap;
	}


	public void setThietbiphongtap(List<ThietBiPhongTap> thietbiphongtap) {
		this.thietbiphongtap = thietbiphongtap;
	}


	
}

