package main.member.service;



import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.common.service.CommonService;
import main.login.controller.Controller;
import main.member.controller.MemberController;
import main.member.dao.MemberDAO;
import main.member.dto.Member;

public class MemberService {
	CommonService cs = new CommonService();
	MemberDAO dao = new MemberDAO();
	

	public void joinMember(ActionEvent event) {
		// TODO Auto-generated method stub
		//Parent memberForm = (Parent) event.getSource();
		Parent memberForm = ((Node) event.getSource()).getScene().getRoot();
		Member m = new Member();
		
		TextField id = (TextField) 
				memberForm.lookup("#txtId");
		TextField name = (TextField)
				memberForm.lookup("#txtName");
		PasswordField pwd = (PasswordField)
				memberForm.lookup("#txtPw");
		PasswordField pwdOk = (PasswordField)
				memberForm.lookup("#txtPwOk");
		TextField address = (TextField) memberForm.lookup("#address");
		TextField email = (TextField) memberForm.lookup("#email");
		TextField phoneNum = (TextField) memberForm.lookup("#phoneNum");
		TextField friendId = (TextField) memberForm.lookup("#friendId");
		
		if(id.getText().isEmpty()) {
			cs.msgBox("입력오류", "아이디 입력 오류", 
			"아이디가 입력되지 않았습니다. 다시 입력하세요");
			id.requestFocus();
			return;
		} else if(name.getText().isEmpty()) {
			cs.msgBox("입력오류", "이름 입력 오류", 
			"이름이 입력되지 않았습니다. 다시 입력하세요");
			name.requestFocus();
			return;
		} else if(pwd.getText().isEmpty()) {
			cs.msgBox("입력오류", "암호 입력 오류", 
			"암호가 입력되지 않았습니다. 다시 입력하세요");
			pwd.requestFocus();
			return;
		} else if(pwdOk.getText().isEmpty()) {
			cs.msgBox("입력오류", "암호확인 입력 오류", 
			"암호확인이 입력되지 않았습니다. 다시 입력하세요");
			pwdOk.requestFocus();
			return;
		} else if(address.getText().isEmpty()) {
			cs.msgBox("입력오류", "주소 입력 오류", 
			"주소가 입력되지 않았습니다. 다시 입력하세요");
			address.requestFocus();
			return;
		}
		
		if(pwd.getText().equals(pwdOk.getText())) {
			cs.msgBox("암호", "암호일치여부", "암호가 일치합니다.");
		} else {
			cs.msgBox("암호", "암호일치여부", 
			"암호가 일치하지 않습니다. 다시 입력하세요.");
			pwd.clear();
			pwdOk.clear();
			pwd.requestFocus();
			return;
		}
		
		m.setId(id.getText());
		m.setPwd(pwd.getText());
		m.setPwdOk(pwdOk.getText());
		m.setName(name.getText());
		m.setAddress(address.getText());
		m.setEmail(email.getText());
		m.setPhoneNum(phoneNum.getText());
		m.setFriendId(friendId.getText());
		
		
		if(dao.insertMember(m)) {
			Stage s = (Stage) 
				memberForm.getScene().getWindow();
			FXMLLoader loader =
				new FXMLLoader(
				getClass().
				getResource("/main/fxml/login.fxml"));
			
			
			try {
				memberForm = loader.load();
				s.setScene(new Scene(memberForm));
				
				Controller logincon = loader.getController();
				logincon.setRoot(memberForm);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			s.setTitle("로그인");
			s.show();
		} else {
			id.clear();
			pwd.clear();
			pwdOk.clear();
			name.clear();
			address.clear();
			email.clear();
			phoneNum.clear();
			friendId.clear();
			
		}
	}

	public void backButtonAction(ActionEvent event) {
		// TODO Auto-generated method stub
		FXMLLoader loder = new FXMLLoader(getClass().getResource("/main/fxml/category.fxml"));
		Parent category;
		try {
			category = loder.load();
			Scene beck = new Scene(category);
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); // 현재 Scene의 Stage 객체 가져오기
			stage.setScene(beck);
			stage.setTitle("카테고리");
			stage.show();
			
			MemberController abcc = loder.getController(); // 컨트롤러 가져오기 (FXML 로드 후)
	        abcc.setRoot(category); // 필요한 경우 root 설정

		} catch (IOException e) {

			e.printStackTrace();
		}
		  

	}

	public void paybasket(ActionEvent event) {
		// TODO Auto-generated method stub
		 try {
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/pay.fxml"));
		        Parent startMain = loader.load();

		        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); // Stage 가져오기

		        stage.setScene(new Scene(startMain));
		        stage.setTitle("장바구니/결제");
		        stage.show();

		       MemberController abcc = loader.getController(); // 컨트롤러 가져오기 (FXML 로드 후)
		        abcc.setRoot(startMain); // 필요한 경우 root 설정

		    } catch (IOException e) { // IOException 처리
		        e.printStackTrace(); // 또는 적절한 로깅 방식 사용
		    }
	}

	public void loginPageGo(ActionEvent event) {
		// TODO Auto-generated method stub
		Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		FXMLLoader loginStage = new FXMLLoader(getClass().getResource("/main/fxml/login.fxml"));
		Parent root;
		try {
			root = loginStage.load();
			stage.setScene(new Scene(root));
			
			Controller abcc = loginStage.getController(); // 컨트롤러 가져오기 (FXML 로드 후)
	        abcc.setRoot(root); 
	        
			stage.setTitle("로그인/회원가입 페이지");
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
