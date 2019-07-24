package com.server.app;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="HocVien")
@XmlAccessorType(XmlAccessType.FIELD)
public class test {
	@XmlElement
	private String name;
	@XmlElement
	private String age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public test(String name, String age) {
		super();
		this.name = name;
		this.age = age;
	}
	public test() {
		super();
	}
	
}
