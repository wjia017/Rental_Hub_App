<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Rentals | Rental Hub</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
</head>
<body>

<div class="layout">

    <aside class="sidebar">
        <div class="logo">
            <i class="fa-solid fa-shirt"></i>
            <div>
                <h2>Rental Hub</h2>
                <p>User Portal</p>
            </div>
        </div>

        <nav>
            <a href="${pageContext.request.contextPath}/user-dashboard">
                <i class="fa-solid fa-house"></i> Home
            </a>

            <a href="${pageContext.request.contextPath}/browse-clothes">
                <i class="fa-solid fa-magnifying-glass"></i> Browse Clothes
            </a>

            <a href="${pageContext.request.contextPath}/my-rentals" class="active">
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
                <input type="text" placeholder="Search my rentals...">
            </div>

            <div class="user-info">
                <i class="fa-regular fa-user"></i>
                <div>
                    <strong>${loggedUser.firstName} ${loggedUser.lastName}</strong>
                    <p>User</p>
                </div>
            </div>
        </header>

        <section class="page-title">
            <h1>My Rentals</h1>
            <p>Track your rental requests and approval status.</p>
        </section>

        <section class="table-card">
            <table>
                <thead>
                    <tr>
                        <th>Cloth</th>
                        <th>Rental Date</th>
                        <th>Return Date</th>
                        <th>Total Price</th>
                        <th>Status</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="rental" items="${rentals}">
                        <tr>
                            <td>${rental.clothName}</td>
                            <td>${rental.rentalDate}</td>
                            <td>${rental.returnDate}</td>
                            <td>Rs. ${rental.totalPrice}</td>
                            <td><span class="status">${rental.status}</span></td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty rentals}">
                        <tr>
                            <td colspan="5" class="empty">No rental requests found.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </section>

    </main>

</div>

</body>
</html>