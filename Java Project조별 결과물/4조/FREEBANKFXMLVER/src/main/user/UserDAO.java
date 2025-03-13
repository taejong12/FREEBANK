package main.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.shop.PurchaseListDTO;

public class UserDAO {

	Connection con;

	public UserDAO() {
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

	public int insertUser(UserDTO userDTO) {

		int result = 0;

		String sql = "insert into FREEBANKUSER(FREEBANKUSER_ID, FREEBANKUSER_NAME, FREEBANKUSER_PWD, FREEBANKUSER_AGE, FREEBANKUSER_SEX, FREEBANKUSER_EMAIL) values(?,?,?,?,?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userDTO.getUserId());
			pstmt.setString(2, userDTO.getUserName());
			pstmt.setString(3, userDTO.getUserPwd());
			pstmt.setInt(4, userDTO.getUserAge());
			pstmt.setString(5, userDTO.getUserSex());
			pstmt.setString(6, userDTO.getUserEmail());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("회원가입 실패");
			e.printStackTrace();
		}

		return result;
	}

	public UserDTO selectUserInfoById(String userId) {

		UserDTO userDTO = new UserDTO();

		String sql = "select * from FREEBANKUSER where FREEBANKUSER_ID = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
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
			}

		} catch (Exception e) {
			System.out.println("회원 조회 실패");
			e.printStackTrace();
		}

		return userDTO;

	}

	// 회원정보수정
	public int updateUserInfo(UserDTO userDTO) {
		int result = 0;

		String sql = "update FREEBANKUSER set FREEBANKUSER_NAME=?, FREEBANKUSER_AGE=?, FREEBANKUSER_EMAIL=?, FREEBANKUSER_UPDATE=SYSDATE where FREEBANKUSER_ID=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userDTO.getUserName());
			pstmt.setInt(2, userDTO.getUserAge());
			pstmt.setString(3, userDTO.getUserEmail());
			pstmt.setString(4, userDTO.getUserId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("회원정보 수정실패");
			e.printStackTrace();
		}

		return result;
	}

	// 회원탈퇴
	public int deleteUserInfo(UserDTO userDTO) {
		int result = 0;

		String sql = "delete from FREEBANKUSER where FREEBANKUSER_ID=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userDTO.getUserId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("회원탈퇴실패");
			e.printStackTrace();
		}

		return result;
	}

	// 회원 구매내역 조회
	public List<PurchaseListDTO> selectUserPLByID(String userId) {
		List<PurchaseListDTO> pLList = new ArrayList<PurchaseListDTO>();

		String sql = "select * from FREEBANKPURCHASELIST where FREEBANKPURCHASELIST_USERID=? order by FREEBANKPURCHASELIST_ID desc";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				PurchaseListDTO purchaseListDTO = new PurchaseListDTO();
				purchaseListDTO.setPurchaseListId(rs.getInt(1));
				purchaseListDTO.setPurchaseListUserId(rs.getString(2));
				purchaseListDTO.setPurchaseListAccount(rs.getString(3));
				purchaseListDTO.setPurchaseListShopId(rs.getInt(4));
				purchaseListDTO.setPurchaseListShopName(rs.getString(5));
				purchaseListDTO.setPurchaseListTotalpayment(rs.getInt(6));
				purchaseListDTO.setPurchaseListTotalshopcount(rs.getInt(7));
				purchaseListDTO.setPurchaseListCreate(rs.getDate(8));
				pLList.add(purchaseListDTO);
			}

		} catch (Exception e) {
			System.out.println("구매내역 목록조회 실패");
			e.printStackTrace();
		}

		return pLList;
	}

	// 구매내역 삭제
	public void deleteUserPurchaseList(String userId) {
		
		String sql = "delete from FREEBANKPURCHASELIST where FREEBANKPURCHASELIST_USERID=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);

			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("구매내역 삭제실패");
			e.printStackTrace();
		}
		
	}

	// 계좌 삭제
	public void deleteUserAccount(String userId) {
		String sql = "delete from FREEBANKACCOUNT where FREEBANKUSER_ID=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);

			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("계좌삭제실패");
			e.printStackTrace();
		}
		
	}

}
