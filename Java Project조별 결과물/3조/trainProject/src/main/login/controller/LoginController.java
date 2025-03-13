package main.login.controller;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import main.common.service.CommonService;
import main.login.service.LoginService;


public class LoginController {

	Parent root;
	CommonService cs;
	LoginService ls;
	
	

	public LoginController() {
		// TODO Auto-generated constructor stub
		cs = new CommonService();
		ls = new LoginService();
		
	}

	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root  = root;
	}

	// Cancel 버튼
	public void CancelProc() {
		ls.CancelProc(root);
	}
	
	// Login 버튼 
	public void LoginProc() {
		ls.LoginProc(root);
	}
	
	// 
	public void SignUpProc() {
		ls.SignUpProc(root);
	}

	public void mainHome() {
		cs.mainPage(root);
	}
}
