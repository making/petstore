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
                <th>&nbsp;</th>
                <th>Product ID</th>
                <th>Name</th>
            </tr>
            <c:forEach var="product" items="${productList}">
                <tr>
                    <td><a
                            href="${pageContext.request.contextPath}/catalog/viewProduct?productId=${f:h(product.productId)}">${product.description}<%--  XSS Vulnerable! --%></a>
                    </td>
                    <td><b> <a
                            href="${pageContext.request.contextPath}/catalog/viewProduct?productId=${f:h(product.productId)}"><font
                            color="BLACK">
                            ${f:h(product.productId)} </font></a>
                    </b></td>
                    <td>${f:h(product.name)}</td>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>