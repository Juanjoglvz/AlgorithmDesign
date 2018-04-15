
public abstract class Function {
	//Abstract class for calculating areas
	//Only valid with positive values
	
	private double linf, lsup; 	//Values to limit the function
	double[] cinterval;			//Confidence interval for mean value method
	double[] cintervalP;		//Confidence interval for proportions method
	
	public Function(double linf, double lsup) {
		if (linf > lsup) {
			double aux = linf;
			linf = lsup;
			lsup = aux;
		}
		
		this.linf = linf;
		this.lsup = lsup;
		
		//Initialize confidence intervals arrays
		this.cinterval = new double[2];
		this.cintervalP = new double[2];
	}
	
	public double getlinf() {
		return this.linf;
	}
	
	public double getlsup() {
		return this.lsup;
	}
	
	public void setlinf(double linf) {
		this.linf = linf;
	}
	
	public void setlsup(double lsup) {
		this.lsup = lsup;
	}
	
	//returns the range of the part of the function we handle
	public double range() { 
		return this.lsup - this.linf;
	}
	
	//returns where the function has its maximum
	public abstract double max();
	
	//returns if the x is in the range of the function we handle or not
	public boolean included(double x) {
		return x >= this.linf && x <= this.lsup;
	}
	
	//returns the value of the function for x
	public abstract double f(double x);
	
	//returns the exact area if it's possible to calculate it
	public abstract double area();
	
	//returns the area by calculating Riemman's Integral
	public double areaRieman(int intervals) {
		double retval = 0.0;
		double increment = range() / intervals;
		double x = getlinf();
		
		while(x <= getlsup()) {
			retval += f(x) * increment;
			x += increment;
		}
		
		return retval;
	}

	//returns the area with the mean value theorem
	public abstract double areaMV(int k);
	
	//returns the area with a probabilistic approach
	public abstract double areaP(int k);
	
	//from a sample, get the confidence interval for the mean stimator.
	//Assumed a big sample and unknown variance
	public void confidenceInterval(double[] values) {
		double mean = mean(values);
		double cuasiVariance = cuasiVariance(values);
		
		this.cinterval[0] = mean - (1.96 * cuasiVariance / Math.sqrt(values.length));
		this.cinterval[1] = mean + (1.96 * cuasiVariance / Math.sqrt(values.length));
	}
	
	public double getcintervalinf() {
		return this.cinterval[0];
	}
	
	public double getcintervalsup() {
		return this.cinterval[1];
	}
	
	//calculate the mean from a sample
	public double mean(double[] values) {
		double retval = 0.0;
		
		for (int i = 0; i < values.length; i++) {
			retval += values[i];
		}
		
		return retval / values.length;
	}
	
	//calculate the cuasi-variance of a sample
	public double cuasiVariance(double[] values) {
		double retval = 0.0;
		double mean = mean(values);
		
		for (int i = 0; i < values.length; i++) {
			retval += Math.pow(values[i] - mean, 2);
		}
		
		return Math.sqrt(retval / (values.length - 1));
	}
	
	//calculate the confidence interval for the mean stimator in a binomial distribution
	public void confidenceIntervalP(double p, int n) {
		this.cintervalP[0] = p - (1.96 * Math.sqrt(p * (1 - p) / n));
		this.cintervalP[1] = p + (1.96 * Math.sqrt(p * (1 - p) / n));
	}
	
	public double getcintervalPinf() {
		return this.cintervalP[0];
	}
	
	public double getcintervalPsup() {
		return this.cintervalP[1];
	}
	
	public String toString() {
		return "Range: [" + getlinf() + ", " + getlsup() + "]\n";
	}
	
	public abstract void calculate(int k);
}
