package main.user;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import main.menu.MenuService;
import main.menu.MenuServiceImple;

public class UserController {

	Parent root;
	UserService us = new UserServiceImpl();
	MenuService ms = new MenuServiceImple();
	UserDTO userDTO = new UserDTO();

	public void setRoot(Parent loginRoot) {
		this.root = loginRoot;

	}

	// 회원가입 버튼
	public void insertJoin() {
		System.out.println("회원가입 작성완료 버튼");
		us.insertUser(root);
		// 메인페이지로 이동(비로그인 상태)
		mainMenu();
	}

	// 일반회원/관리자 메뉴
	public void loginMainMenu(UserDTO userDTO) {
		System.out.println("로그인성공");

		if (userDTO.getUserAdmin().equals("N")) {
			System.out.println("일반회원메뉴");
			ms.loginMainMenu(root, userDTO);
		} else if (userDTO.getUserAdmin().equals("Y")) {
			System.out.println("관리자메뉴");
			ms.adminMainMenu(root, userDTO);
		} else {
			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setTitle("로그인 에러");
			alertError.setHeaderText(null);
			alertError.setContentText("로그인 에러");

			// 확인 버튼을 누를 때까지 대기
			alertError.showAndWait();
		}
	}

	// 로그인 버튼
	public void loginCheck() {
		System.out.println("로그인 일치여부 확인 메서드");

		// 유저 정보 조회
		UserDTO userDTO = us.selectUserInfoById(root);

		if (userDTO.getUserId() != null) {
			loginMainMenu(userDTO);
		}
	}

	// 회원가입 페이지 출력
	public void joinPage() {
		System.out.println("회원가입 페이지로 이동");
		us.joinPage(root);
	}

	// 메인페이지로 이동(비로그인 상태)
	public void mainMenu() {
		System.out.println("메인페이지로 이동");
		ms.mainMenu(root, userDTO);
	}

}
