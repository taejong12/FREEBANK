module gym_exam03 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	
	exports main; 
	opens main.exam01.controller to javafx.fxml;
	exports main.exam01.service;
}