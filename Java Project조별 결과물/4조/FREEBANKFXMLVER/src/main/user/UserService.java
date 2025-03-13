package main.user;

import java.util.List;

import javafx.scene.Parent;
import main.shop.PurchaseListDTO;

public interface UserService {

	UserDTO selectUserInfoById(Parent root); // 회원정보조회

	void insertUser(Parent root); // 회원가입

	void loginPage(Parent root); // 로그인 페이지 출력

	void joinPage(Parent root); // 회원가입 페이지 출력

	void userInfoPage(Parent root, UserDTO userDTO); // 마이페이지 출력

	void userPurchaseListPage(Parent root, UserDTO userDTO); // 회원구매내역 목록페이지 출력

	void userInfoDetailPage(Parent root, UserDTO userDTO); // 회원정보페이지 출력

	void userIdPwdCheckPage(Parent root, UserDTO userDTO); // 회원수정 인증페이지 출력

	void updateUserInfo(UserDTO userDTO); // 회원정보 수정

	void deleteUserInfo(UserDTO userDTO); // 회원정보 삭제

	boolean userInfoCheck(Parent root, UserDTO userDTO); // 회원 아이디/비밀번호 인증

	List<PurchaseListDTO> selectUserPLByID(String userId); // 회원 구매내역 목록 조회

}
