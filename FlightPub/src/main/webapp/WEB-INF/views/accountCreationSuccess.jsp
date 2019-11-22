<%--
  Created by IntelliJ IDEA.
  User: tschw
  Date: 14/08/2018
  Time: 8:32 PM
  To change this template use File | Settings | File Templates.
  Displayed when a user successfully registers for an account.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="template-parts/header.jsp" />

<div class="fix-annoying-submit-button">
    <h2>Your account was created successfully - welcome to Flightpub!</h2>
    <br><br>

    <form action = "/" method ="get">
        <input type = submit value = "Begin Searching">
    </form>
</div>
<jsp:include page="template-parts/footer.jsp" />
