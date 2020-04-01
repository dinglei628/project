<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<body>
<h2>Hello World!</h2>
<form action="add" method="post">
    <p>
        商品名称：<select name="productId">
                     <option>请选择</option>
                     <c:forEach items="${sel}" var="s">
                         <option value="${s.id}">${s.productName}</option>
                     </c:forEach>
                 </select>
    </p>
    <p>
        销售单价：<input type="text" name="price"/>
    </p>
    <p>
        销售数量：<input type="text" name="quantity"/>
    </p>
    <p>
        <input type="hidden" name="userId" value="${login.id}"/>
    </p>
    <p>
        <input type="submit" value="添加"/>
    </p>
</form>
</body>
</html>
