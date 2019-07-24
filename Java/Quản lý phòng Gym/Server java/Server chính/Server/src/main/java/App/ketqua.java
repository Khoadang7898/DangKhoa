package App;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="baitap")
@XmlAccessorType(XmlAccessType.FIELD)
public class ketqua {
	@XmlElement
	private String ketqua;

	public String getKetqua() {
		return ketqua;
	}

	public void setKetqua(String ketqua) {
		this.ketqua = ketqua;
	}

	public ketqua(String ketqua) {
		super();
		this.ketqua = ketqua;
	}

	public ketqua() {
		super();
	}
	
}
