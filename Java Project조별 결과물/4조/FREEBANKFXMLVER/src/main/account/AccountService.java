package main.account;

import java.util.List;

import javafx.scene.Parent;
import main.user.UserDTO;

public interface AccountService {

	String createAccountAndDuplicationCheck(); // 계좌번호 생성과 중복체크

	void insertAccount(UserDTO userDTO); // 계좌개설

	void updateAccountDeposit(Parent root, UserDTO userDTO); // 계좌입금

	void updateAccountWithdrawal(Parent root, UserDTO userDTO); // 계좌출금

	void deleteAccount(Parent root, UserDTO userDTO); // 계좌해지

	void accountPage(Parent root, UserDTO userDTO); // 계좌페이지 출력

	void accountListSelectPage(Parent root, UserDTO userDTO); // 계좌목록 페이지 출력

	List<AccountDTO> selectUserAccountByID(String userId); // 유저의 계좌번호 목록 조회

	void accountDepositUpdatePage(Parent root, UserDTO userDTO); // 계좌입금 페이지 출력

	void accountWithdrawalUpdatePage(Parent root, UserDTO userDTO); // 계좌출금 페이지 출력

	void accountDeletePage(Parent root, UserDTO userDTO); // 계좌해지 페이지 출력

}
