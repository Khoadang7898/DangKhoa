package reqServer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DichVu")
@XmlAccessorType(XmlAccessType.FIELD)
public class DichVu {
	@XmlElement
	private String MaGoi;
	@XmlElement
	private String MoTaGoi; 
	@XmlElement
	private int SoNgayHieuLuc;
	@XmlElement
	private float GiaGoi;
	
	
	public DichVu(String maGoi, String moTaGoi, int soNgayHieuLuc, float giaGoi) {
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


	public int getSoNgayHieuLuc() {
		return SoNgayHieuLuc;
	}


	public void setSoNgayHieuLuc(int soNgayHieuLuc) {
		SoNgayHieuLuc = soNgayHieuLuc;
	}


	public float getGiaGoi() {
		return GiaGoi;
	}


	public void setGiaGoi(float giaGoi) {
		GiaGoi = giaGoi;
	}

}
