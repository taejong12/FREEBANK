package main.account;

import java.util.List;

import javafx.scene.Parent;
import main.user.UserDTO;

public interface AccountService {

	String createAccountAndDuplicationCheck(); // 계좌번호 생성/중복체크

	void insertAccount(UserDTO userDTO); // 계좌개설

	void selectAccount(UserDTO usdrDTO); // 계좌조회

	void updateAccountDeposit(Parent root, UserDTO userDTO); // 계좌입금

	void updateAccountWithdrawal(Parent root, UserDTO userDTO); // 계좌출금

	void deleteAccount(Parent root, UserDTO userDTO); // 계좌해지

	void accountScreen(Parent root, UserDTO userDTO);	//계좌 메뉴 페이지

	void selectAccountListScreen(Parent root, UserDTO userDTO);	//계좌 리스트 조회 화면

	List<AccountDTO> selectUserAccountByID(String userId);	//유저의 계좌번호 목록 조회

	void updateAccountDepositScreen(Parent root, UserDTO userDTO);	//계좌입금 화면

	void updateAccountWithdrawalScreen(Parent root, UserDTO userDTO);	//계좌출금 화면

	void deleteAccountScreen(Parent root, UserDTO userDTO);	//계좌해지 화면


}
