package chapter04;

public class StringTest03 {

	public static void main(String[] args) {
//		String s1 = "Hello " + "World " + "Java " + 1.8;
		String s1 = new StringBuffer("Hello ")
			.append("World ")
			.append("Java ")
			.append(1.8)
			.toString();
		
		System.out.println(s1);
		
		String s2 = "";
		for (int i = 0; i < 1000000000; i++) {
//			s2 = s2 + i;
//			s2 = new StringBuffer(s2).append(i).toString();
		}
		
		StringBuffer sb2 = new StringBuffer("");
		for (int i = 0; i < 1000000; i++) {
			sb2.append(i);
		}
		String s3 = sb2.toString();
		System.out.print(s3.length());

	}

}
