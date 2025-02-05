package main.board;

import java.sql.Date;

public class BoardDTO {
   private String title;
   private String memberid;
   private Date date;  // 작성 날짜
   private String detail;
   private Date modifieddate;  // 수정 날짜

   // 기본 생성자
   public BoardDTO() {
       // 현재 날짜로 초기화
       this.date = new Date(System.currentTimeMillis());  // 작성 날짜
       this.modifieddate = new Date(System.currentTimeMillis());  // 수정 날짜
   }

 
   public String getTitle() {
       return title;
   }

   public void setTitle(String title) {
       this.title = title;
   }

   public String getMemberid() {
       return memberid;
   }

   public void setMemberid(String memberid) {
       this.memberid = memberid;
   }

   public Date getDate() {
       return date;
   }

   public void setDate(Date date) {
       this.date = date;
   }

   public String getDetail() {
       return detail;
   }

   public void setDetail(String detail) {
       this.detail = detail;
   }

   public Date getModifieddate() {
       return modifieddate;
   }

   public void setModifieddate(Date modifieddate) {
       this.modifieddate = modifieddate;
   }

   // 디버깅을 위한 toString() 메서드
   @Override
   public String toString() {
       return "FreeBankBoard{" +
               "title='" + title + '\'' +
               ", memberid='" + memberid + '\'' +
               ", date=" + date +
               ", detail='" + detail + '\'' +
               ", modifieddate=" + modifieddate +
               '}';
       }
}


