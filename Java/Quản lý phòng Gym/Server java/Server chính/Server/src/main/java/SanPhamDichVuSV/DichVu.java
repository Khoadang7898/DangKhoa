package SanPhamDichVuSV;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DichVu")
@XmlAccessorType(XmlAccessType.FIELD)
public class DichVu {
	@XmlElement
	private String maGoi;
	@XmlElement
	private String moTaGoi; 
	@XmlElement
	private int soNgayHieuLuc;
	@XmlElement
	private float giaGoi;
	
	
	public DichVu(String maGoi, String moTaGoi, int soNgayHieuLuc, float giaGoi) {
		super();
		this.maGoi = maGoi;
		this.moTaGoi = moTaGoi;
		this.soNgayHieuLuc = soNgayHieuLuc;
		this.giaGoi = giaGoi;
	}


	public DichVu() {
		super();
	}


	public String getMaGoi() {
		return this.maGoi;
	}


	public void setMaGoi(String maGoi) {
		this.maGoi = maGoi;
	}


	public String getMoTaGoi() {
		return this.moTaGoi;
	}


	public void setMoTaGoi(String moTaGoi) {
		this.moTaGoi = moTaGoi;
	}


	public int getSoNgayHieuLuc() {
		return this.soNgayHieuLuc;
	}


	public void setSoNgayHieuLuc(int soNgayHieuLuc) {
		this.soNgayHieuLuc = soNgayHieuLuc;
	}


	public float getGiaGoi() {
		return this.giaGoi;
	}


	public void setGiaGoi(float giaGoi) {
		this.giaGoi = giaGoi;
	}

}
