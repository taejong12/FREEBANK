package main.common.service;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.login.controller.LoginController;
import main.main.controller.MainController;
import main.main_logon.controller.main_logonController;

public class CommonService {
	
	
	public CommonService() {
		// TODO Auto-generated constructor stub
	}

	// windowClose(ActionEvent event)의 전체적인 흐름
	// 1. 사용자가 어떤 UI 요소(버튼 등) 에서 windowClose 메서드를 호출
	// 2. 그 UI 요소를 Parent로 캐스팅하고,
	// 3. 그 Parent 에서 Scene을 가져오고, Stage를 얻어
	// 4. 그 Stage를 닫는다.
	// 5. 마지막으로 메시지 박스를 띄워 창이 닫혔다는 알림을 사용자에게 알려줌.


	public void windowClose(ActionEvent event) {
		Parent p = (Parent)event.getSource();
		Stage s = (Stage) p.getScene().getWindow();
		s.close();
		msgBox("취소", "취소버튼 클릭", "창이 닫힙니다.");
	}


	public void msgBox(String title, String subject,String content) {
		Alert alert = new Alert(AlertType.ERROR);   //INFORMATION
 		alert.setTitle(title);
		alert.setHeaderText(subject);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public void mainPage(Parent root) {
		
		Stage mainForm =(Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/main.fxml"));
		
		Parent main = null;
		try {
			main = loader.load();
			mainForm.setScene(new Scene(main));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


		MainController ctrl = loader.getController();
		ctrl.setRoot(main);
		
		mainForm.setTitle("메인 페이지");
		mainForm.show();
	}
	
	public void logonMainPage(Parent root) {
		Stage mainLogonForm =(Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/main_logon.fxml"));
		
		Parent logOn = null;
		try {
			logOn = loader.load();
			mainLogonForm.setScene(new Scene(logOn));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


		main_logonController ctrl = loader.getController();
		ctrl.setRoot(logOn);
		
		mainLogonForm.setTitle("메인 페이지");
		mainLogonForm.show();
	}

	
}
