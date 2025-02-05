package main.shop;

import main.user.UserDTO;

public interface ShopService {

	void shopList(UserDTO userDTO);				//쇼핑몰 상품 페이지
	void shopPayment(ShopDTO shopDTO);			//쇼핑몰 결제
	void shopPaymentProcess(ShopDTO shopDTO);	//상품 결제 시스템

}
