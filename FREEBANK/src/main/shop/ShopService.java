package main.shop;

public interface ShopService {

	public void shopInsert();					//쇼핑몰 등록
	public void shopUpdate(ShopDTO shopDTO);	//쇼핑몰 수정
	public void shopDelete(ShopDTO shopDTO);	//쇼핑몰 삭제
	public void shopList();						//쇼핑몰 리스트
	public void shopPayment();					//쇼핑몰 결제
	public void shopCart();						//쇼핑몰 장바구니
	
}
