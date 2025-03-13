package main.ticket.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import main.common.service.CommonService;
import main.common.service.UserSession;
import main.ticket.service.ticketService;

public class ticketController {
	Parent root;
	CommonService cs;
	ticketService ts;
	
	public ticketController() {
		// TODO Auto-generated constructor stub
		cs = new CommonService();
		ts = new ticketService();
	}
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	public void mainHome() {
		cs.logonMainPage(root);
	}
	
	@FXML
	private Label TrainName; //
	@FXML
	private Label UserName;
	@FXML
	private Label TrainDuration; //
	@FXML
	private Label Seat;  //
	@FXML
	private Label TrainArrivalTime; //
	@FXML
	private Label TrainDepartureTime; //
	@FXML
	private Label TrainDestination; //
	@FXML
	private Label TrainDeparture; //
	
	public void initialize() {
		String trainName1 = UserSession.getInstance().getSelectedTrainName(); //
		String trainDeparture1 = UserSession.getInstance().getSelectedTrainDeparture(); //
		String trainDestination1 = UserSession.getInstance().getSelectedTrainDestination(); //
		String trainDepartureTime1 = UserSession.getInstance().getSelectedTrainDepartureTime(); //
		String trainArrivalTime1 = UserSession.getInstance().getSelectedTrainArrivalTime(); //
		String trainDuration1 = UserSession.getInstance().getSelectedTrainDuration(); //
		String UserName1 = UserSession.getInstance().getUserName();
		String trainSeat1 = UserSession.getInstance().getSeat();
		
		
		TrainName.setText(trainName1);
		TrainDeparture.setText(trainDeparture1);
		TrainDestination.setText(trainDestination1);
		TrainDepartureTime.setText(trainDepartureTime1);
		TrainArrivalTime.setText(trainArrivalTime1);
		TrainDuration.setText(trainDuration1);
		Seat.setText("No." + trainSeat1);
		UserName.setText(UserName1);
		
		
		
	}
	
	
	
}
