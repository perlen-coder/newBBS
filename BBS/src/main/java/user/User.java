package user;

// 회원 데이터베이스 구축
// mysql db로 만든 테이블과 동일하게 설정

// 한 명의 회원 데이터를 다룰 수 있는 DB : USER 테이블
public class User {
	private String userID;
	private String userPassword;
	private String userName;
	private String userGender;
	private String userEmail;
	
	// java beans: 하나의 데이터를 관리하고, 처리할 수 있는 기법을 JSP에서 구현하는 것
	// java beans 생성
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
}
