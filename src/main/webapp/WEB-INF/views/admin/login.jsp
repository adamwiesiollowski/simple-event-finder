<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Logowanie</title>
</head>
<body>

<h1><a href="/">Simple Event Finder</a></h1>

<form method="post">
    <div><label> Nazwa użytkownika:<input type="text" name="username"/> </label></div>
    <div><label> Hasło: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Zaloguj się"/></div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

</body>
</html>