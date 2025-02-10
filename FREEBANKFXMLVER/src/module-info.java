module FREEBANKFXMLVER {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;

	exports main;
	opens main.account;
	opens main.admin;
	opens main.board;
	opens main.menu;
	opens main.shop;
	opens main.user;
	
}