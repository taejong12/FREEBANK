package main.searchtrain.service;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.ChoiceSeat.controller.ChoiceSeatController;
import main.common.service.CommonService;
import main.login.controller.LoginController;
import main.searchtrain.dao.SearchtrainDAO;
import main.searchtrain.dto.Searchtrain;

public class SearchtrainService {
	CommonService cs;
	SearchtrainDAO dao;
	
	public SearchtrainService() {
		// TODO Auto-generated constructor stub
		cs = new CommonService();
		dao = new SearchtrainDAO();
	}
	

	public void SearchTrain(Parent root) {
		// TODO Auto-generated method stub
		
		ComboBox<String> cmbTrainSearch = (ComboBox<String>)root.lookup("#cmbtrainSearch");
		ComboBox<String> cmbTrainDeparture = (ComboBox<String>)root.lookup("#cmbtrainDeparture");
		ComboBox<String> cmbtrainDestination = (ComboBox<String>)root.lookup("#cmbtrainDestination");
		
		
		List<Searchtrain> searchTrainList = dao.searchtrain(cmbTrainSearch.getValue(), cmbTrainDeparture.getValue(), cmbtrainDestination.getValue());
		System.out.println("📌 조회된 열차 개수: " + searchTrainList.size());
		
		// ObservableList로 변환
	    ObservableList<Searchtrain> observableTrainList = FXCollections.observableArrayList(searchTrainList);
		
		// TableView와 TableColumn을 가져오기
		TableView<Searchtrain> searchTrainTableView = (TableView<Searchtrain>)root.lookup("#trainTable");

	 // TableView에 데이터 설정
	    searchTrainTableView.setItems(observableTrainList);
	}



	public void SeatProc(Parent root) {
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
}
