package prob02;

import java.util.Scanner;

public class GoodsApp {
	private static final int COUNT_GOODS = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Goods[] goods = new Goods[COUNT_GOODS];

		// 상품 입력
		for (int i = 0; i < goods.length; i++) {
			String str = scanner.nextLine();
			String[] strSplite = str.split(" ");
			goods[i] = new Goods(strSplite[0], strSplite[1], strSplite[2]);
		}
		// 상품 출
		for (int i = 0; i < goods.length; i++) {
			System.out.println(goods[i].toString());
		}
		
		// 자원정리
		scanner.close();
	}
}
