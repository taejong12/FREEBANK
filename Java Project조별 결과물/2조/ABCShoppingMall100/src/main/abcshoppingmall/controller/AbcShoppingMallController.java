package main.abcshoppingmall.controller;


import javafx.event.ActionEvent;
import javafx.scene.Parent;
import main.abcshoppingmall.Service.AbcShoppingMallService;

public class AbcShoppingMallController {
	
	Parent root; 
	AbcShoppingMallService as;
	
	public AbcShoppingMallController() {
		as = new AbcShoppingMallService();
	}
	
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	public void startPage(ActionEvent event) {
		as.startProc(event);
	}


	
}
