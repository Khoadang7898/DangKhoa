package App;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="FogetPassWord")
@XmlAccessorType(XmlAccessType.FIELD)
public class FogetPassWord {
	@XmlElement
	private String username;
	@XmlElement
	private String maChuSoHuu;
	@XmlElement
	private String passNew;
	
	public String getPassNew() {
		return passNew;
	}
	public void setPassNew(String passNew) {
		this.passNew = passNew;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMaChuSoHuu() {
		return maChuSoHuu;
	}
	public void setMaChuSoHuu(String maChuSoHuu) {
		this.maChuSoHuu = maChuSoHuu;
	}
	public FogetPassWord(String username, String maChuSoHuu) {
		super();
		this.username = username;
		this.maChuSoHuu = maChuSoHuu;
		this.passNew="no";
	}
	public FogetPassWord(String username, String maChuSoHuu, String passNew) {
		super();
		this.username = username;
		this.maChuSoHuu = maChuSoHuu;
		this.passNew = passNew;
	}
	public FogetPassWord() {
		super();
	}
	
}
