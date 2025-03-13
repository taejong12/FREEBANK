package main.member.controller;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import main.common.service.CommonService;
import main.member.service.MemberService;

public class MemberController {
	Parent root;
	MemberService ms;
	CommonService cs;
	
	public MemberController() {
		// TODO Auto-generated constructor stub
		ms = new MemberService();
		cs = new CommonService();
	}
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	public void joinMember(ActionEvent event) {
		ms.joinMember(event);
	}
	
	public void backButtonAction(ActionEvent event) {
		ms.backButtonAction(event);
	}
	
	public void basket(ActionEvent event) {
		ms.paybasket(event);
	}
	public void loginPage(ActionEvent event) {
		ms.loginPageGo(event);
		
	}
	
}