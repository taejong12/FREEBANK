package main.question.service;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.common.service.CommonService;
import main.question.controller.QuestionController;
import main.question.dao.QuestionDao;
import main.question.dto.QuestionDto;

public class QuestionService {

	CommonService cs = new CommonService();
	QuestionDao dao;

	public QuestionService() {

		dao = new QuestionDao();
	}

	public void questProc(ActionEvent event) {  // 문의하기 페이지
		FXMLLoader questStage = new FXMLLoader(getClass().getResource("/main/fxml/questList.fxml"));
		Parent root;
		try {
			root = questStage.load();
			Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();		

			stage.setScene(new Scene(root));
			stage.setTitle("문의하기 페이지");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void noticeProc(ActionEvent event) {  // 공지사항페이지
		FXMLLoader questStage = new FXMLLoader(getClass().getResource("/main/fxml/noticeList.fxml"));
		Parent root;
		try {
			root = questStage.load();
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();		

			stage.setScene(new Scene(root));
			stage.setTitle("공지사항 페이지");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void backbuttonAction(ActionEvent event) {  // 뒤로가기
		// TODO Auto-generated method stub
		FXMLLoader loder = new FXMLLoader(getClass().getResource("/main/fxml/question.fxml"));
		Parent root;
		try {
			root = loder.load();
			Scene back = new Scene(root);
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); 
			stage.getProperties().put("previousScene", stage.getScene());
			stage.setScene(back);
			stage.setTitle("FAQ");
			stage.show();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	
	public void sendQuest(ActionEvent event) {
		Scene questForm = (Scene) ((Button) event.getSource()).getScene();
		QuestionDto faq = new QuestionDto();
		TextField quest = (TextField)questForm.lookup("#txtquest");

		faq.setQuest(quest.getText());

		if(quest.getText().isEmpty()) {
			cs.msgBox("입력오류", "문의하기 입력 오류", 
					"정보가 입력되지 않았습니다. 다시 입력하세요");
			quest.requestFocus();
			return;	
		} 
		
		if(dao.insertquest(faq)) {
			Stage s = (Stage) 
				 questForm.getWindow();
			FXMLLoader loader =
				new FXMLLoader(
				getClass().
				getResource("/main/fxml/question.fxml"));
			
			try {
				Parent questForm1 = loader.load();
				s.setScene(new Scene(questForm1));
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			s.setTitle("로그인");
			s.show();
		} else {
			quest.clear();
		}

	}

}
