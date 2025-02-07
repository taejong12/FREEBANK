package main.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.account.AccountDTO;
import main.board.BoardDTO;
import main.shop.PurchaseListDTO;
import main.shop.ShopDTO;
import main.user.UserDTO;

public class AdminDAO {

	Connection con;

	public AdminDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			String user = "C##FREEBANK";
			String pass = "1234";

			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 전체상품조회
	public List<ShopDTO> selectAllAdminShop() {
		List<ShopDTO> shopList = new ArrayList<ShopDTO>();

		String sql = "select * from FREEBANKSHOP order by FREEBANKSHOP_ID desc";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			// ResultSet : SQL 에서 가져오는 값 저장 객체
			ResultSet rs = pstmt.executeQuery();

			// ResultSet.next()
			// - 값이 있으면 true, 없으면 false
			while (rs.next()) {
				ShopDTO shopDTO = new ShopDTO();
				// ResultSet.get데이터타입(컬럼순서)
				// ResultSet.get데이터타입("컬럼이름");
				shopDTO.setShopId(rs.getInt(1));
				shopDTO.setShopName(rs.getString(2));
				shopDTO.setShopContents(rs.getString(3));
				shopDTO.setShopPrice(rs.getInt(4));
				shopDTO.setShopAdminId(rs.getString(5));
				shopDTO.setShopCreate(rs.getDate(6));
				shopDTO.setShopUpdate(rs.getDate(7));
				shopList.add(shopDTO);
			}

		} catch (Exception e) {
			System.out.println("조회할 상품이 존재하지 않습니다.");
			e.printStackTrace();
		}

