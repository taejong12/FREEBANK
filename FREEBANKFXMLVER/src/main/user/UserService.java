package main.user;

import javafx.scene.Parent;

public interface UserService {

	UserDTO selectUserInfoById(Parent root); // 회원정보조회

	void insertUser(Parent root); // 회원가입

	void loginPage(Parent root); // 로그인 페이지 출력

	void joinPage(Parent root); // 회원가입 페이지 출력

}
