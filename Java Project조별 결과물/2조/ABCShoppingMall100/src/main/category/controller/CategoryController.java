package main.category.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.category.service.CategoryService;
import main.pay.DTOService;

public class CategoryController {
	CategoryService cs = new CategoryService();
	DTOService ds = new DTOService();
	static List<String> basket = new ArrayList();

	static List<Integer> price = new ArrayList();

	@FXML
	private Scene previousScene; // 이전 페이지들 객체 저장해줌
	@FXML
	private Button backButton;
	Parent root; 
	@FXML
	private static String product1name;
	private static String product2name;
	private static String product3name;
	private static String product4name;
	private static String product5name;
	private static String product6name;
	private static String product7name;
	private static String product8name;
	private static String product9name;
	private static String product10name;
	private static String product11name;
	private static String product12name;

	private static String product13name;
	private static String product14name;
	private static String product15name;
	private static String product16name;
	private static String product17name;
	private static String product18name;
	private static String product19name;
	private static String product20name;
	private static String product21name;
	private static String product22name;
	private static String product23name;
	private static String product24name;


	private static int product1price;
	private static int product2price;
	private static int product3price;
	private static int product4price;
	private static int product5price;
	private static int product6price;
	private static int product7price;
	private static int product8price;
	private static int product9price;
	private static int product10price;
	private static int product11price;
	private static int product12price;
	private static int product13price;
	private static int product14price;
	private static int product15price;
	private static int product16price;
	private static int product17price;
	private static int product18price;
	private static int product19price;
	private static int product20price;
	private static int product21price;
	private static int product22price;
	private static int product23price;
	private static int product24price;



	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;

	}

	public void backButtonAction(ActionEvent event) {
		cs.backButtonAction(event);
	}

	public void loginPage(ActionEvent event) {
		cs.loginPageGo(event);

	}

	public void CategoryShirt(ActionEvent event) {  // 상의 이동
		cs.CategoryShirt(event);
	}

	public void CategoryPants(ActionEvent event) {  // 하의 이동
		cs.CategoryPants(event);
	}

	public void CategoryShoes(ActionEvent event) {  // 신발 이동
		cs.CategoryShoes(event);
	}

	public void CategoryOnepiece(ActionEvent event) {  // 원피스 이동
		cs.CategoryOnepiece(event);
	}

	public void CategoryAccessory(ActionEvent event) {  // 액세서리 이동
		cs.CategoryAccessory(event);
	}

	public void CategoryOuter(ActionEvent event) {  // 아우터 이동
		cs.CategoryOuter(event);
	}
	public void payProc1(ActionEvent event) { // 장바구니 이동과 결제완료페이지 이동
		cs.payProc(event);

	}

	public static void addProduct(String productName) {
		basket.add(productName);
	}

	public static void addprice(int productprice) {
		price.add(productprice);
	}
	// 버튼 클릭 시 상품명을 "악세사리 1"로 변경

	@FXML
	private void button1(ActionEvent event) {
		product1name = "악세사리 1"; 
		addProduct(product1name);
		product1price = 80000;
		addprice(product1price);
		cs.dkrtp1();
		System.out.println("버튼 1 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button2(ActionEvent event) {
		product2name = "악세사리 2"; 
		addProduct(product2name);
		product2price = 38000;
		addprice(product2price);
		cs.dkrtp1();
		System.out.println("버튼 2 클릭 후 장바구니 상태: " + basket);


	}
	@FXML
	private void button3(ActionEvent event) {
		product3name = "악세사리 3"; 
		addProduct(product3name);
		product3price = 7999;
		addprice(product3price);
		cs.dkrtp1();
		System.out.println("버튼 3 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button4(ActionEvent event) {
		product4name = "악세사리 4"; 
		addProduct(product4name);
		product4price = 88000;
		addprice(product4price);
		cs.dkrtp1();
		System.out.println("버튼 4 클릭 후 장바구니 상태: " + basket);

	}
	//		
	@FXML
	private void button5(ActionEvent event) {
		product5name = "원피스 1"; 
		addProduct(product5name);
		product5price = 20000;
		addprice(product5price);
		cs.dkrtp1();
		System.out.println("버튼 5 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button6(ActionEvent event) {
		product6name = "원피스 2"; 
		addProduct(product6name);
		product6price = 60000;
		addprice(product6price);
		cs.dkrtp1();
		System.out.println("버튼 6 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button7(ActionEvent event) {
		product7name = "원피스 3"; 
		addProduct(product7name);
		product7price = 80000;
		addprice(product7price);
		cs.dkrtp1();
		System.out.println("버튼 7 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button8(ActionEvent event) {
		product8name = "원피스 4"; 
		addProduct(product8name);
		product8price = 69900;
		addprice(product8price);
		cs.dkrtp1();
		System.out.println("버튼 8 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button9(ActionEvent event) {
		product9name = "아우터 1"; 
		addProduct(product9name);
		product9price = 30000;
		addprice(product9price);
		cs.dkrtp1();
		System.out.println("버튼 9 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button10(ActionEvent event) {
		product10name = "아우터 2"; 
		addProduct(product10name);
		product10price = 300000;
		addprice(product10price);
		cs.dkrtp1();
		System.out.println("버튼 10 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button11(ActionEvent event) {
		product11name = "아우터 3"; 
		addProduct(product11name);
		product11price = 100000;
		addprice(product11price);
		cs.dkrtp1();
		System.out.println("버튼 11 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button12(ActionEvent event) {
		product12name = "아우터 4"; 
		addProduct(product12name);
		product12price = 99900;
		addprice(product12price);
		cs.dkrtp1();
		System.out.println("버튼 12 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button13(ActionEvent event) {
		product13name = "바지 1"; 
		addProduct(product13name);
		product13price = 20000;
		addprice(product13price);
		cs.dkrtp1();
		System.out.println("버튼 13 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button14(ActionEvent event) {
		product14name = "바지 2"; 
		addProduct(product14name);
		product14price = 80000;
		addprice(product14price);
		cs.dkrtp1();
		System.out.println("버튼 14 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button15(ActionEvent event) {
		product15name = "바지 3"; 
		addProduct(product15name);
		product15price = 70000;
		addprice(product15price);
		cs.dkrtp1();
		System.out.println("버튼 15 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button16(ActionEvent event) {
		product16name = "바지 4"; 
		addProduct(product16name);
		product16price = 39000;
		addprice(product16price);
		cs.dkrtp1();
		System.out.println("버튼 16 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button17(ActionEvent event) {
		product17name = "상의 1"; 
		addProduct(product17name);
		product17price = 15000;
		addprice(product17price);
		cs.dkrtp1();
		System.out.println("버튼 17 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button18(ActionEvent event) {
		product18name = "상의 2"; 
		addProduct(product18name);
		product18price = 45000;
		addprice(product18price);
		cs.dkrtp1();
		System.out.println("버튼 18 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button19(ActionEvent event) {
		product19name = "상의 3"; 
		addProduct(product19name);
		product19price = 40000;
		addprice(product19price);
		cs.dkrtp1();
		System.out.println("버튼 19 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button20(ActionEvent event) {
		product20name = "상의 4"; 
		addProduct(product20name);
		product20price = 47000;
		addprice(product20price);
		cs.dkrtp1();
		System.out.println("버튼 20 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button21(ActionEvent event) {
		product21name = "신발 1"; 
		addProduct(product21name);
		product21price = 60000;
		addprice(product21price);
		cs.dkrtp1();
		System.out.println("버튼 21 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button22(ActionEvent event) {
		product22name = "신발 2"; 
		addProduct(product22name);
		product22price = 50000;
		addprice(product22price);
		cs.dkrtp1();
		System.out.println("버튼 22 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button23(ActionEvent event) {
		product23name = "신발 3"; 
		addProduct(product23name);
		product23price = 80000;
		addprice(product23price);
		cs.dkrtp1();
		System.out.println("버튼 23 클릭 후 장바구니 상태: " + basket);

	}
	@FXML
	private void button24(ActionEvent event) {
		product24name = "신발 4"; 
		addProduct(product24name);
		product24price = 49000;
		addprice(product24price);
		cs.dkrtp1();
		System.out.println("버튼 24 클릭 후 장바구니 상태: " + basket);

	}
	// 저장된 상품명 가져오는 메서드
	public static List<String> getProductName() {
		System.out.println("현재 장바구니 상태: " + basket); // 로그 출력
		return basket;

	}
	public static List<Integer> getProductPrice() {
		System.out.println("현재 장바구니 상태: " + price); // 로그 출력
		return price;

	}


}