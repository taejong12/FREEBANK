package main.board;

import java.util.List;

public interface BoardService {
    public void deleteBoard(int index);
    public void updateBoard(int index, BoardDTO updatedBoard);
    public List<BoardDTO> getAllBoards();
    public void bankboardMenu();
    public void displayBoards();
}