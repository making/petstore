<div id="Header">
    <div id="Logo">
        <div id="LogoContent">
            <a href="${pageContext.request.contextPath}/catalog">
                <img
                        src="${pageContext.request.contextPath}/images/logo-topbar.gif"/>
            </a>
        </div>
    </div>

    <div id="Menu">
        <div id="MenuContent">
            <a
                    href="${pageContext.request.contextPath}/cart/viewCart">
                <img align="middle" name="img_cart"
                     src="${pageContext.request.contextPath}/images/cart.gif"/>
            </a> <img align="middle"
                      src="${pageContext.request.contextPath}/images/separator.gif"/>

            <sec:authorize access="!isAuthenticated()">
                <a
                        href="${pageContext.request.contextPath}/account/signonForm">
                    Sign In </a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <a
                        href="${pageContext.request.contextPath}/account/signoffForm">
                    Sign Out </a>
                <img align="middle"
                     src="${pageContext.request.contextPath}/images/separator.gif"/>
                <a
                        href="${pageContext.request.contextPath}/account/editAccountForm">
                    My Account </a>
            </sec:authorize>
        </div>
    </div>

    <div id="Search">
        <div id="SearchContent">
            <form
                    action="${pageContext.request.contextPath}/catalog"
                    method="get">
                <input type="text" name="keyword" size="14"/> <input
                    type="submit" name="searchProducts"
                    value="Search"/>
            </form>
        </div>
    </div>

    <div id="QuickLinks">
        <a
                href="${pageContext.request.contextPath}/catalog/viewCategory?categoryId=FISH"><img
                src="${pageContext.request.contextPath}/images/sm_fish.gif"/>
        </a> <img
            src="${pageContext.request.contextPath}/images/separator.gif"/>
        <a
                href="${pageContext.request.contextPath}/catalog/viewCategory?categoryId=DOGS"><img
                src="${pageContext.request.contextPath}/images/sm_dogs.gif"/>
        </a> <img
            src="${pageContext.request.contextPath}/images/separator.gif"/>
        <a
                href="${pageContext.request.contextPath}/catalog/viewCategory?categoryId=REPTILES"><img
                src="${pageContext.request.contextPath}/images/sm_reptiles.gif"/>
        </a> <img
            src="${pageContext.request.contextPath}/images/separator.gif"/>
        <a
                href="${pageContext.request.contextPath}/catalog/viewCategory?categoryId=CATS"><img
                src="${pageContext.request.contextPath}/images/sm_cats.gif"/>
        </a> <img
            src="${pageContext.request.contextPath}/images/separator.gif"/>
        <a
                href="${pageContext.request.contextPath}/catalog/viewCategory?categoryId=BIRDS"><img
                src="${pageContext.request.contextPath}/images/sm_birds.gif"/>
        </a>
    </div>

</div>