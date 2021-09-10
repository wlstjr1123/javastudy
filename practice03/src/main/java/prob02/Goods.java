package prob02;

public class Goods {
	String name;
	String price;
	String count;
	
	public Goods(String name, String price, String count) {
		this.name = name;
		this.price = price;
		this.count = count;
	}

	@Override
	public String toString() {
		return name + "(가격:" + price + "원)이 " + count + "개 입고 되었습니다.";
	}
	
	
}
