package main.board;

import javafx.scene.Parent;
import main.user.UserDTO;

public class BoardController {

	Parent root;
	UserDTO userDTO;

	public void setRoot(Parent root) {
		this.root = root;

	}

	// UserDTO를 설정하는 메서드 추가
	public void setUser(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public void showUserInfo() {
		if (userDTO != null) {
			System.out.println("현재 로그인한 사용자: " + userDTO.getUserId());
		} else {
			System.out.println("로그인 정보 없음");
		}
	}

}
