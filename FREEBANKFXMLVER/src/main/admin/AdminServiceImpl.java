package main.admin;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.account.AccountDTO;
import main.board.BoardDTO;
import main.shop.PurchaseListDTO;
import main.shop.ShopDTO;
import main.user.UserDTO;

public class AdminServiceImpl implements AdminService {

	AdminDAO ad = new AdminDAO();

	// 공지사항 관리페이지 출력
	public void adminBoardPage(Parent root, UserDTO userDTO) {
		Stage adminBoardPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/admin/adminBoardPage.fxml"));

		try {

			Parent adminBoardPageRoot = loader.load();

			AdminController adminBoardPageCtrl = loader.getController();

			adminBoardPageCtrl.setRoot(adminBoardPageRoot);
			adminBoardPageCtrl.setUser(userDTO);

			adminBoardPage.setScene(new Scene(adminBoardPageRoot));
			adminBoardPage.setTitle("공지사항 관리페이지");
			adminBoardPage.show();

			adminBoardPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("공지사항 관리페이지 출력 실패");
			e.printStackTrace();
		}

	}

	// 계좌 관리페이지 출력
	public void adminAccountPage(Parent root, UserDTO userDTO) {
		Stage adminAccountPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/admin/adminAccountPage.fxml"));

		try {

			Parent adminAccountPageRoot = loader.load();

			AdminController adminAccountPageCtrl = loader.getController();

			adminAccountPageCtrl.setRoot(adminAccountPageRoot);
			adminAccountPageCtrl.setUser(userDTO);
			adminAccountPageCtrl.selectAdminAccountList();

			adminAccountPage.setScene(new Scene(adminAccountPageRoot));
			adminAccountPage.setTitle("계좌 관리페이지");
			adminAccountPage.show();

			adminAccountPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("계좌 관리페이지 출력 실패");
			e.printStackTrace();
		}

	}

	// 상품 관리페이지 출력
	public void adminShopPage(Parent root, UserDTO userDTO) {
		Stage adminShopPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/admin/adminShopPage.fxml"));

		try {

			Parent adminShopPageRoot = loader.load();

			AdminController adminShopPageCtrl = loader.getController();

			adminShopPageCtrl.setRoot(adminShopPageRoot);
			adminShopPageCtrl.setUser(userDTO);

			adminShopPage.setScene(new Scene(adminShopPageRoot));
			adminShopPage.setTitle("상품 관리페이지");
			adminShopPage.show();

			adminShopPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("상품 관리페이지 출력 실패");
			e.printStackTrace();
		}

	}

	// 회원 관리 페이지
	public void adminUserPage(Parent root, UserDTO userDTO) {
		Stage adminUserPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/admin/adminUserPage.fxml"));

		try {

			Parent adminUserPageRoot = loader.load();

			AdminController adminUserPageCtrl = loader.getController();

			adminUserPageCtrl.setRoot(adminUserPageRoot);
			adminUserPageCtrl.setUser(userDTO);

			adminUserPage.setScene(new Scene(adminUserPageRoot));
			adminUserPage.setTitle("회원 관리페이지");
			adminUserPage.show();

			adminUserPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("회원 관리페이지 출력 실패");
			e.printStackTrace();
		}
	}

	// 관리자 공지사항 등록 화면
	public void insertAdminBoardPage(Parent root, UserDTO userDTO) {
		Stage insertAdminBoardPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/admin/insertAdminBoardPage.fxml"));

		try {

			Parent insertAdminBoardPageRoot = loader.load();

			AdminController insertAdminBoardPageCtrl = loader.getController();

			insertAdminBoardPageCtrl.setRoot(insertAdminBoardPageRoot);
			insertAdminBoardPageCtrl.setUser(userDTO);

			insertAdminBoardPage.setScene(new Scene(insertAdminBoardPageRoot));
			insertAdminBoardPage.setTitle("공지사항 등록페이지");
			insertAdminBoardPage.show();

			insertAdminBoardPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("공지사항 등록페이지 출력 실패");
			e.printStackTrace();
		}

	}

