package prob4;

import java.util.Scanner;

public class Prob4 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("문자열을 입력하세요 : ");
		String text = scanner.nextLine();

		for(int i = 1; i <= text.length(); i++) {
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < i; j++) {
				sb.append(text.charAt(j));
			}
			System.out.println(sb.toString());
		}
		
		scanner.close();
	}

}
