<sec:authorize access="isAuthenticated()">
    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 0.5rem;">
        <h3 style="margin: 0;"><i class="fas fa-heart"></i> Pet Favorites</h3>
        <button onclick="toggleMyList()" style="background: none; border: none; cursor: pointer; color: var(--text-secondary); padding: 0.25rem;" title="Close">
            <i class="fas fa-times" style="font-size: 1rem;"></i>
        </button>
    </div>
    <div id="myListContent">
        <p>Shop for more of your favorite pets here.</p>
        <ul>
            <sec:authentication property="principal.myList" var="products"/>
            <c:forEach var="product" items="${products}">
                <li>
                    <a href="${pageContext.request.contextPath}/catalog/products/${f:h(product.productId)}">
                        <i class="fas fa-paw"></i> ${f:h(product.name)}
                    </a>
                    <span style="color: var(--text-secondary); font-size: 0.875rem; margin-left: 0.5rem;">(${f:h(product.productId)})</span>
                </li>
            </c:forEach>
        </ul>
    </div>
    <script>
        function toggleMyList() {
            var myList = document.getElementById('MyList');
            var content = document.getElementById('myListContent');
            var cart = document.getElementById('Cart');

            if (content.style.display === 'none') {
                content.style.display = 'block';
                myList.style.width = '30%';
                cart.style.width = '68%';
            } else {
                content.style.display = 'none';
                myList.style.width = 'auto';
                myList.style.padding = '1rem';
                cart.style.width = '100%';
            }
        }
    </script>
</sec:authorize>