package com.server.app;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import CoSoVatChatSV.ThietBiPhongTap;
import NhanVienHocVienSV.HocVien;
import NhanVienHocVienSV.NhanVien;
import SanPhamDichVuSV.DichVu;
@XmlRootElement(name="QueryServer")
@XmlAccessorType(XmlAccessType.FIELD)
public class QueryServer {
	@XmlElement
	private String namequery;//tên loại : Select update delete ..
	@XmlElement
	private String nametable;//Table muốn truy xuất
	@XmlElement
	private HocVien hocvien;
	@XmlElement
	private NhanVien nhanvien;
	@XmlElement
	private ThietBiPhongTap thietbiphongtap;
	@XmlElement
	private DichVu dichvu;
	@XmlElement
	private String thuoctinh;
	@XmlElement
	private String giatri;
	
	
	public String getNamequery() {
		return this.namequery;
	}
	public void setNamequery(String namequery) {
		this.namequery = namequery;
	}
	public String getNametable() {
		return this.nametable;
	}
	public void setNametable(String nametable) {
		this.nametable = nametable;
	}
	public HocVien getHocvien() {
		return hocvien;
	}
	public void setHocvien(HocVien hocvien) {
		this.hocvien = hocvien;
	}
	public NhanVien getNhanvien() {
		return nhanvien;
	}
	public void setNhanvien(NhanVien nhanvien) {
		this.nhanvien = nhanvien;
	}
	public ThietBiPhongTap getThietbiphongtap() {
		return thietbiphongtap;
	}
	public void setThietbiphongtap(ThietBiPhongTap thietbiphongtap) {
		this.thietbiphongtap = thietbiphongtap;
	}
	public DichVu getDichvu() {
		return dichvu;
	}
	public void setDichvu(DichVu dichvu) {
		this.dichvu = dichvu;
	}
	public String getThuoctinh() {
		return thuoctinh;
	}
	public void setThuoctinh(String thuoctinh) {
		this.thuoctinh = thuoctinh;
	}
	public String getGiatri() {
		return giatri;
	}
	public void setGiatri(String giatri) {
		this.giatri = giatri;
	}
	//updata hay insert học viên
	public QueryServer(String namequery, HocVien hocvien) {
		this.nametable = "HocVien";
		this.namequery = namequery;
		this.hocvien = hocvien;
	}
	//updata hay insert nhân viên
	public QueryServer(String namequery, NhanVien nhanvien) {
		this.nametable="NhanVien";
		this.namequery = namequery;
		this.nhanvien = nhanvien;
	}
	//updata hay insert thiết bị phòng tập
	public QueryServer(String namequery, ThietBiPhongTap thietbiphongtap) {
		this.nametable="ThietBiPhongTap";
		this.namequery = namequery;
		this.thietbiphongtap = thietbiphongtap;
	}
	//updata hay insert dịch vụ
	public QueryServer(String namequery, DichVu dichvu) {
		this.nametable = "DichVu";
		this.namequery = namequery;
		this.dichvu = dichvu;
	}
	// SELECT bảng bất kỳ
	public QueryServer(String namequery, String nametable, String thuoctinh, String giatri) {
		this.namequery = namequery;
		this.nametable = nametable;
		this.thuoctinh = thuoctinh;
		this.giatri = giatri;
	}
	public QueryServer(String namequery, String nametable, HocVien hocvien, NhanVien nhanvien,
			ThietBiPhongTap thietbiphongtap, DichVu dichvu, String thuoctinh, String giatri) {
		this.namequery = namequery;
		this.nametable = nametable;
		this.hocvien = hocvien;
		this.nhanvien = nhanvien;
		this.thietbiphongtap = thietbiphongtap;
		this.dichvu = dichvu;
		this.thuoctinh = thuoctinh;
		this.giatri = giatri;
	}
	public QueryServer() {
	}
}
