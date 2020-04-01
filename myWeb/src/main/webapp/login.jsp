<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
<form action="doLogin" method="post">
    <div class="info" style="color:red;">${info}</div>
    <input type="text" name="userName"/>
    <br/>
    <input type="password" name="userPwd"/>
    <br/>
    <input type="submit" value="登录"/>
</form>
</body>
</html>
