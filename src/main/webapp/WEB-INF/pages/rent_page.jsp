<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Rent Cloth | Rental Hub</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/rent_page.css">
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

            <div></div>

            <div class="user-info">
                <c:choose>
                    <c:when test="${not empty loggedUser.profileImage}">
                        <img class="top-profile-img"
                             src="${pageContext.request.contextPath}/get-image?file=${loggedUser.profileImage}"
                             alt="Profile Image">
                    </c:when>

                    <c:otherwise>
                        <i class="fa-regular fa-user"></i>
                    </c:otherwise>
                </c:choose>

                <div>
                    <strong>${loggedUser.firstName} ${loggedUser.lastName}</strong>
                    <p>User</p>
                </div>
            </div>

        </header>

        <section class="rent-wrapper">

            <div class="cloth-preview">

                <div class="cloth-image-large">
                    <c:choose>
                        <c:when test="${not empty cloth.imageName}">
                            <img src="${pageContext.request.contextPath}/get-cloth-image?file=${cloth.imageName}"
                                 alt="${cloth.clothName}">
                        </c:when>

                        <c:otherwise>
                            <i class="fa-solid fa-shirt"></i>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="cloth-detail">
                    <span class="category-badge">${cloth.category}</span>

                    <h1>${cloth.clothName}</h1>

                    <p class="description">
                        ${cloth.description}
                    </p>

                    <div class="detail-list">
                        <p>
                            <strong>Size:</strong>
                            ${cloth.size}
                        </p>

                        <p>
                            <strong>Color:</strong>
                            ${cloth.color}
                        </p>

                        <p>
                            <strong>Status:</strong>
                            ${cloth.status}
                        </p>

                        <p>
                            <strong>Price Per Day:</strong>
                            Rs. ${cloth.rentalPrice}
                        </p>
                    </div>
                </div>

            </div>

            <div class="rent-card">

                <h2>Rental Request</h2>
                <p>Select rental and return date to send request.</p>

                <form action="${pageContext.request.contextPath}/rent-cloth"
                      method="post">

                    <input type="hidden"
                           name="cloth_id"
                           value="${cloth.clothId}">

                    <label>Rental Date</label>
                    <input type="date"
                           name="rental_date"
                           required>

                    <label>Return Date</label>
                    <input type="date"
                           name="return_date"
                           required>

                    <div class="price-note">
                        <i class="fa-solid fa-circle-info"></i>
                        Total price will be calculated by the system based on number of days.
                    </div>

                    <button type="submit">
                        <i class="fa-solid fa-calendar-check"></i>
                        Confirm Rental Request
                    </button>

                </form>

                <a href="${pageContext.request.contextPath}/browse-clothes"
                   class="back-link">
                    <i class="fa-solid fa-arrow-left"></i>
                    Back to Browse Clothes
                </a>

            </div>

        </section>

    </main>

</div>

</body>
</html>