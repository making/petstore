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
        <form:form modelAttribute="orderForm"
                   action="${pageContext.request.contextPath}/order/new">

            <table>
                <tr>
                    <th colspan=2>Shipping Address</th>
                </tr>

                <tr>
                    <td><form:label path="shipToFirstName">First name:</form:label></td>
                    <td><form:input path="shipToFirstName"/></td>
                </tr>
                <tr>
                    <td><form:label path="shipToLastName">Last name:</form:label></td>
                    <td><form:input path="shipToLastName"/></td>
                </tr>
                <tr>
                    <td><form:label path="shipAddress1">Address 1:</form:label></td>
                    <td><form:input size="40" path="shipAddress1"/></td>
                </tr>
                <tr>
                    <td><form:label path="shipAddress2">Address 2:</form:label></td>
                    <td><form:input size="40" path="shipAddress2"/></td>
                </tr>
                <tr>
                    <td><form:label path="shipCity">City:</form:label></td>
                    <td><form:input path="shipCity"/></td>
                </tr>
                <tr>
                    <td><form:label path="shipState">State:</form:label></td>
                    <td><form:input size="4" path="shipState"/></td>
                </tr>
                <tr>
                    <td><form:label path="shipZip">Zip:</form:label></td>
                    <td><form:input size="10" path="shipZip"/></td>
                </tr>
                <tr>
                    <td><form:label path="shipCountry">Country:</form:label></td>
                    <td><form:input size="15" path="shipCountry"/></td>
                </tr>
            </table>
            <form:hidden path="cardType"/>
            <form:hidden path="creditCard"/>
            <form:hidden path="expiryDate"/>
            <form:hidden path="billToFirstName"/>
            <form:hidden path="billToLastName"/>
            <form:hidden path="billAddress1"/>
            <form:hidden path="billAddress2"/>
            <form:hidden path="billCity"/>
            <form:hidden path="billState"/>
            <form:hidden path="billZip"/>
            <form:hidden path="billCountry"/>
            <input type="submit" name="newOrder" value="Continue"/>
        </form:form>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>