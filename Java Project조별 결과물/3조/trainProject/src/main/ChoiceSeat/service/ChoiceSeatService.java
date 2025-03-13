package main.ChoiceSeat.service;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import main.ChoiceSeat.controller.ChoiceSeatController;
import main.Payment.controller.PaymentController;
import main.common.service.UserSession;
import main.searchtrain.controller.SearchtrainController;

public class ChoiceSeatService {

	public void PayProc(Parent root) {
		// TODO Auto-generated method stub
//		RadioButton seat1 = (RadioButton)root.lookup("#Seat1");
//		RadioButton seat2 = (RadioButton)root.lookup("#Seat2");
//		RadioButton seat3 = (RadioButton)root.lookup("#Seat3");
//		RadioButton seat4 = (RadioButton)root.lookup("#Seat4");
//		RadioButton seat5 = (RadioButton)root.lookup("#Seat5");
//		RadioButton seat6 = (RadioButton)root.lookup("#Seat6");
//		RadioButton seat7 = (RadioButton)root.lookup("#Seat7");
//		RadioButton seat8 = (RadioButton)root.lookup("#Seat8");
//		RadioButton seat9 = (RadioButton)root.lookup("#Seat9");
//		RadioButton seat10 = (RadioButton)root.lookup("#Seat10");
//		RadioButton seat11 = (RadioButton)root.lookup("#Seat11");
//		RadioButton seat12 = (RadioButton)root.lookup("#Seat12");
//		RadioButton seat13 = (RadioButton)root.lookup("#Seat13");
//		RadioButton seat14 = (RadioButton)root.lookup("#Seat14");
//		RadioButton seat15 = (RadioButton)root.lookup("#Seat15");
//		RadioButton seat16 = (RadioButton)root.lookup("#Seat16");
//		
//		if(seat1.isSelected()) {
//			UserSession.getInstance().setSeat("01");
//		} else if (seat2.isSelected()) {
//			UserSession.getInstance().setSeat("02");
//		} else if (seat3.isSelected()) {
//			UserSession.getInstance().setSeat("03");
//		} else if (seat4.isSelected()) {
//			UserSession.getInstance().setSeat("04");
//		} else if (seat5.isSelected()) {
//			UserSession.getInstance().setSeat("05");
//		} else if (seat6.isSelected()) {
//			UserSession.getInstance().setSeat("06");
//		} else if (seat7.isSelected()) {
//			UserSession.getInstance().setSeat("07");
//		} else if (seat8.isSelected()) {
//			UserSession.getInstance().setSeat("08");
//		} else if (seat9.isSelected()) {
//			UserSession.getInstance().setSeat("09");
//		} else if (seat10.isSelected()) {
//			UserSession.getInstance().setSeat("10");
//		} else if (seat11.isSelected()) {
//			UserSession.getInstance().setSeat("11");
//		} else if (seat12.isSelected()) {
//			UserSession.getInstance().setSeat("12");
//		} else if (seat13.isSelected()) {
//			UserSession.getInstance().setSeat("13");
//		} else if (seat14.isSelected()) {
//			UserSession.getInstance().setSeat("14");
//		} else if (seat15.isSelected()) {
//			UserSession.getInstance().setSeat("15");
//		} else if (seat16.isSelected()) {
//			UserSession.getInstance().setSeat("16");
//		}
		
//		System.out.println(UserSession.getInstance().getSeat());
		
		
		Stage PayForm =(Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/Payment.fxml"));

		Parent Pay = null;
		try {
			Pay = loader.load();
			PayForm.setScene(new Scene(Pay));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		PaymentController ctrl = loader.getController();
		ctrl.setRoot(Pay);
		
		PayForm.setTitle("결제");
		PayForm.show();
		
	}

	public void backProc(Parent root) {
		// TODO Auto-generated method stub
		Stage SearchTrainForm =(Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/searchtrain.fxml"));

		Parent TrainSearch = null;
		try {
			TrainSearch = loader.load();
			SearchTrainForm.setScene(new Scene(TrainSearch));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		SearchtrainController tcc = loader.getController();
		tcc.setRoot(TrainSearch);
		
		ComboBox<String> cmbTrainSearch = (ComboBox<String>)TrainSearch.lookup("#cmbtrainSearch");
		String trainSearchString[] ={"무궁화호","새마을호","KTX"};
		ComboBox<String> cmbTrainDeparture = (ComboBox<String>)TrainSearch.lookup("#cmbtrainDeparture");
		String trainDepartureString[] = {"서울","용산","서대전","익산","오송", "부산"};
		ComboBox<String> cmbtrainDestination = (ComboBox<String>)TrainSearch.lookup("#cmbtrainDestination");
		String trainDestinationString[] = {"서울","부산", "익산","정읍","원주","오송","용산"};
		
		cmbTrainSearch.getItems().addAll(FXCollections.observableArrayList(trainSearchString));
		cmbTrainDeparture.getItems().addAll(FXCollections.observableArrayList(trainDepartureString));
		cmbtrainDestination.getItems().addAll(FXCollections.observableArrayList(trainDestinationString));
		
		SearchTrainForm.show();
	}

}
