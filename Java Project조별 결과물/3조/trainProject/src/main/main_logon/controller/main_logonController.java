package main.main_logon.controller;

import java.util.Arrays;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import main.common.service.CommonService;
import main.common.service.UserSession;
import main.main_logon.service.main_logonService;

public class main_logonController {
	Parent root;
	main_logonService mls;
	CommonService cs;
	main.History.controller.HistoryController hctrl;
	main.ticket.controller.ticketController tictrl;
	main.member.controller.MemberController mctr;
	main.Traincheck.controller.TraincheckController tcc;
	
	public main_logonController() {
		// TODO Auto-generated constructor stub
		mls = new main_logonService();
		cs = new CommonService();
		hctrl = new main.History.controller.HistoryController();
		tictrl = new main.ticket.controller.ticketController();
		mctr = new main.member.controller.MemberController();
		tcc = new main.Traincheck.controller.TraincheckController();
	}
	
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
	}
	
	public void LogoutProc() {
		cs.msgBox("로그아웃", "로그아웃 합니다.", "메인페이지로 이동합니다.");
		mls.LogoutProc(root);
	}
	
	public void MypageProc() {
		mls.MypageProc(root);
	}
	
	public void HistoryProc() {
		mls.HistoryProc(root);
	}
	
	public void MyticketProc() {
		mls.MyticketProc(root);
	}

	public void TrainSearchProc() {
		mls.TrainSearchProc(root);
	}
	
	
	
	
	
	@FXML //어노테이션?
	private ImageView imageView; // FXML에서 불러온 ImageView  fx:id="imageView"
	
	@FXML 
	private Label nameLabel;  // 이름 메시지를 표시할 라벨

	// 이미지 리스트를 준비 (여러 이미지를 리스트로 관리)
	private List<Image> images = Arrays.asList(
			new Image("file:/C:/12_JAVA/workspace/trainProject/src/main/resources/love.jpg"), //0
			new Image("file:/C:/12_JAVA/workspace/trainProject/src/main/resources/map.jpg"),  //1
			new Image("file:/C:/12_JAVA/workspace/trainProject/src/main/resources/never.jpg") //2
			);

	private int currentIndex = 0; // 현재 이미지 인덱스를 관리


	
	public void initialize() {
		
		 // UserSession에서 사용자 이름 가져오기
        String userName = UserSession.getInstance().getUserName();
        
        // 이름이 있으면 환영 메시지 출력, 없으면 로그인 필요 메시지 출력
        if (userName != null) {
            nameLabel.setText("["+ userName + "]" + "님 환영합니다.");
        } else {
            nameLabel.setText("로그인이 필요합니다.");
        }
		
		// Timeline을 사용하여 3초 간격으로 이미지 변경
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateImage()));
		timeline.setCycleCount(Timeline.INDEFINITE); // 무한 반복
		timeline.play(); // 타이머 시작
	}

	// 이미지를 변경하는 메서드
	private void updateImage() {
		// 이미지 변경
		imageView.setImage(images.get(currentIndex));
		// 인덱스를 다음 이미지로 이동, 마지막 이미지를 넘으면 처음으로 돌아감
		currentIndex = (currentIndex + 1) % images.size();   // 1 % 3 = ?
	}
	
	
	
}
