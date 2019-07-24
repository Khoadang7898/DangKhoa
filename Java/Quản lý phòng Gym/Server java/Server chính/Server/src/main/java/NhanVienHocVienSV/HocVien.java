package NhanVienHocVienSV;



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
		public HocVien(String hoTen, String ngayThangNamSinh, String diaChi, int sDT, String gioiTinh,
				String hinhAnh,String maSoHocVien, String maSoGoiDichVu, String maSoHuanLuyenVien, String ngayDangKy,
				String ngayBatDau, String ngayKetThuc, int soNgayHieuLuc, double chieuCao,
				double canNang) {
			super(hoTen,ngayThangNamSinh,diaChi,sDT,gioiTinh,hinhAnh);
			this.maSoHocVien = maSoHocVien;
			this.maSoGoiDichVu = maSoGoiDichVu;
			this.maSoHuanLuyenVien = maSoHuanLuyenVien;
			this.ngayDangKy = ngayDangKy;
			this.ngayBatDau = ngayBatDau;
			this.ngayKetThuc = ngayKetThuc;
			this.soNgayHieuLuc = soNgayHieuLuc;
			this.chieuCao = chieuCao;
			this.canNang = canNang;
		}
		
		public double getChieuCao() {
			return chieuCao;
		}

		public void setChieuCao(double chieuCao) {
			this.chieuCao = chieuCao;
		}

		public double getCanNang() {
			return this.canNang;
		}

		public void setCanNang(double canNang) {
			this.canNang = canNang;
		}

		public String getMaSoHocVien() {
			return this.maSoHocVien;
		}

		public void setMaSoHocVien(String maSoHocVien) {
			this.maSoHocVien = maSoHocVien;
		}

		public String getNgayDangKy() {
			return this.ngayDangKy;
		}

		public void setNgayDangKy(int a, int b, int c) {
			this.ngayDangKy = c+"-"+b+"-"+a; 
		}
		public void setNgayDangKy(String c) {
			this.ngayDangKy = c; 
		}


		public String getMaSoGoiDichVu() {
			return maSoGoiDichVu;
		}

		public void setMaSoGoiDichVu(String maSoGoiDichVu) {
			this.maSoGoiDichVu = maSoGoiDichVu;
		}

		public String getMaSoHuanLuyenVien() {
			return this.maSoHuanLuyenVien;
		}

		public void setMaSoHuanLuyenVien(String maSoHuanLuyenVien) {
			this.maSoHuanLuyenVien = maSoHuanLuyenVien;
		}

		public String getNgayBatDau() {
			return this.ngayBatDau;
		}

		public void setNgayBatDau(int a, int b, int c) {
			ngayBatDau = c+"-"+b+"-"+a;
		}
		public void setNgayBatDau(String a) {
			this.ngayBatDau = a;
		}
		public String getNgayKetThuc() {
			return this.ngayKetThuc;
		}


		public void setNgayKetThuc(String a) {
			ngayKetThuc = a;
		}
		public void setNgayBatDauS(String c) {
			ngayBatDau = c;
		}


		

		public void setNgayKetThuc(int a, int b, int c) {
			ngayKetThuc = c+"-"+b+"-"+a;
		}
		
		public int getSoNgayHieuLuc() {
			return soNgayHieuLuc;
		}


		
	}
