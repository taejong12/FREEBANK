package main.exam01.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.exam01.common.CommonService;
import main.exam01.dao.GymManagerDAO;
import main.exam01.dao.GymMemberDAO;

import main.exam01.dao.GymMemberDAO;
import main.exam01.dao.GymManagerDAO;
import main.exam01.dto.GymMember;
import main.exam01.dto.GymMachine;

public class GymMemberService {
	private final GymMemberDAO gymMemberDAO;
	private final GymManagerDAO gymMachineDAO;
	private GymMember loggedInMember; // 로그인한 회원 정보 저장

	public GymMemberService(GymMemberDAO gymMemberDAO, GymManagerDAO gymMachineDAO) {
		this.gymMemberDAO = gymMemberDAO;
		this.gymMachineDAO = gymMachineDAO;
	}

	// 로그인한 사용자 정보 가져오기
	public void setLoggedInMember(String memberId, String memberPwd) {
		this.loggedInMember = gymMemberDAO.getMemberById(memberId);
	}

	// 운동 기구 선택 후 칼로리 소모량 계산
	public void calculateCalories(String machineName) {
		if (loggedInMember == null) {
			System.out.println("로그인된 사용자 정보가 없습니다.");
			//return 0;
		}
		GymMachine machine = gymMachineDAO.getMachineByName(machineName);
		if (machine == null) {
			System.out.println("선택한 운동 기구가 존재하지 않습니다.");
			//return 0;
		}
		
		double weight = loggedInMember.getWeight(); // 사용자의 체중 (kg)
		double met = machine.getMet(); // 운동 기구의 MET 값
		double growthfactor =machine.getGrowthfactor();
		int sets=0;
		
		double calorie=0;
		double Muscle=0;
		// 운동 결과 계산
		double timePerSet = 40;
		double totalTime = (timePerSet * sets) / 3600;
		double caloriesBurned = met * weight * totalTime * 1.05;
		// 하루 총 칼로리, 총 근육 증가량
		calorie+=caloriesBurned;
	}
	
	public double muscleCalories(String machineName) {
		if (loggedInMember == null) {
			System.out.println("로그인된 사용자 정보가 없습니다.");
			return 0;
		}
		GymMachine machine = gymMachineDAO.getMachineByName(machineName);
		if (machine == null) {
			System.out.println("선택한 운동 기구가 존재하지 않습니다.");
			return 0;
		}
		return 0;
	}
}
