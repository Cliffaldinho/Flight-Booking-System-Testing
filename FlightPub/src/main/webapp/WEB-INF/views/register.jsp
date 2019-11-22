<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="template-parts/header.jsp" />

<div class="container">
    <h1>Register user</h1>
    <div class="rowx mainx">
        <div class="main-login main-center">
            <form:form class="form-horizontal" method="post" action="create-account">

                <p class = "error-message">${registerErrorMessage}</p>
                <div class="form-group">
                    <label for="username" class="cols-sm-2 control-label">Username</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="username" id="username"  placeholder="Enter your Username"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="cols-sm-2 control-label">Password</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                            <input type="password" class="form-control" name="password" id="password"  placeholder="Enter your Password"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="confirm" class="cols-sm-2 control-label">Confirm Password</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                            <input type="password" class="form-control" name="confirm" id="confirm"  placeholder="Confirm your Password"/>
                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <label for="firstname" class="cols-sm-2 control-label">Your First Name</label>
                    <div class="cols-sm-12">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="firstname" id="firstname"  placeholder="Enter your First Name"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="middlename" class="cols-sm-2 control-label">Your Middle Name</label>
                    <div class="cols-sm-12">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="middlename" id="middlename"  placeholder="Enter your Middle Name"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="lastname" class="cols-sm-2 control-label">Your Last Name</label>
                    <div class="cols-sm-12">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="lastname" id="lastname"  placeholder="Enter your Middle Name"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="dob" class="cols-sm-2 control-label">Your Date of Birth</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <input type="date" class="form-control" name="dob" id="dob"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="email" class="cols-sm-2 control-label">Your Email</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="email" id="email"  placeholder="Enter your Email"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="phone" class="cols-sm-2 control-label">Your Phone Number</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="phone" id="phone"  placeholder="Enter your Phone Number"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="address" class="cols-sm-2 control-label">Your Postal Address</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="address" id="address"  value="${user.address}"/>
                        </div>
                    </div>
                </div>

                <div class="form-group ">
                    <button type="submit" class="btn btn-primary btn-lg btn-block login-button">Register</button>
                </div>
                <div class="login-register">
                    <a href="/">Home</a>
                </div>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="template-parts/footer.jsp" />