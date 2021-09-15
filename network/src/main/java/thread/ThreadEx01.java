package thread;

public class ThreadEx01 {

	public static void main(String[] args) {
//		for (int i = 0; i < 10; i++) {
//			System.out.print(i);
//		}
		new DigitThread().start();
		for (char i = 'a'; i <= 'z'; i++) {
			System.out.print(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
