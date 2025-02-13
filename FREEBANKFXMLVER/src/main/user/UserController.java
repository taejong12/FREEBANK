package main.user;

import java.sql.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import main.account.AccountDTO;
import main.account.AccountService;
import main.account.AccountServiceImpl;
import main.menu.MenuService;
import main.menu.MenuServiceImple;
import main.shop.PurchaseListDTO;
import main.shop.ShopService;
import main.shop.ShopServiceImpl;

public class UserController {

	Parent root;
	UserService us = new UserServiceImpl();
	MenuService ms = new MenuServiceImple();
	UserDTO userDTO = new UserDTO();
	AccountService as = new AccountServiceImpl();
	ShopService ss = new ShopServiceImpl();

	// 회원 상세페이지 컬럼
	@FXML
	private Text userIdText;
	@FXML
	private Text userCreditRatingText;
	@FXML
	private Text userTotalText;
	@FXML
	private Text userCreateText;
	@FXML
	private Text userUpdateText;
	@FXML
	private Text userSexText;

	@FXML
	private TextField userNameTextField;
	@FXML
	private TextField userAgeTextField;
	@FXML
	private TextField userEmailTextField;

	// 회원 구매내역 목록
	@FXML
	TableView<PurchaseListDTO> userPurchaseListTable;
	@FXML
	private TableColumn<PurchaseListDTO, Integer> userPurchaseListIdColumn;
	@FXML
	private TableColumn<PurchaseListDTO, String> userPurchaseListUserIdColumn;
	@FXML
	private TableColumn<PurchaseListDTO, String> userPurchaseListAccountColumn;
	@FXML
	private TableColumn<PurchaseListDTO, Integer> userPurchaseListShopIdColumn;
	@FXML
	private TableColumn<PurchaseListDTO, String> userPurchaseListShopNameColumn;
	@FXML
	private TableColumn<PurchaseListDTO, Integer> userPurchaseListTotalpaymentColumn;
	@FXML
	private TableColumn<PurchaseListDTO, Integer> userPurchaseListTotalshopcountColumn;
	@FXML
	private TableColumn<PurchaseListDTO, String> userPurchaseListCreateColumn;

	// 상단 아이디
	@FXML
	private Text loginUserId;

	public void setRoot(Parent loginRoot) {
		this.root = loginRoot;
	}

	public void setUser(UserDTO userDTO) {
		this.userDTO = userDTO;
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

	// 1.비회원상태
	// 회원가입 페이지 출력
	public void joinPage() {
		System.out.println("회원가입 페이지로 이동");
		us.joinPage(root);
	}

	// 1.비회원상태
	// 메인페이지로 이동(비로그인 상태)
	public void mainMenu() {
		System.out.println("메인페이지로 이동");
		ms.mainMenu(root, userDTO);
	}

	// 2.회원로그인상태
	// 회원메뉴페이지
	public void loginMainMenu() {
		System.out.println("일반회원메뉴페이지로 이동");
		ms.loginMainMenu(root, userDTO);
	}

	// 2.회원로그인상태
	// 회원구매내역 목록페이지 출력
	public void userPurchaseListPage() {
		System.out.println("회원구매내역 목록페이지로 이동");
		us.userPurchaseListPage(root, userDTO);
	}

	// 2.회원로그인상태
	// 회원정보페이지 출력
	public void userInfoDetailPage() {
		System.out.println("회원정보페이지로 이동");
		us.userInfoDetailPage(root, userDTO);
	}

	// 2.회원로그인 상태
	// 마이페이지 출력
	public void userInfoPage() {
		System.out.println("마이페이지로 이동");
		us.userInfoPage(root, userDTO);
	}

	// 회원수정(회원인증)
	public void updateUserInfoCheck() {
		System.out.println("회원수정하기");
		userDTO.setUserInfoUDCheck("U");
		userIdPwdCheckPage();
	}

	// 회원삭제(회원인증)
	public void deleteUserInfoCheck() {
		System.out.println("회원삭제하기");
		userDTO.setUserInfoUDCheck("D");
		userIdPwdCheckPage();
	}

	// 회원정보수정/삭제 아이디/비밀번호 체크 (인증페이지)
	public void userIdPwdCheckPage() {
		System.out.println("회원수정/삭제 아이디/비밀번호 체크 인증페이지");
		us.userIdPwdCheckPage(root, userDTO);
	}

	// 회원정보 출력
	public void selectUserInfo() {
		userIdText.setText(String.valueOf(userDTO.getUserId()));
		userNameTextField.setText(userDTO.getUserName());
		userAgeTextField.setText(String.valueOf(userDTO.getUserAge()));
		userSexText.setText(String.valueOf(userDTO.getUserSex()));
		userEmailTextField.setText(userDTO.getUserEmail());
		userCreditRatingText.setText(String.valueOf(userDTO.getUserCreditRating()));
		userTotalText.setText(String.valueOf(userDTO.getUserTotal()));
		userCreateText.setText(String.valueOf(userDTO.getUserCreate()));
		userUpdateText.setText(String.valueOf(userDTO.getUserUpdate()));
	}

	// 회원 수정/삭제 인증하기
	public void userInfoCheck() {
		boolean userUDCheck = us.userInfoCheck(root, userDTO);
		if (userUDCheck) {
			// 수정/삭제 완료
			System.out.println("수정/삭제 완료");
			mainMenu();

			Alert alertInfo = new Alert(AlertType.INFORMATION);

			alertInfo.setTitle("로그아웃");
			alertInfo.setHeaderText(null);
			alertInfo.setContentText("로그아웃됩니다.");

			// 확인 버튼을 누를 때까지 대기
			alertInfo.showAndWait();

		} else {
			// 마이페이지로 이동
			userInfoPage();
		}
	}

	// 회원 구매내역 출력
	public void selectUserPLList() {

		List<PurchaseListDTO> purchaseListByUserId = us.selectUserPLByID(userDTO.getUserId());

		ObservableList<PurchaseListDTO> purchaseListList = FXCollections.observableArrayList(purchaseListByUserId);

		userPurchaseListIdColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseListId"));
		userPurchaseListUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseListUserId"));
		userPurchaseListAccountColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseListAccount"));
		userPurchaseListShopIdColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseListShopId"));
		userPurchaseListShopNameColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseListShopName"));
		userPurchaseListTotalpaymentColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseListTotalpayment"));
		userPurchaseListTotalshopcountColumn
				.setCellValueFactory(new PropertyValueFactory<>("purchaseListTotalshopcount"));
		userPurchaseListCreateColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseListCreate"));

		userPurchaseListTable.setItems(purchaseListList);
	}

	// 상단 로그인
	public void loginUserId() {
		loginUserId.setText(userDTO.getUserId());
	}

	// 계좌페이지 출력
	public void accountPage() {
		System.out.println("계좌페이지로 이동");
		as.accountPage(root, userDTO);
	}

	// 2.회원메인페이지(회원로그인)
	// 로그아웃(메인페이지로 이동)(비로그인 상태로 변경)
	public void logout() {
		System.out.println("일반회원 로그아웃");
		userDTO = new UserDTO();
		ms.mainMenu(root, userDTO);
	}

	// 2.회원메인페이지(회원로그인)
	// 상품목록 페이지 출력
	public void shopLoginListPage() {
		System.out.println("상품목록 페이지(회원)로 이동");
		ss.shopLoginListPage(root, userDTO);
	}
}
