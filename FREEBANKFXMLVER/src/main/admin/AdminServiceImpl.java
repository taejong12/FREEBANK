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
import main.shop.ShopController;
import main.user.UserDTO;

public class AdminServiceImpl implements AdminService {

	AdminDAO ad = new AdminDAO();

	// 공지사항 관리페이지
	public void adminBoardPage(Parent root, UserDTO userDTO) {
		Stage adminBoardPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/admin/adminBoardPage.fxml"));

		try {

			Parent adminBoardPageRoot = loader.load();

			AdminController adminBoardPageCtrl = loader.getController();

			adminBoardPageCtrl.setRoot(adminBoardPageRoot);
			adminBoardPageCtrl.setUser(userDTO);

			adminBoardPage.setScene(new Scene(adminBoardPageRoot));
			adminBoardPage.setTitle("공지사항 관리 페이지");
			adminBoardPage.show();

			adminBoardPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("공지사항 관리 페이지 에러");
			e.printStackTrace();
		}

	}

	// 계좌 관리 페이지
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
			adminAccountPage.setTitle("계좌 관리 페이지");
			adminAccountPage.show();

			adminAccountPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("계좌 관리 페이지 에러");
			e.printStackTrace();
		}

	}

	// 쇼핑몰 관리 페이지
	public void adminShopPage(Parent root, UserDTO userDTO) {
		Stage adminShopPage = (Stage) root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/admin/adminShopPage.fxml"));

		try {

			Parent adminShopPageRoot = loader.load();

			AdminController adminShopPageCtrl = loader.getController();

			adminShopPageCtrl.setRoot(adminShopPageRoot);
			adminShopPageCtrl.setUser(userDTO);

			adminShopPage.setScene(new Scene(adminShopPageRoot));
			adminShopPage.setTitle("공지사항 관리 페이지");
			adminShopPage.show();

			adminShopPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("공지사항 관리 페이지 에러");
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
			adminUserPage.setTitle("회원 관리 페이지");
			adminUserPage.show();

			adminUserPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("회원 관리 페이지 에러");
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
			insertAdminBoardPage.setTitle("공지사항 등록 페이지");
			insertAdminBoardPage.show();

			insertAdminBoardPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("공지사항 등록 페이지 에러");
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
			selectAdminBoardListPage.setTitle("공지사항 목록 페이지");
			selectAdminBoardListPage.show();

			selectAdminBoardListPageCtrl.showUserInfo();

		} catch (Exception e) {
			System.out.println("공지사항 등록 페이지 에러");
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
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("공지사항 등록 완료");
			alert.setHeaderText(null);
			alert.setContentText("공지사항 등록 완료");

			// 확인 버튼을 누를 때까지 대기
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("공지사항 등록 실패");
			alert.setHeaderText(null);
			alert.setContentText("공지사항 등록 실패");

			// 확인 버튼을 누를 때까지 대기
			alert.showAndWait();
		}
	}

	// 공지사항 목록 조회
	public List<BoardDTO> selectAdminBoardList() {
		return ad.selectAdminBoardList();
	}

	// 공지사항 상세페이지 화면
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

	// 공지사항 수정하기 페이지
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
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("공지사항 삭제완료");
			alert.setHeaderText(null);
			alert.setContentText("공지사항 삭제완료");

			// 확인 버튼을 누를 때까지 대기
			alert.showAndWait();
		} else {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("공지사항 삭제실패");
			alert.setHeaderText(null);
			alert.setContentText("공지사항 삭제실패");

			// 확인 버튼을 누를 때까지 대기
			alert.showAndWait();
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
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("공지사항 수정완료");
			alert.setHeaderText(null);
			alert.setContentText("공지사항 수정완료");

			// 확인 버튼을 누를 때까지 대기
			alert.showAndWait();
		} else {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("공지사항 수정실패");
			alert.setHeaderText(null);
			alert.setContentText("공지사항 수정실패");

			// 확인 버튼을 누를 때까지 대기
			alert.showAndWait();
		}

	}

	//계좌목록 가져오기
	public List<AccountDTO> selectAccountListAll() {
		return ad.selectAccountListAll();
	}

}
