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
import javafx.scene.control.cell.PropertyValueFactory;
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

	// 쇼핑몰 리스트
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

	// 메인페이지 출력(비로그인)
	public void mainMenu() {
		System.out.println("메인페이지로 이동");
		ms.mainMenu(root, userDTO);
	}

}
