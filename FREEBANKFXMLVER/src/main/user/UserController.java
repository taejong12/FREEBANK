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

	public void setRoot(Parent loginRoot) {
		this.root = loginRoot;

	}

	public void insertJoin() {
		System.out.println("회원가입 작성완료 버튼");
		// loginScreen
		us.insertUser(root);

		us.loginScreen(root);
	}

	public void loginMainMenu(UserDTO userDTO) {
		System.out.println("로그인성공");
		System.out.println("일반회원메뉴");
		
		ms.loginMainMenu(root, userDTO);
	}

	public void loginCheck() {
		System.out.println("로그인 일치여부 확인 메서드");
		// 일치하면 loginMainMenu 화면으로 고고

		UserDTO userDTO = us.selectUserInfoById(root);

		if (userDTO.getUserId() != null) {
			loginMainMenu(userDTO);
		}
	}

	public void join() {
		System.out.println("회원가입 페이지로 가기");
		us.joinScreen(root);
	}

	public void mainMenu() {
		System.out.println("메인화면 가기");
		ms.mainMenu(root);
	}

}
