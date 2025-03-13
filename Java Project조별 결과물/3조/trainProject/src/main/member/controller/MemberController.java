package main.member.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextFormatter;
import javafx.scene.paint.Color;
import main.common.service.CommonService;
import main.member.service.MemberService;


public class MemberController {
	Parent root;
	MemberService ms;
	CommonService cs;


	public MemberController() {
		// TODO Auto-generated constructor stub
		ms = new MemberService();
		cs = new CommonService();	

		;
	}

	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;

	}
	public void CancelProc() {
		ms.CancelProc(root);
	}

	public void SignUpProc() {
		ms.SignUpProc(root);
	}

	public void mainHome() {
		cs.mainPage(root);
	}

	public void DuplicateCheckProc() {
		ms.DuplicateCheckProc(root);
	}

	@FXML
	private PasswordField txtPw;

	@FXML
	private PasswordField txtPwOk;

	@FXML
	private Label confirmLabel;

	@FXML
	private Label messageLabel;

	public void initialize() {
		// 비밀번호 유효성 검사 리스너 추가
		txtPw.textProperty().addListener((observable, oldValue, newValue) -> {
			validatePassword(newValue);
			checkPasswordMatch(); // 비밀번호 입력할 때마다 일치 여부도 검사
		});

		// 비밀번호 확인 필드 리스너 추가
		txtPwOk.textProperty().addListener((observable, oldValue, newValue) -> {
			checkPasswordMatch(); // 비밀번호 확인 입력 시 일치 여부 검사
		});

		// 비밀번호 최대 길이 제한 (16자)
		int maxLength = 16;
		txtPw.setTextFormatter(createLengthLimiter(maxLength));
		txtPwOk.setTextFormatter(createLengthLimiter(maxLength));
	}

	// 비밀번호 유효성 검사
	private void validatePassword(String password) {
		if (isValidPassword(password)) { 
			messageLabel.setText("사용 가능한 비밀번호입니다.");
			messageLabel.setTextFill(Color.GREEN);
		} else {
			messageLabel.setText("비밀번호는 8자 이상, 대/소문자, 특수문자 포함해야 합니다.");
			messageLabel.setTextFill(Color.RED);
		}
	}

	// 비밀번호 일치 여부 확인
	private void checkPasswordMatch() {
		String pw = txtPw.getText();
		String pwConfirm = txtPwOk.getText();

		if (!pwConfirm.isEmpty()) { // 확인 필드가 비어있지 않을 때만 비교
			if (pw.equals(pwConfirm)) {
				confirmLabel.setText("비밀번호가 일치합니다.");
				confirmLabel.setTextFill(Color.GREEN);
			} else {
				confirmLabel.setText("비밀번호가 일치하지 않습니다.");
				confirmLabel.setTextFill(Color.RED);
			}
		} else {
			confirmLabel.setText(""); // 비어있으면 메시지 표시 안 함
		}
	}

	// 비밀번호 유효성 검사 (8~16자, 대/소문자, 특수문자 포함)
	private boolean isValidPassword(String password) {
		String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\W).{8,16}$";
		return password.matches(regex);
	}

	// 최대 길이 제한을 위한 TextFormatter 생성
	private TextFormatter<String> createLengthLimiter(int maxLength) {
		return new TextFormatter<>(change -> 
		change.getControlNewText().length() > maxLength ? null : change);
	}	
}


//	public void initialize() {
//	    // PasswordField의 textProperty에 리스너 추가
//	    txtPw.textProperty().addListener((observable, oldValue, newValue) -> {
//	        // 새로운 텍스트 (newValue)로 비밀번호 유효성 검사
//	        if (isValidPassword(newValue)) { 
//	            messageLabel.setText("사용 가능한 비밀번호입니다.");
//	            messageLabel.setTextFill(Color.GREEN);
//	        } else {
//	            messageLabel.setText("비밀번호는 8자 이상, 대/소문자, 특수문자 포함해야 합니다.");
//	            messageLabel.setTextFill(Color.RED);
//	        }
//	    });
//	    
//	    
//	    int maxLength = 16;
//	    txtPw.setTextFormatter(new TextFormatter<>(change -> {
//            // 변경된 텍스트가 최대 길이를 넘으면 변경을 취소
//            if (change.getControlNewText().length() > maxLength) {
//                return null; // 변경 취소
//            }
//            return change; // 변경 허용
//        }));
//	}
//
//	public boolean isValidPassword(String password) {
//	    // 비밀번호 유효성 검사 정규식
//	    String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\W).{8,16}$";
//	    return password.matches(regex);
//	}

//	public void signUpPress() {
//		// TODO Auto-generated method stub
//		ms.signUpPress(root);
//		
//		
//	}

