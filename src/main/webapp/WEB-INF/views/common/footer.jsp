<div id="Footer">
    <div id="PoweredBy">
        &nbsp Powered by <a href="https://spring.io/projects/spring-boot">Spring Boot</a>
    </div>
    <div id="Banner">
        <sec:authorize access="isAuthenticated()">
            <sec:authentication property="principal.account"
                                var="account"/>
            <c:if test="${account.bannerOption}">${account.bannerName}<!-- XSS --></c:if>
        </sec:authorize>
    </div>
</div>