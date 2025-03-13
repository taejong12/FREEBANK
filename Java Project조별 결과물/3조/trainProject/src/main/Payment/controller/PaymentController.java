package main.Payment.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.Payment.service.PaymentService;
import main.common.service.CommonService;
import main.common.service.UserSession;

public class PaymentController {
	Parent root;
	CommonService cs;
	PaymentService ps;
	
	public PaymentController() {
		// TODO Auto-generated constructor stub
		cs = new CommonService();
		ps = new PaymentService();
	}
	
	public void mainHome() {
		cs.logonMainPage(root);
	}

	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
		
	}
	
	public void backProc() {
		ps.backProc(root);
	}
	
	public void CancelProc(ActionEvent event) {
		cs.windowClose(event);
	}

//	private void Proc(String title, String context, String string3) {
//		// TODO Auto-generated method stub
//		cs.msgBox("결제", "결제 완료", "창이 닫힙니다.");
//
	public void CompleteProc() {
		cs.msgBox("결제", "결제 완료", "메인페이지로 넘어갑니다.");
		ps.CompletetProc(root);
		cs.logonMainPage(root);
	}
	
	
	@FXML
	private Label trainName;
	@FXML
	private Label trainDeparture;
	@FXML
	private Label trainDestination;
	@FXML
	private Label trainDepartureTime;
	@FXML
	private Label trainArrivalTime;
	@FXML
	private Label trainFare;
	@FXML
	private Label trainFare1;
	@FXML
	private Label trainSeat;
	
	public void initialize() {
		String trainName1 = UserSession.getInstance().getSelectedTrainName();
		String trainDeparture1 = UserSession.getInstance().getSelectedTrainDeparture();
		String trainDestination1 = UserSession.getInstance().getSelectedTrainDestination();
		String trainDepartureTime1 = UserSession.getInstance().getSelectedTrainDepartureTime();
		String trainArrivalTime1 = UserSession.getInstance().getSelectedTrainArrivalTime();
		String trainFarePrice = UserSession.getInstance().getSelectedTrainFare();
		String trainSeat1 = UserSession.getInstance().getSeat();
		
		
		trainName.setText(trainName1);
		trainDeparture.setText(trainDeparture1);
		trainDestination.setText(trainDestination1);
		trainDepartureTime.setText(trainDepartureTime1);
		trainArrivalTime.setText(trainArrivalTime1);
		trainFare.setText(trainFarePrice);
		trainFare1.setText(trainFarePrice);
		trainSeat.setText("No." + trainSeat1);
		
		
		
	}
	
}
