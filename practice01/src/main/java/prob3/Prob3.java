package prob3;

import java.util.Scanner;

public class Prob3 {
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		/* 코드 작성 */
		int num = scanner.nextInt();
		int result = 0;
		for (int i = num % 2; i <= num; i= i + 2) {
			result += i;
		}
		
		System.out.println(result);
		
		scanner.close();
	}
}
