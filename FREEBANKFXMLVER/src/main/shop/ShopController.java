package main.shop;

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
import main.menu.MenuService;
import main.menu.MenuServiceImple;
import main.user.UserDTO;
import main.user.UserService;
import main.user.UserServiceImpl;

public class ShopController {

	Parent root;
	UserDTO userDTO;
	MenuService ms = new MenuServiceImple();
	ShopService ss = new ShopServiceImpl();
	ShopDTO shopDTO;
	UserService us = new UserServiceImpl();

	// 상품 목록 바인딩
	@FXML
	TableView<ShopDTO> shopListTable;
	@FXML
	private TableColumn<ShopDTO, Integer> shopIdColumn;
	@FXML
	private TableColumn<ShopDTO, String> shopNameColumn;
	@FXML
	private TableColumn<ShopDTO, Integer> shopPriceColumn;

	// 상품 상세페이지 컬럼
	@FXML
	private Text shopId;
	@FXML
	private Text shopName;
	@FXML
	private Text shopContents;
	@FXML
	private Text shopPrice;
	@FXML
	private Text shopTotalShopCountText;
	@FXML
	private Text shopTotalPayment;

	// 총상품갯수
	@FXML
	private TextField shopTotalShopCount;

	// 상품 결제페이지 입력
	@FXML
	private TextField shopPayAccount;
	@FXML
	private TextField shopPayUserId;
	@FXML
	private TextField shopPayUserPwd;

	public void setRoot(Parent root) {
		this.root = root;
	}

