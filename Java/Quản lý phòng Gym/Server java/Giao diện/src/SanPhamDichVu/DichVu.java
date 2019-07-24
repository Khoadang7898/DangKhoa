package SanPhamDichVu;
//import CoSoDuLieu.*;

import java.util.HashMap;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DichVu{
	protected StringProperty MaGoi;
	protected StringProperty MoTaGoi; 
	protected IntegerProperty SoNgayHieuLuc;
	protected StringProperty GiaGoi;
	
//	public DichVu(){
//		this.MaGoi=this.MoTaGoi="";
//		this.GiaGoi = 0.0f;
////		this.TaoDuLieu();
//	}
	//Tạo gói dựa trên tham số số ngày hiệu lực của gói. Mã gói được xác định từ CSDL, Đơn giá phụ thuộc Doanh nghiệp
	public DichVu(String MaGoi,String MoTa,int soNgayHieuLuc, float DonGia){  
		this.setMaGoi(MaGoi);
		this.setMoTaGoi(MoTa);
		this.setSoNgayHieuLuc(soNgayHieuLuc);
		this.setGiaGoi(DonGia);
//		this.CapNhatDuLieu();
	}
	public IntegerProperty SoNgayHieuLucProperty() {
        if (SoNgayHieuLuc == null) SoNgayHieuLuc = new SimpleIntegerProperty(this, "SoNgayHieuLuc");
        return SoNgayHieuLuc;
    }
	public StringProperty MaGoiProperty() {
        if (MaGoi == null) MaGoi = new SimpleStringProperty(this, "MaGoi");
        return MaGoi;
    }
	public StringProperty MoTaGoiProperty() {
        if (MoTaGoi == null) MoTaGoi = new SimpleStringProperty(this, "MoTaGoi");
        return MoTaGoi;
    }
	public StringProperty GiaGoiProperty() {
        if (GiaGoi == null) GiaGoi = new SimpleStringProperty(this, "GiaGoi");
        return GiaGoi;
    }
	public String getMaGoi() {
		return MaGoiProperty().get();
	}

	public void setMaGoi(String maGoi) {
		MaGoiProperty().set(maGoi);;
	}

	public String getMoTaGoi() {
		return MoTaGoiProperty().get();
	}

	public void setMoTaGoi(String moTaGoi) {
		MoTaGoiProperty().set(moTaGoi);;
	}

	public int getSoNgayHieuLuc() {
		return SoNgayHieuLucProperty().get();
	}

	public void setSoNgayHieuLuc(int soNgayHieuLuc) {
		SoNgayHieuLucProperty().set(soNgayHieuLuc);;
	}

	public String getGiaGoi() {
		return GiaGoiProperty().get();
	}

	public void setGiaGoi(Float giaGoi) {
		GiaGoiProperty().set(String.valueOf(giaGoi));;
	}

	//Xuất toàn bộ thông tin dưới dạng chuỗi
	public String toString() {
		return String.format("\n%s : %s\n%s : %s\n%s : %.2f", "Mã gói",this.MaGoi,"Mô tả ",this.MoTaGoi,"Giá",this.GiaGoi);
	}
//	//Trả về toàn bộ thông tin của gói dịch vụ dưới dạng HashMap
//	public HashMap TruyVanThongTin() {
//		HashMap ThongTin = new HashMap();
//		ThongTin.put("Mã gói",this.MaGoi);
//		ThongTin.put("Mô tả gói",this.MoTaGoi);
//		ThongTin.put("Giá gói",this.GiaGoi);
//		return ThongTin;
//	}
//	
//	@Override
//	public double TinhGiaTri() {
//		return this.GiaGoi;
//	}
//	@Override
//	public void TaoDuLieu() {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void CapNhatDuLieu() {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void XoaDuLieu() {
//		// TODO Auto-generated method stub
//		
//	}
	
}
