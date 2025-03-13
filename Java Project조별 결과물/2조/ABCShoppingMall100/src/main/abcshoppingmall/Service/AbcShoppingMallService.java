package main.abcshoppingmall.Service;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.abcshoppingmall.controller.AbcShoppingMallController;
import main.category.controller.CategoryController;

public class AbcShoppingMallService {

	public void startProc(ActionEvent event) {
		// TODO Auto-generated method stub
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/category.fxml"));
		Parent go = null;
		try {
			go = loader.load();
			Stage categoryGo = (Stage)((Button)event.getSource()).getScene().getWindow();
			categoryGo.setScene(new Scene(go));

			categoryGo.setTitle("카테고리 페이지");
			categoryGo.show();

			categoryGo.setTitle("카테고리 페이지");
			categoryGo.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


}
