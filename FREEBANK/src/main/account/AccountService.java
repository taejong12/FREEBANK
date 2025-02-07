package main.account;

import main.user.UserDTO;

public interface AccountService {
	void accountMenu(UserDTO userDTO); // 계좌 메뉴선택

	String createAccountAndDuplicationCheck(); // 계좌번호 생성/중복체크

	void createAccout(UserDTO userDTO); // 계좌개설

	void selectAccount(UserDTO usdrDTO); // 계좌조회

	void updateAccountDeposit(UserDTO userDTO); // 계좌입금

	void updateAccountWithdrawal(UserDTO userDTO); // 계좌출금

	void deleteAccount(UserDTO userDTO); // 계좌해지

}
