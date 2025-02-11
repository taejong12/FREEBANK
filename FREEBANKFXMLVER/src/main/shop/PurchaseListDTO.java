package main.shop;

import java.sql.Date;

public class PurchaseListDTO {

	// FREEBANKPURCHASELIST(구매내역) 테이블 컬럼
	private int purchaseListId; // 구매내역_고유번호(PK)
	private String purchaseListUserId; // 구매내역_유저아이디
	private String purchaseListAccount; // 구매내역_계좌번호
	private int purchaseListShopId; // 구매내역_상품번호
	private String purchaseListShopName; // 구매내역_상품이름
	private int purchaseListTotalpayment; // 구매내역_총결제금액
	private int purchaseListTotalshopcount; // 구매내역_총상품갯수
	private Date purchaseListCreate; // 구매내역_결제일(기본값: 현재날짜)

	public int getPurchaseListId() {
		return purchaseListId;
	}

	public void setPurchaseListId(int purchaseListId) {
		this.purchaseListId = purchaseListId;
	}

	public String getPurchaseListUserId() {
		return purchaseListUserId;
	}

	public void setPurchaseListUserId(String purchaseListUserId) {
		this.purchaseListUserId = purchaseListUserId;
	}

	public String getPurchaseListAccount() {
		return purchaseListAccount;
	}

	public void setPurchaseListAccount(String purchaseListAccount) {
		this.purchaseListAccount = purchaseListAccount;
	}

	public int getPurchaseListShopId() {
		return purchaseListShopId;
	}

	public void setPurchaseListShopId(int purchaseListShopId) {
		this.purchaseListShopId = purchaseListShopId;
	}

	public String getPurchaseListShopName() {
		return purchaseListShopName;
	}

	public void setPurchaseListShopName(String purchaseListShopName) {
		this.purchaseListShopName = purchaseListShopName;
	}

	public int getPurchaseListTotalpayment() {
		return purchaseListTotalpayment;
	}

	public void setPurchaseListTotalpayment(int purchaseListTotalpayment) {
		this.purchaseListTotalpayment = purchaseListTotalpayment;
	}

	public int getPurchaseListTotalshopcount() {
		return purchaseListTotalshopcount;
	}

	public void setPurchaseListTotalshopcount(int purchaseListTotalshopcount) {
		this.purchaseListTotalshopcount = purchaseListTotalshopcount;
	}

	public Date getPurchaseListCreate() {
		return purchaseListCreate;
	}

	public void setPurchaseListCreate(Date purchaseListCreate) {
		this.purchaseListCreate = purchaseListCreate;
	}

}
