<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="user.UserDAO"%>
<%@ page import="java.io.PrintWriter"%>
<!-- 자바스크립트 문장을 사용하기 위해 -->
<%
request.setCharacterEncoding("UTF-8");
%>
<!-- 건너오는 모든 데이터를 UTF-8로 받을 수 있도록 -->
<!-- java beans(UserDAO)를 사용한다는 것을 선언 -->
<!-- scope="page" : 현재의 페이지 안에서만 beans가 사용됨 -->
<jsp:useBean id="user" class="user.User" scope="page" />
<jsp:setProperty name="user" property="userID" />
<jsp:setProperty name="user" property="userPassword" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
	String userID = null;
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	if (userID != null) { // 이미 로그인이 된 사람은 또다시 로그인 할 수 없도록 막아줌
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 로그인이 되어있습니다.')");
		script.println("location.href = 'main.jsp'"); // 이전 페이지로 사용자를 돌려보냄
		script.println("</script>");
	}

	/* userDAO 인스턴스 생성 */
	UserDAO userDAO = new UserDAO();
	int result = userDAO.login(user.getUserID(), user.getUserPassword());
	if (result == 1) {
		// 로그인에 성공한 회원에게 세션을 부여
		session.setAttribute("userID", user.getUserID());
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href = 'main.jsp'");
		script.println("</script>");
	} else if (result == 0) { // 비밀번호가 틀렸을 경우
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('비밀번호가 틀렸습니다.')");
		script.println("history.back()"); // 이전 페이지로 사용자를 돌려보냄
		script.println("</script>");
	} else if (result == -1) { // 아이디가 존재하지 않을 때
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('존재하지 않는 아이디입니다.')");
		script.println("history.back()"); // 이전 페이지로 사용자를 돌려보냄
		script.println("</script>");
	} else if (result == -2) { // DB 오류가 발생했을 때
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('데이터베이스 오류가 발생했습니다.')");
		script.println("history.back()"); // 이전 페이지로 사용자를 돌려보냄
		script.println("</script>");
	}
	%>
</body>
</html>