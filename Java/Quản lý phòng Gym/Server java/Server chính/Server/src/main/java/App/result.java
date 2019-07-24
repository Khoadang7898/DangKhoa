package App;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result")
@XmlAccessorType(XmlAccessType.FIELD)
public class result {
	@XmlElement
	private int result;
	@XmlElement
	private String maLogin;

	public result(int result,String Login) {
		this.result = result;
		this.maLogin = Login;
	}
	
	public result() {
		this.maLogin="";
		this.result=0;
	}
	public String getMaLogin()
	{
		return this.maLogin;
	}
	public void setMaLogin(String Login)
	{
		this.maLogin=Login;
	}
	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
}
