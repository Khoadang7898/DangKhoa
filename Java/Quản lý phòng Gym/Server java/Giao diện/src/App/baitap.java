package App;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="baitap")
@XmlAccessorType(XmlAccessType.FIELD)
public class baitap {
	@XmlElement
	private String tenBaiTap;
	@XmlElement
	private String nhomCo;
	@XmlElement
	private String moTaKyThuat;
	@XmlElement
	private String maBaiTap;
	
	
	
	
	public baitap() {
		super();
	}
	public baitap(String tenBaiTap, String nhomCo, String moTaKyThuat, String maBaiTap) {
		super();
		this.tenBaiTap = tenBaiTap;
		this.nhomCo = nhomCo;
		this.moTaKyThuat = moTaKyThuat;
		this.maBaiTap = maBaiTap;
	}
	public String getTenBaiTap() {
		return tenBaiTap;
	}
	public void setTenBaiTap(String tenBaiTap) {
		this.tenBaiTap = tenBaiTap;
	}
	public String getNhomCo() {
		return nhomCo;
	}
	public void setNhomCo(String nhomCo) {
		this.nhomCo = nhomCo;
	}
	public String getMoTaKyThuat() {
		return moTaKyThuat;
	}
	public void setMoTaKyThuat(String moTaKyThuat) {
		this.moTaKyThuat = moTaKyThuat;
	}
	public String getMaBaiTap() {
		return maBaiTap;
	}
	public void setMaBaiTap(String maBaiTap) {
		this.maBaiTap = maBaiTap;
	}
	

}
