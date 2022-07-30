package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// DAO(Data Access Object, 데이터 접근 객체)
// : 실질적으로 db에서 정보를 불러오거나 db에 정보를 넣고자 할 때 사용
// 데이터 사용기능 담당 클래스.
// DB 데이터 조회나 수정, 입력, 삭제와 같은 로직을 처리하기 위해 사용.(CRUD의 기능을 한다고 보면 됨)
public class UserDAO {

	// Connection : db에 접근하게 해주는 객체
	// PreparedStatement :
	// ResultSet : 정보를 담을 수 있는 객체
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// UserDAO객체 생성했을 때, 자동으로 db Connection이 이루어질 수 있도록함.
	public UserDAO() {
		try {
			// BBS라는 DB에 접속해라
			String dbURL = "jdbc:mysql://localhost:3306/BBS";
			String dbID = "root";
			String dbPassword = "root";
			// Driver : mysql에 접속할 수 있도록, 매개체 역할을 해주는 하나의 라이브러리
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ? ";
		try {
			pstmt = conn.prepareStatement(SQL); // 어떤 정해진 sql문장을 db에 삽입하는 형식으로 인스턴스를 가져옴
			// 매개변수로 넘어온 userID를 ?에 들어갈 수 있도록 해줌
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 결과가 존재하면(아이디가 있는 경우)
				if (rs.getString(1).equals(userPassword)) { // 결과로 나온 userPassword를 받아서, 접속을 시도한 userPassword와 동일하면
					return 1; // 로그인 성공 결과(1)를 반환
				} else
					return 0; // 비밀번호 불일치
			}
			return -1; // -1 : 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // db 오류
	}

	public int join(User user) {
		String SQL = "INSERT INTO USER VALUES(?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // db 오류
	}
}
