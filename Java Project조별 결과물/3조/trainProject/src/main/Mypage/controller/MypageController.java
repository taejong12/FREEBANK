package main.Mypage.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import main.Mypage.service.MypageService;
import main.common.service.CommonService;
import main.common.service.UserSession;


public class MypageController {
	Parent root;
	MypageService mps;
	CommonService cs;
	
	public MypageController() {
		// TODO Auto-generated constructor stub
		mps = new MypageService();
		cs = new CommonService();
		
	}
	
	public void mainHome() {
		cs.logonMainPage(root);
	}

	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
	}
	
	public void MyticketProc() {
		mps.MyticketProc(root);
	}
	
	public void MileageProc() {
		mps.MileageProc(root);
	}
	
	public void LogoutProc() {
		cs.msgBox("로그아웃", "로그아웃 합니다.", "메인페이지로 이동합니다.");
		mps.LogoutProc(root);
	}
	
	public void DeleteAccountProc() {
		cs.msgBox("회원탈퇴", "회원탈퇴 합니다.", "메인페이지로 이동합니다.");
		mps.DeleteAccountProc(root);
	}
	 
	@FXML
	private Label nameLabel;
	@FXML
	private Label idLabel;
	@FXML
	private Label addressLabel;
	@FXML
	private Label phoneLabel;
	
	public void initialize() {
		
		 // UserSession에서 사용자 이름 가져오기
       String userName = UserSession.getInstance().getUserName();
       String userId = UserSession.getInstance().getUserId();
       String useraddress = UserSession.getInstance().getUserAddress();
       String userPhone = UserSession.getInstance().getUserPhone();
       
       nameLabel.setText(userName);
       idLabel.setText(userId);
       addressLabel.setText(useraddress);
       phoneLabel.setText(userPhone);
       
	}
}
