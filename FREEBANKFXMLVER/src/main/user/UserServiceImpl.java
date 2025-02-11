package main.user;

import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.shop.PurchaseListDTO;

public class UserServiceImpl implements UserService {

	UserDAO ud = new UserDAO();

	// 유저 정보 조회 / 로그인 일치여부 확인
	public UserDTO selectUserInfoById(Parent root) {

		TextField id = (TextField) root.lookup("#userId");
		String userId = id.getText();

		PasswordField pwd = (PasswordField) root.lookup("#userPwd");
		String userPwd = pwd.getText();

		UserDTO userDTO = ud.selectUserInfoById(userId);

		Alert alertError = new Alert(AlertType.ERROR);

		if (userDTO.getUserId() == null) {

			alertError.setTitle("로그인 실패");
			alertError.setHeaderText(null);
			alertError.setContentText("아이디/비밀번호가 존재하지 않습니다.");

			// 로그인 입력 필드 초기화
			id.clear();
			pwd.clear();

			// 확인 버튼을 누를 때까지 대기
			alertError.showAndWait();
		} else if (userDTO.getUserId() != null && !(userDTO.getUserPwd().equals(userPwd))) {

			alertError.setTitle("로그인 실패");
			alertError.setHeaderText(null);
			alertError.setContentText("아이디/비밀번호가 일치하지 않습니다.");

			// 로그인 입력 필드 초기화
			id.clear();
			pwd.clear();

			// 확인 버튼을 누를 때까지 대기
			alertError.showAndWait();
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

	// 회원가입하기
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
		// 수정필요
		userDTO.setUserSex("X");
		userDTO.setUserEmail(userEmail);

		int result = ud.insertUser(userDTO);

		if (result >= 1) {
			Alert alertInfo = new Alert(AlertType.INFORMATION);
			alertInfo.setTitle("회원가입 완료");
			alertInfo.setHeaderText(null);
			alertInfo.setContentText("회원가입 완료");

			// 확인 버튼을 누를 때까지 대기
			alertInfo.showAndWait();
		} else {
			Alert alertError = new Alert(AlertType.ERROR);

			alertError.setTitle("회원가입 실패");
			alertError.setHeaderText(null);
			alertError.setContentText("회원가입 실패");

			// 확인 버튼을 누를 때까지 대기
			alertError.showAndWait();
		}

	}

	// 로그인 페이지 출력
	public void loginPage(Parent root) {

		Stage loginPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/user/loginPage.fxml"));

		try {

			Parent loginPageRoot = loader.load();

			UserController loginPageCtrl = loader.getController();

			loginPageCtrl.setRoot(loginPageRoot);

			loginPage.setScene(new Scene(loginPageRoot));
			loginPage.setTitle("로그인 페이지");
			loginPage.show();

		} catch (Exception e) {
			System.out.println("로그인 페이지 출력 에러");
			e.printStackTrace();
		}

	}

	// 회원가입 페이지 출력
	public void joinPage(Parent root) {
		Stage joinPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/user/joinPage.fxml"));

		try {

			Parent joinPageRoot = loader.load();

			UserController joinPageCtrl = loader.getController();

			joinPageCtrl.setRoot(joinPageRoot);

			joinPage.setScene(new Scene(joinPageRoot));
			joinPage.setTitle("회원가입 페이지");
			joinPage.show();

		} catch (Exception e) {
			System.out.println("회원가입페이지 출력 에러");
			e.printStackTrace();
		}
	}

	// 마이페이지 출력
	public void userInfoPage(Parent root, UserDTO userDTO) {

		//수정후업데이트
		userDTO = ud.selectUserInfoById(userDTO.getUserId());
		
		Stage userInfoPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/user/userInfoPage.fxml"));

		try {

			Parent userInfoPageRoot = loader.load();

			UserController userInfoPageCtrl = loader.getController();

			userInfoPageCtrl.setRoot(userInfoPageRoot);
			userInfoPageCtrl.setUser(userDTO);

			userInfoPage.setScene(new Scene(userInfoPageRoot));
			userInfoPage.setTitle("마이페이지");
			userInfoPage.show();

		} catch (Exception e) {
			System.out.println("마이페이지 출력 에러");
			e.printStackTrace();
		}

	}

	// 회원구매내역 목록페이지 출력
	public void userPLListPage(Parent root, UserDTO userDTO) {
		Stage userPLListPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/user/userPLListPage.fxml"));

		try {

			Parent userPLListPageRoot = loader.load();

			UserController userPLListPageCtrl = loader.getController();

			userPLListPageCtrl.setRoot(userPLListPageRoot);
			userPLListPageCtrl.setUser(userDTO);
			// 구매내역 목록출력
			userPLListPageCtrl.selectUserPLList();

			userPLListPage.setScene(new Scene(userPLListPageRoot));
			userPLListPage.setTitle("구매내역 목록페이지");
			userPLListPage.show();

		} catch (Exception e) {
			System.out.println("구매내역 목록페이지 출력 에러");
			e.printStackTrace();
		}

	}

	// 회원정보페이지 출력
	public void userInfoDetailPage(Parent root, UserDTO userDTO) {
		Stage userInfoDetailPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/user/userInfoDetailPage.fxml"));

		try {

			Parent userInfoDetailPageRoot = loader.load();

			UserController userInfoDetailPageCtrl = loader.getController();

			userInfoDetailPageCtrl.setRoot(userInfoDetailPageRoot);
			userInfoDetailPageCtrl.setUser(userDTO);
			// 회원정보 출력
			userInfoDetailPageCtrl.selectUserInfo();

			userInfoDetailPage.setScene(new Scene(userInfoDetailPageRoot));
			userInfoDetailPage.setTitle("회원정보페이지");
			userInfoDetailPage.show();

		} catch (Exception e) {
			System.out.println("회원정보페이지 출력 에러");
			e.printStackTrace();
		}
	}

	// 회원수정 인증페이지 출력
	public void userIdPwdCheckPage(Parent root, UserDTO userDTO) {
		if (userDTO.getUserInfoUDCheck().equals("U")) {

			TextField textUserName = (TextField) root.lookup("#userNameTextField");
			String userName = textUserName.getText();

			TextField textUserAge = (TextField) root.lookup("#userAgeTextField");
			int userAge = Integer.parseInt(textUserAge.getText());

			TextField textUserSex = (TextField) root.lookup("#userSexTextField");
			String userSex = textUserSex.getText();

			TextField textUserEmail = (TextField) root.lookup("#userEmailTextField");
			String userEmail = textUserEmail.getText();

			System.out.println("수정 이름: " + userName);
			System.out.println("수정 나이: " + userAge);
			System.out.println("수정 성별: " + userSex);
			System.out.println("수정 이메일: " + userEmail);

			userDTO.setUserName(userName);
			userDTO.setUserAge(userAge);
			// 수정필요
			userDTO.setUserSex("Y");
			userDTO.setUserEmail(userEmail);
		}

		Stage userIdPwdCheckPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/user/userIdPwdCheckPage.fxml"));

		try {

			Parent userIdPwdCheckPageRoot = loader.load();

			UserController userIdPwdCheckPageCtrl = loader.getController();

			userIdPwdCheckPageCtrl.setRoot(userIdPwdCheckPageRoot);
			userIdPwdCheckPageCtrl.setUser(userDTO);

			userIdPwdCheckPage.setScene(new Scene(userIdPwdCheckPageRoot));
			userIdPwdCheckPage.setTitle("회원수정/삭제 인증페이지");
			userIdPwdCheckPage.show();

		} catch (Exception e) {
			System.out.println("회원수정/삭제 인증페이지 출력 에러");
			e.printStackTrace();
		}
	}

	// 회원정보수정
	public void updateUserInfo(UserDTO userDTO) {

		int result = ud.updateUserInfo(userDTO);

		if (result >= 1) {
			Alert alertInfo = new Alert(AlertType.INFORMATION);
			alertInfo.setTitle("회원수정완료");
			alertInfo.setHeaderText(null);
			alertInfo.setContentText("회원수정완료");

			// 확인 버튼을 누를 때까지 대기
			alertInfo.showAndWait();
		} else {
			Alert alertError = new Alert(AlertType.ERROR);

			alertError.setTitle("회원정보 수정실패");
			alertError.setHeaderText(null);
			alertError.setContentText("회원정보 수정실패");

			// 확인 버튼을 누를 때까지 대기
			alertError.showAndWait();
		}

	}

	// 회원탈퇴
	public void deleteUserInfo(UserDTO userDTO) {

		int result = ud.deleteUserInfo(userDTO);

		if (result >= 1) {
			Alert alertInfo = new Alert(AlertType.INFORMATION);
			alertInfo.setTitle("회원탈퇴완료");
			alertInfo.setHeaderText(null);
			alertInfo.setContentText("회원탈퇴완료");

			// 확인 버튼을 누를 때까지 대기
			alertInfo.showAndWait();
		} else {
			Alert alertError = new Alert(AlertType.ERROR);

			alertError.setTitle("회원탈퇴실패");
			alertError.setHeaderText(null);
			alertError.setContentText("회원탈퇴실패");

			// 확인 버튼을 누를 때까지 대기
			alertError.showAndWait();
		}
	}

	// 회원 아이디/비밀번호 인증
	public boolean userInfoCheck(Parent root, UserDTO userDTO) {

		TextField textId = (TextField) root.lookup("#userInfoCheckId");
		String userId = textId.getText();

		PasswordField textPwd = (PasswordField) root.lookup("#userInfoCheckPwd");
		String userPwd = textPwd.getText();

		UserDTO user = ud.selectUserInfoById(userDTO.getUserId());

		boolean userIdPwdCheck = false;
		
		if (user.getUserId().equals(userId) && user.getUserPwd().equals(userPwd)) {
			System.out.println("아이디/비밀번호 일치");
			if (userDTO.getUserInfoUDCheck().equals("U")) {
				System.out.println("회원정보 수정");
				updateUserInfo(userDTO);
				userIdPwdCheck = true;
			} else if (userDTO.getUserInfoUDCheck().equals("D")) {
				System.out.println("회원정보 삭제");
				deleteUserInfo(userDTO);
				userIdPwdCheck = true;
			} else {
				System.out.println("인증은 성공, 어딘가 잘못됨");
			}
		} else {
			System.out.println("인증실패");
			Alert alertError = new Alert(AlertType.ERROR);

			alertError.setTitle("회원인증실패");
			alertError.setHeaderText(null);
			alertError.setContentText("회원인증실패");

			// 확인 버튼을 누를 때까지 대기
			alertError.showAndWait();
		}

		return userIdPwdCheck;
		
	}

	// 회원 구매내역 목록조회
	public List<PurchaseListDTO> selectUserPLByID(String userId) {
		return ud.selectUserPLByID(userId);
	}

}
