<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<div >
    <h1>BUG</h1>
    <form action="insert" method="post">
        所属项目：<select name="projectId">
        <c:forEach items="${listBug_por}" var="p">
            <option value="${p.id}">${p.name}</option>
        </c:forEach>
    </select>
        <br/>
        严重性：<select name="severity">
                        <option value="1">文字错误</option>
                        <option value="2">次要错误</option>
                        <option value="3">严重错误</option>
                </select>
        <br/>
        Bug标题：<input type="text" name="title"/>
        <br/>
        报告人：<input type="text" name="reportUser"/>
        <br/>
        <input type="submit" value="提交"/>
    </form>
</div>

</body>
</html>
