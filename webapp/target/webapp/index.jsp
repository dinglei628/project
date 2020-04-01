<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<body>
<div >
    <h1>BUG列表</h1>
    <a href="insert1"><button>新增BUG</button></a>
    <table border="1">
        <form action="index" method="post">
            <select name="projectId">
                <option value="0">请选择</option>
                <c:forEach items="${listBug_por}" var="p">
                    <c:if test="${projectId==p.id }">
                        <option value="${p.id}" selected>${p.name}</option>
                    </c:if>
                    <c:if test="${projectId!=p.id }">
                        <option value="${p.id}">${p.name}</option>
                    </c:if>
                </c:forEach>
            </select>
            <input type="submit" value="查询"/>
        </form>
        <tr>
            <td>BUG编号</td>
            <td>严重性</td>
            <td>标题</td>
            <td>报告人</td>
            <td>报告时间</td>
        </tr>
        <c:forEach items="${listBug}" var="b">
            <tr>
                <td>${b.id}</td>
                <td>
                    <c:if test="${b.severity == 1}">
                        文字错误
                    </c:if>
                    <c:if test="${b.severity == 2}">
                        次要错误
                    </c:if>
                    <c:if test="${b.severity == 3}">
                        严重错误
                    </c:if>
                </td>
                <td>${b.title}</td>
                <td>${b.reportUser}</td>
                <td><fmt:formatDate value="${b.createDate}" pattern="yyyy-MM-dd"/> </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
