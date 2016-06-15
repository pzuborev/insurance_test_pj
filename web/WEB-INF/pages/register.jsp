<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<html>
<head>
    <title>Registration page</title>

    <h3>Enter your login and password</h3>
    <form action="/createuser" method="post">
        <table>
            <tr>
                <td>
                    <label id="user-name-label">User:</label>
                </td>
                <td>
                    <input id="username" type="text" name="username"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label id="password-label">Password:</label>
                </td>
                <td>
                    <input id="password" type="password" name="password"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label id="role-label">Role:</label>
                </td>
                <td>

                    <select name="userRoleName">
                        <option value="ADMIN">Admin</option>
                        <option value="USER">User</option>
                    </select>

                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button id="register" onclick="submit">Register</button>
                </td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>


    </form>
</head>
<body>

</body>
</html>
