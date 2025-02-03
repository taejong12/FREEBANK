package main.shop;

import java.util.Scanner;

public class ShopController {

	ShopService ss = new ShopServiceImpl();
	Scanner sc = new Scanner(System.in);
	
	public void shopMain() {
		
		while(true) {
			System.out.println("상품페이지");
			//관리자만 보이도록 
			//아니면 관리자 페이지를 별도로 생성
			System.out.println("99.상품등록");
			System.out.println("1.상품리스트");
			System.out.println("0.이전페이지");			
			System.out.println("선택: ");
			int menu = sc.nextInt();
			
			switch(menu){
				case 99:
					ss.shopInsert();
					break;
				case 1:
					ss.shopList();
					break;
				case 0:
					System.out.println("이전페이지");
					return;
				default:
					System.out.println("없는 목록입니다.");
					break;
			}
			
		}
		 
	 }
	
}
