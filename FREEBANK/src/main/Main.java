package main;

import java.util.Scanner;

import main.admin.AdminService;
import main.admin.AdminServiceImpl;
import main.common.CommonService;
import main.common.CommonServiceImple;
import main.shop.ShopService;
import main.shop.ShopServiceImpl;
import main.user.UserDTO;
import main.user.UserService;
import main.user.UserServiceImpl;

public class Main {

	public static void main(String[] args) {

		
		Scanner scanner = new Scanner(System.in);
		CommonService commonService = new CommonServiceImple();
		UserService userService = new UserServiceImpl();
		ShopService shopService = new ShopServiceImpl();
		AdminService adminService = new AdminServiceImpl();
		
		//회원체크
		UserDTO loginCheck = new UserDTO();
		
		while(true) {

			//일반 유저 로그인 상태
			if(loginCheck != null && "n".equals(loginCheck.getUserAdmin())) {
				commonService.loginMainMenu();
				
				int loginMainMenu = scanner.nextInt();
				
				switch(loginMainMenu){
					case 1:
						//1.공지사항
						//게시판 조회
						break;
					case 2:
						//2.계좌 - 로그인시
						//계좌 조회/개설/입금/출금/삭제
						break;
					case 3:
						//3.쇼핑몰
						shopService.shopList(loginCheck);
						break;
					case 4:
						//4.마이페이지
						//내 정보 조회/수정/회원탈퇴
						loginCheck = userService.userInfo(loginCheck.getUserId());

						break;
					case 5:
						//5.로그아웃
						loginCheck = userService.userLogout();
						break;
					case 0:
						//0.프로그램 종료
						System.out.println("FREEBANK 종료");
						return;
					default:
						System.out.println("잘못 입력하셨습니다.");
				}
			
			//관리자 로그인 상태
			} else if(loginCheck != null && "y".equals(loginCheck.getUserAdmin())) {
				
				commonService.adminMainMenu();
				
				int adminMainMenu = scanner.nextInt();
				
				switch(adminMainMenu) {
					case 1:
						//1.공지사항 관리
						//게시판 조회/입력/수정/삭제
						break;
					case 2:
						//2.계좌 관리(전체 리스트 조회)
						break;
					case 3:
						//3.쇼핑몰 관리
						adminService.adminShop(loginCheck);
						break;
					case 4:
						//4.사용자 정보 관리(전체 리스트 조회)
						adminService.adminUserSelect();
						break;
					case 5:
						//5.로그아웃
						loginCheck = userService.userLogout();
						break;
					case 0:
						//0.프로그램 종료
						System.out.println("FREEBANK 종료");
						return;
					default:
						System.out.println("잘못 입력하셨습니다.");				
				}

			//비로그인 상태
			} else {
				commonService.mainMenu();
				
				int mainMenu = scanner.nextInt();
				
				switch(mainMenu){
					case 1:
						//1.공지사항
						//게시판 조회
						break;
					case 2:
						//2.쇼핑몰
						shopService.shopList(loginCheck);
						break;
					case 3:
						//4.로그인
						loginCheck = userService.userLogin();
						break;
					case 4:
						//4.회원가입
						userService.userRegister();
						break;
					case 0:
						//0.종료
						System.out.println("FREEBANK 종료");
						return;
					default:
						System.out.println("잘못 입력하셨습니다.");
				}
			}
		}
	}
}
