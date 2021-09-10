package prob6;

public class RectTriangle extends Shape {
	double width;
	double height;
	
	public RectTriangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	double getArea() {
		return width * height * 0.5;
	}

	@Override
	double getPerimeter() {
		return width + height + Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
	}

}
