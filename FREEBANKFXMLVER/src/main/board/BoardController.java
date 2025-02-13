package main.board;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import main.account.AccountService;
import main.account.AccountServiceImpl;
import main.menu.MenuService;
import main.menu.MenuServiceImple;
import main.shop.ShopService;
import main.shop.ShopServiceImpl;
import main.user.UserDTO;
import main.user.UserService;
import main.user.UserServiceImpl;

public class BoardController {

	Parent root;
	UserDTO userDTO;
	BoardDTO boardDTO;
	MenuService ms = new MenuServiceImple();
	ShopService ss = new ShopServiceImpl();
	UserService us = new UserServiceImpl();
	AccountService as = new AccountServiceImpl();

	// 공지사항(비회원) 상세페이지 컬럼
	@FXML
	private Text mainDetailBoardId;
	@FXML
	private Text mainDetailBoardAuthor;
	@FXML
	private Text mainDetailBoardTitle;
	@FXML
	private Text mainDetailBoardCreated;
	@FXML
	private Text mainDetailBoardUpdate;
	@FXML
	private TextArea mainDetailBoardContent;

	// 공지사항(회원) 상세페이지 컬럼
	@FXML
	private Text mainUserDetailBoardId;
	@FXML
	private Text mainUserDetailBoardAuthor;
	@FXML
	private Text mainUserDetailBoardTitle;
	@FXML
	private Text mainUserDetailBoardCreated;
	@FXML
	private Text mainUserDetailBoardUpdate;
	@FXML
	private TextArea mainUserDetailBoardContent;

	// 상단 로그인
	@FXML
	private Text loginUserId;

	// 상단 로그인
	public void loginUserId() {
		loginUserId.setText(userDTO.getUserId());
	}

	public void setRoot(Parent root) {
		this.root = root;
	}

	// UserDTO를 설정하는 메서드 추가
	public void setUser(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public void showUserInfo() {
		if (userDTO != null) {
			System.out.println("현재 로그인한 사용자: " + userDTO.getUserId());
		} else {
			System.out.println("로그인 정보 없음");
		}
	}

	// 공지사항 저장
	public void setBoard(BoardDTO boardDTO) {
		this.boardDTO = boardDTO;
	}

	// 공지사항(비회원) 상세페이지 정보
	public void mainDetailBoardInfo() {
		mainDetailBoardId.setText(String.valueOf(boardDTO.getBoardId()));
		mainDetailBoardAuthor.setText(boardDTO.getBoardAuthor());
		mainDetailBoardTitle.setText(boardDTO.getBoardTitle());
		mainDetailBoardCreated.setText(String.valueOf(boardDTO.getBoardCreated()));
		mainDetailBoardUpdate.setText(String.valueOf(boardDTO.getBoardUpdate()));
		mainDetailBoardContent.setText(boardDTO.getBoardContent());
	}

	// 공지사항(회원) 상세페이지 정보
	public void mainUserDetailBoardInfo() {
		mainUserDetailBoardId.setText(String.valueOf(boardDTO.getBoardId()));
		mainUserDetailBoardAuthor.setText(boardDTO.getBoardAuthor());
		mainUserDetailBoardTitle.setText(boardDTO.getBoardTitle());
		mainUserDetailBoardCreated.setText(String.valueOf(boardDTO.getBoardCreated()));
		mainUserDetailBoardUpdate.setText(String.valueOf(boardDTO.getBoardUpdate()));
		mainUserDetailBoardContent.setText(boardDTO.getBoardContent());
	}

	// 1.메인페이지(비로그인 기능)
	// 메인페이지 출력(비로그인) - 상품목록(이전페이지) - 로그인(이전페이지) - 회원가입(이전페이지)
	public void mainMenu() {
		System.out.println("메인페이지로 이동");
		ms.mainMenu(root, userDTO);
	}

	// 1.비회원
	// 쇼핑몰 목록 페이지
	public void shopListPage() {
		System.out.println("상품목록 페이지(비회원)로 이동");
		ss.shopListPage(root, userDTO);
	}

	// 1.비회원 로그인 페이지
	public void loginPage() {
		us.loginPage(root);
	}

	// 2.회원메인페이지(회원로그인)
	// 로그아웃(메인페이지로 이동)(비로그인 상태로 변경)
	public void logout() {
		System.out.println("일반회원 로그아웃");
		userDTO = new UserDTO();
		ms.mainMenu(root, userDTO);
	}

	// 2.로그인 후 기능
	// 회원메인페이지
	public void loginMainMenu() {
		System.out.println("회원메인페이지(로그인)로 이동");
		ms.loginMainMenu(root, userDTO);
	}

	// 2.회원메인페이지(회원로그인)
	// 상품목록 페이지 출력
	public void shopLoginListPage() {
		System.out.println("상품목록 페이지(회원)로 이동");
		ss.shopLoginListPage(root, userDTO);
	}

	// 2.회원
	// 계좌페이지 출력
	public void accountPage() {
		System.out.println("계좌페이지로 이동");
		as.accountPage(root, userDTO);
	}

	// 2.회원메인페이지(회원로그인)
	// 마이페이지 출력
	public void userInfoPage() {
		System.out.println("마이페이지로 이동");
		us.userInfoPage(root, userDTO);
	}

}
