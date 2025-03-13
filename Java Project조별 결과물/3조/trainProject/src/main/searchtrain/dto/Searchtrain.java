package main.searchtrain.dto;

public class Searchtrain {
	private String tr_number;  //열차코드
	private String tr_name;  // 열차이름
	private String tr_local; // 출발지
	private String tr_leave; // 출발 시간
	private String tr_arrive; // 도착 시간
	private String tr_free; // 입석 및 자유석
	private String tr_local2; // 도착지
	private String tr_price; // 운임 요금
	private String tr_time; // 예상 소요 시간
	//private String tr_day; // 탑승일자


//	public Searchtrain(String tr_name, String tr_local, String tr_local2, String tr_leave, String tr_arrive, String tr_time, String tr_free, String tr_price ) {
//		// TODO Auto-generated constructor stub
//		this.tr_name = tr_name;
//		this.tr_local = tr_local;
//		this.tr_local2 = tr_local2;
//		this.tr_leave = tr_leave;
//		this.tr_arrive = tr_arrive;
//		this.tr_time = tr_time;
//		this.tr_free = tr_free;
//		this.tr_price = tr_price;
//	}

	public String getTr_number() {
		return tr_number;
	}
	public void setTr_number(String tr_number) {
		this.tr_number = tr_number;
	}
	public String getTr_name() {
		return tr_name;
	}
	public void setTr_name(String tr_name) {
		this.tr_name = tr_name;
	}
	public String getTr_local() {
		return tr_local;
	}
	public void setTr_local(String tr_local) {
		this.tr_local = tr_local;
	}
	public String getTr_leave() {
		return tr_leave;
	}
	public void setTr_leave(String tr_leave) {
		this.tr_leave = tr_leave;
	}
	public String getTr_arrive() {
		return tr_arrive;
	}
	public void setTr_arrive(String tr_arrive) {
		this.tr_arrive = tr_arrive;
	}
	public String getTr_free() {
		return tr_free;
	}
	public void setTr_free(String tr_free) {
		this.tr_free = tr_free;
	}
	public String getTr_local2() {
		return tr_local2;
	}
	public void setTr_local2(String tr_local2) {
		this.tr_local2 = tr_local2;
	}
	
	public void setTr_price(String tr_price) {
		this.tr_price = tr_price;
	}
	public String getTr_time() {
		return tr_time;
	}
	public void setTr_time(String tr_time) {
		this.tr_time = tr_time;
	}
//	public String getTr_day() {
//		return tr_day;
//	}
//	public void setTr_day(String tr_day) {
//		this.tr_day = tr_day;
//	}
	public String getTr_price() {
		return tr_price;
	}

}
