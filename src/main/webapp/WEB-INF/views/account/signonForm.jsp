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
        <c:if test="${not empty param.error}">
            <div class="alert alert-error">
                <h4 class="alert-heading">Login error!</h4>
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </div>
        </c:if>
        <form:form action='${pageContext.request.contextPath}/account/signon'
                   method="POST">

            <p>Please enter your username and password.</p>
            <p>
                Username: <input type='text' name='username' value='j2ee'>
                <br/> Password: <input type='password' name='password'
                                       value="j2ee"/>
            </p>
            <input name="submit" type="submit" value="Sign in"/>
        </form:form>
        Need a user name and password? <a
            href="${pageContext.request.contextPath}/account/newAccountForm">Register
        Now!</a>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>