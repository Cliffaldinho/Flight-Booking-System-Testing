<%--
  Created by IntelliJ IDEA.
  Date: 30/08/2018
  Time: 11:45 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="template-parts/header.jsp" />

<div class="fix-annoying-submit-button">
    <h2>Thank you for your feedback, your review has been successfully submitted!</h2>
    <br><br>

    <form action = "home" method ="get">
        <input type = submit value = "Back to user account page">
    </form>
</div>

<jsp:include page="template-parts/footer.jsp" />
