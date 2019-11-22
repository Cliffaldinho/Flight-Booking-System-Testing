<%--
  Created by IntelliJ IDEA.
  User: tschw
  Date: 21/08/2018
  Time: 9:41 PM
  Displays when a user attempts to access an account page without being logged in
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="template-parts/header.jsp" />

<div>
    <h2>Sorry, you need to be logged in to access that page</h2>
    <br><br>
    <h3>Log In to Flightpub</h3>
    <p>${loginErrorMessage}</p>
    <form method="post" action="login">
        <div class="input-group">
            <span class="input-group-addon"><i class="fa fa-user fa-lg" aria-hidden="true"></i></span>
            <input type="text" name="username" placeholder="Username"/>
        </div>
        <div class="input-group">
            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"  style="margin-right: 7px;"></i></span>
            <input type="password" name="password" placeholder="Password"/>
        </div>
        <input type="submit" />
        <!--
        Username: <input type="text" name="username" placeholder="Username"/>
        <p/>
        Password: <input type="password" name="password" placeholder="Password"/>
        <p/>
        <input type="submit" /-->
    </form>

    <h5>Don't have an account yet? <a href = "register">Register here</a></h5>

</div>


<jsp:include page="template-parts/footer.jsp" />
