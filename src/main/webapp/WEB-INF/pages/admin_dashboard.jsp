<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard | Rental Hub</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
</head>
<body>

<div class="layout">

    <aside class="sidebar">
        <div class="logo">
            <i class="fa-solid fa-bag-shopping"></i>
            <div>
                <h2>ClothRent</h2>
                <p>Admin Portal</p>
            </div>
        </div>

        <nav>
            <a href="${pageContext.request.contextPath}/admin-dashboard" class="active">
                <i class="fa-solid fa-house"></i> Dashboard
            </a>

            <a href="${pageContext.request.contextPath}/manage-users">
                <i class="fa-solid fa-users"></i> Users
            </a>

            <a href="${pageContext.request.contextPath}/clothes">
                <i class="fa-solid fa-shirt"></i> Clothes
            </a>

            <a href="${pageContext.request.contextPath}/admin-rentals">
                <i class="fa-solid fa-square-check"></i> Rentals
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
                <i class="fa-regular fa-user"></i>
                <div>
                    <strong>${loggedUser.firstName} ${loggedUser.lastName}</strong>
                    <p>Administrator</p>
                </div>
            </div>
        </header>

        <section class="page-title">
            <h1>Admin Dashboard</h1>
            <p>Overview of your rental management system</p>
        </section>

        <section class="stats-grid">

            <div class="stat-card">
                <i class="fa-solid fa-users"></i>
                <p>Total Users</p>
                <h2>${totalUsers}</h2>
            </div>

            <div class="stat-card">
                <i class="fa-solid fa-shirt"></i>
                <p>Total Clothes</p>
                <h2>${totalClothes}</h2>
            </div>

            <div class="stat-card">
                <i class="fa-solid fa-arrow-trend-up"></i>
                <p>Active Rentals</p>
                <h2>${activeRentals}</h2>
            </div>

            <div class="stat-card">
                <i class="fa-solid fa-dollar-sign"></i>
                <p>Monthly Revenue</p>
                <h2>Rs. ${monthlyRevenue}</h2>
            </div>

        </section>

        <section class="activity-card">
            <h2>Recent Activity</h2>

            <div class="activity-item">
                <div>
                    <h4>New user registered</h4>
                    <p>Customer account created</p>
                </div>
                <span>Today</span>
            </div>

            <div class="activity-item">
                <div>
                    <h4>Rental request pending</h4>
                    <p>Waiting for admin approval</p>
                </div>
                <span>Today</span>
            </div>

            <div class="activity-item">
                <div>
                    <h4>New clothing item added</h4>
                    <p>Admin inventory updated</p>
                </div>
                <span>Today</span>
            </div>
        </section>

    </main>

</div>

</body>
</html>