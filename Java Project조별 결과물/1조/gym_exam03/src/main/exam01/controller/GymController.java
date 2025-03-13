package main.exam01.controller;

import main.exam01.service.GymManagerService;
import main.exam01.service.GymService;
import main.exam01.dto.GymMember;
import main.exam01.dao.GymDAO;
import main.exam01.dao.GymMemberDAO;
import main.exam01.dto.GymMachine;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GymController {

	//private GymService gymService;
	Parent root;
	GymService ls;
	GymManagerService gs;
	GymDAO gm;
	//main.exam01.controller.GymController ctrl; // 어떤 코드인지 모르겠음
	@FXML private TextField txtName;
	@FXML private TextField txtId;
	@FXML private PasswordField txtPwd;
	@FXML private TextField txtGender;
	@FXML private TextField txtAge;
	@FXML private TextField txtHeight;
	@FXML private TextField txtWeight;
	@FXML private TextField txtBodyfat;

	@FXML private TextField txtType;
	@FXML private TextField txtMachineName;
	@FXML private TextField txtMet;
	@FXML private TextField txtGrowthFactor;
	@FXML private ComboBox<String> cmbPart;
	@FXML private TextField id;
	@FXML private ComboBox<String> cmbMachine;
	@FXML private TextField totalCalories;
    @FXML private TextField muscleGrowth;
	
    private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";  // 예시 URL, 실제 DB URL로 변경
    private static final String USER = "gym";
    private static final String PASSWORD = "1234";
	
	public void initialize() {
		System.out.println("id TextField: " + id);
		//cmbPart.setItems(FXCollections.observableArrayList("등", "가슴", "어깨", "하체"));
	}
	
	


	public GymController() {
		//ctrl = new main.exam01.controller.GymController();
		this.ls = new GymService();
		this.gm = new GymDAO();
		this.gs = new GymManagerService();
	}

	public boolean registerMember(GymMember member) {
		return ls.registerMember(member);
		// 회원가입
	}

	// 로그인 페이지 -> 정리 잘못해서 gymService의 msgBox, windowClose작동함
	public void loginProc() throws IOException {
		ls.loginProc(root); // 로그인 클릭 버튼
	}

	public void logout() {
		ls.logout(root);
	}

	public void Back() {
		ls.Back(root);
	}

	public void None() {
		ls.None(root);
	}
	

	public List<GymMachine> getMachinesByBodyPart(String bodyPart) {
		return ls.getMachinesByBodyPart(bodyPart);
	}

	public boolean performExercise(String memberId, String machineName, double weight, int sets) {
		return ls.performExercise(memberId, machineName, weight, sets);
	}

	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
	}

	// 최소 버튼
	public void cancelProc(ActionEvent event) {
		ls.windowClose(event); // 최소 클릭 버튼
	}

	// 회원 회원가입 창으로 가는 버튼
	public void membershipProc() {
		ls.setRoot(root);
		ls.membershipProc(root);
	}

	public void joinMember() {
		ls.joinMember(root);
		ls.setRoot(root);

		// 어디서 오류 나는지 확인하기 위한 코드
		String name = txtName.getText();
		String id = txtId.getText();
		String password = txtPwd.getText();
		String gender = txtGender.getText();
		String age = txtAge.getText();
		String height = txtHeight.getText();
		String weight = txtWeight.getText();
		String bodyFat = txtBodyfat.getText();

		// 데이터 검증 로직 추가 가능
		System.out.println("회원 가입 정보:");
		System.out.println("Name: " + name + ", ID: " + id + ", Password: " + password);
		System.out.println("Gender: " + gender + ", Age: " + age + ", Height: " + height);
		System.out.println("Weight: " + weight + ", BodyFat: " + bodyFat);
	}

	public void insertMember() {
		ls.setRoot(root);
	}

	public void openManger() {
		if (gs == null) {
			System.out.println("gymManagerService가 null 상태였으므로 초기화합니다.");
			gs = new GymManagerService();
		}
		String Type = txtType.getText();
		String MachineName = txtMachineName.getText();
		String Met = txtMet.getText();
		String GrowthFactor = txtGrowthFactor.getText();

		System.out.println("머신 정보:");
		System.out.println("Type: " + Type + ", MachineName: " + MachineName);
		System.out.println("Met: " + Met + ", GrowthFactor: " + GrowthFactor);

		gs.openManager(root);
		ls.setRoot(root);
	}

