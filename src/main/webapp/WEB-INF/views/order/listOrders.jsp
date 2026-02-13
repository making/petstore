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
    <%@ include file="/WEB-INF/views/common/backlink.jsp" %>
    <div id="Catalog">
        <h2>My Orders</h2>
        <table>
            <tr>
                <th>Order ID</th>
                <th>Date</th>
                <th>Total Price</th>
            </tr>

            <c:forEach var="order" items="${orderList}">
                <tr>
                    <td><a
                            href="${pageContext.request.contextPath}/order/orders/${f:h(order.orderId)}">${f:h(order.orderId)}</a>
                    </td>
                    <td>${f:h(order.orderDate.withNano(0))}</td>
                    <td><fmt:formatNumber value="${f:h(order.totalPrice)}"
                                          pattern="$#,##0.00"/></td>
                </tr>
            </c:forEach>
        </table>


    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>