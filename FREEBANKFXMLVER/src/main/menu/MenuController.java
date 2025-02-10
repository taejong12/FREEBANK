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

	// 현재 로그인한 유저 정보를 확인하는 메서드 (예제)
	public void showUserInfo() {
		if (userDTO != null) {
			System.out.println("현재 로그인한 사용자: " + userDTO.getUserId());
		} else {
			System.out.println("로그인 정보 없음");
		}
	}

	public void board() {
		System.out.println("메인메뉴 공지사항버튼");

	}

	//쇼핑몰
	public void shop() {
		System.out.println("메인메뉴 쇼핑몰이동");
		ss.shopListPage(root, userDTO);
		
	}

	public void login() {
		System.out.println("메인메뉴 로그인버튼");
		us.loginScreen(root);
	}

	public void join() {
		System.out.println("메인메뉴 회원가입버튼");
		us.joinScreen(root);
	}

	public void account() {
		
		System.out.println("계좌페이지 버튼");
		showUserInfo();
		as.accountScreen(root, userDTO);

	}

	public void userInfo() {
		System.out.println("마이페이지 이동");

	}

	public void logout() {
		System.out.println("로그아웃");
		userDTO = new UserDTO();
		ms.mainMenu(root, userDTO);
	}

	// 이거는 로그인한 후 체크 하면서 화면 전환이 이루어져야 하니까 최초만 메인메뉴 띙주고
	// 여기서는 메뉴에 보여주는 동작
	// 화면 - 컨트롤 - 동작 - 다음페이지 화면 -
	// 즉 지금은 어차피 실행하면 초기에 메인메뉴 - fxml - 컨트롤러 - 서비스 - 다음화면
	// 여기서는 해당하는 화면에서의 동작을 작성
//	public void mainMenu() {
//		System.out.println("메인메뉴");
//		ms.mainMenu();
//	}
//	
//	public void loginMainMenu() {
//		System.out.println("일반회원메뉴");
//		ms.loginMainMenu();
//	}
//	
//	public void adminMainMenu() {
//		System.out.println("관리자메뉴");
//		ms.adminMainMenu();
//	}
//	
}
