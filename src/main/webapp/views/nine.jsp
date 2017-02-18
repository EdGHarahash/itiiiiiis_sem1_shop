<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>HMM3 Castle</title>
    <link rel="shortcut icon" href="../img/icon.jpg" type="image/png">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/shop.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../css/styles.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
</head>
<body>
    <%
        int role = session.getAttribute("account") != null ? (Integer) session.getAttribute("role") : 0;
    %>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="logo redd" href="products?page=1">HMM3 Castle</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <%if (role == 0) {%>
                <li><a href='/login'>Sign In</a></li>
                <li><a href="/registration">Sing Up</a></li>
                <%} else {%>
                <li><a href='/logout'>Logout</a></li>
                <%}%>
                <%if (role == 1) {%>
                <li><a href="/bucket">Bucket</a></li>
                <%}%>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>
