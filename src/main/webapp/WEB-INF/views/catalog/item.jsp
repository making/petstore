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

        <div class="item-detail">
            <div class="item-image">
                ${product.description}<%--  XSS Vulnerable! --%>
            </div>
            <div class="item-info">
                <div class="item-id">
                    <span class="label">Item ID:</span> ${f:h(item.itemId)}
                </div>
                <h2 class="item-title">
                    ${f:h(item.attribute1)} ${f:h(item.attribute2)}
                    ${f:h(item.attribute3)} ${f:h(item.attribute4)}
                    ${f:h(item.attribute5)} ${f:h(product.name)}
                </h2>
                <div class="item-product-name">
                    ${f:h(product.name)}
                </div>
                <div class="item-stock">
                    <c:if test="${item.quantity <= 0}">
                        <span class="out-of-stock"><i class="fas fa-times-circle"></i> Back ordered</span>
                    </c:if>
                    <c:if test="${item.quantity > 0}">
                        <span class="in-stock"><i class="fas fa-check-circle"></i> ${f:h(item.quantity)} in stock</span>
                    </c:if>
                </div>
                <div class="item-price">
                    <fmt:formatNumber value="${f:h(item.listPrice)}" pattern="$#,##0.00"/>
                </div>
                <div class="item-action">
                    <a class="add-to-cart-btn" href="${pageContext.request.contextPath}/cart?add&workingItemId=${f:h(item.itemId)}">
                        <i class="fas fa-cart-plus"></i> Add to Cart
                    </a>
                </div>
            </div>
        </div>

    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>