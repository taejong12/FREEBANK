package main.pay;

import java.sql.Date;

public class Dto {
	private int deliveryNum;
	private Date payDate;
	private String coupon;
	private int cardmoney; 
	private String cardnum;
	private int sum;
	
	
	public int getCardmoney() {
		return cardmoney;
	}
	public void setCardmoney(int total) {
		this.cardmoney = total;
	}
	public int getDeliveryNum() {
		return deliveryNum;
	}
	public void setDeliveryNum(int deliveryNum) {
		this.deliveryNum = deliveryNum;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public  String getCardnum() {

		return cardnum;
	}
	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	
	

}