	// 관리자 공지사항 목록 화면
	public void selectAdminBoardListPage(Parent root, UserDTO userDTO) {
		Stage selectAdminBoardListPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/admin/selectAdminBoardListPage.fxml"));

		try {

			Parent selectAdminBoardListPageRoot = loader.load();

			AdminController selectAdminBoardListPageCtrl = loader.getController();

			selectAdminBoardListPageCtrl.setRoot(selectAdminBoardListPageRoot);
			selectAdminBoardListPageCtrl.setUser(userDTO);
			selectAdminBoardListPageCtrl.selectAdminBoardList();

			selectAdminBoardListPage.setScene(new Scene(selectAdminBoardListPageRoot));
			selectAdminBoardListPage.setTitle("공지사항 목록페이지");
			selectAdminBoardListPage.show();

			selectAdminBoardListPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("공지사항 등록페이지 출력 실패");
			e.printStackTrace();
		}

	}

	// 공지사항 등록
	public void insertAdminBoard(Parent root, UserDTO userDTO) {

		// 제목
		TextField textBoardTitle = (TextField) root.lookup("#boardTitle");
		String boardTitle = textBoardTitle.getText();

		// 내용
		TextField textBoardContent = (TextField) root.lookup("#boardContent");
		String boardContent = textBoardContent.getText();

		System.out.println("제목: " + boardTitle);
		System.out.println("내용: " + boardContent);

		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBoardTitle(boardTitle);
		boardDTO.setBoardContent(boardContent);
		boardDTO.setBoardAuthor(userDTO.getUserId());

		// 공지사항 등록
		int result = ad.insertAdminBoard(boardDTO);

		if (result >= 1) {
			Alert alertInfo = new Alert(AlertType.INFORMATION);
			alertInfo.setTitle("공지사항 등록완료");
			alertInfo.setHeaderText(null);
			alertInfo.setContentText("공지사항 등록완료");

			// 확인 버튼을 누를 때까지 대기
			alertInfo.showAndWait();
		} else {
			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setTitle("공지사항 등록실패");
			alertError.setHeaderText(null);
			alertError.setContentText("공지사항 등록실패");

			// 확인 버튼을 누를 때까지 대기
			alertError.showAndWait();
		}
	}

	// 공지사항 목록 조회
	public List<BoardDTO> selectAdminBoardList() {
		return ad.selectAdminBoardList();
	}

	// 공지사항 상세페이지 출력
	public void adminBoardDetail(Parent root, BoardDTO boardDTO, UserDTO userDTO) {

		System.out.println("공지사항 상세페이지 이동: " + boardDTO.getBoardTitle());

		Stage adminBoardDetail = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/admin/adminBoardDetail.fxml"));

		try {

			Parent adminBoardDetailRoot = loader.load();

			AdminController adminBoardDetailCtrl = loader.getController();

			adminBoardDetailCtrl.setRoot(adminBoardDetailRoot);
			adminBoardDetailCtrl.setUser(userDTO);
			adminBoardDetailCtrl.setBoard(boardDTO);
			adminBoardDetailCtrl.selectAdminBoardInfo();

			adminBoardDetail.setScene(new Scene(adminBoardDetailRoot));
			adminBoardDetail.setTitle("공지사항 상세페이지");
			adminBoardDetail.show();

			adminBoardDetailCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("공지사항 상세페이지 출력 실패");
			e.printStackTrace();
		}

	}

