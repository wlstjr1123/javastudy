package prob5;

public class Prob5 {

	public static void main(String[] args) {
		
		for(int i = 0; i <= 99; i++) {
			String num = String.valueOf(i);
			StringBuffer clap = new StringBuffer();
			for (int j = 0; j < num.length(); j++) {
				if (num.charAt(j) == '3' || num.charAt(j) == '6' || num.charAt(j) == '9') {
					clap.append("짝");
				}
			}
			
			if(clap.length() > 0) {
				System.out.println(num + " " + clap.toString());
			}
			
		}
	}
}
