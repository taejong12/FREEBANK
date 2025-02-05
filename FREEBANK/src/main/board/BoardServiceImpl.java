package main.board;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class BoardServiceImpl implements BoardService{
   private List<BoardDTO> boards = new ArrayList<>(); 
    public void addBoard(BoardDTO board) {
        boards.add(board);
        System.out.println("게시글이 추가되었습니다.");
    }

    public void deleteBoard(int index) {
        if (index >= 0 && index < boards.size()) {
            boards.remove(index);
            System.out.println("게시글이 삭제되었습니다.");
        } else {
            System.out.println("잘못된 게시글 번호입니다.");
        }
    }

    public void updateBoard(int index, BoardDTO updatedBoard) {
        if (index >= 0 && index < boards.size()) {
            boards.set(index, updatedBoard);
            System.out.println("게시글이 수정되었습니다.");
        } else {
            System.out.println("잘못된 게시글 번호입니다.");
        }
    }

    public List<BoardDTO> getAllBoards() {
        return boards;
    }

   
    public void bankboardMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. 게시글 추가");
            System.out.println("2. 게시글 출력");
            System.out.println("3. 게시글 수정");
            System.out.println("4. 게시글 삭제");
            System.out.println("5. 종료");
            System.out.print("메뉴를 선택하세요: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            switch (choice) {
                case 1:
                    System.out.print("제목을 입력하세요: ");
                    String title = scanner.nextLine();
                    System.out.print("회원 아이디를 입력하세요: ");
                    String memberId = scanner.nextLine();
                    System.out.print("내용을 입력하세요: ");
                    String detail = scanner.nextLine();

                   BoardDTO board = new BoardDTO();
                    board.setTitle(title);
                    board.setMemberid(memberId);
                    board.setDetail(detail);
                    board.setDate(new Date(System.currentTimeMillis()));
                    board.setModifieddate(new Date(System.currentTimeMillis()));

                    addBoard(board);
                    break;
                case 2:
                    displayBoards();
                    break;
                case 3:
                    System.out.print("수정할 게시글 번호를 입력하세요: ");
                    int index = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (index < 0 || index >= boards.size()) {
                        System.out.println("잘못된 게시글 번호입니다.");
                        break;
                    }

                    System.out.print("새 제목을 입력하세요: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("새 내용을 입력하세요: ");
                    String newDetail = scanner.nextLine();

                    BoardDTO updatedBoard = new BoardDTO();
                    updatedBoard.setTitle(newTitle);
                    updatedBoard.setDetail(newDetail);
                    updatedBoard.setModifieddate(new Date(System.currentTimeMillis()));

                    updateBoard(index, updatedBoard);
                    break;
                case 4:
                    System.out.print("삭제할 게시글 번호를 입력하세요: ");
                    int deleteIndex = scanner.nextInt() - 1;
                    deleteBoard(deleteIndex);
                    break;
                case 5:
                    System.out.println("프로그램 종료.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    public void displayBoards() {
        if (boards.isEmpty()) {
            System.out.println("게시글이 없습니다.");
        } else {
            for (int i = 0; i < boards.size(); i++) {
                BoardDTO board = boards.get(i);
                System.out.println("[" + (i + 1) + "] 제목: " + board.getTitle());
                System.out.println("회원 아이디: " + board.getMemberid());
                System.out.println("작성 날짜: " + board.getDate());
                System.out.println("내용: " + board.getDetail());
                System.out.println("수정 날짜: " + board.getModifieddate());
                System.out.println("-------------------------------");
            }
        }
    }
}