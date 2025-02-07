package main.user;

import javafx.scene.Parent;

public interface UserService {
	UserDTO userLogin(); // 로그인

	UserDTO userLogout(); // 로그아웃

	UserDTO selectUserInfoById(Parent root); // 회원정보조회

	void insertUser(Parent root); // 회원가입

	UserDTO updateUser(String id); // 회원수정

	UserDTO deleteUser(String id); // 회원탈퇴

	void loginScreen(Parent root);	//회원로그인화면
	
	void joinScreen(Parent root);	//회원가입화면

}
