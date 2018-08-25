package fraktal;

public class ComplexNumber {

	private double real, imag;

	public double getReal() {
		return real;
	}

	public void setReal(double real) {
		this.real = real;
	}

	public double getImag() {
		return imag;
	}

	public void setImag(double imag) {
		this.imag = imag;
	}

	public ComplexNumber(double re, double im) {
		real = re;
		imag = im;
	}

	public ComplexNumber(ComplexNumber cn) {
		real = cn.getReal();
		imag = cn.getImag();
	}

	public void add(ComplexNumber cn) {
		real += cn.real;
		imag += cn.imag;
	}  
	
	public void sub(ComplexNumber cn) {
		real -= cn.real;
		imag -= cn.imag;
	}
	public void sub(double a) {
		real = real-a; 
	}


	public void div(ComplexNumber Divisor){
		
		double tmp =(Divisor.real)*(Divisor.real)+(Divisor.imag*Divisor.imag);
		real = ((real * Divisor.real)+imag*Divisor.imag)/tmp;
		imag = (imag*Divisor.real- real* Divisor.imag)/tmp;}
	
	public void mul(ComplexNumber Faktor){
		final double tmpReal = real * Faktor.real - imag * Faktor.imag;
					imag = Faktor.real * imag + real * Faktor.imag;
					real = tmpReal;
	}

	public void mul(Double Faktor){
		real = real * Faktor;
		imag = imag * Faktor;
	}

	public void pot(double n){
		while(n> 1){
			this.mul(this);
			n--;
		}
		if (n==0){
			real=1;imag=0;
		}
	}
	
	public void quad() {
		final double tmpReal = real * real - imag * imag;
		imag = 2 * real * imag;
		real = tmpReal;
	}

	public double absSqr() {
		return real * real + imag * imag;
	}
}
