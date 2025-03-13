package main.exam01.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.exam01.common.CommonDAO;
import main.exam01.common.CommonService;
import main.exam01.common.DatabaseConnection;
import main.exam01.dto.GymMachine;
import main.exam01.service.GymManagerService;
import main.exam01.service.GymService;


public class GymManagerDAO {
	private Connection con;
	CommonDAO CDAO;
	CommonService ls;
	DatabaseConnection dbc;

	public GymManagerDAO() {
		// TODO Auto-generated constructor stub
		try {
			if (con == null || con.isClosed()) {
				// DB 연결 코드 (예시: JDBC 연결)
				String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
				String user = "c##gym";
				String password = "1234";
				con = DriverManager.getConnection(url,user,password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private TextField txtType, txtMachineName, txtMet, txtGrowthFactor;

	@FXML
	public void handleInsertManager() {
		String Type = txtType.getText();
		String MachineName = txtMachineName.getText();
		double Met = Double.parseDouble(txtMet.getText());
		double GrowthFactor = Double.parseDouble(txtGrowthFactor.getText());

		GymMachine newManager = new GymMachine(Type,MachineName,Met,GrowthFactor);

		GymManagerService service = new GymManagerService();
		boolean result = service.insertMember(newManager);

		if (result) {
			System.out.println("머신 추가 성공");
			ls.windowClose(null);
		} else {
			System.out.println("머신 추가 실패");
		}
	}

	public boolean insertManager(GymMachine gm) {

		GymService ls = new GymService();

		String sql = "INSERT INTO gym_machine (Bodyparts, Machine, Met, Growthfactor) VALUES (?, ?, ?, ?)";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, gm.getBodyparts());
			pstmt.setString(2, gm.getMachine());
			pstmt.setDouble(3, gm.getMet());
			pstmt.setDouble(4, gm.getGrowthfactor());

			int result = pstmt.executeUpdate();  // SQL 실행

			if (result >= 1) {
				ls.msgBox("머신추가", "머신추가 성공", "머신추가를 완료되었습니다.");
				//return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 운동 기구 이름으로 MET 값 가져오기
	public GymMachine getMachineByName(String machineName) {
		String query = "SELECT machine_id, Machine, Met FROM gym_machine WHERE Machine = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
