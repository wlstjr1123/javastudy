package prob02;

import java.util.Scanner;

public class CalcApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while( true ) {
			System.out.print( ">> " );
			String expression = scanner.nextLine();
			
			if( "quit".equals( expression ) ) {
				break;
			}
			
			String[] tokens = expression.split( " " );
			
			if( tokens.length != 3 ) {
				System.out.println( ">> 알 수 없는 식입니다.");
				continue;
			}
			
			int lValue = Integer.parseInt( tokens[ 0 ] );
			int rValue = Integer.parseInt( tokens[ 1 ] );
			
			Arithmetic arithmetic = null;
			
			/* 코드 작성 */
			arithmetic = new Arithmetic() {
				
				@Override
				public int calculate(int a, int b) {
					int result = 0;
					switch (tokens[2]) {
						case "+":
							result = a + b;
							break;
						case "-":
							result = a - b;
							break;
						case "*":
							result = a * b;
							break;
						case "/":
							result = a / b;
							break;
							default:
							System.out.println("사용할수 없는 연산입니다.");
							return 0;
					}
					return result;
				}
			};
			int result = arithmetic.calculate(lValue, rValue);
			System.out.println( ">> " + result );
		}
		
		scanner.close();
	}
}