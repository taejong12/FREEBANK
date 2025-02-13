package main.account;

import java.sql.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import main.menu.MenuService;
import main.menu.MenuServiceImple;
import main.shop.ShopService;
import main.shop.ShopServiceImpl;
import main.user.UserDTO;
import main.user.UserService;
import main.user.UserServiceImpl;

public class AccountController {

	Parent root;
	UserDTO userDTO;
	AccountService as = new AccountServiceImpl();
	MenuService ms = new MenuServiceImple();
	ShopService ss = new ShopServiceImpl();
	UserService us = new UserServiceImpl();

	// 계좌 리스트 바인딩
	@FXML
	TableView<AccountDTO> accountTableView;
	@FXML
	private TableColumn<AccountDTO, String> accountAccountColumn;
	@FXML
	private TableColumn<AccountDTO, Integer> accountBalanceColumn;
	@FXML
	private TableColumn<AccountDTO, Date> accountCreateColumn;
	@FXML
	private TableColumn<AccountDTO, String> accountIdColumn;

	// 상단 아이디
	@FXML
	private Text loginUserId;

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

	// 계좌 목록 출력
	public void selectAccountList() {

		List<AccountDTO> accountListByUserId = as.selectUserAccountByID(userDTO.getUserId());

		// TableColumn이 자동 바인딩 안 될 경우 체크
		if (accountAccountColumn == null || accountBalanceColumn == null) {
			System.out.println("TableColumn이 FXML에서 초기화되지 않음!");
			return;
		}

		accountAccountColumn.setCellValueFactory(new PropertyValueFactory<>("accountAccount"));
		accountBalanceColumn.setCellValueFactory(new PropertyValueFactory<>("accountBalance"));
		accountCreateColumn.setCellValueFactory(new PropertyValueFactory<>("accountCreate"));
		accountIdColumn.setCellValueFactory(new PropertyValueFactory<>("accountId"));

		ObservableList<AccountDTO> accountDTOList = FXCollections.observableArrayList(accountListByUserId);

		accountTableView.setItems(accountDTOList);

	}

	// 계좌 개설
	public void insertAccount() {
		System.out.println("계좌개설 버튼");
		as.insertAccount(userDTO);
	}

	// 계좌목록 페이지 출력
	public void accountListSelectPage() {
		System.out.println("계좌목록 페이지로 이동");
		as.accountListSelectPage(root, userDTO);
	}

	// 계좌입금 페이지 출력
	public void accountDepositUpdatePage() {
		System.out.println("계좌입금 페이지로 이동");
		as.accountDepositUpdatePage(root, userDTO);
	}

	// 계좌출금 페이지 출력
	public void accountWithdrawalUpdatePage() {
		System.out.println("계좌출금 페이지로 이동");
		as.accountWithdrawalUpdatePage(root, userDTO);
	}

	// 계좌해지 페이지 출력
	public void accountDeletePage() {
		System.out.println("계좌해지 페이지로 이동");
		as.accountDeletePage(root, userDTO);
	}

	// 계좌 출금
	public void accountWithdrawal() {
		System.out.println("계좌출금하기");
		as.updateAccountWithdrawal(root, userDTO);
		// 계좌페이지 출력
		accountPage();
	}

	// 메인페이지 출력(일반회원 메인페이지)
	public void loginMainMenu() {
		System.out.println("회원메인페이지(로그인)로 이동");
		ms.loginMainMenu(root, userDTO);
	}

	// 계좌페이지 출력
	public void accountPage() {
		System.out.println("계좌페이지로 이동");
		as.accountPage(root, userDTO);
	}

	// 계좌 입금
	public void accountDeposit() {
		System.out.println("계좌입금하기");
		as.updateAccountDeposit(root, userDTO);
		// 계좌페이지 출력
		accountPage();
	}

	// 계좌 해지
	public void deleteAccount() {
		System.out.println("계좌해지하기");
		as.deleteAccount(root, userDTO);
		// 계좌페이지 출력
		accountPage();
	}

	// 계좌 목록 보여주기(입금, 출금, 해지)
	public void selectAccount() {

		List<AccountDTO> accountListByUserId = as.selectUserAccountByID(userDTO.getUserId());

		accountAccountColumn.setCellValueFactory(new PropertyValueFactory<>("accountAccount"));
		accountBalanceColumn.setCellValueFactory(new PropertyValueFactory<>("accountBalance"));

		ObservableList<AccountDTO> accountDTOList = FXCollections.observableArrayList(accountListByUserId);

		accountTableView.setItems(accountDTOList);
	}

	// 상단 로그인
	public void loginUserId() {
		loginUserId.setText(userDTO.getUserId());
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

}
