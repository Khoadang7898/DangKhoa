package reqServer;

import java.util.GregorianCalendar;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Nguoi")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Nguoi {
	//khu vực làm phiền ae ko động đến Có j liên hệ Khoa Pro lây dơ
	@XmlElement
	private String hoVaTen;
	@XmlElement
	private String ngayThangNamSinh;
	@XmlElement
	private String diaChi;
	@XmlElement
	private int sDT;
	@XmlElement
	private String gioiTinh;
	@XmlElement
	private String hinhAnh;
	public Nguoi()
	{
		
	}
	
	public Nguoi(String hoVaTen, String ngayThangNamSinh, String diaChi, int sDT,String gioitinh,String hinhanh) {
		super();
		this.hoVaTen = hoVaTen;
		this.ngayThangNamSinh = ngayThangNamSinh;
		this.diaChi = diaChi;
		this.sDT = sDT;
		this.hinhAnh=hinhanh;
		this.gioiTinh=gioitinh;
	}

	//Khu vực ae tùy ý nghịch
	public String ChuyenKieuGregorianCalendar(String n)
	{
		
		 String [] list1 = n.split("-");
         String [] list2 = list1[2].split("T");
         System.out.println(list2[0]+"-"+ list1[1]+ "-"+list1[0]);
         return null;
	}
	
	public String LayHoVaTen() {
		return this.hoVaTen;
	}

	public void GanHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}

	public String LayNgayThangNamSinh() {
		String [] list1 = this.ngayThangNamSinh.split("-");
        String [] list2 = list1[2].split("T");
        return (list2[0]+"-"+ list1[1]+ "-"+list1[0]);
	}

	public void GanNgayThangNamSinh(int a, int b, int c) {
		this.ngayThangNamSinh = Integer.toString(c)+"-"+Integer.toString(b)+"-"+Integer.toString(a);
	}
	public void GanNgayThangNamSinhS(String c) {
		this.ngayThangNamSinh = c;
	}


	public String LayDiaChi() {
		return this.diaChi;
	}

	public void GanDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public int LaySDT() {
		return this.sDT;
	}

	public void GanSDT(int sDT) {
		this.sDT = sDT;
	}
	public String LayGioiTinh() {
		return gioiTinh;
	}

	public void GanGioiTinh(String Gioitinh) {
		this.gioiTinh = Gioitinh;
	}
	public String LayHinhAnh() {
		return this.hinhAnh;
	}

	public void GanHinhAnh(String hinhanh) {
		this.hinhAnh = hinhanh;
	}

}
