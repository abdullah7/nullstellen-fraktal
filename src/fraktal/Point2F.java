package fraktal;

import java.io.Serializable;

public class Point2F implements Serializable{
	private static final long serialVersionUID = 52958166169832913L;
	
	public double x;
	public double y;
	
	public Point2F(double x, double y) {
		this.x = x;
		this.y = y;					
	}
	
	public Point2F(Point2F point) {
		if (point != null) {
			x = point.x;
			y = point.y;
		}
	}
	
	public Point2F add(Point2F point) {
		return new Point2F(x + point.x, y + point.y);
	}
}
