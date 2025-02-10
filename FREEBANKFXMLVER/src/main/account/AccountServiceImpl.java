package main.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.menu.MenuController;
import main.user.UserDTO;

public class AccountServiceImpl implements AccountService {

	AccountDAO ad = new AccountDAO();
	Random rd = new Random();

	// 계좌번호 생성과 중복체크
	public String createAccountAndDuplicationCheck() { // 계좌번호는 무작위로 자릿수는 7자리이고 양수
		int accountAccount = rd.nextInt(10000000); // 난수를 7자리로 설정하고 절대값 처리
		String account = String.valueOf(accountAccount);

		// 계좌 번호 중복 체크
		int accountCheck = ad.selectAccount(account);

		// 계좌번호 중복 체크
		if (accountCheck > 0) {
			createAccountAndDuplicationCheck();
		}

		return account; // 참고로 x값의 절대값 처리는 Math.abs(x)
	}

	// 계좌 개설
	public void insertAccount(UserDTO userDTO) {

		// 한 계정당 계좌 개설 3개까지만 가능
		List<AccountDTO> accountDTOList = selectUserAccountByID(userDTO.getUserId());
		Alert alertError = new Alert(AlertType.ERROR);
		Alert alertInfo = new Alert(AlertType.INFORMATION);

		if (accountDTOList.size() >= 3) {

			alertError.setTitle("계좌 개설 제한");
			alertError.setHeaderText(null);
			alertError.setContentText("계좌 3개까지만 개설 가능");

			// 확인 버튼을 누를 때까지 대기
			alertError.showAndWait();

		} else {

			// 계좌번호 생성과 중복체크
			String accountAccount = createAccountAndDuplicationCheck();

			AccountDTO accountDTO = new AccountDTO();
			accountDTO.setAccountAccount(accountAccount);
			accountDTO.setAccountId(userDTO.getUserId());

			// 계좌개설
			int result = ad.insertAccount(accountDTO);

			if (result >= 1) {

				alertInfo.setTitle("계좌 생성 완료");
				// 헤더 제거
				alertInfo.setHeaderText(null);

				// 내용
				String insertAccountSuccessMessage = "회원아이디: " + userDTO.getUserId() + "\n" + "계좌번호: " + accountAccount
						+ "\n" + "계좌 개설이 완료되었습니다.";

				alertInfo.setContentText(insertAccountSuccessMessage);

				// 확인 버튼을 누를 때까지 대기
				alertInfo.showAndWait();

			} else {

				alertError.setTitle("계좌 생성 실패");
				alertError.setHeaderText(null);
				alertError.setContentText("계좌 개설 실패. 문의 바람.");

				// 확인 버튼을 누를 때까지 대기
				alertError.showAndWait();

			}
		}

	}

	@Override
	public void selectAccount(UserDTO usdrDTO) {
		// TODO Auto-generated method stub

	}

	// 계좌입금하기
	public void updateAccountDeposit(Parent root, UserDTO userDTO) {

		// 아이디
		String userIdCheck = userDTO.getUserId();

		// 비밀번호
		String userPwdCheck = userDTO.getUserPwd();

		// 계좌목록
		List<AccountDTO> accountList = ad.selectUserAccountByID(userDTO.getUserId());

		TextField textAccount = (TextField) root.lookup("#account");
		String account = textAccount.getText();

		TextField textId = (TextField) root.lookup("#userId");
		String userId = textId.getText();

		PasswordField textPwd = (PasswordField) root.lookup("#userPwd");
		String userPwd = textPwd.getText();

		// 입금금액
		TextField textDepositAmount = (TextField) root.lookup("#depositAmount");
		int depositAmount = Integer.parseInt(textDepositAmount.getText());

		boolean accountCheck = true;

		for (AccountDTO accountDTO : accountList) {
			if (accountDTO.getAccountAccount().equals(account)) {
				System.out.println("일치하는 계좌 번호 존재");
				if (userIdCheck.equals(userId) && userPwdCheck.equals(userPwd)) {
					// 계좌번호랑 아이디랑 비밀번호가 일치해야함
					accountCheck = false;

					int accountBalanceSum = depositAmount + accountDTO.getAccountBalance();

					// 기존 잔고에 입금 금액 추가
					accountDTO.setAccountBalance(accountBalanceSum);

					ad.updateAccountBalance(accountDTO);

					Alert alertInfo = new Alert(AlertType.INFORMATION);

					alertInfo.setTitle("계좌입금완료");
					alertInfo.setHeaderText(null);
					alertInfo.setContentText("계좌입금완료");

					// 확인 버튼을 누를 때까지 대기
					alertInfo.showAndWait();

				}

			}

		}

		if (accountCheck) {

			// 알럿
			Alert alertError = new Alert(AlertType.ERROR);

			alertError.setTitle("계좌입금실패");
			alertError.setHeaderText(null);
			alertError.setContentText("계좌입금실패");

			// 확인 버튼을 누를 때까지 대기
			alertError.showAndWait();

		}

	}

