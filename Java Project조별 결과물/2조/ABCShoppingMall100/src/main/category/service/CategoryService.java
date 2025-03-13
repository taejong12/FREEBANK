package main.category.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.category.controller.CategoryController;
import main.pay.Controller;

public class CategoryService {


	
	public void backButtonAction(ActionEvent event) {
		// TODO Auto-generated method stub
		FXMLLoader loder = new FXMLLoader(getClass().getResource("/main/fxml/category.fxml"));
		Parent root;
		try {
			root = loder.load();
			Scene beck = new Scene(root);
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); // 현재 Scene의 Stage 객체 가져오기
			stage.setScene(beck);
			stage.setTitle("카테고리");
			stage.show();

		} catch (IOException e) {

			e.printStackTrace();
		}


	}




	public void loginPageGo(ActionEvent event) {
		// TODO Auto-generated method stub
		System.out.println("버튼 작동");
		FXMLLoader loginStage = new FXMLLoader(getClass().getResource("/main/fxml/login.fxml"));
		Parent root;
		try {
			root = loginStage.load();
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();		

			stage.setScene(new Scene(root));
			stage.setTitle("로그인/회원가입 페이지");
			stage.show();

			main.login.controller.Controller cc = loginStage.getController();
			cc.setRoot(root);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void CategoryShirt(ActionEvent event) {  // 상의 이동
		FXMLLoader loginStage = new FXMLLoader(getClass().getResource("/main/fxml/category_shirt.fxml"));
		Parent root;
		try {
			root = loginStage.load();
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();		

			stage.setScene(new Scene(root));
			stage.setTitle("상의 페이지");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void CategoryPants(ActionEvent event) {  // 하의 이동
		FXMLLoader loginStage = new FXMLLoader(getClass().getResource("/main/fxml/category_pants.fxml"));
		Parent root;
		try {
			root = loginStage.load();
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();		

			stage.setScene(new Scene(root));
			stage.setTitle("하의 페이지");
			stage.show();

			CategoryController cc = loginStage.getController();
			cc.setRoot(root);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void CategoryShoes(ActionEvent event) {  // 신발 이동
		FXMLLoader loginStage = new FXMLLoader(getClass().getResource("/main/fxml/category_shoes.fxml"));
		Parent root;
		try {
			root = loginStage.load();
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();		

			stage.setScene(new Scene(root));
			stage.setTitle("신발 페이지");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void CategoryOnepiece(ActionEvent event) {  // 원피스 이동
		FXMLLoader loginStage = new FXMLLoader(getClass().getResource("/main/fxml/category_onepiece.fxml"));
		Parent root;
		try {
			root = loginStage.load();
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();		

			stage.setScene(new Scene(root));
			stage.setTitle("원피스 페이지");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void CategoryAccessory(ActionEvent event) {  // 액세서리 이동
		FXMLLoader loginStage = new FXMLLoader(getClass().getResource("/main/fxml/category_accessory.fxml"));
		Parent root;
		try {
			root = loginStage.load();
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();		

			stage.setScene(new Scene(root));
			stage.setTitle("액세서리 페이지");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void CategoryOuter(ActionEvent event) {  // 아우터 이동
		FXMLLoader loginStage = new FXMLLoader(getClass().getResource("/main/fxml/category_outer.fxml"));
		Parent root;
		try {
			root = loginStage.load();
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();		

			stage.setScene(new Scene(root));
			stage.setTitle("아우터 페이지");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void payProc(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/pay.fxml"));
		Parent root = null;
		try {
			root = loader.load();
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();	
			Controller controller = loader.getController();
			stage.setScene(new Scene(root));
			stage.setTitle("결제 페이지");

			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
		ComboBox<String> cmbo =
				(ComboBox<String>)root.lookup("#cmbo");

		String couponString[] = {"사용안함","20%", "30%"};
		cmbo.getItems().addAll
		(FXCollections.observableArrayList(couponString));




	}

	
	public void dkrtp1() {
		try {
			// 장바구니에 담겼다고 알림
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("장바구니");
			alert.setHeaderText("상품이 장바구니에 담겼습니다.");
			alert.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
}


