package main.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.account.AccountDTO;
import main.user.UserDTO;

public class ShopServiceImpl implements ShopService {

	Scanner scanner = new Scanner(System.in);
	ShopDAO shopDAO = new ShopDAO();

	// 상품목록 조회
	public void selectShopList(UserDTO userDTO) {
		System.out.println("####상품 목록####");

		List<ShopDTO> shopList = new ArrayList<ShopDTO>();

		// 상품목록 전체조회
		shopList = shopDAO.shopSelectAll();

		for (int i = 0; i < shopList.size(); i++) {
			System.out.println("상품번호: " + shopList.get(i).getShopId());
			System.out.println("상품명: " + shopList.get(i).getShopName());
			System.out.println("상품가격: " + shopList.get(i).getShopPrice());
			System.out.println("================================");
		}

		System.out.println("0.취소");
		System.out.println("상품번호를 입력해주세요: ");

		int shopListMenu = scanner.nextInt();

		// 상품 존재 유무 채크
		boolean shopCheck = false;

		for (ShopDTO shopDTO : shopList) {

			if (shopListMenu == shopDTO.getShopId()) {
				shopCheck = true;
				System.out.println("#### 상품상세페이지 ####");
				System.out.println("상품번호: " + shopDTO.getShopId());
				System.out.println("상품명: " + shopDTO.getShopName());
				System.out.println("상품설명: " + shopDTO.getShopContents());
				System.out.println("상품가격: " + shopDTO.getShopPrice());
				System.out.println("상품관리자: " + shopDTO.getShopAdminId());
				System.out.println("상품등록일: " + shopDTO.getShopCreate());
				System.out.println("상품수정일: " + shopDTO.getShopUpdate());
				System.out.println("====================================");
				System.out.println("1.상품 결제");
				System.out.println("0.취소");
				System.out.println("선택: ");

				int shopDetailMenu = scanner.nextInt();

				switch (shopDetailMenu) {
				case 1:
					// 1.상품결제
					if (userDTO.getUserId() != null) {
						shopDTO.setShopUserId(userDTO.getUserId());
						shopPayment(shopDTO);
						break;
					} else {
						System.out.println("로그인이 필요한 기능입니다. 로그인해주세요.");
						return;
					}
				case 0:
					System.out.println("취소");
					break;
				default:
					System.out.println("없는 번호입니다.");
					break;
				}

			} else if (shopListMenu == 0) {
				System.out.println("구매취소");
				return;
			}

		}

		if (!shopCheck) {
			System.out.println("해당하는 상품번호가 존재하지 않습니다.");
		}

	}

	// 상품결제 페이지
	public void shopPayment(ShopDTO shopDTO) {
		System.out.println("####상품 결제 페이지####");
		System.out.println("수량 선택: ");
		int totalShopCount = scanner.nextInt();

		// 총 결제 금액
		int totalPayment = totalShopCount * shopDTO.getShopPrice();

		System.out.println("총 결제금액: " + totalPayment + "원");
		System.out.println("결제하시겠습니까?");
		System.out.println("1.예");
		System.out.println("2.아니오");
		System.out.println("선택: ");

		int paymentMenu = scanner.nextInt();

		switch (paymentMenu) {
		case 1:
			// 상품결제 시스템
			shopDTO.setShopTotalPayment(totalPayment);
			shopDTO.setShopTotalShopCount(totalShopCount);
			shopPaymentProcess(shopDTO);
			break;
		case 2:
			System.out.println("결제취소");
			return;
		default:
			System.out.println("잘못 입력하셨습니다");
		}

	}

	// 상품결제 시스템
	public void shopPaymentProcess(ShopDTO shopDTO) {

		List<AccountDTO> accountDTOList = new ArrayList<AccountDTO>();

		// 회원계좌 전체조회
		accountDTOList = shopDAO.selectUserAccount(shopDTO.getShopUserId());

		if (accountDTOList.isEmpty()) {
			System.out.println("해당 회원의 계좌가 존재하지 않습니다.");
			System.out.println("결제를 하기 위해서는 계좌를 개설하셔야 합니다.");
			return;
		} else {
			// 계좌가 몇개인지 확인
			// 계좌 리스트 출력
			System.out.println("####계좌번호 목록#####");
			for (int i = 0; i < accountDTOList.size(); i++) {
				System.out.println((i + 1) + ".계좌번호: " + accountDTOList.get(i).getAccountAccount());
			}

			System.out.println("========================");
			System.out.println("결제할 계좌번호 선택: ");
			int accountChoice = scanner.nextInt();

			if (accountChoice < 1 || accountChoice > accountDTOList.size()) {
				System.out.println("잘못 입력하셨습니다.");
				return;
			}

			int index = accountChoice - 1;

			String userAccount = accountDTOList.get(index).getAccountAccount();

			// 회원이 선택한 계좌번호 정보 조회
			AccountDTO userAccountInfo = shopDAO.selectUserAccoutByAccount(userAccount);

			if (userAccountInfo.getAccountBalance() < shopDTO.getShopTotalPayment()) {
				System.out.println("잔액이 부족합니다.");
				return;
			}

			// 결제 잔고 차감
			int payment = userAccountInfo.getAccountBalance() - shopDTO.getShopTotalPayment();
			userAccountInfo.setAccountBalance(payment);

			// 결제처리(계좌 테이블 업데이트), 잔고 수정
			shopDAO.updateAccountBalance(userAccountInfo);

			// 구매내역 정보 처리
			PurchaseListDTO purchaseListDTO = new PurchaseListDTO();

			purchaseListDTO.setPurchaseListUserId(shopDTO.getShopUserId());
			purchaseListDTO.setPurchaseListAccount(userAccount);
			purchaseListDTO.setPurchaseListShopId(shopDTO.getShopId());
			purchaseListDTO.setPurchaseListShopName(shopDTO.getShopName());
			purchaseListDTO.setPurchaseListTotalPayment(shopDTO.getShopTotalPayment());
			purchaseListDTO.setPurchaseListTotalShopCount(shopDTO.getShopTotalShopCount());

			// 구매내역입력(구매내역 테이블 인서트)
			shopDAO.insertPurchaseList(purchaseListDTO);

			UserDTO userDTO = new UserDTO();

			// 구매내역 총결제금액 합 = 회원 누적금액
			int sumTotalPayment = shopDAO.sumTotalPayment(purchaseListDTO.getPurchaseListUserId());

			int userCreditRating = 5;

			if (sumTotalPayment < 100000) {
				System.out.println("신용등급: 5");
				userCreditRating = 5;
			} else if (sumTotalPayment >= 100000 && sumTotalPayment < 200000) {
				System.out.println("신용등급: 4");
				userCreditRating = 4;
			} else if (sumTotalPayment >= 200000 && sumTotalPayment < 300000) {
				System.out.println("신용등급: 3");
				userCreditRating = 3;
			} else if (sumTotalPayment >= 300000 && sumTotalPayment < 400000) {
				System.out.println("신용등급: 2");
				userCreditRating = 2;
			} else if (sumTotalPayment >= 400000) {
				System.out.println("신용등급: 1");
				userCreditRating = 1;
			}

			userDTO.setUserCreditRating(userCreditRating);
			userDTO.setUserTotal(sumTotalPayment);
			userDTO.setUserId(purchaseListDTO.getPurchaseListUserId());

			// 누적금액합산, 신용도변경(회원 테이블 업데이트)
			shopDAO.updateUserTotalAndCreditRating(userDTO);

			System.out.println("결제가 완료되었습니다.");

		}
	}
}
