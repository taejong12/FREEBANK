package main.shop;

import java.sql.Date;

public class ShopDTO {

	private int shopId;				//상품 고유번호(PK)	
	private String shopName;		//상품명
	private String shopContents;	//상품설명
	private int shopPrice;			//상품가격
	private String shopAdminId;		//상품관리자
	private Date shopCreate;		//상품등록일(기본값: 현재 날짜)
	private Date shopUpdate;		//상품수정일(기본값: 현재 날짜)
	
	
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
	
	
}
