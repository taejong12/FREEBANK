package main.board;

import java.sql.Date;

public class BoardDTO {

	// FREEBANKBOARD(게시판) 테이블 컬럼
	private int boardId; // 게시판_고유번호(PK)
	private String boardTitle; // 게시판_제목
	private String boardAuthor; // 게시판_작성자
	private String boardContent; // 게시판_내용
	private Date boardCreated; // 게시판_작성일(기본값: 현재날짜)
	private Date boardUpdate; // 게시판_수정일(기본값: 현재날짜)

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardAuthor() {
		return boardAuthor;
	}

	public void setBoardAuthor(String boardAuthor) {
		this.boardAuthor = boardAuthor;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public Date getBoardCreated() {
		return boardCreated;
	}

	public void setBoardCreated(Date boardCreated) {
		this.boardCreated = boardCreated;
	}

	public Date getBoardUpdate() {
		return boardUpdate;
	}

	public void setBoardUpdate(Date boardUpdate) {
		this.boardUpdate = boardUpdate;
	}

}
