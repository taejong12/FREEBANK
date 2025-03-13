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
			String user = "c##FREEBANK";
			String pass = "1234";
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("오라클 연결 실패");
		}
	}

	// 공지사항 등록
	public int insertAdminBoard(BoardDTO boardDTO) {

		int result = 0;

		String sql = "insert into FREEBANKBOARD(FREEBANKBOARD_TITLE, FREEBANKBOARD_AUTHOR, FREEBANKBOARD_CONTENT) values(?,?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getBoardTitle());
			pstmt.setString(2, boardDTO.getBoardAuthor());
			pstmt.setString(3, boardDTO.getBoardContent());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("공지사항 등록 실패");
			e.printStackTrace();
		}
		return result;
	}

	// 공지사항 목록 조회
	public List<BoardDTO> selectAdminBoardList() {

		List<BoardDTO> boardList = new ArrayList<BoardDTO>();

		String sql = "select * from FREEBANKBOARD order by FREEBANKBOARD_ID desc";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			// ResultSet : SQL 에서 가져오는 값 저장 객체
			ResultSet rs = pstmt.executeQuery();

			// ResultSet.next()
			// - 값이 있으면 true, 없으면 false
			while (rs.next()) {
				BoardDTO boardDTO = new BoardDTO();
				// ResultSet.get데이터타입(컬럼순서)
				// ResultSet.get데이터타입("컬럼이름");
				boardDTO.setBoardId(rs.getInt(1));
				boardDTO.setBoardTitle(rs.getString(2));
				boardDTO.setBoardAuthor(rs.getString(3));
				boardDTO.setBoardContent(rs.getString(4));
				boardDTO.setBoardCreated(rs.getDate(5));
				boardDTO.setBoardUpdate(rs.getDate(6));
				boardList.add(boardDTO);
			}

		} catch (Exception e) {
			System.out.println("공지사항 목록조회 실패");
			e.printStackTrace();
		}

		return boardList;
	}

	// 공지사항 삭제하기
	public int deleteAdminBoard(int boardId) {

		int result = 0;

		String sql = "delete from FREEBANKBOARD where FREEBANKBOARD_ID=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardId);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("공지사항 삭제 실패");
			e.printStackTrace();
		}

		return result;
	}

	// 공지사항 수정하기
	public int updateAdminBoard(BoardDTO boardDTO) {

		int result = 0;

		String sql = "update FREEBANKBOARD set FREEBANKBOARD_TITLE=?, FREEBANKBOARD_AUTHOR=?, FREEBANKBOARD_CONTENT=?, FREEBANKBOARD_UPDATE=SYSDATE where FREEBANKBOARD_ID=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getBoardTitle());
			pstmt.setString(2, boardDTO.getBoardAuthor());
			pstmt.setString(3, boardDTO.getBoardContent());
			pstmt.setInt(4, boardDTO.getBoardId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("공지사항 수정 실패");
			e.printStackTrace();
		}

		return result;
	}

	// 계좌조회
	public List<AccountDTO> selectAccountListAll() {
		List<AccountDTO> accountList = new ArrayList<AccountDTO>();

		String sql = "select * from FREEBANKACCOUNT order by FREEBANKACCOUNT_CREATE desc";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			// ResultSet : SQL 에서 가져오는 값 저장 객체
			ResultSet rs = pstmt.executeQuery();

			// ResultSet.next()
			// - 값이 있으면 true, 없으면 false
			while (rs.next()) {
				AccountDTO accountDTO = new AccountDTO();
				// ResultSet.get데이터타입(컬럼순서)
				// ResultSet.get데이터타입("컬럼이름");
				accountDTO.setAccountAccount(rs.getString(1));
				accountDTO.setAccountBalance(rs.getInt(2));
				accountDTO.setAccountCreate(rs.getDate(3));
				accountDTO.setAccountId(rs.getString(4));
				accountList.add(accountDTO);
			}

		} catch (Exception e) {
			System.out.println("계좌조회 실패");
			e.printStackTrace();
		}

		return accountList;
	}

	// 회원 목록 조회
	public List<UserDTO> selectAdminUserListAll() {
		List<UserDTO> userList = new ArrayList<UserDTO>();

		String sql = "select * from FREEBANKUSER order by FREEBANKUSER_CREATE desc";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			// ResultSet : SQL 에서 가져오는 값 저장 객체
			ResultSet rs = pstmt.executeQuery();

			// ResultSet.next()
			// - 값이 있으면 true, 없으면 false
			while (rs.next()) {
				UserDTO userDTO = new UserDTO();
				// ResultSet.get데이터타입(컬럼순서)
				// ResultSet.get데이터타입("컬럼이름");
				userDTO.setUserId(rs.getString(1));
				userDTO.setUserName(rs.getString(2));
				userDTO.setUserPwd(rs.getString(3));
				userDTO.setUserAge(rs.getInt(4));
				userDTO.setUserSex(rs.getString(5));
				userDTO.setUserEmail(rs.getString(6));
				userDTO.setUserAdmin(rs.getString(7));
				userDTO.setUserCreditRating(rs.getInt(8));
				userDTO.setUserTotal(rs.getInt(9));
				userDTO.setUserCreate(rs.getDate(10));
				userDTO.setUserUpdate(rs.getDate(11));
				userList.add(userDTO);
			}

		} catch (Exception e) {
			System.out.println("회원목록 조회실패");
			e.printStackTrace();
		}

		return userList;
	}

	// 상품등록하기
	public int insertAdminShop(ShopDTO shopDTO) {
		int result = 0;

		String sql = "insert into FREEBANKSHOP(FREEBANKSHOP_NAME, FREEBANKSHOP_CONTENTS, FREEBANKSHOP_PRICE, FREEBANKSHOP_ADMINID) values(?,?,?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, shopDTO.getShopName());
			pstmt.setString(2, shopDTO.getShopContents());
			pstmt.setInt(3, shopDTO.getShopPrice());
			pstmt.setString(4, shopDTO.getShopUserId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("상품등록실패");
			e.printStackTrace();
		}

		return result;
	}

	// 상품 목록 조회
	public List<ShopDTO> selectAdminShopListAll() {

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
			System.out.println("상품목록 조회실패");
			e.printStackTrace();
		}

		return shopList;
	}

	// 상품 삭제하기
	public int deleteAdminShop(int shopId) {

		int result = 0;

		String sql = "delete from FREEBANKSHOP where FREEBANKSHOP_ID=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, shopId);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("상품 삭제하기");
			e.printStackTrace();
		}

		return result;
	}

	// 상품 수정하기
	public int updateAdminShop(ShopDTO shopDTO) {

		int result = 0;

		String sql = "update FREEBANKSHOP set FREEBANKSHOP_NAME=?, FREEBANKSHOP_CONTENTS=?, FREEBANKSHOP_PRICE=?, FREEBANKSHOP_ADMINID=?, FREEBANKSHOP_UPDATE=SYSDATE where FREEBANKSHOP_ID=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, shopDTO.getShopName());
			pstmt.setString(2, shopDTO.getShopContents());
			pstmt.setInt(3, shopDTO.getShopPrice());
			pstmt.setString(4, shopDTO.getShopAdminId());
			pstmt.setInt(5, shopDTO.getShopId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("상품 수정하기");
			e.printStackTrace();
		}

		return result;
	}

	// 구매내역 목록조회
	public List<PurchaseListDTO> selectAdminPurchaseListAll() {
		List<PurchaseListDTO> purchaseList = new ArrayList<PurchaseListDTO>();

		String sql = "select * from FREEBANKPURCHASELIST order by FREEBANKPURCHASELIST_ID desc";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			// ResultSet : SQL 에서 가져오는 값 저장 객체
			ResultSet rs = pstmt.executeQuery();

			// ResultSet.next()
			// - 값이 있으면 true, 없으면 false
			while (rs.next()) {
				PurchaseListDTO purchaseListDTO = new PurchaseListDTO();
				// ResultSet.get데이터타입(컬럼순서);
				// ResultSet.get데이터타입("컬럼이름");
				purchaseListDTO.setPurchaseListId(rs.getInt(1));
				purchaseListDTO.setPurchaseListUserId(rs.getString(2));
				purchaseListDTO.setPurchaseListAccount(rs.getString(3));
				purchaseListDTO.setPurchaseListShopId(rs.getInt(4));
				purchaseListDTO.setPurchaseListShopName(rs.getString(5));
				purchaseListDTO.setPurchaseListTotalpayment(rs.getInt(6));
				purchaseListDTO.setPurchaseListTotalshopcount(rs.getInt(7));
				purchaseListDTO.setPurchaseListCreate(rs.getDate(8));
				purchaseList.add(purchaseListDTO);
			}

		} catch (Exception e) {
			System.out.println("구매내역 목록 조회 실패");
			e.printStackTrace();
		}

		return purchaseList;
	}
}
