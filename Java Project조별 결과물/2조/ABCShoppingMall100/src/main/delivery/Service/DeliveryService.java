
package main.delivery.Service;


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.pay.Dao;
import main.pay.Dto;

public class DeliveryService {
	List<Integer> ex = new ArrayList<Integer>();
	Dto d = new Dto();
	Dao dao = new Dao();
	public void deilveryNumFind(Parent root) {
		TextField delinum = (TextField)root.lookup("#txtdeliveryNum");
		
		if(dao.deliveryCheck(Integer.parseInt(delinum.getText()))){
		

			System.out.println("조회 성공");
			deliveryPage(root);
		}else {
			System.out.println("조회 실패");

		}



	}

	public void deliveryPage(Parent root) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/delivery.fxml"));
			Parent newRoot = loader.load();

			Stage stage = (Stage) root.getScene().getWindow();
			stage.setScene(new Scene(newRoot));
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void backButtonAction(ActionEvent event) {
		// TODO Auto-generated method stub
		FXMLLoader loder = new FXMLLoader(getClass().getResource("/main/fxml/category.fxml")); //나중에 링크 달기
		Parent root;
		try {
			root = loder.load();
			Scene beck = new Scene(root);
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); // 현재 Scene의 Stage 객체 가져오기
			stage.setScene(beck);
			stage.setTitle("카테고리");
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
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

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}
