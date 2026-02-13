<div id="Header">
    <div id="Logo">
        <div id="LogoContent">
            <a href="${pageContext.request.contextPath}/catalog" style="text-decoration: none; color: var(--primary-color); font-size: 1.5rem; font-weight: 700;">
                <i class="fas fa-paw"></i> Pet Store
            </a>
        </div>
    </div>

    <div id="Menu">
        <div id="MenuContent">
            <a href="${pageContext.request.contextPath}/cart">
                <i class="fas fa-shopping-cart"></i> Cart
            </a>

            <sec:authorize access="!isAuthenticated()">
                <a href="${pageContext.request.contextPath}/account/signon?form"><i class="fas fa-sign-in-alt"></i> Sign In</a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <a href="${pageContext.request.contextPath}/account/edit?form"><i class="fas fa-user"></i> My Account</a>
                <a href="${pageContext.request.contextPath}/account/signoff?form"><i class="fas fa-sign-out-alt"></i> Sign Out</a>
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
        <a href="${pageContext.request.contextPath}/catalog/categories/FISH">
            <i class="fas fa-fish"></i> Fish
        </a>
        <a href="${pageContext.request.contextPath}/catalog/categories/DOGS">
            <i class="fas fa-dog"></i> Dogs
        </a>
        <a href="${pageContext.request.contextPath}/catalog/categories/REPTILES">
            <i class="fas fa-dragon"></i> Reptiles
        </a>
        <a href="${pageContext.request.contextPath}/catalog/categories/CATS">
            <i class="fas fa-cat"></i> Cats
        </a>
        <a href="${pageContext.request.contextPath}/catalog/categories/BIRDS">
            <i class="fas fa-dove"></i> Birds
        </a>
    </div>

</div>