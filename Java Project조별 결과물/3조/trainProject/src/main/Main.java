package main;

import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.main.controller.MainController;

public class Main extends Application {

	@Override
	public void start(Stage mainstage) throws Exception {
		// 스플래시 화면(Stage) 생성
		Stage splashStage = new Stage();
		ImageView splashImage = new ImageView(
					new Image("file:/C:/12_JAVA/workspace/trainProject/src/main/fxml/trainsplash.png"));

		splashImage.setFitHeight(900);
		splashImage.setFitWidth(400);

		StackPane splashLayout = new StackPane(splashImage);
		Scene splashScene = new Scene(splashLayout, 400, 400);

		splashStage.setScene(splashScene);
		splashStage.show();

		// 일정 시간 후 메인 애플리케이션 실행, (제네릭 타입 공부하기!!!)
		PauseTransition delay = new PauseTransition(Duration.seconds(2));
		delay.setOnFinished(event -> {
			splashStage.close(); // 스플래시 화면 닫기
			showmainpage(mainstage); // 메인 애플리케이션 실행
		});
		delay.play();
	}

	private void showmainpage(Stage mainstage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/main.fxml"));
			Parent root = loader.load();

			MainController ctrl = loader.getController();
			ctrl.setRoot(root);

			mainstage.setScene(new Scene(root));
			mainstage.setTitle("메인페이지");
			mainstage.show();
		} catch (IOException e) {
			e.printStackTrace();
			// 로그 파일 기록 등 추가 조치		
		}
	}	
	
	// "SELECT MEM_NAME FROM MEMBER WHERE MEM_ID =? AND WHERE MEM_PW =?";
	
	

	public static void main(String[] args) {
		try {
			launch(args);
		} catch (Exception e) {
			e.printStackTrace(); // 예외 출력
		}
	}
}
