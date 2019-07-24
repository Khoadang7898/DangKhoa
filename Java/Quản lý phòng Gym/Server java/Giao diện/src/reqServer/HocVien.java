package reqServer;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="HocVien")
@XmlAccessorType(XmlAccessType.FIELD)
public class HocVien extends Nguoi {
	//khu vực ae ko ddunhj chạm . có j liên hệ Khoa pro lây dơ
	@XmlElement
	private String maSoHocVien;
	@XmlElement
	private String maSoGoiDichVu;
	@XmlElement
	private String maSoHuanLuyenVien;
	@XmlElement
	private String ngayDangKy;
	@XmlElement
	private String ngayBatDau;
	@XmlElement
	private String ngayKetThuc;
	@XmlElement
	private int soNgayHieuLuc;
	@XmlElement
	private double chieuCao;
	@XmlElement
	private double canNang;

	public HocVien() {
		super();
		
	}

	
	public double LayChieuCao() {
		return chieuCao;
	}

	public void GanChieuCao(double chieuCao) {
		this.chieuCao = chieuCao;
	}

	public double LayCanNang() {
		return this.canNang;
	}

	public void GanCanNang(double canNang) {
		this.canNang = canNang;
	}

	public String LayMaSoHocVien() {
		return this.maSoHocVien;
	}

	public void GanMaSoHocVien(String maSoHocVien) {
		this.maSoHocVien = maSoHocVien;
	}

	public String LayNgayDangKy() {
		return this.ngayDangKy;
	}

	public void GanNgayDangKy(int a, int b, int c) {
		this.ngayDangKy = c+"-"+b+"-"+a; 
	}
	public void GanNgayDangKyS(String c) {
		this.ngayDangKy = c; 
	}


	public String LayMaSoGoiDichVu() {
		return maSoGoiDichVu;
	}

	public void GanMaSoGoiDichVu(String maSoGoiDichVu) {
		this.maSoGoiDichVu = maSoGoiDichVu;
	}

	public String LayMaSoHuanLuyenVien() {
		return this.maSoHuanLuyenVien;
	}

	public void GanMaSoHuanLuyenVien(String maSoHuanLuyenVien) {
		this.maSoHuanLuyenVien = maSoHuanLuyenVien;
	}

	public String LayNgayBatDau() {
		return this.ngayBatDau;
	}

	public void GanNgayBatDau(int a, int b, int c) {
		ngayBatDau = c+"-"+b+"-"+a;
	}

	public String LayNgayKetThuc() {
		return this.ngayKetThuc;
	}


	public void GanNgayKetThucS(String a) {
		ngayKetThuc = a;
	}
	public void GanNgayBatDauS(String c) {
		ngayBatDau = c;
	}


	

	public void GanNgayKetThuc(int a, int b, int c) {
		ngayKetThuc = c+"-"+b+"-"+a;
	}
	
	public int LaySoNgayHieuLuc() {
		return soNgayHieuLuc;
	}


	
}