	// UserDTO를 설정하는 메서드 추가
	public void setUser(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	// ShopDTO 저장
	public void setShop(ShopDTO shop) {
		this.shopDTO = shop;
	}

	// 1.메인페이지(비로그인 기능)
	// 상품 목록 페이지 이동(비로그인)
	public void shopListPage() {
		System.out.println("상품 목록 페이지로 이동(비로그인)");
		ss.shopListPage(root, userDTO);
	}

	// 1.메인페이지(비로그인 기능)
	// 비로그인 상태 - 결제 버튼 클릭 - 로그인 페이지
	public void userShopPayLoginPage() {
		System.out.println("로그인 후 결제 가능");
		ss.userShopPayLoginPage(root, userDTO);
		us.loginPage(root);
	}

	// 1.메인페이지(비로그인 기능)
	// 메인페이지 출력(비로그인) - 상품목록(이전페이지) - 로그인(이전페이지) - 회원가입(이전페이지)
	public void mainMenu() {
		System.out.println("메인페이지로 이동");
		ms.mainMenu(root, userDTO);
	}

	// 2.로그인 후 기능
	// 회원메인페이지
	public void loginMainMenu() {
		System.out.println("회원메인페이지(로그인)로 이동");
		ms.loginMainMenu(root, userDTO);
	}

	// 2.로그인 후 기능
	// 상품목록페이지
	public void shopLoginListPage() {
		System.out.println("상품 목록 페이지로 이동(회원로그인)");
		ss.shopLoginListPage(root, userDTO);
	}

	// 2.로그인 후 기능
	// 상품 결제하기
	public void userShopPayment() {
		System.out.println("상품 결제하기");
		boolean result = ss.userShopPayment(root, shopDTO, userDTO);
		if (!result) {
			shopLoginListPage();
		}
	}

	// 3.화면출력
	// 상품 목록(비로그인)
	public void selectShopList() {

		List<ShopDTO> selectShopList = ss.selectShopList();
		ObservableList<ShopDTO> shopList = FXCollections.observableArrayList(selectShopList);

		shopIdColumn.setCellValueFactory(new PropertyValueFactory<>("shopId"));
		shopNameColumn.setCellValueFactory(new PropertyValueFactory<>("shopName"));
		shopPriceColumn.setCellValueFactory(new PropertyValueFactory<>("shopPrice"));

		// 상품이름을 Hyperlink로 변환하는 커스텀 Cell 설정
		shopNameColumn.setCellFactory(new Callback<TableColumn<ShopDTO, String>, TableCell<ShopDTO, String>>() {
			public TableCell<ShopDTO, String> call(TableColumn<ShopDTO, String> param) {
				return new TableCell<>() {
					private final Hyperlink link = new Hyperlink();

					{
						link.setOnAction(event -> {
							ShopDTO shop = getTableView().getItems().get(getIndex());
							ss.shopDetailPage(root, shop, userDTO);
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

		shopListTable.setItems(shopList);
	}

	// 3.화면출력
	// 상품목록(회원로그인)
	public void selectShopListLogin() {

		List<ShopDTO> selectShopList = ss.selectShopList();
		ObservableList<ShopDTO> shopList = FXCollections.observableArrayList(selectShopList);

		shopIdColumn.setCellValueFactory(new PropertyValueFactory<>("shopId"));
		shopNameColumn.setCellValueFactory(new PropertyValueFactory<>("shopName"));
		shopPriceColumn.setCellValueFactory(new PropertyValueFactory<>("shopPrice"));

		// 상품이름을 Hyperlink로 변환하는 커스텀 Cell 설정
		shopNameColumn.setCellFactory(new Callback<TableColumn<ShopDTO, String>, TableCell<ShopDTO, String>>() {
			public TableCell<ShopDTO, String> call(TableColumn<ShopDTO, String> param) {
				return new TableCell<>() {
					private final Hyperlink link = new Hyperlink();

					{
						link.setOnAction(event -> {
							ShopDTO shop = getTableView().getItems().get(getIndex());
							ss.shopLoginDetailPage(root, shop, userDTO);
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

		shopListTable.setItems(shopList);
	}

	// 3.화면출력
	// 상품 결제페이지
	public void userShopPayPage() {
		System.out.println("결제페이지로 이동");

		// 상품 선택 갯수
		TextField textShopTotalShopCount = (TextField) root.lookup("#shopTotalShopCount");
		int shopTotalShopCount = Integer.parseInt(textShopTotalShopCount.getText());

		System.out.println("상품 선택 갯수 출력: " + shopTotalShopCount);

		shopDTO.setShopTotalShopCount(shopTotalShopCount);

		ss.userShopPayPage(root, userDTO, shopDTO);
	}

	// 3.화면출력
	// 상품 상세페이지 정보
	public void selectShopDtailInfo() {
		System.out.println("상품 상세페이지 정보");
		shopId.setText(String.valueOf(shopDTO.getShopId()));
		shopName.setText(shopDTO.getShopName());
		shopContents.setText(shopDTO.getShopContents());
		shopPrice.setText(String.valueOf(shopDTO.getShopPrice()));
	}

	// 3.화면출력
	// 상품 결제페이지 정보
	public void selectShopPayInfo() {

		// 이름, 설명, 가격, 갯수, 결제금액
		System.out.println("상품 결제페이지 정보");
		shopName.setText(shopDTO.getShopName());
		shopContents.setText(shopDTO.getShopContents());
		shopPrice.setText(String.valueOf(shopDTO.getShopPrice()));
		shopTotalShopCountText.setText(String.valueOf(shopDTO.getShopTotalShopCount()));

		// 총결제금액
		int sumPay = shopDTO.getShopPrice() * shopDTO.getShopTotalShopCount();
		shopDTO.setShopTotalPayment(sumPay);

		shopTotalPayment.setText(String.valueOf(shopDTO.getShopTotalPayment()));

		System.out.println("상품이름: " + shopName.getText());
		System.out.println("상품설명: " + shopContents.getText());
		System.out.println("상품가격: " + shopPrice.getText());
		System.out.println("상품갯수: " + shopTotalShopCountText.getText());
		System.out.println("상품결제금액: " + shopTotalPayment.getText());

	}
}
