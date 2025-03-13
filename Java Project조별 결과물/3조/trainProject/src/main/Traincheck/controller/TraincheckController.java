package main.Traincheck.controller;

import javafx.scene.Parent;
import main.Traincheck.service.TraincheckService;
import main.common.service.CommonService;

public class TraincheckController {
	Parent root;
	CommonService cs;
	TraincheckService tcs;
	
	public TraincheckController() {
		// TODO Auto-generated constructor stub
		cs = new CommonService();
		tcs = new TraincheckService();	
	}
	
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
	}
	
	public void mainHome() {
		cs.mainPage(root);
	}
}
