
public class Cuadratic extends Function {
	//assumed only cuadratic monomial of type a * x^2 + c
	//assumed only positive a
	private double a;
	private double c;
	
	public Cuadratic(double a, double c, double linf, double lsup) {
		super(linf, lsup);
		this.a = Math.abs(a);
		this.c = c;
	}

	@Override
	public double f(double x) {
		return included(x) ? a * Math.pow(x, 2) + c : Double.MIN_VALUE;
	}
	
	@Override
	public double max() {
		//we know the absolute minimum of the function is where the first derivative is 0
		double retval;
		
		//if one of the limits is the minimum, the maximum is the other limit
		if(f(getlinf()) == 0.0) {
			retval = getlsup();
		}
		else if (f(getlinf()) == 0.0) {
			retval = getlinf();
		}
		//apply bolzano's theorem to know if there's a maximum in the range
		else if (f(getlinf()) * f(getlsup()) < 0 ) {
			//Bolzano applies. The maximum will be where the first derivative is 0. 
			//since there is no first grade monomial, the minimum is in (0, 0)
			retval = Math.abs(getlinf()) >= Math.abs(getlsup()) ? getlinf() : getlsup();
		}
		else {
			//Bolzano is not applied, we now look if the function is increasing or decreasing
			//we know it will be increasing(or decreasing) in the whole interval because bolzano does not apply
			if ((2 * a * (getlsup() - range())) < 0) {
				//decreasing -> the maximum is the lower limit
				retval = getlinf();
			}
			else { //cant be 0
				//increasing -> the maximum is the upper limit
				retval = getlsup();
			}
		}
		
		return retval;
	}

	@Override
	public double area() {
		return (a * Math.pow(getlsup(), 3) / 3 + c * getlsup()) 
				- (a * Math.pow(getlinf(), 3) / 3 + c * getlinf());
	}

	@Override
	public double areaMV(int k) {
		double[] values = new double[k];
		double retval = 0.0;
		
		for (int i = 0; i < values.length; i++) {
			double x = Math.random() * range() + getlinf();
			double y = f(x);
			
			values[i] = range() * y;
			retval += values[i];
		}
		
		confidenceInterval(values);
		
		return retval / k;
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
		
		double p = (double)hits / k;
		
		confidenceIntervalP(p, k);
		
		return range() * f(max()) * p;
	}
	
	@Override
	public double getcintervalPinf() {
		return range() * f(max()) * super.getcintervalPinf();
	}
	
	@Override
	public double getcintervalPsup() {
		return range() * f(max()) * super.getcintervalPsup();
	}
	
	@Override
	public void calculate(int k) {
		System.out.printf("Function: f(x)= %fx^2 + %f \n"
					+ super.toString()
					+ "area:\t %f\n"
					+ "areaRiemman:\t %f\n"
					+ "areaMV:\t %f\t Confidence interval: [%f, %f]\n"
					+ "areaP:\t %f\t Confidence interval: [%f, %f]\n",
					a,c,area(),areaRieman(k),areaMV(k),
					getcintervalinf(),getcintervalsup(),areaP(k),
					getcintervalPinf(),getcintervalPsup());
	}

}
