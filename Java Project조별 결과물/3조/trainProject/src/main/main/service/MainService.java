package main.main.service;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import main.login.controller.LoginController;
import main.member.controller.MemberController;



public class MainService {
	
	
	
	public MainService() {
		// TODO Auto-generated constructor stub
	}


	public void LoginCheck(Parent root) {
		// TODO Auto-generated method stub
		Stage loginForm =(Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/login.fxml"));

		Parent login = null;
		try {
			login = loader.load();
			loginForm.setScene(new Scene(login));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		LoginController ctrl = loader.getController();
		ctrl.setRoot(login);
		
		loginForm.setTitle("로그인페이지");
		loginForm.show();
	}

	public void SignUpProc(Parent root) {
		// TODO Auto-generated method stub
		Stage memberForm =(Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/member.fxml"));

		Parent member = null;
		try {
			member = loader.load();
			memberForm.setScene(new Scene(member));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		MemberController mctr = loader.getController();
		mctr.setRoot(member);
		
		ComboBox<String> cmbEmail = (ComboBox<String>)member.lookup("#cmbEmail");
		String emailString[] ={"naver.com","google.com","hanmail.com","daum.net","yahoo.com","nate.com","icloud.com"};
		cmbEmail.getItems().addAll(FXCollections.observableArrayList(emailString));

		memberForm.show();
	}

	public void LoginProc(Parent root) {

		// TODO Auto-generated method stub
		Stage loginForm =(Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/login.fxml"));

		Parent login = null;
		try {
			login = loader.load();
			loginForm.setScene(new Scene(login));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		LoginController ctrl = loader.getController();
		ctrl.setRoot(login);

		loginForm.show();

	}
}
