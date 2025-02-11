package main.shop;

import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.user.UserDTO;

public class ShopServiceImpl implements ShopService {

	ShopDAO sd = new ShopDAO();

	// 쇼핑몰 페이지 출력
	public void shopPage(Parent root, UserDTO userDTO) {

		Stage shopPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/shop/shopPage.fxml"));

		try {

			Parent shopPageRoot = loader.load();

			ShopController shopPageCtrl = loader.getController();

			shopPageCtrl.setRoot(shopPageRoot);
			shopPageCtrl.setUser(userDTO);
			// 쇼핑몰 리스트 출력
			shopPageCtrl.selectShopList();

			shopPage.setScene(new Scene(shopPageRoot));
			shopPage.setTitle("쇼핑몰 페이지");
			shopPage.show();

			shopPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("쇼핑몰 페이지 출력 실패");
			e.printStackTrace();
		}

	}

	// 쇼핑몰 리스트
	public List<ShopDTO> selectShopList() {
		return sd.selectShopList();
	}

	// 쇼핑몰 상세페이지 출력
	public void shopDetailPage(Parent root, ShopDTO shop, UserDTO userDTO) {
		System.out.println("상품 상세 페이지 이동: " + shop.getShopName());

		Stage shopDetailPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/shop/shopDetailPage.fxml"));

		try {

			Parent shopDetailPageRoot = loader.load();

			ShopController shopDetailPageCtrl = loader.getController();

			shopDetailPageCtrl.setRoot(shopDetailPageRoot);
			shopDetailPageCtrl.setUser(userDTO);
			shopDetailPageCtrl.setShop(shop);

			shopDetailPage.setScene(new Scene(shopDetailPageRoot));
			shopDetailPage.setTitle("쇼핑몰상세페이지");
			shopDetailPage.show();

			shopDetailPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("쇼핑몰 상세페이지 출력 실패");
			e.printStackTrace();
		}

	}

}
