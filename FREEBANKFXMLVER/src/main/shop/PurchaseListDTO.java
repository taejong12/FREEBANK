package main.shop;

import java.sql.Date;

public class PurchaseListDTO {

	// FREEBANKPURCHASELIST(구매내역) 테이블 컬럼
	private int PListId; // 구매내역_고유번호(PK)
	private String PListUserId; // 구매내역_유저아이디
	private String PListAccount; // 구매내역_계좌번호
	private int PListShopId; // 구매내역_상품번호
	private String PListShopName; // 구매내역_상품이름
	private int PListTotalpayment; // 구매내역_총결제금액
	private int PListTotalshopcount; // 구매내역_총상품갯수
	private Date PListCreate; // 구매내역_결제일(기본값: 현재날짜)

	public int getPListId() {
		return PListId;
	}

	public void setPListId(int pListId) {
		PListId = pListId;
	}

	public String getPListUserId() {
		return PListUserId;
	}

	public void setPListUserId(String pListUserId) {
		PListUserId = pListUserId;
	}

	public String getPListAccount() {
		return PListAccount;
	}

	public void setPListAccount(String pListAccount) {
		PListAccount = pListAccount;
	}

	public int getPListShopId() {
		return PListShopId;
	}

	public void setPListShopId(int pListShopId) {
		PListShopId = pListShopId;
	}

	public String getPListShopName() {
		return PListShopName;
	}

	public void setPListShopName(String pListShopName) {
		PListShopName = pListShopName;
	}

	public int getPListTotalpayment() {
		return PListTotalpayment;
	}

	public void setPListTotalpayment(int pListTotalpayment) {
		PListTotalpayment = pListTotalpayment;
	}

	public int getPListTotalshopcount() {
		return PListTotalshopcount;
	}

	public void setPListTotalshopcount(int pListTotalshopcount) {
		PListTotalshopcount = pListTotalshopcount;
	}

	public Date getPListCreate() {
		return PListCreate;
	}

	public void setPListCreate(Date pListCreate) {
		PListCreate = pListCreate;
	}

}
