package main.shop;

import java.sql.Date;

public class PurchaseListDTO {
	
	//FREEBANKPURCHASELIST 구매내역 테이블 컬럼
	private int purchaseListId;						//구매내역 고유번호(PK)
	private String purchaseListUserId;				//구매내역 유저아이디
	private String purchaseListAccount;				//구매내역 계좌번호
	private int purchaseListShopId;					//구매내역 상품번호
	private String purchaseListShopName;			//구매내역 상품이름
	private int purchaseListTotalPayment;			//구매내역 총결제금액
	private int purchaseListTotalShopCount;			//구매내역 총상품갯수
	private Date purchaseListCreate;				//구매내역 결제날짜
	
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
	public int getPurchaseListTotalPayment() {
		return purchaseListTotalPayment;
	}
	public void setPurchaseListTotalPayment(int purchaseListTotalPayment) {
		this.purchaseListTotalPayment = purchaseListTotalPayment;
	}
	public int getPurchaseListTotalShopCount() {
		return purchaseListTotalShopCount;
	}
	public void setPurchaseListTotalShopCount(int purchaseListTotalShopCount) {
		this.purchaseListTotalShopCount = purchaseListTotalShopCount;
	}
	public Date getPurchaseListCreate() {
		return purchaseListCreate;
	}
	public void setPurchaseListCreate(Date purchaseListCreate) {
		this.purchaseListCreate = purchaseListCreate;
	}

}