	// 계좌출금하기
	public void updateAccountWithdrawal(Parent root, UserDTO userDTO) {

		// 아이디
		String userIdCheck = userDTO.getUserId();

		// 비밀번호
		String userPwdCheck = userDTO.getUserPwd();

		// 계좌목록
		List<AccountDTO> accountList = ad.selectUserAccountByID(userDTO.getUserId());

		TextField textAccount = (TextField) root.lookup("#account");
		String account = textAccount.getText();

		TextField textId = (TextField) root.lookup("#userId");
		String userId = textId.getText();

		PasswordField textPwd = (PasswordField) root.lookup("#userPwd");
		String userPwd = textPwd.getText();

		// 출금금액
		TextField textWithdrawalAmount = (TextField) root.lookup("#withdrawalAmount");
		int withdrawalAmount = Integer.parseInt(textWithdrawalAmount.getText());

		boolean accountCheck = true;

		for (AccountDTO accountDTO : accountList) {
			if (accountDTO.getAccountAccount().equals(account)) {
				System.out.println("일치하는 계좌 번호 존재");
				if (userIdCheck.equals(userId) && userPwdCheck.equals(userPwd)) {
					// 잔고가 적으면 안됨
					if (accountDTO.getAccountBalance() >= withdrawalAmount) {

						// 계좌번호랑 아이디랑 비밀번호가 일치해야함
						accountCheck = false;

						int accountBalanceSum = accountDTO.getAccountBalance() - withdrawalAmount;

						// 기존 잔고에 입금 금액 추가
						accountDTO.setAccountBalance(accountBalanceSum);

						ad.updateAccountBalance(accountDTO);

						Alert alertInfo = new Alert(AlertType.INFORMATION);

						alertInfo.setTitle("계좌출금완료");
						alertInfo.setHeaderText(null);
						alertInfo.setContentText("계좌출금완료");

						// 확인 버튼을 누를 때까지 대기
						alertInfo.showAndWait();
					}

				}

			}

		}

		if (accountCheck) {

			// 알럿
			Alert alertError = new Alert(AlertType.ERROR);

			alertError.setTitle("계좌출금실패");
			alertError.setHeaderText(null);
			alertError.setContentText("계좌출금실패");

			// 확인 버튼을 누를 때까지 대기
			alertError.showAndWait();

		}
	}

	// 계좌 해지하기
	public void deleteAccount(Parent root, UserDTO userDTO) {

		// 아이디
		String userIdCheck = userDTO.getUserId();

		// 비밀번호
		String userPwdCheck = userDTO.getUserPwd();

		// 계좌목록
		List<AccountDTO> accountList = ad.selectUserAccountByID(userDTO.getUserId());

		TextField textAccount = (TextField) root.lookup("#account");
		String account = textAccount.getText();

		TextField textId = (TextField) root.lookup("#userId");
		String userId = textId.getText();

		PasswordField textPwd = (PasswordField) root.lookup("#userPwd");
		String userPwd = textPwd.getText();

		boolean accountCheck = true;

		for (AccountDTO accountDTO : accountList) {
			if (accountDTO.getAccountAccount().equals(account)) {
				System.out.println("일치하는 계좌 번호 존재");
				if (userIdCheck.equals(userId) && userPwdCheck.equals(userPwd)) {
					// 잔고가 존재하면 안됨
					if (accountDTO.getAccountBalance() < 1) {

						// 계좌번호랑 아이디랑 비밀번호가 일치해야함
						accountCheck = false;

						ad.deleteAccount(accountDTO.getAccountAccount());

						Alert alertInfo = new Alert(AlertType.INFORMATION);

						alertInfo.setTitle("계좌해지완료");
						alertInfo.setHeaderText(null);
						alertInfo.setContentText("계좌해지완료");

						// 확인 버튼을 누를 때까지 대기
						alertInfo.showAndWait();
					}

				}

			}

		}

		if (accountCheck) {

			// 알럿
			Alert alertError = new Alert(AlertType.ERROR);

			alertError.setTitle("계좌해지실패");
			alertError.setHeaderText(null);
			alertError.setContentText("계좌해지실패");

			// 확인 버튼을 누를 때까지 대기
			alertError.showAndWait();

		}

	}