	// 공지사항 수정페이지 출력
	public void updateAdminBoardPage(Parent root, UserDTO userDTO, BoardDTO boardDTO) {

		Stage updateAdminBoardPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/admin/updateAdminBoardPage.fxml"));

		try {

			Parent updateAdminBoardPageRoot = loader.load();

			AdminController updateAdminBoardPageCtrl = loader.getController();

			updateAdminBoardPageCtrl.setRoot(updateAdminBoardPageRoot);
			updateAdminBoardPageCtrl.setUser(userDTO);
			updateAdminBoardPageCtrl.setBoard(boardDTO);
			// 공지사항번호, 공지사항작성자, 공지사항작성일, 공지사항수정일
			updateAdminBoardPageCtrl.updateAdminBoardInfo();

			updateAdminBoardPage.setScene(new Scene(updateAdminBoardPageRoot));
			updateAdminBoardPage.setTitle("공지사항 수정페이지");
			updateAdminBoardPage.show();

			updateAdminBoardPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("공지사항 수정페이지 출력 실패");
			e.printStackTrace();
		}

	}

	// 공지사항 삭제하기
	public void deleteAdminBoard(BoardDTO boardDTO) {
		int result = ad.deleteAdminBoard(boardDTO.getBoardId());

		if (result >= 1) {
			Alert alertInfo = new Alert(AlertType.INFORMATION);
			alertInfo.setTitle("공지사항 삭제완료");
			alertInfo.setHeaderText(null);
			alertInfo.setContentText("공지사항 삭제완료");

			// 확인 버튼을 누를 때까지 대기
			alertInfo.showAndWait();
		} else {

			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setTitle("공지사항 삭제실패");
			alertError.setHeaderText(null);
			alertError.setContentText("공지사항 삭제실패");

			// 확인 버튼을 누를 때까지 대기
			alertError.showAndWait();
		}
	}

	// 공지사항 수정하기
	public void updateAdminBoard(Parent root, UserDTO userDTO, BoardDTO boardDTO) {

		// 제목
		TextField textBoardTitle = (TextField) root.lookup("#boardTitle");
		String boardTitle = textBoardTitle.getText();

		// 내용
		TextField textBoardContent = (TextField) root.lookup("#boardContent");
		String boardContent = textBoardContent.getText();

		boardDTO.setBoardTitle(boardTitle);
		boardDTO.setBoardContent(boardContent);
		boardDTO.setBoardAuthor(userDTO.getUserId());

		int result = ad.updateAdminBoard(boardDTO);

		if (result >= 1) {
			Alert alertInfo = new Alert(AlertType.INFORMATION);
			alertInfo.setTitle("공지사항 수정완료");
			alertInfo.setHeaderText(null);
			alertInfo.setContentText("공지사항 수정완료");

			// 확인 버튼을 누를 때까지 대기
			alertInfo.showAndWait();
		} else {

			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setTitle("공지사항 수정실패");
			alertError.setHeaderText(null);
			alertError.setContentText("공지사항 수정실패");

			// 확인 버튼을 누를 때까지 대기
			alertError.showAndWait();
		}

	}

	// 계좌목록 조회
	public List<AccountDTO> selectAccountListAll() {
		return ad.selectAccountListAll();
	}

	// 상품 등록페이지 출력
	public void insertAdminShopPage(Parent root, UserDTO userDTO) {
		Stage insertAdminShopPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/admin/insertAdminShopPage.fxml"));

		try {

			Parent insertAdminShopPageRoot = loader.load();

			AdminController insertAdminShopPageCtrl = loader.getController();

			insertAdminShopPageCtrl.setRoot(insertAdminShopPageRoot);
			insertAdminShopPageCtrl.setUser(userDTO);

			insertAdminShopPage.setScene(new Scene(insertAdminShopPageRoot));
			insertAdminShopPage.setTitle("상품 등록페이지");
			insertAdminShopPage.show();

			insertAdminShopPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("상품 등록페이지 출력 실패");
			e.printStackTrace();
		}

	}

	// 상품 조회(수정/삭제)페이지 출력
	public void adminShopListPage(Parent root, UserDTO userDTO) {
		Stage adminShopListPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/admin/adminShopListPage.fxml"));

		try {

			Parent adminShopListPageRoot = loader.load();

			AdminController adminShopListPageCtrl = loader.getController();

			adminShopListPageCtrl.setRoot(adminShopListPageRoot);
			adminShopListPageCtrl.setUser(userDTO);
			// 상품 목록 조회
			adminShopListPageCtrl.selectAdminShopList();

			adminShopListPage.setScene(new Scene(adminShopListPageRoot));
			adminShopListPage.setTitle("상품 조회페이지");
			adminShopListPage.show();

			adminShopListPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("상품 조회페이지 출력 실패");
			e.printStackTrace();
		}

	}

