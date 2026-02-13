<div id="Footer">
    <div style="max-width: 1400px; margin: 0 auto; padding: 0 1rem;">
        <div id="PoweredBy">
            <i class="fas fa-code"></i> Powered by <a href="https://spring.io/projects/spring-boot">Spring Boot</a>
        </div>
        <div id="Banner">
            <sec:authorize access="isAuthenticated()">
                <sec:authentication property="principal.account"
                                    var="account"/>
                <c:if test="${account.bannerOption}">${account.bannerName}<!-- XSS --></c:if>
            </sec:authorize>
        </div>
        <div style="margin-top: 1rem; padding-top: 1rem; border-top: 1px solid rgba(255, 255, 255, 0.1); font-size: 0.75rem; color: rgba(255, 255, 255, 0.6);">
            &copy; 2025 Pet Store. All rights reserved.
        </div>
    </div>
</div>