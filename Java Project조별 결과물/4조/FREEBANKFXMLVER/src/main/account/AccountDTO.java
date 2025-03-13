package main.account;

import java.sql.Date;

public class AccountDTO {

	// FREEBANKACCOUNT(계좌) 테이블 컬럼
	private String accountAccount; // 계좌_번호(PK)
	private int accountBalance; // 계좌_잔고(기본값: 0)
	private Date accountCreate; // 계좌_생성일(기본값: 현재날짜)
	private String accountId; // 회원_아이디(FK)

	public String getAccountAccount() {
		return accountAccount;
	}

	public void setAccountAccount(String accountAccount) {
		this.accountAccount = accountAccount;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Date getAccountCreate() {
		return accountCreate;
	}

	public void setAccountCreate(Date accountCreate) {
		this.accountCreate = accountCreate;
	}

}
