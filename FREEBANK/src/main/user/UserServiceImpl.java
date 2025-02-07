package main.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {
	Scanner sc;
	UserDAO userDAO;
	List<UserDTO> userList;
	UserDTO user;

	public UserServiceImpl() {
		user = new UserDTO();
		sc = new Scanner(System.in);
		userDAO = new UserDAO();
		userList = new ArrayList<UserDTO>();
	}

	public UserDTO userLogin() {
		System.out.println("### 로그인 ###");

		System.out.print("아이디를 입력해주세요: ");
		String id = sc.next();

		System.out.print("비밀번호를 입력해주세요: ");
		String pwd = sc.next();

		UserDTO userDTO = new UserDTO();
		userDTO = userDAO.userSelectId(id);

		if (userDTO == null) {
			System.out.println("아이디/비밀번호가 존재하지 않습니다.");
		} else if (id.equals(userDTO.getUserId()) && pwd.equals(userDTO.getUserPwd())) {
			if ("y".equals(userDTO.getUserAdmin())) {
				System.out.println("관리자로 로그인하셨습니다.");
				return userDTO;
			} else {
				System.out.println("회원으로 로그인하셨습니다.");
				return userDTO;
			}
		}
		return new UserDTO();
	}

	public void userRegister() {
		System.out.println("### 회원가입 ###");
		UserDTO user = new UserDTO();

		try {
			System.out.print("ID: ");
			String userId = sc.next();

			// 아이디 중복 검사
			boolean idExists = false;

			userList = userDAO.userSelect();

			for (UserDTO u : userList) {
				if (userId.equals(u.getUserId())) {
					idExists = true;
					break;
				}
			}
			if (idExists) {
				System.out.println("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요.");
				return; // 다시 회원가입을 시작하게 하기 위해 메서드를 종료
			}
			user.setUserId(userId); // 중복되지 않으면 아이디를 등록

			System.out.print("이름: ");
			user.setUserName(sc.next()); // 이름 입력

			System.out.print("비밀번호: ");
			user.setUserPwd(sc.next()); // 비밀번호 입력

			int age;
			while (true) {
				try {
					System.out.print("나이: ");
					age = sc.nextInt();
					sc.nextLine(); // 입력 버퍼 클리어
					break;
				} catch (Exception e) {
					System.out.println("나이는 숫자로 입력해야 합니다. 다시 입력해주세요.");
					sc.nextLine(); // 잘못된 입력 처리 후 버퍼 비우기
				}
			}
			user.setUserAge(age);

			String gender;
			while (true) {
				System.out.print("성별 (남 : X / 여 : Y): ");
				gender = sc.next().toUpperCase(); // 소문자로 입력해도 자동 변환
				if (gender.equals("X") || gender.equals("Y")) { // x 또는 y 가 맞다면
					break;
				} else {
					System.out.println("잘못된 입력입니다. 'X' 또는 'Y'만 입력해주세요."); // x,y 이외에 다른 문자 입력 시 나오는 문구
				}
			}
			user.setUserSex(gender);
			// 이메일 입력 및 정규식 검증
			String email;
			while (true) {
				System.out.print("이메일: ");
				email = sc.next();
				if (isValidEmail(email)) {
					break;
				} else {
					System.out.println("올바른 이메일 형식이 아닙니다. 다시 입력해주세요.");
				}
			}
			user.setUserEmail(email); // 이메일 형식이 올바르면 저장

			user.setUserAdmin("n"); // 일반회원은 관리자값 n

			user.setUserCreditRating(0); // 회원가입 시 신용도 등급 0

			user.setUserTotal(0); // 회원가입시 누적금액 0

			int result = userDAO.userRegister(user);
			if (result >= 1) {
				System.out.println(user.getUserId() + "님으로 회원가입이 완료되었습니다.");
			}

		} catch (Exception e) {
			System.out.println("회원가입 에러");
		}
	}

	// 이메일 형식 검증 메서드
	public boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(com|net|org|co\\.kr)$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public UserDTO userInfo(String id) {
		System.out.println("### 마이페이지 ###");
		System.out.println("1. 내 정보 보기");
		System.out.println("2. 내 정보 수정");
		System.out.println("3. 프리뱅크 회원 탈퇴");
		System.out.print("메뉴 선택: ");

		int menu = sc.nextInt();

		UserDTO userDTO = new UserDTO();

		switch (menu) {
		case 1:
			userDTO = userOutput(id); // 조회
			break;
		case 2:
			userDTO = userUpdate(id); // 수정
			break;
		case 3:
			userDTO = userDelete(id); // 탈퇴
			break;
		default:
			System.out.println("잘못된 입력입니다.");
		}

		return userDTO;

	}

	public UserDTO userOutput(String id) { // 내 정보 보기 회원
		user = userDAO.userSelectId(id);
		System.out.println("이름 : " + user.getUserName());
		System.out.println("아이디 : " + user.getUserId());
		System.out.println("비밀번호 : " + user.getUserPwd());
		System.out.println("나이 : " + user.getUserAge());
		System.out.println("성별 : " + user.getUserSex());
		System.out.println("이메일 : " + user.getUserEmail());
		System.out.println("관리자 여부 : " + user.getUserAdmin());
		System.out.println("신용등급 : " + user.getUserCreditRating());
		System.out.println("누적금액 : " + user.getUserTotal());
		return user;
	}

	public UserDTO userUpdate(String id) { // 회원 정보 수정 기능
		UserDTO user = userDAO.userSelectId(id); // id 값 먼저 주기
		System.out.println("### 회원 정보 수정 ###");
		System.out.print(id + "님의 정보를 수정하시겠습니까? (y/n): ");
		String updateAnswer = sc.next();
		if (updateAnswer.equals("y") || updateAnswer.equals("Y") || updateAnswer.equals("ㅛ")) {

			System.out.print("수정하실 비밀번호: ");
			user.setUserPwd(sc.next()); // 수정할 비밀번호

			String email;
			while (true) {
				System.out.print("수정하실 이메일: ");
				email = sc.next();

				if (isValidEmail(email)) {
					break;
				} else {
					System.out.println("올바른 이메일 형식이 아닙니다. 다시 입력해주세요.");
				}
			}
			user.setUserEmail(email);

			int result = userDAO.UserUpdate(user);
			if (result >= 1) {
				System.out.println(id + "님 수정을 정상적으로 완료했습니다. 확인을 위해 처음 메뉴로 돌아갑니다.");
				return new UserDTO();
			}
		} else {
			System.out.println("회원 정보 수정을 취소합니다.");
		}
		return user;
	}

	public UserDTO userDelete(String id) { // 회원 탈퇴
		UserDTO user = userDAO.userSelectId(id); // id 값 먼저 주기
		System.out.println("### 회원 탈퇴 ###");
		System.out.print(id + "님 회원 탈퇴를 하시겠습니까? (y/n): ");
		String deleteAnswer = sc.next();
		if (deleteAnswer.equals("y") || deleteAnswer.equals("Y") || deleteAnswer.equals("ㅛ")) {
			System.out.println("회원 탈퇴를 진행합니다. 비밀번호를 입력해주세요.");
			String pwd;
			while (true) {
				System.out.print("암호 입력 : ");
				pwd = sc.next();

				if (pwd.equals(user.getUserPwd())) {
					break;
				} else {
					System.out.println("입력된 암호가 기존 암호와 다릅니다. 다시 입력해주세요.");
				}
			}
			int result = userDAO.userDelete(pwd);
			if (result >= 1) {
				System.out.println(id + "님의 회원 탈퇴 되었습니다. 처음 메뉴로 돌아갑니다.");
				return new UserDTO();
			}
		} else {
			System.out.println("회원 탈퇴를 취소합니다.");
		}
		return user;
	}

	// 로그아웃
	public UserDTO userLogout() {
		System.out.println("로그아웃 되었습니다.");
		return new UserDTO();
	}

}