		return shopList;

	}

	// 상품 등록
	public void insertAdminShop(ShopDTO shopDTO) {

		String sql = "insert into FREEBANKSHOP values(FREEBANKSHOP_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE, SYSDATE)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, shopDTO.getShopName());
			pstmt.setString(2, shopDTO.getShopContents());
			pstmt.setInt(3, shopDTO.getShopPrice());
			pstmt.setString(4, shopDTO.getShopAdminId());

			int result = pstmt.executeUpdate();

			if (result >= 1) {
				System.out.println("상품 등록이 완료되었습니다.");
			}

		} catch (Exception e) {
			System.out.println("상품등록 실패");
			e.printStackTrace();
		}
	}

	// 상품 수정
	public void updateAdminShop(ShopDTO shopDTO) {
		String sql = "update FREEBANKSHOP set FREEBANKSHOP_NAME=?, FREEBANKSHOP_CONTENTS=?, FREEBANKSHOP_PRICE=?, FREEBANKSHOP_UPDATE=SYSDATE where FREEBANKSHOP_ID=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, shopDTO.getShopName());
			pstmt.setString(2, shopDTO.getShopContents());
			pstmt.setInt(3, shopDTO.getShopPrice());
			pstmt.setInt(4, shopDTO.getShopId());

			int result = pstmt.executeUpdate();

			if (result >= 1) {
				System.out.println("상품 수정이 완료되었습니다.");
			}

		} catch (Exception e) {
			System.out.println("상품수정 실패");
			e.printStackTrace();
		}
	}

	// 상품 삭제
	public void deleteAdminShopById(int shopId) {
		String sql = "delete from FREEBANKSHOP where FREEBANKSHOP_ID=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, shopId);

			int result = pstmt.executeUpdate();

			if (result >= 1) {
				System.out.println("상품 삭제가 완료되었습니다.");
			}
		} catch (Exception e) {
			System.out.println("상품삭제 실패");
			e.printStackTrace();
		}

	}

	// 모든 회원 리스트 조회
	public List<UserDTO> userSelect() {
		List<UserDTO> userList = new ArrayList<UserDTO>();
		String sql = "select * from FREEBANKUSER";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserDTO u = new UserDTO();
				u.setUserId(rs.getString(1));
				u.setUserName(rs.getString(2));
				u.setUserPwd(rs.getString(3));
				u.setUserAge(rs.getInt(4));
				u.setUserSex(rs.getString(5));
				u.setUserEmail(rs.getString(6));
				u.setUserAdmin(rs.getString(7));
				u.setUserCreditRating(rs.getInt(8));
				u.setUserTotal(rs.getInt(9));
				userList.add(u);
			}
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 게시글 수정 (제목 기준)
	public int updateAdminBoard(int boardId, String title, String content) {
		String sql = "UPDATE FREEBANKBOARD SET FREEBANKBOARD_TITLE= ?, FREEBANKBOARD_CONTENT = ?, FREEBANKBOARD_UPDATE = SYSDATE WHERE FREEBANKBOARD_ID = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, boardId);

			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 게시판 삭제
	public int deleteAdminBoard(int boardId) {
		// 존재 여부 확인 SQL
		String checkSql = "SELECT COUNT(*) FROM FREEBANKBOARD WHERE FREEBANKBOARD_ID = ?";
		String deleteSql = "DELETE FROM FREEBANKBOARD WHERE FREEBANKBOARD_ID = ?";

		try {
			// 먼저 해당 제목의 게시판이 존재하는지 확인
			PreparedStatement checkStmt = con.prepareStatement(checkSql);
			checkStmt.setInt(1, boardId);
			ResultSet rs = checkStmt.executeQuery();

			if (rs.next() && rs.getInt(1) == 0) {
				System.out.println("해당 제목의 게시글이 존재하지 않습니다.");
				return 0;
			}

			// 게시판 삭제 진행
			PreparedStatement pstmt = con.prepareStatement(deleteSql);
			pstmt.setInt(1, boardId);
			int result = pstmt.executeUpdate();

			return result;

		} catch (Exception e) {
			System.out.println("게시글 삭제 실패. 제목을 확인해주세요.");
			e.printStackTrace();
		}
		return 0;
	}

	// 게시판 등록
	public int insertAdminBoard(BoardDTO board) {

		int result = 0;

		String sql = "insert into FREEBANKBOARD(FREEBANKBOARD_TITLE, FREEBANKBOARD_AUTHOR, FREEBANKBOARD_CONTENT, FREEBANKBOARD_CREATED, FREEBANKBOARD_UPDATE) values(?,?,?,sysdate,sysdate)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getBoardAdminId());
			pstmt.setString(3, board.getContent());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("게시글 등록 실패");
			e.printStackTrace();
		}
		return result;
	}

	// 구매내역 목록 조회
	public List<PurchaseListDTO> selectAdminPurchaseList() {

		List<PurchaseListDTO> purchaseList = new ArrayList<PurchaseListDTO>();

		String sql = "select * from FREEBANKPURCHASELIST order by FREEBANKPURCHASELIST_ID desc";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				PurchaseListDTO purchaseListDTO = new PurchaseListDTO();
				purchaseListDTO.setPurchaseListId(rs.getInt(1));
				purchaseListDTO.setPurchaseListUserId(rs.getString(2));
				purchaseListDTO.setPurchaseListAccount(rs.getString(3));
				purchaseListDTO.setPurchaseListShopId(rs.getInt(4));
				purchaseListDTO.setPurchaseListShopName(rs.getString(5));
				purchaseListDTO.setPurchaseListTotalPayment(rs.getInt(6));
				purchaseListDTO.setPurchaseListTotalShopCount(rs.getInt(7));
				purchaseListDTO.setPurchaseListCreate(rs.getDate(8));
				purchaseList.add(purchaseListDTO);
			}

		} catch (Exception e) {
			System.out.println("구매내역 리스트 조회 실패");
			e.printStackTrace();
		}

		return purchaseList;

	}

	// 계좌 목록 전체 조회
	public List<AccountDTO> selectAdminAccountList() {
		List<AccountDTO> accountList = new ArrayList<AccountDTO>();

		String sql = "select * from FREEBANKACCOUNT";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				AccountDTO accountDTO = new AccountDTO();
				accountDTO.setAccountAccount(rs.getString(1));
				accountDTO.setAccountBalance(rs.getInt(2));
				accountDTO.setAccountCreate(rs.getDate(3));
				accountDTO.setAccountId(rs.getString(4));
				accountList.add(accountDTO);
			}

		} catch (Exception e) {
			System.out.println("계좌 목록 조회 실패");
			e.printStackTrace();
		}

		return accountList;
	}

}
