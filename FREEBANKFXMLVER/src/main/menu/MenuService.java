package main.menu;

import javafx.scene.Parent;
import main.user.UserDTO;

public interface MenuService {

	void mainMenu(Parent root, UserDTO userDTO);		//전체 메뉴
	void loginMainMenu(Parent root, UserDTO userDTO);	//일반 로그인 메뉴
	void adminMainMenu(Parent root, UserDTO userDTO);	//관리자 메뉴
}
