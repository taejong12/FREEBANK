package main.account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.user.UserDTO;

public class AccountDAO {

	Connection con;

	public AccountDAO() {
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

	// 계좌 존재 여부 확인
	public int selectAccount(String account) {

		int accountCheck = 0;

		String sql = "select count(*) from FREEBANKACCOUNT where FREEBANKACCOUNT_ACCOUNT=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, account);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				accountCheck = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("계좌 중복 체크 조회 실패");
			e.printStackTrace();
		}
		return accountCheck;
	}

	// 계좌개설
	public int insertAccount(AccountDTO accountDTO) {

		int result = 0;

		String sql = "insert into FREEBANKACCOUNT(FREEBANKACCOUNT_ACCOUNT, FREEBANKUSER_ID) values(?, ?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountDTO.getAccountAccount());
			pstmt.setString(2, accountDTO.getAccountId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("계좌 개설 실패");
			e.printStackTrace();
		}

		return result;
	}

	// 해당하는 회원의 모든 계좌 목록
	public List<AccountDTO> selectUserAccountAll(UserDTO userDTO) {

		List<AccountDTO> accountDTOList = new ArrayList<AccountDTO>();

		String sql = "select * from FREEBANKACCOUNT where FREEBANKUSER_ID=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userDTO.getUserId());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				AccountDTO accountDTO = new AccountDTO();
				accountDTO.setAccountAccount(rs.getString(1));
				accountDTO.setAccountBalance(rs.getInt(2));
				accountDTO.setAccountCreate(rs.getDate(3));
				accountDTO.setAccountId(rs.getString(4));
				accountDTOList.add(accountDTO);
			}

		} catch (Exception e) {
			System.out.println("회원 계좌 조회 실패");
			e.printStackTrace();
		}

		return accountDTOList;

	}

	// 회원의 계좌번호 조회
	public AccountDTO selectUserAccountByAccount(String userAccount) {

		AccountDTO accountDTO = new AccountDTO();

		String sql = "select * from FREEBANKACCOUNT where FREEBANKACCOUNT_ACCOUNT=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userAccount);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				accountDTO.setAccountAccount(rs.getString(1));
				accountDTO.setAccountBalance(rs.getInt(2));
				accountDTO.setAccountCreate(rs.getDate(3));
				accountDTO.setAccountId(rs.getString(4));
			}

		} catch (Exception e) {
			System.out.println("회원 계좌 조회 실패");
			e.printStackTrace();
		}

		return accountDTO;

	}

	// 회원 비밀번호 조회
	public String selectUserPwdCheck(String userId) {

		String userPwd = null;

		String sql = "select FREEBANKUSER_PWD from FREEBANKUSER where FREEBANKUSER_ID=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				userPwd = rs.getString(1);
			}

		} catch (Exception e) {
			System.out.println("회원 비밀번호 조회 실패");
			e.printStackTrace();
		}

		return userPwd;

	}

	// 계좌잔고 업데이트
	public void updateAccountBalance(AccountDTO accountDTO) {

		String sql = "update FREEBANKACCOUNT set FREEBANKACCOUNT_BALANCE=? where FREEBANKACCOUNT_ACCOUNT=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, accountDTO.getAccountBalance());
			pstmt.setString(2, accountDTO.getAccountAccount());

			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("계좌잔고 업데이트 실패");
			e.printStackTrace();
		}
	}

	// 계좌 해지
	public void deleteAccount(String userAccount) {

		String sql = "delete from FREEBANKACCOUNT where FREEBANKACCOUNT_ACCOUNT=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userAccount);

			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("계좌해지 실패");
			e.printStackTrace();
		}
	}

	// 유저 아이디로 계좌 조회
	public List<AccountDTO> selectUserAccountById(String userId) {

		List<AccountDTO> accountDTOList = new ArrayList<AccountDTO>();

		String sql = "select * from FREEBANKACCOUNT where FREEBANKUSER_ID=?";

		try {

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				AccountDTO accountDTO = new AccountDTO();
				accountDTO.setAccountAccount(rs.getString(1));
				accountDTO.setAccountBalance(rs.getInt(2));
				accountDTO.setAccountCreate(rs.getDate(3));
				accountDTO.setAccountId(rs.getString(4));
				accountDTOList.add(accountDTO);
			}

		} catch (Exception e) {
			System.out.println("회원 아이디로 계좌 조회 실패");
			e.printStackTrace();
		}

		return accountDTOList;

	}

}
