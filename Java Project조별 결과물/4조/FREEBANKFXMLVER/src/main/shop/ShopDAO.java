package main.shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.user.UserDTO;

public class ShopDAO {

	Connection con;

	public ShopDAO() {
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

	// 상품 리스트
	public List<ShopDTO> selectShopList() {
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

	// 구매내역 추가
	public void insertShopPLInfo(PurchaseListDTO purchaseListDTO) {

		String sql = "insert into FREEBANKPURCHASELIST(FREEBANKPURCHASELIST_USERID, FREEBANKPURCHASELIST_ACCOUNT, FREEBANKPURCHASELIST_SHOPID, FREEBANKPURCHASELIST_SHOPNAME, FREEBANKPURCHASELIST_TOTALPAYMENT, FREEBANKPURCHASELIST_TOTALSHOPCOUNT) values(?,?,?,?,?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, purchaseListDTO.getPurchaseListUserId());
			pstmt.setString(2, purchaseListDTO.getPurchaseListAccount());
			pstmt.setInt(3, purchaseListDTO.getPurchaseListShopId());
			pstmt.setString(4, purchaseListDTO.getPurchaseListShopName());
			pstmt.setInt(5, purchaseListDTO.getPurchaseListTotalpayment());
			pstmt.setInt(6, purchaseListDTO.getPurchaseListTotalshopcount());

			int result = pstmt.executeUpdate();

			if (result >= 1) {
				System.out.println("구매내역 추가완료");
			}

		} catch (Exception e) {
			System.out.println("구매내역 추가실패");
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
