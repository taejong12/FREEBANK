package main.shop;

import java.util.List;

import javafx.scene.Parent;
import main.user.UserDTO;

public interface ShopService {

	void shopPage(Parent root, UserDTO userDTO); // 쇼핑몰 페이지 화면 출력

	List<ShopDTO> selectShopList(); // 쇼핑몰 리스트

	void shopDetailPage(Parent root, ShopDTO shop, UserDTO userDTO); // 쇼핑몰 상세페이지 화면 출력

}
