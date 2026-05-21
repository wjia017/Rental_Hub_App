<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Browse Clothes | Rental Hub</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css?v=10">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
</head>
<body>

<div class="layout">

    <aside class="sidebar">
        <div class="logo">
            <i class="fa-solid fa-bag-shopping"></i>
            <div>
                <h2>Rental Hub</h2>
                <p>User Portal</p>
            </div>
        </div>

        <nav>
            <a href="${pageContext.request.contextPath}/user-dashboard">
                <i class="fa-solid fa-house"></i> Home
            </a>

            <a href="${pageContext.request.contextPath}/browse-clothes" class="active">
                <i class="fa-solid fa-magnifying-glass"></i> Browse Clothes
            </a>

            <a href="${pageContext.request.contextPath}/my-rentals">
                <i class="fa-solid fa-cube"></i> My Rentals
            </a>

            <a href="${pageContext.request.contextPath}/profile">
                <i class="fa-solid fa-user"></i> Profile
            </a>

            <a href="${pageContext.request.contextPath}/logout">
                <i class="fa-solid fa-right-from-bracket"></i> Logout
            </a>
        </nav>
    </aside>

    <main class="content">

        <header class="topbar">
            <div class="search-box">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" placeholder="Search clothes, rentals...">
            </div>

            <div class="user-info">
                <c:choose>
                    <c:when test="${not empty loggedUser.profileImage}">
                        <img class="top-profile-img"
                             src="${pageContext.request.contextPath}/get-image?file=${loggedUser.profileImage}"
                             alt="Profile Image">
                    </c:when>

                    <c:otherwise>
                        <div class="initial-circle">
                            ${loggedUser.firstName.charAt(0)}
                        </div>
                    </c:otherwise>
                </c:choose>

                <div>
                    <strong>${loggedUser.firstName} ${loggedUser.lastName}</strong>
                    <p>User</p>
                </div>
            </div>
        </header>

        <section class="page-title">
            <h1>Browse Clothes</h1>
            <p>Discover and rent your perfect outfit.</p>
        </section>

        <section class="clothes-grid">

            <c:forEach var="cloth" items="${clothes}">
                <div class="cloth-card">

                    <div class="cloth-image-box">
                        <c:choose>
                            <c:when test="${not empty cloth.imageName}">
                                <img src="${pageContext.request.contextPath}/get-cloth-image?file=${cloth.imageName}"
                                     alt="${cloth.clothName}">
                            </c:when>

                            <c:otherwise>
                                <i class="fa-solid fa-shirt empty-cloth-icon"></i>
                            </c:otherwise>
                        </c:choose>

                        <span class="badge">${cloth.category}</span>
                    </div>

                    <div class="cloth-info">
                        <h3>${cloth.clothName}</h3>

                        <p class="description">${cloth.description}</p>

                        <p class="price">Rs. ${cloth.rentalPrice}/day</p>

                        <c:choose>
                            <c:when test="${cloth.status == 'available'}">
                                <span class="available">Available</span>

                                <a href="${pageContext.request.contextPath}/rent-page?id=${cloth.clothId}"
                                   class="rent-btn">
                                    Rent Now
                                </a>
                            </c:when>

                            <c:otherwise>
                                <span class="unavailable">Unavailable</span>
                            </c:otherwise>
                        </c:choose>
                    </div>

                </div>
            </c:forEach>

        </section>

    </main>

</div>

</body>
</html>