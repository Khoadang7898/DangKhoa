package CoSoVatChatSV;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ThietBiPhongTap")
@XmlAccessorType(XmlAccessType.FIELD)
public class ThietBiPhongTap {
	//khu vực cảm phiền các bạn ko đọng đến , có j liên hệ khoa pro lây dơ
	@XmlElement
	private String maThietBi;
	@XmlElement
	private String tenThietBi;
	@XmlElement
	private String tinhTrang;
	@XmlElement
	private String ngaySanXuat;
	@XmlElement
	private float gia;
	
	public ThietBiPhongTap(String MaThietBi, String TenThietBi,String TinhTrang,String NgaySanXuat,float Gia){
		this.maThietBi=MaThietBi;
		this.tenThietBi=TenThietBi;
		this.tinhTrang =TinhTrang;
		this.ngaySanXuat =NgaySanXuat;   // Tránh Exception NullPointer
		this.gia = Gia;  
	}
	public ThietBiPhongTap(){
	 
	}
	
	
	
	//phần này tùy ý các bạn phá
	public String getMaThietBi() {
		return this.maThietBi;
	}
	public void setMaThietBi(String maThietBi) {
		this.maThietBi = maThietBi;
	}
	public String getTenThietBi() {
		return this.tenThietBi;
	}
	public void setTenThietBi(String tenThietBi) {
		this.tenThietBi = tenThietBi;
	}
	public String getTinhTrang() {
		return this.tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public String getNgaySanXuat() {
		return this.ngaySanXuat;
	}
	public void setNgaySanXuat(String ngaySanXuat) {
		this.ngaySanXuat = ngaySanXuat;
	}
	public float getGia() {
		return this.gia;
	}
	public void setGia(float gia) {
		this.gia = gia;
	}



}

