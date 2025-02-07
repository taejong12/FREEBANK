package main.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

	@Override
	public void updateAccountDeposit(UserDTO userDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAccountWithdrawal(UserDTO userDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAccount(UserDTO userDTO) {
		// TODO Auto-generated method stub

	}

	// 계좌 화면
	public void accountScreen(Parent root, UserDTO userDTO) {

		Stage accountScreen = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/accountScreen.fxml"));

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

	//계좌 목록 화면
	public void selectAccountListScreen(Parent root, UserDTO userDTO) {
		
		Stage accountListScreen = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/accountListScreen.fxml"));

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

	//계좌번호 목록 조회
	public List<AccountDTO> selectUserAccountByID(String userId) {

		return ad.selectUserAccountByID(userId);
	}

}
