package NhanVienHocVien;

import java.util.GregorianCalendar;
import java.util.HashMap;

public class NhanVien extends Nguoi {
	private String MaNhanVien;
	private String ChucVu;
	private double LuongCoBan;
	private double HeSoLuong;
	public String getMaNhanVien() {
		return MaNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		MaNhanVien = maNhanVien;
	}
	public String getChucVu() {
		return ChucVu;
	}
	public void setChucVu(String chucVu) {
		ChucVu = chucVu;
	}
	public double getLuongCoBan() {
		return LuongCoBan;
	}
	public void setLuongCoBan(double luongCoBan) {
		LuongCoBan = luongCoBan;
	}
	public double getHeSoLuong() {
		return HeSoLuong;
	}
	public void setHeSoLuong(double heSoLuong) {
		HeSoLuong = heSoLuong;
	}
	public NhanVien(String hoVaTen, String diaChi, int soDienThoai, GregorianCalendar NgaySinh, String maNhanVien, String chucVu, double luongCoBan, double heSoLuong,String gioiTinh,String hinhanh) {
		super(hoVaTen, NgaySinh,diaChi, soDienThoai,gioiTinh,hinhanh);
		setMaNhanVien(maNhanVien);
		setChucVu(chucVu);
		setLuongCoBan(luongCoBan);
		setHeSoLuong(heSoLuong);
	}
//	public NhanVien() {
//		super();
//		setMaNhanVien(null);
//		setChucVu(null);
//		setLuongCoBan(-1);
//		setHeSoLuong(-1);
//	}
	@Override
	public void KiemSoatRaVao() {
	}
	public double TinhLuong() {
		return this.HeSoLuong*this.LuongCoBan;
	}
//	public HashMap TruyVanThongTin() {
//		HashMap ThongTin = new HashMap();
//		ThongTin.putAll(super.TruyVanThongTin());
//		ThongTin.put("Mã nhân viên: ",this.MaNhanVien);
//		ThongTin.put("Chức vụ: ",ChucVu);
//		ThongTin.put("Lương cơ bản",this.LuongCoBan);
//		ThongTin.put("Hệ số lương",this.HeSoLuong);
//		ThongTin.put("Lương: ",TinhLuong());
//		return ThongTin;
//	}
	public String toString() {
		return String.format("\n%s\n%s : %s\n%s : %s\n%s : %.2f\n%s : %.2f\n%s %.2f",super.toString(),"Mã nhân viên",this.MaNhanVien,"Chức vụ",this.ChucVu,"Lương cơ bản",this.LuongCoBan,"Hệ số lương",this.HeSoLuong,"Lương",this.TinhLuong());
	}
	
	public Object[] ToArray() {
		return new Object[] {MaNhanVien,this.LayHoVaTen(),this.LayGioiTinh(),ChucVu,HeSoLuong,this.LayDiaChi(),LuongCoBan};
	}
}
