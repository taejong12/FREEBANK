package main.admin;

import java.util.List;

import javafx.scene.Parent;
import main.account.AccountDTO;
import main.board.BoardDTO;
import main.shop.PurchaseListDTO;
import main.shop.ShopDTO;
import main.user.UserDTO;

public interface AdminService {

	// 관리자 목록
	void adminBoardPage(Parent root, UserDTO userDTO); // 공지사항 관리 페이지

	void adminAccountPage(Parent root, UserDTO userDTO); // 계좌 관리 페이지

	void adminShopPage(Parent root, UserDTO userDTO); // 상품 관리 페이지

	void adminUserPage(Parent root, UserDTO userDTO); // 회원 관리 페이지

	// 공지사항
	void adminBoardInsertPage(Parent root, UserDTO userDTO); // 관리자 공지사항 등록 화면

	void adminBoardListPage(Parent root, UserDTO userDTO); // 관리자 공지사항목록화면

	void insertAdminBoard(Parent root, UserDTO userDTO); // 공지사항 등록

	List<BoardDTO> selectAdminBoardList(); // 관리자 공지사항 목록 조회

	void adminBoardDetailPage(Parent root, BoardDTO boardDTO, UserDTO userDTO); // 관리자 공지사항 상세보기 화면

	void deleteAdminBoard(BoardDTO boardDTO); // 공지사항 삭제하기

	void updateAdminBoard(Parent root, UserDTO userDTO, BoardDTO boardDTO); // 공지사항 수정하기

	// 계좌
	List<AccountDTO> selectAccountListAll(); // 계좌목록 가져오기

	// 상품
	void adminShopInsertPage(Parent root, UserDTO userDTO); // 상품 등록페이지 출력

	void adminShopListPage(Parent root, UserDTO userDTO); // 상품 조회(수정/삭제)페이지 출력

	void insertAdminShop(Parent root, UserDTO userDTO); // 상품등록하기

	List<ShopDTO> selectAdminShopListAll(); // 상품 목록 조회

	void adminShopDetailPage(Parent root, ShopDTO shopDTO, UserDTO userDTO); // 상품 상세 페이지 출력

	void deleteAdminShop(ShopDTO shopDTO); // 상품 삭제하기

	void updateAdminShop(Parent root, ShopDTO shopDTO, UserDTO userDTO); // 상품 수정하기

	// 회원
	void adminUserListPage(Parent root, UserDTO userDTO); // 회원 목록페이지 출력

	void adminPurchaseListPage(Parent root, UserDTO userDTO); // 구매내역 목록페이지 출력

	List<UserDTO> selectAdminUserListAll(); // 회원 목록 조회

	List<PurchaseListDTO> selectAdminPLListAll(); // 구매내역 목록조회

}
