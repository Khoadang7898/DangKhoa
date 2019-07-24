package App;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="createAccount")
@XmlAccessorType(XmlAccessType.FIELD)
public class createAccount {
	@XmlElement
	private String userName;
	@XmlElement
	private String passWor;
	@XmlElement
	private String chuSoHuu;
	@XmlElement
	private String loaiTaiKhoan;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWor() {
		return passWor;
	}
	public void setPassWor(String passWor) {
		this.passWor = passWor;
	}
	public String getChuSoHuu() {
		return chuSoHuu;
	}
	public void setChuSoHuu(String chuSoHuu) {
		this.chuSoHuu = chuSoHuu;
	}
	public String getLoaiTaiKhoan() {
		return loaiTaiKhoan;
	}
	public void setLoaiTaiKhoan(String loaiTaiKhoan) {
		this.loaiTaiKhoan = loaiTaiKhoan;
	}
	public createAccount(String userName, String passWor, String chuSoHuu, String loaiTaiKhoan) {
		super();
		this.userName = userName;
		this.passWor = passWor;
		this.chuSoHuu = chuSoHuu;
		this.loaiTaiKhoan = loaiTaiKhoan;
	}
	public createAccount() {
		super();
	}
	
	
}
