package main.exam01.service;

import java.io.IOException;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.exam01.common.CommonService;
import main.exam01.controller.GymController;
import main.exam01.dao.GymDAO;
import main.exam01.dto.GymMachine;
import main.exam01.dto.GymMember;

public class GymService {
	private GymDAO GDAO;
	@FXML Parent root;
	private TextField id;
	private GymManagerService gms;// gms가 null 상태여서 오류가 발생함
	private Stage memberForm;
	    private Stage loginStage;
	//private GymMemberService gmes;

	public GymService() {
		// TODO Auto-generated constructor stub
		this.GDAO = new GymDAO();
		this.gms = new GymManagerService();// 객체를 초기화해서 null을 초기화
		//this.gmes = new GymMemberService();
		System.out.println("GymService 생성자 실행됨, gms 초기화됨: " + (gms != null));
		System.out.println("GymService 객체 생성됨: " + this);
	}


	public boolean registerMember(GymMember member) {
		// TODO Auto-generated method stub
		return false;
	}

	public void loginProc(Parent root) throws IOException {
	    if (root == null) {
	        System.out.println("⚠ root가 null입니다. 로그인 창에서 root를 다시 가져옵니다.");

	        Stage stage = (Stage) root.getScene().getWindow();
	        if (stage != null && stage.getScene() != null) {
	            root = stage.getScene().getRoot();
	        } else {
	            System.out.println("❌ 로그인 창에서 root를 가져올 수 없습니다.");
	            return;
	        }
	    }

	    TextField id = (TextField) root.lookup("#txtId");
	    PasswordField pwd = (PasswordField) root.lookup("#txtPw");

	    if (id == null || pwd == null) {
	        this.msgBox("오류", "시스템 오류", "로그인 입력란을 찾을 수 없습니다.");
	        return;
	    }

	    if (id.getText().isEmpty() || pwd.getText().isEmpty()) {
	        this.msgBox("로그인할 수 없습니다", "ID 혹은 PW를 입력하세요", "로그인 창이 열립니다");
	        return;
	    }

	    String userId = id.getText();
	    String password = pwd.getText();

	    if ("manager".equals(userId) && "9999".equals(password)) {
	        gms.managershipProc(root);
	        System.out.println("관리자 로그인 성공");
	    } else if (GDAO.loginChk(userId, password)) {
	        gms.openMember(root);
	        System.out.println("로그인 성공");
	    } else {
	        System.out.println("로그인 실패");
	        None(root);  // 로그인 실패 후 처리를 위해 추가 작업
	    }
	}







