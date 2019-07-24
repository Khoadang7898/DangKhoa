package App;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import App.thongbao;

import NhanVienHocVienSV.HocVien;
import NhanVienHocVienSV.NhanVien;
import SanPhamDichVuSV.DichVu;
@XmlRootElement(name="dataApp")
@XmlAccessorType(XmlAccessType.FIELD)
public class dataApp {
	@XmlElement
	private int result;//kết quả
	@XmlElement
	private HocVien user;//thông tin của user
	@XmlElement
	private List<thongbao> thongbao;//các thông báo gửi đến cho user
	@XmlElement
	private List<baitap> baitap;//Các bài tập
	@XmlElement
	private List<lichtap> lichtap;//Lịch tập của user
	@XmlElement
	private List<DichVu> dichvu;//Các gói dịch vụ
	@XmlElement
	private List<NhanVien> huanluyenvien;//Các huấn luyện viên
	
	public dataApp() {
	}
	
	
	
	public dataApp(int result, HocVien user, List<App.thongbao> thongbao, List<App.baitap> baitap,
			List<App.lichtap> lichtap, List<DichVu> dichvu, List<NhanVien> huanluyenvien) {
		this.result = result;
		this.user = user;
		this.thongbao = thongbao;
		this.baitap = baitap;
		this.lichtap = lichtap;
		this.dichvu = dichvu;
		this.huanluyenvien = huanluyenvien;
	}



	public int getResult() {
		return result;
	}



	public void setResult(int result) {
		this.result = result;
	}



	public HocVien getUser() {
		return user;
	}
	
	public void setUser(HocVien user) {
		this.user = user;
	}
	
	public List<thongbao> getThongbao() {
		return thongbao;
	}
	
	public void setThongbao(List<thongbao> thongbao) {
		this.thongbao = thongbao;
	}
	
	public List<baitap> getBaitap() {
		return baitap;
	}
	
	public void setBaitap(List<baitap> baitap) {
		this.baitap = baitap;
	}
	
	public List<lichtap> getLichtap() {
		return lichtap;
	}
	
	public void setLichtap(List<lichtap> lichtap) {
		this.lichtap = lichtap;
	}
	
	public List<DichVu> getDichvu() {
		return dichvu;
	}
	
	public void setDichvu(List<DichVu> dichvu) {
		this.dichvu = dichvu;
	}
	
	public List<NhanVien> getHuanluyenvien() {
		return huanluyenvien;
	}
	
	public void setHuanluyenvien(List<NhanVien> huanluyenvien) {
		this.huanluyenvien = huanluyenvien;
	}
	
}
