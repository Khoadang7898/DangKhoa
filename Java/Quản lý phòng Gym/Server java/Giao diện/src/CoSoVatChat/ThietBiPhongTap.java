package CoSoVatChat;
//import CoSoDuLieu.*;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//import CoSoDuLieu.DuocLuuTruTrongDB;

public class ThietBiPhongTap {
	protected IntegerProperty MaThietBi;
	protected StringProperty TinhTrang,TenThietBi;
	protected StringProperty NgaySanXuat;
	protected StringProperty Gia;
	
//	ThietBiPhongTap(){
//		this.MaThietBi=this.TenThietBi=null;
//		this.TinhTrang =null;
//		this.NgaySanXuat = null;
//		this.Gia = 0.0f;
//	}
	public ThietBiPhongTap(int maThietBi, String tenThietBi, String tinhTrang,
			String ngaySanXuat, float gia) {
		super();
		this.setMaThietBi(maThietBi);
		this.setTenThietBi(tenThietBi);
		this.setTinhTrang(tinhTrang);
		this.setNgaySanXuat(ngaySanXuat);
		this.setGia(gia);
	}
	
	public IntegerProperty MaThietBiProperty() {
        if (MaThietBi == null) MaThietBi = new SimpleIntegerProperty(this, "MaThietBi");
        return MaThietBi;
    }
	
	public StringProperty TenThietBiProperty() {
        if (TenThietBi == null) TenThietBi = new SimpleStringProperty(this, "TenThietBi");
        return TenThietBi;
    }
	public StringProperty TinhTrangProperty() {
        if (TinhTrang == null) TinhTrang = new SimpleStringProperty(this, "TinhTrang");
        return TinhTrang;
    }
	public StringProperty NgaySanXuatProperty() {
        if (NgaySanXuat == null) NgaySanXuat = new SimpleStringProperty(this, "NgaySanXuat");
        return NgaySanXuat;
    }
	public StringProperty GiaProperty() {
        if (Gia == null) Gia = new SimpleStringProperty(this, "Gia");
        return Gia;
    }
	public int getMaThietBi() {
		return MaThietBiProperty().get();
	}





	public void setMaThietBi(int maThietBi) {
		MaThietBiProperty().set(maThietBi);;
	}





	public String getTenThietBi() {
		return TenThietBiProperty().get();
	}





	public void setTenThietBi(String tenThietBi) {
		TenThietBiProperty().set(tenThietBi);;
	}





	public String getTinhTrang() {
		return TinhTrangProperty().get();
	}





	public void setTinhTrang(String tinhTrang) {
		TinhTrangProperty().set(tinhTrang);;
	}





	public String getNgaySanXuat() {
		return NgaySanXuatProperty().get();
	}





	public void setNgaySanXuat(String ngaySanXuat) {
		NgaySanXuatProperty().set(ngaySanXuat);;
	}





	public String getGia() {
		return GiaProperty().get();
	}





	public void setGia(float gia) {
		GiaProperty().set(String.valueOf(gia));;
	}





	//	public ThietBiPhongTap(String MaThietBi, String TenThietBi,String TinhTrang,GregorianCalendar NgaySanXuat,float Gia){
//		this.MaThietBi=MaThietBi;
//		this.TenThietBi=TenThietBi;
//		this.TinhTrang =TinhTrang;
//		this.NgaySanXuat = (GregorianCalendar)NgaySanXuat.clone();   // Tránh Exception NullPointer
//		this.Gia = Gia;  
//	}
//	public ThietBiPhongTap(String MaThietBi, String TenThietBi,String TinhTrang,String NgaySanXuat,float Gia){
//		this.MaThietBi=MaThietBi;
//		this.TenThietBi=TenThietBi;
//		this.TinhTrang =TinhTrang;
//		this.Gia = Gia;  
//		//Ngày sản xuất giả sử có format dd/MM/yyyy
//		SimpleDateFormat ft= new SimpleDateFormat("dd/MM/yyyy");
//		this.NgaySanXuat=new GregorianCalendar();
//		this.NgaySanXuat.setTimeInMillis(0);   //Reset về 1970
//		try {
//			this.NgaySanXuat.setTime(ft.parse(NgaySanXuat));
//		}catch (Exception e) {
//			System.out.println("!!! Định dạnh ngày sản xuất khác với định dạng hợp lệ là dd/MM/yyyy.\n=>Ngày sản xuất đã bị đặt tại 1970");
//		}
//	}
//	public HashMap TruyVanThongTin() {
//		HashMap ThongTin = new HashMap();
//		ThongTin.put("Mã thiết bị",this.MaThietBi);
//		ThongTin.put("Tên thiết bị",this.TenThietBi);
//		ThongTin.put("Tình trạng",this.TinhTrang);
//		ThongTin.put("Ngày sản xuất",this.NgaySanXuat);
//		ThongTin.put("Giá",this.Gia);
//		return ThongTin;
//	}
	public String toString() {
		return String.format("\n%s : %s\n%s : %s\n%s : %s\n%s : %td/%tm/%tY\n%s : %.2f (VNĐ)", "Mã thiết bị",this.MaThietBi,"Tên thiết bị",this.TenThietBi,"Tình trạng",this.TinhTrang,"Ngày sản xuất",this.NgaySanXuat,this.NgaySanXuat,this.NgaySanXuat,"Giá",this.Gia);
	}
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
