package main.mileage.controller;

import javafx.scene.Parent;
import main.common.service.CommonService;
import main.mileage.service.mileageService;


public class mileageController {
	Parent root;
	CommonService cs;
	mileageService mis;

	public mileageController() {
		// TODO Auto-generated constructor stub
		mis = new mileageService();
		cs = new CommonService();
	}

	public void setRoot(Parent root) {
		this.root = root;
	}

	public void mainHome() {
		cs.logonMainPage(root);

	}
}
