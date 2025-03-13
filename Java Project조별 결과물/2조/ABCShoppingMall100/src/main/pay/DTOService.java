package main.pay;



import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.category.service.CategoryService;
import main.common.service.CommonService;
import main.login.controller.Controller;
import main.member.controller.MemberController;


public class DTOService {
	int totalSum;
	CommonService cm = new CommonService();
	CategoryService cs = new CategoryService();
	Dao d = new Dao();
	Dto dto = new Dto();


	// 라디오 버튼 선택에 따른 결제 금액 텍스트 처리
	public void handleRadioButtonSelection(RadioButton radioButton1, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4, 
			Text paymentAmountText1, Text paymentAmountText2, Text paymentAmountText3, Text paymentAmountText4,
			Text paymentProductText1,  Text paymentProductText2,  Text paymentProductText3,  Text paymentProductText4, 
			Text product1,Text product2,Text product3,Text product4, Text sum) {

		// 초기화
		paymentAmountText1.setText("");
		paymentAmountText2.setText("");
		paymentAmountText3.setText("");
		paymentAmountText4.setText("");

		paymentProductText1.setText("");
		paymentProductText2.setText("");
		paymentProductText3.setText("");
		paymentProductText4.setText("");



		List<RadioButton> selectedRadioButtons = new ArrayList<>();
		List<Text> t = new ArrayList<>();
		totalSum = 0;

		// 라디오 버튼이 선택되었으면 리스트에 추가
		if (radioButton1.isSelected()) {
			selectedRadioButtons.add(radioButton1);
			t.add(product1);

		}
		if (radioButton2.isSelected()) {
			selectedRadioButtons.add(radioButton2);
			t.add(product2);
		}
		if (radioButton3.isSelected()) {
			selectedRadioButtons.add(radioButton3);
			t.add(product3);
		}
		if (radioButton4.isSelected()) {
			selectedRadioButtons.add(radioButton4);
			t.add(product4);
		}

		// 선택된 라디오 버튼 순서대로 텍스트 설정 (결제 금액과 상품명)
		if (selectedRadioButtons.size() > 0) {
			paymentAmountText1.setText(t.get(0).getText());
			paymentProductText1.setText(selectedRadioButtons.get(0).getText());
			totalSum += Integer.parseInt(t.get(0).getText());


		}
		if (selectedRadioButtons.size() > 1) {
			paymentAmountText2.setText(t.get(1).getText());
			paymentProductText2.setText(selectedRadioButtons.get(1).getText());
			totalSum += Integer.parseInt(t.get(1).getText());

		}
		if (selectedRadioButtons.size() > 2) {
			paymentAmountText3.setText(t.get(2).getText() );
			paymentProductText3.setText(selectedRadioButtons.get(2).getText());
			totalSum += Integer.parseInt(t.get(2).getText());

		}
		if (selectedRadioButtons.size() > 3) {
			paymentAmountText4.setText( t.get(3).getText());
			paymentProductText4.setText(selectedRadioButtons.get(3).getText());
			totalSum += Integer.parseInt(t.get(3).getText());

		}
		sum.setText(totalSum +"원");




		// 나머지 텍스트는 초기화
		if (selectedRadioButtons.size() < 1) {
			paymentAmountText1.setText("");
			paymentProductText1.setText("");

		}
		if (selectedRadioButtons.size() < 2) {
			paymentAmountText2.setText("");
			paymentProductText2.setText("");

		}
		if (selectedRadioButtons.size() < 3) {
			paymentAmountText3.setText("");
			paymentProductText3.setText("");

		}
		if (selectedRadioButtons.size() < 4) {
			paymentAmountText4.setText("");
			paymentProductText4.setText("");


		}




	}
	public void cardPay(ActionEvent event, TextField cardnumber) {
		if (totalSum > 0) {
		
			if(totalSum < d.cardmoney(Integer.parseInt(cardnumber.getText()))) {
			// 버튼을 클릭한 후 해당 버튼의 Scene을 얻어옵니다.
			Stage payStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
			
			// 결제 화면(pay.fxml)을 로드합니다.
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/fxml/payy.fxml"));
			Parent pay = null;
			
			
				try {
					// pay.fxml을 로드하고 결제 화면을 설정합니다.
					pay = loader.load();
					payStage.setScene(new Scene(pay));

					// pay.fxml에서 필요한 요소들을 설정합니다.
					Text sum = (Text) pay.lookup("#sum");
					Text totalSum = (Text) ((Button) event.getSource()).getScene().lookup("#sum");
					sum.setText(totalSum.getText());

					Text num1 = (Text) pay.lookup("#num1");
					Text num2 = (Text) pay.lookup("#num2");

					// 랜덤 숫자 생성
					Random rd = new Random();
					int dnsthd = (rd.nextInt(1000000) + rd.nextInt(9000000));
					num1.setText(String.valueOf(dnsthd));

					d.deliveryNum(dto);
					num2.setText(String.valueOf(dto.getDeliveryNum()));

					dto.setSum(d.cardmoney(Integer.parseInt(cardnumber.getText()))-this.totalSum);
					//System.out.println(dto.getDeliveryNum());
					System.out.println("계좌잔고 : "+dto.getSum());
					d.total(dto.getSum(),Integer.parseInt(cardnumber.getText()));
					
					d.deliDate();
					// 필요한 경우 컨트롤러에 데이터를 넘겨줍니다.
					main.pay.Controller ctr1 = loader.getController();
					ctr1.setRoot(pay);  // 만약 컨트롤러에서 Root를 처리해야 한다면

					// 결제 화면을 보여줍니다.
					payStage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				cm.msgBox("", "", "잔액부족");
			}
		} else {
			cm.msgBox("오류", "선택된 물품이 없음", "선택 후 다시 결제해주세요");
		}

	}

	public void backButtonAction(ActionEvent event) {
		// TODO Auto-generated method stub
		FXMLLoader loder = new FXMLLoader(getClass().getResource("/main/fxml/category.fxml"));
		Parent root;
		try {
			root = loder.load();
			Scene beck = new Scene(root);
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); // 현재 Scene의 Stage 객체 가져오기
			stage.getProperties().put("previousScene", stage.getScene());
			stage.setScene(beck);
			stage.setTitle("카테고리");
			stage.show();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public void delivery(ActionEvent event) {
		// TODO Auto-generated method stub
		FXMLLoader loder = new FXMLLoader(getClass().getResource("/main/fxml/deliveryNum.fxml"));
		Parent root;
		try {
			root = loder.load();
			Scene beck = new Scene(root);
			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow(); // 현재 Scene의 Stage 객체 가져오기
			
			stage.setScene(beck);
			stage.setTitle("배송 조회");
			stage.show();

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	
	public void loginPageGo(ActionEvent event) {
		// TODO Auto-generated method stub
		Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		FXMLLoader loginStage = new FXMLLoader(getClass().getResource("/main/fxml/login.fxml"));
		Parent root;
		try {
			root = loginStage.load();
			stage.setScene(new Scene(root));
			
			Controller abcc = loginStage.getController(); // 컨트롤러 가져오기 (FXML 로드 후)
	        abcc.setRoot(root); 
	        
			stage.setTitle("로그인/회원가입 페이지");
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	    }
	
		
		
		
		



	


