package main.pay;



import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.category.controller.CategoryController;

public class Controller {
	Parent root;
	String s;
	    @FXML
	    private RadioButton radioButton1;
	    @FXML
	    private RadioButton radioButton2;
	    @FXML
	    private RadioButton radioButton3;
	    @FXML
	    private RadioButton radioButton4;
	    
	    @FXML
	    private Text product1;
	    @FXML
	    private Text product2;
	    @FXML
	    private Text product3;
	    @FXML
	    private Text product4;
	    
	    @FXML
	    private Text paymentAmountText1;
	    @FXML
	    private Text paymentAmountText2;
	    @FXML
	    private Text paymentAmountText3;
	    @FXML
	    private Text paymentAmountText4;
	    @FXML
	    private Text paymentProductText1;
	    @FXML
	    private Text paymentProductText2;
	    @FXML
	    private Text paymentProductText3;
	    @FXML
	    private Text paymentProductText4;
	    @FXML
	    private Text sum;
	    @FXML
	    private Text sum1;
	    @FXML
	    private TextField card;
	    @FXML
	    private TextField cvc;
	    @FXML
	    private TextField cardnumber;
	    
	    
	   public void cardinfo(String s) {
		   this.s = s;
		
	   }
	    
	    // PayService 인스턴스 생성
	    DTOService DTOService = new DTOService();
	    Dao d = new Dao();
	    Dto dto = new Dto();

	    // 라디오 버튼이 선택되었을 때 호출되는 메서드
	    public void setRoot(Parent root) {
			// TODO Auto-generated method stub
			this.root = root;
		}
	  
	    // 장바구니 페이지가 로드될 때 호출되는 메서드
	    public void initialize() {
	    	
	        // 카테고리 페이지에서 저장한 상품명 불러오기
	    	List<String> basketItems = CategoryController.getProductName();
	    	List<Integer> priceItems = CategoryController.getProductPrice();
	    	if (basketItems != null && !basketItems.isEmpty()) {
	    	    System.out.println("장바구니 항목: " + basketItems);
	    	}
	       
	        // 텍스트 업데이트
	        if (basketItems.size()>0) {
	        if (product1 != null) {
	            product1.setText(String.valueOf(priceItems.get(0)));
	            radioButton1.setText(basketItems.get(0));
	        }
	        
	        }
	        if (basketItems.size()>1) {
	        	if (product2 != null) {
	        		product2.setText(String.valueOf(priceItems.get(1)));
		            radioButton2.setText(basketItems.get(1));
	        	 }
	        }
	        if (basketItems.size()>2) {
	        	 if (product3 != null) {
	        		 product3.setText(String.valueOf(priceItems.get(2)));
	 	            radioButton3.setText(basketItems.get(2));
	        	 }
	        }
	        if (basketItems.size()>3) {
	        	 if (product4 != null) {
	        		 product4.setText(String.valueOf(priceItems.get(3)));
	 	            radioButton4.setText(basketItems.get(3));
	        	 }
	        }
	      
	   
	    }
	    
	    @FXML
	    private void onRadioButtonSelected() {
	    	
	        // PayService의 handleRadioButtonSelection 메서드 호출
	        DTOService.handleRadioButtonSelection(radioButton1, radioButton2, radioButton3, radioButton4,
	                                              paymentAmountText1, paymentAmountText2, paymentAmountText3, paymentAmountText4,
	                                              paymentProductText1, paymentProductText2, paymentProductText3, paymentProductText4,
	                                              product1, product2, product3, product4,sum);
	    }
	    public void payProc(ActionEvent event) {
	    	DTOService.cardPay(event,cardnumber);
	    	
	    }
		public void loginPage (ActionEvent event) {
			DTOService.loginPageGo(event);
		}
	 
	    public void backButtonAction(ActionEvent event) {
	    	DTOService.backButtonAction(event);
	    }
	    public void delivery(ActionEvent event) {
	    	DTOService.delivery(event);
	    }
	   
	    public void cancle(ActionEvent event) {
	    	
	    	
	    	
	    }
	    
	    public void save(ActionEvent event) {
	   
	   
	    }
	    public TextField getCardnumber() {
	        return cardnumber;
	    }

	    public void setCardnumber(String value) {
	        cardnumber.setText(value);
	    }
	    public void reset() {
	    	
	    	
	    }

	    
}