package main.board;

import java.sql.Date;

public class BoardDTO {
	private int id; // 게시판 번호
	private String title; // 제목
	private String content; // 내용
	private Date created; // 생성 날짜
	private Date update; // 수정 날짜
	private String author; // 작성자(관리자 id)

	// 관리자 아이디
	private String boardAdminId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public String getBoardAdminId() {
		return boardAdminId;
	}

	public void setBoardAdminId(String boardAdminId) {
		this.boardAdminId = boardAdminId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
