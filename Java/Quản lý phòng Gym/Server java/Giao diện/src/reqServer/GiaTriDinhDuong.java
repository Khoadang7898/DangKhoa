package reqServer;

public class GiaTriDinhDuong {
	class GiaTri{
		protected double Max;
		protected double Min;
		
		public GiaTri() {
			super();
			Max = 0;
			Min = 0;
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
