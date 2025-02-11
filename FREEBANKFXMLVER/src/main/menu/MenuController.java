package main.menu;

import javafx.scene.Parent;
import main.account.AccountService;
import main.account.AccountServiceImpl;
import main.shop.ShopService;
import main.shop.ShopServiceImpl;
import main.user.UserDTO;
import main.user.UserService;
import main.user.UserServiceImpl;

public class MenuController {

	Parent root;
	private UserDTO userDTO;
	UserService us = new UserServiceImpl();
	MenuService ms = new MenuServiceImple();
	AccountService as = new AccountServiceImpl();
	ShopService ss = new ShopServiceImpl();

	public void setRoot(Parent root) {
		this.root = root;
	}

	// UserDTO를 설정하는 메서드 추가
	public void setUser(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	// 현재 로그인한 유저 정보를 확인하는 메서드(확인용)
	public void showUserInfo() {
		if (userDTO != null) {
			System.out.println("현재 로그인한 사용자: " + userDTO.getUserId());
		} else {
			System.out.println("로그인 정보 없음");
		}
	}

	// 상품목록 페이지 출력
	public void shopListPage() {
		System.out.println("상품목록 페이지로 이동");
		ss.shopListPage(root, userDTO);
	}

	// 로그인 페이지 출력
	public void loginPage() {
		System.out.println("로그인 페이지로 이동");
		us.loginPage(root);
	}

	// 회원가입 페이지 출력
	public void joinPage() {
		System.out.println("회원가입 페이지로 이동");
		us.joinPage(root);
	}

	// 계좌 페이지 출력
	public void accountPage() {
		System.out.println("계좌 페이지로 이동");
		as.accountPage(root, userDTO);
	}

	// 마이페이지 출력
	public void userInfoPage() {
		System.out.println("마이페이지로 이동");
		us.userInfoPage(root, userDTO);
	}

	// 로그아웃(비로그인 메인페이지로 이동)
	public void logout() {
		System.out.println("일반회원 로그아웃");
		userDTO = new UserDTO();
		ms.mainMenu(root, userDTO);
	}

}
