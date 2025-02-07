package main.admin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.account.AccountDTO;
import main.board.BoardDAO;
import main.board.BoardDTO;
import main.shop.PurchaseListDTO;
import main.shop.ShopDTO;
import main.user.UserDAO;
import main.user.UserDTO;

public class AdminServiceImpl implements AdminService {

	Scanner scanner = new Scanner(System.in);
	AdminDAO adminDAO = new AdminDAO();
	List<UserDTO> userList = new ArrayList<UserDTO>();
	UserDAO userDAO = new UserDAO();
	List<BoardDTO> boardList = new ArrayList<BoardDTO>();
	BoardDTO board = new BoardDTO();
	BoardDAO boardDAO = new BoardDAO();

	// 쇼핑몰 관리
	public void adminShopMenu(UserDTO userDTO) {

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
				insertAdminShop(userDTO.getUserId());
				break;
			case 2:
				// 2.상품조회
				selectAdminShop();
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
	public void insertAdminShop(String adminId) {
		System.out.println("####상품등록 페이지####");

		ShopDTO shopDTO = new ShopDTO();

		System.out.println("상품명 입력: ");
		shopDTO.setShopName(scanner.next());
		System.out.println("상품 설명 입력: ");
		shopDTO.setShopContents(scanner.next());
		System.out.println("상품 가격 입력: ");
		shopDTO.setShopPrice(scanner.nextInt());

		shopDTO.setShopAdminId(adminId);

		adminDAO.insertAdminShop(shopDTO);

	}

	// 상품조회
	public void selectAdminShop() {

		System.out.println("####상품 조회####");

		List<ShopDTO> shopList = new ArrayList<ShopDTO>();

		shopList = adminDAO.selectAllAdminShop();

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
					updateAdminShop(shopDTO);
					break;
				case 2:
					deleteAdminShop(shopDTO);
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
	public void updateAdminShop(ShopDTO shopDTO) {
		System.out.println("####상품수정 페이지####");

		System.out.println("상품명 입력: ");
		shopDTO.setShopName(scanner.next());

		System.out.println("상품설명 입력: ");
		shopDTO.setShopContents(scanner.next());

		System.out.println("상품가격 입력: ");
		shopDTO.setShopPrice(scanner.nextInt());

		adminDAO.updateAdminShop(shopDTO);

	}

	// 상품삭제
	public void deleteAdminShop(ShopDTO shopDTO) {
		System.out.println("####상품삭제 페이지####");
		System.out.println("정말로 삭제하시겠습니까?");
		System.out.println("1.예");
		System.out.println("2.아니오");
		System.out.println("숫자 선택:");
		int shopDeleteMenu = scanner.nextInt();

		switch (shopDeleteMenu) {
		case 1:
			adminDAO.deleteAdminShopById(shopDTO.getShopId());
			break;
		case 2:
			System.out.println("삭제취소");
			break;
		default:
			System.out.println("없는 번호입니다.");
			break;
		}

	}

	// 관리자 유저 목록 조회
	public UserDTO selectAdminUserList() {
		System.out.println("### 회원 리스트 조회 ###");
		userList = userDAO.userSelect();
		if (userList.isEmpty()) {
			System.out.println("조회를 할 수 있는 회원이 없습니다.");
		} else {
			for (UserDTO user : userList) {
				System.out.println("이름\t아이디\t비밀번호\t\t나이\t성\t관리자 여부\t  신용등급\t  누적금액\t  이메일");
				System.out.println(user.getUserName() + "\t" + user.getUserId() + "\t" + user.getUserPwd() + "\t\t"
						+ user.getUserAge() + "\t" + user.getUserSex() + "\t" + user.getUserAdmin() + "\t\t  "
						+ user.getUserCreditRating() + "\t  " + user.getUserTotal() + "\t  " + user.getUserEmail());
				System.out.println(
						"====================================================================================================");
			}
		}
		return null;
	}

	// 관리자 게시판 메뉴
	public void adminBoardMenu(UserDTO userDTO) {
		System.out.println("1.게시판 등록: ");
		System.out.println("2.게시판 조회: ");
		System.out.println("3.게시판 수정: ");
		System.out.println("4.게시판 삭제: ");
		System.out.print("메뉴를 선택해주세요: ");
		int menu = scanner.nextInt();

		switch (menu) {
		case 1:
			insertAdminBoard(userDTO.getUserId());
			break;
		case 2:
			selectAdminBoard();
			break;
		case 3:
			updateAdminBoard();
			break;
		case 4:
			deleteAdminBoard();
			break;
		default:
			System.out.println("잘못 입력 했습니다. 메뉴 1 ~ 4 중 하나를 선택해주세요.");

		}
	}

	// 게시글 수정 (ID 기준)
	public void updateAdminBoard() {
		int num;
		String title, content;
		try {
			System.out.print("수정할 게시글 번호 :");
			num = scanner.nextInt();
			scanner.nextLine(); // 버퍼 비우기

			System.out.print("새 제목 :");
			title = scanner.nextLine();

			System.out.print("새 내용 :");
			content = scanner.nextLine();

			int result = adminDAO.updateAdminBoard(num, title, content);
			if (result >= 1) {
				System.out.println("게시판이 수정되었습니다.");
			} else {
				System.out.println("게시판 수정 실패. 게시판 번호를 확인해주세요.");
			}

		} catch (Exception e) {
			System.out.println("게시판 수정 에러");
		}
	}

	// 게시글 삭제 (ID 기준)
	public void deleteAdminBoard() {
		System.out.print("삭제할 게시글 번호: ");
		int id = scanner.nextInt();

		try {
			int result = adminDAO.deleteAdminBoard(id);

			if (result >= 1) {
				System.out.println("게시글이 삭제되었습니다.");
			} else {
				System.out.println("게시글 삭제 실패. 존재하지 않는 게시글 번호입니다.");
			}
		} catch (Exception e) {
			System.out.println("게시글 삭제 중 오류 발생");
			e.printStackTrace();
		}
	}

	// 게시판 등록
	public void insertAdminBoard(String adminId) {
		System.out.println("#### 게시판 등록 페이지 ####");

		try {

			BoardDTO board = new BoardDTO();
			board.setBoardAdminId(adminId);

			scanner.nextLine();

			System.out.print("게시판 제목: ");
			board.setTitle(scanner.nextLine());

			System.out.print("게시판 내용: ");
			board.setContent(scanner.nextLine());

			int result = adminDAO.insertAdminBoard(board);

			if (result >= 1) {
				System.out.println("게시판이 생성되었습니다.");
			} else {
				System.out.println("이미 존재하는 게시판입니다.");
			}

		} catch (Exception e) {
			System.out.println("게시판 등록 에러");
		}
	}

	// 게시글 조회
	public void selectAdminBoard() {
		List<BoardDTO> boardList = boardDAO.boardSelect(); // 리스트로 받아야 함

		if (boardList.isEmpty()) {
			System.out.println("게시글이 없습니다.");
			return;
		}

		System.out.println("=== 게시글 목록 ===");
		for (BoardDTO board : boardList) {
			System.out.println("게시글 번호: " + board.getId());
			System.out.println("제목: " + board.getTitle());
			System.out.println("작성자: " + board.getAuthor()); // 작성자 추가
			System.out.println("내용: " + board.getContent());
			System.out.println("작성 날짜: " + board.getCreated());
			System.out.println("수정 날짜: " + board.getUpdate());
			System.out.println("----------------------");
		}

	}

	// 관리자 사용자 정보 조회
	public void selectAdminUserInfoMenu() {

		while (true) {
			System.out.println("#### 사용자 정보 관리 페이지 ####");
			System.out.println("1.회원 목록 조회");
			System.out.println("2.구매내역 목록 조회");
			System.out.println("0.이전페이지");
			System.out.println("선택: ");

			int adminUserInfoMenu = scanner.nextInt();

			switch (adminUserInfoMenu) {
			case 1:
				selectAdminUserList();
				break;
			case 2:
				selectAdminPurchaseList();
				break;
			case 0:
				System.out.println("이전페이지");
				return;
			default:
				System.out.println("잘못된 선택입니다.");
			}
		}

	}

	// 구매내역 목록 조회
	public void selectAdminPurchaseList() {

		List<PurchaseListDTO> purchaseList = new ArrayList<PurchaseListDTO>();

		System.out.println("#### 구매내역 목록 조회 ####");

		purchaseList = adminDAO.selectAdminPurchaseList();

		if (purchaseList.isEmpty()) {
			System.out.println("구매내역이 존재하지 않습니다.");
			return;
		}

		System.out.println("|\t번호\t|\t유저아이디\t|\t계좌번호\t|\t상품번호\t|\t상품이름\t|\t총결제금액\t|\t총상품갯수\t|\t결제날짜\t\t|");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------------");
		for (PurchaseListDTO purchaseListDTO : purchaseList) {
			System.out.println("|\t" + purchaseListDTO.getPurchaseListId() + "\t|\t"
					+ purchaseListDTO.getPurchaseListUserId() + "\t|\t" + purchaseListDTO.getPurchaseListAccount()
					+ "\t|\t" + purchaseListDTO.getPurchaseListShopId() + "\t|\t"
					+ purchaseListDTO.getPurchaseListShopName() + "\t|\t"
					+ purchaseListDTO.getPurchaseListTotalPayment() + "\t|\t"
					+ purchaseListDTO.getPurchaseListTotalShopCount() + "\t|\t"
					+ purchaseListDTO.getPurchaseListCreate() + "\t|");
		}
	}

	// 계좌 관리 페이지
	public void selectAdminAccountMenu() {

		while (true) {
			System.out.println("#### 계좌 관리 페이지 ####");
			System.out.println("1.계좌 목록 조회");
			System.out.println("0.이전 페이지");
			System.out.println("선택: ");

			int adminAccountMenu = scanner.nextInt();

			switch (adminAccountMenu) {
			case 1:
				// 계좌 전체 목록
				selectAdminAccountList();
				break;
			case 0:
				System.out.println("이전 페이지");
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
		}

	}

	// 관리자 계좌 목록 조회
	public void selectAdminAccountList() {
		System.out.println("#### 계좌 목록 조회 ####");

		List<AccountDTO> accountList = adminDAO.selectAdminAccountList();

		if (accountList.isEmpty()) {
			System.out.println("계좌목록이 존재하지 않습니다.");
			return;
		}

		System.out.println("|\t계좌번호\t|\t계좌잔액\t|\t계좌생성날짜\t|\t회원아이디\t|");
		System.out.println("-------------------------------------------------------------------------");
		for (AccountDTO accountDTO : accountList) {
			System.out.println("|\t" + accountDTO.getAccountAccount() + "\t|\t" + accountDTO.getAccountBalance()
					+ "\t|\t" + accountDTO.getAccountCreate() + "\t|\t" + accountDTO.getAccountId() + "\t|");
		}

	}

}
