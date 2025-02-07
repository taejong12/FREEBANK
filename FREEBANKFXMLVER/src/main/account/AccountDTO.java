package main.account;

import java.sql.Date;

public class AccountDTO {
	private String accountAccount; // 계좌번호
	private int accountBalance; // 계좌잔고
	private Date accountCreate; // 계좌생성날짜
	private String accountId; // 회원아이디

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