	public void logout(Parent root) {
		// TODO Auto-generated method stub
		try {
			Stage stage = (Stage) root.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/exam01/fxml/login.fxml"));
			Parent logoutRoot = loader.load();
			GymController ctrl = loader.getController();
			ctrl.setRoot(logoutRoot);

			stage.setScene(new Scene(logoutRoot));
			stage.setTitle("로그인");
			stage.show();

			GymService ls = new GymService();
			ls.msgBox("로그아웃", "로그아웃 성공", "로그인 창이 열립니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 회원 페이지 ->
	private void memberInfo(Parent root) {
		// TODO Auto-generated method stub
		Stage s = (Stage)root.getScene().getWindow();
		s.close();

		Stage infoStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/exam01/fxml/memberpage.fxml"));
		Parent info = null;
		try {
			info = loader.load();
			infoStage.setScene(new Scene(info));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//		GymController ctrl = loader.getController();
		//		ListView<GymMember> infoListView = (ListView)info.lookup("#infoListView");
		//		List<GymMember> memberList = GDAO.selectAll();
		//		infoListView.setItems(FXCollections.observableArrayList(memberList));
		//		infoListView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
		//			@Override
		//			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
		//				// TODO Auto-generated method stub
		//				GymMember m = infoListView.getSelectionModel().getSelectedItem();
		//				System.out.println(m.getId());
		//				System.out.println(m.getPwd());
		//				System.out.println(m.getName());
		//				System.out.println(m.getGender());
		//				System.out.println(m.getAge());
		//				System.out.println(m.getHeight());
		//				System.out.println(m.getWeight());
		//				System.out.println(m.getBodyFat());
		//				System.out.println(m.getMem_type());
		//			}
		//		});
		//		infoStage.setTitle("회원정보");
		//		infoStage.show();
	}

	// 관리자 페이지 
	private void openMemberInfo(Parent root, String fxmlPath, String title) {
		Stage currentStage = (Stage) root.getScene().getWindow();
		currentStage.close();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
			Parent newRoot = loader.load();
			GymController ctrl = loader.getController();
			ctrl.setRoot(newRoot);

			ListView<GymMember> infoListView = (ListView<GymMember>) newRoot.lookup("#infoListView");
			if (infoListView != null) {
				List<GymMember> memberList = GDAO.selectAll();
				infoListView.setItems(FXCollections.observableArrayList(memberList));
			}

			Stage newStage = new Stage();
			newStage.setScene(new Scene(newRoot));
			newStage.setTitle(title);
			newStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public List<GymMachine> getMachinesByBodyPart(String bodyPart) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean performExercise(String memberId, String machineName, double weight, int sets) {
		// TODO Auto-generated method stub
		return false;
	}

	// 로그아웃 페이지


	public void Back(Parent root) {
		// TODO Auto-generated method stub
		try {
			Stage stage = (Stage) root.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/exam01/fxml/login.fxml"));
			Parent logoutRoot = loader.load();
			GymController ctrl = loader.getController();
			ctrl.setRoot(logoutRoot);

			stage.setScene(new Scene(logoutRoot));
			stage.setTitle("로그인");
			stage.show();

			GymService ls = new GymService();
			ls.msgBox("Back", "Back 성공", "로그인 창이 열립니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void None(Parent root) {

		this.msgBox("로그인 실패", "ID 또는 비밀번호가 올바르지 않습니다.", "다시 시도하세요.");
	}



	public void msgBox(String title, String subject, String content) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(subject);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public void windowClose(ActionEvent event) {
		Parent p = (Parent)event.getSource();
		Stage s = (Stage) p.getScene().getWindow();
		s.close();
		msgBox("취소", "취소버튼 클릭", "창이 닫힙니다.");
	}

	// 회원가입 페이지
	//-------------------------------------------------
	public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }
	public void membershipProc(Parent root) {
		// TODO Auto-generated method stub
		Stage memberForm = (Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/exam01/fxml/joinmember.fxml"));
		Parent member = null;
		try {
			member = loader.load();
			memberForm.setScene(new Scene(member));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		GymController ctrl = loader.getController();
		ctrl.setRoot(member);

		memberForm.setTitle("회원가입창");
		memberForm.show();
	}
	


	public void joinMember(Parent memberForm) {
		// memberForm이 null이 아닌지 확인
		if (memberForm == null) {
			System.out.println("오류: memberForm이 null입니다.");
			return;
		}

		// FXML 요소가 완전히 초기화된 후에 lookup 호출
		TextField id = (TextField) memberForm.lookup("#txtId");
		TextField pwd = (TextField) memberForm.lookup("#txtPwd");
		TextField name = (TextField) memberForm.lookup("#txtName");
		TextField gender = (TextField) memberForm.lookup("#txtGender");
		TextField age = (TextField) memberForm.lookup("#txtAge");
		TextField height = (TextField) memberForm.lookup("#txtHeight");
		TextField weight = (TextField) memberForm.lookup("#txtWeight");
		TextField bodyfat = (TextField) memberForm.lookup("#txtBodyfat");

		// FXML에서 필드가 null인지 확인
		if (id == null || pwd == null || name == null || gender == null || age == null || height == null || weight == null || bodyfat == null) {
			System.out.println("오류: FXML에 하나 이상의 필드가 누락되었습니다.");
			return;
		}

		// 나머지 유효성 검사 로직
		if (id.getText().isEmpty()) {
			msgBox("입력오류", "아이디 입력 오류", "아이디가 입력되지 않았습니다. 다시 입력하세요");
			id.requestFocus();
			return;
		} else if (name.getText().isEmpty()) {
			msgBox("입력오류", "이름 입력 오류", "이름이 입력되지 않았습니다. 다시 입력하세요");
			name.requestFocus();
			return;
		} else if (pwd.getText().isEmpty()) {
			msgBox("입력오류", "암호 입력 오류", "암호가 입력되지 않았습니다. 다시 입력하세요");
			pwd.requestFocus();
			return;
		}

		// 회원 정보 설정
		GymMember m = new GymMember();
		m.setId(id.getText());
		m.setPwd(pwd.getText());
		m.setName(name.getText());
		m.setAge(Integer.parseInt(age.getText()));
		m.setHeight(Double.parseDouble(height.getText()));
		m.setWeight(Double.parseDouble(weight.getText()));
		m.setBodyFat(Double.parseDouble(bodyfat.getText()));
		m.setGender(gender.getText());

		// 데이터베이스에 회원을 추가하고 화면 전환
		if (GDAO.insertMember(m)) {
			Stage s = (Stage) memberForm.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/exam01/fxml/login.fxml"));
			try {
				memberForm = loader.load();
				s.setScene(new Scene(memberForm));
			} catch (Exception e) {
				e.printStackTrace();
			}

			GymController ctrl = loader.getController();
			//ctrl.setRoot(memberForm);
			s.setTitle("로그인");
			s.show();
		} else {
			clearFields(id, pwd, name, gender, age, height, weight, bodyfat);
		}
	}

	private void clearFields(TextField... fields) {
		for (TextField field : fields) {
			field.clear();
		}
	}
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
	}

	public boolean insertMember(GymMember newMember) {
		// TODO Auto-generated method stub
		return false;
	}
	//-------------------------------------------------






}
