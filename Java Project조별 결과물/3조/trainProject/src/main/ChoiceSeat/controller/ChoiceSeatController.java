package main.ChoiceSeat.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import main.ChoiceSeat.dao.ChoiceSeatDAO;
import main.ChoiceSeat.service.ChoiceSeatService;
import main.common.service.CommonService;
import main.common.service.UserSession;

public class ChoiceSeatController {
	Parent root;
	ChoiceSeatService css;
	CommonService cs;

	public ChoiceSeatController() {
		// TODO Auto-generated constructor stub
		css = new ChoiceSeatService();
		cs = new CommonService();
	}

	public void setRoot(Parent root) {
		this.root = root;
	}

	public void mainHome() {
		cs.logonMainPage(root);
	}

	public void PayProc() {
		css.PayProc(root);
	}

	public void backProc() {
		css.backProc(root);
	}
	
	@FXML
	private Label TR_N;

	public void initialize() {
		  String selectedTrainName = UserSession.getInstance().getSelectedTrainName();
		  TR_N.setText(selectedTrainName);
	}

	@FXML private RadioButton seat1;
	@FXML private RadioButton seat2;
	@FXML private RadioButton seat3;
	@FXML private RadioButton seat4;
	@FXML private RadioButton seat5;
	@FXML private RadioButton seat6;
	@FXML private RadioButton seat7;
	@FXML private RadioButton seat8;
	@FXML private RadioButton seat9;
	@FXML private RadioButton seat10;
	@FXML private RadioButton seat11;
	@FXML private RadioButton seat12;
	@FXML private RadioButton seat13;
	@FXML private RadioButton seat14;
	@FXML private RadioButton seat15;
	@FXML private RadioButton seat16;

	@FXML private ToggleGroup seatGroup;

	private ChoiceSeatDAO csDAO = new ChoiceSeatDAO();  // 좌석 예약 처리 클래스

	
	public void initialize(URL location, ResourceBundle resources) {
	    String selectedTrainNumber = UserSession.getInstance().getSelectedTrainNumber();
	    List<String> reservedSeats = csDAO.getReservedSeats(selectedTrainNumber);

	    for (String seat : reservedSeats) {
	        disableReservedSeat(seat);
	    }
	}

	// 예약된 좌석을 비활성화하는 메서드
	private void disableReservedSeat(String seatNumber) {
	    switch (seatNumber) {
	        case "1": seat1.setDisable(true); break;
	        case "2": seat2.setDisable(true); break;
	        case "3": seat3.setDisable(true); break;
	        case "4": seat4.setDisable(true); break;
	        case "5": seat5.setDisable(true); break;
	        case "6": seat6.setDisable(true); break;
	        case "7": seat7.setDisable(true); break;
	        case "8": seat8.setDisable(true); break;
	        case "9": seat9.setDisable(true); break;
	        case "10": seat10.setDisable(true); break;
	        case "11": seat11.setDisable(true); break;
	        case "12": seat12.setDisable(true); break;
	        case "13": seat13.setDisable(true); break;
	        case "14": seat14.setDisable(true); break; 
	        case "15": seat15.setDisable(true); break;
	        case "16": seat16.setDisable(true); break;
	    }
	}
	
	
	public void onReserveButtonClicked() {
	    RadioButton selectedSeat = (RadioButton) seatGroup.getSelectedToggle();

	    if (selectedSeat != null) {
	        String selectedSeatNumber = selectedSeat.getText();  
	        String trainNumber = UserSession.getInstance().getSelectedTrainNumber();
	        String userId = UserSession.getInstance().getUserId(); 

	        // DB에 예약 정보 저장
	        boolean isReserved = csDAO.reserveSeat(selectedSeatNumber, trainNumber, userId);
	        

	        if (isReserved) {
	            selectedSeat.setDisable(true);  // UI에서 좌석 비활성화
	            selectedSeat.setText(selectedSeat.getText() + " (예약됨)");
	            UserSession.getInstance().setSeat(selectedSeat.getText());
	        } else {
	        	cs.msgBox("예약 X", "예약된 좌석입니다", "예약 X");
	            System.out.println("이미 예약된 좌석입니다.");
	        }
	    }
	}
	
//	@FXML
//	public void initialize() {
//		// RadioButton들을 ToggleGroup에 추가
//		seatGroup = new ToggleGroup();
//		seat1.setToggleGroup(seatGroup);
//		seat2.setToggleGroup(seatGroup);
//		seat3.setToggleGroup(seatGroup);
//		seat4.setToggleGroup(seatGroup);
//		seat5.setToggleGroup(seatGroup);
//		seat6.setToggleGroup(seatGroup);
//		seat7.setToggleGroup(seatGroup);
//		seat8.setToggleGroup(seatGroup);
//		seat9.setToggleGroup(seatGroup);
//		seat10.setToggleGroup(seatGroup);
//		seat11.setToggleGroup(seatGroup);
//		seat12.setToggleGroup(seatGroup);
//		seat13.setToggleGroup(seatGroup);
//		seat14.setToggleGroup(seatGroup);
//		seat15.setToggleGroup(seatGroup);
//		seat16.setToggleGroup(seatGroup);
//
//	}
//
//		@FXML
//		public void onReserveButtonClicked() {
//			// 선택된 좌석 번호 가져오기
//			RadioButton selectedSeat = (RadioButton) seatGroup.getSelectedToggle();
//
//			if (selectedSeat != null) {
//				String selectedSeatNumber = selectedSeat.getText();  // 선택된 좌석 번호
//				System.out.println("선택된 좌석: " + selectedSeatNumber);
//				
//				if(seat1.isSelected()) {
//					UserSession.getInstance().setSeat("01");
//				} else if (seat2.isSelected()) {
//					UserSession.getInstance().setSeat("02");
//				} else if (seat3.isSelected()) {
//					UserSession.getInstance().setSeat("03");
//				} else if (seat4.isSelected()) {
//					UserSession.getInstance().setSeat("04");
//				} else if (seat5.isSelected()) {
//					UserSession.getInstance().setSeat("05");
//				} else if (seat6.isSelected()) {
//					UserSession.getInstance().setSeat("06");
//				} else if (seat7.isSelected()) {
//					UserSession.getInstance().setSeat("07");
//				} else if (seat8.isSelected()) {
//					UserSession.getInstance().setSeat("08");
//				} else if (seat9.isSelected()) {
//					UserSession.getInstance().setSeat("09");
//				} else if (seat10.isSelected()) {
//					UserSession.getInstance().setSeat("10");
//				} else if (seat11.isSelected()) {
//					UserSession.getInstance().setSeat("11");
//				} else if (seat12.isSelected()) {
//					UserSession.getInstance().setSeat("12");
//				} else if (seat13.isSelected()) {
//					UserSession.getInstance().setSeat("13");
//				} else if (seat14.isSelected()) {
//					UserSession.getInstance().setSeat("14");
//				} else if (seat15.isSelected()) {
//					UserSession.getInstance().setSeat("15");
//				} else if (seat16.isSelected()) {
//					UserSession.getInstance().setSeat("16");
//				}
//
//				// DB에서 예약 처리
//				boolean isReserved = csDAO.reserveSeat(selectedSeatNumber, UserSession.getInstance().getSelectedTrainNumber());  // KTX101 기차 번호 예시
//
//				if (isReserved) {
//					// UI에서 예약된 좌석 표시 (예: 선택된 좌석을 비활성화)
//					selectedSeat.setDisable(true);  // 예약된 좌석은 클릭 불가
//					selectedSeat.setText(selectedSeat.getText() + " (예약됨)");
//				}
//			}
//	}
}
