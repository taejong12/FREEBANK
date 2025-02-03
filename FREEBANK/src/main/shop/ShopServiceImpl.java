package main.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShopServiceImpl implements ShopService{
	
	Scanner sc = new Scanner(System.in);
	ShopDAO shopDAO = new ShopDAO();

	@Override
	public void shopInsert() {
		System.out.println("####상품등록 페이지####");
		
		ShopDTO shopDTO = new ShopDTO();
		
		System.out.println("상품명 입력: ");
		shopDTO.setShopName(sc.next());
		System.out.println("상품 설명 입력: ");
		shopDTO.setShopContents(sc.next());
		System.out.println("상품 가격 입력: ");
		shopDTO.setShopPrice(sc.nextInt());
		
		shopDTO.setShopAdminId("관리자아이디");
		
		shopDAO.shopInsert(shopDTO);

	}

	@Override
	public void shopUpdate(ShopDTO shopDTO) {

		System.out.println("####상품수정 페이지####");
		
		System.out.println("상품명 입력: ");
		shopDTO.setShopName(sc.next());
		
		System.out.println("상품설명 입력: ");
		shopDTO.setShopContents(sc.next());
		
		System.out.println("상품가격 입력: ");
		shopDTO.setShopPrice(sc.nextInt());
		
		shopDAO.shopUpdate(shopDTO);
		
		
	}

	@Override
	public void shopDelete(ShopDTO shopDTO) {
		System.out.println("####상품삭제 페이지####");
		System.out.println("정말로 삭제하시겠습니까?");
		System.out.println("1.예");
		System.out.println("2.아니오");
		System.out.println("숫자 선택:");
		int menu = sc.nextInt();
		
		switch(menu) {
			case 1:
				shopDAO.shopDeleteId(shopDTO.getShopId());
				break;
			case 2:
				System.out.println("삭제취소");
				break;
			default:
				System.out.println("없는 번호입니다.");
				break;
		}
	}

	@Override
	public void shopList() {
		System.out.println("####상품리스트 페이지####");
		
		List<ShopDTO> shopList = new ArrayList<ShopDTO>();
		
		shopList = shopDAO.shopSelectAll();
		
		for(int i=0;i<shopList.size();i++) {
			System.out.println("상품번호: "+shopList.get(i).getShopId());
			System.out.println("상품명: " + shopList.get(i).getShopName());
			System.out.println("상품가격: "+shopList.get(i).getShopPrice());
			System.out.println("================================");
		}
		
		System.out.println("0.취소");
		System.out.println("상품번호를 입력해주세요: ");

		int shopMenu = sc.nextInt();
		
		//상품 존재 유무 채크
		boolean shopCheck = false;
		
		for(ShopDTO shopDTO : shopList) {
			
			if(shopMenu==shopDTO.getShopId()) {
				shopCheck = true;
				System.out.println("####상품상세페이지####");
				System.out.println("상품번호: " + shopDTO.getShopId());
				System.out.println("상품명: " + shopDTO.getShopName());
				System.out.println("상품설명: " + shopDTO.getShopContents());
				System.out.println("상품가격: " + shopDTO.getShopPrice());
				System.out.println("상품관리자: " + shopDTO.getShopAdminId());
				System.out.println("상품등록일: " + shopDTO.getShopCreate());
				System.out.println("상품수정일: " + shopDTO.getShopUpdate());
				System.out.println("====================================");
				System.out.println("1.상품 결제(미구현)");
				System.out.println("2.상품 수정");
				System.out.println("3.상품 삭제");
				System.out.println("4.장바구니 담기(미구현)");
				System.out.println("0.취소");
				System.out.println("선택: ");
				
				//관리자 추가 설정 필요(관리자일 경우에만 상품 수정/삭제 창 보이기)
				//아니면 나중에 관리자용 상세페이지/유저용 상세페이지 별도로 만들기
				//유저면 유저서비스 / 관리자면 관리자서비스
				
				int menu = sc.nextInt();
				
				switch(menu){
					case 1:
						//잔고에 있는 금액 가져오기
						//돈이 있으면 해당하는 금액 결제 후 차액 저장
						//금액이 부족하면 구매 불가
						shopPayment();
						break;
					case 2:
						shopUpdate(shopDTO);
						break;
					case 3:
						shopDelete(shopDTO);
						break;
					case 4:
						//아직 미구현
						//할지 말지 고민
						shopCart();
						break;
					case 0:
						System.out.println("취소");
						break;
					default:
						System.out.println("없는 번호입니다.");
						break;
				}

			}else if(shopMenu==0) {
				System.out.println("구매취소");
				return;
			}
		}
		
		if(!shopCheck) {
			System.out.println("해당하는 상품번호가 존재하지 않습니다.");
		}

	}

	@Override
	public void shopPayment() {
		System.out.println("####상품 결제 페이지####");
		
	}

	@Override
	public void shopCart() {
		System.out.println("####장바구니 페이지####");
		
	}




	
}
