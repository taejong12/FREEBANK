package main.login.service;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.category.controller.CategoryController;
import main.common.service.CommonService;
import main.delivery.controller.DeliveryController;
import main.login.controller.Controller;
import main.login.dao.LoginDAO;
import main.member.controller.MemberController;
import main.member.dto.Member;


public class LoginService {
	LoginDAO dao;

	private Member selectedMember;  // infolist에서 선택된 값 저장

	private ListView<Member> infoListView;

	public LoginService() {
		// TODO Auto-generated constructor stub
		dao = new LoginDAO();
	}

	public void loginProc(Parent root) {  // 로그인버튼
		// TODO Auto-generated method stub

		TextField id = 
				(TextField)root.lookup("#txtId");
		PasswordField pwd = 
				(PasswordField)root.lookup("#txtPw");
		//System.out.println("버튼 왔어요. 여기서 오류나면 root=null");
		if(dao.loginChk(id.getText(), pwd.getText())) {
			//memberInfo(root);  기능을 추가 한다면 로그인 버튼을 눌러야 무언가 할수 있게 지금은 출력만 됨
			System.out.println("로그인 성공");
			Stage s = (Stage) root.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/category.fxml"));
			Parent login = null;
			try {
				login = loader.load();
				s.setScene(new Scene(login));
				
				s.setTitle("카테고리");
				
				CategoryController cc = loader.getController();
				cc.setRoot(login);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			System.out.println("로그인 실패");
		}
	}


	public void infoProc(ActionEvent event) {  // 문의하기 페이지이동
		Scene infoForm = (Scene) ((Button) event.getSource()).getScene();  // 현재 화면을 가져와

		TextField id = (TextField)infoForm.lookup("#txtId");    
		PasswordField pwd = (PasswordField)infoForm.lookup("#txtPw"); // TEXTFIELD에 입력한 값을 ID, PWD에 저장(?)

		if(dao.loginChk(id.getText(), pwd.getText())) {

			FXMLLoader memberStage = new FXMLLoader(getClass().getResource("/main/fxml/info.fxml"));
			Parent root = null;
			try {
				root = memberStage.load();
				Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();		

				Controller c = memberStage.getController();
				c.setRoot(root);

				stage.setScene(new Scene(root));
				stage.setTitle("문의하기 관리자페이지");
				stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}

			Member member = dao.selectInfo(id.getText());
			((TextField) root.lookup("#idView")).setText(member.getId());
			((TextField) root.lookup("#nameView")).setText(member.getName());
			((TextField) root.lookup("#pwdView")).setText(member.getPwd());
			((TextField) root.lookup("#adressView")).setText(member.getAddress());
			((TextField) root.lookup("#emailView")).setText(member.getEmail());
			((TextField) root.lookup("#phoneView")).setText(member.getPhoneNum());

		}
	}



	public void logout(Parent root) {
		// TODO Auto-generated method stub
		Stage s = (Stage) root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/login.fxml"));
		Parent logout = null;
		try {
			logout = loader.load();
			s.setScene(new Scene(logout));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		Controller ctrl = loader.getController();
		ctrl.setRoot(logout);

		CommonService cs = new CommonService();
		cs.msgBox("로그아웃", "로그아웃성공", "로그인 창이 열립니다.");
		s.setTitle("로그인");
		s.show();
	}

	public void faqProc(Parent root) {
	
		Stage questGo = (Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/question.fxml"));
		Parent quest = null;

		try {
			quest = loader.load();
			questGo.setScene(new Scene(quest));
		} catch (Exception e) {

			e.printStackTrace();
		}

		Controller qctrl = loader.getController();
		qctrl.setRoot(quest);
		questGo.setTitle("문의하기");
		questGo.show();
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

			CategoryController abcc = loder.getController(); // 컨트롤러 가져오기 (FXML 로드 후)
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

			main.pay.Controller abcc = loader.getController(); // 컨트롤러 가져오기 (FXML 로드 후)
			abcc.setRoot(startMain); // 필요한 경우 root 설정

		} catch (IOException e) { // IOException 처리
			e.printStackTrace(); // 또는 적절한 로깅 방식 사용
		}
	}

	public void membershipProc(Parent root) {
		// TODO Auto-generated method stub
		Stage memberForm =(Stage)root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/joinMember.fxml"));

		Parent member;
		try {
			member = loader.load();
			memberForm.setScene(new Scene(member));
			MemberController ctrl = loader.getController();
			ctrl.setRoot(member);

			memberForm.setTitle("회원가입창");
			memberForm.show();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	


	}

	public void loginPageGo(ActionEvent event) {
		// TODO Auto-generated method stub
		FXMLLoader loginStage = new FXMLLoader(getClass().getResource("/main/fxml/login.fxml"));
		Parent root;
		try {
			root = loginStage.load();
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();		

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

	public void memberOut(ActionEvent event) {

		Scene infoForm = (Scene) ((Button) event.getSource()).getScene();
		TextField id = (TextField)infoForm.lookup("#idView");

		dao.deleteMember(id.getText());

		FXMLLoader memberStage = new FXMLLoader(getClass().getResource("/main/fxml/login.fxml"));
		Parent root = null;
		try {
			root = memberStage.load();
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();		

			stage.setScene(new Scene(root));
			stage.setTitle("로그인 페이지");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}




	}

	public void memberModify(ActionEvent event) {
		// TODO Auto-generated method stubs
		Scene s= (Scene) ((Button) event.getSource()).getScene(); 

		try {
			TextField id = (TextField)s.lookup("#idView");
			TextField name = (TextField)s.lookup("#nameView");
			TextField email = (TextField)s.lookup("#emailView");
			TextField address = (TextField)s.lookup("#adressView");
			TextField phone = (TextField)s.lookup("#phoneView");
			if(id ==null) {
				System.out.println("널");
			}
			Member member = new Member();
			member.setId(id.getText());
			member.setName(name.getText());
			member.setEmail(email.getText());
			member.setAddress(address.getText());
			member.setPhoneNum(phone.getText());

			dao.memberModify(member);

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("널입");
		}


	}
	public void delivery(ActionEvent event) {
		// TODO Auto-generated method stub
		FXMLLoader loder = new FXMLLoader(getClass().getResource("/main/fxml/deliveryNum.fxml"));
		Parent root;
		try {
			root = loder.load();
			Scene beck = new Scene(root);
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); // 현재 Scene의 Stage 객체 가져오기
			DeliveryController dc = loder.getController();
			dc.setRoot(root);
					
			stage.setScene(beck);
			stage.setTitle("배송 조회");
			stage.show();

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	
		
	}


}
