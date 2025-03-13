package main.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.common.service.CommonService;
import main.member.dto.Member;

public class MemberDAO {
	Connection con;

	public MemberDAO() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			String user = "c##user01";
			String pass = "1234";

			con = DriverManager.getConnection(url,user,pass);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


	public void insertMember(Member m) {
		// TODO Auto-generated method stub
		CommonService cs = new CommonService();

		//		if(chkId(m.getMem_id())) {
		String sql = "insert into member values(?,?,?,?,?,?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);   // insert into member values(?,?,?,?,?,?,?);
			pstmt.setString(1, m.getMem_id());
			pstmt.setString(2, m.getMem_pw());
			pstmt.setString(3, m.getMem_name());
			pstmt.setString(4, m.getMem_email());
			pstmt.setString(5, m.getMem_phone());
			pstmt.setString(6, m.getMem_address());
			pstmt.setString(7, m.getMem_gender());
			int result = pstmt.executeUpdate();

			pstmt.close();
			if(result >= 1) {
				cs.msgBox("회원가입","회원가입여부",
						"회원가입에 성공하셨습니다.");
//				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//		} else {
		//			cs.msgBox("아이디", "아이디 중복", 
		//				"같은 아이디가 존재합니다. 다시 입력하세요.");
		//		}
//		return false;
	}

	public boolean chkId(String id) {
		// TODO Auto-generated method stub
		String sql = "select decode(count(*), 1,'false','true') from member where mem_id=?";
		boolean result = false;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				result = Boolean.parseBoolean(rs.getString(1));
			}
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}


}
