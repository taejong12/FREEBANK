package main.main_logon.service;

import java.util.Arrays;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.History.controller.HistoryController;
import main.Mypage.controller.MypageController;
import main.Traincheck.controller.TraincheckController;
import main.common.service.UserSession;
import main.main.controller.MainController;
import main.main_logon.controller.main_logonController;
import main.member.controller.MemberController;
import main.member.service.MemberService;
import main.searchtrain.controller.SearchtrainController;
import main.ticket.controller.ticketController;

public class main_logonService {
	
	
	public void MypageProc(Parent root) {
		// TODO Auto-generated method stub
		Stage MypageForm =(Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/Mypage.fxml"));

		Parent Mypage = null;
		try {
			Mypage = loader.load();
			MypageForm.setScene(new Scene(Mypage));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		MypageController mctr = loader.getController();
		mctr.setRoot(Mypage);
		
		MypageForm.setTitle("마이페이지");
		MypageForm.show();
	}

	public void HistoryProc(Parent root) {
		// TODO Auto-generated method stub
		Stage HistoryForm =(Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/History.fxml"));

		Parent History = null;
		try {
			History = loader.load();
			HistoryForm.setScene(new Scene(History));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		HistoryController hctrl = loader.getController();
		hctrl.setRoot(History);
		
		HistoryForm.setTitle("예매내역");
		HistoryForm.show();
		
	}

	public void MyticketProc(Parent root) {
		// TODO Auto-generated method stub
		Stage MyticketForm =(Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/ticket.fxml"));

		Parent Myticket = null;
		try {
			Myticket = loader.load();
			MyticketForm.setScene(new Scene(Myticket));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		ticketController ctrl = loader.getController();
		ctrl.setRoot(Myticket);
		
		MyticketForm.setTitle("티켓");
		MyticketForm.show();
		
	}

	public void LogoutProc(Parent root) {
		// TODO Auto-generated method stub
		
		UserSession.getInstance().logout();
		
		Stage mainForm =(Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/main.fxml"));

		Parent main = null;
		try {
			main = loader.load();
			mainForm.setScene(new Scene(main));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		MainController ctrl = loader.getController();
		ctrl.setRoot(main);
		
		mainForm.setTitle("메인페이지");
		mainForm.show();
	}
	
	

	public void TrainSearchProc(Parent root) {
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
		String trainDepartureString[] = {"서울","용산","서대전","익산","오송", "부산","원주"};
		ComboBox<String> cmbtrainDestination = (ComboBox<String>)TrainSearch.lookup("#cmbtrainDestination");
		String trainDestinationString[] = {"서울","부산", "익산","정읍","원주","오송","용산"};
		
		cmbTrainSearch.getItems().addAll(FXCollections.observableArrayList(trainSearchString));
		cmbTrainDeparture.getItems().addAll(FXCollections.observableArrayList(trainDepartureString));
		cmbtrainDestination.getItems().addAll(FXCollections.observableArrayList(trainDestinationString));
		
		SearchTrainForm.setTitle("열차검색");
		SearchTrainForm.show();
		
	}

}
