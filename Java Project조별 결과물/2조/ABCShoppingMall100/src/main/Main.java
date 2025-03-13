package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.abcshoppingmall.controller.AbcShoppingMallController;
import main.pay.Controller;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/startMain.fxml"));

		Parent root = loader.load();

		AbcShoppingMallController ac = loader.getController();		
		//Controller c = loader.getController();
		ac.setRoot(root);

		stage.setScene(new Scene(root)); 
		stage.setTitle("환영합니다");
		stage.show();

	}
	public static void main(String[] args) {
		launch(args);
	}

}

