package main.admin;

import main.shop.ShopDTO;
import main.user.UserDTO;

public interface AdminService {

   void adminShop(UserDTO userDTO); // 쇼핑몰 관리

   void adminShopSelect(); // 관리자 상품조회

   void adminShopInsert(String adminId); // 관리자 상품등록

   void adminShopUpdate(ShopDTO shopDTO); // 관리자 상품수정

   void adminShopDelete(ShopDTO shopDTO); // 관리자 상품삭제
   
   UserDTO adminUserSelect(); // 관리자 리스트 조회

}