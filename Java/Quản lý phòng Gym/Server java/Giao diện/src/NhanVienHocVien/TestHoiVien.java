package NhanVienHocVien;

import java.util.GregorianCalendar;

public class TestHoiVien {

	@SuppressWarnings("unused")
	public static void main(String [] args)
	{
		GregorianCalendar ngayThangNamSinh=(GregorianCalendar) GregorianCalendar.getInstance();
		GregorianCalendar ngayDangKy=(GregorianCalendar) GregorianCalendar.getInstance();
		GregorianCalendar ngayBatDau=(GregorianCalendar) GregorianCalendar.getInstance();
		GregorianCalendar ngayKetThuc=(GregorianCalendar) GregorianCalendar.getInstance();
		ngayThangNamSinh.set(1998, 03, 16);
		ngayDangKy.set(2019, 4, 30);
		ngayBatDau.set(2019, 4, 30);
		ngayKetThuc.set(2019, 5, 30);
//	    HocVien a = new HocVien("NTD",ngayThangNamSinh,"KTX Khu A",5981051,"HV1","DV1", "HLV1",ngayDangKy,
//				ngayBatDau, ngayKetThuc, 30, "Nam",
//				1.72, 62);
//		System.out.println(a.toString());
//		a.ChuanDoan();
//		HocVien a = new HocVien("NTD",ngayThangNamSinh,"KTX Khu A",5981051,"HV1","DV1", "HLV1",ngayDangKy,
//				ngayBatDau, ngayKetThuc, 30, "Nam",
//			"/hinhanh",57,1.72);
//		System.out.println(a.toString());
//		System.out.println("Chiều cao: "+a.ChieuCao);
//		System.out.println("Cân Nặng: "+a.CanNang);
//		a.ChuanDoan();
	}

}
