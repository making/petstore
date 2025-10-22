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
        <div style="max-width: 500px; margin: 0 auto;">
            <c:if test="${not empty param.error}">
                <div class="alert alert-error">
                    <h4 class="alert-heading"><i class="fas fa-exclamation-circle"></i>Login error!</h4>
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                </div>
            </c:if>
            <form:form action='${pageContext.request.contextPath}/account/signon'
                       method="POST">

                <p>Please enter your username and password.</p>
                <div style="text-align: left;">
                    <div style="margin-bottom: 1rem;">
                        <label for="username" style="display: block; margin-bottom: 0.5rem; font-weight: 500;">Username:</label>
                        <input type='text' name='username' id='username' value='j2ee' style="width: 100%;">
                    </div>
                    <div style="margin-bottom: 1.5rem;">
                        <label for="password" style="display: block; margin-bottom: 0.5rem; font-weight: 500;">Password:</label>
                        <input type='password' name='password' id='password' value="j2ee" style="width: 100%;"/>
                    </div>
                    <input name="submit" type="submit" value="Sign in" style="width: 100%;"/>
                </div>
            </form:form>
            <div style="margin-top: 1rem;">
                Need a user name and password? <a
                    href="${pageContext.request.contextPath}/account/new?form">Register
                Now!</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>