//	private Label lbName, lbGender, lbAge, lbHeight, lbWeight, lbBodyFat;
//	public void openMember(Parent root,String id) {
//		gs.openMember(root,id);
//		ls.setRoot(root);
//	}
//	
//	public void setMemberInfo(GymMember member2) {
//        if (member2 != null) {
//            lbName.setText(member2.getName());
//            lbGender.setText(member2.getGender());
//            lbAge.setText(String.valueOf(member2.getAge()));
//            lbHeight.setText(String.valueOf(member2.getHeight()));
//            lbWeight.setText(String.valueOf(member2.getWeight()));
//            lbBodyFat.setText(String.valueOf(member2.getBodyFat()));
//        }
//    }


	    // 누적 값을 저장할 변수들
	    private double totalCaloriesAccumulated = 0;
	    private double totalMuscleGrowthAccumulated = 0;

	    // 다른 변수들...

	    // 데이터 업데이트 함수
	    public void updateData() {
	        // TextField에서 현재 값을 가져오기
	        String totalCaloriesValue = totalCalories.getText();
	        String muscleGrowthValue = muscleGrowth.getText();

	        // 현재 값을 double로 변환 (예시로 단위가 정수일 수도 있으므로 적절하게 변환)
	        double caloriesBurned = Double.parseDouble(totalCaloriesValue);
	        double muscleGrowth = Double.parseDouble(muscleGrowthValue);

	        // 애플리케이션 내에서 누적 값 계산
	        totalCaloriesAccumulated += caloriesBurned;
	        totalMuscleGrowthAccumulated += muscleGrowth;

	        // 누적된 값 출력 (디버그용)
	        System.out.println("Total Calories Accumulated: " + totalCaloriesAccumulated);
	        System.out.println("Total Muscle Growth Accumulated: " + totalMuscleGrowthAccumulated);

	        // 데이터베이스 업데이트 (현재 세션 데이터)
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
	            // 현재 세션의 MET_CONSUMPTION과 GROWTHFACTOR_CONSUMPTION 값 업데이트
	            String sqlUpdateCurrent = "UPDATE gym_member SET MET_CONSUMPTION = ?, GROWTHFACTOR_CONSUMPTION = ? WHERE id = ?";
	            try (PreparedStatement statement = connection.prepareStatement(sqlUpdateCurrent)) {
	                statement.setDouble(1, caloriesBurned);
	                statement.setDouble(2, muscleGrowth);
	                statement.setString(3, "hong");  // 사용자 ID (예시로 "hong")

	                int rowsUpdated = statement.executeUpdate();
	                if (rowsUpdated > 0) {
	                    System.out.println("현재 세션 데이터가 업데이트되었습니다.");
	                } else {
	                    System.out.println("현재 세션에 대한 업데이트가 없습니다.");
	                }
	            }

	            // 누적 값 업데이트 (총합)
	            String sqlUpdateTotal = "UPDATE gym_member SET TOTAL_MET_CONSUMPTION = ?, TOTAL_GROWTHFACTOR_CONSUMPTION = ? WHERE id = ?";
	            try (PreparedStatement statement = connection.prepareStatement(sqlUpdateTotal)) {
	                statement.setDouble(1, totalCaloriesAccumulated);  // 누적 MET 소비
	                statement.setDouble(2, totalMuscleGrowthAccumulated);  // 누적 근육 성장
	                statement.setString(3, "hong");  // 사용자 ID (예시로 "hong")

	                int rowsUpdated = statement.executeUpdate();
	                if (rowsUpdated > 0) {
	                    System.out.println("누적 데이터가 업데이트되었습니다.");
	                } else {
	                    System.out.println("누적 데이터에 대한 업데이트가 없습니다.");
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("데이터 업데이트 중 오류가 발생했습니다.");
	        }
	    }
	}

	
	



