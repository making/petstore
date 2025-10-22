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
                    <th colspan=2>Payment Details</th>
                </tr>
                <tr>
                    <td><form:label path="cardType">Card Type:</form:label></td>
                    <td><form:select path="cardType"
                                     items="${creditCardTypes}">
                    </form:select></td>
                </tr>
                <tr>
                    <td><form:label path="creditCard">Card Number:</form:label></td>
                    <td><form:input path="creditCard"/> * Use a fake
                        number!
                    </td>
                </tr>
                <tr>
                    <td><form:label path="expiryDate">Expiry Date (MM/YYYY):</form:label></td>
                    <td><form:input path="expiryDate"/></td>
                </tr>
                <tr>
                    <th colspan=2>Billing Address</th>
                </tr>

                <tr>
                    <td><form:label path="billToFirstName">First name:</form:label></td>
                    <td><form:input path="billToFirstName"/></td>
                </tr>
                <tr>
                    <td><form:label path="billToLastName">Last name:</form:label></td>
                    <td><form:input path="billToLastName"/></td>
                </tr>
                <tr>
                    <td><form:label path="billAddress1">Address 1:</form:label></td>
                    <td><form:input size="40" path="billAddress1"/></td>
                </tr>
                <tr>
                    <td><form:label path="billAddress2">Address 2:</form:label></td>
                    <td><form:input size="40" path="billAddress2"/></td>
                </tr>
                <tr>
                    <td><form:label path="billCity">City:</form:label></td>
                    <td><form:input path="billCity"/></td>
                </tr>
                <tr>
                    <td><form:label path="billState">State:</form:label></td>
                    <td><form:input size="4" path="billState"/></td>
                </tr>
                <tr>
                    <td><form:label path="billZip">Zip:</form:label></td>
                    <td><form:input size="10" path="billZip"/></td>
                </tr>
                <tr>
                    <td><form:label path="billCountry">Country:</form:label></td>
                    <td><form:input size="15" path="billCountry"/></td>
                </tr>

                <tr>
                    <td colspan=2>
                        <form:checkbox path="shippingAddressRequired" id="shippingAddressRequired"/>
                        <form:label path="shippingAddressRequired">Ship to different address...</form:label>
                    </td>
                </tr>

            </table>
            <form:hidden path="shipToFirstName"/>
            <form:hidden path="shipToLastName"/>
            <form:hidden path="shipAddress1"/>
            <form:hidden path="shipAddress2"/>
            <form:hidden path="shipCity"/>
            <form:hidden path="shipState"/>
            <form:hidden path="shipZip"/>
            <form:hidden path="shipCountry"/>
            <input type="submit" name="newOrder" value="Continue"/>

        </form:form>
    </div>

</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>