	// 계좌 화면
	public void accountScreen(Parent root, UserDTO userDTO) {

		Stage accountScreen = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/account/accountScreen.fxml"));

		try {

			Parent accountRoot = loader.load();

			AccountController accountCtrl = loader.getController();

			accountCtrl.setRoot(accountRoot);
			accountCtrl.setUser(userDTO);

			accountScreen.setScene(new Scene(accountRoot));
			accountScreen.setTitle("계좌화면페이지");
			accountScreen.show();

			accountCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("AccountServiceImp.accountScreen 에러(계좌 화면 출력 실패)");
			e.printStackTrace();
		}

	}

	// 계좌 목록 화면
	public void selectAccountListScreen(Parent root, UserDTO userDTO) {

		Stage accountListScreen = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/account/accountListScreen.fxml"));

		try {

			Parent accountListRoot = loader.load();

			AccountController accountListCtrl = loader.getController();

			accountListCtrl.setRoot(accountListRoot);
			accountListCtrl.setUser(userDTO);
			accountListCtrl.outputAccountList();

			accountListScreen.setScene(new Scene(accountListRoot));
			accountListScreen.setTitle("계좌 목록 페이지");
			accountListScreen.show();

			accountListCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("AccountServiceImp.selectAccountListScreen 에러(계좌 화면 출력 실패)");
			e.printStackTrace();
		}

	}

	// 계좌번호 목록 조회
	public List<AccountDTO> selectUserAccountByID(String userId) {

		return ad.selectUserAccountByID(userId);
	}

	// 계좌입금 화면
	public void updateAccountDepositScreen(Parent root, UserDTO userDTO) {

		Stage accountDepositScreen = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/account/accountDepositScreen.fxml"));

		try {

			Parent accountDepositRoot = loader.load();

			AccountController accountDepositCtrl = loader.getController();

			accountDepositCtrl.setRoot(accountDepositRoot);
			accountDepositCtrl.setUser(userDTO);

			accountDepositScreen.setScene(new Scene(accountDepositRoot));
			accountDepositScreen.setTitle("계좌 입금 페이지");
			accountDepositScreen.show();

			accountDepositCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("AccountServiceImp.updateAccountDepositScreen 에러(계좌입금화면)");
			e.printStackTrace();
		}

	}

	// 계좌출금 화면
	public void updateAccountWithdrawalScreen(Parent root, UserDTO userDTO) {

		Stage accountWithdrawalScreen = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/account/accountWithdrawalScreen.fxml"));

		try {

			Parent accountWithdrawalRoot = loader.load();

			AccountController accountWithdrawalCtrl = loader.getController();

			accountWithdrawalCtrl.setRoot(accountWithdrawalRoot);
			accountWithdrawalCtrl.setUser(userDTO);

			accountWithdrawalScreen.setScene(new Scene(accountWithdrawalRoot));
			accountWithdrawalScreen.setTitle("계좌 출금 페이지");
			accountWithdrawalScreen.show();

			accountWithdrawalCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("AccountServiceImp.updateAccountWithdrawalScreen 에러(계좌출금화면)");
			e.printStackTrace();
		}

	}

	// 계좌해지화면
	public void deleteAccountScreen(Parent root, UserDTO userDTO) {

		Stage deleteAccountScreen = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/account/deleteAccountScreen.fxml"));

		try {

			Parent deleteAccountRoot = loader.load();

			AccountController deleteAccountCtrl = loader.getController();

			deleteAccountCtrl.setRoot(deleteAccountRoot);
			deleteAccountCtrl.setUser(userDTO);

			deleteAccountScreen.setScene(new Scene(deleteAccountRoot));
			deleteAccountScreen.setTitle("계좌 해지 페이지");
			deleteAccountScreen.show();

			deleteAccountCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("AccountServiceImp.deleteAccountScreen 에러(계좌해지화면)");
			e.printStackTrace();
		}

	}

}
