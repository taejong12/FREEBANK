package main.Payment.service;

import java.time.LocalDate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.ChoiceSeat.controller.ChoiceSeatController;
import main.Payment.dao.PaymentDAO;
import main.common.service.UserSession;


public class PaymentService {
	
	PaymentDAO pdao;
	
	public PaymentService() {
		// TODO Auto-generated constructor stub
		pdao = new PaymentDAO();
	}

	public void backProc(Parent root) {
		// TODO Auto-generated method stub
		Stage seatForm =(Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/ChoiceSeat.fxml"));

		Parent seat = null;
		try {
			seat = loader.load();
			seatForm.setScene(new Scene(seat));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		ChoiceSeatController ctrl = loader.getController();
		ctrl.setRoot(seat);
		
		seatForm.setTitle("좌석검색");
		seatForm.show();
		
	}

	public void CancelProc(Parent root) {
		// TODO Auto-generated method stub
		
	}

	public void CompletetProc(Parent root) {
		// TODO Auto-generated method stub
		
		LocalDate today = LocalDate.now();
		String dateString = today.toString();
		pdao.insertData(dateString, UserSession.getInstance().getSelectedTrainDeparture(), UserSession.getInstance().getSelectedTrainDestination(), UserSession.getInstance().getSelectedTrainFare(), UserSession.getInstance().getSeat(), UserSession.getInstance().getUserId());
	}

}
