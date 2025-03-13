package main.delivery.controller;


import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.delivery.Service.DeliveryService;

public class DeliveryController {

	DeliveryService delis;
	Parent root;
	@FXML
	private Button backButton;
	
	public DeliveryController() {
		delis = new DeliveryService();
		
	}
	
	public void chkDeliNum(ActionEvent event) {
		 Node source = (Node) event.getSource();
	     Stage stage = (Stage) source.getScene().getWindow();
		 Parent root = stage.getScene().getRoot();
		delis.deilveryNumFind(root);	}
	
	public void backButtonAction (ActionEvent event) {
		delis.backButtonAction(event);
	}
	
	public void loginPage(ActionEvent event) {
		delis.loginPageGo(event);
	}

	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
	}
}
