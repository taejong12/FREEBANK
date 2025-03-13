package main.common.service;

public class UserSession {
    private static volatile UserSession instance; // 멀티스레드 환경 대비
    private String userName;
    private String userId;
    private String userAddress;
    private String userPhone;
    private static String selectedTrainNumber;         // 열차번호
    private static String selectedTrainName;           // 열차이름
    private static String selectedTrainDeparture;      // 출발지
    private static String selectedTrainDestination;    // 도착지
    private static String selectedTrainDepartureTime;  // 출발시간
    private static String selectedTrainArrivalTime;    // 도착시간
    private static String selectedTrainDuration;       // 소요시간
    private static String selectedTrainSeat;           // 좌석 
    private static String selectedTrainFare;           // 요금
    private String seat;

    public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	// private 생성자 (외부에서 직접 생성 불가)
    private UserSession() {}

    // 싱글톤 인스턴스 가져오기 (멀티스레드 안전)
    public static UserSession getInstance() {
        if (instance == null) { 
            synchronized (UserSession.class) { 
                if (instance == null) {
                    instance = new UserSession();
                }
            }
        }
        return instance;
    }
    
    public static void setSelectedData
    	(String trainNumber, String trianName, String trainDeparture, String trainDestination, String trainDepartureTime, String trainArrivalTime, String trainDuration, String trainSeat, String trainFare) {
    	selectedTrainNumber = trainNumber;
    	selectedTrainName = trianName;
    	selectedTrainDeparture = trainDeparture;
    	selectedTrainDestination = trainDestination;
    	selectedTrainDepartureTime = trainDepartureTime;
    	selectedTrainArrivalTime = trainArrivalTime;
    	selectedTrainDuration = trainDuration;
    	selectedTrainSeat = trainSeat;
    	selectedTrainFare = trainFare;
    }
    
    
    public String getSelectedTrainNumber() {return selectedTrainNumber;}
	public String getSelectedTrainName() { return selectedTrainName; }
	public String getSelectedTrainDeparture() { return selectedTrainDeparture; }
	public String getSelectedTrainDestination() { return selectedTrainDestination; }
	public String getSelectedTrainDepartureTime() { return selectedTrainDepartureTime; }
	public String getSelectedTrainArrivalTime() { return selectedTrainArrivalTime; }
	public String getSelectedTrainDuration() { return selectedTrainDuration; }
	public String getSelectedTrainSeat() { return selectedTrainSeat; }
	public String getSelectedTrainFare() { return selectedTrainFare; }


	// 사용자 정보 저장 및 가져오기
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUserAddress() { return userAddress; }
    public void setUserAddress(String userAddress) { this.userAddress = userAddress; }

    public String getUserPhone() { return userPhone; }
    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }
    
   

    // 로그아웃 시 세션 초기화 (인스턴스 유지)
    public void logout() {
        this.userName = null;
        this.userId = null;
        this.userAddress = null;
        this.userPhone = null;
        selectedTrainNumber = null;
        selectedTrainName = null;
        selectedTrainDeparture = null;
        selectedTrainDestination = null;
        selectedTrainDepartureTime = null;
        selectedTrainArrivalTime = null;
        selectedTrainDuration = null;
        selectedTrainSeat = null;
        selectedTrainFare = null;
        this.seat = null;
    }
}