	// 회원 목록페이지 출력
	public void adminUserListPage(Parent root, UserDTO userDTO) {
		Stage adminUserListPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/admin/adminUserListPage.fxml"));

		try {

			Parent adminUserListPageRoot = loader.load();

			AdminController adminUserListPageCtrl = loader.getController();

			adminUserListPageCtrl.setRoot(adminUserListPageRoot);
			adminUserListPageCtrl.setUser(userDTO);
			// 회원 목록 출력
			adminUserListPageCtrl.selectAdminUserListAll();

			adminUserListPage.setScene(new Scene(adminUserListPageRoot));
			adminUserListPage.setTitle("회원 목록페이지");
			adminUserListPage.show();

			adminUserListPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("회원 목록페이지 출력 실패");
			e.printStackTrace();
		}

	}

	// 구매내역 목록페이지 출력
	public void adminPLPage(Parent root, UserDTO userDTO) {
		Stage adminPLPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/admin/adminPLPage.fxml"));

		try {

			Parent adminPLPageRoot = loader.load();

			AdminController adminPLPageCtrl = loader.getController();

			adminPLPageCtrl.setRoot(adminPLPageRoot);
			adminPLPageCtrl.setUser(userDTO);
			adminPLPageCtrl.selectAdminPLListAll();

			adminPLPage.setScene(new Scene(adminPLPageRoot));
			adminPLPage.setTitle("구매내역 목록페이지");
			adminPLPage.show();

			adminPLPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("구매내역 목록페이지 출력 실패");
			e.printStackTrace();
		}

	}

	// 회원 목록 조회
	public List<UserDTO> selectAdminUserListAll() {
		return ad.selectAdminUserListAll();
	}

	// 상품등록하기
	public void insertAdminShop(Parent root, UserDTO userDTO) {

		// 상품이름
		TextField textShopName = (TextField) root.lookup("#shopName");
		String shopName = textShopName.getText();

		// 상품설명
		TextField textShopContents = (TextField) root.lookup("#shopContents");
		String shopContents = textShopContents.getText();

		// 상품가격
		TextField textShopPrice = (TextField) root.lookup("#shopPrice");
		int shopPrice = Integer.parseInt(textShopPrice.getText());

		ShopDTO shopDTO = new ShopDTO();
		shopDTO.setShopUserId(userDTO.getUserId());
		shopDTO.setShopName(shopName);
		shopDTO.setShopContents(shopContents);
		shopDTO.setShopPrice(shopPrice);

		int result = ad.insertAdminShop(shopDTO);

		if (result >= 1) {
			Alert alertInfo = new Alert(AlertType.INFORMATION);

			alertInfo.setTitle("상품등록완료");
			alertInfo.setHeaderText(null);
			alertInfo.setContentText("상품등록완료");

			// 확인 버튼을 누를 때까지 대기
			alertInfo.showAndWait();

		} else {

			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setTitle("상품등록실패");
			alertError.setHeaderText(null);
			alertError.setContentText("상품등록실패");

			// 확인 버튼을 누를 때까지 대기
			alertError.showAndWait();
		}
	}

	// 상품 상세페이지 출력
	public void adminShopDetailPage(Parent root, ShopDTO shopDTO, UserDTO userDTO) {

		System.out.println("상품 상세페이지 이동: " + shopDTO.getShopName());

		Stage adminShopDetailPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/admin/adminShopDetailPage.fxml"));

		try {

			Parent adminShopDetailPageRoot = loader.load();

			AdminController adminShopDetailPageCtrl = loader.getController();

			adminShopDetailPageCtrl.setRoot(adminShopDetailPageRoot);
			adminShopDetailPageCtrl.setUser(userDTO);
			adminShopDetailPageCtrl.setShop(shopDTO);
			// 상품 상세페이지 정보
			adminShopDetailPageCtrl.adminDetailShopInfo();

			adminShopDetailPage.setScene(new Scene(adminShopDetailPageRoot));
			adminShopDetailPage.setTitle("상품 상세페이지");
			adminShopDetailPage.show();

			adminShopDetailPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("상품 상세페이지 출력 실패");
			e.printStackTrace();
		}

	}

