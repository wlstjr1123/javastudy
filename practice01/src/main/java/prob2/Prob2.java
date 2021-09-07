package prob2;

public class Prob2 {
	public static void main(String[] args) {
		/* 코드 작성 */
		for (int i = 1; i <= 10; i++ ) {
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < 10; j++) {			
				if (j == 10) {
					sb.append(i+j);
				} else {
					sb.append(i+j).append(" ");
				}
			}
			System.out.println(sb.toString());
		}
	}
}
