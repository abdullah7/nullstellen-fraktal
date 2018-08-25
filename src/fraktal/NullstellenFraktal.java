package fraktal;

import java.awt.*;

public class NullstellenFraktal extends FraktalProgram {

    protected Point2F center;
    private int MAX_ITER = 50;

    public NullstellenFraktal() {
        center = new Point2F(0, 0);
    }

    public int calcConvergence(ComplexNumber startValue) {

        int iteration = 1;
        ComplexNumber aktuellerWert = new ComplexNumber(startValue);
        while (iteration < MAX_ITER) {
            
	    // divident berechnen
	    ComplexNumber divident = new ComplexNumber(aktuellerWert); 
	    divident.pot(3); //z³
	    divident.sub(1);//-1
		
	    // divisor	
	    ComplexNumber divisor = new ComplexNumber(aktuellerWert); 
	    divisor.quad(); // z²
	    divisor.mul(3.);//*3
		
	    // subtrahend
	    ComplexNumber subtrahend = new ComplexNumber(divident);
	    subtrahend.div(divisor); // quotient = dividend/ divisor

            aktuellerWert.sub(subtrahend);
            iteration++;
            if (aktuellerWert.absSqr() > 4.0) {
	        break;
	    }
        }
        return iteration;
    }


    @Override
    public Color calcPixel(Point2F coordinate) {

        int iter = calcConvergence(new ComplexNumber(coordinate.x, coordinate.y));
        if (iter == MAX_ITER) {
            return Color.black;
        } else {
            double near = Math.sqrt(iter) / Math.sqrt((double) MAX_ITER);
            return ColorFunnction.farbVerlauf(Color.getHSBColor((float) (((coordinate.x / 3) + 0.5f) % 1.f), 1.f, 1.f), Color.getHSBColor((float) (coordinate.x / 3), 1.f, 1.f), near);
        }
    }
}
