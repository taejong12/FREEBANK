package main.menu;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import main.account.AccountService;
import main.account.AccountServiceImpl;
import main.board.BoardDTO;
import main.board.BoardService;
import main.board.BoardServiceImpl;
import main.shop.ShopDTO;
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
	BoardService bs = new BoardServiceImpl();

	// 공지사항 목록
	@FXML
	TableView<BoardDTO> mainBoardTable;
	@FXML
	private TableColumn<BoardDTO, String> mainBoardTitleColumn;
	
	// 상품 목록
	@FXML
	TableView<ShopDTO> mainShopTable;
	@FXML
	private TableColumn<ShopDTO, String> mainShopNameColumn;
	
	@FXML
	private Text loginUserId;
	
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

	// 1.메인페이지(비로그인)
	// 상품목록 페이지 출력
	public void shopListPage() {
		System.out.println("상품목록 페이지(비회원)로 이동");
		ss.shopListPage(root, userDTO);
	}

	// 1.메인페이지(비로그인)
	// 로그인 페이지 출력
	public void loginPage() {
		System.out.println("로그인 페이지로 이동");
		us.loginPage(root);
	}

	// 1.메인페이지(비로그인)
	// 회원가입 페이지 출력
	public void joinPage() {
		System.out.println("회원가입 페이지로 이동");
		us.joinPage(root);
	}

	// 2.회원메인페이지(회원로그인)
	// 계좌 페이지 출력
	public void accountPage() {
		System.out.println("계좌 페이지로 이동");
		as.accountPage(root, userDTO);
	}

	// 2.회원메인페이지(회원로그인)
	// 상품목록 페이지 출력
	public void shopLoginListPage() {
		System.out.println("상품목록 페이지(회원)로 이동");
		ss.shopLoginListPage(root, userDTO);
	}

	// 2.회원메인페이지(회원로그인)
	// 마이페이지 출력
	public void userInfoPage() {
		System.out.println("마이페이지로 이동");
		us.userInfoPage(root, userDTO);
	}

	// 2.회원메인페이지(회원로그인)
	// 로그아웃(메인페이지로 이동)(비로그인 상태로 변경)
	public void logout() {
		System.out.println("일반회원 로그아웃");
		userDTO = new UserDTO();
		ms.mainMenu(root, userDTO);
	}

	// 상단 로그인 아이디 출력
	public void loginUserId() {
		loginUserId.setText(userDTO.getUserId());
	}

	// 공지사항 목록
	public void selectBoardList() {
		List<BoardDTO> selectBoardList = bs.selectBoardList();
		
		ObservableList<BoardDTO> boardList = FXCollections.observableArrayList(selectBoardList);

		mainBoardTitleColumn.setCellValueFactory(new PropertyValueFactory<>("boardTitle"));

		mainBoardTable.setItems(boardList);
		
	}

	// 상품 목록
	public void selectShopList() {
		List<ShopDTO> selectShopList = ss.selectShopList();
		
		ObservableList<ShopDTO> shopList = FXCollections.observableArrayList(selectShopList);
		
		mainShopNameColumn.setCellValueFactory(new PropertyValueFactory<>("shopName"));

		mainShopTable.setItems(shopList);
	}

}
