package main.member.service;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.member.controller.MemberController;
import main.member.dao.MemberDAO;
import main.member.dto.Member;
import main.common.service.CommonService;
import main.login.controller.LoginController;

public class MemberService {
	CommonService cs = new CommonService();
	MemberDAO dao = new MemberDAO();


	public void SignUpProc(Parent memberForm) {
		// TODO Auto-geneated method stub
		Member m = new Member();

		TextField id = (TextField)memberForm.lookup("#txtId");
		TextField name = (TextField)memberForm.lookup("#txtName");
		PasswordField pwd = (PasswordField)memberForm.lookup("#txtPw");
		PasswordField pwdOk = (PasswordField)memberForm.lookup("#txtPwOk");
		TextField phone = (TextField)memberForm.lookup("#txtPhone");
		TextField email =(TextField)memberForm.lookup("#txtEmail");
		TextField address = (TextField)memberForm.lookup("#txtAddress");




		if(id.getText().isEmpty()) {
			cs.msgBox("입력오류", "아이디 입력 오류", 
					"아이디가 입력되지 않았습니다. 다시 입력하세요");
			id.requestFocus();  // 아이디 필드로 포커스 이동
			return;
		} else if(name.getText().isEmpty()) {
			cs.msgBox("입력오류", "이름 입력 오류", 
					"이름이 입력되지 않았습니다. 다시 입력하세요");
			name.requestFocus(); // 이름 필드로 포커스 이동
			return;
		} else if(pwd.getText().isEmpty()) {
			cs.msgBox("입력오류", "암호 입력 오류", 
					"암호가 입력되지 않았습니다. 다시 입력하세요");
			pwd.requestFocus(); // 비밀번호 필드로 포커스 이동
			return;
		} else if(pwdOk.getText().isEmpty()) {
			cs.msgBox("입력오류", "암호확인 입력 오류", 
					"암호확인이 입력되지 않았습니다. 다시 입력하세요");
			pwdOk.requestFocus(); // 비밀번호 확인 필드로 포커스 이동
			return;
		} else if(phone.getText().isEmpty()) {
			cs.msgBox("입력오류", "전화번호 입력 오류", "전화번호가 입력되지 않았습니다. 다시 입력하세요.");
			phone.requestFocus(); // 전화번호 필드로 포커스 이동
			return;
		} else if(email.getText().isEmpty()) {
			cs.msgBox("입력오류", "이메일 입력 오류", "이메일이 입력되지 않았습니다. 다시 입력하세요.");
			email.requestFocus();
			return;
		} else if(address.getText().isEmpty()) {
			cs.msgBox("입력오류", "주소 입력 오류", "주소가 입력되지 않았습니다. 다시 입력하세요");
			address.requestFocus();
			return;
		}

		// 암호, 암호확인 부분에서 일치하는지 안하는지 여부 확인 하는 방법.
		//		if(pwd.getText().equals(pwdOk.getText())) {
		//			cs.msgBox("암호", "암호일치여부", "암호가 일치합니다.");
		//		} else {
		//			cs.msgBox("암호", "암호일치여부", 
		//					"암호가 일치하지 않습니다. 다시 입력하세요.");
		//			pwd.clear();  // 비밀번호 필드 비우기.
		//			pwdOk.clear(); // 비밀번호 확인 필드 비우기
		//			pwd.requestFocus(); // 비밀번호 필드 포커스 이동
		//			return;
		//		}

		m.setMem_name(name.getText());
		m.setMem_id(id.getText());
		m.setMem_pw(pwd.getText());
		m.setMem_phone(phone.getText());



		ComboBox<String> cmbEmail =(ComboBox<String>)memberForm.lookup("#cmbEmail");

		// ComboBox에서 선택된 항목의 값을 반환합니다.
		if(cmbEmail.getValue() == null) {
			cs.msgBox("이메일 선택", "이메일 선택 오류", "이메일 선택 오류입니다.. 다시 선택하세요.");
			cmbEmail.requestFocus();
			return;
		} else {
			switch(cmbEmail.getValue()) { //"zum.com"
			case "naver.com":
				m.setMem_email(email.getText() + "@" + "naver.com");
				break;
			case "google.com":
				m.setMem_email(email.getText() + "@" + "google.com");
				break;
			case "hanmail.com":
				m.setMem_email(email.getText() + "@" + "hanmail.com");
				break;
			case "daum.net":
				m.setMem_email(email.getText() + "@" + "daum.net");
				break;
			case "yahoo.com":
				m.setMem_email(email.getText() + "@" + "yahoo.com");
				break;
			case "nate.com":
				m.setMem_email(email.getText() + "@" + "nate.com");
				break;
			case "icloud.com":
				m.setMem_email(email.getText() + "@" + "icloud.com");
				break;
			default:   
				m.setMem_email(email.getText() + "@" + cmbEmail.getValue());  // 수정필요
				break;
			}			
		}

		m.setMem_address(address.getText());

		RadioButton rdoMan = (RadioButton)memberForm.lookup("#rdoMan");
		RadioButton rdoWoman = (RadioButton)memberForm.lookup("#rdoWoman");

		if(rdoMan.isSelected()) {
			m.setMem_gender("M");
		} else if (rdoWoman.isSelected()) {
			m.setMem_gender("W");
		}

		//텍스트를 다 입력하고 sql문으로 넘겨서 실행한다음 다음페이지로

		dao.insertMember(m);
		Stage s = (Stage) memberForm.getScene().getWindow();
		FXMLLoader loader =new FXMLLoader(getClass().getResource("/main/fxml/login.fxml"));

		Parent login = null;
		try {
			login = loader.load();
			s.setScene(new Scene(login));
		} catch(Exception e) {
			e.printStackTrace();
		}
		LoginController ctrl = loader.getController();
		ctrl.setRoot(login);

		s.setTitle("로그인");
		s.show();

	}





	public void CancelProc(Parent root) {
		// TODO Auto-generated method stub
		Stage loginForm = (Stage) root.getScene().getWindow();
		FXMLLoader loader =new FXMLLoader(getClass().getResource("/main/fxml/login.fxml"));

		Parent cancel = null;
		try {
			cancel = loader.load();
			loginForm.setScene(new Scene(cancel));
		} catch(Exception e) {
			e.printStackTrace();
		}
		LoginController ctrl = loader.getController();
		ctrl.setRoot(cancel);

		loginForm.setTitle("로그인");
		loginForm.show();
	}




	public boolean DuplicateCheckProc(Parent memberForm) {
		// TODO Auto-generated method stub
		TextField id = (TextField)memberForm.lookup("#txtId");
		if(id.getText() == null) {
			System.out.println("아이디를 입력하세요.");
			id.requestFocus();
		} else {
			if(dao.chkId(id.getText())) {
				cs.msgBox("아이디 사용가능", "아이디 사용가능", "아이디를 사용할 수 있습니다.");
				return true;
			} else {
				cs.msgBox("아이디", "아이디 중복", "같은 아이디가 존재합니다. 다시 입력하세요.");
				id.clear();
				id.requestFocus();
			}
			return false;
		}
		return false;
	}
}


