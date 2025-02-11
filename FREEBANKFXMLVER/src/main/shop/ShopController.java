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

public class ShopController {

	Parent root;
	UserDTO userDTO;
	MenuService ms = new MenuServiceImple();
	ShopService ss = new ShopServiceImpl();
	ShopDTO shopDTO;

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

	public void showUserInfo() {
		if (userDTO != null) {
			System.out.println("현재 로그인한 사용자: " + userDTO.getUserId());
		} else {
			System.out.println("로그인 정보 없음");
		}
	}

	public void showShopInfo() {
		if (shopDTO != null) {
			System.out.println("상품아이디: " + shopDTO.getShopId());
			System.out.println("상품이름: " + shopDTO.getShopName());
			System.out.println("상품설명: " + shopDTO.getShopContents());
			System.out.println("상품가격: " + shopDTO.getShopPrice());
			System.out.println("상품회원아이디: " + shopDTO.getShopUserId());
		} else {
			System.out.println("로그인 정보 없음");
		}
	}

	public void setShop(ShopDTO shop) {
		this.shopDTO = shop;
	}

	// 상품 리스트
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

	// 메인페이지 출력(비로그인) 로그아웃
	public void mainMenu() {
		System.out.println("메인페이지로 이동");
		ms.mainMenu(root, userDTO);
	}

	// 상품 결제페이지 출력
	public void userShopPayPage() {
		System.out.println("결제페이지로 이동");

		// 상품 선택 갯수
		TextField textShopTotalShopCount = (TextField) root.lookup("#shopTotalShopCount");
		int shopTotalShopCount = Integer.parseInt(textShopTotalShopCount.getText());

		System.out.println("상품 선택 갯수 출력: " + shopTotalShopCount);

		shopDTO.setShopTotalShopCount(shopTotalShopCount);

		ss.userShopPayPage(root, userDTO, shopDTO);
	}

	// 상품 결제하기
	public void userShopPayment() {
		System.out.println("상품 결제하기");
		boolean result = ss.userShopPayment(root, shopDTO, userDTO);
		if(!result) {
			shopLoginListPage();
		}
	}

	// 상품 상세페이지 정보
	public void selectShopDtailInfo() {
		System.out.println("상품 상세페이지 정보");
		shopId.setText(String.valueOf(shopDTO.getShopId()));
		shopName.setText(shopDTO.getShopName());
		shopContents.setText(shopDTO.getShopContents());
		shopPrice.setText(String.valueOf(shopDTO.getShopPrice()));
	}

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
	
	// 상품 목록 페이지 이동(비로그인)
	public void shopListPage() {
		System.out.println("상품 목록 페이지로 이동");
		ss.shopListPage(root, userDTO);
	}

	// 회원메뉴페이지
	public void loginMainMenu() {
		System.out.println("일반회원메뉴페이지로 이동");
		ms.loginMainMenu(root, userDTO);
	}
	
	// 로그인 상품리스트 페이지
	public void shopLoginListPage() {
		
	}
}
