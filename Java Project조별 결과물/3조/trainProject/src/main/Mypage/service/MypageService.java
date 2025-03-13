package main.Mypage.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Mypage.controller.MypageController;
import main.Mypage.dao.MypageDAO;
import main.common.service.UserSession;
import main.main.controller.MainController;
import main.mileage.controller.mileageController;
import main.ticket.controller.ticketController;

public class MypageService {
	
	
	MypageDAO mdao;
	
	public MypageService() {
		// TODO Auto-generated constructor stub
		mdao = new MypageDAO();
	}

	public void MyticketProc(Parent root) {
		// TODO Auto-generated method stub
		Stage MyticketForm =(Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/ticket.fxml"));

		Parent Myticket = null;
		try {
			Myticket = loader.load();
			MyticketForm.setScene(new Scene(Myticket));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		ticketController tctr = loader.getController();
		tctr.setRoot(Myticket);

		MyticketForm.setTitle("내 티켓");
		MyticketForm.show();
	}

	public void MileageProc(Parent root) {
		// TODO Auto-generated method stub
		Stage MileageForm =(Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/mileage.fxml"));

		Parent Mileage = null;
		try {
			Mileage = loader.load();
			MileageForm.setScene(new Scene(Mileage));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		mileageController mctr = loader.getController();
		mctr.setRoot(Mileage);

		MileageForm.setTitle("마일리지");
		MileageForm.show();
	}

	public void LogoutProc(Parent root) {
		// TODO Auto-generated method stub
		UserSession.getInstance().logout();

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

		mainForm.setTitle("메인페이지");
		mainForm.show();
	}

	public void DeleteAccountProc(Parent root) {
		// TODO Auto-generated method stub
		if(mdao.deleteAccount(UserSession.getInstance().getUserId())) {
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

			mainForm.setTitle("메인페이지");
			mainForm.show();
		} else {
			System.out.println("회원탈퇴 실패!!!");
		}
		
	
	}

}
