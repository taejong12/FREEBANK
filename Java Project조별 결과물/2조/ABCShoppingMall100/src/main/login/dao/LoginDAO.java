package main.login.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.common.service.CommonService;
import main.member.dto.Member;

public class LoginDAO {
	Connection con;
	CommonService cs;

	public LoginDAO() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName
			("oracle.jdbc.driver.OracleDriver");
			String url = 
					"jdbc:oracle:thin:@127.0.0.1:1521:XE";
			String user = "c##user01";
			String pass = "1234";

			con = DriverManager.getConnection(url,user,pass);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public boolean loginChk (String id, String pwd) { // 로그인 췍
		// TODO Auto-generated method stub
		String sql = "select decode(count(*), 1, 'true', 'false') from member where m_id=? and m_pw=?";
		boolean result = false;

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				result = Boolean.
						parseBoolean(rs.getString(1));
			}
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public List<Member> selectAll() {
		// TODO Auto-generated method stub
		String sql = "select * from member";
		List<Member> memberList = new ArrayList<Member>();

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(); // 결과값

			while(rs.next()) {
				Member m = new Member();
				m.setId(rs.getString(1));
				m.setName(rs.getString(2));
				m.setPwd(rs.getString(3));
				m.setAddress(rs.getString(4));
				m.setEmail(rs.getString(5));
				m.setPhoneNum(rs.getString(6));
				m.setFriendId(rs.getString(7));
				m.setPwdOk(rs.getString(8));
				memberList.add(m);
			}
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return memberList;
	}

	public Member selectInfo(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * from member where m_id = ?";
		//		List<Member> memberList = new ArrayList<Member>();
		//	Member dto = new Member();
//	
		Member result =null;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.setString(1, pstmt.toString());
			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				//result = rs.getString(1);
				result = new Member();
				result.setId(rs.getString("M_ID"));
				result.setName(rs.getString("M_NAME"));
				result.setPwd(rs.getString("M_PW"));
				result.setAddress(rs.getString("M_ADDRESS"));
				result.setEmail(rs.getString("M_EMAIL"));
				result.setPhoneNum(rs.getString("M_PHONENUMBER"));
				result.setFriendId(rs.getString("M_FRIENDID"));
				result.setPwdOk(rs.getString("M_PWOK"));

			}
			//			while(rs.next()) {
			//				Member m = new Member();
			//				
			//				m.setId(rs.getString(1));
			//			
			//			//	memberList.add(m);
			//			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//return memberList;
		return result;
	}

	public Member deleteMember(String id) {

		String sql = "DELETE FROM member WHERE m_id = ?";
		Member result = null;
		try {

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	
	public void memberModify(Member member) {
		String sql = "UPDATE member SET m_name = ?, m_email = ?, m_address = ?, m_phonenumber = ? WHERE m_id = ?";
		try {
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getPhoneNum());
			pstmt.setString(5, member.getId());
			
			int result = pstmt.executeUpdate(); // 행 숫자값
			cs = new CommonService();
			if(result >= 1) { 
			cs.msgBox("수정", "수정되었습니다", "정보가 수정되었습니다");
			
			}
			else {
				cs.msgBox("수정", "수정이 안됐습니다", "정보를 다시 입력해주세요");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
				
	}

}