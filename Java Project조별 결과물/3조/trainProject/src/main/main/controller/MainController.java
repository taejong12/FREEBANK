package main.main.controller;

import java.util.Arrays;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import main.common.service.CommonService;
import main.main.service.MainService;

public class MainController {
	Parent root;
	MainService ms;
//	main.login.controller.LoginController ctrl;
//	main.member.controller.MemberController mctr;
	CommonService cs;


	public MainController() {
		// TODO Auto-generated constructor stub
//		ctrl = new main.login.controller.LoginController();
//		mctr = new main.member.controller.MemberController();
		cs = new CommonService();
		ms = new MainService();
	}

	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root  = root;
	}

	// lpgin 버튼 
	public void LoginProc() {
		ms.LoginProc(root);

	}

	// trainSearch''
	public void LoginCheckProc() {
		cs.msgBox("로그인 오류", "로그인 후 접속하세요", "로그인 페이지로 넘어갑니다");
		ms.LoginCheck(root);
	}
	// signUpPress 버튼
	public void SignUpProc() {
		ms.SignUpProc(root);
	}
	
	public void mainHome() {
		cs.mainPage(root);
	}
	
	
	// ==============================================================================================
	@FXML //어노테이션?
	private ImageView imageView; // FXML에서 불러온 ImageView  fx:id="imageView"

	// 이미지 리스트를 준비 (여러 이미지를 리스트로 관리)
	private List<Image> images = Arrays.asList(
			new Image("file:/C:/12_JAVA/workspace/trainProject/src/main/resources/love.jpg"), //0
			new Image("file:/C:/12_JAVA/workspace/trainProject/src/main/resources/map.jpg"),  //1
			new Image("file:/C:/12_JAVA/workspace/trainProject/src/main/resources/never.jpg") //2
			);

	private int currentIndex = 0; // 현재 이미지 인덱스를 관리


	
	public void initialize() {
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
	
	
//	public void initialize() {
//		// 처음 화면에 첫 번째 이미지 표시
//		imageView.setImage(images.get(currentIndex));
//
//		// Timeline을 사용해 일정 시간(3초)마다 자동으로 이미지 전환
//		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {fadeOutAndChangeImage();
//		})
//				);
//		timeline.setCycleCount(Timeline.INDEFINITE); // 무한 반복
//		timeline.play(); // 타이머 시작
//	}
//
//	// 이미지 페이드 아웃 + 변경 후 페이드 인
//	private void fadeOutAndChangeImage() {
//		// Fade Out 효과
//		FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), imageView);
//		fadeOut.setFromValue(1.0);
//		fadeOut.setToValue(0.0);
//
//		// Fade Out이 끝난 후 이미지 변경
//		fadeOut.setOnFinished(event -> {
//			// 이미지 변경 (다음 이미지로)
//			currentIndex = (currentIndex + 1) % images.size();
//			imageView.setImage(images.get(currentIndex));
//
//			// Fade In 효과
//			FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), imageView);
//			fadeIn.setFromValue(0.0);
//			fadeIn.setToValue(1.0);
//			fadeIn.play(); // 페이드 인 시작
//		});
//
//		fadeOut.play(); // 페이드 아웃 시작
//	}
}
