package main.History.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.History.dto.History;
import main.History.service.HistoryService;
import main.common.service.CommonService;
import main.searchtrain.dto.Searchtrain;

public class HistoryController {
	Parent root;
	HistoryService hs;
	CommonService cs;

	public HistoryController() {
		// TODO Auto-generated constructor stub
		hs = new HistoryService();
		cs = new CommonService();

	}

	public void mainHome() {
		cs.logonMainPage(root);
	}

	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
	}
	
	public void SearchProc() {
		hs.SearchProc(root);
	}

	@FXML
	private TableView<History> reservationTable;
	@FXML
	private TableColumn<History, String> purchaseDateColumn; // 구매날짜
	@FXML
	private TableColumn<History, String> departureColumn; // 출발지
	@FXML
	private TableColumn<History, String> arrivalColumn; // 도착지
	@FXML
	private TableColumn<History, String> priceColumn; // 가격
	@FXML
	private TableColumn<History, String> seatColumn; // 좌석


	public void initialize() {
		// TableColumn을 Searchtrain 모델과 연결
		purchaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("tr_day"));
		departureColumn.setCellValueFactory(new PropertyValueFactory<>("tr_local"));
		arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("tr_local2"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("tr_price"));
		seatColumn.setCellValueFactory(new PropertyValueFactory<>("tr_seat"));
	}
}
