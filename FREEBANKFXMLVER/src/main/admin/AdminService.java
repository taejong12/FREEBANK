package main.admin;

import java.util.List;

import javafx.scene.Parent;
import main.account.AccountDTO;
import main.board.BoardDTO;
import main.user.UserDTO;

public interface AdminService {

	void adminBoardPage(Parent root, UserDTO userDTO); // 공지사항 관리 페이지

	void adminAccountPage(Parent root, UserDTO userDTO); // 계좌 관리 페이지

	void adminShopPage(Parent root, UserDTO userDTO); // 쇼핑몰 관리 페이지

	void adminUserPage(Parent root, UserDTO userDTO); // 회원 관리 페이지

	void insertAdminBoardPage(Parent root, UserDTO userDTO); // 관리자 공지사항 등록 화면

	void selectAdminBoardListPage(Parent root, UserDTO userDTO); // 관리자 공지사항목록화면

	void insertAdminBoard(Parent root, UserDTO userDTO); // 공지사항 등록

	List<BoardDTO> selectAdminBoardList(); // 관리자 공지사항 목록 조회

	void adminBoardDetail(Parent root, BoardDTO boardDTO, UserDTO userDTO); // 관리자 공지사항 상세보기 화면

	void updateAdminBoardPage(Parent root, UserDTO userDTO, BoardDTO boardDTO); // 공지사항 수정하기 페이지

	void deleteAdminBoard(BoardDTO boardDTO); // 공지사항 삭제하기

	void updateAdminBoard(Parent root, UserDTO userDTO, BoardDTO boardDTO); // 공지사항 수정하기

	List<AccountDTO> selectAccountListAll(); // 계좌목록 가져오기

	void insertAdminShopPage(Parent root, UserDTO userDTO); // 상품 등록페이지 출력

	void updateAdminShopPage(Parent root, UserDTO userDTO); // 상품 조회(수정/삭제)페이지 출력

	void adminUserListPage(Parent root, UserDTO userDTO); // 회원 목록페이지 출력

	void adminPLPage(Parent root, UserDTO userDTO); // 구매내역 목록페이지 출력

}
