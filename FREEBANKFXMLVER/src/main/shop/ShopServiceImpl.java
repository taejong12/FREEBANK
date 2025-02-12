package main.shop;

import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.account.AccountDAO;
import main.account.AccountDTO;
import main.user.UserDAO;
import main.user.UserDTO;

public class ShopServiceImpl implements ShopService {

	ShopDAO sd = new ShopDAO();
	AccountDAO ad = new AccountDAO();
	UserDAO ud = new UserDAO();

	// 상품 목록페이지 출력(비로그인)
	public void shopListPage(Parent root, UserDTO userDTO) {

		Stage shopListPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/shop/shopListPage.fxml"));

		try {

			Parent shopListPageRoot = loader.load();

			ShopController shopListPageCtrl = loader.getController();

			shopListPageCtrl.setRoot(shopListPageRoot);
			shopListPageCtrl.setUser(userDTO);
			// 상품 목록 출력
			shopListPageCtrl.selectShopList();

			shopListPage.setScene(new Scene(shopListPageRoot));
			shopListPage.setTitle("상품목록 페이지(비회원)");
			shopListPage.show();

		} catch (Exception e) {
			System.out.println("상품목록 페이지(비회원) 출력 실패");
			e.printStackTrace();
		}

	}

	// 상품 목록페이지 출력(회원로그인)
	public void shopLoginListPage(Parent root, UserDTO userDTO) {
		Stage shopLoginListPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/shop/shopLoginListPage.fxml"));

		try {

			Parent shopLoginListPageRoot = loader.load();

			ShopController shopLoginListPageCtrl = loader.getController();

			shopLoginListPageCtrl.setRoot(shopLoginListPageRoot);
			shopLoginListPageCtrl.setUser(userDTO);
			// 상품 리스트 출력
			shopLoginListPageCtrl.selectShopListLogin();

			shopLoginListPage.setScene(new Scene(shopLoginListPageRoot));
			shopLoginListPage.setTitle("상품목록 페이지(회원)");
			shopLoginListPage.show();

		} catch (Exception e) {
			System.out.println("상품목록 페이지(회원로그인) 출력 실패");
			e.printStackTrace();
		}

	}

	// 상품 리스트
	public List<ShopDTO> selectShopList() {
		return sd.selectShopList();
	}

	// 상품 상세페이지 출력(비로그인)
	public void shopDetailPage(Parent root, ShopDTO shop, UserDTO userDTO) {
		Stage shopDetailPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/shop/shopDetailPage.fxml"));

		try {

			Parent shopDetailPageRoot = loader.load();

			ShopController shopDetailPageCtrl = loader.getController();

			shopDetailPageCtrl.setRoot(shopDetailPageRoot);
			shopDetailPageCtrl.setUser(userDTO);
			shopDetailPageCtrl.setShop(shop);
			// 상품 상세페이지 정보
			shopDetailPageCtrl.selectShopDtailInfo();

			shopDetailPage.setScene(new Scene(shopDetailPageRoot));
			shopDetailPage.setTitle("상품상세페이지(비회원)");
			shopDetailPage.show();

		} catch (Exception e) {
			System.out.println("상품 상세페이지(비회원) 출력 실패");
			e.printStackTrace();
		}

	}

	// 상품 상세페이지 출력(회원로그인)
	public void shopLoginDetailPage(Parent root, ShopDTO shop, UserDTO userDTO) {
		Stage shopLoginDetailPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/shop/shopLoginDetailPage.fxml"));

		try {

			Parent shopLoginDetailPageRoot = loader.load();

			ShopController shopLoginDetailPageCtrl = loader.getController();

			shopLoginDetailPageCtrl.setRoot(shopLoginDetailPageRoot);
			shopLoginDetailPageCtrl.setUser(userDTO);
			shopLoginDetailPageCtrl.setShop(shop);
			// 상품 상세페이지 정보
			shopLoginDetailPageCtrl.selectShopDtailInfo();

			shopLoginDetailPage.setScene(new Scene(shopLoginDetailPageRoot));
			shopLoginDetailPage.setTitle("상품상세페이지(회원)");
			shopLoginDetailPage.show();

		} catch (Exception e) {
			System.out.println("상품 상세페이지(회원) 출력 실패");
			e.printStackTrace();
		}

	}

