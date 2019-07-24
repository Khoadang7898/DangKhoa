package NhanVienHocVien;

public class GiaTriDinhDuong {
	class GiaTri{
		protected double Max;
		protected double Min;
		protected double CaloMax;
		protected double CaloMin;
		public GiaTri() {
			super();
			Max = 0;
			Min = 0;
			CaloMax=0;
			CaloMin=0;
		}
		
		public double getCaloMax() {
			return CaloMax;
		}

		public void setCaloMax(double caloMax) {
			CaloMax = caloMax;
		}

		public double getCaloMin() {
			return CaloMin;
		}

		public void setCaloMin(double caloMin) {
			CaloMin = caloMin;
		}

		public double getMax() {
			return Max;
		}
		public void setMax(double max) {
			Max = max;
		}
		public double getMin() {
			return Min;
		}
		public void setMin(double min) {
			Min = min;
		}
	}
}
