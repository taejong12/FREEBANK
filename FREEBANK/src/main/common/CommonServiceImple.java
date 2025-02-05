package main.common;

public class CommonServiceImple implements CommonService{

	
	//통합 전체 메뉴
	public void mainMenu() {
		System.out.println("####FREEBANK 메인페이지####");
		System.out.println("1.공지사항");
		System.out.println("2.쇼핑몰");
		System.out.println("3.로그인");
		System.out.println("4.회원가입");
		System.out.println("0.종료");
		System.out.println("선택: ");
	}

	//일반 유저 메뉴
	public void loginMainMenu() {
		System.out.println("####FREEBANK 메인페이지####");
		System.out.println("1.공지사항");
		System.out.println("2.계좌");
		System.out.println("3.쇼핑몰");
		System.out.println("4.마이페이지");
		System.out.println("5.로그아웃");
		System.out.println("0.종료");
		System.out.println("선택: ");
	}

	//관리자 메뉴
	public void adminMainMenu() {
		System.out.println("####FREEBANK 관리자 메인페이지####");
		System.out.println("1.공지사항 관리");
		System.out.println("2.계좌 관리");
		System.out.println("3.쇼핑몰 관리");
		System.out.println("4.사용자 정보 관리");
		System.out.println("5.로그아웃");
		System.out.println("0.종료");
		System.out.println("선택: ");
	}
	
	
	
}
