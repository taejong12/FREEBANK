package main.menu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.admin.AdminController;
import main.user.UserController;
import main.user.UserDTO;

public class MenuServiceImple implements MenuService {

	Parent root;

	// 비로그인 화면
	public void mainMenu(Parent root, UserDTO userDTO) {

		Stage mainMenuScreen = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/menu/mainMenu.fxml"));

		try {

			Parent mainMenuRoot = loader.load();

			MenuController mainMenuCtrl = loader.getController();

			mainMenuCtrl.setRoot(mainMenuRoot);
			mainMenuCtrl.setUser(userDTO);

			mainMenuScreen.setScene(new Scene(mainMenuRoot));
			mainMenuScreen.setTitle("메인페이지");
			mainMenuScreen.show();

			mainMenuCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("메인페이지 화면 출력 에러");
			e.printStackTrace();
		}

	}

	// 일반회원 화면
	public void loginMainMenu(Parent root, UserDTO userDTO) {
		Stage loginMainMenuScreen = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/menu/loginMainMenu.fxml"));

		try {

			Parent loginMainMenuRoot = loader.load();

			MenuController loginMainMenuCtrl = loader.getController();

			loginMainMenuCtrl.setRoot(loginMainMenuRoot);
			loginMainMenuCtrl.setUser(userDTO);

			loginMainMenuScreen.setScene(new Scene(loginMainMenuRoot));
			loginMainMenuScreen.setTitle("일반회원메인페이지");
			loginMainMenuScreen.show();

			loginMainMenuCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("일반회원 화면 출력 에러");
			e.printStackTrace();
		}

	}

	// 관리자메뉴화면
	public void adminMainMenu(Parent root, UserDTO userDTO) {
		Stage adminMainMenuScreen = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/menu/adminMainMenu.fxml"));

		try {

			Parent adminMainMenuRoot = loader.load();

			AdminController adminMainMenuCtrl = loader.getController();

			adminMainMenuCtrl.setRoot(adminMainMenuRoot);
			adminMainMenuCtrl.setUser(userDTO);

			adminMainMenuScreen.setScene(new Scene(adminMainMenuRoot));
			adminMainMenuScreen.setTitle("관리자메인페이지");
			adminMainMenuScreen.show();

			adminMainMenuCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("관리자메뉴 출력 에러");
			e.printStackTrace();
		}

	}

}
