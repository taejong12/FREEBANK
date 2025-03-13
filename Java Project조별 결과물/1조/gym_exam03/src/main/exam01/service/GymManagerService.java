package main.exam01.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.exam01.common.CommonService;
import main.exam01.controller.GymController;
import main.exam01.dao.GymManagerDAO;
import main.exam01.dao.GymMemberDAO;
import main.exam01.dto.GymMachine;
import main.exam01.dto.GymMember;

public class GymManagerService {
	CommonService cs;
	GymManagerDAO GMDAO;
	GymMemberDAO gm;
	

	public GymManagerService() {
		// TODO Auto-generated constructor stub
		this.GMDAO = new GymManagerDAO();
	}	

	private void clearFields(TextField... fields) {
		for (TextField field : fields) {
			field.clear();
		}
	}

	public boolean insertMember(GymMachine newManager) {
		// TODO Auto-generated method stub
		return false;
	}

	public void managershipProc(Parent root) {
		// TODO Auto-generated method stub
		Stage memberForm = (Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/exam01/fxml/managerpage.fxml"));
		Parent member = null;
		try {
			member = loader.load();
			memberForm.setScene(new Scene(member));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		GymController ctrl = loader.getController();
		ctrl.setRoot(member);

		memberForm.setTitle("관리자창");
		memberForm.show();
	}


	public void openManager(Parent root) {
		// TODO Auto-generated method stub
		// root이 null이 아닌지 확인
		if (root == null) {
			System.out.println("오류: memberForm이 null입니다.");
			return;
		}
		TextField Type = (TextField) root.lookup("#txtType");
		TextField MachineName = (TextField) root.lookup("#txtMachineName");
		TextField Met = (TextField) root.lookup("#txtMet");
		TextField GrowthFactor = (TextField) root.lookup("#txtGrowthFactor");

		// FXML에서 필드가 null인지 확인
		if (Type == null || MachineName == null || Met == null || GrowthFactor == null) {
			System.out.println("오류: FXML에 하나 이상의 필드가 누락되었습니다.");
			return;
		}

		if (Type.getText().isEmpty()) {
			cs.msgBox("입력오류", "타입 입력 오류", "타입이 입력되지 않았습니다. 다시 입력하세요");
			Type.requestFocus();
			return;
		} else if (MachineName.getText().isEmpty()) {
			cs.msgBox("입력오류", "머신이름 입력 오류", "머신이름이 입력되지 않았습니다. 다시 입력하세요");
			MachineName.requestFocus();
			return;
		} else if (Met.getText().isEmpty()) {
			cs.msgBox("입력오류", "칼로리계수 입력 오류", "칼로리계수가 입력되지 않았습니다. 다시 입력하세요");
			Met.requestFocus();
			return;
		}else if (GrowthFactor.getText().isEmpty()) {
			cs.msgBox("입력오류", "근육증가량 입력 오류", "근육증가량이 입력되지 않았습니다. 다시 입력하세요");
			GrowthFactor.requestFocus();
			return;
		}

		GymMachine gm = new GymMachine();
		gm.setBodyparts(Type.getText());
		gm.setMachine(MachineName.getText());
		gm.setMet(Double.parseDouble(Met.getText()));
		gm.setGrowthfactor(Double.parseDouble(GrowthFactor.getText()));

		if (GMDAO.insertManager(gm)) {
			Stage s = (Stage) root.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/exam01/fxml/login.fxml"));
			try {
				root = loader.load();
				s.setScene(new Scene(root));
			} catch (Exception e) {
				e.printStackTrace();
			}

			GymController ctrl = loader.getController();
			//ctrl.setRoot(memberForm);
			s.setTitle("로그인");
			s.show();
		} else {
			clearFields(Type,MachineName,Met,GrowthFactor);
		}

	}

	public void openMember(Parent root) {
		Stage memberForm = (Stage) root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/exam01/fxml/memberpage.fxml"));
		Parent member = null;

		try {
			member = loader.load();
			memberForm.setScene(new Scene(member));
		} catch (Exception e) {
			e.printStackTrace();
		}

		GymController ctrl = loader.getController();
		ctrl.setRoot(member);

		// "part" 콤보박스 가져오기
		ComboBox<String> cmbPart = (ComboBox<String>) member.lookup("#cmbPart");
		String[] partOptions = {"등", "가슴", "어깨", "하체"};
		cmbPart.getItems().addAll(FXCollections.observableArrayList(partOptions));

		// "machine" 콤보박스 가져오기
		ComboBox<String> cmbMachine = (ComboBox<String>) member.lookup("#cmbMachine");

		// "kg" (무게) 콤보박스 가져오기
		ComboBox<String> cmbWeight = (ComboBox<String>) member.lookup("#kg");
		String[] weightOptions = {"5", "10", "15", "20", "25", "30", "40", "50", "60", "70", "80", "90", "100"};
		cmbWeight.getItems().addAll(FXCollections.observableArrayList(weightOptions));

		// "set" (세트 수) 콤보박스 가져오기
		ComboBox<String> cmbSet = (ComboBox<String>) member.lookup("#set");
		String[] setOptions = {"1", "2", "3", "4", "5"};
		cmbSet.getItems().addAll(FXCollections.observableArrayList(setOptions));

		// 체중 입력 필드 가져오기
		TextField txtWeight = (TextField) member.lookup("#txtGender"); // 체중 입력 필드

		// "Total Calories" 및 "muscleGrowth" 출력 필드 가져오기
		TextField totalCalories = (TextField) member.lookup("#totalCalories");
		TextField muscleGrowth = (TextField) member.lookup("#muscleGrowth");

		// cmbPart 선택 시, cmbMachine 업데이트
		cmbPart.setOnAction(event -> {
			String selectedPart = cmbPart.getValue();
			ObservableList<String> machines = FXCollections.observableArrayList();

			if (selectedPart != null) {
				switch (selectedPart) {
				case "가슴":
					machines.addAll("펙텍 플라이", "레그 프레스", "체스트 프레스");
					break;
				case "등":
					machines.addAll("랫풀다운", "스미스 머신 체스트 프레스", "풀업", "원암 로우 머신");
					break;
				case "어깨":
					machines.addAll("스미스 머신 체스트 프레스", "랫풀다운", "케이블 레터럴 레이즈", "숄더 프레스");
					break;
				case "하체":
					machines.addAll("레그컬 익스텐션", "힙 어덕션", "레그프레스", "사이드 레이즈");
					break;
				}
			}

			cmbMachine.setItems(machines);
			cmbMachine.getSelectionModel().clearSelection();
		});

		// 값이 변경될 때 자동으로 결과 계산
		cmbMachine.setOnAction(event -> calculateResults(cmbMachine, cmbWeight, cmbSet, txtWeight, totalCalories, muscleGrowth));
		cmbWeight.setOnAction(event -> calculateResults(cmbMachine, cmbWeight, cmbSet, txtWeight, totalCalories, muscleGrowth));
		cmbSet.setOnAction(event -> calculateResults(cmbMachine, cmbWeight, cmbSet, txtWeight, totalCalories, muscleGrowth));
		memberForm.setTitle("회원창");
		memberForm.show();
	}

	// 🔹 결과 계산 함수
	private void calculateResults(ComboBox<String> cmbMachine, ComboBox<String> cmbWeight, ComboBox<String> cmbSet, 
			TextField txtWeight, TextField totalCalories, TextField muscleGrowth) {
		String selectedMachine = cmbMachine.getValue();
		String selectedWeight = cmbWeight.getValue();
		String selectedSet = cmbSet.getValue();
		String weightStr = txtWeight.getText(); // 체중 입력 필드 값

		// 선택된 값이나 체중이 비어있으면 계산하지 않음
		if (selectedMachine == null || selectedWeight == null || selectedSet == null || weightStr.isEmpty()) {
			return;
		}

		double weight = Double.parseDouble(selectedWeight); // 사용자가 선택한 운동 기구의 무게
		int setCount = Integer.parseInt(selectedSet); // 세트 수
		double userWeight = Double.parseDouble(weightStr); // 사용자의 체중

		// 운동 기구별 MET 값과 Growth Factor 설정
		double met = 0; // 기본값
		double growthFactor = 0; // 기본값

		switch (selectedMachine) {
			case "펙텍 플라이": met = 5.2; growthFactor = 0.15; break;
			case "레그 프레스": met = 7.0; growthFactor = 0.22; break;
			case "체스트 프레스": met = 6.5; growthFactor = 0.22; break;
			case "랫풀다운": met = 6.0; growthFactor = 0.15; break;
			case "스미스 머신 체스트 프레스": met = 5.5; growthFactor = 0.15; break;
			case "풀업": met = 8.0; growthFactor = 0.25; break;
			case "원암 로우 머신": met = 5.8; growthFactor = 0.18; break;
			case "케이블 레터럴 레이즈": met = 4.5; growthFactor = 0.12; break;
			case "숄더 프레스": met = 6.8; growthFactor = 0.2; break;
			case "레그컬 익스텐션": met = 5.7; growthFactor = 0.19; break;
			case "힙 어덕션": met = 4.2; growthFactor = 0.14; break;
			case "레그프레스": met = 5.5; growthFactor = 0.15; break;
			case "사이드 레이즈": met = 3.8; growthFactor = 0.1; break;
		}

		// 계산식 적용
		// 칼로리 계산: ((40 * 세트 수) / 3600) * MET * 체중 * 1.05
		double timePerSet = 40;
        double totalTime = (timePerSet * setCount) / 3600;
        double caloriesBurned = met * weight * totalTime * 1.05;
        double trainingVolume = weight * 10 * setCount;
        double muscleGrowthValue = trainingVolume / 10000 * growthFactor;
//		double totalCal = ((40 * setCount) / 3600) * met * 60 * 1.05;
//
//		// 근육 성장 계산: (무게 * 10 * 세트 수) / 10000 * Growth Factor
//		double muscleGrowthValue = (weight * 10 * setCount) / 10000 * growthFactor;

		// 결과값을 텍스트 필드에 출력
		totalCalories.setText(String.format("%.4f", caloriesBurned));
		muscleGrowth.setText(String.format("%.4f", muscleGrowthValue));
		
	}
}
