package DBsql;
//à ae vui lòng tải mysql-connector-java-8.0.16.jar 
//add vô thư viện của các cậu -> code các cậu tự code để nhớ nha, chụp màn hình chép qua cũng đc ahihi
import java.net.URISyntaxException;

import App.FogetPassWord;
import App.baitap;
import App.createAccount;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.server.app.listdata;
import com.server.app.thongbao;

import App.dataApp;
import App.ketqua;
import App.lichtap;
import App.loginApp;
import App.result;
import CoSoVatChatSV.ThietBiPhongTap;
import DBsql.ConnectionSQL;
import NhanVienHocVienSV.HocVien;
import NhanVienHocVienSV.NhanVien;
import SanPhamDichVuSV.DichVu;

import com.server.app.QueryServer;
public class interfaceSQL {
	public static int numberlichtap=0;
	
	//Ok test nhẹ nào ae
//	 public static void main(String[] args)  {
//		 GregorianCalendar NgaySanXuat = new GregorianCalendar();
//		 NgaySanXuat.set(2019,10,21);
//		 ThietBiPhongTap tap = new ThietBiPhongTap("TB020", "ThietBiPhongTapbykoa", "Good", NgaySanXuat, 20);
//		 QueryServer query = new QueryServer("SELECT","HocVien","maSoHoiVien","HV035");
//		 	listdata list = QueryMySql(query);
//		 	for(HocVien hv : list.getListhoivien())
//		 	{
//		 		System.out.println("1---> "+hv.getHoVaTen());
//		 		System.out.println("2---> "+EditString(hv.getNgayThangNamSinh()));
//		 		System.out.println("3---> "+EditString(hv.getNgayBatDau()));
//		 	}
//		 	System.out.println("Ok ----> "+Integer.toString(list.getResult()));
//		
//	 }
	 public static String EditString(GregorianCalendar k)
	 {
		 return (Integer.toString(k.get(Calendar.YEAR))+"-"+Integer.toString(k.get(Calendar.MONTH)+1)+"-"+Integer.toString(k.get(Calendar.DAY_OF_MONTH)));
	 }
	 public static listdata StatementUPIS(QueryServer q)
	 {
		// System.out.print("upindel");
		 listdata list = new listdata();
		 Connection connection;
		 String query= "";
		 if(q.getNamequery().equals("UPDATE"))
		 {
			 if(q.getNametable().equals("NhanVien"))
			 {
				 query = "UPDATE  nhanvien SET hoTen = '"+q.getNhanvien().getHoVaTen() +"', ngaySinh = '"+(q.getNhanvien().getNgayThangNamSinh()) + "', gioiTinh = '"+q.getNhanvien().getGioiTinh()+"', diaChi = '"+q.getNhanvien().getDiaChi()+"', soDienThoai = " + q.getNhanvien().getSDT()+", chucVu = '" + q.getNhanvien().getChucVu()+"', luong = "+(q.getNhanvien().getLuongCoBan())+" , hinh='"+q.getNhanvien().getHinhAnh()+"' WHERE  maSoNhanVien='"+q.getNhanvien().getMaNhanVien()+"'";
			 }
			 else 
				 if(q.getNametable().equals("HocVien"))
				 {
					
					 query = "UPDATE  hoivien SET hoTen ='"+q.getHocvien().getHoVaTen()+"', diaChi = '"+q.getHocvien().getDiaChi()+"', ngaySinh='"+(q.getHocvien().getNgayThangNamSinh())+"', soDienThoai ="+q.getHocvien().getSDT()+", maGoi = '"+q.getHocvien().getMaSoGoiDichVu()+"', maHLV = '"+q.getHocvien().getMaSoHuanLuyenVien()+"', ngayDangKy='"+(q.getHocvien().getNgayDangKy())+"',hinh='"+q.getHocvien().getHinhAnh()+"', ngayBatDau = '"+(q.getHocvien().getNgayBatDau())+"', ngayKetThuc='"+(q.getHocvien().getNgayKetThuc())+"', soNgayHieuLuc ="+q.getHocvien().getSoNgayHieuLuc()+", gioiTinh='"+q.getHocvien().getGioiTinh()+ "',chieuCao = "+q.getHocvien().getChieuCao()+", " + 
						 		" canNang = "+q.getHocvien().getCanNang()+" Where maSoHoiVien='"+q.getHocvien().getMaSoHocVien()+"'";
					 
				 }
				 else if(q.getNametable().equals("DichVu"))
				 {
					 query = "UPDATE  goidichvu SET thoiGianGoi='"+q.getDichvu().getSoNgayHieuLuc()+"', moTaGoi='"+q.getDichvu().getMoTaGoi()+"',giaGoi = "+q.getDichvu().getGiaGoi()+"  Where maGoi='"+q.getDichvu().getMaGoi()+"'";
				 }
				 else if(q.getNametable().equals("ThietBiPhongTap"))
				 {
					 query = "UPDATE  cosovatchat SET tenThietBi='"+q.getThietbiphongtap().getTenThietBi() +"',gia="+q.getThietbiphongtap().getGia()+", ngayMua = '"+q.getThietbiphongtap().getNgaySanXuat()+"', tinhTrang = '"+q.getThietbiphongtap().getTinhTrang()+"'  WHERE maThietBi ='"+ q.getThietbiphongtap().getMaThietBi()+"';";
				 }
			 
		 }
		 else if(q.getNamequery().equals("INSERT"))
		 {
			 if(q.getNametable().equals("NhanVien"))
			 {
				 query = "Insert into nhanvien (maSoNhanVien,hoTen,ngaySinh,gioiTinh,diaChi,soDienThoai,chucVu,luong)   values ('"+q.getNhanvien().getMaNhanVien() +"','"+q.getNhanvien().getHoVaTen()+"','"+(q.getNhanvien().getNgayThangNamSinh())+"','"+q.getNhanvien().getGioiTinh()+"','"+q.getNhanvien().getDiaChi()+"',"+q.getNhanvien().getSDT()+",'"+q.getNhanvien().getChucVu()+"',"+q.getNhanvien().getLuongCoBan()+")";
			 }
			 else 
				 if(q.getNametable().equals("HocVien"))
				 {
					 query="Insert into hoivien (maSoHoiVien, hoTen,diaChi,ngaySinh,soDienThoai,maGoi,maHLV,ngayDangKy,ngayBatDau,ngayKetThuc,soNgayHieuLuc,gioiTinh,chieuCao,canNang,hinh)"
					 		+ " values ('"+q.getHocvien().getMaSoHocVien()+"','"+q.getHocvien().getHoVaTen()+"','"+q.getHocvien().getDiaChi()+"','"+q.getHocvien().getNgayThangNamSinh()+"',"
							 +q.getHocvien().getSDT()+",'"+q.getHocvien().getMaSoGoiDichVu()+"','"+q.getHocvien().getMaSoHuanLuyenVien()+"','"+q.getHocvien().getNgayDangKy()+"','"+q.getHocvien().getNgayBatDau()+"','"+q.getHocvien().getNgayKetThuc()+"',"+q.getHocvien().getSoNgayHieuLuc()+",'"+q.getHocvien().getGioiTinh()+"',"+q.getHocvien().getChieuCao()+","+q.getHocvien().getCanNang()+",'"+q.getHocvien().getHinhAnh()+"')";
				 }
				 else if(q.getNametable().equals("DichVu"))
				 {
					 query = "Insert into goidichvu (maGoi,thoiGianGoi,moTaGoi,giaGoi)  values ('"+q.getDichvu().getMaGoi()+"',"+q.getDichvu().getSoNgayHieuLuc()+",'"+q.getDichvu().getSoNgayHieuLuc()+"',"+q.getDichvu().getGiaGoi()+")";
				 }
				 else if(q.getNametable().equals("ThietBiPhongTap"))
				 {
					 query = "Insert into cosovatchat (maThietBi,tenThietBi,ngayMua,tinhTrang) " +" values ('"+q.getThietbiphongtap().getMaThietBi()+"','"+q.getThietbiphongtap().getTenThietBi()+"','"+q.getThietbiphongtap().getNgaySanXuat()+"','"+q.getThietbiphongtap().getTinhTrang()+"')";
				 }
			
		 }
		 System.out.print(query);
		 try {
			 
			connection = ConnectionSQL.getConnection();
			Statement  statement = connection.createStatement();
			int rowCount = statement.executeUpdate(query);
			System.out.print(rowCount);
			list.setResult(rowCount);
		
		 } catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return list;
	 }
	 public static GregorianCalendar EditGregorianCalendar(String k)
	 {
		 GregorianCalendar n = new GregorianCalendar();
		 if(k!=null)
		 {
			 if(!k.equals("null")) {
		String[] l = k.split("-");
		 String[]l2 =l[2].split(" ");
		 n.set(Integer.parseInt(l[0]), Integer.parseInt(l[1]), Integer.parseInt(l2[0]));
		 }
		 }
		 return n;
	 }
	 
	 
	 public static ArrayList<HocVien> StatementSHV(String query)
	 {
		 ArrayList<HocVien> list = new ArrayList<>();
		 Connection connection;
		 try {
		
			connection = ConnectionSQL.getConnection();
			Statement statement = connection.createStatement();
			System.out.print(query);
			ResultSet rs = statement.executeQuery(query);
				while(rs.next()) {
					String maSoHoiVien= rs.getString("maSoHoiVien");
					String hoTen= rs.getString("hoTen"); 
					String diaChi= rs.getString("diaChi"); 
					String ngaySinh = rs.getString("ngaySinh");
					//GregorianCalendar ngaySinh = EditGregorianCalendar(c);
					int soDienThoai = rs.getInt("soDienThoai");
					String maGoi = rs.getString("maGoi");
					String maHLV = rs.getString("maHLV");
					String ngayDangKy = rs.getString("ngayDangKy");
					//GregorianCalendar ngayDangKy = EditGregorianCalendar(tam);
					String ngayBatDau = rs.getString("ngayBatDau");
					//GregorianCalendar ngayBatDau = EditGregorianCalendar(e);
					String ngayKetThuc = rs.getString("ngayKetThuc");
				//	GregorianCalendar ngayKetThuc =EditGregorianCalendar(q);
					String gioiTinh = rs.getString("gioiTinh");
					int soNgayHieuLuc= rs.getInt("soNgayHieuLuc");
					double chieuCao= rs.getDouble("chieuCao");
					double canNang= rs.getDouble("canNang");
					String hinh= rs.getString("hinh");
					HocVien HV = new HocVien(hoTen,ngaySinh,diaChi,soDienThoai,gioiTinh,hinh,maSoHoiVien, maGoi, maHLV, ngayDangKy, ngayBatDau, ngayKetThuc, soNgayHieuLuc, chieuCao, canNang);
					list.add(HV);
			}
				
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return list;
	 }
	 
	 public static ArrayList<NhanVien> StatementSNV(String query)
	 {ArrayList<NhanVien> list = new ArrayList<>();
		 Connection connection;
		 try {
			 
			connection = ConnectionSQL.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				String maSoNhanVien = rs.getString("maSoNhanVien");
				String hoTen = rs.getString("hoTen");
				String ngaySinh = rs.getString("ngaySinh");
			//	GregorianCalendar ngaySinh = (GregorianCalendar) Calendar.getInstance();
				//ngaySinh.setTime(tam);
				String gioiTinh = rs.getString("gioiTinh");
				String diaChi= rs.getString("diaChi");
				int soDienThoai = rs.getInt("soDienThoai");
				String chucVu = rs.getString("chucVu");
				double luong = rs.getDouble("luong");
				String hinh = rs.getString("hinh");
				NhanVien NV = new NhanVien(hoTen, diaChi, soDienThoai, ngaySinh, maSoNhanVien, chucVu, luong,luong, gioiTinh, hinh);
				list.add(NV);
			}
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return list;
		
	 }
	 public static ArrayList<DichVu> StatementSEDV(String query)
	 {ArrayList<DichVu> list = new ArrayList<>();
		 Connection connection;
		 try {
			 
			connection = ConnectionSQL.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				String maGoi = rs.getString("maGoi");
				String moTaGoi = rs.getString("moTaGoi");
				int soNgayHieuLuc = rs.getInt("thoiGianGoi");
				float giaGoi = rs.getFloat("giaGoi");
				DichVu DV = new DichVu(maGoi, moTaGoi, soNgayHieuLuc, giaGoi);
				list.add(DV);
			}
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return list;
	 }
	 public static ArrayList<ThietBiPhongTap> StatementSETB(String query)
	 { ArrayList<ThietBiPhongTap> list = new ArrayList<>();
		 Connection connection;
		 try {
			
			connection = ConnectionSQL.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				String MaThietBi = rs.getString("maThietBi");
				String TenThietBi = rs.getString("tenThietBi");
				String TinhTrang = rs.getString("tinhTrang");
				String NgaySanXuat = rs.getString("ngayMua");
				float Gia = rs.getFloat("gia");
				ThietBiPhongTap tb = new ThietBiPhongTap(MaThietBi, TenThietBi, TinhTrang, NgaySanXuat, Gia);
				list.add(tb);
			}
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return list;
	 }

	 
	 public static listdata Select(QueryServer query)
	 {
		 listdata list = new listdata();
		 String nametable = query.getNametable();
		 System.out.print(nametable);
		 if(nametable.equals("HocVien"))
		 {
			 	String q="" ;
			 if(!query.getThuoctinh().equals("*")) {
			  q = "SELECT * from hoivien where "+ query.getThuoctinh()+"='"+query.getGiatri()+"'";
			 }
			 else
			 {
				  q ="SELECT * from hoivien";
			 }
			 System.out.print(q);
			 ArrayList<HocVien> hv = new ArrayList<HocVien>();
			 hv = StatementSHV(q);
			 list.setListhoivien(hv);
	
		 
		 }
		 else if(nametable.equals("NhanVien"))
		 {
			 String q = "";
			 if(!query.getThuoctinh().equals("*")) {
			  q = "SELECT * from nhanvien where "+ query.getThuoctinh()+"='"+query.getGiatri()+"'";
			 }
			 else
			 {
				  q ="SELECT * from nhanvien";
			 }
		
		
			 ArrayList<NhanVien> hv = new ArrayList<NhanVien>();
			 hv = StatementSNV(q);
			 list.setListnhanvien(hv);

			 
		 }
		 else if(nametable.equals("DichVu"))
		 {
			 String q = "";
			 if(!query.getThuoctinh().equals("*")) {
			  q = "SELECT * from goidichvu where "+ query.getThuoctinh()+"='"+query.getGiatri()+"'";
			 }
			 else
			 {
				  q ="SELECT * from goidichvu";
			 }
		

			 ArrayList<DichVu> hv = new ArrayList<DichVu>();
			 hv = StatementSEDV(q);
			 list.setListdichvu(hv);

			 
		 }
		 else 
			 if(nametable.equals("ThietBiPhongTap"))
			 {
				 String q = "";
				 if(!query.getThuoctinh().equals("*")) {
				  q = "SELECT * from cosovatchat where "+ query.getThuoctinh()+"='"+query.getGiatri()+"'";
				 }
				 else
				 {
					  q ="SELECT * from cosovatchat";
				 }
			
	
				 ArrayList<ThietBiPhongTap> hv = new ArrayList<ThietBiPhongTap>();
				 hv = StatementSETB(q);
				 list.setThietbiphongtap(hv);
				
			 }
		  return list;
	
	 }
	 

	 
	 
	 
	 
	 
	 public static result Login(loginApp lg)
	 {
		 result re = new result();
		 try {
			Connection connect = ConnectionSQL.getConnection();
			Statement st = connect.createStatement();
			
			String query = "SELECT * from thongtintaikhoan Where userName ='"+lg.getUsername()+"' AND passWord ='"+lg.getPassword()+"'";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				String chuSoHuu = rs.getString("chuSoHuu");
				re.setResult(1);
				re.setMaLogin(chuSoHuu);
			}
			
		 
		 
		 } catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return re;
	 }
	 public static dataApp getDataUser(loginApp lg)
	 {
		 dataApp data = new dataApp();
		 result rs = Login(lg);
		 data.setResult(rs.getResult());
		 if(rs.getResult()==1)
		 {
			 
			 String query = "SELECT * from hoivien where maSoHoiVien ='"+rs.getMaLogin()+"'";
			 ArrayList<HocVien> list = StatementSHV(query);
			 if(list!=null) {
			 for(HocVien hv : list)
			 {
				 data.setUser(hv);
			 }
			 
			 }
			 query = "SELECT * from nhanvien where chuVu = 'HLV'";
			 ArrayList<NhanVien> listNV = StatementSNV(query);
			 data.setHuanluyenvien(listNV);
			 query= "SELECT * from goidichvu";
			 ArrayList<DichVu> listDV = StatementSEDV(query);
			 data.setDichvu(listDV);
			 Connection connect;
			try {
				connect = ConnectionSQL.getConnection();
				 Statement st= connect.createStatement();
				 ResultSet rs1 = st.executeQuery("SELECT * from baitap ");
				 List<baitap> bt = new ArrayList<baitap>();
				 while(rs1.next())
				 {
					 String maBaiTap = rs1.getString("maBaiTap");
					 String tenBaiTap = rs1.getString("tenBaiTap");
					 String nhomCo = rs1.getString("nhomCo");
					 String moTaKyThuat = rs1.getString("moTaKyThuat");
					 baitap b = new baitap(tenBaiTap, nhomCo, moTaKyThuat, maBaiTap);
					 bt.add(b);
				 }
				 if(bt!=null)
					 data.setBaitap(bt);
				 
				List<lichtap> ll = new ArrayList<lichtap>();
				 ResultSet rs2 = st.executeQuery("SELECT * from lichtap where maHoiVien='"+rs.getMaLogin()+"'");
				 while(rs2.next())
				 {
					 String maLichTap = rs2.getString("maLichTap");
					 String maBaiTap = rs2.getString("maBaiTap");
					 String ngayTap = rs2.getString("ngayTap");
					 String maHoiVien = rs2.getString("maHoiVien");
					 lichtap lt = new lichtap(maLichTap, maBaiTap, ngayTap, maHoiVien);
					 ll.add(lt);
				 }
				 if(ll!=null)
					 data.setLichtap(ll);
				 
				 List<thongbao> tb = new ArrayList<thongbao>();
				 ResultSet rs3 = st.executeQuery("SELECT * from thongbao where doiThuong='hoivien' AND trangThai='active'");
				 while(rs3.next())
				 {
					 String maThongBao = rs3.getString("maThongBao");
					 String ngayThongBao = rs3.getString("ngayThongBao");
					 String noiDung = rs3.getString("noiDung");
					 String trangThai = rs3.getString("trangThai");
					 String doiTuong = rs3.getString("doiTuong");
					 thongbao t = new thongbao(maThongBao, ngayThongBao, noiDung, trangThai, doiTuong);
					 tb.add(t);
				 }
				 
				 if(tb!=null)
					 data.setThongbao(tb);
				 else 
					 data.setThongbao(new ArrayList<thongbao>());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 
		 }
		 
		 
		 return data;
		 
	 }
	public static int setLichTap(lichtap model) {
		try {
			Connection connect = ConnectionSQL.getConnection();
			Statement st = connect.createStatement();
			int k=0;
			k = st.executeUpdate("UPDATE lichtap set maBaiTap='"+model.getMaBaiTap()+"', ngayTap='"+model.getNgayTap()+"' where maHoiVien='"+model.getMaHoiVien()+"' AND maLichTap='"+model.getMaLichTap()+"'");
			return k;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	public static int setNewLichTap(lichtap model) {
		try {
			Connection connect = ConnectionSQL.getConnection();
			Statement st = connect.createStatement();
			int k=0;
			numberlichtap = numberlichtap+1;
			model.setMaLichTap(Integer.toString(numberlichtap));
			k = st.executeUpdate("INSERT into lichtap (maBaiTap, ngayTap,maLichTap,maHoiVien) value ('"+model.getMaBaiTap()+"','"+model.getNgayTap()+"','"+model.getMaLichTap()+"','"+model.getMaHoiVien()+"')");
			return k;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	public static int setNewBaiTap(baitap model) {
		try {
			Connection connect = ConnectionSQL.getConnection();
			Statement st = connect.createStatement();
			int k=0;
			k = st.executeUpdate("INSERT into baitap (maBaiTap, tenBaiTap,nhomCo,moTaKyThuat) value ('"+model.getMaBaiTap()+"','"+model.getTenBaiTap()+"','"+model.getNhomCo()+"','"+model.getMoTaKyThuat()+"')");
			return k;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	public static ketqua FogetPassWord(FogetPassWord data) {
		String query ="";
		if(!data.getPassNew().equals("no"))
		{
			 query ="UPDATE thongtintaikhoan  SET  passWord = '"+data.getPassNew()+"'WHERE userName ='" +data.getUsername()+ 
					"' AND chuSoHuu='"+data.getMaChuSoHuu()+"'";
			 try {
					Connection connect = ConnectionSQL.getConnection();
					Statement st  = connect.createStatement();
					int k = st.executeUpdate(query);
					return new ketqua(Integer.toString(k));
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		else
		{
			query  = "SELECT * from thongtintaikhoan  WHERE userName ='"+data.getUsername()+"' AND chuSoHuu='"+data.getMaChuSoHuu()+"'";
			try {
				System.out.print(query);
				Connection connect = ConnectionSQL.getConnection();
				Statement st  = connect.createStatement();
				ResultSet k = st.executeQuery(query);
				ketqua ok = new ketqua("0");
					while(k.next())
					{
						String pass = k.getString("passWord");
						System.out.print(pass);
						ok.setKetqua(pass);
					}
					return ok;
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		// TODO Auto-generated method stub
		return new ketqua("0");
	}
	public static ketqua createAccount(createAccount data) {
		Connection connect;
		try {
			connect = ConnectionSQL.getConnection();
			
			Statement st  = connect.createStatement();
			String query ="INSERT into thongtintaikhoan (userName,passWord,chuSoHuu,loaiTaiKhoan) value ('"+data.getUserName()+"','"+data.getPassWor()+"','"+data.getChuSoHuu()+"','"+data.getLoaiTaiKhoan()+"')";
			System.out.print(query);
			int k = st.executeUpdate(query);
			return new ketqua(Integer.toString(k));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ketqua("0");
	}
	 

	
}

//	/*Khu vực thao tác Dịch Vụ*/
//	/*Hàm lấy toàn bộ thông tin dịch vụ*/
//	public static ArrayList<DichVu>getALLDichVuInDB() {
//		ArrayList<DichVu> list = new ArrayList<>();
//		Connection connect = ConnectionSQL.getConnection(); 
//		try {
//			Statement st= connect.createStatement();
//			ResultSet rs = st.executeQuery("SELECT * FROM DICHVU");
//			while(rs.next()) {
//				String MaGoi= rs.getString("MAGOI");
//				String MoTaGoi= rs.getString("MOTAGOI"); 
//				int SoNgayHieuLuc= rs.getInt("SONGAYHIEULUC");
//				float GiaGo= rs.getFloat("GIAGOI");
//				DichVu DV = new DichVu(MaGoi,MoTaGoi,GiaGo,SoNgayHieuLuc);
//				list.add(DV);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			connect.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//	}
//	/*Thêm dịch vụ*/
//	
//	public static DichVu ThemDichVu(String Ma,String MT,String SN,String G) {
//		
//		Connection connect = ConnectionSQL.getConnection();
//		try {
//			Statement st = connect.createStatement();
//			ResultSet rs = st.executeQuery("SELECT * FROM DichVu where MAGOI ='"+Ma+"'");
//			int i=0;
//			while(rs.next())
//			{
//				i++;
//			}
//			if(i==0) {
//			String query = "INSERT INTO DichVu ([MAGOI],[MOTAGOI],[SONGAYHIEULUC] ,[GIAGOI])\r\n" + 
//					"     VALUES" + 
//					"           ('" +Ma+ 
//					"           ','" +MT+ 
//					"           ','" +SN+ 
//					"           ','"+G+"')";
//			st.executeUpdate(query);
//			ResultSet rst = st.executeQuery("SELECT * FROM DichVu where MAGOI ='"+Ma+"'");
//			while(rst.next()) {
//				String MaGoi= rst.getString("MAGOI");
//				String MoTaGoi= rst.getString("MOTAGOI"); 
//				int SoNgayHieuLuc= rst.getInt("SONGAYHIEULUC");
//				float GiaGo= rst.getFloat("GIAGOI");
//				DichVu DV = new DichVu(MaGoi,MoTaGoi,GiaGo,SoNgayHieuLuc);
//				return DV;
//			}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
//	/*Xóa dịch vụ*/
//	public static boolean XoaDichVu(String Ma) {
//		Connection connect = ConnectionSQL.getConnection();
//		try {
//			Statement st = connect.createStatement();
//			String query = "DELETE FROM DichVu  WHERE MANV = '"+Ma+"'";
//			boolean kq= st.execute(query);
//			connect.close();
//			return kq;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			connect.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
//	
//	/*Kết thúc khu vực Dịch vụ*/
//	
//	
//	
//	
//	
//	
//	/*Khu vực Nhân Viên*/
//	
//	/*Lấy thông tin nhân viên theo query truyền vào*/
//	public static ArrayList<NhanVien> getNhanVien(String query)
//	{
//		ArrayList<NhanVien> list = new ArrayList<>();/*List chứa kết quả trả về Object Nhân Viên*/
//		Connection connect = ConnectionSQL.getConnection();/*khởi tạo một kết nối*/
//		try {
//			Statement st = connect.createStatement();/*Khởi tạo một lệnh*/
//			ResultSet rs = st.executeQuery(query);/*Thực hiện lệnh query*/
//			/*Ghi kết quả lấy đc vào list*/
//			while (rs.next())
//			{
//				 String HoVaTen = rs.getString("TEN");
//				 String DiaChi= rs.getNString("DIACHI");
//				 int SDT = rs.getInt("SDT");
//				 String MaNhanVien=rs.getNString("MANV");
//				 String ChucVu = rs.getNString("CHUCVU");
//				 double LuongCoBan = rs.getDouble("LUONGCOBAN");
//				double HeSoLuong = rs.getDouble("HESOLUONG");
//				String LinkAnh= rs.getNString("LinkAnh");
//				String GT ="";
//				Date c = rs.getDate("NGAYTHANGNAMSINH");
//				GregorianCalendar B = (GregorianCalendar) Calendar.getInstance();
//				B.setTime(c);
//				 NhanVien NV = new NhanVien(HoVaTen,DiaChi,SDT,B,MaNhanVien,ChucVu,LuongCoBan,HeSoLuong,LinkAnh,GT);/*Khởi tạo Nhân viên*/
//				 list.add(NV);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			connect.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//	}
//	/*Hàm thêm nhân viên mới*/
//	public static int ThemNhanVien(String Ma ,String Name ,String SDT,String DC ,String CV,String L, String HS,String LinkAnh)/*Chuyền các tham số vào thông tin nhân viên muốn thêm*/
//	{
//		Connection connect = ConnectionSQL.getConnection();
//		try {
//			Statement st = connect.createStatement();
//			String query = "INSERT INTO NhanVien  ([MANV],[TEN],[DIACHI],[SDT],[CHUCVU],[LUONGCOBAN],[HESOLUONG],[NGAYTHANGNAMSINH],[LinkAnh]) VALUES ('"+Ma +" ','"+Name +"','"+DC +" ','"+SDT + "','"+CV +" ','"+L +" ','"+HS + " ',12/12/2012,'"+LinkAnh+"')";
//			st.executeUpdate(query);
//			connect.close();
//			return 1;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			connect.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return 0;
//	}
//	
//	/*Hàm kiểm tra mã nhân viên đã tồn tại chưa*/
//	public static boolean KiemTraNV(String Ma) {
//		Connection connect = ConnectionSQL.getConnection();
//		try {
//			Statement st = connect.createStatement();
//			String query = "SELECT * FROM NhanVien WHERE MANV = '"+Ma+"'";
//			ResultSet rs = st.executeQuery(query);
//			int i = 0;
//			while(rs.next())
//			{
//				i++;
//			}
//			connect.close();
//			if(i>0) return true;
//			else return false;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
//	
//	/*Hàm xóa nhân viên*/
//	public static boolean XoaNhanVien(String Ma) {
//		Connection connect = ConnectionSQL.getConnection();
//		try {
//			Statement st = connect.createStatement();
//			String query = "DELETE FROM NhanVien  WHERE MANV = '"+Ma+"'";
//			boolean kq= st.execute(query);
//			connect.close();
//			return kq;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			connect.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
//	
//	
//	/*Hàm cập nhập Thông tin Nhân Viên*/
//	
//	public static NhanVien CapNhapNhanVien(String Ma,String Name,String DC,String SDT,String CV,String L,String HS,String NTNS,String LinkAnh) {
//		Connection connect = ConnectionSQL.getConnection();
//		try {
//			Statement st = connect.createStatement();
//			String query ="UPDATE NHAN_VIEN SET [TEN] = '" + Name+
//					"'      ,[DIACHI] = '" + DC+
//					"'      ,[SDT] = '" + SDT+
//					"'      ,[CHUCVU] = '" +CV+ 
//					"'      ,[LUONGCOBAN] = '" +L+ 
//					"'      ,[HESOLUONG] = '" + HS+
//					"'      ,[NGAYTHANGNAMSINH] = '" +NTNS +
//					"'      ,[LinkAnh] = '" +LinkAnh+ 
//					"' WHERE MANV = '"+ Ma+"'";
//			st.executeUpdate(query);
//			ResultSet rs = st.executeQuery("SELECT * FROM NhanVien WHERE MANV ='"+Ma+"'");/*Thực hiện lệnh query*/
//			/*Ghi kết quả lấy đc vào list*/
//			while (rs.next())
//			{
//				 String HoVaTen = rs.getString("TEN");
//				 String DiaChi= rs.getNString("DIACHI");
//				 int SDTs = rs.getInt("SDT");
//				 Date NTNS1 = rs.getDate("NGAYTHANGNAMSINH");
//				 GregorianCalendar c =(GregorianCalendar) Calendar.getInstance();
//				 c.setTime(NTNS1);
//				 String MaNhanVien=rs.getNString("MANV");
//				 String GT="";
//				 String ChucVu = rs.getNString("CHUCVU");
//				 double LuongCoBan = rs.getDouble("LUONGCOBAN");
//				double HeSoLuong = rs.getDouble("HESOLUONG");
//				String LinkAnhs= rs.getNString("LINKANH");
//				 NhanVien NV = new NhanVien(HoVaTen,DiaChi,SDTs,c,MaNhanVien,ChucVu,LuongCoBan,HeSoLuong,LinkAnhs,GT);/*Khởi tạo Nhân viên*/
//				 return NV;
//			}
//			
//			
//			connect.close();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//	/*Vùng của hội viên*/
//	
//	public static ArrayList<HocVien> getHocVien(String query)
//	{
//		ArrayList<HocVien> list = new ArrayList<>();/*List chứa kết quả trả về Object Nhân Viên*/
//		Connection connect = ConnectionSQL.getConnection();/*khởi tạo một kết nối*/
//		try {
//			Statement st = connect.createStatement();/*Khởi tạo một lệnh*/
//			ResultSet rs = st.executeQuery(query);/*Thực hiện lệnh query*/
//			/*Ghi kết quả lấy đc vào list*/
//			while (rs.next())
//			{
//				 String HoVaTen = rs.getString("HoVaTen");
//				 String DiaChi= rs.getString("DiaChi");
//				 int SDT = rs.getInt("SDT");
//				 String MaHocVien=rs.getString("MaSoHocVien");
//				 String MaGoi = rs.getString("MaSoGoiDichVu");
//				double ChieuCao = rs.getDouble("ChieuCao");
//				String LinkAnh= rs.getString("HinhAnh");
//				String GT =rs.getString("GioiTinh");
//				String maSoHuanLuyenVien = rs.getString("MaSoHuanLuyenVien");
//				 Calendar c = Calendar.getInstance();
//
//				 String B =  rs.getString("NgayThangNamSinh");
//				 String [] list2 = B.split("-");
//				 c.set(Integer.parseInt(list2[2]), Integer.parseInt(list2[1])-1, Integer.parseInt(list2[0]));
//				String NgayDK  = rs.getString("NgayDangKy");
//				  Calendar cDK = Calendar.getInstance();
//				  String [] list3 = NgayDK.split("-");
//					 cDK.set(Integer.parseInt(list3[2]), Integer.parseInt(list3[1])-1, Integer.parseInt(list3[0]));
//				  
//				  Calendar cBD = Calendar.getInstance();
//				 String NgayBD  = rs.getString("NgayBatDau");
//				 String [] list4 = NgayBD.split("-");
//				 cBD.set(Integer.parseInt(list4[2]), Integer.parseInt(list4[1])-1, Integer.parseInt(list4[0]));
//				 Calendar cKT = Calendar.getInstance();
//				String NgayKT= rs.getString("NgayKetThuc");
//				String [] list1 = NgayKT.split("-");
//				 cKT.set(Integer.parseInt(list1[2]), Integer.parseInt(list1[1])-1, Integer.parseInt(list1[0]));
//				int soNgayHieuLuc = rs.getInt("SoNgayHieuLuc");
//				double canNang = rs.getDouble("CanNang");
//				 HocVien HV = new HocVien(HoVaTen,new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)),DiaChi,SDT,MaHocVien,MaGoi,maSoHuanLuyenVien,
//						 new GregorianCalendar(cDK.get(Calendar.YEAR), cDK.get(Calendar.MONTH), cDK.get(Calendar.DAY_OF_MONTH)),new GregorianCalendar(cBD.get(Calendar.YEAR), cBD.get(Calendar.MONTH), cBD.get(Calendar.DAY_OF_MONTH)),new GregorianCalendar(cKT.get(Calendar.YEAR), cKT.get(Calendar.MONTH), cKT.get(Calendar.DAY_OF_MONTH)),soNgayHieuLuc,GT,LinkAnh,ChieuCao,canNang);/*Khởi tạo Nhân viên*/
//				 list.add(HV);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return list;
//	}
//	
//	/*Cập nhập*/
//	public static int CapNhapHoiVien(String query) {	Connection connect = ConnectionSQL.getConnection();
//		int i=0;
//		try {
//			Statement st = connect.createStatement();
//			
//			i = st.executeUpdate(query);
//			/*Ghi kết quả lấy đc vào list*/
//
//			
//			
//			connect.close();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return i;
//	}
//	
//}