	// 상품 결제페이지 출력
	public void userShopPayPage(Parent root, UserDTO userDTO, ShopDTO shopDTO) {
		Stage userShopPayPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/shop/userShopPayPage.fxml"));

		try {

			Parent userShopPayPageRoot = loader.load();

			ShopController userShopPayPageCtrl = loader.getController();

			userShopPayPageCtrl.setRoot(userShopPayPageRoot);
			userShopPayPageCtrl.setUser(userDTO);
			userShopPayPageCtrl.setShop(shopDTO);
			// 상품 결제페이지 정보
			userShopPayPageCtrl.selectShopPayInfo();

			userShopPayPage.setScene(new Scene(userShopPayPageRoot));
			userShopPayPage.setTitle("상품결제페이지");
			userShopPayPage.show();

		} catch (Exception e) {
			System.out.println("상품결제페이지 출력 실패");
			e.printStackTrace();
		}
	}

	// 상품 결제하기
	public boolean userShopPayment(Parent root, ShopDTO shopDTO, UserDTO userDTO) {

		TextField textShopPayAccount = (TextField) root.lookup("#shopPayAccount");
		String shopPayAccount = textShopPayAccount.getText();

		TextField textPayUserId = (TextField) root.lookup("#shopPayUserId");
		String shopPayUserId = textPayUserId.getText();

		TextField textPayUserPwd = (TextField) root.lookup("#shopPayUserPwd");
		String shopPayUserPwd = textPayUserPwd.getText();

		System.out.println("계좌번호: " + shopPayAccount);
		System.out.println("아이디: " + shopPayUserId);
		System.out.println("비밀번호: " + shopPayUserPwd);
		System.out.println("로그인 정보: " + userDTO.getUserId());

		List<AccountDTO> accountList = ad.selectUserAccountById(userDTO.getUserId());

		UserDTO userCheckDTO = new UserDTO();

		userCheckDTO = ud.selectUserInfoById(userDTO.getUserId());

		boolean payCheck = true;

		for (AccountDTO accountDTO : accountList) {
			if (accountDTO.getAccountAccount().equals(shopPayAccount)) {
				System.out.println("계좌번호 일치");
				if (userCheckDTO.getUserId().equals(shopPayUserId)
						&& userCheckDTO.getUserPwd().equals(shopPayUserPwd)) {
					System.out.println("아이디/비밀번호 일치");

					if (accountDTO.getAccountBalance() >= shopDTO.getShopTotalPayment()) {

						// 상품 총결제금액 잔고 차감
						int sumAccountBalance = accountDTO.getAccountBalance() - shopDTO.getShopTotalPayment();

						accountDTO.setAccountBalance(sumAccountBalance);

						// 계좌잔고 업데이트
						ad.updateAccountBalance(accountDTO);

						PurchaseListDTO purchaseListDTO = new PurchaseListDTO();
						purchaseListDTO.setPurchaseListUserId(shopPayUserId);
						purchaseListDTO.setPurchaseListAccount(shopPayAccount);
						purchaseListDTO.setPurchaseListShopId(shopDTO.getShopId());
						purchaseListDTO.setPurchaseListShopName(shopDTO.getShopName());
						purchaseListDTO.setPurchaseListTotalpayment(shopDTO.getShopTotalPayment());
						purchaseListDTO.setPurchaseListTotalshopcount(shopDTO.getShopTotalShopCount());

						// 구매내역 목록 추가
						sd.insertShopPLInfo(purchaseListDTO);

						// 구매내역 총결제금액 합 = 회원 누적금액
						int sumTotalPayment = sd.sumTotalPayment(userDTO.getUserId());

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

						// 누적금액합산, 신용도변경(회원 테이블 업데이트)
						sd.updateUserTotalAndCreditRating(userDTO);

						payCheck = false;

						Alert alertInfo = new Alert(AlertType.INFORMATION);
						alertInfo.setTitle("상품결제완료");
						alertInfo.setHeaderText(null);
						alertInfo.setContentText("상품결제완료");

						// 확인 버튼을 누를 때까지 대기
						alertInfo.showAndWait();
					} else {
						System.out.println("계좌잔고 부족");
					}

				} else {
					System.out.println("아이디/비밀번호 불일치");
				}
			} else {
				System.out.println("계좌번호 불일치");
			}
		}

		if (payCheck) {
			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setTitle("상품결제실패");
			alertError.setHeaderText(null);
			alertError.setContentText("상품결제실패");

			// 확인 버튼을 누를 때까지 대기
			alertError.showAndWait();
		}

		textShopPayAccount.clear();
		textPayUserId.clear();
		textPayUserPwd.clear();

		return payCheck;
	}

	// 비회원 결제 버튼
	public void userShopPayLoginPage(Parent root, UserDTO userDTO) {

		Alert alertError = new Alert(AlertType.ERROR);
		alertError.setTitle("결제실패");
		alertError.setHeaderText(null);
		alertError.setContentText("결제시 로그인 필수");

		// 확인 버튼을 누를 때까지 대기
		alertError.showAndWait();

	}

}
