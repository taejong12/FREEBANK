package main.board;

import java.util.List;

public class BoardServiceImpl implements BoardService {

	BoardDAO bd = new BoardDAO();
	
	@Override
	public List<BoardDTO> selectBoardList() {
		return bd.selectBoardList();
	}
}