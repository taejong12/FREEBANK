package main.user;

public interface UserService {

	UserDTO userLogin(); // 로그인

	UserDTO userLogout(); // 로그아웃

	UserDTO userInfo(String id); // 유저 정보

	void userRegister(); // 회원가입

	UserDTO userOutput(String id); // 유저 정보 출력 함수

	UserDTO userUpdate(String id); // 유저 정보 수정 함수

	UserDTO userDelete(String id); // 회원탈퇴

}
