package prob04;
public class Prob04 {

	public static void main(String[] args) {
		char[] c1 = reverse( "Hello World" );
		printCharArray( c1 );
		
		char[] c2 = reverse( "Java Programming!" );
		printCharArray( c2 );
	}
	
	public static char[] reverse(String str) {
		/* 코드를 완성합니다 */
		char[] c = new char[str.length()];
		
		for (int i = 0; i < str.length(); i++) {
			c[i] = str.charAt(str.length() - 1 - i); 
		}
		return c;
	}

	public static void printCharArray(char[] array){
		/* 코드를 완성합니다 */
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
		}
		
		System.out.println( sb.toString() );
	}
}