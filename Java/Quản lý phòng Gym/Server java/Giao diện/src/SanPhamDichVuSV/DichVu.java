package SanPhamDichVuSV;

public class DichVu {
	private String MaGoi;
	private String MoTaGoi; 
	private float SoNgayHieuLuc;
	private float GiaGoi;
	
	
	public DichVu(String maGoi, String moTaGoi, float soNgayHieuLuc, float giaGoi) {
		super();
		MaGoi = maGoi;
		MoTaGoi = moTaGoi;
		SoNgayHieuLuc = soNgayHieuLuc;
		GiaGoi = giaGoi;
	}


	public DichVu() {
		super();
	}


	public String getMaGoi() {
		return MaGoi;
	}


	public void setMaGoi(String maGoi) {
		MaGoi = maGoi;
	}


	public String getMoTaGoi() {
		return MoTaGoi;
	}


	public void setMoTaGoi(String moTaGoi) {
		MoTaGoi = moTaGoi;
	}


	public float getSoNgayHieuLuc() {
		return SoNgayHieuLuc;
	}


	public void setSoNgayHieuLuc(float soNgayHieuLuc) {
		SoNgayHieuLuc = soNgayHieuLuc;
	}


	public float getGiaGoi() {
		return GiaGoi;
	}


	public void setGiaGoi(float giaGoi) {
		GiaGoi = giaGoi;
	}


	public Object[] toArray() {
		return new Object[] {MaGoi,MoTaGoi,GiaGoi,SoNgayHieuLuc};
	}
}
