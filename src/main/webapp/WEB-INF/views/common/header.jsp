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
                    href="${pageContext.request.contextPath}/cart">
                <img align="middle" name="img_cart"
                     src="${pageContext.request.contextPath}/images/cart.gif"/>
            </a> <img align="middle"
                      src="${pageContext.request.contextPath}/images/separator.gif"/>

            <sec:authorize access="!isAuthenticated()">
                <a
                        href="${pageContext.request.contextPath}/account/signon?form">
                    Sign In </a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <a
                        href="${pageContext.request.contextPath}/account/signoff?form">
                    Sign Out </a>
                <img align="middle"
                     src="${pageContext.request.contextPath}/images/separator.gif"/>
                <a
                        href="${pageContext.request.contextPath}/account/edit?form">
                    My Account </a>
            </sec:authorize>
        </div>
    </div>

    <div id="Search">
        <div id="SearchContent">
            <form
                    action="${pageContext.request.contextPath}/catalog/products"
                    method="get">
                <input type="text" name="keyword" size="14"/> <input
                    type="submit" name="searchProducts"
                    value="Search"/>
            </form>
        </div>
    </div>

    <div id="QuickLinks">
        <a
                href="${pageContext.request.contextPath}/catalog/categories/FISH"><img
                src="${pageContext.request.contextPath}/images/sm_fish.gif"/>
        </a> <img
            src="${pageContext.request.contextPath}/images/separator.gif"/>
        <a
                href="${pageContext.request.contextPath}/catalog/categories/DOGS"><img
                src="${pageContext.request.contextPath}/images/sm_dogs.gif"/>
        </a> <img
            src="${pageContext.request.contextPath}/images/separator.gif"/>
        <a
                href="${pageContext.request.contextPath}/catalog/categories/REPTILES"><img
                src="${pageContext.request.contextPath}/images/sm_reptiles.gif"/>
        </a> <img
            src="${pageContext.request.contextPath}/images/separator.gif"/>
        <a
                href="${pageContext.request.contextPath}/catalog/categories/CATS"><img
                src="${pageContext.request.contextPath}/images/sm_cats.gif"/>
        </a> <img
            src="${pageContext.request.contextPath}/images/separator.gif"/>
        <a
                href="${pageContext.request.contextPath}/catalog/categories/BIRDS"><img
                src="${pageContext.request.contextPath}/images/sm_birds.gif"/>
        </a>
    </div>

</div>