package main.account; // 계좌개설 외 SQL문 확인 필요

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import main.user.UserDTO;

public class AccountServiceImpl implements AccountService {
	Scanner sc;
	AccountDAO accountDAO;
	List<AccountDTO> accountList;
	AccountDTO account;
	Random rd; // 난수생성을 위해 필요

	public AccountServiceImpl() {
		account = new AccountDTO();
		sc = new Scanner(System.in);
		accountDAO = new AccountDAO();
		accountList = new ArrayList<AccountDTO>();
		rd = new Random(); // 계좌번호 생성용 난수
	}

	// 계좌 메뉴선택
	public void accountMenu(UserDTO userDTO) {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("#### 계좌 페이지 ####");
			System.out.println("1. 계좌개설");
			System.out.println("2. 계좌조회");
			System.out.println("3. 계좌입금");
			System.out.println("4. 계좌출금");
			System.out.println("5. 계좌해지");
			System.out.println("0. 이전페이지");
			System.out.print("메뉴 선택 : ");

			int menu = sc.nextInt(); // 예외 처리된 입력 받기

			switch (menu) {
			case 1:
				// 계좌 개설
				createAccout(userDTO);
				break;
			case 2:
				// 계좌 조회
				selectAccount(userDTO);
				break;
			case 3:
				// 계좌 입금
				updateAccountDeposit(userDTO);
				break;
			case 4:
				// 계좌 출금
				updateAccountWithdrawal(userDTO);
				break;
			case 5:
				// 계좌 해지
				deleteAccount(userDTO);
				break;
			case 0:
				System.out.println("이전페이지");
				return;
			default:
				System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
			}
		}
	}

	// 계좌번호 생성과 중복체크
	public String createAccountAndDuplicationCheck() { // 계좌번호는 무작위로 자릿수는 7자리이고 양수
		int accountAccount = Math.abs(rd.nextInt() % 10000000); // 난수를 7자리로 설정하고 절대값 처리
		String account = String.valueOf(accountAccount);

		// 계좌 번호 중복 체크
		int accountCheck = accountDAO.selectAccount(account);

		// 계좌번호 중복 체크
		if (accountCheck > 0) {
			createAccountAndDuplicationCheck();
		}

		return account; // 참고로 x값의 절대값 처리는 Math.abs(x)
	}

	// 계좌개설
	public void createAccout(UserDTO userDTO) {
		System.out.println("#### 계좌 개설 페이지 ####");

		// 계좌번호 생성과 중복체크
		String accountAccount = createAccountAndDuplicationCheck();

		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setAccountAccount(accountAccount);
		accountDTO.setAccountId(userDTO.getUserId());

		// 계좌개설
		accountDAO.insertAccount(accountDTO);
		System.out.println("계좌 개설이 완료되었습니다.");
	}

	// 계좌조회
	public void selectAccount(UserDTO userDTO) {

		// 해당하는 회원의 모든 계좌 목록
		List<AccountDTO> accountDTOList = accountDAO.selectUserAccountAll(userDTO);

		if (accountDTOList.isEmpty()) {
			System.out.println("계좌가 존재하지 않습니다.");
			return;
		}

		System.out.println("#### 계좌 목록 출력 ####");
		for (int i = 0; i < accountDTOList.size(); i++) {
			System.out.println("선택번호: " + (i + 1));
			System.out.println("계좌번호: " + accountDTOList.get(i).getAccountAccount());
			System.out.println("============================================================");
		}

		System.out.println("조회할 계좌의 선택 번호를 선택해주세요.");
		System.out.println("0.취소");
		System.out.println("선택: ");

		int accountDtailMenu = sc.nextInt();

		if (accountDtailMenu < 1 || accountDtailMenu > accountDTOList.size()) {
			System.out.println("잘못된 선택입니다.");
			return;
		} else if (accountDtailMenu == 0) {
			System.out.println("취소");
			return;
		}

		int accountIndex = accountDtailMenu - 1;

		System.out.println("#### 계좌 조회 상세보기 ####");
		System.out.println("계좌번호: " + accountDTOList.get(accountIndex).getAccountAccount());
		System.out.println("계좌잔액: " + accountDTOList.get(accountIndex).getAccountBalance());
		System.out.println("계좌생성날짜: " + accountDTOList.get(accountIndex).getAccountCreate());
		System.out.println("회원아이디: " + accountDTOList.get(accountIndex).getAccountId());
		System.out.println("===============================================================");
	}

	// 계좌입금
	public void updateAccountDeposit(UserDTO userDTO) {
		System.out.println("#### 계좌 입금 페이지 ####");
		System.out.println("입금할 계좌 번호를 입력해주세요: ");
		String userAccount = sc.next();

		// 회원의 계좌번호 조회
		AccountDTO accountDTO = accountDAO.selectUserAccountByAccount(userAccount);

		if (accountDTO.getAccountAccount() == null) {
			System.out.println("해당하는 계좌번호가 존재하지 않습니다.");
			return;
		} else if (!accountDTO.getAccountId().equals(userDTO.getUserId())) {
			System.out.println("회원의 계좌번호가 아닙니다.");
			return;
		}

		System.out.println("입금할 금액 입력: ");
		int accountDepositMoney = sc.nextInt();

		System.out.println("회원의 비밀번호를 입력해주세요: ");
		String userPwdCheck = sc.next();

		// 회원 비밀번호 조회
		String userPwd = accountDAO.selectUserPwdCheck(userDTO.getUserId());

		if (userPwd == null) { // 비밀번호를 입력했으나 아이디가 존재하지 않는 경우
			System.out.println("존재하지 않는 비밀번호입니다.");
			return;
		} else if (!userPwdCheck.equals(userPwd)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		} else if (userPwdCheck.equals(userPwd)) {
			int totalUpdateDepositMoney = accountDTO.getAccountBalance() + accountDepositMoney;
			accountDTO.setAccountBalance(totalUpdateDepositMoney);

			// 계좌잔고 업데이트
			accountDAO.updateAccountBalance(accountDTO);

			System.out.println("계좌 입금 완료");
		}

	}

	// 계좌출금
	public void updateAccountWithdrawal(UserDTO userDTO) {
		System.out.println("#### 계좌 출금 페이지 ####");
		System.out.println("출금할 계좌 번호를 입력해주세요: ");
		String userAccount = sc.next();

		// 회원의 계좌번호 조회
		AccountDTO accountDTO = accountDAO.selectUserAccountByAccount(userAccount);

		if (accountDTO.getAccountAccount() == null) {
			System.out.println("해당하는 계좌번호가 존재하지 않습니다.");
			return;
		} else if (!accountDTO.getAccountId().equals(userDTO.getUserId())) {
			System.out.println("회원의 계좌번호가 아닙니다.");
			return;
		}

		System.out.println("출금할 금액 입력: ");
		int accountWithdrawalMoney = sc.nextInt();

		System.out.println("회원의 비밀번호를 입력해주세요: ");
		String userPwdCheck = sc.next();

		// 회원 비밀번호 조회
		String userPwd = accountDAO.selectUserPwdCheck(userDTO.getUserId());

		if (userPwd == null) { // 비밀번호를 입력했으나 아이디가 존재하지 않는 경우
			System.out.println("존재하지 않는 비밀번호입니다.");
			return;
		} else if (!userPwdCheck.equals(userPwd)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		} else if (userPwdCheck.equals(userPwd)) {
			if (accountWithdrawalMoney > accountDTO.getAccountBalance()) {
				System.out.println("출금할 잔고가 부족합니다.");
				return;
			} else {
				int totalUpdateWithdrawalMoney = accountDTO.getAccountBalance() - accountWithdrawalMoney;
				accountDTO.setAccountBalance(totalUpdateWithdrawalMoney);

				// 계좌잔고 업데이트
				accountDAO.updateAccountBalance(accountDTO);

				System.out.println("계좌 출금 완료");
			}
		}
	}

	// 계좌해지
	public void deleteAccount(UserDTO userDTO) {
		System.out.println("#### 계좌 해지 페이지 ####");

		// 해지 진행
		System.out.println("해지할 계좌 번호를 입력해주세요: ");
		String userAccount = sc.next();

		// 회원의 계좌번호 조회
		AccountDTO accountDTO = accountDAO.selectUserAccountByAccount(userAccount);

		if (accountDTO.getAccountAccount() == null) {
			System.out.println("해당하는 계좌번호가 존재하지 않습니다.");
			return;
		} else if (!accountDTO.getAccountId().equals(userDTO.getUserId())) {
			System.out.println("회원의 계좌번호가 아닙니다.");
			return;
		}

		System.out.println("회원의 비밀번호를 입력해주세요: ");
		String userPwdCheck = sc.next();

		// 회원 비밀번호 조회
		String userPwd = accountDAO.selectUserPwdCheck(userDTO.getUserId());

		if (userPwd == null) { // 비밀번호를 입력했으나 아이디가 존재하지 않는 경우
			System.out.println("존재하지 않는 비밀번호입니다.");
			return;
		} else if (!userPwdCheck.equals(userPwd)) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		} else if (userPwdCheck.equals(userPwd)) {

			// 계좌에 잔고가 있으면 삭제금지
			if (accountDTO.getAccountBalance() > 0) {
				System.out.println("계좌 잔고가 남아있습니다. 남은 금액을 출금해주세요.");
				return;
			}

			// 계좌 해지
			accountDAO.deleteAccount(userAccount);

			System.out.println("계좌 해지 완료");
		}

	}

}
