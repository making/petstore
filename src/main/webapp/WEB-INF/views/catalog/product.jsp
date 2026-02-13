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

        <h2>${f:h(product.name)}</h2>

        <table class="product-items-table">
            <tr>
                <th>Item ID</th>
                <th>Product ID</th>
                <th>Description</th>
                <th>List Price</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach var="item" items="${itemList}">
                <tr>
                    <td class="item-id-cell">
                        <a href="${pageContext.request.contextPath}/catalog/items/${f:h(item.itemId)}">
                            ${f:h(item.itemId)}
                        </a>
                    </td>
                    <td class="product-id-cell">${f:h(item.product.productId)}</td>
                    <td class="description-cell">
                        ${f:h(item.attribute1)} ${f:h(item.attribute2)}
                        ${f:h(item.attribute3)} ${f:h(item.attribute4)}
                        ${f:h(item.attribute5)} ${f:h(product.name)}
                    </td>
                    <td class="price-cell">
                        <fmt:formatNumber value="${f:h(item.listPrice)}" pattern="$#,##0.00"/>
                    </td>
                    <td class="action-cell">
                        <a class="add-to-cart-btn" href="${pageContext.request.contextPath}/cart?add&workingItemId=${f:h(item.itemId)}">
                            <i class="fas fa-cart-plus"></i> Add to Cart
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>