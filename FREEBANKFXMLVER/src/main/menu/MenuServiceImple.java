package main.menu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.admin.AdminController;
import main.user.UserDTO;

public class MenuServiceImple implements MenuService {

	Parent root;

	// 메인페이지 출력
	public void mainMenu(Parent root, UserDTO userDTO) {

		Stage mainMenu = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/menu/mainMenu.fxml"));

		try {

			Parent mainMenuRoot = loader.load();

			MenuController mainMenuCtrl = loader.getController();

			mainMenuCtrl.setRoot(mainMenuRoot);
			mainMenuCtrl.setUser(userDTO);

			mainMenu.setScene(new Scene(mainMenuRoot));
			mainMenu.setTitle("메인페이지");
			mainMenu.show();

			mainMenuCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("메인페이지 출력 에러");
			e.printStackTrace();
		}

	}

	// 일반회원 메인페이지 출력
	public void loginMainMenu(Parent root, UserDTO userDTO) {
		Stage loginMainMenu = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/menu/loginMainMenu.fxml"));

		try {

			Parent loginMainMenuRoot = loader.load();

			MenuController loginMainMenuCtrl = loader.getController();

			loginMainMenuCtrl.setRoot(loginMainMenuRoot);
			loginMainMenuCtrl.setUser(userDTO);
			loginMainMenuCtrl.loginUserId();
			loginMainMenuCtrl.selectBoardList();
			loginMainMenuCtrl.selectShopList();			

			loginMainMenu.setScene(new Scene(loginMainMenuRoot));
			loginMainMenu.setTitle("일반회원 메인페이지");
			loginMainMenu.show();

			loginMainMenuCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("일반회원 메인페이지 출력 에러");
			e.printStackTrace();
		}

	}

	// 관리자 메인페이지 출력
	public void adminMainMenu(Parent root, UserDTO userDTO) {
		Stage adminMainMenu = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/menu/adminMainMenu.fxml"));

		try {

			Parent adminMainMenuRoot = loader.load();

			AdminController adminMainMenuCtrl = loader.getController();

			adminMainMenuCtrl.setRoot(adminMainMenuRoot);
			adminMainMenuCtrl.setUser(userDTO);
			adminMainMenuCtrl.selectBoardList();
			adminMainMenuCtrl.selectShopList();
			
			adminMainMenu.setScene(new Scene(adminMainMenuRoot));
			adminMainMenu.setTitle("관리자 메인페이지");
			adminMainMenu.show();

			adminMainMenuCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("관리자 메인페이지 출력 에러");
			e.printStackTrace();
		}

	}

}
