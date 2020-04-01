<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<body>
<h2>Hello World!</h2>
<form action="count" method="post">
        商品名称：<select name="productName">
                     <c:forEach items="${sel}" var="s">
                         <option value="${s.id}">${s.productName}</option>
                     </c:forEach>
                 </select>
        <input type="submit" value="查询"/>
</form>
<h1>该商品的库存数量是:&nbsp;&nbsp;${one.quantity}</h1>
</body>
</html>
