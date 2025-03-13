package main.login.service;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.common.service.CommonService;
import main.common.service.UserSession;
import main.login.controller.LoginController;
import main.login.dao.LoginDAO;
import main.main.controller.MainController;
import main.main_logon.controller.main_logonController;
import main.member.controller.MemberController;

public class LoginService {
	LoginDAO dao;
	CommonService cs;

	public LoginService() {
		// TODO Auto-generated constructor stub
		dao = new LoginDAO();
		cs = new CommonService();
	}

	public void LoginProc(Parent root) {
		// TODO Auto-generated method stub
		TextField id = (TextField)root.lookup("#txtId");
		PasswordField pwd = (PasswordField)root.lookup("#txtPw");

		if(dao.loginChk(id.getText(), pwd.getText())) {
			UserSession.getInstance().setUserName(dao.getUserName(id.getText(), pwd.getText()));
			UserSession.getInstance().setUserId(dao.getUserId(id.getText(), pwd.getText()));
			UserSession.getInstance().setUserAddress(dao.getUserAddress(id.getText(), pwd.getText()));
			UserSession.getInstance().setUserPhone(dao.getUserPhone(id.getText(), pwd.getText()));
			System.out.println(UserSession.getInstance().getUserName());  // 확인작업
			System.out.println(UserSession.getInstance().getUserId());
			System.out.println(UserSession.getInstance().getUserAddress());
			System.out.println(UserSession.getInstance().getUserPhone());
			cs.msgBox("로그인 성공", "👍로그인 성공!!!", "메인페이지로 넘어갑니다.");
			System.out.println("로그인 성공");

			Stage main_logon =(Stage)root.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/main_logon.fxml"));
			

			Parent logon = null;
			try {
				logon = loader.load();
				main_logon.setScene(new Scene(logon));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			main_logonController ctrl = loader.getController();
			ctrl.setRoot(logon);
			
			main_logon.setTitle("메인페이지(로그인 ○)");
			main_logon.show();
		} else {
			cs.msgBox("로그인 실패", "😐로그인 실패!!!", "다시 로그인하세요.");
			System.out.println("로그인 실패");

			id.clear();
			pwd.clear();
			id.requestFocus();
		}


		// 여기에 다음 페이지 나올 부분 코드 적용!!! ( main.fxml을 이용해서 버튼 추가 및 기능 추가)

	}

	public void CancelProc(Parent root) {
		// TODO Auto-generated method stub
		Stage mainForm =(Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/main.fxml"));

		Parent main = null;
		try {
			main = loader.load();
			mainForm.setScene(new Scene(main));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		MainController ctrl = loader.getController();
		ctrl.setRoot(main);

		mainForm.show();
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

		MemberController ctrl = loader.getController();
		ctrl.setRoot(member);

		ComboBox<String> cmbEmail = (ComboBox<String>)member.lookup("#cmbEmail");
		String emailString[] ={"naver.com","google.com","hanmail.com","daum.net","yahoo.com","nate.com","icloud.com"};
		cmbEmail.getItems().addAll(FXCollections.observableArrayList(emailString));

		memberForm.setTitle("회원가입창");
		memberForm.show();
	}

}
