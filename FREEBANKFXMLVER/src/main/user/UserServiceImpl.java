package main.user;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class UserServiceImpl implements UserService {

	UserDAO ud = new UserDAO();

	@Override
	public UserDTO userLogin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO userLogout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO selectUserInfoById(Parent root) {

		TextField id = (TextField) root.lookup("#userId");
		String userId = id.getText();

		PasswordField pwd = (PasswordField) root.lookup("#userPwd");
		String userPwd = pwd.getText();

		UserDTO userDTO = ud.selectUserInfoById(userId);

		Alert alert = new Alert(AlertType.ERROR);

		if (userDTO.getUserId() == null) {

			alert.setTitle("로그인 실패");
			alert.setHeaderText(null);
			alert.setContentText("아이디/비밀번호가 존재하지 않습니다.");

			// 로그인 입력 필드 초기화
			id.clear();
			pwd.clear();

			// 확인 버튼을 누를 때까지 대기
			alert.showAndWait();
		} else if (userDTO.getUserId() != null && !(userDTO.getUserPwd().equals(userPwd))) {

			alert.setTitle("로그인 실패");
			alert.setHeaderText(null);
			alert.setContentText("아이디/비밀번호가 일치하지 않습니다.");

			// 로그인 입력 필드 초기화
			id.clear();
			pwd.clear();

			// 확인 버튼을 누를 때까지 대기
			alert.showAndWait();
		} else if (userId.equals(userDTO.getUserId()) && userPwd.equals(userDTO.getUserPwd())) {
			if ("Y".equals(userDTO.getUserAdmin())) {
				System.out.println("관리자로 로그인하셨습니다.");
				return userDTO;
			} else {
				System.out.println("회원으로 로그인하셨습니다.");
				return userDTO;
			}
		}

		return new UserDTO();
	}

	//회원가입
	public void insertUser(Parent root) {

		UserDTO userDTO = new UserDTO();

		TextField id = (TextField) root.lookup("#userId");
		String userId = id.getText();

		PasswordField pwd = (PasswordField) root.lookup("#userPwd");
		String userPwd = pwd.getText();

		PasswordField pwdCh = (PasswordField) root.lookup("#userPwdCh");
		String userPwdCh = pwdCh.getText();

		TextField name = (TextField) root.lookup("#userName");
		String userName = name.getText();

		TextField age = (TextField) root.lookup("#userAge");
		int userAge = Integer.parseInt(age.getText());

		TextField sex = (TextField) root.lookup("#userSex");
		String userSex = sex.getText();

		TextField email = (TextField) root.lookup("#userEmail");
		String userEmail = email.getText();

		/*
		 * if (id.getText().isEmpty()) { System.out.println("아이디 입력 필요");
		 * id.requestFocus(); return; }
		 */
		// 회원가입 버튼 누르면 나중에 종합체크하는 메서드 작성
		// joinCheck(userDTO);

		System.out.println("입력된 ID: " + userId);
		System.out.println("입력된 userPwd: " + userPwd);
		System.out.println("입력된 userPwdCh: " + userPwdCh);
		System.out.println("입력된 userName: " + userName);
		System.out.println("입력된 userAge: " + userAge);
		System.out.println("입력된 userSex: " + userSex);
		System.out.println("입력된 userEmail: " + userEmail);

		userDTO.setUserId(userId);
		userDTO.setUserPwd(userPwd);
		userDTO.setUserName(userName);
		userDTO.setUserAge(userAge);
		userDTO.setUserSex("X");
		userDTO.setUserEmail(userEmail);

		ud.insertUser(userDTO);

	}

	@Override
	public UserDTO updateUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO deleteUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	// 로그인화면이동
	public void loginScreen(Parent root) {

		Stage loginScreen = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/user/loginScreen.fxml"));

		try {

			Parent loginRoot = loader.load();

			UserController userCtrl = loader.getController();

			userCtrl.setRoot(loginRoot);
			loginScreen.setScene(new Scene(loginRoot));
			loginScreen.setTitle("로그인페이지");
			loginScreen.show();

		} catch (Exception e) {
			System.out.println("로그인 화면 출력 에러");
			e.printStackTrace();
		}

	}

	// 회원가입화면이동
	public void joinScreen(Parent root) {
		Stage joinScreen = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/user/joinScreen.fxml"));

		try {

			Parent joinRoot = loader.load();

			UserController userCtrl = loader.getController();

			userCtrl.setRoot(joinRoot);
			joinScreen.setScene(new Scene(joinRoot));
			joinScreen.setTitle("회원가입페이지");
			joinScreen.show();

		} catch (Exception e) {
			System.out.println("회원가입화면 출력 에러");
			e.printStackTrace();
		}
	}

}
