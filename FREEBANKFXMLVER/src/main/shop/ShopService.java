package main.shop;

import java.util.List;

import javafx.scene.Parent;
import main.user.UserDTO;

public interface ShopService {

	void shopListPage(Parent root, UserDTO userDTO);	//쇼핑몰 화면

	List<ShopDTO> selectShopList();	//쇼핑몰 리스트

	void shopDetail(Parent root, ShopDTO shop, UserDTO userDTO);	//쇼핑몰 상세페이지

}
