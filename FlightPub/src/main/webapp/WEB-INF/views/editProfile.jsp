<%--
  Created by IntelliJ IDEA.
  User: tschw
  Date: 17/08/2018
  Time: 4:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="template-parts/header.jsp" />

<div class="container">
    <h1>Edit your profile</h1>
    <div class="rowx mainx">
        <div class="main-login main-center">
            <form:form class="form-horizontal" method="post" action="update-profile">

                <h3 class = "result-message">${resultMessage}</h3>

                <div class="form-group">
                    <label for="firstname" class="cols-sm-2 control-label">Your First Name</label>
                    <div class="cols-sm-12">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="firstname" id="firstname"  value="${user.firstname}"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="middlename" class="cols-sm-2 control-label">Your Middle Name</label>
                    <div class="cols-sm-12">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="middlename" id="middlename"  value="${user.middlename}"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="lastname" class="cols-sm-2 control-label">Your Last Name</label>
                    <div class="cols-sm-12">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="lastname" id="lastname"  value="${user.lastname}"/>
                        </div>
                    </div>
                </div>

                <!--<div class="form-group">
                    <label for="dob" class="cols-sm-2 control-label">Your Date of Birth</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <input type="date" class="form-control" name="dob" id="dob"/>
                        </div>
                    </div>
                </div>-->

                <div class="form-group">
                    <label for="email" class="cols-sm-2 control-label">Your Email</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="email" id="email"  value="${user.email}"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="phone" class="cols-sm-2 control-label">Your Phone Number</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="phone" id="phone"  value="${user.phonenumber}"/>
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
                    <button type="submit" class="btn btn-primary btn-lg btn-block login-button">Save</button>
                </div>

            </form:form>

            <form action="/account/home" method="get">
                <div class="form-group ">
                    <button type="submit" class="btn btn-primary btn-lg btn-block login-button">Return to Profile</button>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="template-parts/footer.jsp" />
