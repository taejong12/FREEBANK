package main.admin;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;
import main.account.AccountDTO;
import main.board.BoardDTO;
import main.menu.MenuService;
import main.menu.MenuServiceImple;
import main.shop.ShopDTO;
import main.user.UserDTO;

public class AdminController {

	Parent root;
	UserDTO userDTO;
	AdminService as = new AdminServiceImpl();
	MenuService ms = new MenuServiceImple();
	BoardDTO boardDTO;
	AccountDTO accountDTO;

	// 공지사항 목록 화면 컬럼
	@FXML
	TableView<BoardDTO> boardListTable;
	@FXML
	private TableColumn<BoardDTO, Integer> boardIdColumn;
	@FXML
	private TableColumn<BoardDTO, String> boardTitleColumn;
	@FXML
	private TableColumn<BoardDTO, String> boardAuthorColumn;
	@FXML
	private TableColumn<BoardDTO, String> boardContentColumn;

	// 공지사항 상세보기 페이지 컬럼
	@FXML
	private Text adminDetailBoardId;
	@FXML
	private Text adminDetailBoardTitle;
	@FXML
	private Text adminDetailBoardAuthor;
	@FXML
	private Text adminDetailBoardContent;
	@FXML
	private Text adminDetailBoardCreated;
	@FXML
	private Text adminDetailBoardUpdate;

	// 공지사항 수정하기 페이지 컬럼
	@FXML
	private TextField boardTitle;
	@FXML
	private TextField boardContent;

	// 계좌 목록 화면 컬럼
	@FXML
	TableView<AccountDTO> accountListTable;
	@FXML
	private TableColumn<AccountDTO, String> accountAccountColumn;
	@FXML
	private TableColumn<AccountDTO, Integer> accountBalanceColumn;
	@FXML
	private TableColumn<AccountDTO, String> accountCreateColumn;
	@FXML
	private TableColumn<AccountDTO, String> accountIdColumn;

	public void setRoot(Parent root) {
		this.root = root;
	}

	// UserDTO를 설정하는 메서드 추가
	public void setUser(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public void showUserInfo() {
		if (userDTO != null) {
			System.out.println("현재 로그인한 사용자: " + userDTO.getUserId());
		} else {
			System.out.println("로그인 정보 없음");
		}
	}

	// 공지사항 정보 저장
	public void setBoard(BoardDTO boardDTO) {
		this.boardDTO = boardDTO;
	}

	// 공지사항 목록 출력
	public void selectAdminBoardList() {

		List<BoardDTO> selectBoardList = as.selectAdminBoardList();

		ObservableList<BoardDTO> boardList = FXCollections.observableArrayList(selectBoardList);

		boardIdColumn.setCellValueFactory(new PropertyValueFactory<>("boardId"));
		boardAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("boardAuthor"));
		boardContentColumn.setCellValueFactory(new PropertyValueFactory<>("boardContent"));
		boardTitleColumn.setCellValueFactory(new PropertyValueFactory<>("boardTitle"));

		// 상품이름을 Hyperlink로 변환하는 커스텀 Cell 설정
		boardTitleColumn.setCellFactory(new Callback<TableColumn<BoardDTO, String>, TableCell<BoardDTO, String>>() {
			public TableCell<BoardDTO, String> call(TableColumn<BoardDTO, String> param) {
				return new TableCell<>() {
					private final Hyperlink link = new Hyperlink();

					{
						link.setOnAction(event -> {
							BoardDTO boardDTO = getTableView().getItems().get(getIndex());
							as.adminBoardDetail(root, boardDTO, userDTO);
						});
					}

					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (empty || item == null) {
							setGraphic(null);
						} else {
							link.setText(item);
							setGraphic(link);
						}
					}
				};
			}
		});

