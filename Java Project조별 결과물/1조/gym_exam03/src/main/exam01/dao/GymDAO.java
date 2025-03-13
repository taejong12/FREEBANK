package main.exam01.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.exam01.common.CommonService;
import main.exam01.dto.GymMember;
import main.exam01.service.GymService;

public class GymDAO {
	Connection con;
	CommonService ls;

	public GymDAO() {
		// TODO Auto-generated constructor stub
		ls = new CommonService();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			String user = "c##gym";
			String pass = "1234";

			con = DriverManager.getConnection(url,user,pass);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}

	public boolean loginChk(String id, String pwd) {
		String sql = "SELECT DECODE(COUNT(*), 1, 'true', 'false') FROM gym_member WHERE id=? AND pwd=?";
		boolean result = false;

		// DB 연결 확인
		if (this.con == null) {
			System.out.println("LoginDAO: DB 연결 실패 (Connection is null)");
			return false;
		}else {
			System.out.println("DB 연결 성공: " + con);
		}
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					result = Boolean.parseBoolean(rs.getString(1));
				}
			}
		} catch (Exception e) {
			System.out.println("LoginDAO: 로그인 체크 중 오류 발생");
			e.printStackTrace();
		}
		System.out.println("LoginDAO: loginChk 결과 -> " + result);
		return result;
	}


	public List<GymMember> selectAll() {
		String sql = "SELECT * FROM gym_member";
		List<GymMember> memberList = new ArrayList<GymMember>();

		if (this.con == null) {
			System.out.println("DB 연결이 되어 있지 않습니다.");
			return memberList; // DB 연결이 안된 경우 빈 리스트 반환
		}

		try (PreparedStatement pstmt = con.prepareStatement(sql); 
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				GymMember m = new GymMember();
				m.setId(rs.getString(1));
				m.setPwd(rs.getString(2));
				m.setName(rs.getString(3));
				m.setGender(rs.getString(4));
				m.setAge(rs.getInt(5));
				m.setHeight(rs.getDouble(6));
				m.setWeight(rs.getDouble(7));
				m.setBodyFat(rs.getDouble(8));
				m.setMet_consumption(rs.getDouble(9));
				m.setGrowthfactor_consumption(rs.getDouble(10));
				m.setTotal_met_consumption(rs.getDouble(11));
				m.setTotal_growthfactor_consumption(rs.getDouble(12));
				m.setMem_type(rs.getString(13));
				memberList.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberList;
	}

	private boolean chkId(String id) {
		String sql = "select decode(count(*), 1,'false','true')  from gym_member where id=?";
	boolean result = false;
	try {
		PreparedStatement pstmt =con.prepareStatement(sql);
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
	
	
	// 회원가입 페이지
	//-------------------------------------------------
	
	@FXML
	private TextField txtId, txtPwd, txtName, txtGender, txtAge, txtHeight, txtWeight, txtBodyfat;

	@FXML
	public void handleInsertMember() {
	    String id = txtId.getText();
	    String pwd = txtPwd.getText();
	    String name = txtName.getText();
	    String gender = txtGender.getText();
	    
	    int age = Integer.parseInt(txtAge.getText());
	    double height = Double.parseDouble(txtHeight.getText());
	    double weight = Double.parseDouble(txtWeight.getText());
	    double bodyFat = Double.parseDouble(txtBodyfat.getText());
	    
	    // MET 및 성장 요인 소비량 (초기값 설정 가능)
	    double metConsumption = 0.0;
	    double growthfactorConsumption = 0.0;
	    double totalMetConsumption = 0.0;
	    double totalGrowthfactorConsumption = 0.0;
	    
	    //String memType = txtMemType.getText();
	    String memType = "회원";
	    
	    // GymMember 객체 생성
	    GymMember newMember = new GymMember(id, pwd, name, gender, age, height, weight, bodyFat, 0.0, 0.0, 0.0, 0.0, "회원");
	    
	    // GymService 객체 생성 후 insertMember() 호출
	    GymService service = new GymService();
	    boolean result = service.insertMember(newMember);

	    if (result) {
	        System.out.println("회원가입 성공");
	        ls.windowClose(null);
	    } else {
	        System.out.println("회원가입 실패");
	    }
	}

	
	public boolean insertMember(GymMember m) {
	    GymService ls = new GymService();

	    if (chkId(m.getId())) {  // 아이디 중복 확인
	    	
	    	
	        String sql = "INSERT INTO gym_member (id, pwd, name, gender, age, height, weight, bodyFat, met_consumption, growthfactor_consumption, total_met_consumption, total_growthfactor_consumption, mem_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	            // GymMember 객체에서 값을 가져와 PreparedStatement에 설정
	        	String mem_type = m.getMem_type();
	        	if (mem_type == null || mem_type.isEmpty()) {
		    		mem_type = "회원"; // 기본값을 설정하거나 예외 처리
		        }
	            pstmt.setString(1, m.getId());
	            pstmt.setString(2, m.getPwd());
	            pstmt.setString(3, m.getName());
	            pstmt.setString(4, m.getGender());
	            pstmt.setInt(5, m.getAge());
	            pstmt.setDouble(6, m.getHeight());
	            pstmt.setDouble(7, m.getWeight());
	            pstmt.setDouble(8, m.getBodyFat());
	            pstmt.setDouble(9, m.getMet_consumption());
	            pstmt.setDouble(10, m.getGrowthfactor_consumption());
	            pstmt.setDouble(11, m.getTotal_met_consumption());
	            pstmt.setDouble(12, m.getTotal_growthfactor_consumption());
	            pstmt.setString(13, mem_type);  // mem_type (MEM_TYPE을 GymMember 객체에서 가져옴)

	            int result = pstmt.executeUpdate();  // SQL 실행

	            if (result >= 1) {
	                ls.msgBox("회원가입", "회원가입 성공", "회원가입이 완료되었습니다.");
	                return true;
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } else {
	        ls.msgBox("아이디", "아이디 중복", "같은 아이디가 존재합니다. 다시 입력하세요.");
	    }

	    return false;
	}
	//-------------------------------------------------
	
	
}
