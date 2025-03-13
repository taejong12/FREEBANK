package main.History.service;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import main.History.dao.HistoryDAO;
import main.History.dto.History;
import main.common.service.UserSession;

public class HistoryService {
	
	HistoryDAO HDAO;
	
	public HistoryService() {
		// TODO Auto-generated constructor stub
		HDAO = new HistoryDAO();
	}

	public void SearchProc(Parent root) {
		// TODO Auto-generated method stub
		List<History> HistoryList = HDAO.searchPayment(UserSession.getInstance().getUserId());
		System.out.println("📌 조회된 열차 개수: " + HistoryList.size());
		
		// ObservableList로 변환
	    ObservableList<History> observableHistoryList = FXCollections.observableArrayList(HistoryList);
		
		// TableView와 TableColumn을 가져오기
		TableView<History> searchHistoryTableView = (TableView<History>)root.lookup("#reservationTable");

	 // TableView에 데이터 설정
		searchHistoryTableView.setItems(observableHistoryList);
	}

}
