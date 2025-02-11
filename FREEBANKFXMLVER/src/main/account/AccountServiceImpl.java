package main.account;

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

		if (accountDTOList.size() >= 3) {

			Alert alertError = new Alert(AlertType.ERROR);
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

				Alert alertInfo = new Alert(AlertType.INFORMATION);
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

				Alert alertError = new Alert(AlertType.ERROR);
				alertError.setTitle("계좌 생성 실패");
				alertError.setHeaderText(null);
				alertError.setContentText("계좌 개설 실패. 문의 바람.");

				// 확인 버튼을 누를 때까지 대기
				alertError.showAndWait();

			}
		}

	}

	// 계좌입금하기
	public void updateAccountDeposit(Parent root, UserDTO userDTO) {

		// 아이디
		String userIdCheck = userDTO.getUserId();

		// 비밀번호
		String userPwdCheck = userDTO.getUserPwd();

		// 계좌목록
		List<AccountDTO> accountList = ad.selectUserAccountById(userDTO.getUserId());

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
		List<AccountDTO> accountList = ad.selectUserAccountById(userDTO.getUserId());

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
		List<AccountDTO> accountList = ad.selectUserAccountById(userDTO.getUserId());

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

	// 계좌페이지 출력
	public void accountPage(Parent root, UserDTO userDTO) {

		Stage accountPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/account/accountPage.fxml"));

		try {

			Parent accountPageRoot = loader.load();

			AccountController accountPageCtrl = loader.getController();

			accountPageCtrl.setRoot(accountPageRoot);
			accountPageCtrl.setUser(userDTO);

			accountPage.setScene(new Scene(accountPageRoot));
			accountPage.setTitle("계좌페이지");
			accountPage.show();

			accountPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("계좌페이지 출력 실패");
			e.printStackTrace();
		}

	}

	// 계좌목록페이지 출력
	public void selectAccountListPage(Parent root, UserDTO userDTO) {

		Stage selectAccountListPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/account/selectAccountListPage.fxml"));

		try {

			Parent selectAccountListPageRoot = loader.load();

			AccountController selectAccountListPageCtrl = loader.getController();

			selectAccountListPageCtrl.setRoot(selectAccountListPageRoot);
			selectAccountListPageCtrl.setUser(userDTO);
			// 계좌 리스트 출력하기
			selectAccountListPageCtrl.selectAccountList();

			selectAccountListPage.setScene(new Scene(selectAccountListPageRoot));
			selectAccountListPage.setTitle("계좌목록페이지");
			selectAccountListPage.show();

			selectAccountListPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("계좌목록페이지 출력 실패)");
			e.printStackTrace();
		}

	}

	// 계좌번호 목록 조회
	public List<AccountDTO> selectUserAccountByID(String userId) {
		return ad.selectUserAccountById(userId);
	}

	// 계좌입금 페이지 출력
	public void updateAccountDepositPage(Parent root, UserDTO userDTO) {

		Stage updateAccountDepositPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/account/updateAccountDepositPage.fxml"));

		try {

			Parent updateAccountDepositPageRoot = loader.load();

			AccountController updateAccountDepositPageCtrl = loader.getController();

			updateAccountDepositPageCtrl.setRoot(updateAccountDepositPageRoot);
			updateAccountDepositPageCtrl.setUser(userDTO);

			updateAccountDepositPage.setScene(new Scene(updateAccountDepositPageRoot));
			updateAccountDepositPage.setTitle("계좌입금 페이지");
			updateAccountDepositPage.show();

			updateAccountDepositPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("계좌입금 페이지 출력 실패");
			e.printStackTrace();
		}

	}

	// 계좌출금 페이지 출력
	public void updateAccountWithdrawalPage(Parent root, UserDTO userDTO) {

		Stage updateAccountWithdrawalPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/main/fxml/account/updateAccountWithdrawalPage.fxml"));

		try {

			Parent accountWithdrawalRoot = loader.load();

			AccountController accountWithdrawalCtrl = loader.getController();

			accountWithdrawalCtrl.setRoot(accountWithdrawalRoot);
			accountWithdrawalCtrl.setUser(userDTO);

			updateAccountWithdrawalPage.setScene(new Scene(accountWithdrawalRoot));
			updateAccountWithdrawalPage.setTitle("계좌출금 페이지");
			updateAccountWithdrawalPage.show();

			accountWithdrawalCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("계좌출금 페이지 출력 실패");
			e.printStackTrace();
		}

	}

	// 계좌해지 페이지 출력
	public void deleteAccountPage(Parent root, UserDTO userDTO) {

		Stage deleteAccountPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/account/deleteAccountPage.fxml"));

		try {

			Parent deleteAccountPageRoot = loader.load();

			AccountController deleteAccountPageCtrl = loader.getController();

			deleteAccountPageCtrl.setRoot(deleteAccountPageRoot);
			deleteAccountPageCtrl.setUser(userDTO);

			deleteAccountPage.setScene(new Scene(deleteAccountPageRoot));
			deleteAccountPage.setTitle("계좌해지 페이지");
			deleteAccountPage.show();

			deleteAccountPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("계좌해지 페이지 출력 실패");
			e.printStackTrace();
		}

	}

}
