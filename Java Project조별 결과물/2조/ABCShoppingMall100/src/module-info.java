module ABCShoppingMall {

	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	
	
	exports main;
	
	 exports main.pay;
	opens main.abcshoppingmall.controller to javafx.fxml, javafx.graphics;
	opens main.category.controller to javafx.fxml, javafx.graphics;
	opens main.delivery.controller to javafx.fxml, javafx.graphics;
	opens main.login.controller to javafx.fxml, javafx.graphics;
	opens main.member.controller to javafx.fxml, javafx.graphics;
	opens main.question.controller to javafx.fxml, javafx.graphics;
	 opens main.pay to javafx.fxml,javafx.graphics;
	 

}