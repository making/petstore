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
        <div id="Cart">
            <spring:eval var="cart" expression="@cart"/>
            <h2>Shopping Cart</h2>
            <form:form modelAttribute="cartForm"
                       action="${pageContext.request.contextPath}/cart?update">
                <table>
                    <tr>
                        <th><b>Item ID</b></th>
                        <th><b>Product ID</b></th>
                        <th><b>Description</b></th>
                        <th><b>In Stock?</b></th>
                        <th><b>Quantity</b></th>
                        <th><b>List Price</b></th>
                        <th><b>Total Cost</b></th>
                        <th>&nbsp;</th>
                    </tr>

                    <c:if test="${cart.numberOfItems == 0}">
                        <tr>
                            <td colspan="8"><b>Your cart is empty.</b></td>
                        </tr>
                    </c:if>

                    <c:forEach var="cartItem" items="${cart.cartItems}">
                        <tr>
                            <td><a
                                    href="${pageContext.request.contextPath}/catalog/items/${f:h(cartItem.item.itemId)}">
                                    ${f:h(cartItem.item.itemId)}</a></td>
                            <td>${f:h(cartItem.item.product.productId)}</td>
                            <td>${f:h(cartItem.item.attribute1)}
                                    ${f:h(cartItem.item.attribute2)}
                                    ${f:h(cartItem.item.attribute3)}
                                    ${f:h(cartItem.item.attribute4)}
                                    ${f:h(cartItem.item.attribute5)}
                                    ${f:h(cartItem.item.product.name)}</td>
                            <td>${f:h(cartItem.inStock)}</td>
                            <td><form:label path="quantity[${f:h(cartItem.item.itemId)}]"><form:input
                                    type="number"
                                    min="0"
                                    path="quantity[${f:h(cartItem.item.itemId)}]"
                                    value="${f:h(cartItem.quantity)}"/></form:label></td>
                            <td><fmt:formatNumber
                                    value="${f:h(cartItem.item.listPrice)}"
                                    pattern="$#,##0.00"/></td>
                            <td><fmt:formatNumber
                                    value="${f:h(cartItem.total)}"
                                    pattern="$#,##0.00"/></td>
                            <td><a
                                    href="${pageContext.request.contextPath}/cart?remove&cartItem=${f:h(cartItem.item.itemId)}">Remove</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr class="cart-total-row">
                        <td colspan="6" style="text-align: right; font-weight: 600; font-size: 1.125rem;">
                            Total:
                        </td>
                        <td class="cart-total-amount">
                            <fmt:formatNumber value="${cart.subTotal}" pattern="$#,##0.00"/>
                        </td>
                        <td>
                            <input type="submit" value="Update Cart"/>
                        </td>
                    </tr>
                </table>
            </form:form>
            <c:if test="${cart.numberOfItems > 0 && cart.allInStock}">
                <div class="checkout-section">
                    <a class="checkout-btn" href="${pageContext.request.contextPath}/order/new?form">
                        <i class="fas fa-credit-card"></i> Proceed to Checkout
                    </a>
                </div>
            </c:if>
        </div>

        <sec:authorize access="isAuthenticated()">
            <sec:authentication property="principal.account" var="account"/>
            <div id="MyList">
                <c:if
                        test="${account.listOption}">
                    <%@ include file="mylist.jsp" %>
                </c:if>
            </div>
        </sec:authorize>

        <div id="Separator">&nbsp;</div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>