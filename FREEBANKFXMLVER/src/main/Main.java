package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.menu.MenuController;


public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {


		FXMLLoader loader = new FXMLLoader(
			getClass().getResource("/main/fxml/menu/mainMenu.fxml"));
		
		Parent mainRoot = loader.load();
		
		MenuController mainMenuCtrl = loader.getController();
		mainMenuCtrl.setRoot(mainRoot);
		mainMenuCtrl.selectBoardList();
		mainMenuCtrl.selectShopList();		
		
		stage.setScene(new Scene(mainRoot));
		stage.setTitle("메인페이지");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
