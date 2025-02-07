package main.admin;

import main.shop.ShopDTO;
import main.user.UserDTO;

public interface AdminService {

   void adminShopMenu(UserDTO userDTO); 		// 쇼핑몰 관리
   void selectAdminShop(); 						// 관리자 상품조회
   void insertAdminShop(String adminId); 		// 관리자 상품등록
   void updateAdminShop(ShopDTO shopDTO); 		// 관리자 상품수정
   void deleteAdminShop(ShopDTO shopDTO); 		// 관리자 상품삭제
   UserDTO selectAdminUserList(); 				// 관리자 유저 목록 조회
   void adminBoardMenu(UserDTO userDTO); 		// 관리자 게시판 메뉴
   void updateAdminBoard(); 					// 관리자 게시판 수정
   void deleteAdminBoard(); 					// 관리자 게시판 삭제
   void insertAdminBoard(String adminId); 		// 관리자 게시판 등록
   void selectAdminBoard(); 					// 관리자 게시판 조회
   void selectAdminUserInfoMenu(); 				// 관리자 사용자 정보 조회
   void selectAdminAccountMenu();				// 관리자 계좌 목록 조회
   void selectAdminPurchaseList();				// 관리자 구매내역 목록 조회
}