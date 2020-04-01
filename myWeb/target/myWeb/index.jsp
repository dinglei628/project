<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<body>
<h2>登录成功</h2>
<div>欢迎${login.userName}</div>
<a href="addto">销售</a>
<a href="list">查看全部销售信息</a>
<a href="count">查看库存</a>
<br/>
</body>
</html>
