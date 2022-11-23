<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@page import="Dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

 <jsp:useBean id="student"
  class="bean.*" />

 <jsp:setProperty property="*" name="student" />

 <%
  StudentDao studentDao = new StudentDao();
  int status = studentDao.StudentRegistartion(student);
  if (status > 0) {
   System.out.print("You are successfully registered");
  }
 %>
</body>
</html>