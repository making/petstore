<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="https://example.com/functions" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/common/head.jsp"/>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div id="Content">
    <div id="Catalog">
        <form:form modelAttribute="accountForm"
                   action="${pageContext.request.contextPath}/account/new">

            <h3>User Information</h3>

            <table>
                <tr>
                    <td><form:label path="username">User ID:</form:label></td>
                    <td><form:input path="username"/> <form:errors
                            path="username"/></td>
                </tr>
                <tr>
                    <td><form:label path="password">New password:</form:label></td>
                    <td><form:password path="password"/> <form:errors
                            path="password"/></td>
                </tr>
                <tr>
                    <td><form:label path="repeatedPassword">Repeat password:</form:label></td>
                    <td><form:password path="repeatedPassword"/> <form:errors
                            path="repeatedPassword"/></td>
                </tr>
            </table>

            <%@ include file="accountFields.jsp" %>

            <input type="submit" name="newAccount"
                   value="Save Account Information"/>
        </form:form>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>