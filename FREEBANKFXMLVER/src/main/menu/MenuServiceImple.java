package main.menu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.user.UserController;
import main.user.UserDTO;

public class MenuServiceImple implements MenuService {

	Parent root;
	
	@Override
	public void mainMenu(Parent root) {

		Stage mainMenuScreen = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/mainMenu.fxml"));

		try {
			
			Parent mainMenuRoot = loader.load();
			
			MenuController mainMenuCtrl = loader.getController();
			
			mainMenuCtrl.setRoot(mainMenuRoot);
			mainMenuScreen.setScene(new Scene(mainMenuRoot));
			mainMenuScreen.setTitle("메인페이지");
			mainMenuScreen.show();
			
		} catch (Exception e) {
			System.out.println("메인페이지 화면 출력 에러");
			e.printStackTrace();
		}
		
	}

	@Override
	public void loginMainMenu(Parent root, UserDTO userDTO) {
		Stage loginMainMenuScreen = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/loginMainMenu.fxml"));

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

	@Override
	public void adminMainMenu(Parent root) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
