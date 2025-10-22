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

        <table>
            <tr>
                <td>${product.description}<%--  XSS Vulnerable! --%></td>
            </tr>
            <tr>
                <td><b> ${f:h(item.itemId)} </b></td>
            </tr>
            <tr>
                <td><b><font size="4">
                    ${f:h(item.attribute1)} ${f:h(item.attribute2)}
                    ${f:h(item.attribute3)} ${f:h(item.attribute4)}
                    ${f:h(item.attribute5)} ${f:h(product.name)} </font></b></td>
            </tr>
            <tr>
                <td>${f:h(product.name)}</td>
            </tr>
            <tr>
                <td><c:if test="${item.quantity <= 0}">
                    Back ordered.
                </c:if> <c:if test="${item.quantity > 0}">
                    ${f:h(item.quantity)} in stock.
                </c:if></td>
            </tr>
            <tr>
                <td><fmt:formatNumber value="${f:h(item.listPrice)}"
                                      pattern="$#,##0.00"/></td>
            </tr>

            <tr>
                <td><a
                        href="${pageContext.request.contextPath}/cart/addItemToCart?workingItemId=${f:h(item.itemId)}">
                    Add to Cart</a></td>
            </tr>
        </table>

    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>