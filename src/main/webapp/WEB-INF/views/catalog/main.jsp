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
    <div id="Welcome">
        <div id="WelcomeContent">
            <sec:authorize access="isAuthenticated()">
                <sec:authentication property="principal.account" var="account"/>
                Welcome ${f:h(account.firstName)}!
            </sec:authorize>
        </div>
    </div>
    <div id="Main">
        <div id="Sidebar">
            <div id="SidebarContent">
                <a
                        href="${pageContext.request.contextPath}/catalog/categories/FISH"><img
                        src="${pageContext.request.contextPath}/images/fish_icon.gif"/>
                </a> <br/> Saltwater, Freshwater <br/> <a
                    href="${pageContext.request.contextPath}/catalog/categories/DOGS"><img
                    src="${pageContext.request.contextPath}/images/dogs_icon.gif"/>
            </a> <br/> Various Breeds <br/> <a
                    href="${pageContext.request.contextPath}/catalog/categories/CATS"><img
                    src="${pageContext.request.contextPath}/images/cats_icon.gif"/>
            </a> <br/> Various Breeds, Exotic Varieties <br/> <a
                    href="${pageContext.request.contextPath}/catalog/categories/REPTILES"><img
                    src="${pageContext.request.contextPath}/images/reptiles_icon.gif"/>
            </a> <br/> Lizards, Turtles, Snakes <br/> <a
                    href="${pageContext.request.contextPath}/catalog/categories/BIRDS"><img
                    src="${pageContext.request.contextPath}/images/birds_icon.gif"/>
            </a> <br/> Exotic Varieties
            </div>
        </div>

        <div id="MainImage">
            <div id="MainImageContent">
                <map name="estoremap">
                    <area alt="Birds" coords="72,2,280,250"
                          href="${pageContext.request.contextPath}/catalog/categories/BIRDS"
                          shape="RECT"/>
                    <area alt="Fish" coords="2,180,72,250"
                          href="${pageContext.request.contextPath}/catalog/categories/FISH"
                          shape="RECT"/>
                    <area alt="Dogs" coords="60,250,130,320"
                          href="${pageContext.request.contextPath}/catalog/categories/DOGS"
                          shape="RECT"/>
                    <area alt="Reptiles" coords="140,270,210,340"
                          href="${pageContext.request.contextPath}/catalog/categories/REPTILES"
                          shape="RECT"/>
                    <area alt="Cats" coords="225,240,295,310"
                          href="${pageContext.request.contextPath}/catalog/categories/CATS"
                          shape="RECT"/>
                    <area alt="Birds" coords="280,180,350,250"
                          href="${pageContext.request.contextPath}/catalog/categories/BIRDS"
                          shape="RECT"/>
                </map>
                <img height="355"
                     src="${pageContext.request.contextPath}/images/splash.gif"
                     align="middle" usemap="#estoremap" width="350"/>
            </div>
        </div>

        <div id="Separator">&nbsp;</div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>