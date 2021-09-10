package prob6;

public class Rectangle extends Shape implements Resizable {
	double width;
	double height;
	
	public Rectangle(double w, double h ) {
		width = w;
		height = h;
	}
	
	
	@Override
	double getArea() {
		return width * height;
	}

	@Override
	double getPerimeter() {
		return (width + height) * 2;
	}


	@Override
	public void resize(double s) {
		width = width * s;
		height = height * s;
		
	}

}
