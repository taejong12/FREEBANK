package main.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.shop.ShopDTO;
import main.user.UserDAO;
import main.user.UserDTO;

public class AdminServiceImpl implements AdminService {

   Scanner scanner = new Scanner(System.in);
   AdminDAO adminDAO = new AdminDAO();
   List<UserDTO> userList = new ArrayList<UserDTO>();
   UserDAO userDAO = new UserDAO();
   
   // 쇼핑몰 관리
   public void adminShop(UserDTO userDTO) {

      while (true) {

         System.out.println("#### 쇼핑몰 관리 페이지 ####");
         System.out.println("1.상품등록");
         System.out.println("2.상품조회(수정/삭제)");
         System.out.println("0.이전페이지");
         System.out.println("선택: ");

         int adminShopMenu = scanner.nextInt();

         switch (adminShopMenu) {
         case 1:
            // 1.상품등록
            adminShopInsert(userDTO.getUserId());
            break;
         case 2:
            // 2.상품조회
            adminShopSelect();
            break;
         case 0:
            // 0.이전페이지
            System.out.println("이전페이지");
            return;
         default:
            System.out.println("잘못 입력하셨습니다.");

         }

      }

   }

   // 상품등록
   public void adminShopInsert(String adminId) {
      System.out.println("####상품등록 페이지####");

      ShopDTO shopDTO = new ShopDTO();

      System.out.println("상품명 입력: ");
      shopDTO.setShopName(scanner.next());
      System.out.println("상품 설명 입력: ");
      shopDTO.setShopContents(scanner.next());
      System.out.println("상품 가격 입력: ");
      shopDTO.setShopPrice(scanner.nextInt());

      shopDTO.setShopAdminId(adminId);

      adminDAO.adminShopInsert(shopDTO);

   }

   // 상품조회
   public void adminShopSelect() {

      System.out.println("####상품 조회####");

      List<ShopDTO> shopList = new ArrayList<ShopDTO>();

      shopList = adminDAO.adminShopSelectAll();

      for (int i = 0; i < shopList.size(); i++) {
         System.out.println("상품번호: " + shopList.get(i).getShopId());
         System.out.println("상품명: " + shopList.get(i).getShopName());
         System.out.println("상품가격: " + shopList.get(i).getShopPrice());
         System.out.println("================================");
      }

      System.out.println("0.취소");
      System.out.println("상품번호를 입력해주세요: ");

      int adminShopListMenu = scanner.nextInt();

      // 상품 존재 유무 채크
      boolean adminShopCheck = false;

      for (ShopDTO shopDTO : shopList) {

         if (adminShopListMenu == shopDTO.getShopId()) {
            adminShopCheck = true;
            System.out.println("#### 상품상세페이지 ####");
            System.out.println("상품번호: " + shopDTO.getShopId());
            System.out.println("상품명: " + shopDTO.getShopName());
            System.out.println("상품설명: " + shopDTO.getShopContents());
            System.out.println("상품가격: " + shopDTO.getShopPrice());
            System.out.println("상품관리자: " + shopDTO.getShopAdminId());
            System.out.println("상품등록일: " + shopDTO.getShopCreate());
            System.out.println("상품수정일: " + shopDTO.getShopUpdate());
            System.out.println("====================================");
            System.out.println("1.상품수정");
            System.out.println("2.상품삭제");
            System.out.println("0.취소");
            System.out.println("선택: ");

            int adminShopDetailMenu = scanner.nextInt();

            switch (adminShopDetailMenu) {
            case 1:
               adminShopUpdate(shopDTO);
               break;
            case 2:
               adminShopDelete(shopDTO);
               break;
            case 0:
               System.out.println("상품상세페이지 취소");
               break;
            default:
               System.out.println("없는 번호입니다.");
               break;
            }

         } else if (adminShopListMenu == 0) {
            System.out.println("상품 조회 취소");
            return;
         }
      }

      if (!adminShopCheck) {
         System.out.println("해당하는 상품번호가 존재하지 않습니다.");
      }

   }

   // 상품수정
   public void adminShopUpdate(ShopDTO shopDTO) {
      System.out.println("####상품수정 페이지####");

      System.out.println("상품명 입력: ");
      shopDTO.setShopName(scanner.next());

      System.out.println("상품설명 입력: ");
      shopDTO.setShopContents(scanner.next());

      System.out.println("상품가격 입력: ");
      shopDTO.setShopPrice(scanner.nextInt());

      adminDAO.adminShopUpdate(shopDTO);

   }

   // 상품삭제
   public void adminShopDelete(ShopDTO shopDTO) {
      System.out.println("####상품삭제 페이지####");
      System.out.println("정말로 삭제하시겠습니까?");
      System.out.println("1.예");
      System.out.println("2.아니오");
      System.out.println("숫자 선택:");
      int shopDeleteMenu = scanner.nextInt();

      switch (shopDeleteMenu) {
      case 1:
         adminDAO.adminShopDeleteId(shopDTO.getShopId());
         break;
      case 2:
         System.out.println("삭제취소");
         break;
      default:
         System.out.println("없는 번호입니다.");
         break;
      }

   }

   public UserDTO adminUserSelect() {
      System.out.println("### 회원 리스트 조회 ###");
      userList = userDAO.userSelect();
      if (userList.isEmpty()) {
         System.out.println("조회를 할 수 있는 회원이 없습니다.");
      } else {
         for (UserDTO user : userList) {
            System.out.println("이름\t아이디\t비밀번호\t나이\t성\t이메일\t\t\t  관리자 여부\t신용등급\t누적금액");
            System.out.println(user.getUserName() + "\t" + user.getUserId() + "\t" + user.getUserPwd() + "\t"
                  + user.getUserAge() + "\t" + user.getUserSex() + "\t" + user.getUserEmail() + "\t  "
                  + user.getUserAdmin() + "\t\t" + user.getUserCreditRating() + "\t" + user.getUserTotal());
            System.out.println();
         }
      }
      return null;
   }
}
