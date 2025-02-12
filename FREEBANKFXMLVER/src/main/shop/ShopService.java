package main.shop;

import java.util.List;

import javafx.scene.Parent;
import main.user.UserDTO;

public interface ShopService {

	void shopListPage(Parent root, UserDTO userDTO); // 상품목록 페이지 화면 출력(비로그인)

	List<ShopDTO> selectShopList(); // 상품 리스트

	void shopDetailPage(Parent root, ShopDTO shop, UserDTO userDTO); // 상품 상세페이지 화면 출력(비로그인)

	void userShopPayPage(Parent root, UserDTO userDTO, ShopDTO shopDTO); // 상품 결제페이지 출력

	boolean userShopPayment(Parent root, ShopDTO shopDTO, UserDTO userDTO); // 상품 결제하기

	void shopLoginListPage(Parent root, UserDTO userDTO); // 상품목록 페이지 화면 출력(회원로그인)

	void userShopPayLoginPage(Parent root, UserDTO userDTO); // 비로그인 상태 결제버튼 클릭(로그인창)

	void shopLoginDetailPage(Parent root, ShopDTO shop, UserDTO userDTO); // 상품 상세페이지 화면 출력(회원로그인)

}
