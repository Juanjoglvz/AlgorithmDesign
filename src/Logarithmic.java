
public class Logarithmic extends Function {
	//function of type logbase (a*x)
	//We assume x > 1
	//we also assume base > 1

	private double base;
	private double a;
	
	public Logarithmic(double base, double a, double linf, double lsup) {
		super(linf, lsup);
		
		//exception should be used to handle this instead of changing it
		if(getlinf() < 1) {
			super.setlinf(1);
		}
		
		//exception should be used to handle this instead of changing it
		if(base <= 1) {
			base = Math.E;
		}
		this.base = base;
		this.a = a;
	}
	
	@Override
	public double max() {
		// A logarightmic function is always increasing
		return getlsup();
	}

	@Override
	public double f(double x) {
		return included(x) ? Math.log(x * a) / Math.log(base) : Double.MIN_VALUE;
	}

	@Override
	public double area() {
		return ((getlsup() * Math.log(getlsup() * a) - getlsup()) / Math.log(base))
				- ((getlinf() * Math.log(getlinf() * a) - getlinf()) / Math.log(base));
	}

	@Override
	public double areaMV(int k) {
		double[] values = new double[k];
		double retval = 0.0;
		
		for (int i = 0; i < k; i++) {
			double x = Math.random() * range() + getlinf();
			double y = f(x);
			
			values[i] = range() * y;
			retval += values[i];
		}
		
		confidenceInterval(values);
		
		return retval;
	}

	@Override
	public double areaP(int k) {
		int hits = 0;
		
		for (int i = 0; i < k; i++) {
			double x = Math.random() * range() + getlinf();
			double y = Math.random() * f(max());
			
			if (y <= f(x)) {
				hits++;
			}
		}
		
		double p = (double) hits / k;
		
		confidenceIntervalP(p, k);
		return range() * f(max()) * p;
	}
	
	@Override
	public double getcintervalPinf() {
		return super.getcintervalPinf() * range() * f(max());
	}
	
	@Override
	public double getcintervalPsup() {
		return super.getcintervalPsup() * range() * f(max());
	}

	@Override
	public void calculate(int k) {
		System.out.printf("Function: f(x)= log%f (%f * x)\n"
				+ super.toString()
				+ "area:\t %f\n"
				+ "areaRiemman:\t %f\n"
				+ "areaMV:\t %f\t Confidence interval: [%f, %f]\n"
				+ "areaP:\t %f\t Confidence interval: [%f, %f]\n",
				base,a,area(),areaRieman(k),areaMV(k),
				getcintervalinf(),getcintervalsup(),areaP(k),
				getcintervalPinf(),getcintervalPsup());

	}

}
