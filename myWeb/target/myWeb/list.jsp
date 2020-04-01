<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<body>
<h2>Hello World!</h2>
<form action="list" method="post">
    <select name = "a">
        <option value="0">请选择</option>
        <option value="1">销售时间</option>
        <option value="2">销售总价</option>
    </select>
    <input type="submit" value="查询"/>
</form>
<table border="1">
    <tr>
        <td>ID</td>
        <td>商品</td>
        <td>单价</td>
        <td>数量</td>
        <td>总价</td>
        <td>销售日期</td>
        <td>销售人</td>
    </tr>
    <c:forEach items="${page.list}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.productName}</td>
            <td>${p.price}</td>
            <td>${p.quantity}</td>
            <td>${p.price * p.quantity}</td>
            <td><fmt:formatDate value="${p.saleDate}" pattern="yyyy-MM-dd" /></td>
            <td>${login.realName}</td>
        </tr>
    </c:forEach>
</table>
<a href="list?pageIndex=1">首页</a>
<c:if test="${not page.hasUp }">
   <span>上一页</span>
</c:if>
<c:if test="${page.hasUp }">
    <a href="list?pageIndex=${page.pageIndex-1 }">上一页</a>
</c:if>
<c:if test="${not page.hasNext }">
    <span>下一页</span>
</c:if>
<c:if test="${page.hasNext }">
    <a href="list?pageIndex=${page.pageIndex + 1}">下一页</a>
</c:if>
<a href="list?pageIndex=${page.totalPageCount}">尾页</a>
</body>
</html>
