package main.user;

public class UserDTO {
   private String userId; // 가입할 아이디
   private String userName; // 가입할 이름
   private String userPwd; // 가입할 비밀번호
   private int userAge; // 나이
   private String userSex; // 성별
   private String userEmail; // 이메일
   private String userAdmin; // 관리자 여부
   private int userCreditRating; // 신용등급
   private int userTotal; // 누적금액

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

}
