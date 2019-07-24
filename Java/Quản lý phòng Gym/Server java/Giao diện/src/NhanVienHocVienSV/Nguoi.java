package NhanVienHocVienSV;
import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Nguoi")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Nguoi {
	@XmlElement
	private String HoVaTen;
	@XmlElement
	private GregorianCalendar NgayThangNamSinh;
	@XmlElement
	private String DiaChi;
	@XmlElement
	private String SDT;
	@XmlElement
	private String GioiTinh;
	@XmlElement
	private String HinhAnh;
	public Nguoi(String hoVaTen, GregorianCalendar ngayThangNamSinh, String diaChi, String sDT, String gioiTinh,
			String hinhAnh) {
		this.HoVaTen = hoVaTen;
		this.NgayThangNamSinh = ngayThangNamSinh;
		this.DiaChi = diaChi;
		this.SDT = sDT;
		this.GioiTinh = gioiTinh;
		this.HinhAnh = hinhAnh;
	}
	public Nguoi() {
		
	}
	public String getHoVaTen() {
		return HoVaTen;
	}
	public void setHoVaTen(String hoVaTen) {
		HoVaTen = hoVaTen;
	}
	public GregorianCalendar getNgayThangNamSinh() {
		return NgayThangNamSinh;
	}
	public void setNgayThangNamSinh(GregorianCalendar ngayThangNamSinh) {
		NgayThangNamSinh = ngayThangNamSinh;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}
	public String getHinhAnh() {
		return HinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		HinhAnh = hinhAnh;
	}
	
	
	
	
	
	
	
//	public Nguoi()
//	{
//
//	}
//	
//	public Nguoi(String hoVaTen, GregorianCalendar ngayThangNamSinh, String diaChi, int sDT,String gioitinh,String hinhanh) {
//		super();
//		this.HoVaTen = hoVaTen;
//		this.NgayThangNamSinh = ngayThangNamSinh;
//		this.DiaChi = diaChi;
//		this.SDT = sDT;
//		this.HinhAnh=hinhanh;
//		this.GioiTinh=gioitinh;
//	}
//
//	public String LayHoVaTen() {
//		return this.HoVaTen;
//	}
//
//	public void GanHoVaTen(String hoVaTen) {
//		this.HoVaTen = hoVaTen;
//	}
//
//	public GregorianCalendar LayNgayThangNamSinh() {
//		
//		return this.NgayThangNamSinh;
//	}
//	public String LayNgayThangNamSinhString() {
//		String n="";
//		n = Integer.toString(this.NgayThangNamSinh.get(Calendar.DATE)) + "-" +Integer.toString(this.NgayThangNamSinh.get(Calendar.MONTH)) + "-"+Integer.toString(this.NgayThangNamSinh.get(Calendar.YEAR));
//		return n;
//	}
	public void GanNgayThangNamSinh(int a, int b, int c) {
		this.NgayThangNamSinh = (GregorianCalendar) GregorianCalendar.getInstance();
		this.NgayThangNamSinh.set(a,b,c);
	}
//
//	public String LayDiaChi() {
//		return this.DiaChi;
//	}
//
//	public void GanDiaChi(String diaChi) {
//		this.DiaChi = diaChi;
//	}
//
//	public int LaySDT() {
//		return this.SDT;
//	}
//
//	public void GanSDT(int sDT) {
//		this.SDT = sDT;
//	}
//	public String LayGioiTinh() {
//		return GioiTinh;
//	}
//
//	public void GanGioiTinh(String Gioitinh) {
//		this.GioiTinh = Gioitinh;
//	}
//	public String LayHinhAnh() {
//		return this.HinhAnh;
//	}
//
//	public void GanHinhAnh(String hinhanh) {
//		this.HinhAnh = hinhanh;
//	}


}
