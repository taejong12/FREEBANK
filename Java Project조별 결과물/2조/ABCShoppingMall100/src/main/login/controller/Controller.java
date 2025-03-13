package main.login.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.common.service.CommonService;
import main.login.service.LoginService;

public class Controller {
	
	Parent root;
	LoginService ls;
	CommonService cs;
	
	public Controller() {
		
		ls = new LoginService();
		cs = new CommonService();
	}
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	public void loginProc() {
		ls.loginProc(root);
	}
	
	public void cancelProc(ActionEvent event) {
		cs.windowClose(event);
		System.out.println("취소 버튼 클릭");
	}
	public void membershipProc() {
		ls.membershipProc(root);
	}
	
	public void logout() {
		ls.logout(root);
	}
	
	public void infoProc(ActionEvent event) {
		ls.infoProc(event);
	}
	
	public void FAQProc() {
		ls.faqProc(root);
	}
	public void backButtonAction(ActionEvent event) {
		ls.backButtonAction(event);
	}
	
	public void basket(ActionEvent event) {
		ls.paybasket(event);
	}
	
	public void loginPage(ActionEvent event) {
		ls.loginPageGo(event);
		
	}
	
	public void memberOut(ActionEvent event) {
		ls.memberOut(event);
	}
	
	public void memberModify(ActionEvent event) {
		ls.memberModify(event);
		//System.out.println("정보페이지 버튼");
	}
	public void delivery(ActionEvent event) {
		ls.delivery(event);
	}
	
	
}