		boardListTable.setItems(boardList);
	}

	// 공지사항 상세보기 출력 정보
	public void selectAdminBoardInfo() {
		adminDetailBoardId.setText(String.valueOf(boardDTO.getBoardId()));
		adminDetailBoardTitle.setText(boardDTO.getBoardTitle());
		adminDetailBoardAuthor.setText(boardDTO.getBoardAuthor());
		adminDetailBoardContent.setText(boardDTO.getBoardContent());
		adminDetailBoardCreated.setText(String.valueOf(boardDTO.getBoardCreated()));
		adminDetailBoardUpdate.setText(String.valueOf(boardDTO.getBoardUpdate()));

		System.out.println("공지사항 번호: " + adminDetailBoardId.getText());
		System.out.println("공지사항 제목: " + adminDetailBoardTitle.getText());
		System.out.println("공지사항 작성자: " + adminDetailBoardAuthor.getText());
		System.out.println("공지사항 내용: " + adminDetailBoardContent.getText());
		System.out.println("공지사항 작성일: " + adminDetailBoardCreated.getText());
		System.out.println("공지사항 수정일: " + adminDetailBoardUpdate.getText());
	}

	// 공지사항 수정 정보 불러오기
	public void updateAdminBoardInfo() {
		adminDetailBoardId.setText(String.valueOf(boardDTO.getBoardId()));
		adminDetailBoardAuthor.setText(boardDTO.getBoardAuthor());
		adminDetailBoardCreated.setText(String.valueOf(boardDTO.getBoardCreated()));
		adminDetailBoardUpdate.setText(String.valueOf(boardDTO.getBoardUpdate()));
		boardTitle.setText(boardDTO.getBoardTitle());
		boardContent.setText(boardDTO.getBoardContent());

		System.out.println("공지사항 번호: " + adminDetailBoardId.getText());
		System.out.println("공지사항 작성자: " + adminDetailBoardAuthor.getText());
		System.out.println("공지사항 작성일: " + adminDetailBoardCreated.getText());
		System.out.println("공지사항 수정일: " + adminDetailBoardUpdate.getText());
		System.out.println("공지사항 제목: " + boardTitle.getText());
		System.out.println("공지사항 내용: " + boardContent.getText());
	}

	// 계좌목록 출력하기
	public void selectAdminAccountList() {

		List<AccountDTO> selectAccountList = as.selectAccountListAll();

		ObservableList<AccountDTO> accountList = FXCollections.observableArrayList(selectAccountList);

		accountAccountColumn.setCellValueFactory(new PropertyValueFactory<>("accountAccount"));
		accountBalanceColumn.setCellValueFactory(new PropertyValueFactory<>("accountBalance"));
		accountCreateColumn.setCellValueFactory(new PropertyValueFactory<>("accountCreate"));
		accountIdColumn.setCellValueFactory(new PropertyValueFactory<>("accountId"));

		accountListTable.setItems(accountList);
	}

	// 공지사항 관리페이지 출력
	public void adminBoardPage() {
		System.out.println("공지사항 관리페이지로 이동");
		as.adminBoardPage(root, userDTO);
	}

	// 계좌 관리페이지 출력
	public void adminAccountPage() {
		System.out.println("계좌 관리페이지로 이동");
		as.adminAccountPage(root, userDTO);
	}

	// 쇼핑몰 관리페이지 출력
	public void adminShopPage() {
		System.out.println("쇼핑몰 관리페이지로 이동");
		as.adminShopPage(root, userDTO);
	}

	// 회원 관리페이지 출력
	public void adminUserPage() {
		System.out.println("회원 관리페이지로 이동");
		as.adminUserPage(root, userDTO);
	}

	// 관리자 로그아웃(메인페이지(비로그인))
	public void adminLogout() {
		System.out.println("관리자 로그아웃");
		userDTO = new UserDTO();
		ms.mainMenu(root, userDTO);
	}

	// 공지사항 등록페이지 출력
	public void insertAdminBoardPage() {
		System.out.println("공지사항 등록페이지로 이동");
		as.insertAdminBoardPage(root, userDTO);
	}

	// 공지사항 목록페이지(수정/삭제) 출력
	public void selectAdminBoardListPage() {
		System.out.println("공지사항 목록페이지(수정/삭제)로 이동");
		as.selectAdminBoardListPage(root, userDTO);
	}

	// 관리자 메인페이지 출력(이전페이지)
	public void adminMainMenu() {
		System.out.println("메인메뉴로 이동");
		ms.adminMainMenu(root, userDTO);
	}

	// 공지사항 등록하기
	public void insertAdminBoard() {
		System.out.println("공지사항 등록");
		as.insertAdminBoard(root, userDTO);
		// 공지사항 관리페이지 출력
		adminBoardPage();
	}

	// 공지사항 수정페이지 출력
	public void updateAdminBoardPage() {
		System.out.println("공지사항 수정페이지로 이동");
		as.updateAdminBoardPage(root, userDTO, boardDTO);
	}

	// 공지사항 삭제하기
	public void deleteAdminBoard() {
		System.out.println("공지사항 삭제하기");
		as.deleteAdminBoard(boardDTO);
		// 공지사항 목록페이지(수정/삭제) 출력
		selectAdminBoardListPage();
	}

	// 공지사항 수정하기
	public void updateAdminBoard() {
		System.out.println("공지사항 수정하기");
		as.updateAdminBoard(root, userDTO, boardDTO);
		// 공지사항 목록페이지(수정/삭제) 출력
		selectAdminBoardListPage();
	}

	// 상품 등록페이지 출력
	public void insertAdminShopPage() {
		System.out.println("상품 등록페이지로 이동");
		as.insertAdminShopPage(root, userDTO);
	}

	// 상품 조회(수정/삭제)페이지 출력
	public void updateAdminShopPage() {
		System.out.println("상품 조회페이지로 이동");
		as.updateAdminShopPage(root, userDTO);
	}

	// 회원 목록페이지 출력
	public void adminUserListPage() {
		System.out.println("회원 목록페이지로 이동");
		as.adminUserListPage(root, userDTO);
	}

	// 구매내역 목록페이지 출력
	public void adminPLPage() {
		System.out.println("구매내역 목록페이지로 이동");
		as.adminPLPage(root, userDTO);
	}

}
