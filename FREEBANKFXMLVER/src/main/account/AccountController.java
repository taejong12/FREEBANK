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
import main.menu.MenuService;
import main.menu.MenuServiceImple;
import main.user.UserDTO;

public class AccountController {

	Parent root;
	UserDTO userDTO;
	AccountService as = new AccountServiceImpl();
	MenuService ms = new MenuServiceImple();

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

	// 컨트롤러의 initialize()가 자동으로 실행됨. 초기값
	//계좌 리스트 호출
	public void outputAccountList() {

		System.out.println("outputAccountList#########################################");
		showUserInfo();

		List<AccountDTO> accountListByUserId = as.selectUserAccountByID(userDTO.getUserId());

		// 🔥 TableColumn이 자동 바인딩 안 될 경우 체크
		if (accountAccountColumn == null || accountBalanceColumn == null) {
			System.out.println("❌ TableColumn이 FXML에서 초기화되지 않음!");
			return;
		}

		accountAccountColumn.setCellValueFactory(new PropertyValueFactory<>("accountAccount"));
		accountBalanceColumn.setCellValueFactory(new PropertyValueFactory<>("accountBalance"));
		accountCreateColumn.setCellValueFactory(new PropertyValueFactory<>("accountCreate"));
		accountIdColumn.setCellValueFactory(new PropertyValueFactory<>("accountId"));

		ObservableList<AccountDTO> accountDTOList = FXCollections.observableArrayList(accountListByUserId);

		accountTableView.setItems(accountDTOList);

	}

	public void insertAccount() {
		System.out.println("계좌개설 버튼");
		as.insertAccount(userDTO);

	}

	public void selectAccount() {
		System.out.println("계좌조회 버튼");
		as.selectAccountListScreen(root, userDTO);

	}

	public void updateAccountDeposit() {
		System.out.println("계좌입금 페이지 가기");
		as.updateAccountDepositScreen(root, userDTO);
	}

	public void updateAccountWithdrawal() {
		System.out.println("계좌출금 페이지 가기");
		as.updateAccountWithdrawalScreen(root, userDTO);
	}

	public void accountWithdrawal() {
		System.out.println("계좌출금하기");
		as.updateAccountWithdrawal(root, userDTO);
		account();
	}

	public void deleteAccountScreen() {
		System.out.println("계좌해지 페이지 가기");
		as.deleteAccountScreen(root, userDTO);
	}

	public void loginMainMenu() {
		System.out.println("메인메뉴 버튼");
		ms.loginMainMenu(root, userDTO);

	}

	public void account() {
		System.out.println("이전페이지 버튼");
		as.accountScreen(root, userDTO);

	}

	public void accountDeposit() {
		System.out.println("입금하기");
		as.updateAccountDeposit(root, userDTO);
		account();
	}
	
	public void deleteAccount() {
		System.out.println("계좌해지하기");
		as.deleteAccount(root, userDTO);
		account();
	}

}
