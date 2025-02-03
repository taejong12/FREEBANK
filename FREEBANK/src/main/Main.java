package main;

import main.shop.ShopController;
import main.shop.ShopService;
import main.shop.ShopServiceImpl;

public class Main {

	public static void main(String[] args) {
		System.out.println("메인화면");
		
		ShopController sc = new ShopController();
		
		sc.shopMain();
		
	}
}
