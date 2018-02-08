<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%!public String codeToChinese1(String str) {
		String s = str;
		try {
			byte[] temp = s.getBytes("iso-8859-1");
			s = new String(temp, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public String[] codeToChinese2(String[] str) {
		String[] s = str.clone();
		try {
			for (int i = 0; i < s.length; i++) {
				byte[] temp = s[i].getBytes("iso-8859-1");
				s[i] = new String(temp, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}%>



<title>新用户注册数据</title>
</head>
<body>
	这是新用户注册所提交的数据：
	<br> 用户名是：
	<%=codeToChinese1(request.getParameter("name"))%>
	<br>
	<%-- 密码是：<%=new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8")%><br> --%>
	性别是：<%=request.getParameter("gender")%><br> 电子邮箱是：<%=request.getParameter("email")%><br>
	爱好是：<%
		String[] str = codeToChinese2(request.getParameterValues("habbit"));
		if (str != null) {
			for (String i : str) {
				out.println(i);
			}
		}
	%>

	<br>

</body>
</html>