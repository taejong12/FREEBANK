package main.searchtrain.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.common.service.CommonService;
import main.common.service.UserSession;
import main.searchtrain.dao.SearchtrainDAO;
import main.searchtrain.dto.Searchtrain;
import main.searchtrain.service.SearchtrainService;

public class SearchtrainController {
	Parent root;
	SearchtrainService sts;
	CommonService cs;
	
	public SearchtrainController() {
		// TODO Auto-generated constructor stub
		sts = new SearchtrainService();
		cs = new CommonService();
	}
	
	
	public void setRoot(Parent root) {
		this.root = root;
		
	}
	
	public void mainHome() {
		cs.logonMainPage(root);
	}
	
	public void SearchTrain() {
		sts.SearchTrain(root);
	}
	
	public void SeatProc() {
		sts.SeatProc(root);
	}
	
	@FXML
    private TableView<Searchtrain> trainTable;
	@FXML
	private TableColumn<Searchtrain, String> trainNumberColumn; // 열차번호
    @FXML
    private TableColumn<Searchtrain, String> trainNameColumn; // 열차이름
    @FXML
    private TableColumn<Searchtrain, String> departureColumn; // 출발지
    @FXML
    private TableColumn<Searchtrain, String> arrivalColumn; // 도착지
    @FXML
    private TableColumn<Searchtrain, String> leaveTimeColumn; // 출발시간
    @FXML
    private TableColumn<Searchtrain, String> arriveTimeColumn; // 도착시간
    @FXML
    private TableColumn<Searchtrain, String> travelTimeColumn; // 소요시간
    @FXML
    private TableColumn<Searchtrain, String> availableSeatsColumn; // 좌석
    @FXML
    private TableColumn<Searchtrain, String> priceColumn; // 요금
    
    public void initialize() {
        // TableColumn을 Searchtrain 모델과 연결
    	trainNumberColumn.setCellValueFactory(new PropertyValueFactory<>("tr_number"));
        trainNameColumn.setCellValueFactory(new PropertyValueFactory<>("tr_name"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<>("tr_local"));
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("tr_local2"));
        leaveTimeColumn.setCellValueFactory(new PropertyValueFactory<>("tr_leave"));
        arriveTimeColumn.setCellValueFactory(new PropertyValueFactory<>("tr_arrive"));
        travelTimeColumn.setCellValueFactory(new PropertyValueFactory<>("tr_time"));
        availableSeatsColumn.setCellValueFactory(new PropertyValueFactory<>("tr_free"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("tr_price"));
        
        
        
        trainTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                UserSession.setSelectedData(newSelection.getTr_number(), newSelection.getTr_name(), newSelection.getTr_local(), newSelection.getTr_local2(), newSelection.getTr_leave(), newSelection.getTr_arrive(), newSelection.getTr_time(), newSelection.getTr_free(), newSelection.getTr_price());
                
                System.out.println(UserSession.getInstance().getSelectedTrainNumber());
                System.out.println(UserSession.getInstance().getSelectedTrainName());
                System.out.println(UserSession.getInstance().getSelectedTrainDeparture());
                System.out.println(UserSession.getInstance().getSelectedTrainDestination());
                System.out.println(UserSession.getInstance().getSelectedTrainDepartureTime());
                System.out.println(UserSession.getInstance().getSelectedTrainArrivalTime());
                System.out.println(UserSession.getInstance().getSelectedTrainDuration());
                System.out.println(UserSession.getInstance().getSelectedTrainSeat());
                System.out.println(UserSession.getInstance().getSelectedTrainFare());
            }
        });

    }

}
