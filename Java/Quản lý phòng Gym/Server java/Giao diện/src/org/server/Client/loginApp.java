package org.server.Client;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="loginApp")
@XmlAccessorType(XmlAccessType.FIELD)
public class loginApp {
	@XmlElement
	private String username;
	@XmlElement
	private String password;
	public loginApp(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public loginApp() {
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}