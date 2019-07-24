package org.server.Client;

import java.util.List;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import reqServer.DichVu;
import reqServer.HocVien;
import reqServer.NhanVien;
import reqServer.ThietBiPhongTap;
@XmlRootElement(name="listdata")
@XmlAccessorType(XmlAccessType.FIELD)
public class listdata {
	@XmlElement
	private List<HocVien> listhoivien;
	@XmlElement
	private List<NhanVien> listnhanvien;
	@XmlElement
	private List<DichVu> listdichvu;
	@XmlElement
	private List<ThietBiPhongTap> thietbiphongtap;
	@XmlElement
	private int result;

	public int getResult() {
		return result;
	}


	public void setResult(int result) {
		this.result = result;
	}




	public listdata( ) {
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


	public listdata(List<HocVien> listhoivien, List<NhanVien> listnhanvien, List<DichVu> listdichvu,
			List<ThietBiPhongTap> thietbiphongtap) {
		this.listhoivien = listhoivien;
		this.listnhanvien = listnhanvien;
		this.listdichvu = listdichvu;
		this.thietbiphongtap = thietbiphongtap;
	}
	public ObservableList<ObservableList> getNhanVienOb(){
		ObservableList<ObservableList> data =FXCollections.observableArrayList();
		for(NhanVien nv : this.listnhanvien)
		{
			ObservableList<String> n =  FXCollections.observableArrayList();
			n.add(nv.getMaNhanVien());
			n.add(nv.getChucVu());
			n.add(Double.toString(nv.getHeSoLuong()));
			n.add(Double.toString(nv.getLuongCoBan()));
			n.add(nv.LayHoVaTen());
			n.add(nv.LayNgayThangNamSinh());
			n.add(nv.LayDiaChi());
			n.add(Integer.toString(nv.LaySDT()));
			n.add(nv.LayGioiTinh());
			n.add(nv.LayHinhAnh());
			data.add(n);
		}
		return data;
	}

public ObservableList<ObservableList> getHoiVienOb(){
	ObservableList<ObservableList> data =FXCollections.observableArrayList();
	for(HocVien nv : this.listhoivien)
	{
		ObservableList<String> n =  FXCollections.observableArrayList();
		n.add(nv.LayMaSoHocVien());
		n.add(nv.LayMaSoGoiDichVu());
		n.add(nv.LayMaSoHuanLuyenVien());
		n.add(nv.LayHoVaTen());
		n.add(nv.LayNgayThangNamSinh());
		n.add(nv.LayDiaChi());
		n.add(Integer.toString(nv.LaySDT()));
		n.add(nv.LayGioiTinh());
		n.add(nv.LayHinhAnh());
		n.add(nv.LayNgayDangKy());
		n.add(nv.LayNgayBatDau());
		n.add(nv.LayNgayKetThuc());
		n.add(Integer.toString(nv.LaySoNgayHieuLuc()));
		n.add(Double.toString(nv.LayChieuCao()));
		n.add(Double.toString(nv.LayCanNang()));
		data.add(n);
	}
	return data;
}



public ObservableList<ObservableList> getDichVuOb(){
	ObservableList<ObservableList> data =FXCollections.observableArrayList();
	for(DichVu nv : this.listdichvu)
	{
		ObservableList<String> n =  FXCollections.observableArrayList();
		n.add(nv.getMaGoi());
		n.add(nv.getMoTaGoi());
		n.add(Integer.toString(nv.getSoNgayHieuLuc()));
		n.add(Float.toString(nv.getGiaGoi()));
		data.add(n);
	}
	return data;
}

public ObservableList<ObservableList> getThietBiOb(){
	ObservableList<ObservableList> data =FXCollections.observableArrayList();
	if(this.thietbiphongtap!=null)
	for(ThietBiPhongTap nv : this.thietbiphongtap)
	{
		ObservableList<String> n =  FXCollections.observableArrayList();
		n.add(nv.getMaThietBi());
		n.add(nv.getTenThietBi());
		n.add(nv.getTinhTrang());
		n.add(nv.getNgaySanXuat());
		n.add(Float.toString(nv.getGia()));
		data.add(n);
	}
	return data;
}
}