	// 상품 목록 조회
	public List<ShopDTO> selectAdminShopListAll() {
		return ad.selectAdminShopListAll();
	}

	// 상품 수정페이지 출력
	public void updateAdminShopPage(Parent root, UserDTO userDTO, ShopDTO shopDTO) {
		Stage updateAdminShopPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/admin/updateAdminShopPage.fxml"));

		try {

			Parent updateAdminShopPageRoot = loader.load();

			AdminController updateAdminShopPageCtrl = loader.getController();

			updateAdminShopPageCtrl.setRoot(updateAdminShopPageRoot);
			updateAdminShopPageCtrl.setUser(userDTO);
			updateAdminShopPageCtrl.setShop(shopDTO);
			// 상품 수정페이지 정보
			updateAdminShopPageCtrl.updateAdminDetailShopInfo();

			updateAdminShopPage.setScene(new Scene(updateAdminShopPageRoot));
			updateAdminShopPage.setTitle("상품 수정페이지");
			updateAdminShopPage.show();

			updateAdminShopPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("상품 수정페이지 출력 실패");
			e.printStackTrace();
		}

	}

	// 상품 삭제하기
	public void deleteAdminShop(ShopDTO shopDTO) {

		int result = ad.deleteAdminShop(shopDTO.getShopId());

		if (result >= 1) {
			Alert alertInfo = new Alert(AlertType.INFORMATION);

			alertInfo.setTitle("상품삭제완료");
			alertInfo.setHeaderText(null);
			alertInfo.setContentText("상품삭제완료");

			// 확인 버튼을 누를 때까지 대기
			alertInfo.showAndWait();

		} else {

			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setTitle("상품삭제실패");
			alertError.setHeaderText(null);
			alertError.setContentText("상품삭제실패");

			// 확인 버튼을 누를 때까지 대기
			alertError.showAndWait();
		}
	}

	// 상품 수정하기
	public void updateAdminShop(Parent root, ShopDTO shopDTO, UserDTO userDTO) {

		// 상품이름
		TextField textShopName = (TextField) root.lookup("#adminDetailShopNameTextField");
		String shopName = textShopName.getText();

		// 상품설명
		TextField textShopContents = (TextField) root.lookup("#adminDetailShopContentsTextField");
		String shopContents = textShopContents.getText();

		// 상품가격
		TextField textShopPrice = (TextField) root.lookup("#adminDetailShopPriceTextField");
		int shopPrice = Integer.parseInt(textShopPrice.getText());

		shopDTO.setShopUserId(userDTO.getUserId());
		shopDTO.setShopName(shopName);
		shopDTO.setShopContents(shopContents);
		shopDTO.setShopPrice(shopPrice);

		int result = ad.updateAdminShop(shopDTO);

		if (result >= 1) {
			Alert alertInfo = new Alert(AlertType.INFORMATION);

			alertInfo.setTitle("상품수정완료");
			alertInfo.setHeaderText(null);
			alertInfo.setContentText("상품수정완료");

			// 확인 버튼을 누를 때까지 대기
			alertInfo.showAndWait();

		} else {

			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setTitle("상품수정실패");
			alertError.setHeaderText(null);
			alertError.setContentText("상품수정실패");

			// 확인 버튼을 누를 때까지 대기
			alertError.showAndWait();
		}

	}

	// 구매내역 목록조회
	public List<PurchaseListDTO> selectAdminPLListAll() {
		return ad.selectAdminPLListAll();
	}

}
