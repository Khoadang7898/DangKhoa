//package org.server.Client;
//
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//
//import data.DichVu;
//import data.HocVien;
//import data.NhanVien;
//import data.ThietBiPhongTap;
//
//public class Chuyen {
//	
//	 public static GregorianCalendar EditGregorianCalendar(String k)
//	 {
//		 System.out.print(k);
//		 String[] l = k.split("-");
//		 String [] l2 = l[2].split("T");
//		 GregorianCalendar n = new GregorianCalendar();
//		 n.set(Integer.parseInt(l[0]), Integer.parseInt(l[1]), Integer.parseInt(l2[0]));
//		 return n;
//	 }
//	 public static String EditString(GregorianCalendar k)
//	 {
//		 return (Integer.toString(k.get(Calendar.YEAR))+"-"+Integer.toString(k.get(Calendar.MONTH)+1)+"-"+Integer.toString(k.get(Calendar.DAY_OF_MONTH)));
//	 }
//	 
//	public static HocVien HocVienChinh(reqServer.HocVien HV )
//	{
//		
//		return ( new HocVien(HV.LayHoVaTen(),EditGregorianCalendar(HV.LayNgayThangNamSinh()) , HV.LayDiaChi(), HV.LaySDT(), HV.LayMaSoHocVien(), HV.LayMaSoGoiDichVu(), HV.LayMaSoHuanLuyenVien(), HV.LayNgayDangKy(), HV.LayNgayDangKy(), HV.LayNgayDangKy(), HV.LaySoNgayHieuLuc(), HV.LayGioiTinh(), HV.LayHinhAnh(), HV.LayChieuCao(), HV.LayCanNang()));
//		
//	}
//	public static reqServer.HocVien HocVienReq(HocVien hv)
//	{
//		reqServer.HocVien h = new reqServer.HocVien();
//		h.GanCanNang(hv.LayCanNang());
//		h.GanChieuCao(hv.LayChieuCao());
//		h.GanDiaChi(hv.LayDiaChi());
//		h.GanGioiTinh(hv.LayGioiTinh());
//		h.GanHinhAnh(hv.LayHinhAnh());
//		h.GanHoVaTen(hv.LayHoVaTen());
//		h.GanMaSoGoiDichVu(hv.LayMaSoGoiDichVu());
//		h.GanMaSoHocVien(hv.LayMaSoHocVien());
//		h.GanMaSoHuanLuyenVien(hv.LayMaSoHuanLuyenVien());
//		h.GanNgayBatDauS(hv.LayNgayBatDau());
//		h.GanNgayDangKyS(hv.LayNgayDangKy());
//		h.GanNgayKetThucS(hv.LayNgayKetThuc());
//		h.GanNgayThangNamSinhS(EditString(hv.LayNgayThangNamSinh()));
//		return h;
//	}
//	public static NhanVien NhanVienChinh(reqServer.NhanVien nv)
//	{
//		return(new NhanVien(nv.LayHoVaTen(), nv.LayDiaChi(), nv.LaySDT(), EditGregorianCalendar(nv.LayNgayThangNamSinh()), nv.getMaNhanVien(), nv.getChucVu(), nv.getLuongCoBan(), nv.getHeSoLuong(), nv.LayGioiTinh(), nv.LayHinhAnh()));
//		
//	}
//	public static reqServer.NhanVien NhanVienReq(NhanVien nv)
//	{
//		reqServer.NhanVien n = new reqServer.NhanVien();
//		n.GanDiaChi(nv.LayDiaChi());
//		n.GanGioiTinh(nv.LayGioiTinh());
//		n.GanHinhAnh(nv.LayHinhAnh());
//		n.GanHoVaTen(nv.LayHoVaTen());
//		n.GanNgayThangNamSinhS(EditString(nv.LayNgayThangNamSinh()));
//		n.GanSDT(nv.LaySDT());
//		n.setChucVu(nv.getChucVu());
//		n.setHeSoLuong(nv.getHeSoLuong());
//		n.setLuongCoBan(nv.getLuongCoBan());
//		n.setMaNhanVien(nv.getMaNhanVien());
//		return n;
//	}
//	public static DichVu DichVuChinh(reqServer.DichVu dv)
//	{
//		return (new DichVu(dv.getMaGoi(), dv.getMoTaGoi(), dv.getSoNgayHieuLuc(), dv.getGiaGoi()));
//	}
//	public static reqServer.DichVu DichVuReq(DichVu dv)
//	{
//		return (new reqServer.DichVu(dv.getMaGoi(), dv.getMoTaGoi(), dv.getSoNgayHieuLuc(), Integer.parseInt(dv.getGiaGoi())));
//	}
//	
//	public static ThietBiPhongTap ThietBiPhongTapChinh(reqServer.ThietBiPhongTap tb)
//	{
//		return new ThietBiPhongTap(tb.getMaThietBi(), tb.getTenThietBi(), tb.getTinhTrang(), tb.getNgaySanXuat(), tb.getGia());
//	}
//	
//	public static reqServer.ThietBiPhongTap ThietBiPhongTapReq(ThietBiPhongTap tb)
//	{
//		return new reqServer.ThietBiPhongTap(tb.getMaThietBi(), tb.getTenThietBi(), tb.getTinhTrang(), tb.getNgaySanXuat(), Integer.parseInt(tb.getGia()));
//	}
//}
