module trainProject {
	requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
	requires java.sql;
	requires javafx.base;
	requires jdk.jsobject;
	requires javafx.graphics;
	
	
	exports main;
	opens main.login.controller to javafx.fxml;
	opens main.member.controller to javafx.fxml;
	opens main.main.controller to javafx.fxml;
	opens main.main_logon.controller to javafx.fxml;
	opens main.mileage.controller to javafx.fxml;
	opens main.Mypage.controller to javafx.fxml;
	opens main.Payment.controller to javafx.fxml;
	opens main.ticket.controller to javafx.fxml;
	opens main.Traincheck.controller to javafx.fxml;
	opens main.History.controller to javafx.fxml;
	opens main.ChoiceSeat.controller to javafx.fxml;
	opens main.searchtrain.controller to javafx.fxml;
	opens main.searchtrain.dto to javafx.base;
	opens main.History.dto to javafx.base;
	

	
	
	

    opens main to javafx.graphics;

}