
	
	package main.question.dao;

	import java.sql.Connection;

	import java.sql.DriverManager;
	import java.sql.PreparedStatement;

	import main.common.service.CommonService;
	import main.question.dto.QuestionDto;

	public class QuestionDao {

	Connection con;
		
		public QuestionDao() {
			
			try {
				Class.forName
				("oracle.jdbc.driver.OracleDriver");
				String url =  "jdbc:oracle:thin:@127.0.0.1:1521:XE";
				String user = "c##user01";
				String pass = "1234";
				
				con = DriverManager.getConnection(url,user,pass);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				System.out.println("오라클 연결 실패");
			}
		}

		public boolean insertquest(QuestionDto quest) {
			CommonService cs = new CommonService();
			String sql = "insert into quest values(?)";
			
			try {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, quest.getQuest());
				int result = pstmt.executeUpdate();
				pstmt.close();
				if(result >= 1) {
					cs.msgBox("문의하기", "문의하기 작성 여부", "문의하기가 접수되었습니다");
					return true;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
	}
		
	
//		public int insert(QuestionDto qd) {
//			String sql = "insert into FAQ values()";
//			
//			try {
//				PreparedStatement pstmt = con.prepareStatement(sql);
//				pstmt.setString(1, qd.getQuest());
//				
//				int result = pstmt.executeUpdate();
//				return result; 
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return 0;
//		}
		
	
