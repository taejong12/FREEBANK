package main.shop;

import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.user.UserDTO;

public class ShopServiceImpl implements ShopService {

	ShopDAO sd = new ShopDAO();

	// 쇼핑몰 화면
	public void shopListPage(Parent root, UserDTO userDTO) {

		Stage shopListPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/shop/shopListPage.fxml"));

		try {

			Parent shopListPageRoot = loader.load();

			ShopController shopListPageCtrl = loader.getController();

			shopListPageCtrl.setRoot(shopListPageRoot);
			shopListPageCtrl.setUser(userDTO);
			// 쇼핑몰 리스트 출력
			shopListPageCtrl.selectShopList();

			shopListPage.setScene(new Scene(shopListPageRoot));
			shopListPage.setTitle("쇼핑몰목록페이지");
			shopListPage.show();

			shopListPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("쇼핑몰목록페이지 출력 실패");
			e.printStackTrace();
		}

	}

	// 쇼핑몰 리스트
	public List<ShopDTO> selectShopList() {
		return sd.selectShopList();
	}

	// 상품 상세 화면 이동
	public void shopDetail(Parent root, ShopDTO shop, UserDTO userDTO) {
		System.out.println("상품 상세 페이지 이동: " + shop.getShopName());

		Stage shopDetail = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/shop/shopDetail.fxml"));

		try {

			Parent shopDetailRoot = loader.load();

			ShopController shopDetailCtrl = loader.getController();

			shopDetailCtrl.setRoot(shopDetailRoot);
			shopDetailCtrl.setUser(userDTO);
			shopDetailCtrl.setShop(shop);
			
			shopDetail.setScene(new Scene(shopDetailRoot));
			shopDetail.setTitle("쇼핑몰상세페이지");
			shopDetail.show();

			shopDetailCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("쇼핑몰 상세페이지 출력 실패");
			e.printStackTrace();
		}

	}


}
