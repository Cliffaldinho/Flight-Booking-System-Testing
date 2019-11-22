<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="template-parts/header.jsp" />

<div class="fix-annoying-submit-button">
    <h2>Congratulations, payment was successful!</h2>
    <h3>A copy of your ticket has been sent to your email.</h3>
    <br><br>

    <form action = "/" method ="get">
        <input type = submit value = "Search Again">
    </form>
</div>

<jsp:include page="template-parts/footer.jsp" />