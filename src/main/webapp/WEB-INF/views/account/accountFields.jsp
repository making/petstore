<h3>Account Information</h3>

<table>
    <tr>
        <td><form:label path="firstName">First name:</form:label></td>
        <td><form:input path="firstName"/> <form:errors
                path="firstName"/></td>
    </tr>
    <tr>
        <td><form:label path="lastName">Last name:</form:label></td>
        <td><form:input path="lastName"/> <form:errors
                path="lastName"/></td>
    </tr>
    <tr>
        <td><form:label path="email">Email:</form:label></td>
        <td><form:input size="40" type="email" path="email"/> <form:errors
                path="email"/></td>
    </tr>
    <tr>
        <td><form:label path="phone">Phone:</form:label></td>
        <td><form:input path="phone"/> <form:errors path="phone"/></td>
    </tr>
    <tr>
        <td><form:label path="address1">Address 1:</form:label></td>
        <td><form:input size="40" path="address1"/> <form:errors
                path="address1"/></td>
    </tr>
    <tr>
        <td><form:label path="address2">Address 2:</form:label></td>
        <td><form:input size="40" path="address2"/> <form:errors
                path="address2"/></td>
    </tr>
    <tr>
        <td><form:label path="city">City:</form:label></td>
        <td><form:input path="city"/> <form:errors path="city"/></td>
    </tr>
    <tr>
        <td><form:label path="state">State:</form:label></td>
        <td><form:input size="4" path="state"/> <form:errors
                path="state"/></td>
    </tr>
    <tr>
        <td><form:label path="zip">Zip:</form:label></td>
        <td><form:input size="10" path="zip"/> <form:errors
                path="zip"/></td>
    </tr>
    <tr>
        <td><form:label path="country">Country:</form:label></td>
        <td><form:input size="15" path="country"/> <form:errors
                path="country"/></td>
    </tr>
</table>

<h3>Profile Information</h3>

<table>
    <tr>
        <td><form:label path="languagePreference">Language Preference:</form:label></td>
        <td><form:select path="languagePreference"
                         items="${languageList}"/> <form:errors
                path="languagePreference"/></td>
    </tr>
    <tr>
        <td><form:label path="favouriteCategoryId">Favourite Category:</form:label></td>
        <td><form:select path="favouriteCategoryId"
                         items="${categoryList}"/> <form:errors
                path="favouriteCategoryId"/></td>
    </tr>
    <tr>
        <td><form:checkbox path="listOption" id="listOption"/>
            <form:label path="listOption">Enable MyList</form:label></td>
        <td><form:errors path="listOption"/></td>
    </tr>
    <tr>
        <td><form:checkbox path="bannerOption" id="bannerOption"/>
            <form:label path="bannerOption">Enable MyBanner</form:label></td>
        <td><form:errors path="bannerOption"/></td>
    </tr>
</table>
