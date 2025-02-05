package main.board;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    // Oracle DB 연결 정보를 설정합니다.
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";  // 접속할 DB의 URL (SID가 xe일 경우)
    private static final String USER = "freebank";  // 사용자명 수정
    private static final String PASSWORD = "1234";  // 비밀번호 수정

    // 게시글 추가 메소드
    public void addBoard(String title, String memberId, String detail) {
        // SQL 쿼리 (게시글 추가)
        String sql = "INSERT INTO Board (\"TITLE\", \"MEMBERID\", \"DETAIL\", \"DATE\", \"MODIFIEDDATE\") VALUES (?, ?, ?, ?, ?)";

        // 데이터베이스 연결 및 쿼리 실행
        try (
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            // 현재 시간으로 작성 날짜와 수정 날짜 설정
            Date currentDate = new Date(System.currentTimeMillis());

            // PreparedStatement에 값 설정
            stmt.setString(1, title);            // 제목
            stmt.setString(2, memberId);         // 회원 아이디
            stmt.setString(3, detail);           // 내용
            stmt.setDate(4, currentDate);        // 작성 날짜
            stmt.setDate(5, currentDate);        // 수정 날짜

            // 쿼리 실행
            int rowsAffected = stmt.executeUpdate();

            // 데이터가 성공적으로 삽입되었는지 확인
            if (rowsAffected > 0) {
                conn.commit(); // 커밋 추가
                System.out.println("게시글이 추가되었습니다.");
            }

        } catch (SQLException e) {
            // 예외 처리
            e.printStackTrace();
        }
    }

    // 모든 게시글 조회 메소드
    public List<Board> getAllBoards() {
        List<Board> boards = new ArrayList<>();
        String sql = "SELECT * FROM Board";

        // 데이터베이스 연결 및 쿼리 실행
        try (
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // 결과셋을 읽어서 List에 추가
            while (rs.next()) {
                int id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String memberId = rs.getString("MEMBERID");
                String detail = rs.getString("DETAIL");
                Date createdDate = rs.getDate("DATE");
                Date modifiedDate = rs.getDate("MODIFIEDDATE");

                // Board 객체 생성 후 리스트에 추가
                boards.add(new Board(id, title, memberId, detail, createdDate, modifiedDate));
            }

        } catch (SQLException e) {
            // 예외 처리
            e.printStackTrace();
        }

        return boards;
    }

    // Board 클래스 (게시글 정보를 담는 객체)
    public static class Board {
        private int id;
        private String title;
        private String memberId;
        private String detail;
        private Date createdDate;
        private Date modifiedDate;

        public Board(int id, String title, String memberId, String detail, Date createdDate, Date modifiedDate) {
            this.id = id;
            this.title = title;
            this.memberId = memberId;
            this.detail = detail;
            this.createdDate = createdDate;
            this.modifiedDate = modifiedDate;
        }

        // Getter 메소드
        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getMemberId() {
            return memberId;
        }

        public String getDetail() {
            return detail;
        }

        public Date getCreatedDate() {
            return createdDate;
        }

        public Date getModifiedDate() {
            return modifiedDate;
        }

        // Board 객체 출력
        @Override
        public String toString() {
            return "ID: " + id + ", 제목: " + title + ", 회원ID: " + memberId +
                    ", 내용: " + detail + ", 작성일: " + createdDate + ", 수정일: " + modifiedDate;
        }
    }

    public static void main(String[] args) {
        BoardDAO boardDAO = new BoardDAO();

        // 게시글 추가
        boardDAO.addBoard("제목 예시", "회원ID123", "여기에 긴 내용이 들어갑니다.");

        // 게시글 조회
        List<Board> boards = boardDAO.getAllBoards();
        for (Board board : boards) {
            System.out.println(board);
        }
    }
}
