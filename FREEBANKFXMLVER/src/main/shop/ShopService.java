package main.shop;

import java.util.List;

import javafx.scene.Parent;
import main.user.UserDTO;

public interface ShopService {

	void shopListPage(Parent root, UserDTO userDTO); // 상품목록 페이지 화면 출력

	List<ShopDTO> selectShopList(); // 상품 리스트

	void shopDetailPage(Parent root, ShopDTO shop, UserDTO userDTO); // 상품 상세페이지 화면 출력

	void userShopPayPage(Parent root, UserDTO userDTO, ShopDTO shopDTO); // 상품 결제페이지 출력

	boolean userShopPayment(Parent root, ShopDTO shopDTO, UserDTO userDTO); // 상품 결제하기

}
