package main.shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.account.AccountDTO;
import main.user.UserDTO;

public class ShopDAO {

	Connection con;

	public ShopDAO() {
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

	// 상품목록 전체조회
	public List<ShopDTO> shopSelectAll() {

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

	// 회원계좌 전체조회
	public List<AccountDTO> selectUserAccount(String userId) {

		List<AccountDTO> accountDTOList = new ArrayList<AccountDTO>();

		String sql = "select * from FREEBANKACCOUNT where FREEBANKUSER_ID=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, userId);

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
				accountDTOList.add(accountDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return accountDTOList;
	}

	// 계좌번호 정보 조회
	public AccountDTO selectUserAccoutByAccount(String userAccount) {

		AccountDTO accountDTO = new AccountDTO();

		String sql = "select * from FREEBANKACCOUNT where FREEBANKACCOUNT_ACCOUNT=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, userAccount);

			// ResultSet : SQL 에서 가져오는 값 저장 객체
			ResultSet rs = pstmt.executeQuery();

			// ResultSet.next()
			// - 값이 있으면 true, 없으면 false
			while (rs.next()) {
				// ResultSet.get데이터타입(컬럼순서)
				// ResultSet.get데이터타입("컬럼이름");
				accountDTO.setAccountAccount(rs.getString(1));
				accountDTO.setAccountBalance(rs.getInt(2));
				accountDTO.setAccountCreate(rs.getDate(3));
				accountDTO.setAccountId(rs.getString(4));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return accountDTO;
	}

	// 결제처리(계좌 테이블 업데이트), 잔고 수정
	public void updateAccountBalance(AccountDTO userAccountInfo) {

		String sql = "update FREEBANKACCOUNT set FREEBANKACCOUNT_BALANCE=? where FREEBANKACCOUNT_ACCOUNT=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userAccountInfo.getAccountBalance());
			pstmt.setString(2, userAccountInfo.getAccountAccount());

			int result = pstmt.executeUpdate();

			if (result >= 1) {
				System.out.println("결제중 잔고 수정이 완료되었습니다.");
			}

		} catch (Exception e) {
			System.out.println("결제중 잔고 수정 실패");
			e.printStackTrace();
		}

	}

	// 구매내역입력(구매내역 테이블 인서트)
	public void insertPurchaseList(PurchaseListDTO purchaseListDTO) {

		String sql = "insert into FREEBANKPURCHASELIST(FREEBANKPURCHASELIST_USERID, FREEBANKPURCHASELIST_ACCOUNT, FREEBANKPURCHASELIST_SHOPID, FREEBANKPURCHASELIST_SHOPNAME, FREEBANKPURCHASELIST_TOTALPAYMENT, FREEBANKPURCHASELIST_TOTALSHOPCOUNT, FREEBANKPURCHASELIST_CREATE) values(?,?,?,?,?,?,SYSDATE)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, purchaseListDTO.getPurchaseListUserId());
			pstmt.setString(2, purchaseListDTO.getPurchaseListAccount());
			pstmt.setInt(3, purchaseListDTO.getPurchaseListShopId());
			pstmt.setString(4, purchaseListDTO.getPurchaseListShopName());
			pstmt.setInt(5, purchaseListDTO.getPurchaseListTotalPayment());
			pstmt.setInt(6, purchaseListDTO.getPurchaseListTotalShopCount());

			int result = pstmt.executeUpdate();

			if (result >= 1) {
				System.out.println("구매내역 등록이 완료되었습니다.");
			}

		} catch (Exception e) {
			System.out.println("구매내역 등록 실패");
			e.printStackTrace();
		}

	}

	// 구매내역 총결제금액 합 = 회원 누적금액
	public int sumTotalPayment(String userId) {

		int sumTotalPayment = 0;

		String sql = "select sum(FREEBANKPURCHASELIST_TOTALPAYMENT) from FREEBANKPURCHASELIST where FREEBANKPURCHASELIST_USERID=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, userId);

			// ResultSet : SQL 에서 가져오는 값 저장 객체
			ResultSet rs = pstmt.executeQuery();

			// ResultSet.next()
			// - 값이 있으면 true, 없으면 false
			while (rs.next()) {
				sumTotalPayment = rs.getInt(1);
				System.out.println("누적금액 합산 완료");
			}

		} catch (Exception e) {
			System.out.println("누적 금액 합산 실패");
			e.printStackTrace();
		}

		return sumTotalPayment;
	}

	// 누적금액합산, 신용도변경(회원 테이블 업데이트)
	public void updateUserTotalAndCreditRating(UserDTO userDTO) {

		String sql = "update FREEBANKUSER set FREEBANKUSER_CREDITRATING=?, FREEBANKUSER_TOTAL=? where FREEBANKUSER_ID=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userDTO.getUserCreditRating());
			pstmt.setInt(2, userDTO.getUserTotal());
			pstmt.setString(3, userDTO.getUserId());

			int result = pstmt.executeUpdate();

			if (result >= 1) {
				System.out.println("누적금액, 신용도 업데이트가 완료되었습니다.");
			}

		} catch (Exception e) {
			System.out.println("누적금액, 신용 업데이트 실패");
			e.printStackTrace();
		}

	}

}
