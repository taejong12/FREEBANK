//package main.question.controller;
//
//import javafx.event.ActionEvent;
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import main.question.service.QuestionService;
//
//public class QuestionController {
//
//	Parent root;
//	QuestionService qs;
//	
//	public QuestionController() {
//		qs = new QuestionService();	
//		
//	}
//	
//	public void setRoot(Parent root) {
//		// TODO Auto-generated method stub
//		this.root = root;
//	}
//
//	public void questProc() {
//		//qctrl.setRoot(root);
//		qs.questProc(root);
//	}
//	
//	public void askProc() {
//		qs.askProc(root);
//	}
//	

package main.question.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import main.question.service.QuestionService;

public class QuestionController {

	Parent root;
	QuestionService qs = new QuestionService();
	
	@FXML
	private Scene previousScene;
	@FXML
	private Button backButton; 
	
	
	public void backbuttonAction(ActionEvent event) {
		qs.backbuttonAction(event);
	}

	public void setRoot(Parent root) {
		this.root = root;
	}

	public void questProc(ActionEvent event) {
		qs.questProc(event);
	}
	
	public void noticeProc(ActionEvent event) {
		qs.noticeProc(event);
	}
	
	public void sendQuest(ActionEvent event) {
		qs.sendQuest(event);
	}

		
}
//	public void noticeProc() {
//		qs.noticeProc(root);
//	}
//		
//}
