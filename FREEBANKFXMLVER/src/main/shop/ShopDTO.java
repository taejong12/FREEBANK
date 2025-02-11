package main.shop;

import java.sql.Date;

public class ShopDTO {

	// FREEBANKSHOP(상품) 상품 테이블 컬럼
	private int shopId; // 상품_고유번호(PK)
	private String shopName; // 상품_이름
	private String shopContents; // 상품_설명
	private int shopPrice; // 상품_가격
	private String shopAdminId; // 상품_관리자아이디
	private Date shopCreate; // 상품_등록일(기본값: 현재날짜)
	private Date shopUpdate; // 상품_수정일(기본값: 현재날짜)

	// 로그인 체크 유무
	private String shopUserId; // 상품 로그인 체크

	// 결제 체크
	private int shopTotalShopCount; // 총상품갯수
	private int shopTotalPayment; // 총결제금액

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopContents() {
		return shopContents;
	}

	public void setShopContents(String shopContents) {
		this.shopContents = shopContents;
	}

	public int getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(int shopPrice) {
		this.shopPrice = shopPrice;
	}

	public String getShopAdminId() {
		return shopAdminId;
	}

	public void setShopAdminId(String shopAdminId) {
		this.shopAdminId = shopAdminId;
	}

	public Date getShopCreate() {
		return shopCreate;
	}

	public void setShopCreate(Date shopCreate) {
		this.shopCreate = shopCreate;
	}

	public Date getShopUpdate() {
		return shopUpdate;
	}

	public void setShopUpdate(Date shopUpdate) {
		this.shopUpdate = shopUpdate;
	}

	public String getShopUserId() {
		return shopUserId;
	}

	public void setShopUserId(String shopUserId) {
		this.shopUserId = shopUserId;
	}

	public int getShopTotalShopCount() {
		return shopTotalShopCount;
	}

	public void setShopTotalShopCount(int shopTotalShopCount) {
		this.shopTotalShopCount = shopTotalShopCount;
	}

	public int getShopTotalPayment() {
		return shopTotalPayment;
	}

	public void setShopTotalPayment(int shopTotalPayment) {
		this.shopTotalPayment = shopTotalPayment;
	}
}
