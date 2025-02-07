package main.shop;

import main.user.UserDTO;

public interface ShopService {
	void selectShopList(UserDTO userDTO);				//상품목록 조회
	void shopPayment(ShopDTO shopDTO);					//상품결제 페이지
	void shopPaymentProcess(ShopDTO shopDTO);			//상품결제 시스템
}
