package main.user;

import java.sql.Date;

public class UserDTO {

	// FREEBANKUSER(회원) 테이블 컬럼
	private String userId; // 회원_아이디(PK)
	private String userName; // 회원_이름
	private String userPwd; // 회원_비밀번호
	private int userAge; // 회원_나이
	private String userSex; // 회원_성별(X:남, Y:여)
	private String userEmail; // 회원_이메일
	private String userAdmin; // 회원_관리자여부(일반회원:N, 관리자:Y)(기본값: N)
	private int userCreditRating; // 회원_신용등급(기본값: 5)
	private int userTotal; // 회원_누적금액(기본값: 0)
	private Date userCreate; // 회원_생성일(기본값: 현재날짜)
	private Date updateCreate; // 회원_수정일(기본값: 현재날짜)

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserAdmin() {
		return userAdmin;
	}

	public void setUserAdmin(String userAdmin) {
		this.userAdmin = userAdmin;
	}

	public int getUserCreditRating() {
		return userCreditRating;
	}

	public void setUserCreditRating(int userCreditRating) {
		this.userCreditRating = userCreditRating;
	}

	public int getUserTotal() {
		return userTotal;
	}

	public void setUserTotal(int userTotal) {
		this.userTotal = userTotal;
	}

	public Date getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(Date userCreate) {
		this.userCreate = userCreate;
	}

	public Date getUpdateCreate() {
		return updateCreate;
	}

	public void setUpdateCreate(Date updateCreate) {
		this.updateCreate = updateCreate;
	}

}
